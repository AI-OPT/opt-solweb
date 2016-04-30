<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/jsp/common/common.jsp"%>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>运营家服务在线</title>

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
										<li><span><a
												href="../api/index.html?activemenu=m_api">首页</a></span></li>
										<li class="active"><span>服务在线</span></li>
									</ol>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="col-lg-12">
							<div class="main-box clearfix">
								<header class="main-box-header clearfix">
									<h2>运营家服务在线</h2>
									<div class="main-box-body clearfix">
										&nbsp;&nbsp;&nbsp;&nbsp;<span id="DIV_API_STATISCIS"></span>您可以通过网站，在线查看服务产品设计文档，查阅服务定义的规范，通过对接本地服务注册中心实现在线DEBUG，QA人员可以通过在线测试完成服务测试。系统提供服务开发过程管控，有效提升服务开发的效率。
									</div>
								</header>
							</div>
						</div>
					</div>
					<%String[] colors = new String[] { "bg-aqua", "bg-green", "bg-yellow", "bg-red","bg-purple","bg-blue","bg-light-blue","bg-navy","bg-teal","bg-olive","bg-lime","bg-orange","bg-maroon" } ; %>
					<div class="row">				
						<div class="main-box clearfix">
							<div class="tabs-wrapper profile-tabs">
								<c:if test=" ${fn:length(apiOwnerTypes)<=0}">
									没有任何服务信息
								</c:if>
								<c:if test="${fn:length(apiOwnerTypes)>0}">
								<ul class="nav nav-tabs">
									<c:forEach var="apiOwnerType" items="${apiOwnerTypes}" varStatus="varStatus">
									<li <c:if test="${varStatus.index==0}">class="active"</c:if> ><a href="#tab-<c:out value="${apiOwnerType.ownerType}"/>" data-toggle="tab"><c:out value="${apiOwnerType.ownerTypeName}"/>[<c:out value="${apiOwnerType.ownerCount}"/>/<c:out value="${apiOwnerType.serviceCount}"/>]</a></li>
									</c:forEach>
								</ul>
								<div class="tab-content">
									<c:forEach var="apiOwnerType" items="${apiOwnerTypes}" varStatus="varStatus">
									<div class="tab-pane fade <c:if test="${varStatus.index==0}">in active</c:if>" id="tab-<c:out value="${apiOwnerType.ownerType}"/>">
										<c:if test=" ${fn:length(apiOwnerType.ownerStatistics)<=0}">
											此产品体系下没有任何产品
										</c:if>
										<c:if test="${fn:length(apiOwnerType.ownerStatistics)>0}">
										<c:forEach var="ownerStat" items="${apiOwnerType.ownerStatistics}" varStatus="varOwnerStatStatus">
										<div class="col-lg-4 col-sm-6 col-xs-12" style="color: white;">
											<div class="main-box infographic-box <c:out value="${ownerStat.color}"/>">
												<i class="fa fa-user"></i> <span class="headline"><a style="color: white;" href="${_base}/api/downloadAPIs?activemenu=m_api&owner=<c:out value="${ownerStat.owner}"/>&ownerType=<c:out value="${ownerStat.ownerType}"/>" target="_blank" title="点击下载服务数据"><c:out value="${ownerStat.ownerName}"/></a></span>
												<span class="value" > 
												  <a style="color: white;" href="${_base}/api/tosearch.html?activemenu=m_api&owner=<c:out value="${ownerStat.owner}"/>&ownerType=<c:out value="${ownerStat.ownerType}"/>"><c:out value="${ownerStat.apiCount}"/>个服务 </a>
												</span>
											</div>
										</div>
										</c:forEach>
										</c:if>
									</div>
									</c:forEach>
								</div>
								</c:if>
							</div>
						</div>
					</div>
					

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
				this.init();
			}

			$.extend($.PageController, {
				defaults : {

				},
				prototype : {
					
					init: function(){
						this.bindEvents();
						this.getAPIStatistics();
					},

					bindEvents : function() {
						var _this = this;
						$("#BTN_SEARCH").bind("click", function() {
							
						});
						
					},
					
					getAPIStatistics: function(){
						ajaxController.ajax({
							method : "POST",
							url : _base + "/api/getAPIStatistics?rnd="
									+ Math.random(),
							dataType : "json",
							showWait : false,
							success : function(data) {
								var template = $.templates("#APIStatiscisImpl");
								var htmlOutput = template.render(data.data);
								$("#DIV_API_STATISCIS").html(htmlOutput);
							}
						});
					}

				}

			});

		})(jQuery);

	</script>
	<script id="APIStatiscisImpl" type="text/x-jsrender">
	在线网站目前一共接入了<font color="red"><b>{{:ownerTypeCount}}</b>类</font>产品体系,包含<font color="red"><b>{{:ownerCount}}</b>个</font>产品，共收录了<font color="red"><b>{{:apiCount}}</b>个</font>服务。
	</script>
	

</body>
</html>

