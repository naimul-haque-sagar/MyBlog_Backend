package myBrain.controller;

import io.swagger.models.Response;
import lombok.AllArgsConstructor;
import myBrain.dto.CategoryDto;
import myBrain.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/post")
    public ResponseEntity postCategory(@RequestBody CategoryDto categoryDto){
        categoryService.postCategory(categoryDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
