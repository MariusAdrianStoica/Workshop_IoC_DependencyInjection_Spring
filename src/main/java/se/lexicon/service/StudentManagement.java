package se.lexicon.service;

import se.lexicon.exception.DataNotFoundException;
import se.lexicon.models.Student;

import java.util.List;

public interface StudentManagement {

    Student create()throws IllegalArgumentException;
    Student save(Student student);
    Student find(int id);
    Student remove(int id) throws DataNotFoundException;
    List<Student> findAll();
    Student edit(Student student)throws DataNotFoundException;
}
