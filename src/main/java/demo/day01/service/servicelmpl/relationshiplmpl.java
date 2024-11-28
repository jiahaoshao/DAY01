package demo.day01.service.servicelmpl;

import demo.day01.domain.Information;
import demo.day01.domain.relationship;
import demo.day01.repository.relationshipDao;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class relationshiplmpl {
    @Resource
    private relationshipDao relationshipDao;

    public List<relationship> findByReceiverOrRequester(String receiver, String requester) {
        List<relationship> relationships = relationshipDao.findByReceiverOrRequester(receiver, requester);
        List<demo.day01.domain.relationship> updatedRelationships = new ArrayList<>();
                for (relationship relationship : relationships) {
            if (relationship.getStatus() == 1){
                updatedRelationships.add(relationship);
            }
        }
                return updatedRelationships;
    }


    public  List<relationship> findByRequesterAndreceiver(String requester, String receiver) {
        return relationshipDao.findByRequesterAndReceiver(requester,receiver);
    }

    public  List<relationship> findByRequester(String username) {
        return relationshipDao.findByRequester(username);
    }

    public  List<relationship> findByReceiver(String username) {
        List<relationship> relationships = relationshipDao.findByReceiver(username);
        List<demo.day01.domain.relationship> updatedRelationships = new ArrayList<>();
        if (!relationships.isEmpty()) {
            for (demo.day01.domain.relationship existingInformation : relationships) {
                    if (existingInformation.getStatus() == 0) {
                        // 如果需要收集这些记录，可以添加到列表中
                        updatedRelationships.add(existingInformation);
                    }
            }
        }
        return updatedRelationships;
    }

    public relationship updaterelatonship(String Requester,String Receiver) {
        // 首先，查找具有相同 username 的数据
        List<relationship> existingrelationships = findByRequesterAndreceiver(Requester, Receiver);
        if (!existingrelationships.isEmpty()) {
            // 假设您只想更新第一条找到的数据（这通常不是最佳实践，因为可能有多个用户具有相同的 username）
            demo.day01.domain.relationship existingInformation = existingrelationships.get(0);

            existingInformation.setRequester(Requester);
            existingInformation.setReceiver(Receiver);
            existingInformation.setStatus(0);
            // 保存更新后的数据
            return relationshipDao.save(existingInformation);
        }
        else {
            relationship relationship = new relationship();
            relationship.setRequester(Requester);
            relationship.setReceiver(Receiver);
            relationshipDao.save(relationship); // 这将把information对象保存到数据库中
        }
        return null;
    }

    public relationship changerelatonship(String Requester,String Receiver) {
        System.out.println(Requester);
        System.out.println(Receiver);
        // 首先，查找具有相同 username 的数据
        List<relationship> existingrelationships = findByRequesterAndreceiver(Requester,Receiver);
        System.out.println(existingrelationships);
        if (!existingrelationships.isEmpty()) {
            for (demo.day01.domain.relationship existingInformation : existingrelationships) {
                // 更新每条数据的状态
                existingInformation.setStatus(-1);
                // 保存更新后的数据
                relationshipDao.save(existingInformation);
            }
        }
        return null;
    }

    public relationship Successrelatonship(String Requester,String Receiver) {
        // 首先，查找具有相同 username 的数据
        List<relationship> existingrelationships = findByRequesterAndreceiver(Requester,Receiver);
        if (!existingrelationships.isEmpty()) {
            for (demo.day01.domain.relationship existingInformation : existingrelationships) {
                // 更新每条数据的状态
                existingInformation.setStatus(1);
                // 保存更新后的数据
                relationshipDao.save(existingInformation);
            }
        }
        return null;
    }
}
