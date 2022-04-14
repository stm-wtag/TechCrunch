package src.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import src.exceptions.BadRequestException;
import src.model.FactoryObjectMapper;
import src.model.Post;
import src.model.PostInput;
import src.model.PostOutput;
import src.service.PostService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users/{userId}/")
public class PostController {

    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/posts")
    public ResponseEntity<PostOutput> createPost(@Valid @RequestBody PostInput postInput,BindingResult bindingResult,@PathVariable int userId ){
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }

        Post post = FactoryObjectMapper.convertPostInputToModel(postInput);
        Post savedPost = postService.savePost(post, userId);
        PostOutput postOutput = FactoryObjectMapper.convertPostModelToPostOutput(savedPost);
        return new ResponseEntity<>(postOutput , HttpStatus.CREATED);
    }

    @GetMapping(value = "/posts/{postId}")
    public ResponseEntity<PostOutput> getPost(@PathVariable int userId, @PathVariable int postId) {
        Post fetchedPost = postService.getPostById(userId, postId);
        PostOutput postOutput = FactoryObjectMapper.convertPostModelToPostOutput(fetchedPost);
        return  new ResponseEntity<>(postOutput , HttpStatus.OK);
    }

    @PatchMapping(value="/posts/{postId}")
    public ResponseEntity<PostOutput> editPost(@RequestBody Post post, @PathVariable int userId, @PathVariable int postId){
        Post fetchedPost = postService.editPostById(post, userId, postId);
        PostOutput postOutput = FactoryObjectMapper.convertPostModelToPostOutput(fetchedPost);
        return new ResponseEntity<>(postOutput, HttpStatus.OK);
    }

    @DeleteMapping(value="/posts/{postId}")
    public ResponseEntity<Void> deletePost( @PathVariable int userId, @PathVariable int postId){
        postService.deletePostById(userId,postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value="/posts")
    public ResponseEntity<List<Post>> getAllPostOfUser(@PathVariable int userId){
        List<Post> postSet = postService.getParticularUserPosts(userId);
        return new ResponseEntity<>(postSet, HttpStatus.OK);
    }


}