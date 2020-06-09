package myBrain.beanMapper;

import myBrain.dto.PostDto;
import myBrain.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDto mapToDto(Post post);

    @Mapping(target = "username" ,expression = "java(getCurrentUser())")
    @Mapping(target = "createdOn", expression = "java(instantTime())")
    @Mapping(target = "id",ignore = true)
    Post mapToModel(PostDto postDto);

    default Instant instantTime(){
        return Instant.now();
    }
    default String getCurrentUser(){
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
}
