<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>服务设计管理</title>
	<%@ include file="/inc/inc.jsp" %>
</head>

<body
	class="theme-whbl  pace-done fixed-header fixed-leftmenu fixed-footer">
	<div id="theme-wrapper">

		<%@ include file="/jsp/common/head.jsp"%>


		<div id="page-wrapper" class="container">
			<div class="row">
				<%@ include file="/jsp/common/leftmenu.jsp"%>
<div id="content-wrapper"><!--右侧灰色背景-->
	<div class="row"><!--外围框架-->
		<div class="col-lg-12"><!--删格化-->
			<div class="row"><!--内侧框架-->
				<div class="col-lg-12"><!--删格化-->
					<div class="main-box clearfix"><!--白色背景-->
						<form id="serviceForm" action="${_base}/service/save" method="post">
						<div id="addViewDiv" class="main-box-body clearfix">
                            <!-- 查询条件 -->
                            <div class="form-label bd-bottom ui-form" data-widget="validator">
								<ul>
					                <li class="col-md-12 ui-form-item">
					                    <p class="word"><span>*</span>选择服务</p>
					                    <p><input id="srvApiId" name="srvApiId" type="text" class="int-text int-medium"
					                              required data-msg-required="服务不能为空"  value="${srvApiId}" <c:if test="${isEdit}">readonly</c:if>/></p>
					                </li>
					                <li class="col-md-12 ui-form-item">
										<p class="word">服务名称</p>
										<p>
											<input name="srvApiName" type="text" class="int-text int-medium" value="${srvApiName}" readonly>
										</p>
									</li>
					             </ul>
					             <ul>
					             	<li class="col-md-12 ui-form-item">
					             		<p class="word">服务入参</p>
					             		<p class="right pr-30">
                                    		<input type="button" class="biu-btn  btn-primary btn-blue btn-auto  ml-5"
                                           	value="新  增" onclick="pager._addInputData()">
                                		</p>
					             		<p>
					             			<div class="main-box-body clearfix">
												<!--table表格-->
												<div class="table-responsive clearfix">
													<table class="table table-hover table-border table-bordered">
														<thead>
														<tr>
															<th>参数名称</th>
															<th>父级参数</th>
															<th>必填</th>
															<th>操作</th>
														</tr>
														</thead>
														<tbody id="inputParamsData">
															<c:forEach var="inputParams" items="${inputParamList}">
																<tr id="inputParams_${inputParams.inputId}">
																	<td id="inputName_${inputParams.inputId}">${inputParams.inputName}</td>
																	<td id="parentInputName_${inputParams.inputId}">${inputParams.parentInputName}</td>
																	<td id="isRequired_${inputParams.inputId}">${inputParams.isRequired}</td>
							                                        <td>
																		<a href="javaScript:void(0)" onclick="pager._modifyInputData('${inputParams.inputId}','${inputParams.inputName}','${inputParams.parentInputName}','${inputParams.isRequired}')" class="blue-border">修改</a>
																		<a href="javaScript:void(0)" onclick="pager._deleteInputData('${inputParams.inputId}')" class="blue-border">删除</a>
																	</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
													<%--<div id="showMessageDiv"></div>
													<script id="inputParamsTemple" type="text/template">
									{{for inputParamList}}
									<tr id="inputParams_{{:inputId}}">
										<td id="inputName_{{:inputId}}">{{:inputName}}</td>
										<td id="parentInputName_{{:inputId}}">{{:parentInputName}}</td>
										<td id="isRequired_{{:inputId}}">{{:isRequired}}</td>
                                        <td>
											<a href="" onclick="pager._modifyInputData('{{:inputId}}','{{:inputName}}',':{{:parentInputName}}','{{:isRequired}}')" class="blue-border">修改</a>
											<a href="${_base}/serviceDesign/inputParams/delete?inputId='{{:inputId}}'" class="blue-border">删除</a>
										</td>
									</tr>
									{{/for}}
								</script> --%>
												</div>
											 </div>
					             		</p>
					            	</li>
					             </ul>
					             <ul>
					             	<li class="col-md-12 ui-form-item">
					             		<p class="word">服务出参</p>
					             		<p class="right pr-30">
                                    		<input type="button" class="biu-btn  btn-primary btn-blue btn-auto  ml-5"
                                           	value="新  增" onclick="pager._addOutputData()">
                                		</p>
					             		<p>
					             		<div class="main-box-body clearfix">
												<!--table表格-->
												<div class="table-responsive clearfix">
													<table class="table table-hover table-border table-bordered">
														<thead>
														<tr>
															<th>参数名称</th>
															<th>父级参数</th>
															<th>操作</th>
														</tr>
														</thead>
														<tbody id="outputParamsData">
															<c:forEach var="outputParams" items="${outputParamList}">
																<tr id="outputParams_${outputParams.outputId}">
																	<td id="outputName_${outputParams.outputId}">${outputParams.outputName}</td>
																	<td id="parentOutputName_${outputParams.outputId}">${outputParams.parentOutputName}</td>
							                                        <td>
																		<a href="javaScript:void(0)" onclick="pager._modifyOutputData('${outputParams.outputId}','${outputParams.outputName}','${outputParams.parentOutputName}')" class="blue-border">修改</a>
																		<a href="javaScript:void(0)" onclick="pager._deleteOutputData('${outputParams.outputId}')" class="blue-border">删除</a>
																	</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
													<%-- <div id="showMessageDiv"></div>
													<script id="searchProdRouteTemple" type="text/template">
									{{for result ~pageNo=pageNo ~pageSize=pageSize}}
									<tr>
										<td>{{:supplyId}}</td>
										<td>
											<div class="hind1 text-l pl-15">
                                                <div class="center-hind" >{{:supplyName}}</div>
                                                <div class="showbj"><i class="fa fa-posi fa-caret-up"></i>{{:supplyName}}</div>
                                            </div>
										</td>
                                        <td class="text-l">
											<a href="${_base}/serviceDesign/outputParams/modify?outputId='{{:outputId}}'" class="blue-border">修改</a>
											<a href="${_base}/serviceDesign/outputParams/delete?outputId='{{:outputId}}'" class="blue-border">删除</a>
										</td>
									</tr>
									{{/for}}
								</script>--%>
												</div>
											 </div>
										</p>
					            	</li>
					             </ul>
                            </div> 
                            <div  class="row pt-30">
                            	<p class="center pr-30 mt-30">
                            		<input id="submitSaveBtn" type="button" class="biu-btn  btn-primary  btn-small  ml-5"
                                           value="提  交">
                                    <input id="cancelBtn" type="button" class="biu-btn  btn-primary  btn-small  ml-5"
                                           value="取  消">
                            	</p>
                            </div>
                            
						</div>
						</form>
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
    var inputAddCount = 0;
    var inputModifyArray = [];
    var inputDeleteArray = [];
    var outputAddCount = 0;
    var outputModifyArray = [];
    var outputDeleteArray = [];
    (function () {
        seajs.use('app/jsp/serviceDesign/save', function (serviceSavePager) {
            pager = new serviceSavePager({element: document.body});
            pager.render();
        });
    })(); 
</script>
</html>