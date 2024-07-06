package com.aldiichsan.mapper;

import com.aldiichsan.model.ProductInsertModel;
import com.aldiichsan.model.ProductModel;
import com.aldiichsan.model.ProductUpdateModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    ProductModel findById(Long id);
    List<ProductModel> getAllProduct(int size, int page);
    ProductModel getNewestProduct();
    int countAllProduct();
    void createNewProduct(ProductInsertModel body);
    void editExistingProduct(ProductUpdateModel body);
    void deleteOne(Long id);
}
