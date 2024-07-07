package com.aldiichsan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailsModel {
    private Long shoppingCartId;
    private Long cartDetailsId;
    private Long customerId;
    private String customerName;
    private String address;
    private Long productId;
    private String productName;
    private Long price;
    private Long typeId;
    private String type;
    private Long quantity;
    private Long total;
    private String status;
}
