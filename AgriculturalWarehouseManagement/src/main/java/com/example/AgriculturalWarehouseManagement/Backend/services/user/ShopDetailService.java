package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.FilterShopDetailRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductUserHomepageResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ShopDetailResponse;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopDetailService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;

    public List<ShopDetailResponse> getShopDetailProduct() {
        List<Object[]> raw = productRepository.rawShopDetailsOfProducts();

        return raw.stream().map(row -> new ShopDetailResponse(
                ((Number) row[0]).intValue(),       // productId
                ((Number) row[1]).intValue(),       // categoryId
                (String) row[2],                   // imageUrlGallery
                (String) row[3],                   // productName
                ((Number) row[4]).intValue(),   // ratingProductDetail
                ((Number) row[5]).intValue(),   // ratingCount
                (String) row[6],                   // productDetailName
                ((Number) row[7]).doubleValue(),   // productPrice
                ((String) row[8]),            // productWeight (weight + unit)
                (String) row[9]                 // productDescription
        )).toList();
    }


    public List<ShopDetailResponse> getShopDetailProducts() {

        if (getShopDetailProduct().isEmpty()) {
            return new ArrayList<>();
        } else {
            List<ShopDetailResponse> response = getShopDetailProduct();

            return response;
        }

    }

    public List<ShopDetailResponse> getFilterShopDetailsOfProducts(FilterShopDetailRequest request, String searchName) {
        String sql = """
        WITH first_img AS (
            SELECT g.ProductID, g.ImageURL
            FROM Gallery g
            JOIN (
                SELECT ProductID, MIN(GalleryID) AS min_id
                FROM Gallery
                GROUP BY ProductID
            ) f ON f.ProductID = g.ProductID AND f.min_id = g.GalleryID
        ),
        avg_rating AS (
            SELECT ProductID, ROUND(AVG(Rating)) AS avgRating, COUNT(*) AS ratingCount
            FROM CommentProduct
            GROUP BY ProductID
        ),
        cheapest_detail AS (
            SELECT pd.*, ROW_NUMBER() OVER (PARTITION BY pd.ProductID ORDER BY pd.Price ASC, pd.ProductDetailID ASC) AS rn
            FROM ProductDetail pd
        )
        SELECT p.ProductID, p.CategoryID, fi.ImageURL, p.ProductName,
               COALESCE(ar.avgRating, 0), COALESCE(ar.ratingCount, 0),
               cd.DetailName, cd.Price, CONCAT(cw.weight, ' ', cw.unit),
               p.Description
        FROM Product p
        JOIN cheapest_detail cd ON cd.ProductID = p.ProductID AND cd.rn = 1
        JOIN categoryWeight cw ON cw.CategoryWeightID = cd.CategoryWeightID
        LEFT JOIN first_img fi ON fi.ProductID = p.ProductID
        LEFT JOIN avg_rating ar ON ar.ProductID = p.ProductID
        WHERE p.Status = 'ACTIVE'
    """;

        Map<String, Object> params = new HashMap<>();

        if (searchName != null && !searchName.isBlank()) {
            sql += " AND p.ProductName LIKE :searchName";
            params.put("searchName", "%" + searchName + "%");
        }

        if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()) {
            sql += " AND p.CategoryID IN :categoryIds";
            params.put("categoryIds", request.getCategoryIds());
        }

        if (request.getSortBy().toLowerCase().equals("rating")) {
            sql += " AND COALESCE(ar.avgRating, 0) > 0";
        }

        sql += " AND cd.Price BETWEEN :minPrice AND :maxPrice";
        params.put("minPrice", request.getMinPrice());
        params.put("maxPrice", request.getMaxPrice());

        switch (request.getSortBy().toLowerCase()) {
            case "popularity"   -> sql += " ORDER BY COALESCE(ar.avgRating, 0) DESC";
            case "pricelowhigh" -> sql += " ORDER BY cd.Price ASC";
            case "pricehighlow" -> sql += " ORDER BY cd.Price DESC";
            case "atoz"         -> sql += " ORDER BY p.ProductName ASC";
            case "ztoa"         -> sql += " ORDER BY p.ProductName DESC";
        }

        jakarta.persistence.Query query = entityManager.createNativeQuery(sql);

        params.forEach(query::setParameter);

        @SuppressWarnings("unchecked")
        List<Object[]> resultList = query.getResultList();

        List<ShopDetailResponse> responses = new ArrayList<>();

        for (Object[] row : resultList) {
            ShopDetailResponse res = new ShopDetailResponse();
            res.setProductId(((Number) row[0]).intValue());
            res.setCategoryId(((Number) row[1]).intValue());
            res.setImageUrlGallery((String) row[2]);
            res.setProductName((String) row[3]);
            res.setRatingProductDetail(((Number) row[4]).intValue());
            res.setRatingCount(((Number) row[5]).intValue());
            res.setProductDetailName((String) row[6]);
            res.setProductPrice(((Number) row[7]).doubleValue());
            res.setProductWeight((String) row[8]);
            res.setProductDescription((String) row[9]);
            responses.add(res);
        }

        return responses;
    }


}
