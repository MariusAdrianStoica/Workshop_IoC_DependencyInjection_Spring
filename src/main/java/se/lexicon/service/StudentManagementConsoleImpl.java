package se.lexicon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.data_access.StudentDao;
import se.lexicon.exception.DataNotFoundException;
import se.lexicon.models.Student;
import se.lexicon.util.UserInputService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentManagementConsoleImpl implements StudentManagement{

    UserInputService scannerService;
    StudentDao studentDao;

    //List<Student> studentList = new ArrayList<>();

    @Autowired
    public StudentManagementConsoleImpl(UserInputService scannerService, StudentDao studentDao) {
        this.scannerService = scannerService;
        this.studentDao = studentDao;
    }

    @Override
    public Student create() throws IllegalArgumentException{
        System.out.println("\nPlease enter new student's name: ");

        String studentName = scannerService.getString();

        if (studentName.length()<1) throw new IllegalArgumentException("Name was invalid");

        return new Student(studentName);
    }

    @Override
    public Student save(Student student) {
        if (student.equals(null)) throw new IllegalArgumentException("Student was null");
        //step 1: if not null, student will get an id, using studentDao.save method;
        //step 2: new student will be added to students storage;
        //step 3: return new created student with id

        return studentDao.save(student);
    }

    @Override
    public Student find(int id){
        System.out.println("Please enter student's ID to find(must be a positive number): ");
        id = scannerService.getInt();
        //System.out.println("ID is: "+id);
        if (id < 1)throw new IllegalArgumentException("StudentId must be positive number!");
        return studentDao.find(id);
    }

    @Override
    public Student remove(int id) throws DataNotFoundException{
        System.out.println("\nPlease enter student's ID, you want to remove: ");
        id = scannerService.getInt();
        if (id < 1)throw new IllegalArgumentException("StudentId must be positive number!");
        studentDao.delete(id);
        return null;
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(studentDao.findAll());
    }

    @Override
    public Student edit(Student student) throws DataNotFoundException{
        if (student.equals(null)) throw new IllegalArgumentException("Student was null");
        Student updatedStudent = studentDao.find(student.getId());
        studentDao.save(updatedStudent);
        return updatedStudent;
    }
}
