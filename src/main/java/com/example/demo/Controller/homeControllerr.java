package com.example.demo.Controller;

import java.util.List;

import com.example.demo.Model.Students;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class homeControllerr {

    @Autowired
    StudentService studentService;

    @GetMapping("/") //-->  each time a login happens. a unique session id is generated.
    //                      to fetch this sessionId and to print in our website. we use this HttpsSevletRequest
    public String greet( HttpServletRequest request){
        return "hello" + request.getSession().getId();
    }

    @GetMapping("/student")
    public List<Students> gettingStudents(){
        return studentService.getStudents();
    }

    //this method is used to get csrf token
    @GetMapping("/csrfToken")
    public CsrfToken gettingCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
    //adding a data to secured
    @PostMapping("/student")
    public void addingStudents(@RequestBody Students newStud){
        studentService.addStudents(newStud);
    }

    @DeleteMapping("/student/{oldStud}")
    public void deletingStudent(@PathVariable int oldStud){
        studentService.deleteStudents(oldStud);
    }

}
