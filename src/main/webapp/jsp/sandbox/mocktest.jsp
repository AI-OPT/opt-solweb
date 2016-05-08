<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/jsp/common/common.jsp"%>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Dubbo服务在线测试-服务在线管理</title>
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
										<li class="active"><span>dubbo服务测试</span></li>
									</ol>
								</div>
							</div>
						</div>
					</div>

					<div class="row" id="DIV_API">
						<div class="col-lg-12">
							<div class="main-box">
								<header class="main-box-header clearfix">
									<h2>填写测试信息</h2>
								</header>
								<div class="main-box-body clearfix">
									<div class="form-horizontal" role="form">
										<div class="form-group">
											<label class="col-lg-2 control-label">注册中心地址</label>
											<div class="col-lg-8">
												<input type="text" class="form-control" id="registryURL" size="10" value="zookeeper://10.1.228.222:19181" placeholder="请确保测试站点可以连接到您的服务注册中心">
												<select>
													<option>选择环境</option>
												</select>
												<a href="javascript:void(0)" name="HrefConnectTest">连通性测试</a>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">提供者类型</label>
											<div class="col-lg-8">
												<input type="text"   class="form-control" id="ownerType" value="<c:out value="${apiCallSetting.ownerType}"/>" readonly>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">提供者</label>
											<div class="col-lg-8">
												<input type="text"   class="form-control" id="owner" value="<c:out value="${apiCallSetting.owner}"/>" readonly>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">服务接口</label>
											<div class="col-lg-8">
												<input type="text"   class="form-control" id="interfaceName" value="<c:out value="${apiCallSetting.interfaceName}"/>" readonly>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">方法</label>
											<div class="col-lg-8">
												<small class="red"><c:out value="${apiCallSetting.briefComment}"/></small>
												<input type="text" class="form-control" id="method" value="<c:out value="${apiCallSetting.method}"/>" readonly>
												<a href="javascript:void(0)" name="HrefSelectCase">选择用例</a> &nbsp;&nbsp;<a href="<%=_base %>/api/getAPIVersionNewDetail.html?indexId=<c:out value="${apiCallSetting.indexId}"/>&activemenu=m_api" target="_blank">查看规范</a>
											</div>
										</div>
										
										<c:forEach items="${apiCallSetting.reqSettings}" var="reqParam" varStatus="status">
											<div class="form-group" name="DIV_REQ_PARAM_SETTING"  id="DIV_REQ_PARAM_${reqParam.paramName}">
												<label class="col-lg-2 control-label">入参${reqParam.sort}<br>(${reqParam.paramName})</label>
												<div class="col-lg-8">
												    ${reqParam.javaType}
													<input type="hidden" id="sort" value="${reqParam.sort}"/>
													<input type="hidden" id="paramName" value="${reqParam.paramName}"/>
													<c:if test="${reqParam.editorType==1 }">
														<div id="REQ_JSONEDITOR"  class="JSONEDITOR" paramjson="<c:out value="${reqParam.json}"/>"></div>
													</c:if>
													<c:if test="${reqParam.editorType==2 }">
														<input type="text" class="form-control" id="REQ_JSONTEXT" name="REQ_JSONTEXT" value="<c:out value="${reqParam.json}"/>" />
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
										<c:if test="${apiCallSetting.editorType!=3 }">
											<div class="form-group" name="DIV_RESP_PARAM_SETTING">
												<label class="col-lg-2 control-label">测试成功信息</label>
												<div class="col-lg-8">
												    ${apiCallSetting.returnJavaType} 
													<c:if test="${apiCallSetting.editorType==1 }">
														<div id="RESP_JSONEDITOR"  class="JSONEDITOR"></div>
													</c:if>
													<c:if test="${apiCallSetting.editorType==2 }">
														<textarea class="form-control" id="RESP_JSONTEXT" rows="8" readonly></textarea>
													</c:if> 
												</div>
											</div>
										</c:if>
										<c:if test="${apiCallSetting.editorType==3 }">
											<div class="form-group" name="DIV_RESP_PARAM_SETTING">
												<label class="col-lg-2 control-label">测试成功信息</label>
												<div class="col-lg-8">
												    ${apiCallSetting.returnJavaType} 
													<textarea class="form-control" id="RESP_JSONTEXT" rows="8" readonly>测试成功，无返回信息</textarea>
												</div>
											</div>
										</c:if>
										<div class="form-group" id="DIV_FAILURE_RESULT" style="display:none">
											<label class="col-lg-2 control-label">测试失败信息</label>
											<div class="col-lg-8">
												<textarea class="form-control" id="RESP_FAILURETEXT" rows="8" readonly></textarea>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">标签</label>
											<div class="col-lg-8">
												<input type="text" class="form-control" id="caseTag">
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">测试人员</label>
											<div class="col-lg-8">
												<input type="text" class="form-control" id="tester">
											</div>
										</div>
										<div class="form-group" id="DIV_SAVE_CASE" style="display:none">
											<div class="col-lg-offset-2 col-lg-10">
												<button type="button" class="btn btn-success" id="BTN_SAVE_CASE">保存为测试用例</button>
											</div>
										</div>
										<div class="form-group" id="DIV_CANNT_SAVE_CASE" style="display:none">
											<div class="col-lg-offset-2 col-lg-10">
												<button type="button" class="btn btn-default" id="BTN_SAVE_FAILURE">测试失败，用例不能保存</button>
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
	

	
<script id="APICaseListImpl" type="text/x-jsrender">
	<div class="row">
	 <div class="divcss5" style="height:400px;overflow:scroll;overflow-x: hidden;">
		<div class="col-lg-12">
			<div class="main-box clearfix">
				<header class="main-box-header clearfix">
					<div class="filter-block pull-right"> 
						<div class="form-group pull-left">查询条件：</div>
						<div class="form-group pull-left"> 
							<input type="text" id="QUERY_CASE_TAG"  placeholder="请输入标签关键字" class="form-control"> 
						</div>
						<div class="form-group pull-left"> 
							<input type="text" id="QUERY_CASE_TESTER"  placeholder="请输入测试人员名称" class="form-control"> 
						</div>
						<input type="button" value="查询" id="BTN_QUERY_CASE" class="btn btn-primary pull-right" > 
					</div>
				</header>
				<div class="main-box-body clearfix">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th class="text-center"></th>
									<th class="text-center"><span>标签</span></th>
									<th class="text-center"><span>测试者</span></th>
									<th class="text-center"><span>测试时间</span></th>
								</tr>
							</thead>
							<tbody id="APICaseListBody">	
							</tbody>
						</table>
					</div>
					<div id="divPagination" class="row filter-block pull-right "></div>
				</div>
			</div>
		</div>
	</div>
	</div> 		
