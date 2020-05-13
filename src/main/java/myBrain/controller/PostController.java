package myBrain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myBrain.dto.PostDto;
import myBrain.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	private PostService postService;
	
	@PostMapping
	public ResponseEntity<HttpStatus> createPost(@RequestBody PostDto postDto) {
		postService.createPost(postDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<PostDto>> showAllPosts(){
		return new ResponseEntity<List<PostDto>>(postService.showAllPosts(),HttpStatus.OK);
	}
	
	@GetMapping("get/{id}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id){
		return new ResponseEntity<PostDto>(postService.readSinglePost(id),HttpStatus.OK);
	}
}
