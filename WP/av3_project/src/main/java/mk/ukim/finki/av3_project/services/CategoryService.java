package mk.ukim.finki.av3_project.services;

import mk.ukim.finki.av3_project.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listCategories();

    Category create(String name,String description);

    Category update(String name,String description);

    void delete(String name);

    List<Category> searchCategories(String text);
}
