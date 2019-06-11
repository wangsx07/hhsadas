/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.car.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.car.dao.CarDriverMsgDao;
import com.jeesite.modules.car.dao.CarInfoDao;
import com.jeesite.modules.car.entity.CarDriverMsg;
import com.jeesite.modules.car.entity.CarInfo;
import com.jeesite.modules.driver.entity.DriverInfo;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * car_infoService
 * @author wsx
 * @version 2019-04-23
 */
@Service
@Transactional(readOnly=false)
public class CarInfoService extends CrudService<CarInfoDao, CarInfo> {
	@Autowired
	private CarInfoDao carDao;
	@Autowired
	private CarDriverMsgDao msgDao;
	/**
	 * 获取单条数据
	 * @param carInfo
	 * @return
	 */
	@Override
	public CarInfo get(CarInfo carInfo) {
		return super.get(carInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param carInfo 查询条件
	 * @param carInfo.page 分页对象
	 * @return
	 */
	@Override
	public Page<CarInfo> findPage(CarInfo carInfo) {
		return super.findPage(carInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param carInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CarInfo carInfo) {
		super.save(carInfo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(carInfo.getId(), "carInfo_image");
	}
	
	/**
	 * 更新状态
	 * @param carInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CarInfo carInfo) {
		super.updateStatus(carInfo);
	}
	
	/**
	 * 删除数据
	 * @param carInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CarInfo carInfo) {
		super.delete(carInfo);
	}

	public void addRelate(String[] idArray, String car_id) {
		for(int i=0;i<idArray.length;i++) {
			carDao.addRelate(idArray[i],car_id);
		}
		
	}
	/**
	 * 删除关联的信息
	 * @param id
	 */
	public void deleteAll(String id) {
		carDao.deleteAllRelate(id);
	}
	/**
	 * 
	 * @param imei
	 * @return
	 */
	public Map getAllInfoByImei(String imei) {
		Map m=new HashMap();
		CarInfo car=carDao.findCarByImei(imei);
		List<DriverInfo> list=carDao.findallDrivers(car.getId());
		m.put("carInfo", car);
		m.put("drivers", list);
		return m;
	}

	public CarDriverMsg findMsgByImei(String imei) {
		CarInfo car=carDao.findCarByImei(imei);
		CarDriverMsg msg=carDao.findMsgByCarId(car.getId());
		if(msg.getSendStatus().equals("1")) {
			return null;
		}
		msgDao.updateState(msg.getId());
		return msg;
	}
	/*
	 * 判断给定的imei是否存在
	 */
	public boolean judgeImei(String imei) {
		int i=carDao.findImei(imei);
		return i==1;
	}
	/*
	 * 初始化车辆状态
	 * */
	public void initStatus(String imei) {
		carDao.intiStatus(imei);
	}
	
}