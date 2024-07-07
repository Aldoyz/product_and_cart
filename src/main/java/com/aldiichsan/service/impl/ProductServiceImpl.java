package com.aldiichsan.service.impl;

import com.aldiichsan.exception.ApiExceptionHandling;
import com.aldiichsan.mapper.ProductMapper;
import com.aldiichsan.model.ProductInsertModel;
import com.aldiichsan.model.ProductModel;
import com.aldiichsan.model.ProductUpdateModel;
import com.aldiichsan.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productServiceMybatis")
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LogManager.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ApiExceptionHandling apiExceptionHandling;

    @Override
    public ProductModel selectOne(Long id) {
        return productMapper.findById(id);
    }

    @Override
    public List<ProductModel> getAllProduct(int size, int page) {
        return productMapper.getAllProduct(size, page);
    }

    @Override
    public int countAllProduct() {
        return productMapper.countAllProduct();
    }

    @Override
    public ProductModel createNewProduct(ProductInsertModel product) {
        try {
            productMapper.createNewProduct(product);
            return productMapper.getNewestProduct();
        } catch (Exception e) {
            apiExceptionHandling.ExceptionHandling(e);
            return null;
        }
    }

    @Override
    public ProductModel editExistingProduct(ProductUpdateModel body) {
        try {
            productMapper.editExistingProduct(body);
            return productMapper.findById(body.getId());
        } catch (Exception e) {
            apiExceptionHandling.ExceptionHandling(e);
            return null;
        }

    }

    @Override
    public void deleteOne(Long id) {
        productMapper.deleteOne(id);
    }
}
