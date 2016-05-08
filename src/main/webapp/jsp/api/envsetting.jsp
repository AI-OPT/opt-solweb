<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/jsp/common/common.jsp"%>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>环境设置-服务在线管理</title>
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
										<li class="active"><span>环境设置</span></li>
									</ol>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-12">
							<div class="main-box clearfix">
								<header class="main-box-header clearfix">
									<h2 class="pull-left">已经设置好的信息</h2>
								</header>
								<div class="main-box-body clearfix">
									<div class="table-responsive">
										<table class="table">
											<thead>
												<tr>
													<th class="text-center">提供者类型</th>
													<th class="text-center">提供者</th>
													<th class="text-center">环境名称</th>
													<th class="text-center">注册中心</th>
													<th class="text-center">REST服务地址</th>
													<th>&nbsp;</th>
												</tr>
											</thead>
											<tbody id="search-results">
												
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-lg-12">
							<div class="main-box">
								<header class="main-box-header clearfix">
									<h2>设置环境</h2>
								</header>
								<div class="main-box-body clearfix">
									<div class="form-horizontal" role="form">
										<div class="form-group">
											<label class="col-lg-2 control-label">提供者类型</label>
											<div class="col-lg-8">
												<input type="hidden" id="settingsId"/>
												<input type="text" class="form-control" id="ownertype" size="10" value="<c:out value="${ownerType}"/>" readonly>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">提供者</label>
											<div class="col-lg-8">
												<input type="text" class="form-control" id="owner" size="10" value="<c:out value="${owner}"/>" readonly>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">环境名称</label>
											<div class="col-lg-8">
												<input type="text" class="form-control" id="env">
												<small class="red">设置一个环境的名称，比如：dev/qa/product</small>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">注册中心地址</label>
											<div class="col-lg-8">
												<input type="text" class="form-control" id="zkcenter">
												<small class="red">请填写注册中心地址信息 zookeeper://localhost:19181</small>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">REST服务地址</label>
											<div class="col-lg-8"> 
												<input type="text" class="form-control" id="resthttp">
												<small class="red">请填写部署后的地址信息 http://ip:port/modulename/</small>
											</div>
										</div>
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
				this.loadAllSettings();
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
					},
					
					loadAllSettings: function(){
						var _this = this;
						ajaxController.ajax({
							method : "POST",
							url : _base + "/api/getEnvSettings?rnd="+ Math.random(),
							dataType : "json",
							data: {
								ownerType: "<c:out value="${ownerType}"/>",
								owner: "<c:out value="${owner}"/>"
							},
							showWait : true,
							message : "正在获取数据...",
							success : function(data) {
								var d = data.data;
								if(d.length!=0){
									var template = $.templates("#AllEnvSettingListImpl");
				                    var htmlOutput = template.render(d);
				                    $("#search-results").html(htmlOutput);
				                    
				                    $("[name='HrefEditEnv']").bind("click",function(){
				                    	var settingsId = $(this).attr("settingsId");
				                    	_this.loadOneSetting(settingsId);
				                    });
				                 }
							}
						});
					},
					
					loadOneSetting: function(settingsId){
						var _this = this;
						ajaxController.ajax({
							method : "POST",
							url : _base + "/api/getEnvSetting?rnd="+ Math.random(),
							dataType : "json",
							data: {
								settingId: settingsId
							},
							showWait : true,
							message : "正在加载数据...",
							success : function(data) {
								var d = data.data;
								$("#settingsId").val(d.settingsId);
								$("#env").val(d.env);
								$("#owner").val(d.owner);
								$("#ownerType").val(d.ownertype);
								$("#zkcenter").val(d.zkcenter);
								$("#resthttp").val(d.resthttp);
							}
						});
					},
					
					submit: function(){
						var _this = this;
						var settingsId = $("#settingsId").val().trim(); 
						var owner = $("#owner").val().trim(); 
						var ownertype = $("#ownertype").val().trim(); 
						var env = $("#env").val().trim(); 
						var zkcenter = $("#zkcenter").val().trim(); 
						var resthttp = $("#resthttp").val().trim(); 
						if(owner==""){
							messageController.alert("提供者不能为空");
							return ;
						}
						if(ownertype==""){
							messageController.alert("提供者类型不能为空");
							return ;
						}
						if(env==""){
							messageController.alert("环境名称不能为空");
							return ;
						}
						if(zkcenter==""){
							messageController.alert("注册中心不能为空");
							return ;
						}
						var data = {
							settingsId: settingsId,
							owner: owner,
							ownertype: ownertype,
							env: env,
							zkcenter: zkcenter,
							resthttp: resthttp
						};
						ajaxController.ajax({
							method : "POST",
							url : _base + "/api/saveEnvSetting?rnd="+ Math.random(),
							dataType : "json",
							data: {
								envSetting: JSON.stringify(data)
							},
							showWait : true,
							message : "正在提交设置...",
							success : function(data) {
								messageController.alert("提交成功",function(){
									_this.loadAllSettings();
									_this.resetForm();
									
								});
							}
						});
					},
					
					resetForm: function(){
						$("#settingsId").val("");
						$("#env").val("");
						$("#zkcenter").val("");
						$("#resthttp").val("");
					}

				}

			});

		})(jQuery);
	</script>

</body>
</html>

<script id="AllEnvSettingListImpl" type="text/x-jsrender">
<tr>
	<td class="text-center">{{:ownertype}}</td>
	<td class="text-center">{{:owner}}</td>
	<td class="text-center">{{:env}}</td>
	<td class="text-center">{{:zkcenter}}</td>
	<td class="text-center">{{:resthttp}}</td>
	<td class="text-center"><a href="javascript:void(0)" class="table-link" name="HrefEditEnv" settingsId = "{{:settingsId}}">
			<span class="fa-stack"> <i
				class="fa fa-square fa-stack-2x"></i> <i
				class="fa fa-pencil fa-stack-1x fa-inverse"></i>
		</span>
	</a></td>
</tr>
</script>
