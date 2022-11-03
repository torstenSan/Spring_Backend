package com.ama_mind.ama_mind_app_DB.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ama_mind.ama_mind_app_DB.exceptions.EntityAlreadyExistsException;
import com.ama_mind.ama_mind_app_DB.exceptions.EntityNotFoundException;
import com.ama_mind.ama_mind_app_DB.model.User;

@Service
public class UserService {

    private static List<User> users;

    private static final String ALREADY_EXISTS_MESSAGE = "Customer with firstName: %s and lastName: %s is already existing";

    public List<User> getUser() {
        initUser();
        return users;
    }

    private void initUser() {
        if(users==null){
            User user1 = new User();
            user1.setUserKey(UUID.randomUUID().toString());
            user1.setPassword("Smile1987!");
            user1.setFirstcategorie("depression");

            User user2 = new User();
            user2.setUserKey(UUID.randomUUID().toString());
            user2.setPassword("Smile1987!");
            user2.setFirstcategorie("depression");

            users = Arrays.asList(user1, user2);
        }
    }

        public User getUserByKey(String key) {
        return users.stream()
                .filter(us -> us.getUserKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("Customer not found with id: %s", key)));
    }

   
    public User createUser(User us) {

        checkExistingUser(us);

        us.setUserKey(UUID.randomUUID().toString());
        users.add(us);

        return us;
    }

    private void checkExistingUser(User us) {
        users.stream()
                .filter(cus -> cus.getUserKey().equals(us.getUserKey()))
                .filter(cus -> cus.getPassword().equals(us.getPassword()))
                .findFirst()
                .ifPresent(cus -> {
                    throw new EntityAlreadyExistsException(String.format(ALREADY_EXISTS_MESSAGE, cus.getUserKey(), cus.getPassword()));
                });
    }

    public void deleteUser(String id) {
        users = users.stream()
                .filter(user -> !user.getUserKey().equals(id))
                .collect(Collectors.toList());
    }

}
