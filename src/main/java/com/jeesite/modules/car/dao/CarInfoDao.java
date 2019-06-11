/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.car.dao;

import java.util.List;

import org.springframework.context.annotation.Lazy;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.car.entity.CarDriverMsg;
import com.jeesite.modules.car.entity.CarInfo;
import com.jeesite.modules.driver.entity.DriverInfo;

/**
 * car_infoDAO接口
 * @author wsx
 * @version 2019-04-23
 */
@MyBatisDao
public interface CarInfoDao extends CrudDao<CarInfo> {

	void addRelate(String driver_id, String car_id);

	void deleteAllRelate(String id);

	CarInfo findCarByImei(String imei);

	List<DriverInfo> findallDrivers(String carId);

	CarDriverMsg findMsgByCarId(String carId);
	//判断imei是否存在
	int findImei(String imei);
   //更新为离线状态
	void updateSta(String imei);

	void intiStatus(String imei);
	
}