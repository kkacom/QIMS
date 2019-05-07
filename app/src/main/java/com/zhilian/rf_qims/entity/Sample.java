package com.zhilian.rf_qims.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by sl on 2017-10-20.
 */
@Entity
public class Sample implements Serializable{

	private static final long serialVersionUID = -5413752457680450495L;
	@Id(autoincrement = true)
	private Long id;//1.样品ID
	private String addr;//3.项目地点
	private String part;//4.使用部位
	private String sampleModel;//5.样品型号
	private String madeDate;//6.制作日期
	private String installDate;//7.安装日期
	private String madeNo;//8.生产编号
	private String checkDate;//9.检验日期
	private String siteNumber;//9.现场编号
	private String sampleNo;//10.样品编号
	private String sampleState;//11.样品状态
	private String sampleType;//12.样品类型
	private String installAddr;//13.安装位置
	private String temperature;//14.温度
	private String humidity;//15.湿度
	private String desc;//16.描述
	private int status;//17.状态（0.服务器委托，1.本地新建，2.已上传）
	private long pid;//18.项目Id
	private String tester; //主检人员
	private String tester1; //辅检人员
	private String demo2Ddate;// 第几块（由上至下）

	public String getTester() {
		return tester;
	}

	public void setTester(String tester) {
		this.tester = tester;
	}

	public String getTester1() {
		return tester1;
	}

	public void setTester1(String tester1) {
		this.tester1 = tester1;
	}

	//生产尺寸-门框(底框)
	private String door_frame_height;
	private String door_frame_width;

	//生产尺寸-门扇(底扇)
	private String door_leaf_width;
	private String door_leaf_height;
	private String door_leaf_thickness;
	private String battenHeight;

	//生产尺寸-悬摆门
	private String hangingPlateWidth;
	private String hangingPlateWidthCenter;
	private String hangingPlateWidthBottom;
	private String hangingPlateHeight;
	private String hangingPlateHeightCenter;
	private String hangingPlateHeightBottom;

	//装配尺寸-直径公差
	private String hingepageShaftDiameter;
	private String hingepageHoleDiameter;
	private String atresiaShaftDiameter;
	private String atresiaHoleDiameter;

	//装配尺寸-钢板厚度
	private String doorframeSteelThick;
	private String steel_angle_width;
	private String steelPlatePositive;
	private String steelPlateOpposite;

	//生产质量-加工质量
	private String legHeightAcross;

	//生产质量-混凝土强度
	private String strength;

	//生产质量-钢筋分布
	private String steelbarProtectThick;
	private String steelbarSpacing;
	private String barDiameterDesignValue;

	//生产质量-悬摆门
	private String doorLeafBaseVentWidth;
	private String doorLeafBaseVentHeight;
	private String hangingPlateThicknessTop;
	private String hangingPlateThicknessBottom;
	private String steelPlantThickness;

	//安装质量-悬摆门
	private String equivalent_pipe_diameter;

	public Integer getIsselected() {
		return isselected;
	}

	public void setIsselected(Integer isselected) {
		this.isselected = isselected;
	}

	private Integer isselected;//0没选中，1选中

    public Integer getIsprinted() {
        return isprinted;
    }

    public void setIsprinted(Integer isprinted) {
        this.isprinted = isprinted;
    }

    private Integer isprinted;//0没选中，1选中

