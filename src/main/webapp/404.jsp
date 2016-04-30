<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/jsp/common/common.jsp"%>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>404-运营家服务在线</title>
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
										<li><a href="#">首页</a></li>
										<li class="active"><span>404</span></li>
									</ol>
								</div>
							</div>
						</div>
					</div>


					<div id="content-wrapper">
						<div class="row">
							<div class="col-lg-12">
								<div id="error-box">
									<div class="row">
										<div class="col-xs-12" style="min-height: 900px;">
											<div id="error-box-inner">
												<img src="<%=_base %>/resources/img/error-404-v3.png"
													alt="Have you seen this page?" />
											</div>
											<h1>ERROR 404</h1>
											<p>
												您访问的页面不存在
											</p>
											<p>
												<a href="<%=_base %>/">返回主页</a>.
											</p>
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
	</script> 
</body>
</html>

