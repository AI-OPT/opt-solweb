<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/jsp/common/common.jsp"%>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>服务搜索-服务在线管理</title>
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
										<li class="active"><span>服务搜索</span></li>
									</ol>
								</div>
							</div>
						</div>
					</div>


					<!-- aa -->

					<div class="row">
						<div class="col-lg-12">
							<div class="main-box clearfix">
								<header class="main-box-header clearfix" >
									<h2>
										<span class="emerald" id="HEADER_TOTAL_SPAN">搜索服务
										</span>
									</h2>
									<small class="gray"><c:if test="${not empty owner}">当前搜索的服务提供者:<font color="red"><c:out value="${owner}"/></font>   </c:if><c:if test="${limitException=='0'}"><font color="red">搜索范围为声明了异常的服务</font> </c:if><c:if test="${limitException=='1'}"><font color="red">搜索范围为没有声明异常的服务</font> </c:if>您可以输入服务接口的类，方法，服务编码，服务描述的关键字。例如："getSysParam"或者"获取字典参数"</small>
								</header>
								<div class="main-box-body clearfix">
									<div id="search-form">
										<div class="input-group">
											<input type="text" class="form-control input-lg"
												id="API_KEY" data-toggle="tooltip" data-placement="bottom" title="输入服务关键字，回车开始搜索" value="<c:out value="${keywords}"/>"/>
											<div class="input-group-btn">
												<button class="btn btn-lg btn-primary" type="button" id="BTN_SEARCH">
													<i class="fa fa-search"></i> Search
												</button>
											</div>
										</div>
									</div>
									<small class="gray" id="SEARCH_RESULT_TIPS"></small>
									<ul id="search-results">
										
									</ul>
									<div id="divPagination" class="row filter-block pull-right "></div>
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
				this.init();
			}

			$.extend($.PageController, {
				defaults : {
					DEFAULT_PAGE_SIZE: 10
				},
				prototype : {
					
					init: function(){
						this.bindEvents();
						this.searchAPIDocs(1,this.settings.DEFAULT_PAGE_SIZE);
					},

					bindEvents : function() {
						var _this = this;
						$("#BTN_SEARCH").bind("click", function() {
							_this.searchAPIDocs(1,_this.settings.DEFAULT_PAGE_SIZE);
						});
						$('#API_KEY').bind('keypress',function(event){
							if(event.keyCode == "13"){
								_this.searchAPIDocs(1,_this.settings.DEFAULT_PAGE_SIZE);
							}
						});
					},
					
					searchAPIDocs: function(pageNo,pageSize){
						var _this = this;
						var apiKey=$("#API_KEY").val();
						var queryCond = {
								keywords: $.trim(apiKey),
								owner: "<c:out value="${owner}"/>",
								ownerType: "<c:out value="${ownerType}"/>",
								limitException: "<c:out value="${limitException}"/>",
								pageInfo: {
									pageNo: pageNo,
									pageSize: pageSize
								}
						}
						
						ajaxController.ajax({
							method : "POST",
							url : _base + "/api/search?rnd="+ Math.random(),
							dataType : "json",
							data: {
								queryCond:  JSON.stringify(queryCond)
							},
							showWait : true,
							message : "正在为您搜索结果，请稍候...",
							success : function(data) {
								var d = data.data;
								if(d.result && d.result.length!=0){
									var template = $.templates("#SearchResultImpl");
				                    var htmlOutput = template.render(d.result?d.result:[]);
				                    $("#search-results").html(htmlOutput);
				                    _this.bindAPIDelete();
				                    var count = d.count;
				                     var pageCount= d.pageCount;
				                     var pageNo = d.pageNo;
				                     var pageSize= d.pageSize;
				                     _this.renderAPIDocsPagination(pageCount,pageNo,pageSize);
				                     $('#divPagination').show().bootstrapPaginator("show",pageNo);
				                     $("#SEARCH_RESULT_TIPS").html("为您找到相关结果约<font color='red'>"+count+"</font>个").show();
								}else{
									$("#search-results").html("没有搜索到相关信息");
				                     $('#divPagination').hide();
				                     $("#SEARCH_RESULT_TIPS").hide();
								}
								
			                    
							}
						});
					},
					
					renderAPIDocsPagination: function(totalPages,currentPage,pageSize){
						var _this = this;
						var options = {
							totalPages: totalPages,
						    currentPage: currentPage,
						    numberOfPages:8,
						    onPageClicked: function(e,originalEvent,type,page){
						    	e.stopImmediatePropagation();
						    	var currentTarget = $(e.currentTarget);
						    	var oldpages = currentTarget.bootstrapPaginator("getPages");
								_this.searchAPIDocs(page,pageSize);
						    }
						}
						$('#divPagination').bootstrapPaginator(options);
					},
					
					bindAPIDelete: function(){
						var _this = this;
						$("[name='HrefAPIDelete']").bind("click",function(){
							var indexId = $(this).attr("indexId");
							if(indexId==""){
								messageController.alert("索引为空，无法作废服务");
								return ;
							}
							messageController.confirm("确定要作废服务吗?",function(){
								_this.deleteAPI(indexId);
							});
						});
					},
					
					deleteAPI: function(indexId){
						var _this = this;
						ajaxController.ajax({
							method : "POST",
							url : _base + "/api/deleteAPI?rnd="+ Math.random(),
							dataType : "json",
							data: {
								indexId:  indexId
							},
							showWait : true,
							message : "正在将服务作废，请稍候...",
							success : function(data) {
								messageController.alert("服务作废成功",function(){
									_this.searchAPIDocs(1,_this.settings.DEFAULT_PAGE_SIZE);
								});
							}
						});
					}

				}

			});

		})(jQuery);
	</script> 
	<script id="SearchResultImpl" type="text/x-jsrender">
	<li>
		<h3 class="title">
			<a class="table-link" href="${_base}/api/apidetail.html?activemenu=m_api&owner={{:owner}}&ownerType={{:ownerType}}&interfaceName={{:interfaceName}}&methodName={{:methodName}}&version={{:version}}">
				<span>{{:interfaceNameHighlight}}</span>#<span>{{:methodNameHighlight}}</span>
			</a>
		</h3>
		<div class="clearfix">
			<div class="desc">{{:briefCommentHighlight}}</div>
			<div class="desc"><b>[API_CODE]:</b><font color="blue">{{:apiCodeHighlight}}</font></div>
			<div class="desc"><b>[签名异常]:</b><font color="red">{{:exceptions}}</font></div>
			<div class="desc"><b>[提供者]:</b><a href="${_base}/api/tosearch.html?owner={{:owner}}&ownerType={{:ownerType}}&activemenu=m_api"> <font color="blue">{{:ownerHighlight}}</font></a></div>
			<div class="desc"><b>[仓库组]:</b><font color="blue">{{:groupIdHighlight}}</font> <b>[构件名]:</b><font color="blue">{{:artifactIdHighlight}}</font> <b>[最新版本]:</b> <font color="blue"><a class="table-link" href="${_base}/api/apidetail.html?activemenu=m_api&owner={{:owner}}&ownerType={{:ownerType}}&interfaceName={{:interfaceName}}&methodName={{:methodName}}&version={{:version}}">{{:version}}</a> </font> </div>
			<div class="desc">

