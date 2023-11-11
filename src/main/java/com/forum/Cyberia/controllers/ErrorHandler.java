package com.forum.Cyberia.controllers;

import com.forum.Cyberia.models.exceptions.NotFoundException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFound(NotFoundException e) {
        ModelMap model = new ModelMap();
        model.addAttribute("error", e.getMessage());

        return new ModelAndView("error", model);
    }
}
