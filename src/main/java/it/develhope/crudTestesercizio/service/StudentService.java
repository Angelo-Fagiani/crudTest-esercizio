package it.develhope.crudTestesercizio.service;

import it.develhope.crudTestesercizio.entities.Student;
import it.develhope.crudTestesercizio.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student setStudentStatus(Long id, boolean isWorking)throws Exception{
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()){
            throw new Exception();
        }
        student.get().setWorking(isWorking);
        return studentRepository.save(student.get());
    }
}
