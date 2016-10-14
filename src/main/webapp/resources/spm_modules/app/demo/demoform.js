define('app/demo/demoform', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
	    Widget = require('arale-widget/1.2.0/widget'),
	    Dialog = require("optDialog/src/dialog"),
	    AjaxController = require('opt-ajax/1.0.0/index');
    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("bootstrap-paginator/bootstrap-paginator.min");
    require("app/util/jsviews-ext");

	require("jquery-validation/1.15.1/jquery.validate");
	require("app/util/aiopt-validate-ext");
    var SendMessageUtil = require("app/util/sendMessage");

    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
	//Validator.addRule('upperCaseRule', /^[A-Z]{1}$/, '请输入大写字母');
    //定义页面组件类
    var catAddPager = Widget.extend({
    	
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
			"click #addCatBtn":"_addCatTemp",
			"click #submitAddBtn":"_submitCatList"
            },
    	//重写父类
    	setup: function () {
			catAddPager.superclass.setup.call(this);
			var formValidator=this._initValidate();
			$(":input").bind("focusout",function(){
				formValidator.element(this);
			});
    	},
    	_initValidate:function(){
    		
    		var formValidator=$("#dataForm").validate({
    			rules: {
    				//productCatName: "required",
    				productCatName: {
    					required:true,
    					maxlength:10,
    					minlength:3,
    					commonText:true
    					},
    				firstLetter: {
    					required:true,
    					maxWords:2,
    					maxlength:10,
    					minlength:3,
    					regexp: /^\d{4}-\d{2}-\d{2}$/
    					},
    				serialNumber: {
    					required: true,
    					digits:true,
    					min:1,
    					max:100
    				},
    				isChild: {
    					required: true
    				},
    				startDate:{
    					required: true,
    					isDate: true,
    					maxDate: $("#endDate").val()
    				},
    				endDate:{
    					required: true,
    					isDate:true,
    					minDate: '2016-09-01'
    				},
    				fee:{
    					required: true,
    					moneyNumber: true
    				},
    				mynum:{
    					required: true,
    					amount: [8,3]
    				}
    				,
    				idno:{
    					required: true,
    					idcard: true
    				}
    			},
    			messages: {
    				productCatName: {
    					required:"请输入类目名称",
    					maxlength:"最大长度不能超过{0}",
    					minlength:"最小长度不能小于{0}"
    					},
    				firstLetter: {
    					required:"请输入类目名称首字母",
    					maxWords:"单词格式超了",
    					minlength:"最小长度为{0}",
    					regexp: "日期格式不对111"
    						
    				}
    				,
    				serialNumber: {
    					required: "请输入排序",
    					digits: "只能输入数字",
    					min:"最小值为{0}",
    					max:"最大值为{0}"
    				},
    				isChild: {
    					required: "请输入是否存在子分类"
    				},
    				startDate:{
    					required: "请输入开始日期"     						
    				},
    				endDate:{
    					required: "请输入结束日期",
    					isDate:"日期格式错误"
    				},
    				fee:{
    					required: "请输入金额"
    				},
    				mynum:{
    					required: "请输入三位数字"
    				},
    				idno:{
    					required: "请输入身份证号码"
    				}
    			}
    		});
    		
    		return formValidator;
    	},
		
		//增加类目
		_addCatTemp:function(){
			catNum['num']=catNum['num']+1;
			var template = $.templates("#catAddTemplate");
			var htmlOutput = template.render(catNum);
			$("#subDiv").before(htmlOutput);
		},
		//提交添加
		_submitCatList:function() {
			var _this= this;
			//父类目
			var parentCatId = $('#parentProductCatId').val();
			var catArr = [];
			var hasError = false;
			var formValidator=_this._initValidate();
			formValidator.form();
			if(!$("#dataForm").valid()){
				alert('验证不通过！！！！！');
				return;
			}
			//获取所有的form-label下的input
			$("#addViewDiv > .form-label.bd-bottom ").each(function (index, form) {
				
				var catObj = {};
				console.log(index + " form-label");
				if (parentCatId != null & parentCatId != '')
					catObj['parentProductCatId'] = parentCatId;
				//类目名
				var catName = $(this).find("input[name='productCatName']")[0];
				catObj['productCatName'] = catName.value;
				//首字母
				var fLetter = $(this).find("input[name='firstLetter']")[0];
				catObj['firstLetter'] = fLetter.value;
				//排序
				var sn = $(this).find("input[name='serialNumber']")[0];
				catObj['serialNumber'] = sn.value;
				//是否有子分类
				var isChild = $(this).find("input[type='radio']:checked")[0];
				catObj['isChild'] = isChild.value;
				catArr.push(catObj);
			});
			console.log("No error");
			if (hasError)
				return;
			console.log("cat arr lengeth " + catArr.length);
			ajaxController.ajax({
				type: "post",
				processing: true,
				message: "保存中，请等待...",
				url: _base + "/cat/edit/create",
				data: {'catListStr': JSON.stringify(catArr)},
				success: function (data) {
					if ("1" === data.statusCode) {
						//alert("保存成功");
						//保存成功,回退到进入的列表页
						window.history.go(-1)
					}
				}
			});
		}
    });
    
    module.exports = catAddPager;
});

