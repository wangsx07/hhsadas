/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.driver.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.car.entity.CarDriverMsg;
import com.jeesite.modules.car.service.CarDriverMsgService;
import com.jeesite.modules.driver.entity.DriverInfo;
import com.jeesite.modules.driver.service.DriverInfoService;

/**
 * driver_infoController
 * @author wsx
 * @version 2019-04-23
 */
@Controller
@RequestMapping(value = "${adminPath}/driver/driverInfo")
public class DriverInfoController extends BaseController {

	@Autowired
	private DriverInfoService driverInfoService;
	@Autowired
	private CarDriverMsgService msgService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DriverInfo get(Long id, boolean isNewRecord) {
		return driverInfoService.get(String.valueOf(id), isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("driver:driverInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(DriverInfo driverInfo, Model model) {
		model.addAttribute("driverInfo", driverInfo);
		return "modules/driver/driverInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("driver:driverInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DriverInfo> listData(DriverInfo driverInfo, HttpServletRequest request, HttpServletResponse response) {
		driverInfo.setPage(new Page<>(request, response));
		Page<DriverInfo> page = driverInfoService.findPage(driverInfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("driver:driverInfo:view")
	@RequestMapping(value = "form")
	public String form(DriverInfo driverInfo, Model model) {
		model.addAttribute("driverInfo", driverInfo);
		System.out.println("-------form----------->"+driverInfo);
		return "modules/driver/driverInfoForm";
	}

	/**
	 * 保存driver_info
	 */
	@RequiresPermissions("driver:driverInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DriverInfo driverInfo) {
		if(!driverInfo.getIsNewRecord()) {
			//修改了司机信息
			List ids=driverInfoService.findCarsByDriverId(driverInfo.getId());
			for(int i=0;i<ids.size();i++) {
				CarDriverMsg msg=new CarDriverMsg((String)ids.get(i),driverInfo.getId(),"0","1");			
				msgService.insert(msg);
			}
		}
		driverInfoService.save(driverInfo);
		return renderResult(Global.TRUE, text("保存司机信息成功！"));
	}
	
	/**
	 * 删除driver_info
	 */
	@RequiresPermissions("driver:driverInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DriverInfo driverInfo) {
		//驾驶员物理删除消息
		List ids=driverInfoService.findCarsByDriverId(driverInfo.getId());
		for(int i=0;i<ids.size();i++) {
			CarDriverMsg msg=new CarDriverMsg((String)ids.get(i),driverInfo.getId(),"0","1");			
			msgService.insert(msg);
		}
		driverInfoService.delete(driverInfo);
		//根据driver_id删除关系表中的关系
		driverInfoService.deleteAllRel(driverInfo.getId());
		return renderResult(Global.TRUE, text("删除司机信息成功！"));
	}
	/**
	 * 根据car_id查询司机列表
	 */
	@RequestMapping(value = "findDrivers")
	@ResponseBody
	public List<DriverInfo> findDrivers(String id,Model model){
		System.out.println("id----------------->"+id);
		List<DriverInfo> drivers=driverInfoService.findListByCarId(id);
		return drivers;
	}
	/**
	 * 根据id删除 司机-车辆的关系
	 *  
	 */
	@RequestMapping(value="deleteRelation")
	@ResponseBody
	public String deleteRelation(String car_id,String rrid) {
		//删除 车辆-司机关系
		CarDriverMsg msg=new CarDriverMsg(car_id,"-1","0","1");
		msgService.insert(msg);
		
		driverInfoService.delRelByRid(rrid);
		return renderResult(Global.TRUE, text("删除关联的驾驶员信息成功！"));
	}
	
	/**
	 * 根据car_id查询剩余司机
	 */
	@RequestMapping(value = {"lockList", ""})
	public String lockList(String carId,DriverInfo driverInfo, Model model) {
		model.addAttribute("driverInfo", driverInfo);
		model.addAttribute("carId", carId);
		return "modules/driver/urldriverInfoList";
	}
	
	@SuppressWarnings("all")
	@RequestMapping(value = "lockListData")
	@ResponseBody
	public Page<DriverInfo> lockListData(DriverInfo driverInfo,String carId, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("+++++++++++++++++++>"+carId);
		driverInfo.setPage(new Page<>(request, response));
		Page p=driverInfo.getPage();
		List<DriverInfo> list=driverInfoService.findLockPage(carId);
		p.setList(list);
		return p;
	}
	//拼接字符串
	public String joinStr(List ids) {
		StringBuilder s=new StringBuilder();
		for(int i=0;i<ids.size();i++) {
			s.append(ids.get(i)).append(";");
		}
		return s.toString();
	}
}