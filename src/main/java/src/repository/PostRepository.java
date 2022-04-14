package src.repository;

import src.model.Post;
import src.model.PostOutput;

import java.util.List;

public interface PostRepository {

    PostOutput save(Post post);

    Post findPost(int postId);

    void delete(Post post);

    List<Post> getAllPostsOfAnUser(int userId);
}
