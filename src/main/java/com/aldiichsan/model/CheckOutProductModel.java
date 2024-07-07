package com.aldiichsan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckOutProductModel {
    private Long cartDetailsId;
    private Long id;
    private String name;
    private Long price;
    private ProductTypeModel type;
    private Long quantity;
    private Long total;
    private String status;
}
