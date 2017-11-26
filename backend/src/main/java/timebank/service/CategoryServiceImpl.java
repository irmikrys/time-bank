package timebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import timebank.model.Category;
import timebank.repository.CategoryRepository;
import java.util.Optional;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  @Qualifier("categoryRepository")
  private CategoryRepository categoryRepository;

  @Override
  public Optional<Category> findByIdCategory(long idCategory) {
    return categoryRepository.findByIdCategory(idCategory);
  }

  @Override
  public Optional<Category> findByName(String name) {
    return categoryRepository.findByName(name);
  }

  @Override
  public Iterable<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public Category createCategory(String name) {
    Category category = new Category();
    category.setName(name);
    return categoryRepository.save(category);
  }
}
