<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<body
	class="theme-whbl  pace-done fixed-header fixed-leftmenu fixed-footer">

	<div class="row"><!--外围框架-->
		<div class="col-lg-12"><!--删格化-->
			<div class="row"><!--内侧框架-->
				<div class="col-lg-12"><!--删格化-->
					<div class="clearfix"><!--白色背景-->
                            <!--标题-->
                        	<div class="form-label">
							<ul>
									<li class="width-xlag">
										<p><input type="text" class="int-text int-xxlarge" id="searchParams" placeholder="产品线编码/产品线名称"></p>
										<p><input type="button" class="biu-btn  btn-primary btn-blue btn-medium ml-10"
												  id="searchPrdlineBtn" value="查  询"></p>
									</li>
							</ul>
						</div>
                        	<div class="main-box-body clearfix">
							<!--table表格-->
							<div class="table-responsive clearfix">
								<table class="table table-hover table-border table-bordered">
									<thead>
									<tr>
										<th>选择</th>
										<th>产品线编码</th>
										<th>产品线名称</th>
										<th>创建时间</th>
										<th>行业类型</th>
										<th>负责人</th>
									</tr>
									</thead>
									<tbody id="searchPrdlineData">
									</tbody>

								</table>
								<div id="showMessageDiv"></div>
								<script id="searchPrdlineTemple" type="text/template">
									{{for result ~pageNo=pageNo ~pageSize=pageSize}}
									<tr>
										<td style="padding: 1px 1px"><input type="radio" name="CHEK_PRDLINE"
										class="checkbox-medium" value="{{:prdlineId}}" prdlineName="{{:prdlineName}}" prdlineCode="{{:prdlineCode}}" prdlineManager="{{:prdlineManager}}"></td>
										<td style="padding: 1px 1px">{{:prdlineCode}}</td>
										<td style="padding: 1px 1px">
											<div class="hind1 text-l pl-15">
                                                <div class="center-hind" >{{:prdlineName}}</div>
                                                <div class="showbj"><i class="fa fa-posi fa-caret-up"></i>{{:prdlineName}}</div>
                                            </div>
										</td>
										<td style="padding: 1px 1px">{{:createTime}}</td>
										<td style="padding: 1px 1px">{{:industryName}}</td>
                                        <td style="padding: 1px 1px">{{:prdlineManager}}</td>
									</tr>
									{{/for}}
								</script>
							</div>
							<!--分页-->
							<div class="paging" style="margin-top:1px">
								<ul id="selsect-prdline-pagination">
								</ul>
							</div>
							<!--分页结束-->
						</div>
                        	
                        	
                        	
						</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
    var selectprdlinepager;
    (function () {
        seajs.use('app/jsp/serviceDefine/selectPrdline', function (prdListPager) {
        	selectprdlinepager = new prdListPager({element: document.body});
        	selectprdlinepager.render();
        });
    })(); 
</script>

</html>