package lt.code.academy.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blog.dto.Article;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ARTICLES")
    public class ArticleEntity {
        @Id
        @GeneratedValue
        @Column(updatable = false)
        private UUID id;
        @Column(nullable = false, length = 100)
        private String title;
        @Column(nullable = false, length = 200)
        private String category;
        @Column(nullable = false, length = 500)
        private String description;
    private int totalComments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


        public static ArticleEntity convert(Article article) {
            return new ArticleEntity(
                    article.getId(),
                    article.getTitle(),
                    article.getDescription(),
                    article.getCategory(),
                    article.getTotalComments(),
                    article.getCreatedAt(),
                    article.getUpdatedAt()
            );
        }
    }
