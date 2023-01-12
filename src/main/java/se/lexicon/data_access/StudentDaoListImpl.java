package se.lexicon.data_access;

import org.springframework.stereotype.Component;
import se.lexicon.exception.DataNotFoundException;
import se.lexicon.models.Student;
import se.lexicon.sequencer.StudentIdGenerator;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDaoListImpl implements StudentDao{

    List<Student> students =new ArrayList<>();


    @Override
    public Student save(Student student) { //create
        if (student == null)throw new IllegalArgumentException("Student was null");
        student.setId(StudentIdGenerator.nextId());
        Student createdStudent = new Student(student.getId(), student.getName());
        students.add(createdStudent);
        return createdStudent;
    }

    @Override
    public Student find(int id) throws DataNotFoundException {
        if (id < 1) throw new IllegalArgumentException("StudentId must be positive number");

        Student student= null;
        for (Student element: students) {
            if (element.getId() == id) student = element;
        }
        if (student.equals(null)) throw new DataNotFoundException("Student with ID: \"" + id + "\" was not found!");

        return student;
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public void delete(int id) throws DataNotFoundException{
        Student student = find(id);
        if (!student.equals(null)) students.remove(student);
        else throw new DataNotFoundException("Student with ID: \"" + id + "\" was not found!");
    }
}
