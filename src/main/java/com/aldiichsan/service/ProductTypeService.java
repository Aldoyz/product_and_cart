package com.aldiichsan.service;

import com.aldiichsan.model.ProductTypeModel;

import java.util.List;

public interface ProductTypeService {
    ProductTypeModel selectOne(Long id);
    List<ProductTypeModel> getAllTypes(int size, int page);
    int countAllTypes();
    List<ProductTypeModel> createNewType(List<String> types);
    ProductTypeModel editExistingType(ProductTypeModel body);
    void deleteOne(Long id);
}
