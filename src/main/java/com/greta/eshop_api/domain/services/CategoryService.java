package com.greta.eshop_api.domain.services;

import com.greta.eshop_api.exceptions.ResourceNotFoundException;
import com.greta.eshop_api.exposition.dtos.Categories.CategoryRequestDTO;
import com.greta.eshop_api.exposition.dtos.Categories.CategoryResponseDTO;
import com.greta.eshop_api.exposition.mappers.CategoryMapper;
import com.greta.eshop_api.persistence.entities.CategoryEntity;
import com.greta.eshop_api.persistence.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(CategoryMapper::toDto)
                .toList();
    }

    public CategoryResponseDTO findById(Long id) {
        CategoryEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie introuvable"));
        return CategoryMapper.toDto(entity);
    }

    public CategoryResponseDTO create(CategoryRequestDTO dto) {
        CategoryEntity entity = CategoryMapper.toEntity(dto);
        CategoryEntity saved = repository.save(entity);
        return CategoryMapper.toDto(saved);
    }

    public CategoryResponseDTO update(Long id, CategoryRequestDTO dto) {
        CategoryEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie introuvable"));

        entity.setName(dto.name());

        CategoryEntity updated = repository.save(entity);
        return CategoryMapper.toDto(updated);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Catégorie introuvable");
        }
        repository.deleteById(id);
    }
}
