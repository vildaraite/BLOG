package lt.code.academy.blog.controller;
import lt.code.academy.blog.dto.Article;
import lt.code.academy.blog.service.ArticleService;
import lt.code.academy.blog.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;



@Controller
@RequestMapping("/articles")
public class ArticleController {

       private final ArticleService articleService;
       private final MessageService messageService;

    public ArticleController(ArticleService articleService, MessageService messageService) {
        this.articleService = articleService;
        this.messageService = messageService;
    }


   @GetMapping
   public String showArticles(Model model, @RequestParam(required = false) String category, @RequestParam(required = false)String title) {
        model.addAttribute("articles", articleService.getFilteredArticles(category, title));

        return "articles";
   }

    /*@GetMapping
    public String showArticles(Model model) {
        model.addAttribute("articles", articleService.getArticle());

        return "articles";
    }*/
    @GetMapping("/create")
    public String openCreateArticleForm(Model model, String messageKey) {
        model.addAttribute("article", new Article());
        model.addAttribute("message", messageService.getMessage(messageKey));

        return "form/article";
    }
    @PostMapping("/create")
    public String createArticle(Article article) {
        String messageKey = "lt.code.academy.blog.article.create.success.message";

        articleService.createArticle(article);


    return "redirect:/articles/crete?messageKey" + messageKey;
    }

    @GetMapping("/{id}")
    public String getArticleDetails(@PathVariable UUID id, Model model) {
        model.addAttribute("article", articleService.getArticle(id));

        return "articleDetails";
    }

    @GetMapping("/{id}/update")
    public String openUpdateArticleForm(@PathVariable UUID id, Model model) {
        model.addAttribute("article", articleService.getArticle(id));

        return "form/article";
    }

    @PostMapping("/{id}/update")
    public String updateArticle(Article article) {
        articleService.updateArticle(article);

        return "redirect:/articles";
    }

    @GetMapping("/{id}/delete")
    public String deleteArticle(@PathVariable UUID id) {
        articleService.deleteArticle(id);


        return "reirect:/articles";
    }

}