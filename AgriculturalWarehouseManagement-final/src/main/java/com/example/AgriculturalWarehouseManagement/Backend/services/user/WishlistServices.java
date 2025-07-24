package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.*;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.models.Wishlist;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistServices {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductUsersHomepageService productUsersHomepageService;


    public WishlistAPIResponse insertWishlist(int userId, int productId) {
        System.out.println(productId);
        Optional<User> user = userRepository.findById((long) userId);
        Optional<Product> product = productRepository.findById(productId);
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user.get());
        wishlist.setProduct(product.get());
        wishlistRepository.save(wishlist);

        WishlistAPIResponse wishlistAPIResponse = new WishlistAPIResponse();
        wishlistAPIResponse.setWishlistId(wishlist.getWishlistId());
        wishlistAPIResponse.setUserId(user.get().getUserId());
        wishlistAPIResponse.setProductId(productId);

        return wishlistAPIResponse;
    }

    public List<WishlistResponse> getWishlist() {
        List<Object[]> raw = wishlistRepository.listofWishlists();

        return raw.stream().map(row -> new WishlistResponse(
                ((Number) row[0]).intValue(),      // userId
                ((Number) row[1]).intValue()       // productId
        )).toList();
    }

    public List<ProductUserHomepageResponse> getListOfWishlist(int userId) {
        if (getWishlist().isEmpty()) {
            return new ArrayList<>();
        } else {
            List<WishlistResponse> response = getWishlist();
            List<ProductUserHomepageResponse> productUserHomepage = productUsersHomepageService.getProductUsersHomepages();
            List<ProductUserHomepageResponse> productUserHomepageWishlist = new ArrayList<>();
            for (WishlistResponse wishlistResponse : response) {
                for (ProductUserHomepageResponse productUserHomepageResponse : productUserHomepage) {
                    if (productUserHomepageResponse.getProductId() == wishlistResponse.getProductId() &&
                            wishlistResponse.getUserId() == userId) {
                        productUserHomepageWishlist.add(productUserHomepageResponse);
                    }
                }
            }
            return productUserHomepageWishlist;
        }


    }

    public List<WishlistTableResponse> getWishlistTable() {
        List<Object[]> raw = wishlistRepository.allWishlists();

        return raw.stream().map(row -> new WishlistTableResponse(
                ((Number) row[0]).intValue(),      // wishlistId
                ((Number) row[1]).intValue(),      // userId
                ((Number) row[2]).intValue()       // productId
        )).toList();
    }

    public ResponseResult<WishlistResponse> deleteWishlistByProductIdAndUserId(int userID, int productId) {

        if (getWishlistTable().isEmpty()){
            return new ResponseResult<>("ERROR","Delete Wishlist Error",false);
        }

        List<WishlistTableResponse> wishlistTableResponses = getWishlistTable();
        List<Integer> wishlistIds = new ArrayList<>();
        for (WishlistTableResponse wishlistTableResponse : wishlistTableResponses) {
            if (wishlistTableResponse.getProductId() == productId &&
            wishlistTableResponse.getUserId() == userID) {
                wishlistIds.add(wishlistTableResponse.getWishlistId());
            }
        }

        for (Integer wishlistId : wishlistIds) {
            wishlistRepository.deleteById((long)wishlistId);
        }

        return new ResponseResult<>("SUCCESS","Delete Wishlist Success",true);
    }

    public ResponseResult<WishlistResponse> deleteAllWishlist(int userID) {

        int deleteAllWishlist = wishlistRepository.deleteByUserId(userID);

        if (deleteAllWishlist > 0) {
            return new ResponseResult<>("SUCCESS","Delete Wishlist Success",true);
        }

        return new ResponseResult<>("ERROR","Delete Wishlist Error",false);
    }
}
