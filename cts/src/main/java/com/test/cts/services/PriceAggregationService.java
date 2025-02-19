package com.test.cts.services;

import com.test.cts.model.vo.PriceVO;

import java.util.List;

public interface PriceAggregationService {
    void aggregatePrices();
    List<PriceVO> list(String key);

}