				@Generated(hash = 1039214001)
				public Sample(Long id, String addr, String part, String sampleModel,
						String madeDate, String installDate, String madeNo, String checkDate,
						String siteNumber, String sampleNo, String sampleState, String sampleType,
						String installAddr, String temperature, String humidity, String desc,
						int status, long pid, String tester, String tester1, String demo2Ddate,
						String door_frame_height, String door_frame_width, String door_leaf_width,
						String door_leaf_height, String door_leaf_thickness, String battenHeight,
						String hangingPlateWidth, String hangingPlateWidthCenter,
						String hangingPlateWidthBottom, String hangingPlateHeight,
						String hangingPlateHeightCenter, String hangingPlateHeightBottom,
						String hingepageShaftDiameter, String hingepageHoleDiameter,
						String atresiaShaftDiameter, String atresiaHoleDiameter,
						String doorframeSteelThick, String steel_angle_width,
						String steelPlatePositive, String steelPlateOpposite, String legHeightAcross,
						String strength, String steelbarProtectThick, String steelbarSpacing,
						String barDiameterDesignValue, String doorLeafBaseVentWidth,
						String doorLeafBaseVentHeight, String hangingPlateThicknessTop,
						String hangingPlateThicknessBottom, String steelPlantThickness,
						String equivalent_pipe_diameter, Integer isselected, Integer isprinted) {
					this.id = id;
					this.addr = addr;
					this.part = part;
					this.sampleModel = sampleModel;
					this.madeDate = madeDate;
					this.installDate = installDate;
					this.madeNo = madeNo;
					this.checkDate = checkDate;
					this.siteNumber = siteNumber;
					this.sampleNo = sampleNo;
					this.sampleState = sampleState;
					this.sampleType = sampleType;
					this.installAddr = installAddr;
					this.temperature = temperature;
					this.humidity = humidity;
					this.desc = desc;
					this.status = status;
					this.pid = pid;
					this.tester = tester;
					this.tester1 = tester1;
					this.demo2Ddate = demo2Ddate;
					this.door_frame_height = door_frame_height;
					this.door_frame_width = door_frame_width;
					this.door_leaf_width = door_leaf_width;
					this.door_leaf_height = door_leaf_height;
					this.door_leaf_thickness = door_leaf_thickness;
					this.battenHeight = battenHeight;
					this.hangingPlateWidth = hangingPlateWidth;
					this.hangingPlateWidthCenter = hangingPlateWidthCenter;
					this.hangingPlateWidthBottom = hangingPlateWidthBottom;
					this.hangingPlateHeight = hangingPlateHeight;
					this.hangingPlateHeightCenter = hangingPlateHeightCenter;
					this.hangingPlateHeightBottom = hangingPlateHeightBottom;
					this.hingepageShaftDiameter = hingepageShaftDiameter;
					this.hingepageHoleDiameter = hingepageHoleDiameter;
					this.atresiaShaftDiameter = atresiaShaftDiameter;
					this.atresiaHoleDiameter = atresiaHoleDiameter;
					this.doorframeSteelThick = doorframeSteelThick;
					this.steel_angle_width = steel_angle_width;
					this.steelPlatePositive = steelPlatePositive;
					this.steelPlateOpposite = steelPlateOpposite;
					this.legHeightAcross = legHeightAcross;
					this.strength = strength;
					this.steelbarProtectThick = steelbarProtectThick;
					this.steelbarSpacing = steelbarSpacing;
					this.barDiameterDesignValue = barDiameterDesignValue;
					this.doorLeafBaseVentWidth = doorLeafBaseVentWidth;
					this.doorLeafBaseVentHeight = doorLeafBaseVentHeight;
					this.hangingPlateThicknessTop = hangingPlateThicknessTop;
					this.hangingPlateThicknessBottom = hangingPlateThicknessBottom;
					this.steelPlantThickness = steelPlantThickness;
					this.equivalent_pipe_diameter = equivalent_pipe_diameter;
					this.isselected = isselected;
					this.isprinted = isprinted;
				}

