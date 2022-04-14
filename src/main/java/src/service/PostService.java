package src.service;

import src.model.Post;
import src.model.PostInput;
import src.model.PostOutput;

import javax.transaction.Transactional;
import java.util.List;

public interface PostService {

    PostOutput savePost(PostInput postInput, int id);

    Post getPostInstanceById(int userId, int postId);

    PostOutput getPostById(int userId, int postId);

    PostOutput editPostById(Post post, int userId, int postId);

    void deletePostById(int userId, int postId);

    List<Post> getParticularUserPosts(int userID);
}
