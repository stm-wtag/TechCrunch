package src.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import src.model.entities.TokenBlockList;
import src.model.entities.User;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@ComponentScan(basePackages = "src.config.BeanConfig")
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory, PasswordEncoder passwordEncoder) {
        this.sessionFactory = sessionFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User save(User user, boolean passwordEdited){
        Session currentSession = sessionFactory.getCurrentSession();
        if(passwordEdited){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        currentSession.save(user);
        return user;
  }

    @Override
    @Transactional
    public User findUser(int userId){
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(User.class, userId);
    }

    @Override
    @Transactional
    public void delete(int userId){
        Session currentSession = sessionFactory.getCurrentSession();
        User user = findUser(userId);
        currentSession.delete(user);

    }

    @Override
    @Transactional
    public User findUserByUsername(String username) {
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> c = criteriaQuery.from(User.class);
        criteriaQuery.where(cb.equal(c.get("userName"), username));
        return sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList().get(0);

    }

    @Transactional
    public Boolean checkDuplicate(String credential, String credentialType) {
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<User> root = cq.from(User.class);
        cq.select(root.<String>get(credentialType));
        List<String> resultList = sessionFactory.getCurrentSession().createQuery(cq).getResultList();
        for (String s : resultList) {
            if (s.equals(credential)) return false;
        }
        return true;
    }
    @Transactional
    public void saveTokenToBlackList(String token){
        Session currentSession = sessionFactory.getCurrentSession();
        TokenBlockList tokenBlockList = new TokenBlockList();
        tokenBlockList.setToken(token);
        currentSession.save(tokenBlockList);
    }

    @Transactional
    public Boolean checkUserTokenValidity(String clientToken){
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<TokenBlockList> root = cq.from(TokenBlockList.class);
        cq.select(root.<String>get("token"));
        List<String> resultList = sessionFactory.getCurrentSession().createQuery(cq).getResultList();
        for(String s: resultList){
            if (s.equals(clientToken)) return false;
        }
        return true;
    }

}