</script>

<script id="APICaseListBodyTmpl" type="text/x-jsrender">
		<tr>
			<td class="text-center">
					<input type="radio" name="RADIO_CASE" value="{{:caseId}}"/>
			</td>
			<td class="text-center">{{:caseTag}}</td>
			<td class="text-center">{{:tester}}</td>
			<td class="text-center">{{:testTime}}</td>
		</tr>
</script>

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
							_this.mockTest();
						});
						
						$("#BTN_SAVE_CASE").bind("click", function() {
							_this.saveCase();
						});
						
						$("[name='HrefSelectCase']").bind("click", function() {
							_this.toSelectAPICase();
						});  
						
						$("[name='HrefConnectTest']").bind("click", function() {
							_this.testConnect();
						});
						
						
					},
					
					toSelectAPICase: function(){ 
						var _this = this;
			            var template = $.templates("#APICaseListImpl");
			            var htmlOutput = template.render({});
			            bootbox.dialog({
			                title: "选择已有的用例",
			                message: htmlOutput,
			                buttons: {
			                    saveBtn: {
			                        label: "确定",
			                        className: "btn-success",
			                        callback: function () {
			                        	 _this.selectAPICase();
			                        	 return false;
			                        }
			                    }
			                }
			            });
			            var pageSize =5;
			            $("#BTN_QUERY_CASE").bind("click", function() {
							_this.queryAPICases(1,pageSize);
						});  
			            _this.queryAPICases(1,pageSize);
					},
					
					selectAPICase: function(){
						var _this = this;
						var caseId=$("[name='RADIO_CASE']:checked").val();
						if(!caseId || caseId==""){
							messageController.alert("请先选择用例");
							return ;
						}
						
						ajaxController.ajax({
							method : "POST",
							url : _base + "/sandbox/queryAPICaseReqParamByCaseId?rnd="+ Math.random(),
							dataType : "json",
							data: {
								caseId:  caseId
							},
							showWait : true,
							message : "处理中，请稍候...",
							success : function(data) {
								var d = data.data;
								_this.fillingReqParams(d?d:[]);
								bootbox.hideAll();
							}
						});
					},
					
					fillingReqParams: function(settingReqParams){ 
						var _this = this;
						$.each(settingReqParams,function(index,d){
							var paramName = d.paramName;
							var editorType = d.editorType;
							var json = d.json;
							var reqParamDIVObject=$("#DIV_REQ_PARAM_"+paramName);
							if(!reqParamDIVObject){
								return false;
							}
							
							if(editorType=="1"){
								//判断是JSON编辑器的情况
								var reqEditor = _this.getReqJSONEditor(paramName);
								if(reqEditor){
									reqEditor.set(json?JSON.parse(json):{});
								}
							}else if(editorType=="2"){
								//如果是普通文本框
								reqParamDIVObject.find("#REQ_JSONTEXT").val(json?json:"");
							} 
						});
					},
					
					queryAPICases: function(pageNo,pageSize){
						var _this = this;
						var tester = $("#QUERY_CASE_TESTER").val();
						var caseTag = $("#QUERY_CASE_TAG").val();
						var queryCond = {
								interfaceName: "<c:out value="${apiCallSetting.interfaceName}"/>",
								method: "<c:out value="${apiCallSetting.method}"/>",
								tester: $.trim(tester),
								caseTag: $.trim(caseTag),
								pageInfo: {
									pageNo: pageNo,
									pageSize: pageSize
								}
						}
						
						ajaxController.ajax({
							method : "POST",
							url : _base + "/sandbox/queryAPICases?rnd="+ Math.random(),
							dataType : "json",
							data: {
								queryCond:  JSON.stringify(queryCond)
							},
							showWait : true,
							message : "查询中，请稍候...",
							success : function(data) {
								var d = data.data;
								 var template = $.templates("#APICaseListBodyTmpl");
			                     var htmlOutput = template.render(d.result?d.result:[]);
			                     $("#APICaseListBody").html(htmlOutput);
			                     var count = d.count;
			                     var pageCount= d.pageCount;
			                     var pageNo = d.pageNo;
			                     var pageSize= d.pageSize;
			                     _this.renderAPICasePagination(pageCount,pageNo,pageSize);
			                     $('#divPagination').bootstrapPaginator("show",pageNo);
							}
						});
					},
					
					renderAPICasePagination: function(totalPages,currentPage,pageSize){
						var _this = this;
						var options = {
							totalPages: totalPages,
						    currentPage: currentPage,
						    numberOfPages:5,
						    onPageClicked: function(e,originalEvent,type,page){
						    	e.stopImmediatePropagation();
						    	var currentTarget = $(e.currentTarget);
						    	var oldpages = currentTarget.bootstrapPaginator("getPages");
								_this.queryAPICases(page,pageSize);
						    }
						}
						$('#divPagination').bootstrapPaginator(options);
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
							var container = document.getElementById(jsoneditorId);
							var editor = new JSONEditor(container, options, {});
							respEditors.push(editor);
						});
						this.respEditors = respEditors;
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
					
					getRespJSONEditor: function(index){
						var editors = this.respEditors;
						var editor = editors[index];
						return editor;
					},
					
					mockTest: function(){
						var _this = this; 
						var registryURL = $("#registryURL").val();
						var interfaceName = $("#interfaceName").val();
						var method = $("#method").val();
						if($.trim(registryURL)==""){
							messageController.alert("请输入测试的服务注册中心");
							return ;
						}
						if($.trim(interfaceName)==""){
							messageController.alert("服务接口不能为空");
							return ;
						}
						if($.trim(method)==""){
							messageController.alert("方法不能为空");
							return ;
						}
						
						var reqParams = new Array();
						$("[name='DIV_REQ_PARAM_SETTING']").each(function(index,div){
							var paramName = $(div).find("#paramName").val();
							var sort = $(div).find("#sort").val();
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
								json: jsonStr
							}
							reqParams.push(data);
						});
						
						var apiCallCase = {
							owner : "<c:out value="${apiCallSetting.owner}"/>",
							interfaceName: "<c:out value="${apiCallSetting.interfaceName}"/>",
							method: "<c:out value="${apiCallSetting.method}"/>",
							reqParams: reqParams
						};
						
						ajaxController.ajax({
							method : "POST",
							url : _base + "/sandbox/mockTest?rnd="+ Math.random(),
							dataType : "json",
							data: {
								data: JSON.stringify(apiCallCase),
								registryURL: registryURL
							},
							showWait : true,
							message : "正在执行服务测试...",
							success : function(data) {
								var testResult = data.data;
								$("#DIV_TEST_RESULT").show(); 
								if(testResult.actualCode=="failure"){
									//如果测试失败，不允许保存用例
									$("#RESP_FAILURETEXT").val(testResult.actualResult?testResult.actualResult:"");
									$("#DIV_SAVE_CASE").hide();
									$("#DIV_CANNT_SAVE_CASE").show();
									$("#DIV_FAILURE_RESULT").show();
									$("[name='DIV_RESP_PARAM_SETTING']").hide();
								}else{
								<c:if test="${apiCallSetting.editorType==1 }">
									_this.getRespJSONEditor(0).set(testResult.actualResult?JSON.parse(testResult.actualResult):{});
								</c:if>
								<c:if test="${apiCallSetting.editorType==2 }">
									$("#RESP_JSONTEXT").val(testResult.actualResult?testResult.actualResult:"");
								</c:if>
									$("#RESP_FAILURETEXT").val("");
									$("#DIV_FAILURE_RESULT").hide();
									$("[name='DIV_RESP_PARAM_SETTING']").show();
									$("#DIV_SAVE_CASE").show();
									$("#DIV_CANNT_SAVE_CASE").hide();
								}
							}
						});
					},
					
					testConnect: function(){
						var _this = this;
						var registryURL = $("#registryURL").val();
						if($.trim(registryURL)==""){
							messageController.alert("请输入测试的服务注册中心");
							return ;
						}
						ajaxController.ajax({
							method : "POST",
							url : _base + "/sandbox/checkRegistryAvailable?rnd="+ Math.random(),
							dataType : "json",
							data: {
								registryURL: registryURL
							},
							showWait : true,
							message : "正在测试到注册中心的连通性...",
							success : function(data) {
								if(data.data=="1"){
									messageController.alert("连接成功");
								}else{
									messageController.alert("连接失败");
								}
							}
						});
					},
					
					saveCase: function(){
						var _this = this;
						var interfaceName = $("#interfaceName").val();
						var method = $("#method").val();
						var tester=$("#tester").val();
						var caseTag = $("#caseTag").val();
						if($.trim(interfaceName)==""){
							messageController.alert("服务接口不能为空");
							return ;
						}
						if($.trim(method)==""){
							messageController.alert("方法不能为空");
							return ;
						}
						if($.trim(caseTag)==""){
							messageController.alert("请填写标签，方便检索");
							return ;
						}
						if($.trim(caseTag).length>100){
							messageController.alert("标签不能超过100个字符");
							return ;
						}
						if($.trim(tester)==""){
							messageController.alert("请填写测试人员名称");
							return ;
						}
						
						
						var reqParams = new Array();
						$("[name='DIV_REQ_PARAM_SETTING']").each(function(index,div){
							var paramName = $(div).find("#paramName").val();
							var sort = $(div).find("#sort").val();
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
								sort:sort,
								json: jsonStr
							}
							reqParams.push(data);
						});
						
						var actualResult="";
						<c:if test="${apiCallSetting.editorType==1 }">
						actualResult =JSON.stringify(_this.getRespJSONEditor(0).get()); 
						</c:if>
						<c:if test="${apiCallSetting.editorType==2 }">
						actualResult=$("#RESP_JSONTEXT").val();
						</c:if>
						
						
						var data = {
							owner: "<c:out value="${apiCallSetting.owner}"/>",
							ownerType: "<c:out value="${apiCallSetting.ownerType}"/>",
							tester:tester,
							caseTag: caseTag,
							interfaceName: interfaceName,
							method: method,
							actualResult: actualResult,
							reqParams: reqParams
						};
						
						ajaxController.ajax({
							method : "POST",
							url : _base + "/sandbox/saveTestCase?rnd="+ Math.random(),
							dataType : "json",
							data: {
								data: JSON.stringify(data)
							},
							showWait : true,
							message : "正在执行测试用例保存...",
							success : function(data) {
								messageController.alert("保存成功",function(){
									$("#DIV_TEST_RESULT").hide();
									$("#actualResult").val("");
								});
							}
						});
					}

				}

			});

		})(jQuery);
	</script>
</body>
</html>

