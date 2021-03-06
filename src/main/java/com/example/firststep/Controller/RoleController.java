package com.example.firststep.Controller;

import com.example.firststep.Entity.Role;
import com.example.firststep.Repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Role")
public class RoleController {
    @Autowired
    private RoleRepo roleRepo;
    @PostMapping("/save")
    public ResponseEntity<Role> getUser(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/role").toUriString());
        return ResponseEntity.created(uri).body(roleRepo.save(role));
    }
    @DeleteMapping("/delete/{role-id}")
    @ResponseBody
    public void removeRole(@PathVariable("role-id") long id) {

        roleRepo.deleteById(id);
    }
    @PutMapping("/modify-role")
    @ResponseBody
    public Role modifyRole(@RequestBody Role role) {
        return roleRepo.save(role);
    }
    @GetMapping("/retrieve-all-role")
    @ResponseBody
    public List<Role> getPub() {
        List<Role> list = roleRepo.findAll();
        return list;
    }

}
