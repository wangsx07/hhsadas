/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.car.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.car.entity.CarDriverMsg;

/**
 * car_driver_msgDAO接口
 * @author wsx
 * @version 2019-04-27
 */
@MyBatisDao
public interface CarDriverMsgDao extends CrudDao<CarDriverMsg> {

	void updateState(String id);
	
}