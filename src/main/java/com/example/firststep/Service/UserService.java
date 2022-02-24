package com.example.firststep.Service;

import com.example.firststep.Entity.Role;
import com.example.firststep.Entity.User;
import com.example.firststep.Repository.RoleRepo;
import com.example.firststep.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService, UserDetailsService {
    private final UserRepo userRepo;
    private  final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);
        if (user == null){
          log.error("User not found in the database");
          throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database:{}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }
    @Override
    public User saveUser(User user) {
        log.info("Saiving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saiving new role {} to the database", role.getName());
        return roleRepo.save(role) ;
    }

    @Override
    public void addRoleToUser(String name, String roleName) {
        log.info("Adding new Role {} to user {}", roleName , name);
        User user = userRepo.findByName(name);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String name) {
        log.info("Fetching user {}" , name);
        return userRepo.findByName(name);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users {}");
        return userRepo.findAll();
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUserBYid(Long id) {
        userRepo.deleteById(id);
    }


}
