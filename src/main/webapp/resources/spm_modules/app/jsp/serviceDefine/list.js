define('app/jsp/serviceDefine/list', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
	    Widget = require('arale-widget/1.2.0/widget'),
	    Dialog = require("optDialog/src/dialog"),
	    AjaxController = require('opt-ajax/1.0.0/index');
    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("my97DatePicker/WdatePicker");
    require("bootstrap-paginator/bootstrap-paginator.min");
    require("app/util/jsviews-ext");
    require("opt-paging/aiopt.pagination");
    
    require("jquery-validation/1.15.1/jquery.validate");
    require("app/util/aiopt-validate-ext");
    
    var SendMessageUtil = require("app/util/sendMessage");
    
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    var clickId = "";
    //定义页面组件类
    var servicelistPager = Widget.extend({
    	
    	Implements:SendMessageUtil,
    	//属性，使用时由类的构造函数传入
    	attrs: {
    		clickId:""
    	},
    	Statics: {
    		DEFAULT_PAGE_SIZE: 10
    	},
    	//事件代理
    	events: {
    		//查询在售商品
            "click #searchServiceBtn":"_selectServiceList"
            },
    	//重写父类
    	setup: function () {
    		servicelistPager.superclass.setup.call(this);
    		this._selectServiceList();
    	},
    	//查询列表
    	_selectServiceList:function(){
    		var _this = this;
    		var searchParams = $("#searchParams").val();
    		var srvCategoryId = $("#categoryId").val();
    		$("#pagination-ul").runnerPagination({
    			
	 			url: _base+"/serviceDefine/getServiceList",
	 			
	 			method: "POST",
	 			dataType: "json",
	 			renderId:"searchServiceData",
	 			messageId:"showMessageDiv",
	 			data: {"searchParams":searchParams,
	 				"srvCategoryId":srvCategoryId
		 			},
	           	pageSize: servicelistPager.DEFAULT_PAGE_SIZE,
	           	visiblePages:5,
	           	callback: function (data) {
	            	if(data && data.result && data.result.length>0){
	            		$("#serviceCount").html(data.count);
	            		var template = $.templates("#searchServiceTemple");
	            	    var htmlOutput = template.render(data);
	            	    $("#searchServiceData").html(htmlOutput);
	            	}
	            }
    		});
    	},
    	/**
    	 * 显示产品线
    	 */
    	_showPrdlineInfo:function(srvApiId){
    		var innerHtml="<div class='eject-large-paging' id='large1'>"
    			+"			<div class='eject-large-list'>"
    			+"		           <div class='table table-border table-bordered table-bg table-hover mt-10'>"
    			+"		                  <table width='550px' border='0'>"
    			+"							<thead>"
    			+"								<tr class='bj'>"
    			+"								  	<th>产品线名称</th>"                                                                                                               
    			+"									<th>产品线版本</th>"
    			+"									<th>服务版本</th>"
    			+"								</tr>"  
    			+"							</thead>"
    			+"		                    <tbody id='showPrdlineData'>"                                                                                                                                         
    			+"		                    </tbody>"
    			+"						</table>"
    			+"                      <div id='showPrdlineMessageDiv'></div>"
    			+"               </div>"
    			+"			</div>"
    			+"	    <div class='paging'>"
    			+"			<ul id='prdline-show-pagination'>"
    			+"			</ul>"
    			+"		</div>"  		
    			+"		</div>";
    		var d = Dialog({
    			title:"产品标签",
    			width:"600px",
    			height:"450px",
    			closeIconShow:true,
    			innerHtml:innerHtml
    		});
    		d.show();
    		
    		$("#prdline-show-pagination").runnerPagination({
    			
	 			url: _base+"/serviceDefine/getPrdlineList",
	 			
	 			method: "POST",
	 			dataType: "json",
	 			renderId:"showPrdlineData",
	 			messageId:"showPrdlineMessageDiv",
	 			data: {"srvApiId":srvApiId},
	           	pageSize: 5,
	           	visiblePages:5,
	            render: function (data) {
	            	if(data != null && data != 'undefined' && data.length>0){
	            		var template = $.templates("#showPrdlineTemple");
	            	    var htmlOutput = template.render(data);
	            	    $("#showPrdlineData").html(htmlOutput);
	            	}
	            }
    		});
    	},
    	
    	/**
    	 * 显示编辑产品线列表
    	 */
    	_editPrdlineInfo:function(srvApiId){
    		var innerHtml="<div class='eject-large-paging' id='large1'>"
    			+"			<div class='eject-large-list'>"
    			+"					<div class='row'>"
    			+"					<p class='right pr-30'>"
    			+"						<input type='button' class='biu-btn  btn-primary btn-blue btn-auto  ml-5' value='添加产品线' onclick='pager._editPrdlineDialog(\""+srvApiId+"\")'>"
    			+"					</p>"
    			+"					</div>"
    			+"		           <div class='table table-border table-bordered table-bg table-hover mt-10'>"
    			+"		                  <table width='650px' border='0'>"
    			+"							<thead>"
    			+"								<tr class='bj'>"
    			+"								  	<th>产品线编码</th>" 
    			+"								  	<th>产品线名称</th>"                                                                                                               
    			+"									<th>产品线版本</th>"
    			+"									<th>服务版本</th>"
    			+"									<th>负责人</th>"
    			+"									<th>操作</th>"
    			+"								</tr>"  
    			+"							</thead>"
    			+"		                    <tbody id='editPrdlineData'>"                                                                                                                                         
    			+"		                    </tbody>"
    			+"						</table>"
    			+"                      <div id='editPrdlineMessageDiv'></div>"
    			+"               </div>"
    			+"			</div>"
    			+"	    <div class='paging'>"
    			+"			<ul id='prdline-edit-pagination'>"
    			+"			</ul>"
    			+"		</div>"  		
    			+"		</div>";
    		var d = Dialog({
    			title:"产品标签",
    			width:"700px",
    			height:"500px",
    			closeIconShow:true,
    			innerHtml:innerHtml
    		});
    		d.show();
    		this._renderPrdlineData(srvApiId);
    		
    	},
    	_renderPrdlineData:function(srvApiId){
    		$("#prdline-edit-pagination").runnerPagination({
    			
	 			url: _base+"/serviceDefine/getPrdlineList",
	 			
	 			method: "POST",
	 			dataType: "json",
	 			renderId:"editPrdlineData",
	 			messageId:"editPrdlineMessageDiv",
	 			data: {"srvApiId":srvApiId},
	           	pageSize: 5,
	           	visiblePages:5,
	            render: function (data) {
	            	if(data != null && data != 'undefined' && data.length>0){
	            		var template = $.templates("#editPrdlineTemple");
	            	    var htmlOutput = template.render(data);
	            	    $("#editPrdlineData").html(htmlOutput);
	            	}
	            }
    		});
    	},
    	/**
    	 * 编辑产品线对话框
    	 */
    	_editPrdlineDialog:function(srvApiId,srvPrdlineId){
    		var _this = this;
    		var innerHtml="<form id='addPrdlineForm' method='post'>"
						+"	<div id='addPrdlineDiv' class='main-box-body clearfix'>"
						+"    <div class='form-label bd-bottom ui-form' data-widget='validator'>"
						+"   	<input id='srvApiId' name='srvApiId' type='hidden' value='"+srvApiId+"'>"
						+"   	<input id='srvPrdlineId' name='srvPrdlineId' type='hidden' value='"+srvPrdlineId+"'>"
						+"        <ul>"
						+"        	<li class='col-md-12 ui-form-item'>"
						+"         		<p class='word'><span>*</span>产品线编码</p>"
						+"         		<p><input id='prdlineCode' name='prdlineCode' type='text' class='int-text int-medium'  maxlength='120' readonly required data-msg-required='服务版本不能为空' onclick='pager._selectPrdlineDialog('"+srvPrdlineId+"')'></p>"
						+"        	</li>"
						+"         </ul>"
						+"		 <ul>"
						+"         	<li class='col-md-12 ui-form-item'>"
						+"         		<p class='word'>产品线名称</p>"
						+"         		<p><input id='prdlineName' name='prdlineName' type='text' class='int-text int-medium' readonly></p>"
						+"       	</li>"
						+"         </ul>"
						+"         <ul>"
						+"         	<li class='col-md-12 ui-form-item'>"
						+"        		<p class='word'><span>*</span>产品线版本</p>"
						+"         		<p><select name='prdlineVersionId' id='prdlineVersionId' class='select select-small'>"
						+"					<option value=''>--请选择--</option>"					
						+"				</select></p>"
						+"       	</li>"
						+"        </ul>"
						+"        <ul>"
						+"         	<li class='col-md-12 ui-form-item'>"
						+"        		<p class='word'><span>*</span>服务版本</p>"
						+"         		<p><select name='srvVersionId' id='srvVersionId' class='select select-small'>"
						+"					<option value=''>--请选择--</option>"					
						+"				</select></p>"
						+"       	</li>"
						+"        </ul>"
						+"        <ul>"
						+"         	<li class='col-md-12 ui-form-item'>"
						+"        		<p class='word'><span>*</span>负责人</p>"
						+"         		<p><input id='prdlineManager' name='prdlineManager' type='text' class='int-text int-medium' required data-msg-required='负责人不能为空'></p>"
						+"       	</li>"
						+"        </ul>"
						+"    </div> "
						+"</div>"
						+"</form>";
    		var d = Dialog({
    			title:"新增版本",
    			width:"550px",
    			height:"450px",
    			closeIconShow:true,
    			innerHtml:innerHtml,
    			okValue: '确 定',
				ok:function(){
					var isOk = false;
					if(srvPrdlineId == null){
						isOk = _this._addPrdline(srvApiId);
					}else{
						isOk = _this._modifyPrdline(srvApiId);
					}
					if(!isOk){
						return false;
					}
				},
				cancelValue:'取消',
				cancel:function(){
					this.close();
				}
    		});
    		d.show();
    		
    		if(srvPrdlineId != null){
    			ajaxController.ajax({
    				type: "post",
    				processing: true,
    				message: "保存中，请等待...",
    				url: _base+"/serviceDefine/getPrdline",
    				data:{"srvPrdlineId":srvPrdlineId},
    				success: function(data){
    					if(data.data){
    						$("#prdlineCode").val(data.data.prdlineCode);
    						$("#prdlineName").val(data.data.prdlineName);
    						$("#prdlineManager").val(data.data.prdlineManager);
    						var prdlineVersionId = data.data.prdlineVersionId;
    						_this._inintPrdlineVersionSelect(srvPrdlineId,prdlineVersionId);
    						var serviceVersionId = data.data.srvVersionId;
    						_this._inintServiceVersionSelect(srvApiId,serviceVersionId);
    					}
    				}
    			});
    		}else{
    			_this._inintPrdlineVersionSelect(srvPrdlineId,"");
    			_this._inintServiceVersionSelect(srvApiId,"");
    		}
    	},
    	
    	/**
    	 * 初始化产品线版本下拉框
    	 */
    	_inintPrdlineVersionSelect(srvPrdlineId,prdlineVersionId){
    		ajaxController.ajax({
				type: "post",
				processing: true,
				message: "保存中，请等待...",
				url: _base+"/serviceDefine/getSelectPrdlineVersionList",
				data:{"srvPrdlineId":srvPrdlineId},
				success: function(data){
					if("1"===data.statusCode && data.data && data.data.length>0){
						for(var i=0;i<data.data.length;i++){
							if(data.data[i].prdlineVersionId == prdlineVersionId){
								var varItem = new Option(data.data[i].prdlineVersion, data.data[i].prdlineVersionId,true,true); 
								document.getElementById("prdlineVersionId").options.add(varItem);
							}else{
								var varItem = new Option(data.data[i].prdlineVersion, data.data[i].prdlineVersionId); 
								document.getElementById("prdlineVersionId").options.add(varItem);
							}
						}
					}
				}
			});
    	},
    	
    	/**
    	 * 初始化服务版本下拉框
    	 */
    	_inintServiceVersionSelect(srvApiId,serviceVersionId){
    		ajaxController.ajax({
				type: "post",
				processing: true,
				message: "保存中，请等待...",
				url: _base+"/serviceDefine/getSelectServiceVersionList",
				data:{"srvApiId":srvApiId},
				success: function(data){
					if("1"===data.statusCode && data.data && data.data.length>0){
						for(var i=0;i<data.data.length;i++){
							if(data.data[i].srvVersionId == serviceVersionId){
								var varItem = new Option(data.data[i].srvVersion, data.data[i].srvVersionId,true,true); 
								document.getElementById("srvVersionId").options.add(varItem);
							}else{
								var varItem = new Option(data.data[i].srvVersion, data.data[i].srvVersionId); 
								document.getElementById("srvVersionId").options.add(varItem);
							}
						}
					}
				}
			});
    	},
    	
    	/**
    	 * 查询产品线
    	 */
    	_selectPrdlineDialog:function(srvPrdlineId){
    		if(srvPrdlineId != null){
    			return;
    		}
    		
    	},
    	
    	/**
    	 * 新增产品线
    	 */
    	_addPrdline:function(srvApiId){
    		var _this = this;
    		var validateForm = $("#addPrdlineForm").validate();
			if(!validateForm.form()){
				return false;
			}
			ajaxController.ajax({
				type: "post",
				processing: true,
				message: "保存中，请等待...",
				url: _base+"/serviceDefine/addPrdline",
				data:$('#addPrdlineForm').serializeArray(),
				success: function(data){
					if("1"===data.statusCode){
						_this._renderPrdlineData(srvApiId);
					}
				}
			});
			return true;
    	},
    	/**
    	 * 修改产品线
    	 */
    	_modifyPrdline:function(srvPrdlineId){
    		var _this = this;
    		var validateForm = $("#addPrdlineForm").validate();
			if(!validateForm.form()){
				return false;
			}
			ajaxController.ajax({
				type: "post",
				processing: true,
				message: "保存中，请等待...",
				url: _base+"/serviceDefine/modifyPrdline",
				data:$('#addPrdlineForm').serializeArray(),
				success: function(data){
					if("1"===data.statusCode){
						_this._renderPrdlineData(srvApiId);
					}
				}
			});
			return true;
    	},
    	
    	/**
    	 * 显示版本信息
    	 */
    	_showVersionInfo:function(srvApiId){
    		var innerHtml="<div class='eject-large-paging' id='large1'>"
    			+"			<div class='eject-large-list'>"
    			+"		           <div class='table table-border table-bordered table-bg table-hover mt-10'>"
    			+"		                  <table width='550px' border='0'>"
    			+"							<thead>"
    			+"								<tr class='bj'>"
    			+"								  	<th>服务版本</th>"                                                                                                               
    			+"									<th>版本时间</th>"
    			+"									<th>版本修改摘要</th>"
    			+"								</tr>"  
    			+"							</thead>"
    			+"		                    <tbody id='showVersionData'>"                                                                                                                                         
    			+"		                    </tbody>"
    			+"						</table>"
    			+"                      <div id='showVersionMessageDiv'></div>"
    			+"               </div>"
    			+"			</div>"
    			+"	    <div class='paging'>"
    			+"			<ul id='version-show-pagination'>"
    			+"			</ul>"
    			+"		</div>"  		
    			+"		</div>";
    		var d = Dialog({
    			title:"服务版本",
    			width:"600px",
    			height:"450px",
    			closeIconShow:true,
    			innerHtml:innerHtml
    		});
    		d.show();
    		
    		$("#version-show-pagination").runnerPagination({
    			
	 			url: _base+"/serviceDefine/getVersionList",
	 			
	 			method: "POST",
	 			dataType: "json",
	 			renderId:"showVersionData",
	 			messageId:"showVersionMessageDiv",
	 			data: {"srvApiId":srvApiId},
	           	pageSize: 5,
	           	visiblePages:5,
	            render: function (data) {
	            	if(data != null && data != 'undefined' && data.length>0){
	            		var template = $.templates("#showVersionTemple");
	            	    var htmlOutput = template.render(data);
	            	    $("#showVersionData").html(htmlOutput);
	            	}
	            }
    		});
    	},
    	/**
    	 * 显示编辑版本信息
    	 */
    	_editVersionInfo:function(srvApiId){
    		var innerHtml="<div class='eject-large-paging' id='large1'>"
    			+"				<div class='eject-large-list'>"
    			+"					<div class='row'>"
    			+"					<p class='right pr-30'>"
    			+"						<input type='button' class='biu-btn  btn-primary btn-blue btn-auto  ml-5' value='添加版本' onclick='pager._addVersionDialog(\""+srvApiId+"\")'>"
    			+"					</p>"
    			+"					</div>"
    			+"		           <div class='table table-border table-bordered table-bg table-hover mt-10'>"
    			+"		                  <table width='550px' border='0'>"
    			+"							<thead>"
    			+"								<tr class='bj'>"
    			+"								  	<th>服务版本</th>"                                                                                                               
    			+"									<th>版本时间</th>"
    			+"									<th>版本修改摘要</th>"
    			+"								</tr>"  
    			+"							</thead>"
    			+"		                    <tbody id='editVersionData'>"                                                                                                                                         
    			+"		                    </tbody>"
    			+"						</table>"
    			+"                      <div id='editVersionMessageDiv'></div>"
    			+"               </div>"
    			+"			</div>"
    			+"	    <div class='paging'>"
    			+"			<ul id='version-edit-pagination'>"
    			+"			</ul>"
    			+"		</div>"  		
    			+"		</div>";
    		var d = Dialog({
    			title:"服务版本",
    			width:"600px",
    			height:"450px",
    			closeIconShow:true,
    			innerHtml:innerHtml
    		});
    		d.show();
    		
    		this._renderVersionData(srvApiId);
    	},
    	
    	_renderVersionData:function(srvApiId){
    		$("#version-edit-pagination").runnerPagination({
    			
	 			url: _base+"/serviceDefine/getVersionList",
	 			
	 			method: "POST",
	 			dataType: "json",
	 			renderId:"editVersionData",
	 			messageId:"editVersionMessageDiv",
	 			data: {"srvApiId":srvApiId},
	           	pageSize: 5,
	           	visiblePages:5,
	            render: function (data) {
	            	if(data != null && data != 'undefined' && data.length>0){
	            		var template = $.templates("#editVersionTemple");
	            	    var htmlOutput = template.render(data);
	            	    $("#editVersionData").html(htmlOutput);
	            	}
	            }
    		});
    	},
    	
    	/**
    	 * 新增版本
    	 */
    	_addVersionDialog:function(srvApiId){
    		var _this = this;
    		var innerHtml="<form id='addVersionForm' method='post'>"
						+"	<div id='addVersionDiv' class='main-box-body clearfix'>"
						+"    <div class='form-label bd-bottom ui-form' data-widget='validator'>"
						+"   	<input id='srvApiId' name='srvApiId' type='hidden' value='"+srvApiId+"'>"
						+"        <ul>"
						+"        	<li class='col-md-12 ui-form-item'>"
						+"         		<p class='word'><span>*</span>服务版本</p>"
						+"         		<p><input id='srvVersion' name='srvVersion' type='text' class='int-text int-medium'  maxlength='120' required data-msg-required='服务版本不能为空'></p>"
						+"        	</li>"
						+"         </ul>"
						+"		 <ul>"
						+"         	<li class='col-md-12 ui-form-item'>"
						+"         		<p class='word'><span>*</span>版本时间</p>"
						+"         		<p><input id='createTime' name='createTime' type='text' class='int-text int-medium'  maxlength='120' required data-msg-required='版本时间不能为空'></p>"
						+"       	</li>"
						+"         </ul>"
						+"         <ul>"
						+"         	<li class='col-md-12 ui-form-item'>"
						+"        		<p class='word'>版本修改摘要</p>"
						+"         		<p><textarea id='versionRemark' name='versionRemark' class='int-text textarea-xlarge' style='width:190px' maxlength='500'></textarea></p>"
						+"       	</li>"
						+"        </ul>"
						+"    </div> "
						+"</div>"
						+"</form>";
    		var d = Dialog({
    			title:"新增版本",
    			width:"550px",
    			height:"400px",
    			closeIconShow:true,
    			innerHtml:innerHtml,
    			okValue: '确 定',
				ok:function(){
					var isOk = _this._addVersion(srvApiId);
					if(!isOk){
						return false;
					}
				},
				cancelValue:'取消',
				cancel:function(){
					this.close();
				}
    		});
    		d.show();
    	},
    	
    	_addVersion:function(srvApiId){
    		var _this = this;
    		var validateForm = $("#addVersionForm").validate();
			if(!validateForm.form()){
				return false;
			}
			ajaxController.ajax({
				type: "post",
				processing: true,
				message: "保存中，请等待...",
				url: _base+"/serviceDefine/addVersion",
				data:$('#addVersionForm').serializeArray(),
				success: function(data){
					if("1"===data.statusCode){
						_this._renderVersionData(srvApiId);
//						var d = Dialog({
//							title:"提示",
//							content:"保存成功",
//							icon:'success',
//							cancelValue: '确 定',
//							cancel:function(){
//								this.close();
//								
//							}
//						});
//						d.show();
					}
				}
			});
    	}
    	
    });
    
    module.exports = servicelistPager
});

