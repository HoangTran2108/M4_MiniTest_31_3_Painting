package com.example.painting.controller;

import com.example.painting.model.Category;
import com.example.painting.model.Painting;
import com.example.painting.service.categoryService.ICategoryService;
import com.example.painting.service.paintingService.IPaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IPaintingService paintingService;

    @GetMapping("/categories")
    public ModelAndView listCategory() {
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("category/list");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    //create category
    @GetMapping("/create-category")
    public ModelAndView formCreateCategory() {
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create-category")
    public ModelAndView createCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "New category created successfully");
        return modelAndView;
    }

    //edit category
    @GetMapping("/edit-category/{id}")
    public ModelAndView formEditCategory(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if(category.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/category/edit");
            modelAndView.addObject("category", category.get());
            return modelAndView;
        }
        else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit-category")
    public ModelAndView editCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "New category update successfully");
        return modelAndView;
    }

    //delete category
    @GetMapping("/delete-category/{id}")
    public ModelAndView formDeleteCategory(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if(category.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("category", category.get());
            return modelAndView;
        }
        else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/delete-category")
    public ModelAndView deleteCategory(@ModelAttribute("category") Category category) {
        categoryService.remove(category.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:categories");
        return modelAndView;
    }


    @GetMapping("/view-category/{id}")
    public ModelAndView viewCategory(@PathVariable("id") Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        Iterable<Painting> paintings = paintingService.findAllByCategory(categoryOptional.get());
        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category", categoryOptional.get());
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }
}
