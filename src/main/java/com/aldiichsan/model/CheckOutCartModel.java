package com.aldiichsan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckOutCartModel {
    private Long shoppingCartId;
    private CustomerModel customer;
    private List<CheckOutProductModel> products;
    private Long overallTotal;
}
