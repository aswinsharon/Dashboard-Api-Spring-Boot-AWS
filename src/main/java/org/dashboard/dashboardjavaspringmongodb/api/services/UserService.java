package org.dashboard.dashboardjavaspringmongodb.api.services;

import org.dashboard.dashboardjavaspringmongodb.api.models.User;
import org.dashboard.dashboardjavaspringmongodb.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String id, User user) {
        if (userRepository.existsById(id)) {
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public User findByUserNameAndEmail(String username, String email){
        Optional<User> user = userRepository.findByEmailAndUsername(email, username);
        return user.orElse(null);
    }
}