<div class="main-box clearfix">
	<div class="tabs-wrapper tabs-no-header">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab-gradle-{{:id}}" data-toggle="tab">Gradle</a></li>
			<li><a href="#tab-maven-{{:id}}" data-toggle="tab">Maven</a></li>
		</ul>
		<div class="tab-content tab-content-body link-title clearfix">
			<div class="tab-pane fade in active" id="tab-gradle-{{:id}}">
				<textarea rows="2" cols="100" readonly>{{:groupId}}:{{:artifactId}}:{{:version}}</textarea>
			</div>
			<div class="tab-pane fade" id="tab-maven-{{:id}}">
				<textarea rows="5" cols="100" readonly>
<dependency>
  <groupId>{{:groupId}}</groupId>
  <artifactId>{{:artifactId}}</artifactId>
  <version>{{:version}}</version>
</dependency>
				</textarea>
			</div>
		</div>
	</div>
</div>




			</div>
			<div class="desc"><b>[负责人]:</b><font color="blue">{{:authorHighlight}}</font></div>
			<div class="desc"><b>[REST支持]:</b><font color="red">{{if restSupported==true}} 支持 {{else}} 不支持 {{/if}}</font></div>
			{{if restSupported==true}}
			<div class="desc"><b>[REST地址]:</b><font color="blue">http://ip:port/xx/{{:restRelativeURLHighlight}}</font></div>
			{{/if}}
			<div class="desc"><b>[详细说明]:</b>{{:detailCommentHighlight}}</div>
			
			<div class="link-title">
				<footer class="story-footer"> 
					{{if isSetted==false}}
					<a href="${_base}/sandbox/apireqparamset.html?indexId={{:id}}&activemenu=m_api"> 
						<i class="fa fa-pencil"></i>   设置模板
					</a>
					{{else}}
					<a href="${_base}/sandbox/apireqparamset.html?indexId={{:id}}&activemenu=m_api"> 
						<i class="fa fa-pencil"></i>   修改模板
					</a>
					<a href="${_base}/sandbox/toMockTest.html?indexId={{:id}}&activemenu=m_api"> 
						<i class="fa fa-jsfiddle"></i> dubbo测试
					</a>
					{{if restSupported==true}}
					<a href="${_base}/sandbox/resttest.html?indexId={{:id}}&activemenu=m_api"> 
						<i class="fa fa-jsfiddle"></i> rest测试
					</a>
					{{/if}}
					{{/if}}

					<c:if test="${allowDelete=='ASIAINFORUNNER128UHXUYSHAMD5OPOQOUAJHDGH9787GDHSGAGG'}">
						<a href="javascript:void(0)" name="HrefAPIDelete" indexId="{{:id}}"> 
							<i class="fa fa-wrench"></i> 作废
						</a>
					</c:if>

					<a href="${_base}/api/downloadAPI?ownerType={{:ownerType}}&owner={{:owner}}&artifactId={{:artifactId}}&interfaceName={{:interfaceName}}&method={{:methodName}}&activemenu=m_api"> 
						<i class="fa fa-cloud-download"></i> 下载数据
					</a>


				</footer>
			</div>
		</div>
	</li>
	</script>
</body>
</html>

