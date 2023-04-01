package com.example.painting.service.paintingService;

import com.example.painting.model.Category;
import com.example.painting.model.Painting;
import com.example.painting.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPaintingService extends IGeneralService<Painting> {
    Iterable<Painting> findAllByCategory(Category category);
    Page<Painting> findAll(Pageable pageable);
    Page<Painting> findAllByCodePainContaining(String codePaint, Pageable pageable);
}
