package demo.day01.repository;

import demo.day01.domain.group_categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface group_categoriesDao extends JpaRepository<group_categories, Integer> {

    @Override
    List<group_categories> findAllById(Iterable<Integer> integers);

    List<group_categories> findAllByGroupId(int group_id);
}
