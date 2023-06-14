package lt.code.academy.blog.service;
import lt.code.academy.blog.dto.Article;
import lt.code.academy.blog.entity.ArticleEntity;
import lt.code.academy.blog.exception.ArticleNotExistRuntimeException;
import lt.code.academy.blog.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
    }

    public void createArticle (Article article) {
        articleRepository.save(ArticleEntity.convert(article));
    }

    public Article saveArticle(Article article) {
        if (article.getCreatedAt() == null) {
            article.setCreatedAt(LocalDateTime.now());
        }
        return Article.convert(articleRepository.save(ArticleEntity.convert(article)));
    }

    public List<Article> getArticles () {
        return articleRepository.findAll()
                .stream()
                .map(Article::convert)
                .toList();
    }
    public List<Article> getArticlesByCategory(String category) {
        return articleRepository.findAllByCategoryOrTitleContainsOrDescriptionContains(category, category, category)
                .stream()
                .map(Article::convert)
                .toList();
    }
    public List<Article> getArticlesByCategoryAndTitle(String category, String title) {
        return articleRepository.getArticlesByCategoryAndTitle(category, title)
                .stream()
                .map(Article::convert)
                .toList();
    }

    public List<Article> getFilteredArticles(String category, String title){
        if(category != null && !category.isBlank()) {
            if(title!= null && !title.equals("null")) {
                return getArticlesByCategoryAndTitle(category, title);
            }

            return getArticlesByCategory(category);
        }

        return getArticles();
    }

    public Page<Article> getArticleByPage(Pageable pageable) {
        return articleRepository.findAll(pageable)
                .map(Article::convert);
    }

    public Article getArticle(UUID id) {
        return articleRepository.findById(id)
                .map(Article::convert)
                .orElseThrow(() -> new ArticleNotExistRuntimeException(id));
    }

    public void updateArticle (Article article) {
        articleRepository.save(ArticleEntity.convert(article));
    }

    public void deleteArticle(UUID id) {
        articleRepository.deleteById(id);
    }


}
