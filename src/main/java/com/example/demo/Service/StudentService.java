package com.example.demo.Service;


import com.example.demo.Model.Students;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {
    List<Students> students= new ArrayList<Students>(Arrays.asList(
            new Students("Rakshi", 123, 80),
            new Students("Nishu", 124, 90),
            new Students("kushi", 125, 70),
            new Students("virat", 126, 87)
    ));

    public List<Students> getStudents(){
        return students;
    }

    public void addStudents(Students newStudent) {
        students.add(newStudent);
    }

    public void deleteStudents(int deleteStudent){
        int index=0;
        int i;
        for(i=0;i<students.size();i++){
            if(students.get(i).getStudId()==deleteStudent){
                index=i;
            }
        }
        students.remove(index);
    }
}
