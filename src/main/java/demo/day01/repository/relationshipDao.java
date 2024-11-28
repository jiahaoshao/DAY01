package demo.day01.repository;

import demo.day01.domain.Information;
import demo.day01.domain.relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface relationshipDao extends JpaRepository<relationship, String> {
    List<relationship> findByRequester(String username);
    List<relationship> findByReceiver(String receiver);
    List<relationship> findByRequesterAndReceiver(String requester, String receiver);
    List<relationship> findByReceiverOrRequester(String receiver, String requester);
}
