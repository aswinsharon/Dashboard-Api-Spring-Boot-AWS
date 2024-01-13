package org.dashboard.dashboardjavaspringmongodb.api.controllers;

import java.util.List;

//<------------- Annotations ------->
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;

import org.dashboard.dashboardjavaspringmongodb.api.utils.utils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.dashboard.dashboardjavaspringmongodb.api.models.ApiResponse;
import org.dashboard.dashboardjavaspringmongodb.api.models.User;
import org.dashboard.dashboardjavaspringmongodb.api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.dashboard.dashboardjavaspringmongodb.api.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public ResponseEntity<ResponseEntity<ApiResponse>> getAllUsers() {
        ResponseEntity<ApiResponse> response;
        try {
            List<User> users = mongoTemplate.findAll(User.class);

            if (users.isEmpty()) {
                response = utils.jsonResponse(404, "Users not found", users);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response = utils.jsonResponse(200, "Users fetched successfully", users);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            // Handle the exception and return a custom error response
            // String errorMessage = "An error occurred while fetching users: " +
            // e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseEntity<ApiResponse>> createUser(@RequestBody User user) {
        ResponseEntity<ApiResponse> response;
        try {
            Query query = new Query();

            query.addCriteria(Criteria.where("username").is(user.getUsername())
                    .and("email").is(user.getEmail()));

            boolean userExists = mongoTemplate.exists(query, User.class);

            if (utils.validateNewUserObjectFields(user) && utils.validateNewUserObjectValues(user)) {
                if (!userExists) {
                    User createdUser = userService.createUser(user);
                    response = utils.jsonResponse(201, "User created successfully", createdUser);
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                } else {
                    response = utils.jsonResponse(409, "Invalid Username or Email");
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                }
            } else {
                response = utils.jsonResponse(400, "Invalid data");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

        } catch (Exception exception) {
            response = utils.jsonResponse(500, "Internal server error", exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntity<ApiResponse>> deleteUser(@PathVariable String id) {
        ResponseEntity<ApiResponse> response;
        if (!userRepository.existsById(id)) {
            response = utils.jsonResponse(404, "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
