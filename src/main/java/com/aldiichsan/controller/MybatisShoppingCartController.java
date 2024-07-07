package com.aldiichsan.controller;

import com.aldiichsan.model.*;
import com.aldiichsan.service.ShoppingCartService;
import com.aldiichsan.util.ApiResponse;
import com.aldiichsan.util.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/mybatis/cart")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Api(value = "CRUD APIs for shopping cart")
public class MybatisShoppingCartController {

    private final ShoppingCartService scs;

    public MybatisShoppingCartController(@Qualifier("shoppingCartServiceMybatis") ShoppingCartService scs) {
        this.scs = scs;
    }

    @GetMapping("/details")
    @ApiOperation("Check details of items in the cart")
    public ResponseEntity checkShoppingCart(
            @RequestParam Long customerId
    ) {
        return ResponseEntity.ok().body(
                ApiResponse.success(
                        HttpStatus.OK,
                        ResponseMessage.DATA_FETCHED.getMessage(),
                        scs.getCartDetails(customerId)
                )
        );
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Add new item to the cart")
    public ResponseEntity addItemToCart(
            @RequestBody CartDetailsInsertModel body
    ) {
        scs.addItemToCart(body);
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.CREATED, ResponseMessage.DATA_CREATED.getMessage()));
    }

    @PostMapping(path = "/check-out", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Checking out all item in cart")
    public ResponseEntity checkOut(
            @RequestBody CheckOutCartModel body
    ) {
        scs.checkOutItem(body);
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, ResponseMessage.DATA_MODIFIED.getMessage()));
    }

    @DeleteMapping(path = "/remove/{id}")
    @ApiOperation("Delete one item from cart")
    public ResponseEntity deleteProductProduct(
            @PathVariable Long id
    ) {
        scs.deleteOneItem(id);
        return ResponseEntity.ok().body(ApiResponse.success(HttpStatus.OK, ResponseMessage.DATA_DELETED.getMessage()));
    }
}
