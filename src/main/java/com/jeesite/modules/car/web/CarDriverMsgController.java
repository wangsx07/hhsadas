/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.car.web;

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

/**
 * car_driver_msgController
 * @author wsx
 * @version 2019-04-27
 */
@Controller
@RequestMapping(value = "${adminPath}/car/carDriverMsg")
public class CarDriverMsgController extends BaseController {

	@Autowired
	private CarDriverMsgService carDriverMsgService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CarDriverMsg get(Long id, boolean isNewRecord) {
		return carDriverMsgService.get(String.valueOf(id), isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("car:carDriverMsg:view")
	@RequestMapping(value = {"list", ""})
	public String list(CarDriverMsg carDriverMsg, Model model) {
		model.addAttribute("carDriverMsg", carDriverMsg);
		return "modules/car/carDriverMsgList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("car:carDriverMsg:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CarDriverMsg> listData(CarDriverMsg carDriverMsg, HttpServletRequest request, HttpServletResponse response) {
		carDriverMsg.setPage(new Page<>(request, response));
		Page<CarDriverMsg> page = carDriverMsgService.findPage(carDriverMsg);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("car:carDriverMsg:view")
	@RequestMapping(value = "form")
	public String form(CarDriverMsg carDriverMsg, Model model) {
		model.addAttribute("carDriverMsg", carDriverMsg);
		return "modules/car/carDriverMsgForm";
	}

	/**
	 * 保存car_driver_msg
	 */
	@RequiresPermissions("car:carDriverMsg:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CarDriverMsg carDriverMsg) {
		carDriverMsgService.save(carDriverMsg);
		return renderResult(Global.TRUE, text("保存car_driver_msg成功！"));
	}
	
	/**
	 * 删除car_driver_msg
	 */
	@RequiresPermissions("car:carDriverMsg:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CarDriverMsg carDriverMsg) {
		carDriverMsgService.delete(carDriverMsg);
		return renderResult(Global.TRUE, text("删除car_driver_msg成功！"));
	}
	
}