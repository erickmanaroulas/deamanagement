package br.com.dea.management;

import br.com.dea.management.student.domain.Student;
import br.com.dea.management.student.repository.StudentRepository;
import br.com.dea.management.student.service.StudentService;
import br.com.dea.management.user.domain.User;
import br.com.dea.management.user.repository.UserRepository;
import br.com.dea.management.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DeamanagementApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DeamanagementApplication.class, args);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {
        //Deleting all Users
        this.userRepository.deleteAll();

        //Creating some user
        for (int i = 0; i < 5; i++) {
            User u = User.builder()
                    .email("email " + i)
                    .name("name " + i)
                    .linkedin("linkedin " + i)
                    .password("pwd " + i)
                    .build();

            Student s = Student.builder()
                    .university("UNI " + i)
                    .graduation("Grad " + i)
                    .finishDate(LocalDate.now().plusYears(4))
                    .enrollmentDate(LocalDate.now())
                    .user(u)
                    .build();

            this.studentRepository.save(s);
        }
    }
}
