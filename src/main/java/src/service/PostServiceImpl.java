package src.service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.exceptions.EntityNotFoundException;
import src.model.entities.Post;
import src.model.entities.User;
import src.repository.PostRepository;
import javax.transaction.Transactional;
import java.util.List;


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
        Post post = postRepository.findPost(postId, postAuthor);
        if (postAuthor ==  null) throw new EntityNotFoundException("User doesn't exist");
        if (post == null) throw new EntityNotFoundException("post: "+ postId + " doesnt belong to user:" + userId);
        return post;
    }

    @Override
    @Transactional
    public Post editPostById(Post post, int userId, int postId){
        Post fetchedPost = getPostById(userId, postId);
        if (checkFieldValidity(post.getTitle())) fetchedPost.setTitle(post.getTitle());
        if (checkFieldValidity(post.getDescription())) fetchedPost.setDescription(post.getDescription());
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
    public List<Post> getParticularUserPosts(int userId){
        userService.getUserById(userId);
        return postRepository.getAllPostsOfAnUser(userId);
    }

    public boolean checkFieldValidity(String field){
        return field != null && !field.isEmpty() && !StringUtils.isBlank(field);
    }
}
