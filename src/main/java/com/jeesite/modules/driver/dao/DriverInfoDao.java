/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.driver.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.driver.entity.DriverInfo;

/**
 * driver_infoDAO接口
 * @author wsx
 * @version 2019-04-23
 */
@MyBatisDao
public interface DriverInfoDao extends CrudDao<DriverInfo> {
	public List<DriverInfo> findlistByCarId(String id);

	public void delRelByRid(String rid);
	//
	public List<DriverInfo> selectLockDriver(String carId);

	public void deleteAllRel(String driver_id);

	public List findCarsByDriverId(String driverId);
}