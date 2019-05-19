package com.gyq.controller;

import com.gyq.vo.JsonResult;
import com.gyq.vo.TransMessageVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trans")
public class TransController {

    @RequestMapping("/test")
    public JsonResult test(int id){
        TransMessageVo vo = new TransMessageVo();
        vo.setId(id);
        return JsonResult.failedResult(vo);
    }
}
