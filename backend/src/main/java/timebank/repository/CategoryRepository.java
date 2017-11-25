package timebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import timebank.model.Category;

import java.util.Optional;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long> {

  Optional<Category> findByIdCategory(long idCategory);

  Optional<Category> findByName(String name);

}
