package myBrain.beanMapper;

import myBrain.dto.CategoryDto;
import myBrain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category mapToModel(CategoryDto categoryDto);
}
