package lt.code.academy.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.code.academy.blog.entity.CommentEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private UUID id;
    private String body;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID articleId;

    public static Comment convert(CommentEntity entity) {
        return new Comment(
                entity.getId(),
                entity.getBody(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getArticle().getId()
        );
    }
}