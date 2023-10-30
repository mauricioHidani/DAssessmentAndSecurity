package com.devsuperior.demo.services;

import com.devsuperior.demo.entities.Role;
import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.projections.UserDetailsProjection;
import com.devsuperior.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        List<UserDetailsProjection> founded = repository.findByUsernameWithRole(username);
        User user = new User();
        user.setEmail(founded.get(0).getUsername());
        user.setPassword(founded.get(0).getPassword());
        founded.forEach(u -> user.addRoles(new Role(u.getRoleId(), u.getAuthority())));

        return user;
    }

}
