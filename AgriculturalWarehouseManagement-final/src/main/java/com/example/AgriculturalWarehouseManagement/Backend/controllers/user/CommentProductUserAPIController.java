package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ViewCommentProductUserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.CommentProductUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentProductUserAPIController {
    @Autowired
    private CommentProductUserService commentProductUserService;

    @PutMapping("/dislike/{commentId}")
    public ResponseEntity<ViewCommentProductUserResponse> dislikeComment(@PathVariable("commentId") int commentId) {
        ViewCommentProductUserResponse viewCommentProductUserResponses = commentProductUserService.updateDislikesComment(commentId);
        return ResponseEntity.ok(viewCommentProductUserResponses);
    }

    @PutMapping("/like/{commentId}")
    public ResponseEntity<ViewCommentProductUserResponse> likeComment(@PathVariable("commentId") int commentId) {
        ViewCommentProductUserResponse viewCommentProductUserResponses = commentProductUserService.updateLikesComment(commentId);
        return ResponseEntity.ok(viewCommentProductUserResponses);
    }
}
