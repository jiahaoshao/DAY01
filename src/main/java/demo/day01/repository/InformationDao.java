package demo.day01.repository;

import demo.day01.domain.Information;
import demo.day01.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;

@Repository
public interface InformationDao extends JpaRepository<Information, String> {
    List<Information> findByUsername(String username);

    Information findByusername(String username);
}
