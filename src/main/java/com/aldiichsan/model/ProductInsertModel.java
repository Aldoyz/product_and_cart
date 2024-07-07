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
public class ProductInsertModel {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Long typeId;
    private String name;
    private Long price;
}
