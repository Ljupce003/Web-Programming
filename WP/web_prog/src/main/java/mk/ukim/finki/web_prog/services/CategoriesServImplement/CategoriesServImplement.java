package mk.ukim.finki.web_prog.services.CategoriesServImplement;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.web_prog.model.Category;
import mk.ukim.finki.web_prog.services.CategoriesServ;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class CategoriesServImplement implements CategoriesServ {

    private List<Category> categoryList;

    @Override
    public List<Category> listCategories() {
        return this.categoryList;
    }

    @Override
    public Category create(String name, String description) {
        Category newCategory=new Category(name,description);
        this.categoryList.add(newCategory);
        return newCategory;
    }

    @Override
    public Category update(String name, String description) {
        Category updatedCategory= null;
        for (Category category : this.categoryList) {
            if(category.getName().equals(name)){
                updatedCategory=category;
                category.setDescription(description);
                break;
            }
        }
        if(updatedCategory==null){
            updatedCategory=new Category(name,description);
            this.categoryList.add(updatedCategory);
        }
        return updatedCategory;
    }

    @Override
    public void delete(String name) {
        this.categoryList.removeIf(category -> category.getName().equals(name));
    }

    @Override
    public List<Category> searchCategories(String text) {
        List<Category> searched=new ArrayList<>();
        for (Category category : this.categoryList) {
            if(category.getName().contains(text) || category.getDescription().contains(text)) {
                searched.add(category);
            }
        }

        return searched;
    }
}
