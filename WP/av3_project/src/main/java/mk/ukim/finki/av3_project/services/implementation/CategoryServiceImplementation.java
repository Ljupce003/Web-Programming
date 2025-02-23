package mk.ukim.finki.av3_project.services.implementation;

import mk.ukim.finki.av3_project.model.Category;
import mk.ukim.finki.av3_project.repository.LocalCategoryRepository;
import mk.ukim.finki.av3_project.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {

    private final LocalCategoryRepository categoryRepository;

    public CategoryServiceImplementation(LocalCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(String name, String description) throws IllegalArgumentException{
        if(name ==null || name.isEmpty() || description ==null || description.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category newCategory=new Category(name,description);

        return categoryRepository.save(newCategory);
    }

    @Override
    public Category update(String name, String description) {
        if(name ==null || name.isEmpty() || description ==null || description.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category newCategory=new Category(name,description);

        return categoryRepository.save(newCategory);
    }

    @Override
    public void delete(String name) {
        categoryRepository.delete(name);
    }

    @Override
    public List<Category> searchCategories(String text) {
        return categoryRepository.search(text);
    }
}
