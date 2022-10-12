package it.develhope.crudTestesercizio.controller;

import it.develhope.crudTestesercizio.entities.Student;
import it.develhope.crudTestesercizio.repositories.StudentRepository;
import it.develhope.crudTestesercizio.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public @ResponseBody Student create(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping("/")
    public @ResponseBody List<Student> getAll(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Student getById(@PathVariable Long id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        }else{
            return null;
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody Student update(@PathVariable Long id, @RequestBody @NonNull Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    @PutMapping("/{id}/working")
    public @ResponseBody Student isWorking(@PathVariable long id, @RequestParam("working") boolean working) throws Exception {
        return studentService.setStudentStatus(id, working);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        studentRepository.deleteById(id);
    }
    }


