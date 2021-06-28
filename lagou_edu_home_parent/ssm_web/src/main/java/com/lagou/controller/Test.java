package com.lagou.controller;

import com.lagou.domain.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: Emest
 * @date: 2021/6/8$ 15:20$
 * @description:
 */
@Controller
@RequestMapping("/test")
public class Test {

    @RequestMapping("/rest")
    public ResponseResult listReq(@RequestBody List<Integer> list) {

        return new ResponseResult(true, 200, "链表", list);
    }
}
