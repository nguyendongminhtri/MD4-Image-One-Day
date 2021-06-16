package com.hsl.controller;

import com.hsl.model.CommentRate;
import com.hsl.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/index");
        List<CommentRate> commentRates = commentService.findAll();
        modelAndView.addObject("comments", commentRates);
        modelAndView.addObject("commentRate", new CommentRate());
        return modelAndView;
    }

//    @GetMapping("{id}")
//    public ModelAndView showInformation(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("customers/info");
//        Customer customer = customerService.findOne(id);
//        modelAndView.addObject("customer", customer);
//        return modelAndView;
//    }
//
    @PostMapping
    public String updateCustomer(CommentRate commentRate) {
        commentService.save(commentRate);
        return "redirect:/comment";
    }

    @GetMapping("/{id}/like")
    public String view(@PathVariable int id, Model model) {
//        commentService.like(id);
//        model.addAttribute("product", productService.selectProduct(id));
//        return "redirect:/comment";

//        ModelAndView modelAndView = new ModelAndView("/index");
        CommentRate commentRate = commentService.findOne(id);
//        modelAndView.addObject("commentRate", commentRate);
        commentRate.setLikeComment(commentRate.getLikeComment()+1);
        commentService.update(commentRate);
//        return modelAndView;
        return "redirect:/comment";
    }
}
