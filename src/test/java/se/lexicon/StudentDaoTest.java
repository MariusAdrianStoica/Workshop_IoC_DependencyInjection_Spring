package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.lexicon.data_access.StudentDao;
import se.lexicon.exception.DataNotFoundException;
import se.lexicon.models.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class) //in order to write the unit test in spring framework
@ContextConfiguration(classes = ComponentScanConfig.class)
public class StudentDaoTest {

    @Autowired
    StudentDao testObject;
    Student createdStudent;


    @BeforeEach
    public void setup(){
        Student studentData = new Student("Marius");
        createdStudent = testObject.save(studentData);
    }

    @Test
    public void find()throws DataNotFoundException {
        Student expectedStudent = new Student(0, "Marius");
        Student actualStudent = testObject.find(createdStudent.getId());

        assertEquals(expectedStudent, actualStudent);


    }
}