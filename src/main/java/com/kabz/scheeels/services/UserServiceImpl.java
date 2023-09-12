package com.kabz.scheeels.services;

import com.kabz.scheeels.entities.Address;
import com.kabz.scheeels.entities.User;
import com.kabz.scheeels.models.OrderModel;
import com.kabz.scheeels.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User registerNewUser(OrderModel order) {

        User user = new User();
        user.setFirstName(order.getFirstName());
        user.setLastName(order.getLastName());
        user.setEmail(order.getEmail());
        user.setAddress(Address.builder()
                .street(order.getStreet())
                .city(order.getCity())
                .state(order.getState())
                .zip(order.getZip()).build());
        user.setActive(true);

        return userRepo.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
