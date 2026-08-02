package com.example.demo.Controller;


import com.example.demo.Model.Users;
import com.example.demo.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;


    //this line for to register new users
    @PostMapping("/register")
    public Users newRegister(@RequestBody Users users){
        return registerService.newRegister(users);
    }

    //this line for to login after verfiy without needing to authorise each time
    @PostMapping("/login")
    public String login(@RequestBody Users users){
        return registerService.verify(users);
    }
    //after this, we need to configure the config file not to ask credential each time for login page and others certain page.
    //go to config file and do changes security filter

//    @DeleteMapping("/deleteUser/{deleteUser}")
//    public Users deletingRegister(@PathVariable int deleteUser){
//        registerService.deleteUser(users);
//    }

}
