package dev.shaz.productservice.services;

import dev.shaz.productservice.exceptions.NotFoundException;
import dev.shaz.productservice.models.Category;
import dev.shaz.productservice.models.Product;
import dev.shaz.productservice.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("selfCategoryService")
public class SelfCategoryService implements CategoryService{
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfCategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public List<String> getProductsFromCategoryId(String id) throws NotFoundException{
        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString(id));

        if(optionalCategory.isEmpty()){
            throw new NotFoundException("There are no products for the selected category");
        }

        Category category = optionalCategory.get();

        List<String> productTitleList = new ArrayList<>();
        for(Product product : category.getProduct()){
            productTitleList.add(product.getTitle());
        }

        return productTitleList;
    }

    @Override
    @Transactional
    public List<List<String>> getProductsFromCategoryIds(List<String> uuids){
        List<UUID> uuidList = new ArrayList<>();
        for(String id : uuids){
            uuidList.add(UUID.fromString(id));
        }

        List<Category> categories = categoryRepository.findAllById(uuidList);

        List<List<String>> productTitleLists = new ArrayList<>();
        for(Category category : categories){

            List<String> productTitleList = new ArrayList<>();
            for(Product product : category.getProduct()){
                productTitleList.add(product.getTitle());
            }

            productTitleLists.add(productTitleList);
        }

        return productTitleLists;
    }
}
