package com.aldiichsan.controller;

import com.aldiichsan.model.ProductInsertModel;
import com.aldiichsan.model.ProductModel;
import com.aldiichsan.model.ProductUpdateModel;
import com.aldiichsan.service.ProductService;
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
@RequestMapping("/v1/mybatis/product")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Api(value = "CRUD APIs for product types")
public class MybatisProductListController {

    private final ProductService ps;

    public MybatisProductListController(@Qualifier("productServiceMybatis") ProductService ps) {
        this.ps = ps;
    }

    @GetMapping(path = "/{id}")
    @ApiOperation("Select one product")
    public ResponseEntity selectOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, ResponseMessage.DATA_FETCHED.getMessage(), ps.selectOne(id)));
    }

    @GetMapping(path = "/list")
    @ApiOperation("List all available products")
    public ResponseEntity getAllProduct(
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "1") int page
    ) {
        int totalProducts = ps.countAllProduct();
        long totalPage = ((long) Math.ceil(((double) totalProducts) / size));
        List<ProductModel> products = ps.getAllProduct(size, (page-1) * size);
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, ResponseMessage.DATA_FETCHED.getMessage(), totalProducts, totalPage, page, size, products));
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Add new product")
    public ResponseEntity addProduct(
            @RequestBody ProductInsertModel body
    ) {
        ProductModel product = ps.createNewProduct(body);
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.CREATED, ResponseMessage.DATA_CREATED.getMessage(), product));
    }

    @PutMapping(path = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Edit existing product")
    public ResponseEntity editProductProduct(
            @RequestBody ProductUpdateModel body
    ) {
        ProductModel editedType = ps.editExistingProduct(body);
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, ResponseMessage.DATA_MODIFIED.getMessage(), editedType));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation("Delete one product")
    public ResponseEntity deleteProductProduct(
            @PathVariable Long id
    ) {
        ps.deleteOne(id);
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, ResponseMessage.DATA_DELETED.getMessage()));
    }
}
