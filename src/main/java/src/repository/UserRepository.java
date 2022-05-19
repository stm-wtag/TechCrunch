package src.repository;

import src.model.entities.User;

public interface UserRepository {

    User save(User user, boolean edited);

    User findUser(int userId);

    void delete(int userId);

    User findUserByUsername(String username);

    Boolean checkDuplicate(String credential, String credentialType);

    void saveTokenToBlackList(String token);

    Boolean checkUserTokenValidity(String clientToken);
}
