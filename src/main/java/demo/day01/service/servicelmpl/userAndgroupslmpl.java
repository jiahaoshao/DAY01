package demo.day01.service.servicelmpl;

import demo.day01.domain.userAndgroups;
import demo.day01.repository.userAndgroupsDao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class userAndgroupslmpl {
    @Resource
    private userAndgroupsDao userAndgroupsDao;

    public ResponseEntity<?> update (String name, int id) {
        List<userAndgroups> list = userAndgroupsDao.findAllByUsernameAndGroupId(name, id);
        if (list.isEmpty()) {
            userAndgroups userAndgroups = new userAndgroups();
            userAndgroups.setUsername(name);
            userAndgroups.setGroupId(id);
            userAndgroupsDao.save(userAndgroups);
            return ResponseEntity.ok("success!");
        }
        return ResponseEntity.ok("fail!");
    }
}
