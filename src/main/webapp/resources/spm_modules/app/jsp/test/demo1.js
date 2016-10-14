define('app/jsp/test/demo1', function (require, exports, module) {
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
    
    
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    //定义页面组件类
    var commentListPage = Widget.extend({
    	
    	//属性，使用时由类的构造函数传入
    	attrs: {
    	},
    	Statics: {
    		DEFAULT_PAGE_SIZE: 10
    	},
    	//事件代理
    	events: {
            },
    	//重写父类
    	setup: function () {
    		commentListPage.superclass.setup.call(this);
    		this._clearQueryParams();
    	},
    	//清空查询条件
    	_clearQueryParams:function(){
    		$("#shopScoreMs option:selected").val("");
    		$("#commentTimeBegin").val("");
    		$("#commentTimeEnd").val("");
    		$("#shopScoreWl option:selected").val("");
    		$("#shopScoreFw option:selected").val("");
    		$("#standedProdId").val("");
    		$("#orderId").val("");
    	}
    });
    
    module.exports = commentListPage;
});

