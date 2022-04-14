package src.repository;

import src.model.User;
import src.model.UserOutput;

import javax.transaction.Transactional;

public interface UserRepository {

    User save(User user);

    User findUser(int userId);

    boolean delete(int userId);
}
