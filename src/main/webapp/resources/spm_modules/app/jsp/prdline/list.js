define('app/jsp/prdline/list', function (require, exports, module) {
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
    
    
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    //定义页面组件类
    var prdlineListPager = Widget.extend({
    	
    	//属性，使用时由类的构造函数传入
    	attrs: {
    	},
    	Statics: {
    		DEFAULT_PAGE_SIZE: 10
    	},
    	//事件代理
    	events: {
    		"click #searchServiceBtn":"_selectServiceList"
        },
    	//重写父类
    	setup: function () {
    		prdlineListPager.superclass.setup.call(this);
    		this._selectServiceList();
    	},
    	//查询列表
    	_selectServiceList:function(srvCategoryId){
    		var _this = this;
    		var searchParams = $("#searchParams").val();
    		
    		$("#pagination-ul").runnerPagination({
    			
	 			url: _base+"/prdline/getPrdlineList",
	 			
	 			method: "POST",
	 			dataType: "json",
	 			renderId:"searchPrdlineData",
	 			messageId:"showMessageDiv",
	 			data: {"searchParams":searchParams},
	           	pageSize: prdlineListPager.DEFAULT_PAGE_SIZE,
	           	visiblePages:5,
	           	callback: function (data) {
	            	if(data && data.result && data.result.length>0){
	            		var template = $.templates("#searchPrdlineTemple");
	            	    var htmlOutput = template.render(data);
	            	    $("#searchPrdlineData").html(htmlOutput);
	            	}
	            }
    		});
    	},
    	/**
    	 * 删除询问
    	 * @param prdlineId
    	 * @returns
    	 */
    	_deleteDialog:function(prdlineId){
    		var _this = this;
    		var d = Dialog({
				title:"提示",
				content:"确定删除该条数据？",
				icon:'help',
				okValue: '确 定',
				ok:function(){
					this.close();
					_this._deletePrdline(prdlineId);
				},
				cancelValue:'取消',
				cancel:function(){
					this.close();
				}
			});
			d.show();
    	},
    	/**
    	 * 删除操作
    	 */
    	_deletePrdline:function(prdlineId){
    		var _this = this;
    		ajaxController.ajax({
				type: "post",
				processing: true,
				message: "保存中，请等待...",
				url: _base+"/prdline/delete",
				data:{"prdlineId":prdlineId},
				success: function(data){
					if("1"===data.statusCode){
						_this._selectServiceList();
					}
				}
			});
    	},
    	/**
    	 * 显示版本信息
    	 */
    	_showVersionInfo:function(prdlineId){
    		var innerHtml="<div class='eject-large-paging' id='large1'>"
    			+"				<div class='eject-large-list'>"
    			+"					<div class='row'>"
    			+"					<p class='right pr-30'>"
    			+"						<input type='button' class='biu-btn  btn-primary btn-blue btn-auto  ml-5' value='添加版本' onclick='pager._addVersionDialog(\""+prdlineId+"\")'>"
    			+"					</p>"
    			+"					</div>"
    			+"		           <div class='table table-border table-bordered table-bg table-hover mt-10'>"
    			+"		                  <table width='550px' border='0'>"
    			+"							<thead>"
    			+"								<tr class='bj'>"
    			+"								  	<th>产品线名称</th>"                                                                                                               
    			+"									<th>产品线版本</th>"
    			+"									<th>版本时间</th>"
    			+"								</tr>"  
    			+"							</thead>"
    			+"		                    <tbody id='versionData'>"                                                                                                                                         
    			+"		                    </tbody>"
    			+"						</table>"
    			+"                      <div id='versionMessageDiv'></div>"
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
    		
    		this._renderVersionData(prdlineId);
    	},
    	
    	_renderVersionData:function(prdlineId){
    		$("#version-edit-pagination").runnerPagination({
    			
	 			url: _base+"/prdline/getVersionList",
	 			
	 			method: "POST",
	 			dataType: "json",
	 			renderId:"versionData",
	 			messageId:"versionMessageDiv",
	 			data: {"prdlineId":prdlineId},
	           	pageSize: 5,
	           	visiblePages:5,
	            render: function (data) {
	            	if(data != null && data != 'undefined' && data.length>0){
	            		var template = $.templates("#versionTemple");
	            	    var htmlOutput = template.render(data);
	            	    $("#versionData").html(htmlOutput);
	            	}
	            }
    		});
    	},
    	
    	/**
    	 * 新增版本
    	 */
    	_addVersionDialog:function(prdlineId){
    		var _this = this;
    		var innerHtml="<form id='addVersionForm' method='post'>"
						+"	<div id='addVersionDiv' class='main-box-body clearfix'>"
						+"    <div class='form-label bd-bottom ui-form' data-widget='validator'>"
						+"   	<input id='prdlineId' name='prdlineId' type='hidden' value='"+prdlineId+"'>"
						+"        <ul>"
						+"        	<li class='col-md-12 ui-form-item'>"
						+"         		<p class='word'><span>*</span>服务版本</p>"
						+"         		<p><input id='prdlineVersion' name='prdlineVersion' type='text' class='int-text int-medium'  maxlength='120' required data-msg-required='服务版本不能为空'></p>"
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
						+"         		<p><textarea id='prdlineRemark' name='prdlineRemark' class='int-text textarea-xlarge' style='width:190px' maxlength='500'></textarea></p>"
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
					var isOk = _this._addVersion(prdlineId);
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
    	/**
    	 * 新增版本
    	 */
    	_addVersion:function(prdlineId){
    		var _this = this;
    		var validateForm = $("#addVersionForm").validate();
			if(!validateForm.form()){
				return false;
			}
			ajaxController.ajax({
				type: "post",
				processing: true,
				message: "保存中，请等待...",
				url: _base+"/prdline/addVersion",
				data:$('#addVersionForm').serializeArray(),
				success: function(data){
					if("1"===data.statusCode){
						_this._renderVersionData(prdlineId);
					}
				}
			});
			return true;
    	}
    });
    
    module.exports = prdlineListPager;
});

