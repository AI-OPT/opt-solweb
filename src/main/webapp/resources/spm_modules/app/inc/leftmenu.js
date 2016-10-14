define('app/inc/leftmenu',function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
    Widget = require('arale-widget/1.2.0/widget');
    //定义页面组件类
    var LeftMenuPager = Widget.extend({
    	//属性，使用时由类的构造函数传入
    	attrs: {
    		activemenu: "m_home"
    	},
    	//事件代理
    	events: {
        },
    	//重写父类
    	setup: function () {
    		LeftMenuPager.superclass.setup.call(this);
    		
    	},
    	
    	//当activemenu属性的值发生变化时候触发
    	_onRenderActivemenu: function(activemenu) {
    		if(activemenu==undefined || activemenu==""){
    			 activemenu="m_home";
    		}
    		$("#"+activemenu).addClass("active");
    	 },
    });
    
    module.exports = LeftMenuPager
});

