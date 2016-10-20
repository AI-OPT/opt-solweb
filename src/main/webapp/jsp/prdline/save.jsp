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
<div id="content-wrapper"><!--右侧灰色背景-->
	<div class="row"><!--外围框架-->
		<div class="col-lg-12"><!--删格化-->
			<div class="row"><!--内侧框架-->
				<div class="col-lg-12"><!--删格化-->
					<div class="main-box clearfix"><!--白色背景-->
						<form id="prdLineForm" action="${_base}/prdline/save" method="post">
						<div id="addViewDiv" class="main-box-body clearfix">
                            <!-- 查询条件 -->
                            <div class="form-label bd-bottom ui-form" data-widget="validator">
                            	<input id="prdlineId" name="prdlineId" type="hidden" value="${prdlineId}">
								<ul>
					                <li class="col-md-12 ui-form-item">
					                    <p class="word"><span>*</span>产品线编码</p>
					                    <p><input name="prdlineCode" type="text" class="int-text int-medium" maxlength="30"
					                              required data-msg-required="产品线编码不能为空"  value="${prdlineCode}"></p>
					                </li>
					                <li class="col-md-12 ui-form-item">
										<p class="word"><span>*</span>产品线名称</p>
										<p><input  name="prdlineName" type="text" class="int-text int-medium"  maxlength="60"
												  required data-msg-required="产品线名称不能为空"  value="${prdlineName}"></p>
									</li>
					             </ul>
					             <ul>
					             	<li class="col-md-12 ui-form-item">
					             		<p class="word">行业类型</p>
					             		<p><input  name="industryCode" type="text" class="int-text int-medium"  maxlength="30"  value="${industryCode}"></p>
					            	</li>
					             </ul>
					             <ul>
					             	<li class="col-md-12 ui-form-item">
					             		<p class="word">负责人</p>
					             		<p><input  name="prdlineManager" type="text" class="int-text int-medium"  maxlength="30" value="${prdlineManager}"></p>
					            	</li>
					             </ul>
					             <ul>
					             	<li class="col-md-12 ui-form-item">
					             		<p class="word">产品线描述</p>
					             		<p><textarea name="prdlineRemark" class="int-text textarea-xlarge" maxlength="500" value="${prdlineRemark}"></textarea></p>
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
	<%@ include file="/jsp/common/foot.jsp"%>
</div>
</div>
</div>
</div>
</body>
<script type="text/javascript">
    var pager;
    (function () {
        seajs.use('app/jsp/prdline/save', function (prdlineAddPager) {
            pager = new prdlineAddPager({element: document.body});
            pager.render();
        });
    })();
</script>
</html>