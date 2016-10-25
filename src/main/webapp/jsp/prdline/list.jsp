<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>产品线管理</title>
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
									<li class="width-xlag">
										<p><input type="text" class="int-text int-xxlarge" id="searchParams" placeholder="产品线编码/产品线名称"></p>
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
								<h2 class="pull-left">产品线列表</h2>
							</header>
                            <div class="row"><!--删格化-->
                                <p class="right pr-30">
                                    <input type="button" class="biu-btn  btn-primary btn-blue btn-auto  ml-5"
                                           value="新增产品线" onclick="javaScript:window.location.href = '${_base}/prdline/add';">
                                </p>
                        	</div>
                        	
                        	<div class="main-box-body clearfix">
							<!--table表格-->
							<div class="table-responsive clearfix">
								<table class="table table-hover table-border table-bordered">
									<thead>
									<tr>
										<th>序号</th>
										<th>产品线编码</th>
										<th>产品线名称</th>
										<th>创建时间</th>
										<th>行业类型</th>
										<th>负责人</th>
										<th>操作</th>
									</tr>
									</thead>
									<tbody id="searchPrdlineData">
									</tbody>

								</table>
								<div id="showMessageDiv"></div>
								<script id="searchPrdlineTemple" type="text/template">
									{{for result ~pageNo=pageNo ~pageSize=pageSize}}
									<tr>
										<td>{{:#index+1+(~pageNo-1)*~pageSize}}</td>
										<td>{{:prdlineCode}}</td>
										<td>
											<div class="hind1 text-l pl-15">
                                                <div class="center-hind" >{{:prdlineName}}</div>
                                                <div class="showbj"><i class="fa fa-posi fa-caret-up"></i>{{:prdlineName}}</div>
                                            </div>
										</td>
										<td>{{:createTime}}</td>
										<td>{{:industryName}}</td>
                                        <td>{{:prdlineManager}}</td>
										<td>
											<a href="${_base}/prdline/edit?prdlineId={{:prdlineId}}" onclick="pager._editPrdlineInfo('{{:srvApiId}}')" class="blue-border">修改</a>
											<a href="javaScript:void(0)" onclick="pager._deleteDialog('{{:prdlineId}}')" class="blue-border">删除</a>
											<a href="javaScript:void(0)" onclick="pager._showVersionInfo('{{:prdlineId}}')" class="blue-border">版本</a>
											<a href="${_base}/prdline/serviceList?prdlineId={{:prdlineId}}" class="blue-border">服务列表</a>
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
<script id="versionTemple" type="text/template">
	<tr>
		<td>{{:prdlineName}}</td>
		<td>{{:prdlineVersion}}</td>
		<td>{{:createTime}}</td>
	</tr>
</script>
</body>

<script type="text/javascript">
    var pager;
    (function () {
        seajs.use('app/jsp/prdline/list', function (prdListPager) {
            pager = new prdListPager({element: document.body});
            pager.render();
        });
    })(); 
</script>

</html>