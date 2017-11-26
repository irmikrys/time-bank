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

  
  public Optional<Category> findByIdCategory(long idCategory) {
    return this.categoryRepository.findByIdCategory(idCategory);
  }

  public Optional<Category> findByName(String name) {
    return this.categoryRepository.findByName(name);
  }

  public Iterable<Category> findAll() {
    return this.categoryRepository.findAll();
  }

  public Category createCategory(String name) {
    Category category = new Category();
    category.setName(name);
    return this.categoryRepository.save(category);
  }
}
