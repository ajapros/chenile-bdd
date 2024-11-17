package org.chenile.bdd.price.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.chenile.jpautils.entity.BaseJpaEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Entity
@Table(name = "price")
public class Price extends BaseJpaEntity  {
    // Define your data model here
    public String variantId;
    public BigDecimal amount;
    public void setAmount(BigDecimal amount){
        amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.amount = amount;
    }

}
