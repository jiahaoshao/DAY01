package demo.day01.repository;

import demo.day01.domain.groupMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface groupsDao extends JpaRepository<groupMain, String> {
        List<groupMain> findByGroupId(int id);

        @Override
        List<groupMain> findAll();
}
