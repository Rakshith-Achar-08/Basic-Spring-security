package com.example.demo.Controller;


import com.example.demo.Model.Users;
import com.example.demo.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public Users newRegister(@RequestBody Users users){
        return registerService.newRegister(users);
    }
    
//    @DeleteMapping("/deleteUser/{deleteUser}")
//    public Users deletingRegister(@PathVariable int deleteUser){
//        registerService.deleteUser(users);
//    }

}
