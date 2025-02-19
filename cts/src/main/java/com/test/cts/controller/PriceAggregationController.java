package com.test.cts.controller;


import com.test.cts.model.vo.PriceVO;
import com.test.cts.model.vo.user.InfoVO;
import com.test.cts.services.PriceAggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/market")
public class PriceAggregationController {

    @Autowired
    PriceAggregationService priceAggregationService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<InfoVO> list(@RequestParam(required = false) String key){
        List<PriceVO> vo  = priceAggregationService.list(key);
        return new ResponseEntity(vo, HttpStatus.OK);
    }
}
