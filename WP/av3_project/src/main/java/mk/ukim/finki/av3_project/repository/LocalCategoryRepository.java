package mk.ukim.finki.av3_project.repository;

import mk.ukim.finki.av3_project.bootstrap.DataHolder;
import mk.ukim.finki.av3_project.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class LocalCategoryRepository {

    public Category save(Category category){
        DataHolder.categories.removeIf(c -> c.getName().equals(category.getName()));

        DataHolder.categories.add(category);

        return category;
    }

    public List<Category> findAll(){
        return DataHolder.categories;
    }

    public Optional<Category> findByName(String name){
        return DataHolder.categories.stream().filter(c -> c.getName().equals(name)).findFirst();
    }

    public List<Category> search(String text){
        return DataHolder.categories.stream().filter(c -> c.getName().contains(text) || c.getYear().contains(text)).collect(Collectors.toList());
    }

    public void delete(String name){
        DataHolder.categories.removeIf(c -> c.getName().equals(name));
    }

}
