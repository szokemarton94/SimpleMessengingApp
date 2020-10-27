package application.service;

import application.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsManagerService implements UserDetailsManager {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    UserDetailsManagerService userDetailsManagerService;
    PasswordEncoder passwordEncoder;

    //Constructor
    public UserDetailsManagerService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUser(UserDetails user) {
        User userToTransact;
        userToTransact = new User(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                new ArrayList<Authority>()
        );
        entityManager.persist(userToTransact);

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return true;
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return entityManager
                .createQuery("Select user From User user where user.userName = :userName", User.class)
                .setParameter("userName", username)
                .getSingleResult();
    }


    @Transactional
    public Authority getAuthority(String authType) {
        return entityManager
                .createQuery("SELECT a FROM Authority a WHERE a.authorityName = :authName", Authority.class)
                .setParameter("authName", authType)
                .getSingleResult();
    }
}
