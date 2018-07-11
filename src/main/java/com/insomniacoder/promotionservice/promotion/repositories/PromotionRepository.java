package com.insomniacoder.promotionservice.promotion.repositories;

import com.insomniacoder.promotionservice.promotion.entities.Promotion;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PromotionRepository extends CrudRepository<Promotion, Long> {

    Optional<Promotion> findByCode(String promotionCode);
}