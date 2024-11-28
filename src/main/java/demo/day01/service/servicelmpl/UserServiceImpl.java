package demo.day01.service.servicelmpl;

import demo.day01.domain.Information;
import demo.day01.domain.User;
import demo.day01.repository.InformationDao;
import demo.day01.repository.UserDao;
import demo.day01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Autowired
    private InformationDao informationDao;

    @Override
    public User loginService(String username, String password) {

        User user = userDao.findByUsernameAndPassword(username, password);

        if (user != null) {
            user.setPassword(" ");
        }
        return user;
    }

    @Override
    public User registerService(User user) {
        if (userDao.findByUsername(user.getUsername()) != null) {
            return null;
        }
        else {
            User newUser = userDao.save(user);
            if (newUser == null) {
                newUser.setPassword(" ");
            }
            return newUser;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    public User saveUser(User user) {
        Information information = new Information();
        information.setUsername(user.getUsername());
        informationDao.save(information); // 这将把information对象保存到数据库中
        return null;
    }


}
