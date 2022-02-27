package com.example.firststep.Service;

import com.example.firststep.Entity.Role;
import com.example.firststep.Entity.User;
import org.springframework.scheduling.annotation.Schedules;

import java.util.List;
//@Schedules()
public interface IUserService {
    User saveUser(User user);

    void addRoleToUser(String name, String roleName);

    User getUser(String name);

    List<User> getUsers();

    User UpdateUser(User user);

    void deleteUserBYid(Long id);
}
