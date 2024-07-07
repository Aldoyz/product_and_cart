package com.aldiichsan.controller;

import com.aldiichsan.model.ProductTypeModel;
import com.aldiichsan.service.ProductTypeService;
import com.aldiichsan.util.ApiResponse;
import com.aldiichsan.util.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/mybatis/product-type")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Api(value = "CRUD APIs for product types")
public class MybatisProductTypeController {

    private final ProductTypeService pts;

    public MybatisProductTypeController(@Qualifier("productTypeServiceMybatis") ProductTypeService pts) {
        this.pts = pts;
    }

    @GetMapping(path = "/{id}")
    @ApiOperation("Select one product type")
    public ResponseEntity selectOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, ResponseMessage.DATA_FETCHED.getMessage(), pts.selectOne(id)));
    }

    @GetMapping(path = "/list")
    @ApiOperation("List all available product types")
    public ResponseEntity getAllProductTypes(
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "1") int page
    ) {
        int totalTypes = pts.countAllTypes();
        long totalPage = ((long) Math.ceil(((double) totalTypes) / size));
        List<ProductTypeModel> productTypes = pts.getAllTypes(size, (page-1) * size);
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, ResponseMessage.DATA_FETCHED.getMessage(), totalTypes, totalPage, page, size, productTypes));
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Add new product type")
    public ResponseEntity addProductType(
            @RequestBody List<String> types
    ) {
        List<ProductTypeModel> productTypes = pts.createNewType(types);
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.CREATED, ResponseMessage.DATA_CREATED.getMessage(), productTypes));
    }

    @PutMapping(path = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Edit existing product type")
    public ResponseEntity editProductType(
            @RequestBody ProductTypeModel body
    ) {
        ProductTypeModel editedType = pts.editExistingType(body);
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, ResponseMessage.DATA_MODIFIED.getMessage(), editedType));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation("Delete one product type")
    public ResponseEntity deleteProductType(
            @PathVariable Long id
    ) {
        pts.deleteOne(id);
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, ResponseMessage.DATA_DELETED.getMessage()));
    }
}
