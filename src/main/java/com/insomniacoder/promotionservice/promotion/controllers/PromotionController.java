package com.insomniacoder.promotionservice.promotion.controllers;

import com.insomniacoder.promotionservice.promotion.dtos.PromotionResponseDTO;
import com.insomniacoder.promotionservice.promotion.entities.Promotion;
import com.insomniacoder.promotionservice.promotion.repositories.PromotionRepository;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/promotion")
public class PromotionController {

    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PromotionController.class);

    private final PromotionRepository promotionRepository;

    public PromotionController(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    //
    @GetMapping("/{promotionCode}")
    public PromotionResponseDTO getDiscountByPromotionCode(@PathVariable("promotionCode") String promotionCode) {
        Optional<Promotion> promotion = promotionRepository.findByCode(promotionCode);
        //  return new PromotionResponseDTO();
        return promotion.map(promotion1 -> new PromotionResponseDTO(true, promotion1.getDiscount()))
                .orElseGet(() -> new PromotionResponseDTO(false, 0D));
    }

//    //id = promotion id
//    @SuppressWarnings("unchecked")
//    @GetMapping("/{id}/promotion/{promotionCode}")
//    @HystrixCommand(fallbackMethod = "getPromotionFallBack")
//    public Map<String, String> getPromotion(@PathVariable Long id,
//                                            @PathVariable String promotionCode) {
//        LOGGER.info("getting promotion for %id",id);
//        return restTemplate.getForObject(PROMOTION_SERVICE_URL + promotionCode, Map.class);
//    }
//
//    public Map<String, String> getPromotionFallBack(Long id, String promotionCode, Throwable hystrixCommand) {
//        String message = hystrixCommand.getMessage();
//        LOGGER.info(message);
//        HashMap<String, String> response = new HashMap<>();
//        response.put("success","false");
//        response.put("message",message);
//        return response;
//    }


}
