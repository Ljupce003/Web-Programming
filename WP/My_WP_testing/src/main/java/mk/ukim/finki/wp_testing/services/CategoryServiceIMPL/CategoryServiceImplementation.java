package mk.ukim.finki.wp_testing.services.CategoryServiceIMPL;

import mk.ukim.finki.wp_testing.model.Category;
import mk.ukim.finki.wp_testing.repository.CategoryRepository;
import mk.ukim.finki.wp_testing.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(String name, String description) {
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