				@Generated(hash = 976859954)
				public Sample() {
				}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddr() {
					return this.addr;
	}
	public void setAddr(String addr) {
					this.addr = addr;
	}
	public String getPart() {
					return this.part;
	}
	public void setPart(String part) {
					this.part = part;
	}
	public String getSampleModel() {
					return this.sampleModel;
	}
	public void setSampleModel(String sampleModel) {
					this.sampleModel = sampleModel;
	}
	public String getMadeDate() {
					return this.madeDate;
	}
	public void setMadeDate(String madeDate) {
					this.madeDate = madeDate;
	}
	public String getInstallDate() {
					return this.installDate;
	}
	public void setInstallDate(String installDate) {
					this.installDate = installDate;
	}
	public String getMadeNo() {
					return this.madeNo;
	}
	public void setMadeNo(String madeNo) {
					this.madeNo = madeNo;
	}
	public String getCheckDate() {
					return this.checkDate;
	}
	public void setCheckDate(String checkDate) {
					this.checkDate = checkDate;
	}
	public String getSampleNo() {
					return this.sampleNo;
	}
	public void setSampleNo(String sampleNo) {
					this.sampleNo = sampleNo;
	}
	public String getSampleState() {
					return this.sampleState;
	}
	public void setSampleState(String sampleState) {
					this.sampleState = sampleState;
	}
	public String getSampleType() {
					return this.sampleType;
	}
	public void setSampleType(String sampleType) {
					this.sampleType = sampleType;
	}
	public String getInstallAddr() {
					return this.installAddr;
	}
	public void setInstallAddr(String installAddr) {
					this.installAddr = installAddr;
	}
	public String getTemperature() {
					return this.temperature;
	}
	public void setTemperature(String temperature) {
					this.temperature = temperature;
	}
	public String getHumidity() {
					return this.humidity;
	}
	public void setHumidity(String humidity) {
					this.humidity = humidity;
	}
	public String getDesc() {
					return this.desc;
	}
	public void setDesc(String desc) {
					this.desc = desc;
	}
	public int getStatus() {
					return this.status;
	}
	public void setStatus(int status) {
					this.status = status;
	}
	public long getPid() {
					return this.pid;
	}
	public void setPid(long pid) {
					this.pid = pid;
	}

	public String getDoor_frame_height() {
		return door_frame_height;
	}

	public void setDoor_frame_height(String door_frame_height) {
		this.door_frame_height = door_frame_height;
	}

	public String getDoor_frame_width() {
		return door_frame_width;
	}

	public void setDoor_frame_width(String door_frame_width) {
		this.door_frame_width = door_frame_width;
	}

	public String getDoor_leaf_width() {
		return door_leaf_width;
	}

	public void setDoor_leaf_width(String door_leaf_width) {
		this.door_leaf_width = door_leaf_width;
	}

	public String getDoor_leaf_height() {
		return door_leaf_height;
	}

	public void setDoor_leaf_height(String door_leaf_height) {
		this.door_leaf_height = door_leaf_height;
	}

	public String getDoor_leaf_thickness() {
		return door_leaf_thickness;
	}

	public void setDoor_leaf_thickness(String door_leaf_thickness) {
		this.door_leaf_thickness = door_leaf_thickness;
	}

	public String getBattenHeight() {
		return battenHeight;
	}

	public void setBattenHeight(String battenHeight) {
		this.battenHeight = battenHeight;
	}

	public String getHangingPlateWidth() {
		return hangingPlateWidth;
	}

	public void setHangingPlateWidth(String hangingPlateWidth) {
		this.hangingPlateWidth = hangingPlateWidth;
	}

	public String getHangingPlateWidthCenter() {
		return hangingPlateWidthCenter;
	}

	public void setHangingPlateWidthCenter(String hangingPlateWidthCenter) {
		this.hangingPlateWidthCenter = hangingPlateWidthCenter;
	}

	public String getHangingPlateWidthBottom() {
		return hangingPlateWidthBottom;
	}

	public void setHangingPlateWidthBottom(String hangingPlateWidthBottom) {
		this.hangingPlateWidthBottom = hangingPlateWidthBottom;
	}

	public String getHangingPlateHeight() {
		return hangingPlateHeight;
	}

	public void setHangingPlateHeight(String hangingPlateHeight) {
		this.hangingPlateHeight = hangingPlateHeight;
	}

	public String getHangingPlateHeightCenter() {
		return hangingPlateHeightCenter;
	}

	public void setHangingPlateHeightCenter(String hangingPlateHeightCenter) {
		this.hangingPlateHeightCenter = hangingPlateHeightCenter;
	}

	public String getHangingPlateHeightBottom() {
		return hangingPlateHeightBottom;
	}

	public void setHangingPlateHeightBottom(String hangingPlateHeightBottom) {
		this.hangingPlateHeightBottom = hangingPlateHeightBottom;
	}

	public String getHingepageShaftDiameter() {
		return hingepageShaftDiameter;
	}

	public void setHingepageShaftDiameter(String hingepageShaftDiameter) {
		this.hingepageShaftDiameter = hingepageShaftDiameter;
	}

	public String getHingepageHoleDiameter() {
		return hingepageHoleDiameter;
	}

