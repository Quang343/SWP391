package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ShopDetailResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ViewCommentProductUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.CommentProduct;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.CommentProductRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentProductUserService {

    @Autowired
    private CommentProductRepository commentProductRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public void insertProduct(int productId, int accountId, int rating, String content) {

        Optional<User> user = userRepository.findByUserIdNative(accountId);
        Optional<Product> product = productRepository.findById(productId);

        CommentProduct commentProduct = new CommentProduct();
        commentProduct.setUser(user.get());
        commentProduct.setCommentText(content);
        commentProduct.setDislikes(0);
        commentProduct.setStatus("Visible");
        commentProduct.setRating(rating);
        commentProduct.setLikes(0);
        commentProduct.setProduct(product.get());

        commentProductRepository.save(commentProduct);
    }

    public List<ViewCommentProductUserResponse> getViewCommentProuduct(int productId) {
        List<Object[]> raw = commentProductRepository.rawViewCommentProduct(productId);

        return raw.stream().map(row -> {
            // Các giá trị khác trong row
            Timestamp timestamp = (Timestamp) row[5]; // Timestamp của sql
            LocalDateTime createdAt = timestamp.toLocalDateTime();
            return new ViewCommentProductUserResponse(
                    ((Number) row[0]).intValue(),   // commentProductId
                    ((Number) row[1]).intValue(),   // productId
                    ((Number) row[2]).intValue(),   // userId
                    (String) row[3],                // userName
                    (String) row[4],                // productName (hoặc tên khác)
                    createdAt,                      // createdAt (LocalDateTime)
                    ((Number) row[6]).intValue(),   // rating
                    (String) row[7],                // commentText
                    ((Number) row[8]).intValue(),   // dislikes
                    ((Number) row[9]).intValue(),   // likes
                    (String) row[10]                // status
            );
        }).toList();
    }

    public List<ViewCommentProductUserResponse> getViewCommentProuducts(int productId) {

        if (getViewCommentProuduct(productId).isEmpty()) {
            return new ArrayList<>();
        } else {
            return getViewCommentProuduct(productId);
        }
    }

    public ViewCommentProductUserResponse updateDislikesComment(int commentId) {

        Optional<CommentProduct> commentProduct = commentProductRepository.findById((long) commentId);

        if (commentProduct.isPresent()) {
            commentProduct.get().setDislikes(commentProduct.get().getDislikes() + 1);

            commentProductRepository.save(commentProduct.get());

            return new ViewCommentProductUserResponse(
                    commentProduct.get().getCommentProductId(),
                    commentProduct.get().getProduct().getId(),
                    commentProduct.get().getUser().getUserID(),
                    commentProduct.get().getUser().getUserName(),
                    commentProduct.get().getUser().getUserName(),
                    commentProduct.get().getCreatedAt(),
                    commentProduct.get().getRating(),
                    commentProduct.get().getCommentText(),
                    commentProduct.get().getDislikes(),
                    commentProduct.get().getLikes(),
                    commentProduct.get().getStatus()
            );
        } else {
            return new ViewCommentProductUserResponse();
        }

    }

    public ViewCommentProductUserResponse updateLikesComment(int commentId) {

        Optional<CommentProduct> commentProduct = commentProductRepository.findById((long) commentId);

        if (commentProduct.isPresent()) {
            commentProduct.get().setLikes(commentProduct.get().getLikes() + 1);

            commentProductRepository.save(commentProduct.get());

            return new ViewCommentProductUserResponse(
                    commentProduct.get().getCommentProductId(),
                    commentProduct.get().getProduct().getId(),
                    commentProduct.get().getUser().getUserID(),
                    commentProduct.get().getUser().getUserName(),
                    commentProduct.get().getUser().getUserName(),
                    commentProduct.get().getCreatedAt(),
                    commentProduct.get().getRating(),
                    commentProduct.get().getCommentText(),
                    commentProduct.get().getDislikes(),
                    commentProduct.get().getLikes(),
                    commentProduct.get().getStatus()
            );
        } else {
            return new ViewCommentProductUserResponse();
        }

    }
}
