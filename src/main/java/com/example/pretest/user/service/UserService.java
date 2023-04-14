package com.example.pretest.user.service;

import com.example.pretest.user.dto.request.UserRequest;
import com.example.pretest.user.dto.response.UserResponse;
import com.example.pretest.user.exception.UserException;
import com.example.pretest.user.model.User;
import com.example.pretest.user.model.UserProfile;
import com.example.pretest.user.repository.UserProfileRepository;
import com.example.pretest.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserProfileRepository userProfileRepository;

    public List<UserResponse> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();
        for (User user : userList) {
            UserProfile userProfile = userProfileRepository.findByUserId(user.getId());
            responses.add(new UserResponse(user, userProfile));
        }

        return responses;
    }

    public UserResponse getUserById(Long id) throws UserException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserException("user not found");
        }
        UserProfile userProfile = userProfileRepository.findByUserId(user.getId());
        return new UserResponse(user, userProfile);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createNewUser(UserRequest userRequest) {
        User newUser = new User();
        User savedUser = saveUser(newUser, userRequest);
        UserProfile userProfile = new UserProfile();
        saveUserProfile(savedUser, userProfile, userRequest);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User saveUser(User newUser, UserRequest userRequest) {
        newUser.setUserName(userRequest.getUserName());
        newUser.setPassword(userRequest.getPassword());
        newUser = userRepository.save(newUser);

        return newUser;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserProfile saveUserProfile(User user, UserProfile userProfile, UserRequest userRequest) {
        userProfile.setUserId(user);
        userProfile.setFullName(userRequest.getFullName());
        userProfile.setAddress(userRequest.getAddress());
        userProfile.setNik(userRequest.getNik());
        userProfile.setAvatar(userRequest.getAvatar());
        userProfile = userProfileRepository.save(userProfile);
        return userProfile;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateUser(Long id, UserRequest userRequest) throws UserException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserException("user not found");
        }
        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        user = userRepository.save(user);

        UserProfile userProfile = userProfileRepository.findByUserId(user.getId());
        userProfile.setFullName(userRequest.getFullName());
        userProfile.setAddress(userRequest.getAddress());
        userProfile.setNik(userRequest.getNik());
        userProfile.setAvatar(userRequest.getAvatar());
        userProfileRepository.save(userProfile);
    }

    public void deleteUserById(Long id) throws UserException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserException("user not found");
        }
        UserProfile userProfile = userProfileRepository.findByUserId(user.getId());
        userProfileRepository.deleteById(userProfile.getId());
        userRepository.deleteById(user.getId());
    }
}
