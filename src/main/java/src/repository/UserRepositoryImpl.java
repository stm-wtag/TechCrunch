package src.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import src.model.FactoryObjectMapper;
import src.model.User;
import src.model.UserOutput;
import javax.transaction.Transactional;


@Repository
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public UserOutput save(User user){
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(user);
        return FactoryObjectMapper.convertUserEntityToUserOutput(user);
  }

    @Override
    @Transactional
    public User findUser(int userId){
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(User.class, userId);
    }

    @Override
    @Transactional
    public boolean delete(int userId){
        boolean deleted = false;
        Session currentSession = sessionFactory.getCurrentSession();
        User user = findUser(userId);
        if(user != null){
            currentSession.delete(user);
            deleted = true;
        }
        return deleted;
    }





}
