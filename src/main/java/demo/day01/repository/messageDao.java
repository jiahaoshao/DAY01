package demo.day01.repository;

import demo.day01.domain.message;
import demo.day01.domain.relationship;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface messageDao extends JpaRepository<message, String> {
    @Query("SELECT m FROM message m WHERE (m.sender = :requester AND m.receiver = :receiver) OR (m.sender = :receiver AND m.receiver = :requester)")
    List<message> findmessageByRequesterAndReceiver(@Param("requester") String requester, @Param("receiver") String receiver);
}
