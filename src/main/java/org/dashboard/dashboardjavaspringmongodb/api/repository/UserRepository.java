package org.dashboard.dashboardjavaspringmongodb.api.repository;

import org.dashboard.dashboardjavaspringmongodb.api.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
