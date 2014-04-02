package service;

import model.Category;

import java.util.List;

public interface SearchService {
    Iterable<Category> getAllCategories();
}
