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
    Long id;
    Long typeId;
    String name;
    Long price;
}
