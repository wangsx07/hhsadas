/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.alarm.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.alarm.entity.AlarmInfo;

/**
 * alarm_infoDAO接口
 * @author wsx
 * @version 2019-05-09
 */
@MyBatisDao
public interface AlarmInfoDao extends CrudDao<AlarmInfo> {
	
}