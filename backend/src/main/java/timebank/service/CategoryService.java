package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import timebank.model.Category;
import timebank.repository.CategoryRepository;
import java.util.Optional;

@Service("categoryService")
public class CategoryService {

  @Autowired
  @Qualifier("categoryRepository")
  private CategoryRepository categoryRepository;

  public Iterable<Category> findAll() {
    return this.categoryRepository.findAll();
  }

}
