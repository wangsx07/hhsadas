/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.driver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.driver.dao.DriverInfoDao;
import com.jeesite.modules.driver.entity.DriverInfo;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * driver_infoService
 * @author wsx
 * @version 2019-04-23
 */
@Service
@Transactional(readOnly=false)
public class DriverInfoService extends CrudService<DriverInfoDao, DriverInfo> {
	@Autowired
	public DriverInfoDao driverDao;
	/**
	 * 获取单条数据
	 * @param driverInfo
	 * @return
	 */
	@Override
	public DriverInfo get(DriverInfo driverInfo) {
		return super.get(driverInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param driverInfo 查询条件
	 * @param driverInfo.page 分页对象
	 * @return
	 */
	@Override
	public Page<DriverInfo> findPage(DriverInfo driverInfo) {
		return super.findPage(driverInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param driverInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(DriverInfo driverInfo) {
		super.save(driverInfo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(driverInfo.getId(), "driverInfo_image");
	}
	
	/**
	 * 更新状态
	 * @param driverInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(DriverInfo driverInfo) {
		super.updateStatus(driverInfo);
	}
	
	/**
	 * 删除数据
	 * @param driverInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(DriverInfo driverInfo) {
		super.delete(driverInfo);
	}
	/**
	 * 根据carId查询驾驶员信息
	 */
	public List<DriverInfo> findListByCarId(String id){
		return driverDao.findlistByCarId(id);
	}
	
	/**
	 * 删除关联的驾驶员
	 */
	public void delRelByRid(String rid) {
		driverDao.delRelByRid(rid);
	}
	/**
	 * 查询除该carId关联的
	 * @param driverInfo
	 * @param carId
	 * @return
	 */
	public List<DriverInfo> findLockPage(String carId) {
		List<DriverInfo> list=driverDao.selectLockDriver(carId);
		return list;
	}
	//根据driver_id删除关系表中的记录
	public void deleteAllRel(String driver_id) {
		driverDao.deleteAllRel(driver_id);
		
	}
	//根据driver_id查询关系表中的所有车辆
	public List findCarsByDriverId(String driverId){
		return driverDao.findCarsByDriverId(driverId);
	}
}