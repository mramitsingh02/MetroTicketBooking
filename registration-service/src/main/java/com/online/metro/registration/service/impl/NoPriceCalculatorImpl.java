package com.online.metro.registration.service.impl;

import com.online.metro.registration.service.PriceCalculator;
import org.springframework.stereotype.Service;

@Service("noPriceCalculator")
public class NoPriceCalculatorImpl implements PriceCalculator {
    @Override
    public double calculatePrice(final Long from, final Long to) {
        return 0;
    }
}
