/**
 * 定义消息提示类AIMessageController
 * 基于bootstrap样式的bootbox定义消息弹出窗
 * 依赖于jquery.js和bootbox.js
 * author:zhangchao
 * */
(function($){
	/**
	 *类AIMessageController的默认构造器
	 * */
	$.AIMessageController = function(){
		this.settings = $.extend(true,{},$.AIMessageController.defaults);
		this.setLocal();
	}
	/**
	 *定义AIMessageController的扩展方法
	 * */
	$.extend($.AIMessageController,{		
		//常量定义
		defaults: {
			
		},	
		//函数原型定义
		prototype: {
			//设置语言区域
			setLocal: function(){
				bootbox.setLocale("zh_CN");
			},// end of setLocal
			//警告弹出框
			alert: function(message,callback,args){
				//bootbox风格警告框
				bootbox.alert({ 
					size: 'small',
				    message: message?message:"", 
				    closeButton:false,
				    callback: function(){ 
						if(!args || args.constructor!=window.Array){
							args = arguments; 
						}
						callback && callback.apply(this,args); 
				    }
				})
			},//end of alert
			
			//消息确认框
			confirm: function(message,okCallback,okArgs){
				//bootbox风格确认框
				bootbox.confirm({ 
				    size: 'small',
				    message: message?message:"", 
				    closeButton:false,
				    callback: function(result){ 
				    	if(result){
					    	if(!okArgs || okArgs.constructor!=window.Array){
					    		okArgs = arguments; 
							}
					    	okCallback && okCallback.apply(this,okArgs); 
				    	}
				    }
				})
			},
			//显示等待消息提示框
			showWait: function(message){
				var msg =message?message:"正在处理中，请稍候...";
				var html ="<div id='AI_WAITING' class='AI_WAITING'>"+ msg +"</div>"; 
				var dialog = bootbox.dialog({ 
					size: 'small',
					closeButton:false,
					message: html
				});
				this.showWaitDialog = dialog;
			},//end of showWait
			//隐藏等待提示窗
			hideWait: function(){
				var dialog = this.showWaitDialog;
				if(dialog)dialog.modal('hide');
			}//end of hideWait
			
		}//end of prototype
		
	});//end of $.extend
	
	
})(jQuery);//end of $.AIMessageController

//定义全局消息类实例messageController，可在页面直接调用messageController弹出消息
var messageController = new $.AIMessageController();

/**
 * 定义Ajax工具类AjaxController
 * 基于jquery、bootstrap、bootbox风格的ajax工具类
 * 依赖于jquery.js、jquery.form.js和bootbox.js
 * author:zhangchao
 * */
