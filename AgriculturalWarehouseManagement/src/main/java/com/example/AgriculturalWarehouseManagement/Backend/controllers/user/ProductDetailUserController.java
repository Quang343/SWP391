package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.CommentProductUserRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.*;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductDetailUserController {

    @Autowired
    private ProductDetailUserService productDetailUserService;

    @Autowired
    private ShopDetailService shopDetailService;

    @Autowired
    private CategoryUsersService categoryUsersService;

    @Autowired
    private CommentProductUserService commentProductUserService;

    @Autowired
    private CartUserService cartUserService;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @GetMapping("/productDetail")
    public String productDetail(@RequestParam(name = "productId", defaultValue = "0") int productId,
                                @RequestParam(name = "productDetailId", defaultValue = "0", required = false) int productDetailId,
                                @RequestParam(name = "quantity", defaultValue = "-1", required = false) int quantity,
                                Model model) {

        // Trending Products
        List<ProductDetailUserResponse> trendingProducts = productDetailUserService.getTrendingProducts();
        model.addAttribute("trendingProducts", trendingProducts);

        // Related Products
        List<CategoryUsersResponse> categoryUsersResponses = categoryUsersService.getAllListCategory();
        model.addAttribute("categoryUsersResponses", categoryUsersResponses);

        int categoryId = shopDetailService.getProductByProductId(productId); // Get ProductByProductId return CategoryId
        List<ShopDetailResponse> relatedProducts = shopDetailService.getShopDetailsOfProductsByCategoryIds(categoryId);
        model.addAttribute("relatedProducts", relatedProducts);

        // View gallery
        List<GalleryUserResponse> galleryUserResponses = shopDetailService.getGalleryUsers(productId);
        if (galleryUserResponses == null || galleryUserResponses.isEmpty()) {
            galleryUserResponses = new ArrayList<>();

            GalleryUserResponse galleryUserResponse = new GalleryUserResponse();
            galleryUserResponse.setProductId(0);
            galleryUserResponse.setGalleryId(0);
            galleryUserResponse.setImageUrl("");

            galleryUserResponses.add(galleryUserResponse);
        }
        model.addAttribute("galleryUserResponses", galleryUserResponses);

        // View Description
        String productDescription = shopDetailService.getProductDescriptionUser(productId);
        model.addAttribute("productDescription", productDescription);

        // Overall rating
        OverallRatingProductResponse overallRatingProductResponse = shopDetailService.getOverallRatingProducts(productId);
        int totalRatings = overallRatingProductResponse.getRatingFive() + overallRatingProductResponse.getRatingFour() + overallRatingProductResponse.getRatingThree() + overallRatingProductResponse.getRatingTwo() + overallRatingProductResponse.getRatingOne();
        model.addAttribute("overallRatingProductResponse", overallRatingProductResponse);
        if (totalRatings == 0) model.addAttribute("totalRatings", "1");
        else model.addAttribute("totalRatings", totalRatings);


        // Get logic productdetail
        List<ProductDetailUserResponse> productDetailUserResponses = productDetailUserService.getProductUsers(productId);
        int avgRating = productDetailUserResponses.size() != 0 ? productDetailUserResponses.get(0).getAvgRating() : 0;
        int ratingCount = productDetailUserResponses.size() != 0 ? productDetailUserResponses.get(0).getRatingCount() : 0;
        model.addAttribute("avgRating", avgRating);
        model.addAttribute("ratingCount", ratingCount);

        String imageUrl = productDetailUserResponses.isEmpty() ? "" : galleryUserResponses.get(0).getImageUrl();
        String productName = shopDetailService.getProductNameUser(productId);
        model.addAttribute("imageUrl", imageUrl);
        for (ProductDetailUserResponse productDetailUserResponse : productDetailUserResponses) {
            System.out.println("hello2" + productDetailUserResponse);
        }
        model.addAttribute("productName", productName);
        model.addAttribute("productId", productId);

        // View Comment product
        List<ViewCommentProductUserResponse> viewCommentProductUserResponses = commentProductUserService.getViewCommentProuducts(productId);
        model.addAttribute("viewCommentProductUserResponses", viewCommentProductUserResponses);

        // View seller
        if (productDetailUserResponses == null || productDetailUserResponses.isEmpty()) {
            model.addAttribute("emailSeller", "Chưa có người bán");
        } else {
            if (productDetailId  == 0){
                int productDetailNewId = productDetailUserResponses.get(0).getProductDetailId();
                ViewSellerResponse viewSellerResponse = productDetailUserService.getViewSellerProductDetailObject(productDetailNewId);
                model.addAttribute("emailSeller", viewSellerResponse.getEmailSeller());
            } else {
                ViewSellerResponse viewSellerResponse = productDetailUserService.getViewSellerProductDetailObject(productDetailId);
                model.addAttribute("emailSeller", viewSellerResponse.getEmailSeller());
            }
        }


        // View Weights
        List<WeightCompareProductDetailsResponse> weightProductDetailsResponses = productDetailUserService.getcompareProductDetails(productId);
        model.addAttribute("weightProductDetailsResponses", weightProductDetailsResponses);
        if (productDetailId == 0) {
            model.addAttribute("productDetailId", weightProductDetailsResponses.isEmpty() ? 0 : weightProductDetailsResponses.get(0).getProductDetailId());
            model.addAttribute("categoryWeightId", weightProductDetailsResponses.isEmpty() ? 0 : weightProductDetailsResponses.get(0).getCategoryWeightId());
            model.addAttribute("priceProductDetail", weightProductDetailsResponses.isEmpty() ? 0 : weightProductDetailsResponses.get(0).getPrice());

            if (!weightProductDetailsResponses.isEmpty()) {
                ProductDetailUserResponse productDetailUserResponse = productDetailUserService.getProductDetailUsers(weightProductDetailsResponses.get(0).getProductDetailId());
                model.addAttribute("productDetailUserResponse", productDetailUserResponse);
            }

            session.setAttribute("productId", productId);
            session.setAttribute("productDetailId", weightProductDetailsResponses.isEmpty() ? 0 : weightProductDetailsResponses.get(0).getProductDetailId());


        } else {
            model.addAttribute("productDetailId", productDetailId);
            for (WeightCompareProductDetailsResponse weightCompareProductDetailsResponse : weightProductDetailsResponses) {
                if (weightCompareProductDetailsResponse.getProductDetailId() == productDetailId) {
                    model.addAttribute("priceProductDetail", weightCompareProductDetailsResponse.getPrice());
                    model.addAttribute("categoryWeightId", weightCompareProductDetailsResponse.getCategoryWeightId());
                    break;
                }
            }

            ProductDetailUserResponse productDetailUserResponse = productDetailUserService.getProductDetailUsers(productDetailId);
            model.addAttribute("productDetailUserResponse", productDetailUserResponse);

            session.setAttribute("productId", productId);
            session.setAttribute("productDetailId", productDetailId);

            if (quantity > 0) {
                ResponseResult<ProductDetailUserResponse> result = productDetailUserService.checkQuantityProduct(quantity, productDetailId);
                if (result.isActive()) {
                    session.removeAttribute("productId");
                    session.removeAttribute("productDetailId");

                    session.setAttribute("productDetailIdCart", productDetailId);
                    session.setAttribute("quantityCart", quantity);

                    return "redirect:/cart";
                } else {
                    model.addAttribute("quantityError", result.getMessage());
                }
            } else if (quantity == 0) {
                model.addAttribute("quantityError", "Số lượng bạn nhập không được bằng 0");
            } else if (quantity < -1) {
                model.addAttribute("quantityError", "Số lượng bạn nhập không được nhỏ hơn 0");
            }

        }

        // View cart
        Object accountIdObj = session.getAttribute("accountId");
        if (accountIdObj != null) {
            int accountId = (int) accountIdObj;

            List<CartUserResponse> cartUserResponses = cartUserService.getCartByUserIds(accountId);

            int limit = Math.min(cartUserResponses.size(), 3);

            List<CartUserResponse> limitedCartUserResponses = cartUserResponses.subList(0, limit);

            model.addAttribute("sizeCart", cartUserResponses.size());
            model.addAttribute("cartUserResponses", limitedCartUserResponses);
            model.addAttribute("sizeCartBelow", Math.max(cartUserResponses.size() - 3, 0));


            double totalCart = 0;
            for (CartUserResponse cartUserResponse : cartUserResponses) {
                totalCart += cartUserResponse.getTotalPrice();
            }

            model.addAttribute("totalCart", totalCart);
        }

        return "FrontEnd/Home/productDetail";
    }

    @PostMapping("/commentProduct")
    public String insertCommentProduct(@ModelAttribute CommentProductUserRequest request) {
        int productId = request.getProductId();
        int accountId = request.getAccountId();
        int rating = request.getRating();
        String content = request.getContent();
        commentProductUserService.insertProduct(productId, accountId, rating, content);

        return "redirect:/productDetail?productId=" + productId;
    }
}
