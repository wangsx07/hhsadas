/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.report.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

/**
 * report_imgEntity
 * @author wsx
 * @version 2019-04-27
 */
@Table(name="report_img", alias="a", columns={
		@Column(name="id", attrName="id", label="举报id", isPK=true),
		@Column(name="imei", attrName="imei", label="车辆imei"),
		@Column(name="facefile", attrName="facefile", label="上传图片"),
		@Column(name="timetag",attrName="timetag", label="时间戳"),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
	}, orderBy="a.id DESC"
)
public class ReportImg extends DataEntity<ReportImg> {
	
	private static final long serialVersionUID = 1L;
	private String imei;		// 车辆imei
	private String facefile;		// 上传图片
	private String timetag;        //时间戳
	public ReportImg() {
		this(null);
	}

	public ReportImg(String id){
		super(id);
	}
	
	
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	public String getTimetag() {
		return timetag;
	}

	public void setTimetag(String timetag) {
		this.timetag = timetag;
	}

	@NotBlank(message="上传图片不能为空")
	@Length(min=0, max=128, message="上传图片长度不能超过 128 个字符")
	public String getFacefile() {
		return facefile;
	}

	public void setFacefile(String facefile) {
		this.facefile = facefile;
	}

	@Override
	public String toString() {
		return "ReportImg [imei=" + imei + ", facefile=" + facefile + "]";
	}
	
}