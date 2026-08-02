package com.example.demo.Service;

import com.example.demo.Model.Users;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private UserRepo userRepo;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Users newRegister(Users users){
        //this line bycrypt the cipher text and store it in db
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return userRepo.save(users);
    }

//    public Users deleteUser(Users users){
//        return userRepo.deleteById(users.getId());
//    }

}
