package com.test.cts.services.impl;

import com.test.cts.enums.CryptoPlatform;
import com.test.cts.dao.model.crypto.PriceAggregation;
import com.test.cts.model.dto.PriceAggregationDTO;
import com.test.cts.model.vo.BinanceResponseVO;
import com.test.cts.model.vo.HuobiResponseVO;
import com.test.cts.dao.repo.PriceAggregationRepository;
import com.test.cts.model.vo.PriceVO;
import com.test.cts.services.PriceAggregationService;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceAggregationServiceImpl implements PriceAggregationService {

    Logger logger = LoggerFactory.getLogger(PriceAggregationServiceImpl.class);

    @Autowired
    private PriceAggregationRepository priceAggregationRepository;

    private static final String BINANCE_API_URL = "https://api.binance.com/api/v3/ticker/bookTicker";
    private static final String HUOBI_API_URL = "https://api.huobi.pro/market/tickers";

    private final RestTemplate restTemplate = new RestTemplate();

    // Method to fetch data from Binance
    private void fetchBinanceData(Map<String, PriceAggregationDTO> m) {
        logger.info("Get price from Binance - Started");
        ResponseEntity<BinanceResponseVO[]> response = restTemplate.getForEntity(BINANCE_API_URL, BinanceResponseVO[].class);
        if (response.getBody() == null) {
            return;
        }

        for (BinanceResponseVO data : response.getBody()) {
            // Save best pricing (best bid and ask price)
            comparePricing(m, data.getSymbol().toUpperCase(), data.getBidPrice(), data.getAskPrice(), CryptoPlatform.Binance.name());
        }
        logger.info("Get price from Binance - Completed");
    }

    // Method to fetch data from Huobi
    private void fetchHuobiData(Map<String, PriceAggregationDTO> m) {
        logger.info("Get price from Huobi - Started");
        ResponseEntity<HuobiResponseVO> response = restTemplate.getForEntity(HUOBI_API_URL, HuobiResponseVO.class);
        if (response.getBody() == null) {
            return;
        }
        List<HuobiResponseVO.Data> tickers = response.getBody().getData();
        for (HuobiResponseVO.Data ticker : tickers) {
            // Save best pricing (best bid and ask price)
            comparePricing(m, ticker.getSymbol().toUpperCase(), ticker.getBid(), ticker.getAsk(), CryptoPlatform.Huobi.name());
        }
        logger.info("Get price from Huobi - Completed");
    }

    private void comparePricing(Map<String, PriceAggregationDTO> m, String symbol,
                                double bidPrice, double askPrice,
                                String source) {
        PriceAggregationDTO vo = m.get(symbol);
        if (vo == null) {
            vo = new PriceAggregationDTO();
            vo.setSymbol(symbol);
        }

        vo.compareBid(bidPrice,source);
        vo.compareAsk(askPrice,source);

//        logger.info("Price Aggregation DTO - Symbol: {} , bid: {} , bid source: {}, ask: {}, ask source: {}",
//                symbol,vo.getBid(),vo.getBidSource(),vo.getAsk(),vo.getAskSource());
        m.put(symbol, vo);
    }

    // Method to save the best pricing into the database
    private void saveBestPricing(Map<String, PriceAggregationDTO> m) {
        logger.info("Store best price to DB - Started");
        List<PriceAggregation> list = new ArrayList<>();
        for (Map.Entry<String, PriceAggregationDTO> stringBinanceResponseVOEntry : m.entrySet()) {
            PriceAggregationDTO dto = stringBinanceResponseVOEntry.getValue();

            PriceAggregation p = new PriceAggregation();
            p.setSymbol(dto.getSymbol());
            p.setBid(dto.getBid());
            p.setBidFrom(dto.getBidSource());
            p.setAsk(dto.getAsk());
            p.setAskFrom(dto.getAskSource());
            list.add(p);

            BigDecimal bid  = new BigDecimal(p.getBid());
//            bid = bid.setScale(8); // Set scale to 8 digits
            BigDecimal ask  = new BigDecimal(p.getAsk());
//            ask = ask.setScale(8); // Set scale to 8 digits
//            logger.info("Price Aggregation - Symbol: {} , bid: {} , bid source: {}, ask: {}, ask source: {}",
//                    p.getSymbol(),bid,p.getBidFrom(),ask,p.getAskFrom());
        }

        // Update or save the pricing information
        priceAggregationRepository.saveAll(list);
        logger.info("Store best price to DB - Completed");
    }

    @Override
    public void aggregatePrices() {
        Map<String, PriceAggregationDTO> m = new HashMap<>();
        fetchBinanceData(m);
        fetchHuobiData(m);
        saveBestPricing(m);
    }

    /**
     *
     //Retrive the Crypto price list
     * @param key - The key use for filter the list
     * @return
     */
    @Override
    public List<PriceVO> list(String key) {
        logger.info("Check list started - Key: {}" ,  key);
        List<PriceAggregation> priceAggregations = null;
        List<PriceVO> priceVOS = new ArrayList<>();
        if(StringUtils.isBlank(key)){
            priceAggregations = priceAggregationRepository.findAll();
        }else{
            key = key.toUpperCase();
            priceAggregations = priceAggregationRepository.findAllBySymbol(key);
        }

        if(priceAggregations == null){
            return priceVOS;
        }

        logger.info("priceAggregations size -  {}" ,  priceAggregations.size());
        for (PriceAggregation priceAggregation : priceAggregations) {
            String bidString = String.format("%.10f", priceAggregation.getBid());
            String askString = String.format("%.10f", priceAggregation.getAsk());
            PriceVO vo = new PriceVO(priceAggregation.getSymbol(),bidString,askString);
            priceVOS.add(vo);
        }
        logger.info("priceVOS size -  {}" ,  priceVOS.size());
        return priceVOS;
    }
}