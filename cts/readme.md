API List
1. Crypto Price List 
   - URL example - http://localhost:8080/market/list
   - URL method - GET
   
2. User Info with wallet 
   - URL example - http://localhost:8080/user/info?userId=10001
   - URL method - GET
   
3. User trade history list
    - URL example - http://localhost:8080/user/trade/history?userId=10001
    - URL method - GET
4. Trade API
    - URL example - http://localhost:8080/trades/trade
    - URL method - POST
    - Request param -
      {
      "userId":10001, //User Id from user table
      "symbol":"BTCUSDT" // the symbol get from Crypto Price List,
      "quantity":0.05 // the quantity would like to request,
      "orderType":"bid or ask" // The order type for trade request only support "bid" and "ask"
      }
