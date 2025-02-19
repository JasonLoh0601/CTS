package com.test.cts.controller;


import com.test.cts.dao.model.user.TransactionHistory;
import com.test.cts.model.vo.user.InfoVO;
import com.test.cts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity<InfoVO> details(@RequestParam Long userId){
        InfoVO vo  = userService.getInfo(userId);
        return new ResponseEntity(vo, HttpStatus.OK);
    }

    @RequestMapping(value = "/trade/history", method = RequestMethod.GET)
    public ResponseEntity<List<TransactionHistory>> tradeHistory(@RequestParam Long userId){
        List<TransactionHistory> vo  = userService.tradeHistory(userId);
        return new ResponseEntity(vo, HttpStatus.OK);
    }
}
