package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import timebank.model.Category;
import timebank.repository.CategoryRepository;

@Service("categoryService")
public class CategoryService {

  @Autowired
  @Qualifier("categoryRepository")
  private CategoryRepository categoryRepository;

  public Iterable<Category> findAll() {
    return this.categoryRepository.findAll();
  }

  public Category createCategory(String name) {
    Category category = new Category();
    category.setName(name);
    return this.categoryRepository.save(category);
  }
}
