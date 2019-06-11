/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.car.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.car.entity.CarDriverMsg;
import com.jeesite.modules.car.dao.CarDriverMsgDao;

/**
 * car_driver_msgService
 * @author wsx
 * @version 2019-04-27
 */
@Service
@Transactional(readOnly=false)
public class CarDriverMsgService extends CrudService<CarDriverMsgDao, CarDriverMsg> {
	
	/**
	 * 获取单条数据
	 * @param carDriverMsg
	 * @return
	 */
	@Override
	public CarDriverMsg get(CarDriverMsg carDriverMsg) {
		return super.get(carDriverMsg);
	}
	
	/**
	 * 查询分页数据
	 * @param carDriverMsg 查询条件
	 * @param carDriverMsg.page 分页对象
	 * @return
	 */
	@Override
	public Page<CarDriverMsg> findPage(CarDriverMsg carDriverMsg) {
		return super.findPage(carDriverMsg);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param carDriverMsg
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CarDriverMsg carDriverMsg) {
		super.save(carDriverMsg);
	}
	
	/**
	 * 更新状态
	 * @param carDriverMsg
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CarDriverMsg carDriverMsg) {
		super.updateStatus(carDriverMsg);
	}
	
	/**
	 * 删除数据
	 * @param carDriverMsg
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CarDriverMsg carDriverMsg) {
		super.delete(carDriverMsg);
	}
	
}