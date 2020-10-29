package application.service;

import application.DTO.RegistrationDTO;
import application.entity.Authority;
import application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;

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

    /**
     * Function: create a new User in DataBase
     */
    @Override
    @Transactional
    public void createUser(UserDetails user) {
        User userToTransact;
        userToTransact = new User(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                new ArrayList<>()
        );
        userToTransact.addAuthority(userDetailsManagerService.getAuthority("USER"));
        entityManager.persist(userToTransact);

    }

    @Override
    @Transactional
    public void updateUser(UserDetails user) {
        User changedDetails = new User((RegistrationDTO) user);
    }

    @Override
    @Transactional
    public void deleteUser(String username) {

    }

    @Override
    @Transactional
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    @Transactional
    public boolean userExists(String username) {
        return true;
    }

    @Override
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
                .createQuery("SELECT a FROM Authority a WHERE a.authorityName =: authName", Authority.class)
                .setParameter("authName", authType)
                .getSingleResult();
    }
}
