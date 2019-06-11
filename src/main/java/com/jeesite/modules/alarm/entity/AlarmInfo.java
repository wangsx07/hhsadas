/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.alarm.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * alarm_infoEntity
 * @author wsx
 * @version 2019-05-09
 */
@Table(name="alarm_info", alias="a", columns={
		@Column(name="id", attrName="id", label="警报id", isPK=true),
		@Column(name="type", attrName="type", label="类型"),
		@Column(name="comment", attrName="comment", label="内容"),
		@Column(name="img", attrName="img", label="图片路径"),
		@Column(name="imei", attrName="imei", label="车辆imei"),
		@Column(name="driver_id", attrName="driverId", label="司机id"),
	}, orderBy="a.id DESC"
)
public class AlarmInfo extends DataEntity<AlarmInfo> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 类型
	private String comment;		// 内容
	private String img;		// 图片路径
	private String imei;		// 车辆imei
	private Long driverId;		// 司机id
	
	public AlarmInfo() {
		this(null);
	}

	public AlarmInfo(String id){
		super(id);
	}
	
	@NotBlank(message="类型不能为空")
	@Length(min=0, max=16, message="类型长度不能超过 16 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@NotBlank(message="内容不能为空")
	@Length(min=0, max=512, message="内容长度不能超过 512 个字符")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@NotBlank(message="图片路径不能为空")
	@Length(min=0, max=512, message="图片路径长度不能超过 512 个字符")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@NotBlank(message="车辆imei不能为空")
	@Length(min=0, max=32, message="车辆imei长度不能超过 32 个字符")
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	@NotNull(message="司机id不能为空")
	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	
}