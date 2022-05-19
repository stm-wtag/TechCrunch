package src.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import src.exceptions.BadRequestException;
import src.model.dto.UserEdit;
import src.model.dto.UserInput;
import src.model.dto.UserOutput;
import src.model.dto.SignedInUser;
import src.model.entities.User;
import src.model.objectMapper.FactoryObjectMapper;
import src.service.UserService;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserOutput> createUser(@Valid @RequestBody UserInput userInput, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        User user = FactoryObjectMapper.convertUserInputToModel(userInput);
        User savedUser = userService.saveUser(user);
        UserOutput userOutput = FactoryObjectMapper.convertUserEntityToUserOutput(savedUser);
        return new ResponseEntity<>(userOutput, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserOutput> getUser(@PathVariable("userId") int userId) {
        User user= userService.getUserById(userId);
        UserOutput userOutput = FactoryObjectMapper.convertUserEntityToUserOutput(user);
        return ResponseEntity.ok(userOutput);

    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId) {
        if(!isUserAuthenticatedAndAuthorized(userId)){
            throw new SecurityException("You cannot delete someone else's profile");
        }
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{userId}")
    public ResponseEntity<UserOutput> updateUser(@Valid @RequestBody UserEdit userEdit, BindingResult bindingResult, @PathVariable("userId") int userId) {
            if(!isUserAuthenticatedAndAuthorized(userId)){
                throw new SecurityException("You cannot edit someone else's profile");
            }
            if (bindingResult.hasErrors()) {
                throw new BadRequestException(bindingResult);
            }
            User user = FactoryObjectMapper.convertUserEditToUserModel(userEdit);
            User savedUser = userService.updateUserById(user, userId);
            UserOutput userOutput = FactoryObjectMapper.convertUserEntityToUserOutput(savedUser);
            return ResponseEntity.ok(userOutput);

        }

    public Boolean isUserAuthenticatedAndAuthorized(int userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SignedInUser loggedInUser = (SignedInUser) authentication.getPrincipal();
        return loggedInUser.getId() == userId;
    }

    }






