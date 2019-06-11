/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.car.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * car_driver_msgEntity
 * @author wsx
 * @version 2019-04-27
 */
@Table(name="car_driver_msg", alias="a", columns={
		@Column(name="id", attrName="id", label="消息id", isPK=true),
		@Column(name="car_id", attrName="carId", label="车辆id"),
		@Column(name="driver_id", attrName="driverId", label="驾驶员id"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="send_status", attrName="sendStatus", label="消息状态"),
		@Column(name="msg_type", attrName="msgType", label="消息类型"),
	}, orderBy="a.id DESC"
)
public class CarDriverMsg extends DataEntity<CarDriverMsg> {
	
	private static final long serialVersionUID = 1L;
	private Long carId;		// 车辆id
	private Long driverId;		// 驾驶员id
	private String sendStatus;		// 消息状态
	private String msgType;		// 消息类型
	
	public CarDriverMsg() {
		this(null);
	}

	public CarDriverMsg(String id){
		super(id);
	}
	
	
	public CarDriverMsg(String carId, String driverId, String sendStatus, String msgType) {
		super();
		this.carId = Long.parseLong(carId);
		this.driverId = Long.parseLong(driverId);
		this.sendStatus = sendStatus;
		this.msgType = msgType;
	}

	@NotNull(message="车辆id不能为空")
	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}
	
	@NotNull(message="驾驶员id不能为空")
	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	
	@NotBlank(message="消息状态不能为空")
	@Length(min=0, max=16, message="消息状态长度不能超过 16 个字符")
	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	
	@NotBlank(message="消息类型不能为空")
	@Length(min=0, max=16, message="消息类型长度不能超过 16 个字符")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
}