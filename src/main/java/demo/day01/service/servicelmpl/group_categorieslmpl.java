package demo.day01.service.servicelmpl;

import demo.day01.domain.group_categories;
import demo.day01.repository.group_categoriesDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class group_categorieslmpl {

    @Resource
    private group_categoriesDao group_categoriesDao;

    public group_categories update(int groupId, List<Integer> categoryId) {
        for (Integer categoryId1 : categoryId) {
            group_categories group_categories = new group_categories();
            group_categories.setGroupId(groupId);
            group_categories.setCategoryId(categoryId1);
            group_categoriesDao.save(group_categories);
        }
        return null;
    }

    public List<group_categories> findByGroupId(int groupId) {
         return group_categoriesDao.findAllByGroupId(groupId);
    }
}
