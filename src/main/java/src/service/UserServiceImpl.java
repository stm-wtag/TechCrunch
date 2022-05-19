package src.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import src.exceptions.EntityNotFoundException;
import src.model.dto.SignedInUser;
import src.model.entities.User;
import src.repository.UserRepository;
import javax.transaction.Transactional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Transactional
    @Override
    public User saveUser(User user) {
        return userRepository.save(user, true);

    }

    @Transactional
    @Override
    public User getUserById(int userId) {
        User user = userRepository.findUser(userId);
        if (user != null){
            return user;
        }
        else {
            throw new EntityNotFoundException("User doesn't exist");
        }
    }

    @Transactional
    @Override
    public void deleteUserById(int userId) {
         userRepository.delete(userId);
         saveTokenToBlackList();
    }

    @Transactional
    @Override
    public User updateUserById(User user, int id) {
        boolean edited = false;
        User userToUpdate = getUserById(id);
        if (checkFieldValidity(user.getUserName())){
            userToUpdate.setUserName(user.getUserName());
            saveTokenToBlackList();
        }
        if (checkFieldValidity(user.getEmailAddress())){
            userToUpdate.setEmailAddress(user.getEmailAddress());
        }
        if (checkFieldValidity(user.getPassword())){
            userToUpdate.setPassword(user. getPassword());
            edited = true;
            saveTokenToBlackList();
        }
        return userRepository.save(userToUpdate, edited);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public void saveTokenToBlackList(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SignedInUser loggedInUser = (SignedInUser) authentication.getPrincipal();
        userRepository.saveTokenToBlackList(loggedInUser.getToken());
    }

    public boolean checkFieldValidity(String credential){
        return credential != null && !credential.isEmpty() && !StringUtils.isBlank(credential);

    }


}

