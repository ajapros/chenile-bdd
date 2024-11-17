package org.chenile.bdd.price.service.impl;

import org.chenile.base.exception.BadRequestException;
import org.chenile.base.exception.ErrorNumException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.chenile.bdd.price.model.Price;
import org.chenile.bdd.price.service.PriceService;

import org.chenile.bdd.price.configuration.dao.PriceRepository;
import org.chenile.base.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class PriceServiceImpl implements PriceService{
    private static final Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);
    @Autowired
    PriceRepository priceRepository;
    @Override
    public Price save(Price entity) {
        validate(entity);
        entity = priceRepository.save(entity);
        return entity;
    }

    public Price delete(String id){
        Price entity = retrieve(id);
        priceRepository.delete(entity);
        return entity;
    }

    private void validate(Price entity){
        if (entity.variantId == null ) {
            throw new BadRequestException(1502,"variantId field is required");
        }
        if (entity.amount == null){
            throw new BadRequestException(1503,"amount field is required");
        }
        if (entity.amount.doubleValue() <= 0){
            throw new BadRequestException(1501,"amount should be greater than zero");
        }
        Price priceEntity = priceRepository.findByVariantId(entity.variantId);
        if (priceEntity != null){
            throw new BadRequestException(1505,"Price record already exists for variant");
        }
    }

    @Override
    public Price retrieve(String id) {
        Optional<Price> entity = priceRepository.findById(id);
        if (entity.isPresent()) return entity.get();
        throw new NotFoundException(1500,"Unable to find price with ID " + id);
    }
}