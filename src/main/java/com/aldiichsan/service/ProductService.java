package com.aldiichsan.service;

import com.aldiichsan.model.ProductInsertModel;
import com.aldiichsan.model.ProductModel;
import com.aldiichsan.model.ProductUpdateModel;

import java.util.List;

public interface ProductService {
    ProductModel selectOne(Long id);
    List<ProductModel> getAllProduct(int size, int page);
    int countAllProduct();
    ProductModel createNewProduct(ProductInsertModel body);
    ProductModel editExistingProduct(ProductUpdateModel body);
    void deleteOne(Long id);
}
