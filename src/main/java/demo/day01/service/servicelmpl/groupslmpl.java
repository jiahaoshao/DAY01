package demo.day01.service.servicelmpl;

import demo.day01.domain.Category;
import demo.day01.domain.groupMain;
import demo.day01.repository.categoryDao;
import demo.day01.repository.groupsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class groupslmpl {
    @Resource
    private groupsDao groupsDao;

    @Autowired
    private categoryDao categoryDao;

    public List<groupMain> findGroupById(int id) {
        return groupsDao.findByGroupId(id);
    }

    public Integer createdGroups(String name, String description, String image, String createName) {
        groupMain groupsmain = new groupMain();
        groupsmain.setCreateName(createName);
        groupsmain.setDescription(description);
        groupsmain.setName(name);
        groupsmain.setImage(image);
        groupsDao.save(groupsmain);
        return groupsmain.getGroupId();
    }

}