	public void setHingepageHoleDiameter(String hingepageHoleDiameter) {
		this.hingepageHoleDiameter = hingepageHoleDiameter;
	}

	public String getAtresiaShaftDiameter() {
		return atresiaShaftDiameter;
	}

	public void setAtresiaShaftDiameter(String atresiaShaftDiameter) {
		this.atresiaShaftDiameter = atresiaShaftDiameter;
	}

	public String getAtresiaHoleDiameter() {
		return atresiaHoleDiameter;
	}

	public void setAtresiaHoleDiameter(String atresiaHoleDiameter) {
		this.atresiaHoleDiameter = atresiaHoleDiameter;
	}

	public String getDoorframeSteelThick() {
		return doorframeSteelThick;
	}

	public void setDoorframeSteelThick(String doorframeSteelThick) {
		this.doorframeSteelThick = doorframeSteelThick;
	}

	public String getSteelPlatePositive() {
		return steelPlatePositive;
	}

	public void setSteelPlatePositive(String steelPlatePositive) {
		this.steelPlatePositive = steelPlatePositive;
	}

	public String getSteelPlateOpposite() {
		return steelPlateOpposite;
	}

	public void setSteelPlateOpposite(String steelPlateOpposite) {
		this.steelPlateOpposite = steelPlateOpposite;
	}

	public String getLegHeightAcross() {
		return legHeightAcross;
	}

	public void setLegHeightAcross(String legHeightAcross) {
		this.legHeightAcross = legHeightAcross;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getSteelbarProtectThick() {
		return steelbarProtectThick;
	}

	public void setSteelbarProtectThick(String steelbarProtectThick) {
		this.steelbarProtectThick = steelbarProtectThick;
	}

	public String getSteelbarSpacing() {
		return steelbarSpacing;
	}

	public void setSteelbarSpacing(String steelbarSpacing) {
		this.steelbarSpacing = steelbarSpacing;
	}

	public String getBarDiameterDesignValue() {
		return barDiameterDesignValue;
	}

	public void setBarDiameterDesignValue(String barDiameterDesignValue) {
		this.barDiameterDesignValue = barDiameterDesignValue;
	}

	public String getDoorLeafBaseVentWidth() {
		return doorLeafBaseVentWidth;
	}

	public void setDoorLeafBaseVentWidth(String doorLeafBaseVentWidth) {
		this.doorLeafBaseVentWidth = doorLeafBaseVentWidth;
	}

	public String getDoorLeafBaseVentHeight() {
		return doorLeafBaseVentHeight;
	}

	public void setDoorLeafBaseVentHeight(String doorLeafBaseVentHeight) {
		this.doorLeafBaseVentHeight = doorLeafBaseVentHeight;
	}

	public String getHangingPlateThicknessTop() {
		return hangingPlateThicknessTop;
	}

	public void setHangingPlateThicknessTop(String hangingPlateThicknessTop) {
		this.hangingPlateThicknessTop = hangingPlateThicknessTop;
	}

	public String getHangingPlateThicknessBottom() {
		return hangingPlateThicknessBottom;
	}

	public void setHangingPlateThicknessBottom(String hangingPlateThicknessBottom) {
		this.hangingPlateThicknessBottom = hangingPlateThicknessBottom;
	}

	public String getSteelPlantThickness() {
		return steelPlantThickness;
	}

	public void setSteelPlantThickness(String steelPlantThickness) {
		this.steelPlantThickness = steelPlantThickness;
	}

	public String getEquivalent_pipe_diameter() {
		return equivalent_pipe_diameter;
	}

	public void setEquivalent_pipe_diameter(String equivalent_pipe_diameter) {
		this.equivalent_pipe_diameter = equivalent_pipe_diameter;
	}

	public String getSteel_angle_width() {
		return this.steel_angle_width;
	}

	public void setSteel_angle_width(String steel_angle_width) {
		this.steel_angle_width = steel_angle_width;
	}

	public String getDemo2Ddate() {
		return this.demo2Ddate;
	}

	public void setDemo2Ddate(String demo2Ddate) {
		this.demo2Ddate = demo2Ddate;
	}

	public String getSiteNumber() {
		return this.siteNumber;
	}

	public void setSiteNumber(String siteNumber) {
		this.siteNumber = siteNumber;
	}
}
