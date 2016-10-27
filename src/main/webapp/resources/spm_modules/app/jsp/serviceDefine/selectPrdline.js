define('app/jsp/serviceDefine/selectPrdline', function (require, exports, module) {
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
    	//事件代理
    	events: {
    		"click #searchPrdlineBtn":"_selectPrdlineList"
        },
    	//重写父类
    	setup: function () {
    		prdlineListPager.superclass.setup.call(this);
    		this._selectPrdlineList();
    	},
    	//查询列表
    	_selectPrdlineList:function(){
    		var _this = this;
    		var searchParams = $("#searchParams").val();
    		
    		$("#selsect-prdline-pagination").runnerPagination({
    			
	 			url: _base+"/prdline/getPrdlineList",
	 			
	 			method: "POST",
	 			dataType: "json",
	 			renderId:"searchPrdlineData",
	 			messageId:"showMessageDiv",
	 			data: {"searchParams":searchParams},
	           	pageSize: 5,
	           	visiblePages:5,
	           	callback: function (data) {
	            	if(data && data.result && data.result.length>0){
	            		var template = $.templates("#searchPrdlineTemple");
	            	    var htmlOutput = template.render(data);
	            	    $("#searchPrdlineData").html(htmlOutput);
	            	}
	            }
    		});
    	}
    	
    });
    
    module.exports = prdlineListPager;
});

