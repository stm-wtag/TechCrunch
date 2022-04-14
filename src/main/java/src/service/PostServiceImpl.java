package src.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.model.*;
import src.repository.PostRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostServiceImpl implements PostService {

    private final UserService userService;
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(UserService userService, PostRepository postRepository) {
        this.userService= userService;
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public Post savePost(Post post, int id){
        User postAuthor = userService.getUserById(id);
        post.setUser(postAuthor);
        return postRepository.save(post);

    }

    @Override
    @Transactional
    public Post getPostById(int userId, int postId){
        User postAuthor = userService.getUserById(userId);
        Post post = postRepository.findPost(postId);
        if (postAuthor ==  null) throw new NoSuchElementException("There is no such user");
        if (post == null) throw new NoSuchElementException("There is no such post");

        return post;
    }


    @Override
    @Transactional
    public Post editPostById(Post post, int userId, int postId){
        Post fetchedPost = getPostById(userId, postId);
        if (post.getTitle() != null) fetchedPost.setTitle(post.getTitle());
        if (post.getDescription() != null) fetchedPost.setDescription(post.getDescription());
        return postRepository.save(fetchedPost);

    }

    @Override
    @Transactional
    public void deletePostById(int userId, int postId){
        Post fetchedPost = getPostById(userId, postId);
        postRepository.delete(fetchedPost);
    }

    @Override
    @Transactional
    public List<Post> getParticularUserPosts(int userID){
        return postRepository.getAllPostsOfAnUser(userID);
    }
}
