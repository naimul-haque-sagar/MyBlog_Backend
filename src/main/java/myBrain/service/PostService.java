package myBrain.service;

import myBrain.beanMapper.PostMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import myBrain.dto.PostDto;
import myBrain.exception.PostNotFoundException;
import myBrain.model.Post;
import myBrain.repository.PostRepository;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PostService {

    private final AuthService authService;

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    @Transactional
    public List<PostDto> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional
    public void createPost(PostDto postDto) {
        Post post = postMapper.mapToModel(postDto);
        postRepository.save(post);
    }

    @Transactional
    public PostDto readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return postMapper.mapToDto(post);
    }

}
