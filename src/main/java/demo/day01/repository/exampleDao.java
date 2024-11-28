package demo.day01.repository;


import demo.day01.domain.example;
import demo.day01.domain.message;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface exampleDao extends JpaRepository<example, Integer> {
    @Query("SELECT u FROM example u WHERE u.username LIKE CONCAT('%', :query, '%')")
    List<example> findByUsernameContaining(@Param("query") String query);

}
