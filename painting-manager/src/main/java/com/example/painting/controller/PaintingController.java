package com.example.painting.controller;

import com.example.painting.model.Category;
import com.example.painting.model.Painting;
import com.example.painting.service.categoryService.ICategoryService;
import com.example.painting.service.paintingService.IPaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PaintingController {
    @Autowired
    private IPaintingService paintingService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    Iterable<Category> paintings(){
        return categoryService.findAll();
    }


    @GetMapping("/paintings")
    public ModelAndView listCustomers(@RequestParam("search") Optional<String> search, Pageable pageable){
        Page<Painting> paintings;
        if(search.isPresent()){
            paintings = paintingService.findAllByCodePainContaining(search.get(), pageable);
        } else {
            paintings = paintingService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/painting/list");
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }


    //create painting
    @GetMapping("/create-painting")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/painting/create");
        modelAndView.addObject("painting", new Painting());
        return modelAndView;
    }

    @PostMapping("/create-painting")
    public ModelAndView savePainting(@ModelAttribute("painting") Painting painting) {
        paintingService.save(painting);
        ModelAndView modelAndView = new ModelAndView("/painting/create");
        modelAndView.addObject("painting", new Painting());
        modelAndView.addObject("message", "New painting created successfully");
        return modelAndView;
    }

    //update painting
    @GetMapping("/edit-painting/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Painting> painting = paintingService.findById(id);
        if (painting.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/painting/edit");
            modelAndView.addObject("painting", painting.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit-painting")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Painting painting) {
        paintingService.save(painting);
        ModelAndView modelAndView = new ModelAndView("/painting/edit");
        modelAndView.addObject("painting", painting);
        modelAndView.addObject("message", "Painting updated successfully");
        return modelAndView;
    }

    //delete painting
    @GetMapping("/delete-painting/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Painting> painting = paintingService.findById(id);
        if (painting.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/painting/delete");
            modelAndView.addObject("painting", painting.get());
            return modelAndView;

        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/delete-painting")
    public String deleteCustomer(@ModelAttribute("customer") Painting painting) {
        paintingService.remove(painting.getId());
        return "redirect:paintings";
    }

}
