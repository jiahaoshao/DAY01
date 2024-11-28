package demo.day01.service;

import demo.day01.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {
    User loginService(String username, String password);
    User registerService(User user);
    List<User> getAllUsers();
    User saveUser(User user);
}
