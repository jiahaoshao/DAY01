package demo.day01.repository;

import demo.day01.domain.commentsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface postsDao extends JpaRepository<commentsDTO, Integer> {
    List<commentsDTO> findAllByGroupId(int groupId);
}
