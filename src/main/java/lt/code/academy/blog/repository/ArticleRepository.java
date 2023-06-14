package lt.code.academy.blog.repository;
import lt.code.academy.blog.dto.Article;
import lt.code.academy.blog.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {

    List <ArticleEntity> findAllByCategoryOrTitleContainsOrDescriptionContains(String category, String title, String description);
    // select * from articles where category = ......

    @Query ("SELECT p FROM ArticleEntity p WHERE p.category = :category AND p.title = :title")
    List<ArticleEntity> getArticlesByCategoryAndTitle(@Param("category")String category, @Param("title")String title);
}
