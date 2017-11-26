package timebank.service;

import timebank.model.Category;

import java.util.Optional;

public interface CategoryService {

  Optional<Category> findByIdCategory(long idCategory);

  Optional<Category> findByName(String name);

  Iterable<Category> findAll();

  Category createCategory(String name);

}
