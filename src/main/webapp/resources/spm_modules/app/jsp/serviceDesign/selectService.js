define('app/jsp/serviceDesign/selectService', function (require, exports, module) {
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
    		DEFAULT_PAGE_SIZE: 5
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
    		$("#select-service-pagination").runnerPagination({
    			
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
    	}
    	
    });
    
    module.exports = servicelistPager
});

