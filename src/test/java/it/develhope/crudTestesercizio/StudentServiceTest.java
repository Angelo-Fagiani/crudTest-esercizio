package it.develhope.crudTestesercizio;

import it.develhope.crudTestesercizio.entities.Student;
import it.develhope.crudTestesercizio.repositories.StudentRepository;
import it.develhope.crudTestesercizio.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles(value = "test")
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void checkStudentIsWorking()throws Exception{
        Student student = new Student();
        student.setWorking(true);
        student.setName("Angelo");
        student.setSurname("Fagiani");

        Student studentFromDB = studentRepository.save(student);
        assertThat(studentFromDB).isNotNull();
        assertThat(studentFromDB.getId()).isNotNull();

        Student studentFromService = studentService.setStudentStatus(student.getId(), true);
        assertThat(studentFromService).isNotNull();
        assertThat(studentFromService.getId()).isNotNull();
        assertThat(studentFromService.isWorking()).isTrue();

        Student studentFromFind = studentRepository.findById(studentFromDB.getId()).get();
        assertThat(studentFromFind).isNotNull();
        assertThat(studentFromFind.getId()).isNotNull();
        assertThat(studentFromFind.getId()).isNotNull().isEqualTo(studentFromDB.getId());
        assertThat(studentFromFind.isWorking()).isTrue();

    }
}
