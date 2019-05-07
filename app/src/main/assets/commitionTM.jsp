<!DOCTYPE html>

<html>


<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <meta charset="utf-8">
    <title></title>
    <link href="css/rf.css" rel="stylesheet" type="text/css" />
</head>

<body>
	
    <table class="wordInfo">
        <tr>
            <td width="8%" class="title" title="填写指南：${titleMap.get('itemCode')}">委托单编号</td>
            <td width="12%" class="readonly">${commission.itemCode}</td>
            <td width="8%" class="title" title="填写指南：${titleMap.get('acceptDate')}">收样日期</td>
            <td width="12%">
                <input type="text" name="t_Commission.acceptDate" value="${commission.acceptDate}" maxlength="10" data-toggle="datepicker" data-rule="required" class="required" />
            </td>
            <td width="8%" class="title" title="填写指南：${titleMap.get('projectCode')}">项目代码</td>
            <td width="12%">
                <input type="text" name="t_Commission.projectNumber" value="${commission.projectNumber}" maxlength="50" />
            </td>
            <td width="8%" class="title" title="填写指南：${titleMap.get('entrustForm')}">委托形式</td>
            <td width="12%">
                <select name="t_Commission.entrustForm" data-toggle="selectpicker" data-rule="required" class="required" style="width: 100%">
                    <c:forEach items="${entrustForms }" var="o">
                        <option value="${o.name }" <c:if test="${o.name==commission.entrustForm}">selected</c:if>>${o.name }</option>
                    </c:forEach>
                </select>
            </td>
            <td width="8%" class="title" title="填写指南：${titleMap.get('secrecy')}">保密要求</td>
            <td width="12%">
                <input type="radio" name="t_Commission.secrecy" value="1" <c:if test="${1==commission.secrecy}">checked</c:if> data-toggle="icheck" data-label="有" data-rule="checked" class="required" />
                <input type="radio" name="t_Commission.secrecy" value="0" <c:if test="${empty commission.secrecy || 0==commission.secrecy}">checked</c:if> data-toggle="icheck" data-label="无" data-rule="checked" class="required" />
            </td>
        </tr>
        <tr>
            <td class="title" title="填写指南：${titleMap.get('entrustUnit')}">委托单位</td>
            <td colspan="3">
                <input type="hidden" name="t_Commission.entrustUnitId" id="enterpriseId" value="${commission.entrustUnitId}" />
                <input type="text" name="t_Commission.entrustUnit" id="enterpriseName" value="${commission.entrustUnit}" maxlength="100" data-rule="required" class="required" suggestFields="entrustUnit" suggestUrl="Main/Search/searchEnterprises" lookupGroup="t_Commission" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('itemName')}">项目名称</td>
            <td colspan="3">
                <input type="text" name="t_Commission.itemName" value="${commission.itemName}" maxlength="20" data-rule="required" class="required" catchFields="enterpriseId" suggestFields="name" suggestUrl="Main/Search/searchEnterpriseProject" lookupGroup="t_Commission" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('entrustUnitPhone')}">联系电话</td>
            <td>
                <input type="text" name="t_Commission.entrustUnitPhone" value="${commission.entrustUnitPhone}" maxlength="20" />
            </td>
        </tr>
        <tr>
            <td class="title" title="填写指南：${titleMap.get('projectAddress')}">样品地址</td>
            <td colspan="3">
                <input type="text" name="t_Commission.projectAddress" value="${commission.projectAddress}" maxlength="200" data-rule="required" class="required" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('sampleSource')}">样品来源</td>
            <td class="required">
                <select name="t_Commission.sampleSource" data-toggle="selectpicker" data-rule="required">
                    <option value="">-请选择-</option>
                    <option value="1" <c:if test="${commission.sampleSource==1}">selected</c:if> >厂区</option>
                    <option value="2" <c:if test="${commission.sampleSource==2}">selected</c:if> >现场</option>
                </select>
            </td>
            <td class="title" title="填写指南：${titleMap.get('areaId')}">样品区域</td>
            <td class="required">
                <select name="t_Commission.areaId" data-toggle="selectpicker" data-rule="required">
                    <option value="">-请选择-</option>
                    <c:forEach items="${areaList}" var="area">
                        <option value="${area.id }" <c:if test="${commission.areaId==area.id}">selected</c:if> >${area.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td class="title" title="填写指南：${titleMap.get('autoReportForm')}">出报告方式</td>
            <td class="required">
                <input type="radio" name="t_Commission.autoReportForm" value="1" <c:if test="${'1'==commission.autoReportForm}">checked</c:if> data-toggle="icheck" data-label="一起出" data-rule="checked" class="required" />
                <input type="radio" name="t_Commission.autoReportForm" value="2" <c:if test="${empty commission.autoReportForm || '2'==commission.autoReportForm}">checked</c:if> data-toggle="icheck" data-label="分开出" data-rule="checked" class="required" />
            </td>
        </tr>
        <tr>
            <td class="title" title="填写指南：${titleMap.get('witnessUnit')}">见证单位</td>
            <td colspan="3">
                <input type="text" name="t_Commission.witnessUnit" value="${commission.witnessUnit}" maxlength="100" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('witnessUnitPhone')}">联系电话</td>
            <td>
                <input type="text" name="t_Commission.witnessUnitPhone" value="${commission.witnessUnitPhone}" maxlength="20" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('witness')}">见证人姓名</td>
            <td>
                <input type="text" name="t_Commission.witness" value="${commission.witness}" maxlength="20" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('witnessPhone')}">见证人电话</td>
            <td>
                <input type="text" name="t_Commission.witnessPhone" value="${commission.witnessPhone}" maxlength="20" />
            </td>
        </tr>
        <tr>
            <td class="title" title="填写指南：${titleMap.get('entrustType')}">委托类别</td>
            <td>
                <select name="t_Commission.entrustType" data-toggle="selectpicker" data-width="130" data-rule="required" class="required">
                    <c:forEach items="${entrustTypes }" var="o">
                        <option value="${o.name }" <c:if test="${o.name==commission.entrustType}">selected</c:if>>${o.name }</option>
                    </c:forEach>
                </select>
            </td>
            <td class="title" title="填写指南：${titleMap.get('supervisionUnit')}">监督单位</td>
            <td colspan="3">
                <input type="text" name="t_Commission.supervisionUnit" value="${commission.supervisionUnit}" maxlength="100" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('supervisor')}">监督员姓名</td>
            <td>
                <input type="text" name="t_Commission.supervisor" value="${commission.supervisor}" maxlength="20" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('monitoringNumber')}">监督编号</td>
            <td>
                <input type="text" name="t_Commission.monitoringNumber" value="${commission.monitoringNumber}" maxlength="20" />
            </td>
        </tr>
        <tr>
            <td class="title" title="填写指南：${titleMap.get('sender')}">委托人姓名</td>
            <td>
                <input type="text" name="t_Commission.sender" value="${commission.sender}" maxlength="20" data-rule="required" class="required" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('senderPhone')}">委托人电话</td>
            <td>
                <input type="text" name="t_Commission.senderPhone" value="${commission.senderPhone}" maxlength="20" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('accepterId')}">收 样 人</td>
            <td>
                <input type="hidden" name="accepter.id" value="${commission.accepterId}" />
                <input type="text" name="accepter.name" value="${commission.accepterName}" maxlength="20" suggestFields="name" suggestUrl="Main/Search/searchUsersByAuthority" lookupGroup="accepter" data-rule="required" class="required" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('sampleTreatment')}">样品处理</td>
            <td colspan="3">
                <input type="radio" name="t_Commission.sampleTreatment" value="0" <c:if test="${empty commission.sampleTreatment || 0==commission.sampleTreatment}">checked</c:if>
                data-toggle="icheck" data-label="收方自理" data-rule="checked" class="required" />
                <input type="radio" name="t_Commission.sampleTreatment" value="1" <c:if test="${1==commission.sampleTreatment}">checked</c:if>
                data-toggle="icheck" data-label="退样：" data-rule="checked" class="required" />
                <input type="text" name="t_Commission.returnExplain" value="${commission.returnExplain}" maxlength="100" style="width: 430px;" />
            </td>
        </tr>
        <tr>
            <td class="title" title="填写指南：${titleMap.get('unitPrice')}">单价</td>
            <td>
                <input type="text" name="t_Commission.unitPrice" value="${commission.unitPrice}" readonly="readonly" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('quantity')}">数量</td>
            <td>
                <input type="text" name="t_Commission.quantity" value="${commission.quantity}" readonly="readonly" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('money')}">金额</td>
            <td>
                <input type="text" name="t_Commission.money" value="${commission.money}" readonly="readonly" />
            </td>
            <td class="title" title="填写指南：${titleMap.get('paymentForm')}">付款方式</td>
            <td>
                <select name="t_Commission.paymentForm" data-toggle="selectpicker" data-width="130" data-rule="required" class="required">
                    <c:forEach items="${paymentForms }" var="o">
                        <option value="${o.name }" <c:if test="${o.name==commission.paymentForm}">selected</c:if>>${o.name }</option>
                    </c:forEach>
                </select>
            </td>
            <td class="title"></td>
            <td class="readonly"></td>
        </tr>
        <tr>
            <td class="title" title="填写指南：${titleMap.get('remark')}">备 注</td>
            <td colspan="9">
                <textarea name="t_Commission.remark" rows="2" maxlength="200" style="width: 100%">${commission.remark}</textarea>
            </td>
        </tr>
    </table>
</body>

</html>