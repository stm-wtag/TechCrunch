package src.repository;

import src.model.entities.Post;
import src.model.entities.User;

import java.util.List;

public interface PostRepository {

    Post save(Post post);

    Post  findPost(int postId, User postAuthor);

    void delete(Post post);

    List<Post> getAllPostsOfAnUser(int userId);
}
