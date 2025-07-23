package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.*;
import com.example.AgriculturalWarehouseManagement.Backend.models.Cart;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.CartRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductDetailRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductDetailUserService productDetailUserService;

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    public ResponseResult<CartUserResponse> insertCart(int accountId, int productDetailIdCart, int quantityCart) {

        ProductDetailUserResponse productDetailUserResponse = productDetailUserService.getProductDetailUsers(productDetailIdCart);

        if (productDetailUserResponse.getStatus().equals("Hết hàng")) {
            return new ResponseResult<>("ERROR", "Sản phẩm đã hết hàng", false);
        }

        if (productDetailUserResponse.getExpiryStatus().equals("Hết hạn")) {
            return new ResponseResult<>("ERROR", "Sản phẩm đã hết hạn", false);
        }

        Optional<User> user = userRepository.findByUserIdNative(accountId);
        Optional<ProductDetail> productDetail = productDetailRepository.findById(productDetailIdCart);
        double price = productDetail.get().getPrice();
        double total = quantityCart * price;

        Cart cart = new Cart();
        cart.setUser(user.get());
        cart.setProductDetail(productDetail.get());
        cart.setQuantity(quantityCart);
        cart.setTotal(total);

        cartRepository.save(cart);

        return new ResponseResult<>("SUCCESS", "Thêm giỏ hàng thành công", true);
    }

    // View cart
    public List<CartUserResponse> getCartByUserId(int accountId) {
        List<Object[]> raw = cartRepository.rawGetCartByUserID(accountId);

        List<CartUserResponse> list = raw.stream().map(row -> new CartUserResponse(
                ((Number) row[0]).intValue(),                // cartId
                ((Number) row[1]).intValue(),               // userId
                ((Number) row[2]).intValue(),               // productId
                ((Number) row[3]).intValue(),               // productDetailId
                (String) row[4],                            // imageUrl
                (String) row[5],                            // productName
                (String) row[6],                            // sellerName
                (String) row[7],                            // productWeight
                ((Number) row[8]).doubleValue(),               // productPrice
                ((Number) row[9]).intValue(),               // quantity
                ((Number) row[10]).doubleValue()                // totalPrice
        )).toList();
        return list;

    }

    public List<CartUserResponse> getCartByUserIds(int accountId) {
        if (getCartByUserId(accountId).isEmpty()) {
            return new ArrayList<>();
        } else {
            List<CartUserResponse> response = getCartByUserId(accountId);
            return response;
        }
    }

    public ResponseResult<List<CartUserResponse>> getCartHomeByUserIds(int accountId) {
        if (getCartByUserId(accountId).isEmpty()) {
            return new ResponseResult<>("ERROR", "Không có list cart", false, new ArrayList<>());
        } else {
            List<CartUserResponse> response = getCartByUserId(accountId);
            return new ResponseResult<>("SUCCESS", "Lấy list cart thành công", true, response);
        }
    }

    // Update API
    public ResponseResult<CartUserResponse> updateCartAPI(int cartId, int productDetailId, int quantity) {

        ProductDetailUserResponse productDetailUserResponse = productDetailUserService.getProductDetailUsers(productDetailId);

        if (productDetailUserResponse.getStatus().equals("Hết hàng")) {
            return new ResponseResult<>("ERROR", "Sản phẩm đã hết hàng", false);
        }

        if (productDetailUserResponse.getExpiryStatus().equals("Hết hạn")) {
            return new ResponseResult<>("ERROR", "Sản phẩm đã hết hạn", false);
        }

        if (productDetailUserResponse.getRemainQuantity() < quantity) {
            return new ResponseResult<>("ERROR", "Số lượng sản phẩm trong shop không đủ", false);
        }

        ProductDetail productDetail = productDetailRepository.findById(productDetailId).get();
        Cart cart = cartRepository.findById((long) cartId).get();
        double total = productDetail.getPrice() * quantity;

        cart.setQuantity(quantity);
        cart.setTotal(total);
        cartRepository.save(cart);

        CartUserResponse cartUserReponse = new CartUserResponse();
        cartUserReponse.setTotalPrice(total);
        return new ResponseResult<>("SUCCESS", "Update số lượng sản phẩm thành công", true, cartUserReponse);
    }

    // Get data of table cart
    public List<CartTableUserResponse> getCartTableByUserId(int accountId) {
        List<Object[]> raw = cartRepository.rawGetTableCartByUserID(accountId);

        List<CartTableUserResponse> list = raw.stream().map(row -> new CartTableUserResponse(
                ((Number) row[0]).intValue(),                // cartId
                ((Number) row[1]).intValue(),               // userId
                ((Number) row[2]).intValue(),               // productDetailId
                ((Number) row[3]).intValue(),               // quantity
                ((Number) row[4]).doubleValue()           // total
        )).toList();
        return list;

    }

    public ResponseResult<TotalCartUserResponse> getTotalCart(int accountId) {

        if (getCartByUserId(accountId).isEmpty()) {
            return new ResponseResult<>("ERROR", "Chưa có tài khoản để thêm sản phẩm vào giỏ hàng", false);
        } else {
            double totalCart = 0;
            for (CartTableUserResponse cartUserResponse : getCartTableByUserId(accountId)) {
                totalCart += cartUserResponse.getPrice();
            }

            TotalCartUserResponse totalCartUserResponse = new TotalCartUserResponse();
            totalCartUserResponse.setUserId(accountId);
            totalCartUserResponse.setTotalCart(totalCart);

            return new ResponseResult<>("SUCCESS", "Đã lấy ra total cart", true, totalCartUserResponse);
        }

    }

    // Get productdetail by productId to Add to cart API
    public List<ProductDetailUserResponse> getProductUser(Integer productId) {
        List<Object[]> raw = productDetailRepository.rawGetProductbyIds(productId);

        return raw.stream().map(row -> new ProductDetailUserResponse(
                ((Number) row[0]).intValue(),                // categoryId
                ((Number) row[1]).intValue(),               // productId
                (String) row[2],                            // productName
                (String) row[3],                            // productDescription
                (String) row[4],                            // imageUrl
                ((Number) row[5]).intValue(),               // productDetailId
                (String) row[6],                            // productWeight
                ((Number) row[7]).intValue(),               // batchId
                ((Number) row[8]).intValue(),               // importedQuantity
                ((Number) row[9]).intValue(),               // soldQuantity
                ((java.sql.Date) row[10]).toLocalDate(),     // manufactureDate
                ((Number) row[11]).intValue(),              // expiry
                ((Number) row[12]).doubleValue(),              // Price
                ((java.sql.Date) row[13]).toLocalDate(),    // expiryDate
                ((Number) row[14]).intValue(),              // totalAdjustedRemoveQuantity
                ((Number) row[15]).intValue(),              // remainQuantity
                (String) row[16],                           // status
                (String) row[17],                            // expiryStatus
                ((Number) row[18]).intValue(),                  // avgRating
                ((Number) row[19]).intValue()                   // ratingCount
        )).toList();

    }

    public ResponseResult<CartTableUserResponse> addToCart(int productId, int userId) {

        if (getProductUser(productId).isEmpty()) {
            return new ResponseResult<>("ERROR", "Không có sản phẩm chi tiết", false);
        } else {

            int productDetailId = 0;
            String statusMessageStatus = "";
            String statusMessageStatusExpire = "";

            for (ProductDetailUserResponse productDetailUserResponse : getProductUser(productId)) {
                if (productDetailUserResponse.getStatus().equals("Hết hàng")) {
                    statusMessageStatus = "Hết hàng";
                    continue;
                }

                if (productDetailUserResponse.getExpiryStatus().equals("Hết hạn")) {
                    statusMessageStatusExpire = "Hết hạn";
                    continue;
                }

                productDetailId = productDetailUserResponse.getProductDetailId();
                break;
            }

            System.out.println("aptcart" + productDetailId);

            if (productDetailId == 0) {
                if (statusMessageStatus.equals("")) {
                    return new ResponseResult<>("ERROR", "Sản phẩm đã " + statusMessageStatusExpire, false);
                }

                return new ResponseResult<>("ERROR", "Sản phẩm đã " + statusMessageStatus, false);

            } else {

                Optional<User> user = userRepository.findByUserIdNative(userId);
                Optional<ProductDetail> productDetail = productDetailRepository.findById(productDetailId);
                double totalPrice = 1 * productDetail.get().getPrice();

                Cart cart = new Cart();
                cart.setUser(user.get());
                cart.setProductDetail(productDetail.get());
                cart.setQuantity(1);
                cart.setTotal(totalPrice);

                cartRepository.save(cart);

                CartTableUserResponse cartTableUserResponse = new CartTableUserResponse();
                cartTableUserResponse.setUserId(userId);
                cartTableUserResponse.setProductDetailId(productDetailId);
                cartTableUserResponse.setQuantity(1);
                cartTableUserResponse.setPrice(totalPrice);

                return new ResponseResult<>("SUCCESS", "Thêm sản phẩm vào giỏ hàng thành công", true, cartTableUserResponse);
            }

        }


    }


    // Get productdetail by productId to Add to cart API
    public List<ProductDetailUserResponse> getProductDetailUser(Integer productId) {
        List<Object[]> raw = productDetailRepository.rawGetProductDetails(productId);

        return raw.stream().map(row -> new ProductDetailUserResponse(
                ((Number) row[0]).intValue(),                // categoryId
                ((Number) row[1]).intValue(),               // productId
                (String) row[2],                            // productName
                (String) row[3],                            // productDescription
                (String) row[4],                            // imageUrl
                ((Number) row[5]).intValue(),               // productDetailId
                (String) row[6],                            // productWeight
                ((Number) row[7]).intValue(),               // batchId
                ((Number) row[8]).intValue(),               // importedQuantity
                ((Number) row[9]).intValue(),               // soldQuantity
                ((java.sql.Date) row[10]).toLocalDate(),     // manufactureDate
                ((Number) row[11]).intValue(),              // expiry
                ((Number) row[12]).doubleValue(),              // Price
                ((java.sql.Date) row[13]).toLocalDate(),    // expiryDate
                ((Number) row[14]).intValue(),              // totalAdjustedRemoveQuantity
                ((Number) row[15]).intValue(),              // remainQuantity
                (String) row[16],                           // status
                (String) row[17],                            // expiryStatus
                ((Number) row[18]).intValue(),                  // avgRating
                ((Number) row[19]).intValue()                   // ratingCount
        )).toList();

    }

    public ResponseResult<CartTableUserResponse> addToCartProductDetail(int productDetailId, int userId) {

        if (getProductDetailUser(productDetailId).isEmpty()) {
            return new ResponseResult<>("ERROR", "Không có sản phẩm chi tiết", false);
        } else {

            Optional<User> user = userRepository.findByUserIdNative(userId);
            Optional<ProductDetail> productDetail = productDetailRepository.findById(productDetailId);
            double totalPrice = 1 * productDetail.get().getPrice();

            Cart cart = new Cart();
            cart.setUser(user.get());
            cart.setProductDetail(productDetail.get());
            cart.setQuantity(1);
            cart.setTotal(totalPrice);

            cartRepository.save(cart);

            CartTableUserResponse cartTableUserResponse = new CartTableUserResponse();
            cartTableUserResponse.setUserId(userId);
            cartTableUserResponse.setProductDetailId(productDetailId);
            cartTableUserResponse.setQuantity(1);
            cartTableUserResponse.setPrice(totalPrice);

            return new ResponseResult<>("SUCCESS", "Thêm sản phẩm vào giỏ hàng thành công", true, cartTableUserResponse);


        }


    }

    public ResponseResult<CartTableUserResponse> deleteCartUser(int cartId) {
        Optional<Cart> cartOptional = cartRepository.findById((long) cartId);

        if(!cartOptional.isPresent()) {
            return new ResponseResult<>("ERROR","Không thấy giỏ hàng id để xóa", false);
        } else {
            cartRepository.deleteById((long) cartId);
            return new ResponseResult<>("SUCCESS", "Xóa giỏ hàng thành công",true);
        }

    }
}

