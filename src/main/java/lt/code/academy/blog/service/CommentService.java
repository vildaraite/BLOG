package lt.code.academy.blog.service;

import lt.code.academy.blog.dto.Article;
import lt.code.academy.blog.dto.Comment;
import lt.code.academy.blog.entity.CommentEntity;
import lt.code.academy.blog.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    public CommentService(CommentRepository commentRepository, ArticleService articleService) {
        this.commentRepository = commentRepository;
        this.articleService = articleService;
    }

    public void saveComment(Comment comment) {
        Article article = articleService.getArticle(comment.getArticleId());
        int commentCount = article.getTotalComments();
        if (comment.getCreatedAt() == null) {
            comment.setCreatedAt(LocalDateTime.now());
            article.setTotalComments(commentCount + 1);
        }

        articleService.saveArticle(article);
        commentRepository.save(CommentEntity.convert(comment, articleService));
    }

    public Comment findCommentById(UUID id) {
        return commentRepository.findById(id)
                .map(Comment::convert)
                .orElse(null);
    }

    public List<Comment> findAllCommentsByArticleId(UUID id) {
        return commentRepository.getAllByPostId(id)
                .stream()
                .map(Comment::convert)
                .toList();
    }

    public void deleteComments(List<Comment> comments) {
        for (Comment comment : comments) {
            commentRepository.deleteById((comment.getId()));
        }
    }

    public void deleteCommentById (UUID id) {
        Article article = articleService.getArticle(findCommentById(id).getArticleId());
        int commentCount = article.getTotalComments();
        article.setTotalComments(commentCount - 1);
        articleService.saveArticle(article);
        commentRepository.deleteById(id);
    }
}
