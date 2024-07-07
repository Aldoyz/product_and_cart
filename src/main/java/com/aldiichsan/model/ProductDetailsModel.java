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
public class ProductDetailsModel {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private ProductTypeModel type;
    private String name;
    private Long price;
}
