package lt.code.academy.blog.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blog.entity.ArticleEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private UUID id;
    @NotBlank
    @Size(min = 5, max = 20, message = "Title='${validatedValue}' turi buti tarp {min} ir {max}")
    private String title;
    @NotBlank
    @Size(min = 2, max = 500, message = "{validation.article.size}")
    private String description;
    @NotBlank
    @Size(min = 2, max = 100, message = "{validation.article.size}")
    private String category;
    private int totalComments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

  public static Article convert(ArticleEntity entity) {
        return new Article(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getCategory(),
                entity.getTotalComments(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}