package lt.code.academy.blog.controller;

import lt.code.academy.blog.dto.Comment;
import lt.code.academy.blog.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public String saveNewComment(Comment comment){
        if (comment.getBody() == null || comment.getBody().isBlank() || comment.getBody().isEmpty()){
            return "redirect:/create" + comment.getArticleId();
        }

        return "redirect:/create" + comment.getArticleId();
    }


}
