package org.dashboard.dashboardjavaspringmongodb.api.services;

import java.util.List;
import org.dashboard.dashboardjavaspringmongodb.api.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    List<User> getAllUsers();

    User getUserById(String id);

    User createUser(User user);

    User updateUser(String id, User user);

    void deleteUser(String id);
}
