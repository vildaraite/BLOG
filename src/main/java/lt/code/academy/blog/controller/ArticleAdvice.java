package lt.code.academy.blog.controller;


import lt.code.academy.blog.exception.ArticleNotExistRuntimeException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;

@ControllerAdvice
public class ArticleAdvice {

    @ExceptionHandler(ArticleNotExistRuntimeException.class)
    public String handleProductNotExistException(ArticleNotExistRuntimeException exception, Model model) {
        model.addAttribute("articleId", exception.getId());

        return "articleNotExist";
    }
    @InitBinder
    public void validateEmptyString(WebDataBinder dataBinder) {
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, editor);
    }
    @ModelAttribute("customDate")
    public Date myCustomDate() {
        return new Date();
    }
}