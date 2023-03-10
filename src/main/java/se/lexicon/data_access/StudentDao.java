package se.lexicon.data_access;

import se.lexicon.exception.DataNotFoundException;
import se.lexicon.models.Student;

import java.util.List;

public interface StudentDao {

    Student save(Student student);

    Student find(int id)throws RuntimeException;

    List<Student> findAll();

    void delete(int id)throws DataNotFoundException;
}