(function($){
	/**
	 * 类AjaxController的构造器
	 * */
	$.AjaxController = function(){
		this.settings = $.extend(true,{},$.AjaxController.defaults);
	}
	/**
	 * 扩展AjaxController，增加属性或方法。
	 * */
	$.extend($.AjaxController,{
		//定义常量
		defaults: {
			//提交表单所属的div
			AJAX_SUBMIT_CONTAINER: "_X_AJAX_SUBMIT_CONTAINER_DIV",
			//提交表单所属div的jquery的标识ID
			SELECTOR_AJAX_SUBMIT_CONTAINER: "#_X_AJAX_SUBMIT_CONTAINER_DIV",
			//ajax请求成功标识
			AJAX_STATUS_SUCCESS: "1",
			//ajax请求失败标识
			AJAX_STATUS_FAILURE: "0",
			//请求成功json数据的key，需与java后台的数据key一致
			STATUS_CODE: "statusCode",
			//请求成功json数据的key，需与java后台的数据key一致
			STATUS_INFO: "statusInfo"
		},
	
		prototype: {
			//ajax请求封装，利用jquery.form.js提交表单
			ajax: function(options){
				//取得当前$.AjaxController实例对象的句柄
				var _this = this; 
				//自定义回调函数
				var callbacks = {};
				//截取before函数，并放入callbacks
				if(typeof options.before == 'function'){
					callbacks["before"] = options.before;
					delete options.before;
				}
				//截取success函数，并放入callbacks
				if(typeof options.success=='function'){
					callbacks["success"] = options.success;
					delete options.success;
				}
				//截取failure函数，并放入callbacks
				if(typeof options.failure=='function'){
					callbacks["failure"] = options.failure;
					delete options.failure;
				}
				//截取error函数，并放入callbacks
				if(typeof options.error=='function'){
					callbacks["error"] = options.error;
					delete options.error;
				}
				//截取target(待更新的目标对象)
				var target = options.target;
				delete options.target; 
				//获取postmode，默认为request，若为update，则更新target对象
				var postmode = options.postmode?options.postmode:"request";
				//获取是否显示等待框标识
				var showWait = options && options.showWait==true?true:false;  
				//定义要showWaite的消息
				var message = options.message;
				//获取消息控制器，如果要求显示在父窗体中，则获取父窗体中的消息控制器，否则使用默认的消息控制器
				var msgController = options.showArea=='parent'&&parent.messageController? parent.messageController: messageController;
				//获取ajax选项
				var settings = {}; $.extend(settings,options); 

				//定义ajax成功调用函数
				settings["success"] = function(transport){ 
					//关闭等待消息提示框
					if(showWait)messageController.hideWait();
					//校验返回结果是否为JSON
					var checkJSON = _this.checkJSON(transport);
					if(!checkJSON){
						msgController.alert("AJAX请求返回的数据格式不是JSON，无法处理");
						return;
					}
					//获取调用结果状态码
					var status = transport[_this.settings.STATUS_CODE];
					//获取调用结果状态文本
					var statusInfo = transport[_this.settings.STATUS_INFO];
					//调用失败时，提示错误，并执行failure回调。
					if(status && status == _this.settings.AJAX_STATUS_FAILURE){
						msgController.alert(statusInfo,function(){
							callbacks["failure"] && callbacks["failure"].call(this,transport);  
						});  
					}
					//调用成功，执行success回调
					else{
						//若postmode为update，则更新target底下
						if(postmode=="update")$(target).html(transport);
						//执行自定义回调
						callbacks["success"] && callbacks["success"].call(this,transport);
					} 
				}; 
				//定义ajax提交前函数
				settings["beforeSubmit"] = function(){ 
					return callbacks["before"] && callbacks["before"].call(this);  
				}; 
				//定义ajax网络调用错误
				settings["error"] = function(transport){  
					if(showWait)msgController.hideWait();
					msgController.alert("网络请求错误,错误码:"+transport.status+",请重试。",function(){
						callbacks["error"] && callbacks["error"].call(this,transport);
					});  
				};
				//若要求显示等待消息，则显示等待模态窗口
				if(showWait)msgController.showWait(message);
				//设置待提交的表单数据
				settings.data=options.data?options.data:{};
				//设置提交方式 post或get ajaxSubmit里为type,对应$.ajax里面的method属性
				settings.type=options.method?options.method:{};
				//设置随机数，防止浏览器缓存
				var q="ajax_req_random="+new Date().getTime();
				//在请求url后面，添加随机数，防止浏览器缓存
				settings.url += (settings.url.indexOf('?') >= 0 ? '&' : '?') + q;
				//若待提交的表单只有一个（postselectors参数只有一个值）
				if(options.postselectors && options.postselectors.length==1){ 
					settings.semantic=true; 
					//取出待提交的表单postContainerSelector
					var postContainerSelector=options.postselectors[0]; 
					//表单postContainerSelector存在，则进行ajax提交
					if($(postContainerSelector).length){
						//执行ajax提交
						$(postContainerSelector).ajaxSubmit(settings);
					}
					//表单若不存在，创建一个容器进行提交
					else{
						_this.processCombineParamContainer(options.postselectors);
						//执行ajax提交
						$(this.settings.SELECTOR_AJAX_SUBMIT_CONTAINER).ajaxSubmit(settings);
					}
					
				}
				//多个表单同时提交处理，创建一个容器，并将所有待提交表单包含其中，然后进行提交
				else{
					settings.semantic=true;
					_this.processCombineParamContainer(options.postselectors);
					//执行ajax提交
					$(this.settings.SELECTOR_AJAX_SUBMIT_CONTAINER).ajaxSubmit(settings);
				}  
			},//end of ajax
			/**
			 * 合并多个表单，并将合并后的表单置于一个容器div中：this.settings.SELECTOR_AJAX_SUBMIT_CONTAINER
			 */
			processCombineParamContainer: function(/**Array*/postContainerSelectors){
				//创建一个容器div：this.settings.SELECTOR_AJAX_SUBMIT_CONTAINER
				this.createSubmitContainer();
				//获取创建的容器div
				var submitContainer = $(this.settings.SELECTOR_AJAX_SUBMIT_CONTAINER);
				//克隆各表单到容器div中。
				if(postContainerSelectors && $.isArray(postContainerSelectors)){
					$(postContainerSelectors).each(function(index,selector){
						if($(selector).length){
							$(selector).clone().prependTo(submitContainer);
						} 
					}); 
				}  
			},//end of processCombineParamContainer
			
			/**
			 * 创建提交容器
			 */
			createSubmitContainer: function(){ 
				var xSubmitContainer = $(this.settings.SELECTOR_AJAX_SUBMIT_CONTAINER);
				if(!xSubmitContainer.length){
					$(document.body).append("<div id='"+ this.settings.AJAX_SUBMIT_CONTAINER +"' style='display:none'></div>");
				}else{
					xSubmitContainer.html("");
				}
			},//end of createSubmitContainer
			/**
			 * 校验对象是否为JSON对象
			 */
			checkJSON: function(obj){
				var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;    
				return isjson;
			}//end of checkJSON
			
		}
		
	});
	
	
})(jQuery);
//定义全局ajax工具类对象ajaxController
var ajaxController = new $.AjaxController();

