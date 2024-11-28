package demo.day01.repository;

import demo.day01.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface categoryDao extends JpaRepository<Category, Integer> {
    @Override
    List<Category> findAllById(Iterable<Integer> integers);

}
