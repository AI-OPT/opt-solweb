<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/jsp/common/common.jsp"%>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>REST服务在线测试-服务在线管理</title>
<link rel="stylesheet" href="${_base }/resources/jsoneditor/jsoneditor.min.css">
<script src="${_base }/resources/jsoneditor/jsoneditor.min.js"></script>
<script src="${_base }/resources/jsoneditor/asset/ace/ace.js"></script>
<script src="${_base }/resources/jsoneditor/asset/jsonlint/jsonlint.js"></script>
 <style type="text/css">
    code {
      background-color: #f5f5f5;
    }

    .JSONEDITOR{
      width: 100%;
      height: 300px;
    }
    .divcss5{text-indent:25px}
  </style>
</head>
<body
	class="theme-whbl  pace-done fixed-header fixed-leftmenu fixed-footer">
	<div id="theme-wrapper">

		<%@ include file="/jsp/common/head.jsp"%>


		<div id="page-wrapper" class="container">
			<div class="row">
				<%@ include file="/jsp/common/leftmenu.jsp"%>

				<div id="content-wrapper">
					<div class="row">
						<div class="col-lg-12">
							<div class="row">
								<div class="col-lg-12">
									<ol class="breadcrumb">
										<li><span>首页</span></li>
										<li><span><a href="../api/tosearch.html?owner=<c:out value="${apiCallSetting.owner}"/>&ownerType=<c:out value="${apiCallSetting.ownerType}"/>&keywords=<c:out value="${apiCallSetting.method}"/>&activemenu=m_api">服务搜索</a></span></li>
										<li class="active"><span>REST服务测试</span></li>
									</ol>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-12">
							<div class="main-box">
								<header class="main-box-header clearfix">
									<h2>填写测试信息</h2>
								</header>
								<div class="main-box-body clearfix">
									<div class="form-horizontal" role="form">
										<div class="form-group">
											<label class="col-lg-2 control-label">HTTP地址</label>
											<div class="col-lg-8">
												<input type="text" class="form-control" id="restURL" value="http://10.1.228.222:10771/customer/<c:out value="${apiRest.restRelativeURL}"/>">
												请确保HTTP Restful地址可以正常访问
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">REST请求方式</label>
											<div class="col-lg-8">
												<input type="text" class="form-control" id="restMethod" size="10" value="<c:out value="${apiRest.restMethod}"/>" readonly>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">映射的服务接口</label>
											<div class="col-lg-8">
												<input type="text"   class="form-control" id="interfaceName" value="<c:out value="${apiRest.interfaceName}"/>" readonly>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">映射的方法</label>
											<div class="col-lg-8">
												<input type="text" class="form-control" id="method" value="<c:out value="${apiRest.methodName}"/>" readonly>
											</div>
										</div>
										
										<c:forEach items="${apiRest.restParams}" var="reqParam" varStatus="status">
											<div class="form-group" name="DIV_REQ_PARAM_SETTING"  id="DIV_REQ_PARAM_${reqParam.paramName}">
												<label class="col-lg-2 control-label">入参${status.index}<br>(${reqParam.paramName})</label>
												<div class="col-lg-8">
												   由${reqParam.javaType}映射
													<input type="hidden" id="paramName" value="${reqParam.paramName}"/>
													<c:if test="${reqParam.editorType==1 }">
														<div id="REQ_JSONEDITOR"  class="JSONEDITOR" paramjson="<c:out value="${reqParam.paramValue}"/>"></div>
													</c:if>
													<c:if test="${reqParam.editorType==2 }">
														<input type="text" class="form-control" id="REQ_JSONTEXT" name="REQ_JSONTEXT" value="<c:out value="${reqParam.paramValue}"/>" />
													</c:if>
													
												</div>
											</div>
										</c:forEach>
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<button type="button" class="btn btn-success" id="BTN_TEST">执行测试</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					
					<div class="row" style="display:none" id="DIV_TEST_RESULT">
						<div class="col-lg-12">
							<div class="main-box">
								<header class="main-box-header clearfix">
									<h2>测试结果</h2>
								</header>
								<div class="main-box-body clearfix">
									<div class="form-horizontal" role="form"> 
										<div class="form-group" id="DIV_SUCCESS_RESULT" style="display:none">
											<label class="col-lg-2 control-label">测试成功信息</label>
											<div class="col-lg-8">
											   <div id="RESP_JSONEDITOR"  class="JSONEDITOR"></div>
											</div>
										</div> 
										<div class="form-group" id="DIV_FAILURE_RESULT" style="display:none">
											<label class="col-lg-2 control-label">测试失败信息</label>
											<div class="col-lg-8">
												<textarea class="form-control" id="RESP_JSONTEXT" rows="8" readonly></textarea>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>




					<!-- aaa -->



					<%@ include file="/jsp/common/foot.jsp"%>
				</div>
			</div>
		</div>
	</div>
	


	<script>
		$(document).ready(function() {
			$('#external-events div.external-event').each(function() {
				var eventObject = {
					title : $.trim($(this).text())
				};
				$(this).data('eventObject', eventObject);
				$(this).draggable({
					zIndex : 999,
					revert : true,
					revertDuration : 0
				});
			});

			var pagController = new $.PageController();

		});

		(function($) {

			$.PageController = function() {
				this.settings = $.extend(true, {}, $.PageController.defaults);
				this.bindEvents();
				this.initJSONEditors();
			}

			$.extend($.PageController, {
				defaults : {

				},
				prototype : {

					bindEvents : function() {
						var _this = this;
						$("#BTN_TEST").bind("click", function() {
							_this.restTest();
						});
						
						
					},   
					 
					
					initJSONEditors: function(){
						var options = {
						    mode: 'code',
						    modes: ['code', 'form', 'text', 'tree', 'view'], 
						    error: function (err) {
						      alert(err.toString());
						    }
						 };
						//输入参数编辑器
						var reqEditors = new Array();
						$("[name='DIV_REQ_PARAM_SETTING']").each(function(index,div){
							var obj = $(div).find("#REQ_JSONEDITOR");
							if(!obj){
								return;
							}
							
							var jsoneditorId = obj.attr("id");
							if(jsoneditorId==undefined){
								return;
							}
							var paramName = $(div).find("#paramName").val();
							//数据库中保存的json字符串
							var paramjson=obj.attr("paramjson");
							var container = document.getElementById(jsoneditorId);
							var editor = new JSONEditor(container, options, {});
							editor.set(paramjson?JSON.parse(paramjson):{});
							
							var data = {
									paramName: paramName,
									editor: editor
							}
							reqEditors.push(data);
						}); 
						this.reqEditors = reqEditors; 
						
						//输出参数编辑器
						var container = document.getElementById("RESP_JSONEDITOR");
						this.respEditor = new JSONEditor(container, options, {});
					},
					
					getReqJSONEditor: function(paramName){
						var editors = this.reqEditors;
						var array =$.grep(editors,function(d,index){
							return d.paramName==paramName;
						});
						if(!array || array.length==0){
							return ;
						}
						var editor = array[0].editor;
						return editor;
					},
					
					restTest: function(){
						var _this = this; 
						var restURL = $("#restURL").val();
						var restMethod = $("#restMethod").val();
						if($.trim(restURL)==""){
							messageController.alert("请输入测试的HTTP地址");
							return ;
						}
						if($.trim(restMethod)==""){
							messageController.alert("REST请求方式为空");
							return ;
						}
						
						var restParams = new Array();
						$("[name='DIV_REQ_PARAM_SETTING']").each(function(index,div){
							var paramName = $(div).find("#paramName").val();
							var jsonTEXT = $(div).find("#REQ_JSONTEXT");
							var jsonStr = "";
							if(jsonTEXT && jsonTEXT.length>0){
								jsonStr = jsonTEXT.val();
							}else{
								var editor = _this.getReqJSONEditor(paramName);
								jsonStr =JSON.stringify(editor.get());
							}

							var data = {
								paramName: paramName,
								paramValue: jsonStr
							}
							restParams.push(data);
						});
						
						var data = {
							restURL: restURL,
							restMethod: restMethod,
							restParams: restParams
						};
						
						ajaxController.ajax({
							method : "POST",
							url : _base + "/sandbox/restTest?rnd="+ Math.random(),
							dataType : "json",
							data: {
								data: JSON.stringify(data)
							},
							showWait : true,
							message : "正在执行服务测试...",
							success : function(data) {
								var testResult = data.data;
								$("#DIV_TEST_RESULT").show(); 
								if(testResult.actualCode=="failure"){
									//如果测试失败，不允许保存用例
									$("#RESP_JSONTEXT").val(testResult.actualResult?testResult.actualResult:"");
									$("#DIV_SUCCESS_RESULT").hide();
									$("#DIV_FAILURE_RESULT").show();
								}else{
									$("#DIV_SUCCESS_RESULT").show();
									$("#DIV_FAILURE_RESULT").hide();
									_this.respEditor.set(testResult.actualResult?JSON.parse(testResult.actualResult):{});
								}
							}
						});
					}

				}

			});

		})(jQuery);
	</script>
</body>
</html>

