package com.insomniacoder.promotionservice.promotion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromotionResponseDTO {

    private Boolean valid;
    private Double discount;
}
