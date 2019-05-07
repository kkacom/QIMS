package com.zhilian.rf_qims.util;

import com.colin.utils.StrKit;
import com.zhilian.rf_qims.entity.CaTestJson;
import com.zhilian.rf_qims.entity.Company;
import com.zhilian.rf_qims.entity.EntEquipmentJson;
import com.zhilian.rf_qims.entity.PersonnelJson;
import com.zhilian.rf_qims.entity.Project;
import com.zhilian.rf_qims.entity.Sample;
import com.zhilian.rf_qims.entity.SampleCheck;
import com.zhilian.rf_qims.entity.SampleCheckAlterLog;
import com.zhilian.rf_qims.entity.WorkAbilityJson;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/16.
 */

public class UploadParams {
    public static Map<String, String> getProjectParams(Project project, Company company) {
        Map<String, String> params = new HashMap<>();
        if (project.getStatus() == 0||project.getStatus()==2) {
            params.put("id", project.getId() + "");
        } else if (project.getStatus() == 1) {
            params.put("id", "0");
        }
        params.put("itemCode", StrKit.notBlank(project.getItemCode()) ? project.getItemCode() : "");
        params.put("projectNumber", StrKit.notBlank(project.getProjectCode()) ? project.getProjectCode() : "");
        params.put("entrustUnit", StrKit.notBlank(company.getName()) ? company.getName() : "");
        params.put("entrustUnitId", StrKit.notBlank(project.getEntrustId() + "") ? project.getEntrustId() + "" : "");
        params.put("itemName", StrKit.notBlank(project.getItemName()) ? project.getItemName() : "");
        params.put("projectAddress", StrKit.notBlank(project.getProjectAddr()) ? project.getProjectAddr() : "");
        params.put("areaId", StrKit.notBlank(project.getAreaId() + "") ? project.getAreaId() + "" : "");
        params.put("sampleSource", StrKit.notBlank(project.getSampleSource()) ? project.getSampleSource() : "1");
        params.put("entrustForm", StrKit.notBlank(project.getCommissionShape()) ? project.getCommissionShape() : "");
        params.put("entruster", StrKit.notBlank(project.getSender()) ? project.getSender() : "");
        params.put("entrusterPhone", StrKit.notBlank(project.getSenderPhone()) ? project.getSenderPhone() : "");
        params.put("entrustType", StrKit.notBlank(project.getCommissionCategory()) ? project.getCommissionCategory() : "");
        params.put("witnessUnit", StrKit.notBlank(project.getWitnessUnit()) ? project.getWitnessUnit() : "");
        params.put("witnessUnitPhone", StrKit.notBlank(project.getWitnessUnitPhone()) ? project.getWitnessUnitPhone() : "");
        params.put("witness", StrKit.notBlank(project.getWitness()) ? project.getWitness() : "");
        params.put("witnessPhone", StrKit.notBlank(project.getWitnessPhone()) ? project.getWitnessPhone() : "");
        params.put("supervisionUnit", StrKit.notBlank(project.getSupervisionUnit()) ? project.getSupervisionUnit() : "");
        params.put("supervisor", StrKit.notBlank(project.getSupervisor()) ? project.getSupervisor() : "");
        params.put("secrecy", StrKit.notBlank(project.getSecret()) ? project.getSecret() : "");
        params.put("remark", StrKit.notBlank(project.getDesc()) ? project.getDesc() : "");
        return params;
    }

