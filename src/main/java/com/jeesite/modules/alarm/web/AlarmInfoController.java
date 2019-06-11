/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.alarm.web;

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
import com.jeesite.modules.alarm.entity.AlarmInfo;
import com.jeesite.modules.alarm.service.AlarmInfoService;

/**
 * alarm_infoController
 * @author wsx
 * @version 2019-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/alarm/alarmInfo")
public class AlarmInfoController extends BaseController {

	@Autowired
	private AlarmInfoService alarmInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public AlarmInfo get(Long id, boolean isNewRecord) {
		return alarmInfoService.get(String.valueOf(id), isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("alarm:alarmInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(AlarmInfo alarmInfo, Model model) {
		model.addAttribute("alarmInfo", alarmInfo);
		return "modules/alarm/alarmInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("alarm:alarmInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<AlarmInfo> listData(AlarmInfo alarmInfo, HttpServletRequest request, HttpServletResponse response) {
		alarmInfo.setPage(new Page<>(request, response));
		Page<AlarmInfo> page = alarmInfoService.findPage(alarmInfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("alarm:alarmInfo:view")
	@RequestMapping(value = "form")
	public String form(AlarmInfo alarmInfo, Model model) {
		model.addAttribute("alarmInfo", alarmInfo);
		return "modules/alarm/alarmInfoForm";
	}

	/**
	 * 保存alarm_info
	 */
	@RequiresPermissions("alarm:alarmInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated AlarmInfo alarmInfo) {
		alarmInfoService.save(alarmInfo);
		return renderResult(Global.TRUE, text("保存alarm_info成功！"));
	}
	
	/**
	 * 删除alarm_info
	 */
	@RequiresPermissions("alarm:alarmInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(AlarmInfo alarmInfo) {
		alarmInfoService.delete(alarmInfo);
		return renderResult(Global.TRUE, text("删除alarm_info成功！"));
	}
	
}