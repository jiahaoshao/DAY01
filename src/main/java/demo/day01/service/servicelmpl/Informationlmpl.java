package demo.day01.service.servicelmpl;

import demo.day01.domain.Information;
import demo.day01.repository.InformationDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Informationlmpl {
    @Resource
    private InformationDao informationDao;

    public  List<Information> findByUsername(String username) {
        return informationDao.findByUsername(username);
    }


    public Information updateInformation(Information information) {
        String username = information.getUsername();

        // 首先，查找具有相同 username 的数据
        List<Information> existingInformations = findByUsername(username);

        if (!existingInformations.isEmpty()) {
            // 假设您只想更新第一条找到的数据（这通常不是最佳实践，因为可能有多个用户具有相同的 username）
            Information existingInformation = existingInformations.get(0);

            existingInformation.setAge(information.getAge());

            // 保存更新后的数据
            return informationDao.save(existingInformation);
        } else {
            // 如果没有找到具有相同 username 的数据，您可以选择抛出异常、返回 null 或执行其他逻辑
            throw new RuntimeException("No information found with username: " + username);
        }
    }
}
