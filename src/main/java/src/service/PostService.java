package src.service;

import src.model.entities.Post;
import java.util.List;

public interface PostService {

    Post savePost(Post post, int id);

    Post getPostById(int userId, int postId);

    Post editPostById(Post post, int userId, int postId);

    void deletePostById(int userId, int postId);

    List<Post> getParticularUserPosts(int userID);
}
