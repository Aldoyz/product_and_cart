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
public class ProductModel {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    String type;
    String name;
    Long price;
}
