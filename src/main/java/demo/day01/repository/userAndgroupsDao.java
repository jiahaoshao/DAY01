package demo.day01.repository;

import demo.day01.domain.userAndgroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.beans.JavaBean;
import java.util.List;

@Repository
public interface userAndgroupsDao extends JpaRepository <userAndgroups, Integer> {
    List<userAndgroups> findAllByUsernameAndGroupId(String username, int id);
    List<userAndgroups> findAllByUsername(String username);
    List<userAndgroups> findAllByGroupId(int id);
}
