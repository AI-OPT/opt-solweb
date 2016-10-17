<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>运营管理</title>
	<%@ include file="/inc/inc.jsp" %>
</head>

<body
	class="theme-whbl  pace-done fixed-header fixed-leftmenu fixed-footer">
	<div id="theme-wrapper">

		<%@ include file="/jsp/common/head.jsp"%>


		<div id="page-wrapper" class="container">
			<div class="row">
				<%@ include file="/jsp/common/leftmenu.jsp"%>

	<div id="content-wrapper">

<!-- <div class="content-wrapper-iframe">右侧灰色背景 -->
	<div class="row"><!--外围框架-->
		<div class="col-lg-12"><!--删格化-->
			<div class="row"><!--内侧框架-->
				<div class="col-lg-12"><!--删格化-->
					<div class="main-box clearfix"><!--白色背景-->
						<!-- 查询条件 -->
						<div class="form-label">
							<ul>
								<li class="col-md-12">
									<p class="word">商品评价</p>
									<p>
										<select id="shopScoreMs" class="select select-medium">
											<option value="">全部</option>
											<option value="5">好评</option>
											<option value="3">中评</option>
											<option value="1">差评</option>
										</select>
									</p>
									<p class="sos"><a href="javascript:void(0);">高级搜索<i class="fa fa-caret-down"></i></a>
									</p>
								</li>
							</ul>
							<!--点击展开-->
							<div id="selectDiv" class="open" style="display:none;">
								<ul>
									<li class="col-md-6">
										<p class="word">评价开始时间</p>
										<p><input id="commentTimeBegin" name="commentTimeBegin" class="int-text int-medium "
											  type="text" readonly
											  onfocus="WdatePicker({el:id,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'commentTimeEnd\');}'})"/>
										<span class="time"> <i class="fa  fa-calendar" ></i></span></p>
									</li>
									<li class="col-md-6">
										<p class="word">评价结束时间</p>
										<p><input id="commentTimeEnd" name="commentTimeEnd" class="int-text int-medium "
											  type="text" readonly
											  onfocus="WdatePicker({el:id,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'commentTimeBegin\');}'})"/>
										<span class="time"> <i class="fa  fa-calendar" ></i></span>
										</p>
									</li>
								</ul>
								<ul>
									<li class="col-md-6">
										<p class="word">服务态度</p>
										<select id="shopScoreWl" class="select select-medium">
											<option value="">全部</option>
											<option value="1">1分</option>
											<option value="2">2分</option>
											<option value="3">3分</option>
											<option value="4">4分</option>
											<option value="5">5分</option>
										</select>
									</li>
									<li class="col-md-6">
										<p class="word">物流服务</p>
										<select id="shopScoreFw" class="select select-medium">
											<option value="">全部</option>
											<option value="1">1分</option>
											<option value="2">2分</option>
											<option value="3">3分</option>
											<option value="4">4分</option>
											<option value="5">5分</option>
										</select>
									</li>
								</ul>
								<ul>
									<li class="col-md-6">
										<p class="word">商品ID</p>
										<p><input id="standedProdId" type="text" class="int-text int-medium"></p>
									</li>
									<li class="col-md-6">
										<p class="word">订单号</p>
										<p><input id="orderId" type="text" class="int-text int-medium"></p>
									</li>
								</ul>
							</div>
								<ul>
									<li class="width-xlag">
										<p class="word">&nbsp;</p>
										<p><input type="button" class="biu-btn  btn-primary btn-blue btn-medium ml-10"
												  id="selectCommentList" value="查  询"></p>
									</li>
								</ul>
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
						<!--标题-->
						<header class="main-box-header clearfix">
							<h2 class="pull-left">商品评论列表</h2>
						</header>
						<div class="row"><!--删格化-->
                                <p class="right pr-30">
                                    <input id="discardMoreBtn" type="button" class="biu-btn  btn-primary btn-blue btn-auto  ml-5"
                                           value="批量屏蔽">
                                </p>
                        </div>
						<!--标题结束-->
						<div class="main-box-body clearfix">
							<!--table表格-->
							<div class="table-responsive clearfix">
								<table id="TableView" class="table table-hover table-border table-bordered">
									<thead>
									<tr>
										<th><input id="checkall" name="checkall" type="checkbox" value="" /></th>
										<th>商品评价</th>
										<th>评价时间</th>
										<th>评价人</th>
										<th>评价内容</th>
										<th>评价图片</th>
										<th>物流服务</th>
										<th>服务态度</th>
										<th>商品ID</th>
										<th>商品名称</th>
										<th>订单号</th>
										<th>操作</th>
									</tr>
									</thead>
									<tbody id="searchCommentData">
										<tr>
										<td><input id="box" name="box" type="checkbox" value="{{:commentId}}" /></td>
										<td>好评(5星)</td>
										<td>2016-12-12</td>
										<td>测试</td>
										<td>
											<div class="hind1 text-l">
                                                <div class="center-hind" >测试</div>
                                                <div class="showbj"><i class="fa fa-posi fa-caret-up"></i>测试测试</div>
                                            </div>
										</td>
										<td>无</td>
										<td>5分</td>
										<td>5分</td>
										<td>10101</td>
										<td>
											<div class="hind1 text-l pl-15">
                                                <div class="center-hind" >测试题</div>
                                                <div class="showbj"><i class="fa fa-posi fa-caret-up"></i>测试</div>
                                            </div>
										</td>
										<td>1111</td>
                                        <td>
											<a href="javaScript:void(0)" class="blue-border">屏蔽</a>
										</td>
									</tr>
									</tbody>

								</table>
								<div id="showMessageDiv"></div>
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
<%@ include file="/jsp/common/foot.jsp"%>
</div>
				</div>
			</div>
		</div>
</body>
<script id="imageTemple" type="text/template">
	<div id="photo-{{:#index+1}}"><img src="{{:picAddr}}" width="400" height="230"/></div>
</script>
<script type="text/javascript">
	var pager;
	var imageCount = 0;
	<%-- 展示日历 --%>
	$('#selectDiv').delegate('.fa.fa-calendar','click',function(){
		$(this).parent().prev().focus();
	});
	(function () {
		<%-- 高级区域 --%>
		$(".form-label ul li .sos a").click(function () {
			$(".open ").slideToggle(100);
			$(".nav-form ").toggleClass("reorder remove");
		});
		seajs.use(['app/jsp/test/demo1','app/util/center-hind'], function(commentListPage,centerHind) {
			pager = new commentListPage({element : document.body});
			pager.render();
			new centerHind({element : document.body}).render();
		});
	})();
</script>
</html>