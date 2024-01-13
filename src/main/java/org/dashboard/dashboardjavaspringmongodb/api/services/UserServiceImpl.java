package org.dashboard.dashboardjavaspringmongodb.api.services;

import org.dashboard.dashboardjavaspringmongodb.api.models.User;
import org.dashboard.dashboardjavaspringmongodb.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        if (userRepository.existsById(id)) {
            // user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
