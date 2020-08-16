package myBrain.service;

import lombok.AllArgsConstructor;
import myBrain.beanMapper.CategoryMapper;
import myBrain.dto.CategoryDto;
import myBrain.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public void postCategory(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.mapToModel(categoryDto));
    }
}
