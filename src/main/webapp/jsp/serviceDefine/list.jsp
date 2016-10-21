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
	<div class="content-wrapper"><!--右侧灰色背景-->
	
	<div id="content-wrapper"><!--右侧灰色背景-->
	<div class="row"><!--外围框架-->
		<div class="col-lg-12"><!--删格化-->
			<div class="row"><!--内侧框架-->
				<div class="col-lg-12"><!--删格化-->
					<div class="main-box clearfix"><!--白色背景-->
						<div class="main-box-body clearfix">
                            <!-- 查询条件 -->
						<div class="form-label">
							<ul>
								<li class="col-md-6">
									<p class="word">当前目录：</p>
									<p id="category"></p>
								</li>
								<li class="col-md-6">
									<p class="word">服务个数：</p>
									<p id="serviceCount"></p>
								</li>
							</ul>
							<ul>
									<li class="width-xlag">
										<p><input type="text" class="int-text int-xxlarge" id="searchParams"></p>
										<p><input type="button" class="biu-btn  btn-primary btn-blue btn-medium ml-10"
												  id="searchServiceBtn" value="查  询"></p>
									</li>
							</ul>
						</div>
                            
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row"><!--外围框架-->
		<div class="col-lg-12"><!--删格化-->
			<div class="row"><!--内侧框架-->
				<div class="col-lg-12"><!--删格化-->
					<div class="main-box clearfix"><!--白色背景-->
						<div class="main-box-body clearfix">
                            <!--标题-->
							<header class="main-box-header clearfix">
								<h2 class="pull-left">服务列表</h2>
							</header>
                            <div class="row"><!--删格化-->
                                <p class="right pr-30">
                                    <input type="button" class="biu-btn  btn-primary btn-blue btn-auto  ml-5"
                                           value="打标签" onclick="javaScript:window.location.href = '${_base}/normprodedit/add';">
                                </p>
                                <p class="right pr-30">
                                    <input type="button" class="biu-btn  btn-primary btn-blue btn-auto  ml-5"
                                           value="打版本" onclick="javaScript:window.location.href = '${_base}/normprodedit/add';">
                                </p>
                        	</div>
                        	
                        	<div class="main-box-body clearfix">
							<!--table表格-->
							<div class="table-responsive clearfix">
								<table class="table table-hover table-border table-bordered">
									<thead>
									<tr>
										<th>选择</th>
										<th>序号</th>
										<th>服务编码</th>
										<th>服务名称</th>
										<th>服务分类</th>
										<th>产品标签</th>
										<th>版本记录</th>
									</tr>
									</thead>
									<tbody id="searchServiceData">
									</tbody>

								</table>
								<div id="showMessageDiv"></div>
								<script id="searchServiceTemple" type="text/template">
									{{for result ~pageNo=pageNo ~pageSize=pageSize}}
									<tr>
										<td></td>
										<td>{{:#index+1+(~pageNo-1)*~pageSize}}</td>
										<td>{{:srvApiId}}</td>
										<td>
											<div class="hind1 text-l pl-15">
                                                <div class="center-hind" >{{:srvApiName}}</div>
                                                <div class="showbj"><i class="fa fa-posi fa-caret-up"></i>{{:srvApiName}}</div>
                                            </div>
										</td>
										<td>
											<div class="hind1 text-l pl-15">
                                                <div class="center-hind" >{{:srvCategoryValue}}</div>
                                                <div class="showbj"><i class="fa fa-posi fa-caret-up"></i>{{:srvCategoryValue}}</div>
                                            </div>
										</td>
										<td><a href="${_base}/normprodedit/{{:productId}}" class="blue-border">{{:prdlineCount}}</a></td>
                                        <td>
											<a href="${_base}/normprodquery/{{:productId}}" class="blue-border">{{:versionCount}}</a>
										</td>
									</tr>
									{{/for}}
								</script>
							</div>
							<!--分页-->
							<div class="paging">
								<ul id="pagination-ul">
								</ul>
							</div>
							<!--分页结束-->
						</div>
                        	
                        	
                        	
						</div>
					</div>
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
        seajs.use('app/jsp/serviceDefine/list', function (serviceListPager) {
            pager = new serviceListPager({element: document.body});
            pager.render();
        });
    })(); 
</script>
</html>