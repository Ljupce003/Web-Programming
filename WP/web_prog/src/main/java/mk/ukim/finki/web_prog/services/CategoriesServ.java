package mk.ukim.finki.web_prog.services;

import mk.ukim.finki.web_prog.model.Category;

import java.util.List;

public interface CategoriesServ {

    List<Category> listCategories();

    Category create(String name, String description);

    Category update(String name, String description);

    void delete(String name);

    List<Category> searchCategories(String text);

}
