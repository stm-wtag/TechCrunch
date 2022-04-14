package src.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import src.model.Post;
import src.model.User;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public PostRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Post save(Post post){
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(post);
        return post;
    }

    @Override
    @Transactional
    public Post findPost(int postId){
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Post.class, postId);
    }

    @Override
    @Transactional
    public void delete(Post post){
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(post);

    }

    @Override
    @Transactional
    public List<Post> getAllPostsOfAnUser(int userId){
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> root =  criteriaQuery.from(User.class);
        Join<User, Post> userPostJoin = root.join("posts", JoinType.LEFT);
        User user = new User();
        user.setId(userId);
        criteriaQuery.where(cb.equal(userPostJoin.get("user"), user));
        Query<User> postQuery = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        return new ArrayList<>(postQuery.getSingleResult().getPosts());
    }

}
