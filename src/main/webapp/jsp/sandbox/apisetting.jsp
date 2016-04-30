<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/jsp/common/common.jsp"%>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>服务设置-运营家服务在线</title>
<link rel="stylesheet" href="${_base }/resources/jsoneditor/jsoneditor.min.css">
<script src="${_base }/resources/jsoneditor/jsoneditor.min.js"></script>
<script src="${_base }/resources/jsoneditor/asset/ace/ace.js"></script>
<script src="${_base }/resources/jsoneditor/asset/jsonlint/jsonlint.js"></script>
 <style type="text/css">
    code {
      background-color: #f5f5f5;
    }

    .JSONEDITOR {
      width: 100%;
      height: 300px;
    }
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
										<li class="active"><span>服务设置</span></li>
									</ol>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-12">
							<div class="main-box">
								<header class="main-box-header clearfix">
									<h2>设置调用返回信息</h2>
								</header>
								<div class="main-box-body clearfix">
									<div class="form-horizontal" role="form">
										<div class="form-group">
											<label class="col-lg-2 control-label">提供者</label>
											<div class="col-lg-8">
												<input type="text" class="form-control" id="owner" size="10" value="<c:out value="${apiCallSetting.owner}"/>" readonly>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">服务接口</label>
											<div class="col-lg-8">
												<input type="text" class="form-control" id="interfaceName" value="<c:out value="${apiCallSetting.interfaceName}"/>" readonly>
												<input type="hidden" id="settingId"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">方法</label>
											<div class="col-lg-8">
												<small class="red"><c:out value="${apiCallSetting.briefComment}"/></small>
												<input type="text" class="form-control" id="method" value="<c:out value="${apiCallSetting.method}"/>" readonly>
												<a href="<%=_base %>/api/getAPIVersionNewDetail.html?indexId=<c:out value="${apiCallSetting.indexId}"/>&activemenu=m_api" target="_blank">查看规范</a>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">调用返回方式</label>
											<div class="col-lg-8"> 
												<select class="form-control" id="callType">
													<option value="1" <c:if test="${apiCallSetting.callType==1 }">selected</c:if>>沙箱模拟返回</option>
													<option value="2" <c:if test="${apiCallSetting.callType==2 }">selected</c:if>>实际调用返回</option>
												</select>
											</div>
										</div>
										<c:if test="${apiCallSetting.editorType!=3 }">
											<div class="form-group" name="DIV_RESP_PARAM_SETTING" <c:if test="${apiCallSetting.callType==2 }">style="display:none"</c:if>>
												<label class="col-lg-2 control-label">沙箱返回参数</label>
												<div class="col-lg-8">
												    ${apiCallSetting.returnJavaType} 
													<c:if test="${apiCallSetting.editorType==1 }">
														<div id="RESP_JSONEDITOR"  class="JSONEDITOR"  paramjson="<c:out value="${apiCallSetting.sandboxResp}"/>"></div>
													</c:if>
													<c:if test="${apiCallSetting.editorType==2 }">
														<input type="text" class="form-control" id="RESP_JSONTEXT" name="RESP_JSONTEXT" value="<c:out value="${apiCallSetting.sandboxResp}"/>"/>
													</c:if>
													
												</div>
											</div>
										</c:if>
										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<button type="button" class="btn btn-success" id="BTN_SUBMIT">提交</button>
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
						$("#BTN_SUBMIT").bind("click", function() {
							_this.submit();
						});
						
						$("#callType").bind("change",function(){
							var callType =  $(this).val(); 
							(callType!="1")?$("[name='DIV_RESP_PARAM_SETTING']").hide():$("[name='DIV_RESP_PARAM_SETTING']").show();
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
						//输出参数编辑器
						var respEditors = new Array();
						$("[name='DIV_RESP_PARAM_SETTING']").each(function(index,div){
							var obj = $(div).find("#RESP_JSONEDITOR");
							if(!obj){
								return;
							}
							
							var jsoneditorId = obj.attr("id");
							if(jsoneditorId==undefined){
								return;
							}
							//数据库中保存的json字符串
							var paramjson=obj.attr("paramjson");
							var container = document.getElementById(jsoneditorId);
							var editor = new JSONEditor(container, options, {});
							editor.set(paramjson?JSON.parse(paramjson):{});
							respEditors.push(editor);
						}); 
						this.respEditors = respEditors;
					}, 
					
					getRespJSONEditor: function(index){
						var editors = this.respEditors;
						var editor = editors[index];
						return editor;
					},
					
					submit: function(){
						var _this = this;
						var callType = $("#callType").val(); 
						if(callType==""){
							messageController.alert("调用方式不能为空");
							return ;
						}
						
						var returnJson = "";
						$("[name='DIV_RESP_PARAM_SETTING']").each(function(index,div){
							var jsonTEXT = $(div).find("#RESP_JSONTEXT");
							if(jsonTEXT && jsonTEXT.length>0){
								returnJson = jsonTEXT.val();
							}else{
								var editor = _this.getRespJSONEditor(index);
								returnJson =JSON.stringify(editor.get());
							}
						}); 
						
						var data = {
							settingId: "<c:out value="${apiCallSetting.settingId}"/>",
							interfaceName: "<c:out value="${apiCallSetting.interfaceName}"/>",
							method: "<c:out value="${apiCallSetting.method}"/>",
							callType: callType,
							sandboxResp: returnJson
						};
						ajaxController.ajax({
							method : "POST",
							url : _base + "/sandbox/setAPISandboxSetting?rnd="+ Math.random(),
							dataType : "json",
							data: {
								data: JSON.stringify(data)
							},
							showWait : true,
							message : "正在提交设置...",
							success : function(data) {
								messageController.alert("提交成功");
							}
						});
					}

				}

			});

		})(jQuery);
	</script>

</body>
</html>

