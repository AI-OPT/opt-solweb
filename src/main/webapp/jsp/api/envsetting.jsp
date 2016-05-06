<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/jsp/common/common.jsp"%>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>调用环境设置-服务在线管理</title>
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
										<li class="active"><span>调用环境设置</span></li>
									</ol>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-12">
							<div class="main-box clearfix">
								<header class="main-box-header clearfix">
									<h2 class="pull-left">服务提供者已经设置的环境信息</h2>
								</header>
								<div class="main-box-body clearfix">
									<div class="table-responsive">
										<table class="table">
											<thead>
												<tr>
													<th class="text-center">提供者类型</th>
													<th class="text-center">提供者</th>
													<th class="text-center">注册中心</th>
													<th class="text-center">HttpRest前缀</th>
													<th>&nbsp;</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="text-center"></td>
													<td class="text-center"></td>
													<td class="text-center"></td>
													<td class="text-center"></td>
													<td class="text-center"><a href="#" class="table-link">
															<span class="fa-stack"> <i
																class="fa fa-square fa-stack-2x"></i> <i
																class="fa fa-pencil fa-stack-1x fa-inverse"></i>
														</span>
													</a></td>
												</tr>
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
									<h2>设置调用环境信息</h2>
								</header>
								<div class="main-box-body clearfix">
									<div class="form-horizontal" role="form">
										<div class="form-group">
											<label class="col-lg-2 control-label">提供者类型</label>
											<div class="col-lg-8">
												<input type="hidden" id="settingId"/>
												<input type="text" class="form-control" id="ownerType" size="10" value="<c:out value="${owner}"/>" readonly>
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
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">注册中心地址</label>
											<div class="col-lg-8">
												<input type="text" class="form-control" id="zkcenter">
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label">HTTP REST服务地址</label>
											<div class="col-lg-8"> 
												<input type="text" class="form-control" id="resthttp">
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
					},
					
					submit: function(){
						var _this = this;
						var callType = $("#callType").val(); 
 						
						var data = {
							
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

