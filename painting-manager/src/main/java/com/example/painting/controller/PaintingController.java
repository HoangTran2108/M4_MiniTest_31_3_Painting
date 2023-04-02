package com.example.painting.controller;

import com.example.painting.model.Category;
import com.example.painting.model.Painting;
import com.example.painting.model.PaintingForm;
import com.example.painting.service.categoryService.ICategoryService;
import com.example.painting.service.paintingService.IPaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class PaintingController {
    @Value("${file-image}")
    private String uploadFile;

    @Autowired
    private IPaintingService paintingService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private PaintingForm paintingF;

    @ModelAttribute("categories")
    Iterable<Category> paintings() {
        return categoryService.findAll();
    }


    @GetMapping("/paintings")
    public ModelAndView listCustomers(@RequestParam("search1") Optional<String> search1,
                                      @RequestParam("search2") Optional<Category> search2, Pageable pageable) {
        Page<Painting> paintings;
        if (search1.isPresent() || search2.isPresent()) {
            paintings = paintingService.findAllByCodePainContainingAndCategory(search1.get(), search2.get(), pageable);
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
        modelAndView.addObject("painting", new PaintingForm());
        return modelAndView;
    }

    @PostMapping("/create-painting")
    public ModelAndView savePainting(@ModelAttribute PaintingForm paintingForm) {
        MultipartFile multipartFile = paintingForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(paintingForm.getImage().getBytes(), new File(uploadFile + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Painting painting = new Painting(paintingForm.getCodePain(), paintingForm.getHeight(),
                paintingForm.getWeight(), paintingForm.getMaterial(), paintingForm.getDescription(),
                paintingForm.getPrice(), fileName, paintingForm.getCategory());
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
    public ModelAndView updateCustomer(Painting painting) {
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
    public String deleteCustomer(Painting painting) {
        paintingService.remove(painting.getId());
        return "redirect:paintings";
    }

}
