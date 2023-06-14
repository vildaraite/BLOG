package lt.code.academy.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blog.dto.Article;
import lt.code.academy.blog.dto.Comment;
import lt.code.academy.blog.service.ArticleService;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private ArticleEntity article;

    public static CommentEntity convert(Comment comment, ArticleService articleService) {
        ArticleEntity article = ArticleEntity.convert(articleService.getArticle(comment.getArticleId()));
        return new CommentEntity(
                comment.getId(),
                comment.getBody(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                article
        );
    }
}
