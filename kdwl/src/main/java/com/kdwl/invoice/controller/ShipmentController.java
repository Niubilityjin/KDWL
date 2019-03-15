package com.kdwl.invoice.controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;

    import org.springframework.web.bind.annotation.RestController;
import com.kdwl.core.include.controller.IController;
import org.springframework.beans.factory.annotation.Autowired;
import com.kdwl.invoice.service.ShipmentService;
import com.kdwl.invoice.entity.Shipment;
import io.swagger.annotations.*;
import com.kdwl.core.include.result.ProcessResult;
import com.baomidou.mybatisplus.plugins.Page;

import com.kdwl.core.include.logger.LoggerFactory;
import org.slf4j.Logger;

/**
 *　　
 *   @description : Shipment 控制器
 *   ---------------------------------
 * 	 @author Jang
 *   @since 2019-03-14
 */
@RestController
@Api(value="/shipment", description="Shipment 控制器")
@RequestMapping("/shipment")
public class ShipmentController extends IController {
    private final Logger logger = LoggerFactory.getLogger(ShipmentController.class);

    @Autowired
    public ShipmentService shipmentService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : Jang
     * @since : Create in 2019-03-14
     */
    @GetMapping("/getShipmentList")
    @ApiOperation(value="/getShipmentList", notes="获取分页列表")
    public ProcessResult<Shipment> getShipmentList(@ApiParam(name="Shipment",value="Shipment 实体类")Shipment param ,
                                @ApiParam(name="length",value="页大小",required=true,defaultValue = "10")Integer length,
                                @ApiParam(name="pageNo",value="页数",required=true,defaultValue = "1")Integer pageNo) {
            ProcessResult<Shipment> resJson=new ProcessResult<>();

            try {
                Page<Shipment> page=new Page<Shipment>(pageNo,length);
                shipmentService.selectPage(page, param.getPageWrapper());
                resJson.setRecordsTotal(page.getTotal());
                resJson.setDatas(page.getRecords());
                resJson.setRes(true);
            }catch (Exception e){
                resJson.setRes(false);
                resJson.setResult("异常信息:"+e.getClass().getName());
                logger.info("异常信息:{}",e.getMessage());
            }
            return resJson;
    }

    /**
     * @description : 通过id获取Shipment
     * ---------------------------------
     * @author : Jang
     * @since : Create in 2019-03-14
     */
    @GetMapping("/getShipmentById")
    @ApiOperation(value="/getShipmentById", notes="通过id获取Shipment")
    public ProcessResult<Shipment> getShipmentById(@ApiParam(name="id",value="ShipmentID",required=true)Long id) {
            ProcessResult<Shipment> resJson = new ProcessResult<>();
            try {
                Shipment param= shipmentService.selectById(id);
                resJson.setData(param);
                resJson.setRes(true);
            }catch (Exception e) {
                resJson.setRes(false);
                resJson.setResult("异常信息:"+e.getClass().getName());
                logger.info("异常信息:{}",e.getMessage());
            }
            return resJson;
    }

    /**
     * @description : 通过id删除Shipment
     * ---------------------------------
     * @author : Jang
     * @since : Create in 2019-03-14
     */
    @DeleteMapping("/deleteShipmentById")
    @ApiOperation(value="/deleteShipmentById", notes="通过id删除Shipment")
    public ProcessResult<Shipment> deleteShipmentById(@ApiParam(name="id",value="ShipmentID",required=true)Long id) {
            ProcessResult<Shipment> resJson = new ProcessResult<>();
            try{
                resJson.setRes(shipmentService.deleteById(id));
            }catch (Exception e) {
                resJson.setRes(false);
                resJson.setResult("异常信息:"+e.getClass().getName());
                logger.info("异常信息:{}",e.getMessage());
            }
            return resJson;
    }

    /**
     * @description : 通过id更新Shipment
     * ---------------------------------
     * @author : Jang
     * @since : Create in 2019-03-14
     */
    @PutMapping("/updateShipmentById")
    @ApiOperation(value="/updateShipmentById", notes="通过id更新Shipment")
    public ProcessResult<Shipment> updateShipmentById(@ApiParam(name="Shipment",value="Shipment 实体类")Shipment param) {
            ProcessResult<Shipment> resJson = new ProcessResult<>();
            try{
                resJson.setRes(shipmentService.updateById(param));
            }catch (Exception e) {
                resJson.setRes(false);
                resJson.setResult("异常信息:"+e.getClass().getName());
                logger.info("异常信息:{}",e.getMessage());
            }
            return resJson;
    }

    /**
     * @description : 添加Shipment
     * ---------------------------------
     * @author : Jang
     * @since : Create in 2019-03-14
     */
	@PutMapping("/addShipment")
    @ApiOperation(value="/addShipment", notes="添加Shipment")
    public ProcessResult<Shipment> addShipment(@ApiParam(name="Shipment",value="Shipment 实体类")Shipment param) {
            ProcessResult<Shipment> resJson = new ProcessResult<>();
            try{
                resJson.setRes(shipmentService.insert(param));
            }catch (Exception e) {
                resJson.setRes(false);
                resJson.setResult("异常信息:"+e.getClass().getName());
                logger.info("异常信息:{}",e.getMessage());
            }
            return resJson;
    }
}