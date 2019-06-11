/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.driver.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * driver_infoEntity
 * @author wsx
 * @version 2019-04-23
 */
@Table(name="driver_info", alias="a", columns={
		@Column(name="id", attrName="id", label="司机id", isPK=true),
		@Column(name="name", attrName="name", label="司机姓名", queryType=QueryType.LIKE),
		@Column(name="telephone", attrName="telephone", label="电话号码"),
		@Column(name="sex", attrName="sex", label="性别"),
		@Column(name="facefile", attrName="facefile", label="人脸图"),
	}, orderBy="a.id DESC"
)
public class DriverInfo extends DataEntity<DriverInfo> {
	
	private static final long serialVersionUID = 1L;
	private String rid;             //关联id 
	private String name;		    // 司机姓名
	private String telephone;		// 电话号码
	private String sex;		        // 性别
	private String facefile;		// 人脸图
	public DriverInfo() {
		this(null);
	}

	public DriverInfo(String id){
		super(id);
	}
	
	@NotBlank(message="司机姓名不能为空")
	@Length(min=0, max=64, message="司机姓名长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="电话号码长度不能超过 64 个字符")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=32, message="性别长度不能超过 32 个字符")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@NotBlank(message="人脸图片不能为空")
	@Length(min=0, max=128, message="图片路径长度不能超过 128 个字符")
	public String getFacefile() {
		return facefile;
	}

	public void setFacefile(String facefile) {
		this.facefile = facefile;
	}
	
	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	@Override
	public String toString() {
		return "DriverInfo [name=" + name + ", telephone=" + telephone + ", sex=" + sex + ", facefile=" + facefile+", id="+id
				+ ", rid="+rid+"]";
	}
	
}