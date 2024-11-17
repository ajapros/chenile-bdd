package org.chenile.bdd.price.service;

import org.chenile.bdd.price.model.Price;

public interface PriceService {
	// Define your interface here
    public Price save(Price price);
    public Price retrieve(String id);
    public Price delete(String id);
}
