package lt.code.academy.blog.repository;

import lt.code.academy.blog.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {

    @Query("SELECT c FROM CommentEntity c WHERE c.post.id = :post_id ORDER BY c.createdAt desc")
    List<CommentEntity> getAllByPostId(@Param("post_id") UUID post_id);
}