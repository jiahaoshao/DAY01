package demo.day01.service.servicelmpl;

import demo.day01.domain.Category;
import demo.day01.repository.categoryDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class categorylmpl {
    @Resource
    private categoryDao categoryDao;

    public List<Integer> createCategory(String name) {
        String[] categoriesArray = name.split(",");
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < categoriesArray.length; i++) {
            Category category = new Category();
            category.setCategoryName(categoriesArray[i]);
            categoryDao.save(category);
            int id = category.getCategoryId();
            idList.add(id);
        }
        return idList;
    }

    public Optional<Category> findById(int id) {
        return categoryDao.findById(id);
    }
}
