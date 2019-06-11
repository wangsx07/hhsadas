/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.car.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.car.entity.CarDriverMsg;
import com.jeesite.modules.car.entity.CarInfo;
import com.jeesite.modules.car.service.CarDriverMsgService;
import com.jeesite.modules.car.service.CarInfoService;
import com.jeesite.modules.car.utils.ReturnJson;
import com.jeesite.modules.context.PollingStatus;
import com.jeesite.modules.utils.SecurityMD;

/**
 * car_infoController
 * @author wsx
 * @version 2019-04-23
 */
@Controller
@RequestMapping(value = "${adminPath}/car/carInfo")
public class CarInfoController extends BaseController {
	@Autowired
	private CarInfoService carInfoService;
	
	@Autowired
	private CarDriverMsgService msgService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CarInfo get(Long id, boolean isNewRecord) {
		return carInfoService.get(String.valueOf(id), isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("car:carInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CarInfo carInfo, Model model) {
		model.addAttribute("carInfo", carInfo);
		return "modules/car/carInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("car:carInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CarInfo> listData(CarInfo carInfo, HttpServletRequest request, HttpServletResponse response) {
		if(carInfo.getOnlinestate()!=null && carInfo.getOnlinestate() !=0l && carInfo.getOnlinestate() != 1l) {
			carInfo.setOnlinestate(null);
		}
		carInfo.setPage(new Page<>(request, response));
		Page<CarInfo> page = carInfoService.findPage(carInfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("car:carInfo:view")
	@RequestMapping(value = "form")
	public String form(CarInfo carInfo, Model model) {
		if(carInfo.getIsNewRecord()) {
			carInfo.setId(random(16));
			carInfo.setFlag("insert");
		}
		model.addAttribute("carInfo", carInfo);
		System.out.println("--------form------------->"+carInfo);
		System.out.println("--------form---------------->"+carInfo.getIsNewRecord());
		return "modules/car/carInfoForm";
	}

	/**
	 * 保存car_info
	 */
	@RequiresPermissions("car:carInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(HttpServletRequest request,@Validated CarInfo carInfo) {
		System.out.println(request.getRequestURI());
		String id=carInfo.getId();
		String flag=carInfo.getFlag();
		if(id!=null && id.length()==16 && flag.equals("insert")) {
			carInfo.setIsNewRecord(true);
		}
		if(id==null) {
			carInfo.setId(random(16));
		}
		System.out.println("=======save======="+carInfo);
		System.out.println("=======save======="+carInfo.getIsNewRecord());
		carInfoService.save(carInfo);
		if(carInfo.getIsNewRecord()) {
			//新创建车辆
			CarDriverMsg msg=new CarDriverMsg(carInfo.getId(),"-1","0","0");
			msgService.insert(msg);
		}else {
			//修改车辆信息
			CarDriverMsg msg=new CarDriverMsg(carInfo.getId(),"-1","0","1");
			msgService.insert(msg);
		}
		return renderResult(Global.TRUE, text("保存信息成功！"));
	}
	
	/**
	 * 删除car_info
	 */
	@RequiresPermissions("car:carInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CarInfo carInfo) {
		carInfoService.delete(carInfo);
		//这里删除关联表中的关于该car_id的记录
		carInfoService.deleteAll(carInfo.getId());
		//删除车辆信息
		CarDriverMsg msg=new CarDriverMsg(carInfo.getId(),null,"0","2");
		msgService.insert(msg);
		return renderResult(Global.TRUE, text("删除信息成功！"));
	}
	/**
	 *为指定车辆添加驾驶员
	 */
	@ResponseBody
	@RequestMapping(value="addRelate")
	public String addRelate(HttpServletRequest request) {
		String str="";
		Enumeration<String> en=request.getParameterNames();
		while(en.hasMoreElements()) {
			str=en.nextElement();
		}	
		JSONObject jobj=JSON.parseObject(str);
		String car_id=(String) jobj.get("car_id");
		JSONArray jarray=jobj.getJSONArray("ids");
		String[] idArray =new String[jarray.size()];
		for(int i=0;i<jarray.size();i++) {
			idArray[i]=jarray.getString(i);
		}
		carInfoService.addRelate(idArray,car_id);
		//添加 车辆-用户关系信息
		for(int i=0;i<idArray.length;i++) {
			CarDriverMsg msg=new CarDriverMsg(car_id,idArray[i],"0","1");
			msgService.insert(msg);
		}
		
		return renderResult(Global.TRUE, text("添加驾驶员成功！"));
	}
	/**
	 * 根据IMEI查询全部信息
	 */
	@RequestMapping(value="getInfoByIMEI")
	@ResponseBody
	public ReturnJson  getAllInfoByIMEI(String imei) {
		ReturnJson rj=new ReturnJson();
		if(null==imei) {
			rj.setCode(500);
			rj.setMsg("param is error");
			return rj;
		} 
		Map m=carInfoService.getAllInfoByImei(imei);
		rj.setCode(200);
		rj.setMsg("success");
		rj.setDate(m);
		return rj;
	}
	/**
	 * 根据imei获取最新消息
	 */
	@ResponseBody
	@RequestMapping(value="getMsgByIMEI")
	public  ReturnJson getMsgByIMEI(String imei) {
		ReturnJson rj=new ReturnJson();
		Map m=new HashMap();
		if(null==imei) {
			rj.setCode(500);
			rj.setMsg("param is error");
			return rj;
		} 
		CarDriverMsg msg=carInfoService.findMsgByImei(imei);
		if(null==msg) {
			rj.setCode(404);
			rj.setMsg("no msg");
			return rj;
		}	
			m.put("msg", msg);
			rj.setCode(200);
			rj.setMsg("success");
			rj.setDate(m);
			return rj;
	}
	/**
	 * license获取
	 * @param imei: 车辆imei
	 *        app_key: MD5(imei+HHS) 
	 */
	@ResponseBody
	@RequestMapping(value="getLicense")
	public ReturnJson getLicence(String imei,String app_key) {
		ReturnJson rj=new ReturnJson();
		String copykey=null;
		if(null!=imei) 
		   copykey=SecurityMD.md5(imei+"HHS");
		if(null==imei||"".equals(imei) || null==app_key||!app_key.equals(copykey)) {
			rj.setCode(500);
			rj.setMsg("param is error");
			return rj;
		}
		boolean b=carInfoService.judgeImei(imei);
		if(b) {
			Map m=new HashMap();
			m.put("lic", SecurityMD.md5(imei+"ADAS"));
			rj.setCode(200);
			rj.setMsg("success");
			rj.setDate(m);
			return rj;
		} 
		rj.setCode(404);
		rj.setMsg("no data");
		return rj;
	}
	
	/**
	 * 同步状态 （每1分钟客户端发送一次心跳，2分钟内接受不到心跳，认为设备离线）
	 * @param  imei 车辆imei
	 */
	@ResponseBody
	@RequestMapping("syncheart")
	public ReturnJson synstatus(String imei) {
		ReturnJson rj=new ReturnJson("success",200,null);
		if(null==imei||"".equals(imei)) {
			rj.setCode(500);
			rj.setMsg("param is error");
			return rj;
		}
		long currentTime=System.currentTimeMillis();
		if(!PollingStatus.infoMap.containsKey(imei)) {
			carInfoService.initStatus(imei);
		};
		PollingStatus.infoMap.put(imei, currentTime);
		return rj;
	}
	
	
	
	/**
	 * 生成一个m位数字字符串
	 */
	public static String random(int m) {
		StringBuilder s = new StringBuilder();
		Random random = new Random();
		Integer ints;
		for (int i = 0; i <m ; i++) {
		ints = random.nextInt(10);
		s.append(ints.toString());
		}
		return s.toString();
	}
	/**
	 * 拼接字符串
	 */
	public String  joinStr(String[] array) {
		StringBuilder s = new StringBuilder();
		for(int i=0;i<array.length;i++) {
			s.append(array[i]).append(";");
		}
		return s.toString();
		
	}
}