/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.alarm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.alarm.entity.AlarmInfo;
import com.jeesite.modules.alarm.dao.AlarmInfoDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * alarm_infoService
 * @author wsx
 * @version 2019-05-09
 */
@Service
@Transactional(readOnly=true)
public class AlarmInfoService extends CrudService<AlarmInfoDao, AlarmInfo> {
	
	/**
	 * 获取单条数据
	 * @param alarmInfo
	 * @return
	 */
	@Override
	public AlarmInfo get(AlarmInfo alarmInfo) {
		return super.get(alarmInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param alarmInfo 查询条件
	 * @param alarmInfo.page 分页对象
	 * @return
	 */
	@Override
	public Page<AlarmInfo> findPage(AlarmInfo alarmInfo) {
		return super.findPage(alarmInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param alarmInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(AlarmInfo alarmInfo) {
		super.save(alarmInfo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(alarmInfo.getId(), "alarmInfo_image");
	}
	
	/**
	 * 更新状态
	 * @param alarmInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(AlarmInfo alarmInfo) {
		super.updateStatus(alarmInfo);
	}
	
	/**
	 * 删除数据
	 * @param alarmInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(AlarmInfo alarmInfo) {
		super.delete(alarmInfo);
	}
	
}