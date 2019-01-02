package com.ml.random.controller.r_origin;

import com.ml.random.domain.constant.CommonConstant;
import com.ml.random.domain.dto.response.ResponseResult;
import com.ml.random.domain.enums.ResponseStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/random/origin")
public class OriginController {

    @ApiOperation("生产随机数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1:正面 0：反面", required = true, dataType = "String", paramType = "query")

    })
    @RequestMapping(value = "generator",method = RequestMethod.POST)
    public ResponseResult register(
            @RequestParam(name = "type") Integer type
    ){
        try{
            return new ResponseResult(CommonConstant.SUCCESS, ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("生产随机数据异常",e);
            return new ResponseResult(CommonConstant.ERROR,ResponseStatus.Default.getKey(),e.getMessage());
        }
    }


}
