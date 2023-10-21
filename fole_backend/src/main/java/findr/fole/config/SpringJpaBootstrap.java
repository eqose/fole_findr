package findr.fole.config;

import findr.fole.enums.Role;
import findr.fole.model.Student;
import findr.fole.model.User;
import findr.fole.repository.StudentRepository;
import findr.fole.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    public SpringJpaBootstrap(UserRepository userRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadUser();
        loadStudent();
    }

    private void loadUser() {
        Optional<User> admin = userRepository.findByUsername("Admin");
        if(admin.isEmpty()) {
            User user = new User();
            user.setUsername("Admin");
            user.setFirstName("Admin");
            user.setLastName("Admin");
            user.setPassword("1234");
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }
        Optional<User> user2 = userRepository.findByUsername("User");
        if(user2.isEmpty()) {
            User user = new User();
            user.setUsername("User");
            user.setFirstName("User");
            user.setLastName("User");
            user.setPassword("User");
            user.setRole(Role.USER);
            userRepository.save(user);
        }
    }
    private void loadStudent() {
        Optional<Student> student = studentRepository.findByFirstNameAndLastName("Kevin", "Hoxhalli");
        if(student.isEmpty()) {
            Student student1 = new Student();
            student1.setFirstName("Kevin");
            student1.setLastName("Hoxhalli");
            student1.setBirthDay(LocalDate.of(2001, 05, 07));
            student1.setNationalNo("K123123351");
            studentRepository.save(student1);
        }
    }

}
