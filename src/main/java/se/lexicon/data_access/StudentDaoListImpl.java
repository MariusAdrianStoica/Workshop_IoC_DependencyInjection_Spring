package se.lexicon.data_access;

import org.springframework.stereotype.Component;
import se.lexicon.exception.DataNotFoundException;
import se.lexicon.models.Student;
import se.lexicon.sequencer.StudentIdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class StudentDaoListImpl implements StudentDao{

    List<Student> students =new ArrayList<>();


    @Override
    public Student save(Student student) { //create
        if (student == null)throw new IllegalArgumentException("Student was null");
        if (student.getId() == 0) {
            student.setId(StudentIdGenerator.nextId());
            Student createdStudent = new Student(student.getId(), student.getName());
            students.add(createdStudent);
            System.out.println(student + " was added");
        } else students.forEach(element -> {
            if (element.getId() == student.getId()) {
                element.setName(student.getName());
                System.out.println("Student with ID: "+ student.getId() + " was updated");
            }
        });
        return null;
    }

    @Override
    public Student find(int id) throws NoSuchElementException {
        if (id < 1) throw new IllegalArgumentException("StudentId must be positive number");

        //System.out.println("students size is: "+students.size());

        /*
        for (Student element: students)
            if (element.getId() == id) return element;
        */

        Optional<Student> foundStudent = students.stream()
                                .filter(element-> element.getId() ==id)
                                .findFirst();

        if (!foundStudent.isPresent())
            System.out.println(("Student with ID: \"" + id + "\" was not found!\""));
        else System.out.println(foundStudent.get() + " was found");
        return foundStudent.get();
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public void delete(int id) throws DataNotFoundException{
        Student student = find(id);
        if (!student.equals(null)) {
            students.remove(student);
            System.out.println("Student with ID: \"" + id + "\" was removed!");
        }
        else throw new DataNotFoundException("Student with ID: \"" + id + "\" was not found!");
    }
}
