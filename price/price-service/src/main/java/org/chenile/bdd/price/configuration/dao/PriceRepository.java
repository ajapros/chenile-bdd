package org.chenile.bdd.price.configuration.dao;

import org.chenile.bdd.price.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  public interface PriceRepository extends JpaRepository<Price,String> {
    public Price findByVariantId(String variantId);
}
