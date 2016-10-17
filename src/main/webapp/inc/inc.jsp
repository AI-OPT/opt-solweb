<%@page import="com.ai.opt.sso.client.filter.SSOClientUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="_base" value="${pageContext.request.contextPath}"/>
<c:set var="_slpres" value="${_base}/resources/local"/>
<c:set var="spmRes" value="${_base}/resources/spm_modules"/>
<%-- <c:set var="accountBalanceLink" value="${_base}/account/balance/index"/>
<c:set var="accountRechargeOneLink" value="${_base}/account/recharge/one"/> --%>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "No-cache");

    String slp_uac_host=SSOClientUtil.getCasServerUrlPrefixRuntime(request);
    request.setAttribute("slp_uac_host", slp_uac_host);
    String ssoLoginUrl=SSOClientUtil.getCasServerLoginUrlRuntime(request);
    request.setAttribute("ssoLoginUrl", ssoLoginUrl);
%>
<c:set var="uedroot" value="${pageContext.request.contextPath}/resources/template/default"/>
<script>
    var _base = "${_base}";
    var _spm_res = "${spmRes}";
    var slp_uac_host="${slp_uac_host}";
    var ssoLoginUrl="${ssoLoginUrl}";
    var uedroot="${uedroot}";
</script>

<!-- <link rel="stylesheet" type="text/css" href="${_base}/resources/slpmall/styles/bootstrap.css"> -->
<link rel="stylesheet" type="text/css" href="${spmRes}/optDialog/css/ui-dialog.css"/>

<link rel="stylesheet" type="text/css" href="${uedroot}/css/bootstrap/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${uedroot}/css/bootstrap/font-awesome.css"/>
<link rel="stylesheet" type="text/css" href="${uedroot}/css/modular/frame.css"/>
<link rel="stylesheet" type="text/css" href="${uedroot}/css/modular/global.css"/>
<link rel="stylesheet" type="text/css" href="${uedroot}/css/modular/modular.css"/>
<link rel="stylesheet" type="text/css" href="${uedroot}/css/modular/validate.css"/>
<link rel="stylesheet" type="text/css" href="${uedroot}/css/ztree/zTreeStyle.css"/>

<script src="${_base}/resources/spm_modules/jquery/1.9.1/jquery.min.js"></script>
<script src="${_base}/resources/spm_modules/bootstrap/dist/js/bootstrap.js"></script>
<script src="${_base}/resources/spm_modules/seajs/2.3.0/dist/sea.js"></script>
<script src="${_base}/resources/spm_modules/seajs/seajs-css.js"></script>
<script src="${_base}/resources/spm_modules/app/core/config.js"></script>
<!-- 公共事件 -->
<script src="${_base}/resources/spm_modules/app/util/common.js" type="text/javascript"></script>
<script src="${uedroot}/scripts/modular/theme.js"></script>
<script src="${uedroot}/scripts/modular/p-skin-changer.js"></script>

<script src="${uedroot}/scripts/plugin/jquery.nanoscroller.min.js"></script>
<script src="${uedroot}/scripts/modular/skin.js"></script>
<script src="${uedroot}/scripts/modular/theme.js"></script>

<%@ include file="/jsp/common/common.jsp"%>
