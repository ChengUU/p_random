package com.ml.random.controller;

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
@RequestMapping("/random/statistics")
public class StattisticsController {

    @ApiOperation("手动统计")
    @RequestMapping(value = "handlerStatistics",method = RequestMethod.POST)
    public ResponseResult handlerStatistics(){
        try{
            return new ResponseResult(CommonConstant.SUCCESS, ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("手动统计异常",e);
            return new ResponseResult(CommonConstant.ERROR,ResponseStatus.Default.getKey(),e.getMessage());
        }
    }



    @ApiOperation("获取统计数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1:常规数据 2：1相关数据", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value = "scommon",method = RequestMethod.POST)
    public ResponseResult scommon(
            @RequestParam(name = "type") Integer type
    ){
        try{
            return new ResponseResult(CommonConstant.SUCCESS, ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("获取统计数据异常",e);
            return new ResponseResult(CommonConstant.ERROR,ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("获取原始数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNumber", value = "展示数据大小", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value = "sorigin",method = RequestMethod.POST)
    public ResponseResult sorigin(
            @RequestParam(name = "PageNumber") Integer PageNumber
    ){
        try{
            return new ResponseResult(CommonConstant.SUCCESS, ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("获取原始数据异常",e);
            return new ResponseResult(CommonConstant.ERROR,ResponseStatus.Default.getKey(),e.getMessage());
        }
    }


}
