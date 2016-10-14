define('app/util/sendMessage', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
    Widget = require('arale-widget/1.2.0/widget'),
    Dialog = require("artDialog/src/dialog");
    
    /**
     * 定时器
     */
    var _res;
    //定义页面组件类
    var SendMessageUtil = Widget.extend({
    	//重写父类
    	setup: function () {
    		SendMessageUtil.superclass.setup.call(this);
    	},
    	events: {
        },
        _closeMessage: function(){
        	 $("#CLOSE_POP").parent(".table-pop").hide(200);
        	 clearInterval(_res);
        	 $("#PHONE_IDENTIFY").removeAttr("disabled"); //移除disabled属性
             $('#PHONE_IDENTIFY').val('获取验证码');
             $('#phoneVerifyCode').val('');
        },
    	//获取短信验证码
    	_getPhoneVerifyCode: function(){
    		var smsFlag=1;
    		if(smsFlag!="0"){
            	 var step = 60;
                 $('#PHONE_IDENTIFY').val('重新发送60');
                 $("#PHONE_IDENTIFY").attr("disabled", true);
                 _res = setInterval(function(){
                     $("#PHONE_IDENTIFY").attr("disabled", true);//设置disabled属性
                     $('#PHONE_IDENTIFY').val('重新发送'+step);
                     step-=1;
                     if(step <= 0){
	                     $("#PHONE_IDENTIFY").removeAttr("disabled"); //移除disabled属性
	                     $('#PHONE_IDENTIFY').val('获取验证码');
	                     clearInterval(_res);//清除setInterval
                     }
                 },1000);
                 var param={
            		phone:	$("input[name='phoneNum']").val()
     			};
         		$.ajax({
 			        type: "post",
 			        processing: false,
 			        url: _base+"/verify/toSendPhone",
 			        dataType: "json",
 			        data: param,
 			        message: "正在加载数据..",
 			        success: function (data) {
 			        	if(data.responseHeader.resultCode=="9999"){
			        		$('#showSmsMsg').text("1分钟后可重复发送 ");
			    			$("#phoneVerifyCode").val("");
							return false;
			        	}else{
			        		$('#showSmsMsg').text("验证码已成功发送");
			    			$("#phoneVerifyCode").val("");
			        	}
 			        },
 			        error: function(XMLHttpRequest, textStatus, errorThrown) {
 			        	Dialog({
        					title: '提示',
        					width: '200px',
        					height: '50px',
        					content: '系统出错，请稍后再试！'
        				}).show();
 			        }
         		}); 
             }
    	},
    	//校验短信验证码是否正确
		_verifyPhoneCode: function(func){
			if($("#phoneVerifyCode").val() == '' || $("#phoneVerifyCode").val() == null){
    			$('#showSmsMsg').text("请填写验证码");
    			return false;
    		}else{
    			$('#showSmsMsg').text("");
    		}
    		var param={
    			phone : $("input[name='phoneNum']").val(),
    			phoneVerifyCode : $("#phoneVerifyCode").val()
    		};
    		$.ajax({
		        type: "post",
		        processing: false,
		        url: _base+"/verify/verifyPhoneCode",
		        dataType: "json",
		        data: param,
		        message: "正在加载数据..",
		        success: function (data) {
		        	if(data.responseHeader.resultCode=="000003"){
		        		$('#showSmsMsg').text("请重新发送验证码  ");
		    			$("#showSmsMsg").attr("style","display:block");
		    			$('#phoneVerifyCode').val("");
						return false;
		        	}else if(data.responseHeader.resultCode=="000002"){
		        		$('#showSmsMsg').text("验证码已失效  ");
		    			$("#showSmsMsg").attr("style","display:block");
						return false;
		        	}else if(data.responseHeader.resultCode=="000001"){
		        		$('#showSmsMsg').text("短信验证码错误 ");
		    			$("#showSmsMsg").attr("style","display:block");
						return false;
		        	}else if(data.responseHeader.resultCode=="000000"){
		        		$("#showSmsMsg").attr("style","display:none");
		        		func();
		        		clearInterval(_res);
		        	}
		        }
     		}); 
    	}
    	
    });
    
    module.exports = SendMessageUtil;
});

