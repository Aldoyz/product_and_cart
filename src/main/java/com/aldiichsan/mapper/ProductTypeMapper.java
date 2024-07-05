package com.aldiichsan.mapper;

import com.aldiichsan.model.ProductTypeModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductTypeMapper {
    ProductTypeModel findById(Long id);
    List<ProductTypeModel> getAllTypes();
    int countAllTypes();
    void createNewType(List<String> types);
    void editExistingType(ProductTypeModel body);
    void deleteOne(Long id);
}
