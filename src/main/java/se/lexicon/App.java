package se.lexicon;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.lexicon.data_access.StudentDao;
import se.lexicon.exception.DataNotFoundException;
import se.lexicon.models.Student;
import se.lexicon.service.StudentManagement;
import se.lexicon.util.UserInputService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws DataNotFoundException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ComponentScanConfig.class);

        StudentDao studentDao = context.getBean(StudentDao.class);

        UserInputService userInputService =context.getBean(UserInputService.class);
        StudentManagement studentManagement = context.getBean(StudentManagement.class);

        Student testStudent = studentManagement.create();

        System.out.println("New Student1: "+ testStudent);
        studentManagement.save(testStudent);

        System.out.println("\nAll students: \n" +studentManagement.findAll()+"\n");

        Student testStudent2 = studentManagement.create();
        System.out.println("New Student2: "+ testStudent2);
        studentManagement.save(testStudent2);

        Student testStudent3 = studentManagement.create();
        System.out.println("New Student3: "+ testStudent3);
        studentManagement.save(testStudent3);


        System.out.println("\nAll students: \n" +studentManagement.findAll()+"\n");

        studentManagement.find(2);

        studentManagement.remove(1);
        System.out.println("\nAll students: \n" +studentManagement.findAll()+"\n");

        studentManagement.edit(new Student(2,"Test"));
        System.out.println("\nAll students: \n" +studentManagement.findAll()+"\n");





    }
}
