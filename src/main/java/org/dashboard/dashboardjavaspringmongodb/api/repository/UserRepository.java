package org.dashboard.dashboardjavaspringmongodb.api.repository;

import org.dashboard.dashboardjavaspringmongodb.api.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmailAndUsername(String email, String username);
}
