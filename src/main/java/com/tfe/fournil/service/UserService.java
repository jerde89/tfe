package com.tfe.fournil.service;

import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public boolean checkIfEmailIsUnique(String email) {
        List<User> userList = userRepository.findByEmail(email);
        return CollectionUtils.isEmpty(userList);
    }
}
