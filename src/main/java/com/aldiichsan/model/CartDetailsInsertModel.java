package com.aldiichsan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailsInsertModel {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Long shoppingCartId;
    private CustomerModel customer;
    private Long productId;
    private Long quantity;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long total;
    private Long statusId;
}
