/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.car.entity;

import java.util.List;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.driver.entity.DriverInfo;

/**
 * car_infoEntity
 * @author wsx
 * @version 2019-04-23
 */
@Table(name="car_info", alias="a", columns={
		@Column(name="id", attrName="id", label="车辆id", isPK=true),
		@Column(name="platenum", attrName="platenum", label="车牌号"),
		@Column(name="organization", attrName="organization", label="所属车队组织"),
		@Column(name="imei", attrName="imei", label="终端IMEI"),
		@Column(name="onlinestate", attrName="onlinestate", label="车辆在线状态", comment="车辆在线状态（0-不在线，1-在线）"),
	}, orderBy="a.id DESC"
)
public class CarInfo extends DataEntity<CarInfo> {
	
	private static final long serialVersionUID = 1L;
	private String id;              //id
	private String platenum;		// 车牌号
	private String organization;		// 所属车队组织
	private String imei;		// 终端IMEI
	private Long onlinestate;		// 车辆在线状态（0-不在线，1-在线）
	private List<DriverInfo> driverList; //所有驾驶员
	private String flag; //标识信息
	public CarInfo() {
		this(null);
	}

	public CarInfo(String id){
		super(id);
	}
	
	@NotBlank(message="车牌号不能为空")
	@Length(min=0, max=64, message="车牌号长度不能超过 64 个字符")
	public String getPlatenum() {
		return platenum;
	}

	public void setPlatenum(String platenum) {
		this.platenum = platenum;
	}
	
	@NotBlank(message="所属车队组织不能为空")
	@Length(min=0, max=64, message="所属车队组织长度不能超过 64 个字符")
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	@NotBlank(message="终端IMEI不能为空")
	@Length(min=0, max=64, message="终端IMEI长度不能超过 64 个字符")
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	public Long getOnlinestate() {
		return onlinestate;
	}

	public void setOnlinestate(Long onlinestate) {
		this.onlinestate = onlinestate;
	}
	
	public List<DriverInfo> getDriverList() {
		return driverList;
	}

	public void setDriverList(List<DriverInfo> driverList) {
		this.driverList = driverList;
	}
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CarInfo [id=" + id + ", platenum=" + platenum + ", organization=" + organization + ", imei=" + imei
				+ ", onlinestate=" + onlinestate + ", driverList=" + driverList + ", flag=" + flag + "]";
	}
}