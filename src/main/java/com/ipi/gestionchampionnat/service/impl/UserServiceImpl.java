package com.ipi.gestionchampionnat.service.impl;

import com.ipi.gestionchampionnat.dao.UserDao;
import com.ipi.gestionchampionnat.pojos.User;
import com.ipi.gestionchampionnat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User ajouterUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User recupererUser(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public List<User> recupererUsers() {
        return userDao.findAll();
    }

    @Override
    public User modifierUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void supprimerUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User recupererUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User connecterUser(String email, String password) {
        User user = userDao.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
