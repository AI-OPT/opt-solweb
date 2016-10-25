<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>服务管理</title>
	<%@ include file="/inc/inc.jsp" %>
</head>

<body
	class="theme-whbl  pace-done fixed-header fixed-leftmenu fixed-footer">
	<div id="theme-wrapper">

		<%@ include file="/jsp/common/head.jsp"%>


		<div id="page-wrapper" class="container">
			<div class="row">
				<%@ include file="/jsp/common/leftmenu.jsp"%>
<div id="content-wrapper"><!--右侧灰色背景-->
	<div class="row"><!--外围框架-->
		<div class="col-lg-12"><!--删格化-->
			<div class="row"><!--内侧框架-->
				<div class="col-lg-12"><!--删格化-->
					<div class="main-box clearfix"><!--白色背景-->
						<form id="serviceForm" action="${_base}/service/save" method="post">
						<div id="addViewDiv" class="main-box-body clearfix">
                            <!-- 查询条件 -->
                            <div class="form-label bd-bottom ui-form" data-widget="validator">
                            	<input id="srvApiId" name="srvApiId" type="hidden" value="${srvApiId}">
                            	<input id="srvCenter" name="srvCenter" type="hidden" value="${srvCenter}">
                            	<input id="srvCategoryId" name="srvCategoryId" type="hidden" value="${srvCategoryId}">
								<ul>
					                <li class="col-md-12 ui-form-item">
					                    <p class="word"><span>*</span>所属中心</p>
					                    <p><input id="srvCenterName" name="srvCenterName" type="text" class="int-text int-medium"
					                              required data-msg-required="所属中心不能为空"  value="${srvCenterName}" readonly></p>
					                </li>
					                <li class="col-md-12 ui-form-item">
										<p class="word"><span>*</span>服务分类</p>
										<p>
											<input id="srvCategoryName" name="srvCategoryName" type="text" class="int-text int-medium" required data-msg-required="服务分类不能为空" value="${srvCategoryName}" readonly>
										</p>
									</li>
					             </ul>
					             <ul>
					             	<li class="col-md-12 ui-form-item">
					             		<p class="word"><span>*</span>服务类型</p>
					             		<p>
					             			<select id="srvClass" name="srvClass" class="select select-medium" required data-msg-required="服务类型不能为空">
					             				<option value="" >-- 请选择 --</option>
							                   	<option value="1" <c:if test="${srvClass == '1'}">selected="selected"</c:if> >查询</option>
							                   	<option value="2" <c:if test="${srvClass == '2'}">selected="selected"</c:if> >创建</option>
							                   	<option value="3" <c:if test="${srvClass == '3'}">selected="selected"</c:if> >修改</option>
							                   	<option value="4" <c:if test="${srvClass == '4'}">selected="selected"</c:if> >删除</option>
					                    	</select>
					             		</p>
					            	</li>
					             </ul>
					             <ul>
					             	<li class="col-md-12 ui-form-item">
					             		<p class="word"><span>*</span>服务名称</p>
					             		<p><input  name="srApiName" type="text" class="int-text int-medium"  maxlength="120" value="${srApiName}" required data-msg-required="服务名称不能为空"></p>
					            	</li>
					             </ul>
					             <ul>
					             	<li class="col-md-12 ui-form-item">
					             		<p class="word">服务描述</p>
					             		<p><textarea name="srvRemark" class="int-text textarea-xlarge" maxlength="500">${srvRemark}</textarea></p>
					            	</li>
					             </ul>
                            </div> 
                            <div  class="row pt-30">
                            	<p class="center pr-30 mt-30">
                            		<input id="submitSaveBtn" type="button" class="biu-btn  btn-primary  btn-small  ml-5"
                                           value="提  交">
                                    <input id="cancelBtn" type="button" class="biu-btn  btn-primary  btn-small  ml-5"
                                           value="取  消">
                            	</p>
                            </div>
                            
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<%@ include file="/jsp/common/foot.jsp"%>
</div>
</div>
</div>
</body>
<script type="text/javascript">
    var pager;
    (function () {
        seajs.use('app/jsp/serviceDefine/save', function (serviceSavePager) {
            pager = new serviceSavePager({element: document.body});
            pager.render();
        });
    })(); 
</script>
</html>