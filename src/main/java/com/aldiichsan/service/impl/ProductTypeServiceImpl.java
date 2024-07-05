package com.aldiichsan.service.impl;

import com.aldiichsan.exception.AlreadyExistsException;
import com.aldiichsan.exception.ApiExceptionHandling;
import com.aldiichsan.mapper.ProductTypeMapper;
import com.aldiichsan.model.ProductTypeModel;
import com.aldiichsan.service.ProductTypeService;
import com.aldiichsan.util.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
    public List<ProductTypeModel> getAllTypes() {
        return productTypeMapper.getAllTypes();
    }

    @Override
    public int countAllTypes() {
        return productTypeMapper.countAllTypes();
    }

    @Override
    public List<ProductTypeModel> createNewType(List<String> types) {
        try {
            productTypeMapper.createNewType(types);
            return productTypeMapper.getAllTypes();
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new AlreadyExistsException(ResponseMessage.DATA_ALREADY_EXISTS.getMessage());
            } else {
                log.error("Error when updating a class.", e);
                throw e;
            }
        }
    }

    @Override
    public ProductTypeModel editExistingType(ProductTypeModel body) {
        try {
            productTypeMapper.editExistingType(body);
            return productTypeMapper.findById(body.getId());
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new AlreadyExistsException(ResponseMessage.DATA_ALREADY_EXISTS.getMessage());
            } else {
                log.error("Error when updating a class.", e);
                throw e;
            }
        }
    }

    @Override
    public void deleteOne(Long id) {
        productTypeMapper.deleteOne(id);
    }
}
