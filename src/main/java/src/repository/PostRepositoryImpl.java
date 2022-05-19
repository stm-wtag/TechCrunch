package src.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import src.model.entities.Post;
import src.model.entities.User;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
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
    public Post save(Post post) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(post);
        return post;
    }

    @Override
    @Transactional
    public Post findPost(int postId, User postAuthor) {
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = cb.createQuery(Post.class);
        Root<Post> root = criteriaQuery.from(Post.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cb.and(cb.equal(root.get("id"), postId), cb.equal(root.get("user"), postAuthor)));
        return sessionFactory.getCurrentSession().createQuery(criteriaQuery).uniqueResult();
    }

    @Override
    @Transactional
    public void delete(Post post) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(post);

    }

    @Override
    @Transactional
    public List<Post> getAllPostsOfAnUser(int userId) {
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = cb.createQuery(Post.class);
        Root<Post> root = criteriaQuery.from(Post.class);
        criteriaQuery.select(root);
        User user = new User();
        user.setId(userId);
        criteriaQuery.where(cb.equal(root.get("user"), user));
        return sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();

    }



}
