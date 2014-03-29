package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;
import service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

    private CategoryRepository categoryRepository;

    @Autowired
    public SearchServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
