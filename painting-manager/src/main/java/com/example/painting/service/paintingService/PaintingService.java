package com.example.painting.service.paintingService;

import com.example.painting.model.Category;
import com.example.painting.model.Painting;
import com.example.painting.repository.IPaintingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaintingService implements IPaintingService{
    @Autowired
    private IPaintingRepository paintingRepository;
    @Override
    public Iterable<Painting> findAll() {
        return paintingRepository.findAll();
    }

    @Override
    public Optional<Painting> findById(Long id) {
        return paintingRepository.findById(id);
    }

    @Override
    public void save(Painting painting) {
        paintingRepository.save(painting);
    }

    @Override
    public void remove(Long id) {
        paintingRepository.deleteById(id);
    }

    @Override
    public Iterable<Painting> findAllByCategory(Category category) {
        return paintingRepository.findAllByCategory(category);
    }

    @Override
    public Page<Painting> findAll(Pageable pageable) {
        return paintingRepository.findAll(pageable);
    }

    @Override
    public Page<Painting> findAllByCodePainContaining(String codePaint, Pageable pageable) {
        return paintingRepository.findAllByCodePainContaining(codePaint, pageable);
    }
}
