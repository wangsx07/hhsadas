/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.report.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.car.utils.ReturnJson;
import com.jeesite.modules.report.entity.ReportImg;
import com.jeesite.modules.report.service.ReportImgService;
import com.jeesite.modules.report.utils.Utils;

/**
 * report_imgController
 * 
 * @author wsx
 * @version 2019-04-27
 */
@Controller
@RequestMapping(value = "${adminPath}/report/reportImg")
public class ReportImgController extends BaseController {

	@Autowired
	private ReportImgService reportImgService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ReportImg get(Long id, boolean isNewRecord) {
		return reportImgService.get(String.valueOf(id), isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("report:reportImg:view")
	@RequestMapping(value = { "list", "" })
	public String list(ReportImg reportImg, Model model) {
		model.addAttribute("reportImg", reportImg);
		return "modules/report/reportImgList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("report:reportImg:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ReportImg> listData(ReportImg reportImg, HttpServletRequest request, HttpServletResponse response) {
		reportImg.setPage(new Page<>(request, response));
		Page<ReportImg> page = reportImgService.findPage(reportImg);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("report:reportImg:view")
	@RequestMapping(value = "form")
	public String form(ReportImg reportImg, Model model) {
		model.addAttribute("reportImg", reportImg);
		return "modules/report/reportImgForm";
	}

	/**
	 * 保存report_img
	 */
	@RequiresPermissions("report:reportImg:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ReportImg reportImg) {
		reportImgService.save(reportImg);
		return renderResult(Global.TRUE, text("保存report_img成功！"));
	}

	/**
	 * 删除report_img
	 */
	@RequiresPermissions("report:reportImg:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ReportImg reportImg) {
		reportImgService.delete(reportImg);
		return renderResult(Global.TRUE, text("删除report_img成功！"));
	}

	/**
	 * 上传报警图片
	 */
	@ResponseBody
	@RequestMapping(value = "uploadReportInfo")
	public ReturnJson uploadReportInfo(HttpServletRequest request,ReportImg report, @RequestParam("image") MultipartFile image) {
		ReturnJson rj = new ReturnJson();
		if (null == report.getImei() || null == image||null==report.getTimetag()) {
			rj.setCode(500);
			rj.setMsg("param error");
			return rj;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		sdf.format(new Date());
		String path = request.getServletContext().getRealPath("/")+"reportimg"+File.separator+sdf.format(new Date())+File.separator;
		String  str=Utils.uploadImg(path, image);
		if(null==str) {
			rj.setCode(501);
			rj.setMsg("upload error");
			return rj;
		}
		report.setFacefile(str);
		System.out.println(report);
		reportImgService.insert(report);
		rj.setCode(200);
		rj.setMsg("success");
		return rj;
	}
}