    public static Map<String, String> getSampleParams(Sample sample,Company company) {
        Map<String, String> params = new HashMap<>();
        if (sample.getStatus() == 0||sample.getStatus()==2) {
            params.put("id", sample.getId() + "");
        } else if (sample.getStatus() == 1) {
            params.put("id", "0");
        }
        params.put("rid", sample.getPid() + "");
        params.put("number", StrKit.notBlank(sample.getSampleNo()) ?sample.getSampleNo():"");
        params.put("type", StrKit.notBlank(sample.getSampleType())? sample.getSampleType():"");
        params.put("smode", StrKit.notBlank(sample.getSampleModel())?sample.getSampleModel():"");
        params.put("eid",company.getId()+"");
//        params.put("unit", StrKit.notBlank(company.getName()) ?company.getName():"");
        params.put("projectName", StrKit.notBlank(sample.getAddr()) ?sample.getAddr():"");
        params.put("productionDate", StrKit.notBlank(sample.getMadeDate()) ?sample.getMadeDate():"");
        params.put("categoryCode", StrKit.notBlank(sample.getMadeNo()) ?sample.getMadeNo():"");
        params.put("siteNumber", StrKit.notBlank(sample.getSiteNumber()) ?sample.getSiteNumber():"");
        params.put("testDate", StrKit.notBlank(sample.getCheckDate()) ?sample.getCheckDate():"");
        params.put("tester",StrKit.notBlank(sample.getTester())?sample.getTester():"");
        params.put("tester1",StrKit.notBlank(sample.getTester1())?sample.getTester1():"");
        params.put("demo2Ddate",sample.getDemo2Ddate());
        params.put("temperature",StrKit.notBlank(sample.getTemperature())?sample.getTemperature():"");
        params.put("humidity",StrKit.notBlank(sample.getHumidity())?sample.getHumidity():"");

        params.put("door_frame_height", defaultString(sample.getDoor_frame_height()));
        params.put("door_frame_width", defaultString(sample.getDoor_frame_width()));

        params.put("door_leaf_width", defaultString(sample.getDoor_leaf_width()));
        params.put("door_leaf_height", defaultString(sample.getDoor_leaf_height()));
        params.put("door_leaf_thickness",defaultString(sample.getDoor_leaf_thickness()));
        params.put("battenHeight", defaultString(sample.getBattenHeight()));

        params.put("hangingPlateWidth", defaultString(sample.getHangingPlateWidth()));
        params.put("hangingPlateWidthCenter", defaultString(sample.getHangingPlateWidthCenter()));
        params.put("hangingPlateWidthBottom",defaultString(sample.getHangingPlateWidthBottom()));
        params.put("hangingPlateHeight",  defaultString(sample.getHangingPlateHeight()));
        params.put("hangingPlateHeightCenter",  defaultString(sample.getHangingPlateHeightCenter()));
        params.put("hangingPlateHeightBottom", defaultString(sample.getHangingPlateHeightBottom()));

        params.put("hingepageShaftDiameter",  defaultString(sample.getHingepageShaftDiameter()));
        params.put("hingepageHoleDiameter",  defaultString(sample.getHingepageHoleDiameter()));
        params.put("atresiaShaftDiameter",  defaultString(sample.getAtresiaShaftDiameter()));
        params.put("atresiaHoleDiameter",  defaultString(sample.getAtresiaHoleDiameter()));

        params.put("doorframeSteelThick", defaultString(sample.getDoorframeSteelThick()));
        params.put("steel_angle_width", defaultString(sample.getSteel_angle_width()));
        params.put("steelPlatePositive",  defaultString(sample.getSteelPlatePositive()));
        params.put("steelPlateOpposite",  defaultString(sample.getSteelPlateOpposite()));

        params.put("legHeightAcross",  defaultString(sample.getLegHeightAcross()));

        params.put("strength",  defaultString(sample.getStrength()));

        params.put("steelbarProtectThick", defaultString(sample.getSteelbarProtectThick()));
        params.put("steelbarSpacing",  defaultString(sample.getSteelbarSpacing()));
        params.put("barDiameterDesignValue", defaultString(sample.getBarDiameterDesignValue()));

        params.put("doorLeafBaseVentWidth",  defaultString(sample.getDoorLeafBaseVentWidth()));
        params.put("doorLeafBaseVentHeight",  defaultString(sample.getDoorLeafBaseVentHeight()));
        params.put("hangingPlateThicknessTop",  defaultString(sample.getHangingPlateThicknessTop()));
        params.put("hangingPlateThicknessBottom",  defaultString(sample.getHangingPlateThicknessBottom()));
        params.put("steelPlantThickness",  defaultString(sample.getSteelPlantThickness()));

        params.put("equivalent_pipe_diameter",  defaultString(sample.getEquivalent_pipe_diameter()));

        return params;
    }

