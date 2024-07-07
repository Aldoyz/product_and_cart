package com.aldiichsan.service.impl;

import com.aldiichsan.exception.ApiExceptionHandling;
import com.aldiichsan.mapper.ProductTypeMapper;
import com.aldiichsan.model.ProductTypeModel;
import com.aldiichsan.service.ProductTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productTypeServiceMybatis")
public class ProductTypeServiceImpl implements ProductTypeService {

    private static final Logger log = LogManager.getLogger(ProductTypeServiceImpl.class);

    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Autowired
    private ApiExceptionHandling apiExceptionHandling;

    @Override
    public ProductTypeModel selectOne(Long id) {
        return productTypeMapper.findById(id);
    }

    @Override
    public List<ProductTypeModel> getAllTypes(int size, int page) {
        return productTypeMapper.getAllTypes(size, page);
    }

    @Override
    public int countAllTypes() {
        return productTypeMapper.countAllTypes();
    }

    @Override
    public List<ProductTypeModel> createNewType(List<String> types) {
        try {
            productTypeMapper.createNewType(types);
            return productTypeMapper.getNewestType(types.size());
        } catch (Exception e) {
            apiExceptionHandling.ExceptionHandling(e);
            return null;
        }
    }

    @Override
    public ProductTypeModel editExistingType(ProductTypeModel body) {
        try {
            productTypeMapper.editExistingType(body);
            return productTypeMapper.findById(body.getId());
        } catch (Exception e) {
            apiExceptionHandling.ExceptionHandling(e);
            return null;
        }

    }

    @Override
    public void deleteOne(Long id) {
        productTypeMapper.deleteOne(id);
    }
}
