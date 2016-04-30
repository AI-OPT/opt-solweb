<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/jsp/common/common.jsp"%>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>服务详情-运营家服务在线</title>
<link rel="stylesheet" href="${_base }/resources/treegrid/css/jquery.treegrid.css" type="text/css"/>
<script src="${_base}/resources/treegrid/js/jquery.treegrid.bootstrap3.js" ></script>
<script src="${_base}/resources/treegrid/js/jquery.treegrid.min.js" ></script>
<script src="${_base}/resources/treegrid/js/jquery.cookie.js" ></script>
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
										<li><a href="${_base}/api/index.html?activemenu=m_home">首页</a></li> 
										<li><span><a href="../api/tosearch.html?activemenu=m_api">服务搜索</a></span></li>
										<li class="active"><span>服务详情</span></li>
									</ol>
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-lg-12">
									<div class="main-box clearfix">
										<header class="main-box-header clearfix" >
											<h2>
												<span class="emerald">服务：<font color="red"><c:out value="${apiDoc.interfaceName}"/></font>
												<br>方法：<font color="red"><c:out value="${apiDoc.methodName}"/></font>   版本：<font color="red"><c:out value="${apiDoc.version}"/></font>
												</span>
											</h2>
											<div class="desc"><c:out value="${apiDoc.briefComment}"/></div>
											<small class="gray">服务管理者:<font color="red"><c:out value="${apiDoc.author}"/> </font>&nbsp;&nbsp;&nbsp;索引发布时间:<font color="red"><c:out value="${apiDoc.publishDate}"/></font></small>
										</header>
									</div>
								</div>
							</div>
							
							<!-- 入参开始 -->
							<div class="row">
								<div class="col-lg-12">
									<div class="main-box clearfix">
										<header class="main-box-header clearfix">
											<h2>服务入参规范</h2>
										</header>
										<div class="main-box-body clearfix">
											<div class="table-responsive">
												<c:if test="${fn:length(apiDoc.inAPIParamDocs)<=0}">
													此服务没有入参信息
												</c:if>
												<c:if test="${fn:length(apiDoc.inAPIParamDocs)>=0}">
												<table class="table tree-1 table-bordered table-striped table-condensed">
									               	<thead>
														<tr>
														<th>参数名称</th>
														<th>参数类型</th>
														<th>参数说明</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="APIParamDoc" items="${apiDoc.inAPIParamDocs}" varStatus="varStatus">
										                <tr class="treegrid-<c:out value="${APIParamDoc.id}"/>" id="treegrid-<c:out value="${APIParamDoc.id}"/>" indexId="<c:out value="${APIParamDoc.id}"/>" <c:if test="${APIParamDoc.canUnfold==true}"> name="TR_IN_EXPANDER_NAME"</c:if>>
										                    <td><c:out value="${APIParamDoc.name}"/> <c:if test="${APIParamDoc.canUnfold==true}"><b><a href="javascript:void(0)">包含子属性</a></b> </c:if></td><td><c:out value="${APIParamDoc.className}"/></td><td><c:out value="${APIParamDoc.commentText}"/></td>
										                </tr>
										                </c:forEach>
									                </tbody>       
									            </table>
									            </c:if>	
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 入参结束 -->
							<!-- 出参开始 -->
							<div class="row">
								<div class="col-lg-12">
									<div class="main-box clearfix">
										<header class="main-box-header clearfix">
											<h2>服务返回参数规范</h2>
										</header>
										<div class="main-box-body clearfix">
											<div class="table-responsive">
												<c:if test="${apiDoc.returnAPIParamDoc==null}">
													此服务没有返回信息
												</c:if>
												<c:if test="${apiDoc.returnAPIParamDoc!=null}">
												<table class="table tree-2 table-bordered table-striped table-condensed">
									               	<thead>
														<tr>
														<th>参数名称</th>
														<th>参数类型</th>
														<th>参数说明</th>
														</tr>
													</thead>
													<tbody>
										                <tr class="treegrid-<c:out value="${apiDoc.returnAPIParamDoc.id}"/>" id="treegrid-<c:out value="${apiDoc.returnAPIParamDoc.id}"/>" indexId="<c:out value="${apiDoc.returnAPIParamDoc.id}"/>" <c:if test="${apiDoc.returnAPIParamDoc.canUnfold==true}"> name="TR_OUT_EXPANDER_NAME"</c:if>>
										                    <td><c:out value="${apiDoc.returnAPIParamDoc.name}"/> <c:if test="${apiDoc.returnAPIParamDoc.canUnfold==true}"><b><a href="javascript:void(0)">包含子属性</a></b> </c:if></td><td><c:out value="${apiDoc.returnAPIParamDoc.className}"/><c:if test="${apiDoc.returnAPIParamDoc.generic==true}">&lt;
										                    	<c:forEach var="genericAPIParamDoc" items="${apiDoc.returnAPIParamDoc.genericAPIParamDocs}" varStatus="varStatus">
										               				<c:if test="${genericAPIParamDoc.canUnfold==true}"><b><a href="javascript:void(0)"><c:out value="${genericAPIParamDoc.name}"/></a></b> </c:if>
										              			 </c:forEach>
										                    &gt;</c:if></td><td><c:out value="${apiDoc.returnAPIParamDoc.commentText}"/></td>
										                </tr>
									                </tbody>       
									            </table>
									            </c:if>	
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 入参结束 -->
							<!-- 签名异常 -->
							<div class="row">
								<div class="col-lg-12">
									<div class="main-box clearfix">
										<header class="main-box-header clearfix">
											<h2>签名异常信息</h2>
										</header>
										<div class="main-box-body clearfix">
											<div class="table-responsive">
												<c:out value="${apiDoc.exceptions}"/>
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
				this.expanderInActions = [];
				this.expanderOutActions = [];
				this.init();
			}

			$.extend($.PageController, {
				defaults : {
					DEFAULT_PAGE_SIZE: 10
				},
				prototype : {
					
					init: function(){
						this.bindEvents();
						this.renderAPIInParamTreeGrid();
						this.renderAPIOutParamTreeGrid();
					},
					
					renderAPIInParamTreeGrid: function(){
						var _this = this;
						$('.tree-1').treegrid({
		                    expanderExpandedClass: 'glyphicon glyphicon-minus',
		                    expanderCollapsedClass: 'glyphicon glyphicon-plus',
		                    onChange: function() {
		                        //alert("Changed: " + $(this).attr("class"));
		                    },
		                    onCollapse: function() {
		                        //alert("Collapsed " + $(this).attr("pid"));
		                    },
		                    onExpand: function() {
		                       // alert("Expanded: " + $(this).attr("class"));
		                    }
		                });
						$("[name='TR_IN_EXPANDER_NAME']").unbind("click").bind("click", function() {
							var pIndexId=$(this).attr("indexId");
							var expand = _this.checkParamExpanderAction(_this.expanderInActions,pIndexId);
							if(!expand){
								_this.getAPIInParamSubClassFields(pIndexId);
							}
						}); 
					},
					
					renderAPIOutParamTreeGrid: function(){
						var _this = this;
						$('.tree-2').treegrid({
		                    expanderExpandedClass: 'glyphicon glyphicon-minus',
		                    expanderCollapsedClass: 'glyphicon glyphicon-plus',
		                    onChange: function() {
		                        //alert("Changed: " + $(this).attr("class"));
		                    },
		                    onCollapse: function() {
		                        //alert("Collapsed " + $(this).attr("pid"));
		                    },
		                    onExpand: function() {
		                       // alert("Expanded: " + $(this).attr("class"));
		                    }
		                });
						$("[name='TR_OUT_EXPANDER_NAME']").unbind("click").bind("click", function() {
							var pIndexId=$(this).attr("indexId");
							var expand = _this.checkParamExpanderAction(_this.expanderOutActions,pIndexId);
							if(!expand){
								_this.getAPIOutParamSubClassFields(pIndexId);
							}
						}); 
					},
					
					checkParamExpanderAction: function(data,pIndexId){
						var _this = this;
						var array = $.grep(data,function(d,i){
							return d.pIndexId==pIndexId;
						});
						if(!array || array.length==0)return false;
						var d = array[0];
						return d.action;
					},

					bindEvents : function() {
						var _this = this;
						
					},
					getAPIInParamSubClassFields: function(pIndexId){
						var _this = this;
						ajaxController.ajax({
							method : "POST",
							url : _base + "/api/getSubClassFields?rnd="+ Math.random(),
							dataType : "json",
							data: {
								pIndexId:  pIndexId
							},
							showWait : true,
							message : "正在处理中，请稍候...",
							success : function(data) {
								var d = data.data;
								if(d && d.length!=0){
									var template = $.templates("#APIInParamSubClassFieldsImpl");
				                    var html = template.render(d?d:[]);
				                   	_this.appendRecord(pIndexId,html);
				                   	_this.renderAPIInParamTreeGrid();
								}
								_this.expanderInActions.push({pIndexId:pIndexId,action:true});
							}
						});
					},
					
					getAPIOutParamSubClassFields: function(pIndexId){
						var _this = this;
						ajaxController.ajax({
							method : "POST",
							url : _base + "/api/getSubClassFields?rnd="+ Math.random(),
							dataType : "json",
							data: {
								pIndexId:  pIndexId
							},
							showWait : true,
							message : "正在处理中，请稍候...",
							success : function(data) {
								var d = data.data;
								if(d && d.length!=0){
									var template = $.templates("#APIOutParamSubClassFieldsImpl");
				                    var html = template.render(d?d:[]);
				                   	_this.appendRecord(pIndexId,html);
				                   	_this.renderAPIOutParamTreeGrid();
								}
								_this.expanderOutActions.push({pIndexId:pIndexId,action:true});
							}
						});
					},
					
					appendRecord: function(pIndexId,html){
						//alert(html);
						$("#treegrid-"+pIndexId).after(html);
					}


				}

			});

		})(jQuery);
	</script> 
	
	<script id="APIInParamSubClassFieldsImpl" type="text/x-jsrender">
	<tr class="treegrid-{{:id}} treegrid-parent-{{:pid}}" id="treegrid-{{:id}}" pid="treegrid-parent-{{:pid}}" indexId="{{:id}}" {{if canUnfold==true}} name="TR_IN_EXPANDER_NAME" {{/if}} >
		<td>{{:paramName}} {{if canUnfold==true}}<b><a href="javascript:void(0)">包含子属性</a></b>{{/if}}</td><td>{{:className}}</td><td>{{:commentText}}</td>
	</tr>
	</script>
	
	<script id="APIOutParamSubClassFieldsImpl" type="text/x-jsrender">
	<tr class="treegrid-{{:id}} treegrid-parent-{{:pid}}" id="treegrid-{{:id}}" pid="treegrid-parent-{{:pid}}" indexId="{{:id}}" {{if canUnfold==true}} name="TR_OUT_EXPANDER_NAME" {{/if}} >
		<td>{{:paramName}} {{if canUnfold==true}}<b><a href="javascript:void(0)">包含子属性</a></b>{{/if}}</td><td>{{:className}}</td><td>{{:commentText}}</td>
	</tr>
	</script>
</body>
</html>

