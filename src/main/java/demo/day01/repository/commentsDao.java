package demo.day01.repository;

import demo.day01.domain.comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface commentsDao extends JpaRepository<comments, Integer> {
    List<comments> findAllByPostId(int postId);
}