    public static Map<String, String> getWorkAbilityParams(WorkAbilityJson workAbilityJson) {
        Map<String, String> params = new HashMap<>();
        params.put("id", "0");
        params.put("eid",defaultString(String.valueOf(workAbilityJson.getEID())));
        params.put("testdate",defaultString(workAbilityJson.getTESTDATE()));
        params.put("confirmer",defaultString(workAbilityJson.getCONFIRMER()));
        params.put("vc",defaultString(workAbilityJson.getVC()));
        params.put("ccad",defaultString(workAbilityJson.getCCAD()));
        params.put("tester",defaultString(workAbilityJson.getTESTER()));
        params.put("assistant",defaultString(workAbilityJson.getASSISTANT()));
        params.put("administrator",defaultString(workAbilityJson.getADMINISTRATOR()));
        params.put("enterprise_name",defaultString(workAbilityJson.getENTERPRISE_NAME()));
        params.put("wcode",defaultString(workAbilityJson.getWCODE()));
        params.put("wtype",defaultString(workAbilityJson.getWTYPE()));
        return params;
    }
    //从业能力
    public static Map<String, String> getSingleWorkAbilityParams(WorkAbilityJson workAbilityJson) {
        Map<String, String> params = new HashMap<>();
        if (workAbilityJson.getSTATUS() == 1) {
            params.put("id", workAbilityJson.getWID() + "");
        } else if (workAbilityJson.getSTATUS() == 0) {
            params.put("id", "0");
        }
        //基础
        params.put("eid",defaultString(String.valueOf(workAbilityJson.getEID())));
        params.put("testdate",defaultString(workAbilityJson.getTESTDATE()));
        params.put("confirmer",defaultString(workAbilityJson.getCONFIRMER()));
        params.put("vc",defaultString(workAbilityJson.getVC()));
        params.put("ccad",defaultString(workAbilityJson.getCCAD()));
        params.put("tester",defaultString(workAbilityJson.getTESTER()));
        params.put("assistant",defaultString(workAbilityJson.getASSISTANT()));
        params.put("administrator",defaultString(workAbilityJson.getADMINISTRATOR()));
        params.put("enterprise_name",defaultString(workAbilityJson.getENTERPRISE_NAME()));
        params.put("wcode",defaultString(workAbilityJson.getWCODE()));
        params.put("wtype",defaultString(workAbilityJson.getWTYPE()));
        //其他打分
        params.put("total",defaultString(workAbilityJson.getTOTAL()));
        params.put("item1",defaultString(workAbilityJson.getITEM()));
        params.put("item1_1",defaultString(workAbilityJson.getITEM1_1()));
        params.put("item1_2",defaultString(workAbilityJson.getITEM1_2()));
        params.put("item1_3",defaultString(workAbilityJson.getITEM1_3()));
        params.put("item1_4",defaultString(workAbilityJson.getITEM1_4()));
        params.put("item2",defaultString(workAbilityJson.getITEM2()));
        params.put("item2_1",defaultString(workAbilityJson.getITEM2_1()));
        params.put("item2_2",defaultString(workAbilityJson.getITEM2_2()));
        params.put("item2_3",defaultString(workAbilityJson.getITEM2_3()));
        params.put("item2_4",defaultString(workAbilityJson.getITEM2_4()));
        params.put("item2_5",defaultString(workAbilityJson.getITEM2_5()));
        params.put("item2_6",defaultString(workAbilityJson.getITEM2_6()));
        params.put("item2_7",defaultString(workAbilityJson.getITEM2_7()));
        params.put("item2_8",defaultString(workAbilityJson.getITEM2_8()));
        params.put("item3",defaultString(workAbilityJson.getITEM3()));
        params.put("item3_1",defaultString(workAbilityJson.getITEM3_1()));
        params.put("item3_2",defaultString(workAbilityJson.getITEM3_2()));
        params.put("item3_3",defaultString(workAbilityJson.getITEM3_3()));
        params.put("item4",defaultString(workAbilityJson.getITEM4()));
        return params;
    }
    //从业打分表
    public static Map<String, String> getCaTestParams(CaTestJson caTestJson,String arg1) {
        Map<String, String> params = new HashMap<>();
        if (caTestJson.getUPLOADSTATUS() == 1) {
            params.put("id", caTestJson.getCTID() + "");
        } else if (caTestJson.getUPLOADSTATUS() == 0) {
            params.put("id", "0");
        }
        params.put("cid",defaultString(arg1));
        params.put("item",defaultString(caTestJson.getITEM()));
        params.put("score",defaultString(caTestJson.getSCORE()));
        params.put("choose",defaultString(caTestJson.getCHOOSE()));
        params.put("remark",defaultString(caTestJson.getREMARK()));
        params.put("status",defaultString(caTestJson.getSTATUS()));
        params.put("field1",defaultString(caTestJson.getFIELD1()));
        params.put("field2",defaultString(caTestJson.getFIELD2()));
        params.put("field3",defaultString(caTestJson.getFIELD3()));
        params.put("wremark",defaultString(caTestJson.getWREMARK()));
        return params;
    }
    //从业人员表
    public static Map<String, String> getPersonnelParams(PersonnelJson personnelJson) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(personnelJson.getID()));
        params.put("eid",defaultString(String.valueOf(personnelJson.getEID())));
        params.put("cid",defaultString(String.valueOf(personnelJson.getCID())));
        params.put("post_type",defaultString(personnelJson.getPOST_TYPE()));
        params.put("pername",defaultString(personnelJson.getPERNAME()));
        params.put("idnumber",defaultString(personnelJson.getIDNUMBER()));
        params.put("choose",defaultString(personnelJson.getCHOOSE()));
        params.put("score",defaultString(personnelJson.getSCORE()));
        params.put("status",defaultString(personnelJson.getSTATUS()));
        params.put("remark",defaultString(personnelJson.getREMARK()));
        params.put("phone",defaultString(personnelJson.getPHONE()));
        params.put("hiredate",defaultString(personnelJson.getHIREDATE()));
        params.put("professional",defaultString(personnelJson.getPROFESSIONAL()));
        params.put("work_type",defaultString(String.valueOf(personnelJson.getWORK_TYPE())));
        return params;
    }

    //从业设备表
    public static Map<String, String> getEquipmentlParams(EntEquipmentJson equipmentJson) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(equipmentJson.getID()));
        params.put("eid",defaultString(String.valueOf(equipmentJson.getEID())));
        params.put("cid",defaultString(String.valueOf(equipmentJson.getCID())));
        params.put("etype",defaultString(equipmentJson.getETYPE()));
        params.put("ename",defaultString(equipmentJson.getENAME()));
        params.put("ecode",defaultString(equipmentJson.getECODE()));
        params.put("emodel",defaultString(equipmentJson.getEMODEL()));
        params.put("score",defaultString(equipmentJson.getSCORE()));
        params.put("choose",defaultString(equipmentJson.getCHOOSE()));
        params.put("remark",defaultString(equipmentJson.getREMARK()));
        params.put("field1",defaultString(equipmentJson.getFIELD()));
        return params;
    }

    //样品检测修改日志
    public static Map<String, String> getSampleCheckAlterLogParams(SampleCheckAlterLog sampleCheckAlterLog) {
        Map<String, String> params = new HashMap<>();
        params.put("id", "0");
        params.put("sampleid",defaultString(String.valueOf(sampleCheckAlterLog.getSampleid())));
        params.put("applicant",defaultString(String.valueOf(sampleCheckAlterLog.getApplicant())));
        params.put("check_time",defaultString(sampleCheckAlterLog.getCheck_time()));
        params.put("check_num",defaultString(sampleCheckAlterLog.getCheck_num()));
        params.put("change_num",defaultString(sampleCheckAlterLog.getChange_num()));
        params.put("change_reason",defaultString(sampleCheckAlterLog.getChange_reason()));
        params.put("field_name",defaultString(sampleCheckAlterLog.getField_name()));
        params.put("serial_number",defaultString(sampleCheckAlterLog.getSerial_number()));
        return params;
    }

    public static Map<String, String> getSampleCheckParams(SampleCheck sampleCheck) {
        Map<String, String> params = toMap(sampleCheck);
        return params;
    }

    public static Map<String, String> toMap(Object javaBean) {
        Map<String, String> result = new HashMap<String, String>();
        Method[] methods = javaBean.getClass().getDeclaredMethods();

        for (Method method : methods) {
            try {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);

                    Object value = method.invoke(javaBean, (Object[]) null);
                    result.put(field, null == value ? "" : value.toString());
                }
            } catch (Exception e) {
            }
        }
        return result;
    }

    public static String defaultString(String value){
      return StrKit.notBlank(value)?value:"";
    }
}