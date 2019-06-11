/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.report.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.report.entity.ReportImg;
import com.jeesite.modules.report.dao.ReportImgDao;

/**
 * report_imgService
 * @author wsx
 * @version 2019-04-27
 */
@Service
@Transactional(readOnly=true)
public class ReportImgService extends CrudService<ReportImgDao, ReportImg> {
	
	/**
	 * 获取单条数据
	 * @param reportImg
	 * @return
	 */
	@Override
	public ReportImg get(ReportImg reportImg) {
		return super.get(reportImg);
	}
	
	/**
	 * 查询分页数据
	 * @param reportImg 查询条件
	 * @param reportImg.page 分页对象
	 * @return
	 */
	@Override
	public Page<ReportImg> findPage(ReportImg reportImg) {
		return super.findPage(reportImg);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param reportImg
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ReportImg reportImg) {
		super.save(reportImg);
	}
	
	/**
	 * 更新状态
	 * @param reportImg
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ReportImg reportImg) {
		super.updateStatus(reportImg);
	}
	
	/**
	 * 删除数据
	 * @param reportImg
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ReportImg reportImg) {
		super.delete(reportImg);
	}
	
}