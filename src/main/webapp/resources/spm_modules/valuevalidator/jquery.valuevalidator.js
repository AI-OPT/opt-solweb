(function($){
	
	/**定义一个值对象校验框架*/
	$.ValueValidator = function(){
		this.settings = $.extend(true,{},$.ValueValidator.defaults);
	};
	
	/**格式化提示信息输出*/
	$.ValueValidator.format = function(name,source, params) {
		var arr = new Array(); 
		arr.push(name);
		if($.isArray(params)){ 
			$.merge(arr,params);
		}  
		if (typeof params=="string" || typeof params=="number") { 
			arr.push(params);
		} 
		$.each(arr, function(i, n) {
			source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n);
		}); 
		return source;
	};
	
	/**为值校验框架扩展一些属性与方法*/
	$.extend($.ValueValidator,{
		/**默认属性*/
		defaults: {
			elements: [], //存储所有等待校验的元素
			fieldErrors:[] //字段级校验失败信息
		},
		
		/*扩展一些方法*/
		prototype: {
			/**
			* 为指定元素的指定值进行规则绑定
			* @param element:绑定元素的校验规则
				var element = {
					labelName: "英文名",
					fieldName: "enName",
					fieldRules: {},
					ruleMessages: {
						required: "XXX"
					}
				}
			* @return this 当前校验对象，支持链式调用
			**/
			addRule: function(/*json*/element){
				if(!element)return this;
				/*1.优先判断是否存在必填，如果存在，则需要将必填放在第一位校验*/ 
				var rules = element.fieldRules;
				if(rules.required){ 
					var param = rules.required;
					delete rules.required;
					rules = $.extend({required: param}, rules);
					element.fieldRules = rules;
				} 
				/*2.将对应的信息存储起来*/ 
				this.settings.elements.push(element);
				return this;
			},
			/*执行规则校验并返回第一个错误信息*/
			fireRulesAndReturnFirstError: function(){
				var message;
				var res=this.valid();
				if(!res){
					var errors = this.getErrors();
					if(errors.length>0){
						var error = errors[0];
						message = error.fieldInfo?error.fieldInfo:false;
					}
				}
				return message;
			},
			
			fireFieldRule:function(fieldName){
				var _this = this;
				var es=$.grep(_this.settings.elements,function(e,i){
					return e.fieldName==fieldName;
				});
				if(!es || es.length==0){
					return false;
				}
				var ele= es[0];
				_this.validElement(ele);
				var validerrors = ele.validerrors;
				return (!validerrors || validerrors.length==0)?false:validerrors[0];
			},
			
			/*获取绑定的规则*/
			getRules: function(){
				return this.settings.elements;
			},
			/**
			* 根据指定字段获取一个元素规则
			* 如果能获取到则对象本身，否则返回false
			* @param options
			**/
			getRule: function(fieldName){
				var _this=this;
				var rules = _this.getRules();
				for(var i=0;i<rules.length;i++){
					var rule = rules[i];
					if(rule.fieldName==fieldName){
						return rule;
					}
				}
				return false;
			},
			/**
			* 执行校验
			* 如果校验不通过，则返回false/成功true
			*/
			valid: function(){
				var _this=this;
				if(!_this.settings.elements.length)return true;
				_this.settings.fieldErrors=[];
				$.each(_this.settings.elements,function(index,element){
					var valid = _this.check(element);
					if(!valid){
						_this.createError(element);
					}
				});
				return _this.settings.fieldErrors.length?false:true;
			},
			/*校验单个元素信息,返回当前元素对象*/
			validElement: function(element){
				var _this = this;
				var valid = _this.check(element);
				if(!valid){
					_this.createError(element);
				}
			},
			
			/*获取校验错误信息*/
			getErrors: function(){
				var errors = this.settings.fieldErrors;
				return errors;
			},
			/**
			* 生成单条错误信息，并进行存储
			* @param element 单个元素
			**/
			createError: function(element){
				if(!element)return;
				var _this=this;
				var validerrors = element.validerrors;
				$.each(validerrors,function(index,error){
					var field = {
						fieldName: element.fieldName,
						fieldInfo: error
					}
					_this.settings.fieldErrors.push(field);
				})
			}, 
			/**
			* 校验单个元素的信息
			* @param element:单个元素校验对象
			* @return true/false 不通过为false
			**/
			check: function(/*json*/element){
				var _this=this;
				if(!element)return;
				/*存储该元素的所有校验错误信息*/
				element.validerrors = [];
				/*循环执行规则校验*/
				for(method in element.fieldRules){
					try{ 
						/*获取校验信息*/
						var rule = { method: method, parameters:element.fieldRules[method] };
						var fieldValue=element.getValue && element.getValue.call(_this);
						/*校验结果只允许返回true/false*/
						var validResult=$.ValueValidator.methods[method].call(this,fieldValue,rule.parameters);
						/*如果校验不成功，则格式化校验信息*/
						if(!validResult){
							/*判断是否有自定义的消息*/
							var ruleMessages=element.ruleMessages?element.ruleMessages:{};
							var custMessage =  ruleMessages[method];
							/*如果自定义消息存在，而且不为空，则显示自定义消息*/
							if(custMessage && $.trim(custMessage)!=""){
								element.validerrors.push(custMessage);
							}else{ 
								/*如果自定义消息不存在，则显示默认消息*/
								var message=_this.format(element.labelName,method,rule.parameters);
								element.validerrors.push(message);
							}
						} 
					}catch(ex){  
						alert("执行规则["+ method +"]出错");
						throw ex;
					}
				}
				/*如果存在校验不通过的信息，则标识当前元素校验不通过*/
				return element.validerrors.length?false:true;
			},
			/**
			* 格式化提示信息
			* @param name:元素名称
			* @param method：校验规则
			* @param parameters:对应的参数
			**/
			format: function(name,method,parameters) {  
				var source = $.ValueValidator.messages[method];
				source=$.ValueValidator.format(name,source,parameters); 
				return source;
			}
		},
		/*预定义信息提示模板*/
		messages: {
			moneyRule:"{0}格式不正确",
			required: "{0}不能为空", 
			numberlength: "{0}必须是{1}位数字", 
			email: "{0}不是有效的E-MAIL地址",
			url: "{0}不是有效的URL地址",
			date: "{0}不是有效的日期格式(yyyy-MM-dd)", 
			datetime: "{0}不是有效的时间格式(yyyy-MM-dd hh:mm:ss)", 
			lessdate: "{0}所选择的日期大于{2}日期[{1}]", 
			greaterdate:  "{0}所选择的日期小于{2}日期[{1}]", 
			number: "{0}不是有效的数字", 
			positiveNumber: "{0}不是有效的数字", 
			integer: "{0}不是有效的整数", 
			maxInt: "{0}的值不能大于{1}", 
			minInt: "{0}的值不能小于{1}", 
			positiveInteger: "{0}不是有效的正整数", 
			nonnegativeInteger: "{0}不是有效的非负整数", 
			equalto: "{0}的值与{1}不相等",
			accept: "{0}文件后缀不是指定的({1})格式",
			maxlength: "{0}的长度或数量不能大于{1}",
			minlength: "{0}的长度或数量不能小于{1}",
			strlength: "{0}的长度不为{1}位",
			rangelength: "{0}的长度或数量必须在{1}到{2}之间",
			range: "{0}的值必须在{1}到{2}之间",
			max: "{0}的值不能大于{1}",
			min: "{0}的值不能小于{1}",
			postcode:"{0}不是有效的邮政编码", 
			regexp: "{0}的值与定义的格式不匹配",
			phonenumber:"{0}不是有效的电话号码",
			decimal:"{0}与格式要求不匹配,如果是整数最大为{1}位;如果是小数则小数位最大为{2}位",
			rightexp:"{0}格式不正确",
			moneyNumber:"{0}格式不正确，应是正数且最多2位小数",
			cnlength: "{0}输入的长度超过最大字节({1}个字节)限制(1个汉字占2字节)",
			amount:"{0}与格式要求不匹配,有效小数位最大为{2}位,且整数有效位数最大为{1}位",
			unequalto: "{0}的值不能为{1}",
			notContainSpecChar: "{0}中多个请用半角英文逗号\",\"隔开且不能包含空格",
			notContainSpecCharForAll: "{0}中不能含有特殊字符",
			notContainCN: "{0}不能包含中文",
			notContainSpace: "{0}不能包含空格",
			notContainSpaceNotTrim: "{0}不能包含空格",
			notExitSpace: "{0}不能存在空格",
			character: "{0}不是有效的字母",
			notInputEM: "{0}不允许输入全角",
			isDate: "{0}格式应为，例如\"2011-01-01\""
		},
		
		/*类方法集合，声明一些校验规则*/
		methods: {
		
			/**
			* 校验是否满足必填要求 
			* @param value:元素取值,单string或array
			* @param parameters:规则绑定设定的参数,必须为true
			* @return true/false:校验不通过为false
			**/
			required: function(value,paramters){
				if(!paramters)return true;
				var valid = $.isArray(value)? value.length > 0:$.trim(value).length > 0;
				return valid;
			},
			/**
			* 校验某组选最少必须选择的数量,如复选框至少要选择2个
			* @param value:元素取值,单string或array
			* @param parameters: 最小长度 数字
			* @return true/false:校验不通过为false
			**/
			minlength: function(value,paramters){
				var arr = $.isArray(value)?value:[];
				arr = typeof value == "string"?arr.push(arr):arr; 
				var valid = arr.length>=paramters;
				return valid;
			},
			/**
			* 校验是否是几位数字
			* @param value:元素取值
			* @param parameters: 长度
			* @return true/false:校验不通过为false
			**/
			numberlength: function(value,paramters){
				var v=$.trim(value);
				var empty = v.length?false:true;
				if(empty)return false;
				if(isNaN(v)) {
					return false;
				}
				if(v.match(/\d/g).length!=paramters){
					return false;
				} 
				return true;
			},
			/**
			* 校验某个字段的长度
			* @param value:元素取值,单string
			* @param parameters: 最小长度 数字
			* @return true/false:校验不通过为false
			**/
			strlength: function(value,paramters){
				/*如果参数值存在，则进行校验*/
				var empty = value.length?false:true;
				if(empty)return false;
				/*如果值存在,则进行校验最小值校验*/ 
				var valid = value.length == paramters;  
				return valid;
				
			},
			/**
			* 校验某组选最多能选择的数量,如复选框最多只能选4个
			* @param value:元素取值,单string或array
			* @param parameters: 最大长度 数字
			* @return true/false:校验不通过为false
			**/
			maxlength: function(value,paramters){
				var arr = $.isArray(value)?value:[];
				arr = typeof value == "string"?arr.push(arr):arr; 
				var valid =arr.length<=paramters;
				return valid;
			},
			/**
			*资金
			**/
			moneyRule : function(value,paramters){
				if(paramters==false)return true;  
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re = /^0{1}$|^[1-9](\d*|(\d*\.\d+))$|^0{1}\.\d+$/;
				var valid =  (re.test(value))?true:false;
				return valid ;
			},
			/**
			* 校验某组选的个数选择范围
			* @param value:元素取值,单string或array
			* @param parameters: 区间数组 [2,4]
			* @return true/false:校验不通过为false
			**/
			rangelength: function(value,paramters){
				var arr = $.isArray(value)?value:[];
				arr = typeof value == "string"?arr.push(arr):arr; 
				var valid = arr.length >= paramters[0] && arr.length<=paramters[1];
				return valid;
			},
			/**
			* 校验某元素取值的最小值
			* @param value:元素取值,单string
			* @param parameters: 最小长度 数字
			* @return true/false:校验不通过为false
			**/
			min: function(value,paramters){ 
				/*如果值存在,则进行校验最小值校验*/ 
				var valid = $.trim(value).length?value >= paramters:true;  
				return valid;
			},
			/**
			* 校验某元素取值的最大值
			* @param value:元素取值,单string
			* @param parameters: 最大长度 数字
			* @return true/false:校验不通过为false
			**/
			max: function(value,paramters){
				/*如果值存在,则进行校验最大值校验*/ 
				var valid = $.trim(value).length?value <= paramters:true;  
				return valid;
			},
			/**
			* 校验某数字取值的最大值 add by yangpy 20111111
			* @param value:元素取值 数字
			* @param parameters: 最大长度 数字
			* @return true/false:校验不通过为false
			**/
			maxInt: function(value,paramters){
				/*如果值存在,则进行校验最大值校验*/ 
				var valid = $.trim(value).length?Number(value)<=Number(paramters):true;  
				return valid;
			},
			/**
			* 校验某元素取值的最小值 add by yangpy 20111111
			* @param value:元素取值 数字
			* @param parameters: 最大长度 数字
			* @return true/false:校验不通过为false
			**/
			minInt: function(value,paramters){
				/*如果值存在,则进行校验最大值校验*/ 
				var valid = $.trim(value).length?Number(value)>=Number(paramters):true;  
				return valid;
			},
			/**
			* 校验某元素取值的是否在以个范围内
			* @param value:元素取值,单string
			* @param parameters: 最大长度 数字
			* @return true/false:校验不通过为false
			**/
			range: function(value,paramters){
				/*如果值存在，则进行校验*/ 
				var valid = $.trim(value).length?(value >= paramters[0] && value <= paramters[1]):true;  
				return valid;
			},
			/**
			* 校验元素的取值是否是email
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			email: function(value,paramters){
				/*如果参数为false，表示不进行email格式校验*/ 
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var valid = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
				return valid;
			},
			/**
			* 校验元素的取值是否是url
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			url: function(value,paramters){
				/*如果参数为false，表示不进行email格式校验*/ 
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var strRegex = "^((https|http|ftp|rtsp|mms)?://)" 
    				+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"  
          			+ "(([0-9]{1,3}\.){3}[0-9]{1,3}"   
          			+ "|" 
          			+ "([0-9a-z_!~*'()-]+\.)*" 
          			+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." 
          			+ "[a-z]{2,6})" 
          			+ "(:[0-9]{1,4})?" 
          			+ "((/?)|" // 
          			+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$"; 
          		var re=new RegExp(strRegex); 
				var valid =  (re.test(value))?true:false;				
				return valid;
			},
			/**
			* 校验元素的取值是符合日期格式YYYY-MM-DD
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			date: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re =/^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/i;
				var valid =  (re.test(value))?true:false;		
				return valid
			},
			/**
			* 校验元素的取值是符合日期格式YYYY-MM-DD hh:MM:ss
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			datetime: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re =/^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\d):[0-5]?\d:[0-5]?\d$/i;
				var valid =  (re.test(value))?true:false;		
				return valid
			},
			/**
			* 校验指定日期值是否小于或等于目标参数日期,如小于或等于返回true,否则返回false
			* @param value:元素取值,单string，日期格式 yyyy-mm-dd 或者yyyy-mm-dd hh:mm:ss
			* @param parameters:目标时间 单string，日期格式 yyyy-mm-dd 或者yyyy-mm-dd hh:mm:ss
			* @return true/false:校验不通过为false
			**/
			lessdate: function(value,paramters){
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				empty = $.trim(paramters[0]).length?false:true;
				if(empty)return true;
				var d1 = new Date(value.replace(/-/g,"/"));  
				var d2 = new Date(paramters[0].replace(/-/g,"/"));  
				return Date.parse(d1)<=Date.parse(d2);
			},
			/**
			* 校验指定日期值是否大于或等于目标参数日期,如大于或等于返回true,否则返回false
			* @param value:元素取值,单string，日期格式 yyyy-mm-dd 或者yyyy-mm-dd hh:mm:ss
			* @param parameters:目标时间 单string，日期格式 yyyy-mm-dd 或者yyyy-mm-dd hh:mm:ss
			* @return true/false:校验不通过为false
			**/
			greaterdate: function(value,paramters){
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				empty = $.trim(paramters[0]).length?false:true;
				if(empty)return true;
				var d1 = new Date(value.replace(/-/g,"/"));  
				var d2 = new Date(paramters[0].replace(/-/g,"/"));  
				return Date.parse(d1)>=Date.parse(d2);
			},
			/**
			* 校验元素的取值是数字,包含负数
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			number: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/;
				var valid =  (re.test(value))?true:false;
				return valid;
			},
			/**
			* 校验元素的取值是数字,不包含负数
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			positiveNumber: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re = /^(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/;
				var valid =  (re.test(value))?true:false;
				return valid;
			},
			/**
			* add by yangpy 2011-11-10-
			* 校验元素的取值是整数,包含负数
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			integer: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re = /^(\+|-)?(0|[1-9]\d*)$/; 
				var valid =  (re.test(value))?true:false;
				return valid;
			},
			/**
			* add by wangdong 2012-11-26-
			* 校验元素的取值是正整数
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			positiveInteger: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re = /^([1-9]\d*)$/; 
				var valid =  (re.test(value))?true:false;
				return valid;
			},
			/**
			* add by xindw 2013-4-8-
			* 校验元素的取值是非负整数
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			nonnegativeInteger: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re = /^(0|[1-9]\d*)$/; 
				var valid =  (re.test(value))?true:false;
				return valid;
			},
			/*add by zhangpeng 2011-11-29 begin*/
			character: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re = /^([a-z|A-Z]*)$/; 
				var valid =  (re.test(value))?true:false;
				return valid;
			},
			/*add by zhangpeng 2011-11-29 end*/
			notInputEM: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re = /[^\x00-\xff]/g 
						///^[^\x00-\xff]*$/;
				var valid =  (re.test(value))?false:true;		
				return valid;
			},

			/**
			* FUNCTION: isDate 校验日期是否合法yyyy-mm-dd这种格式的日期
			* PARAMETER: 字符串s
			* RETURNS: true/false
			*/

			isDate: function (value,paramters){
			   if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re = /^\d{4}-\d{2}-\d{2}$/;
				var valid =  (re.test(value))?true:false;		
				return valid;
			},
			
			/**
			* 校验元素的取值是否为指定的值
			* @param value:元素取值,单string
			* @param parameters:待比较的值
			* @return true/false:校验不通过为false
			**/
			equalto: function(value,paramters){
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				return $.trim(value)==$.trim(paramters);
			},
			/**
			* 校验元素的取值是否为指定的值 add by yangpy 2011-10-27
			* @param value:元素取值,单string
			* @param parameters:待比较的值
			* @return true/false:校验不通过为false
			**/
			unequalto: function(value,paramters){
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				return $.trim(value)!=$.trim(paramters);
				
			},
			/**
			* 校验元素的取值是否为邮政编码
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			postcode: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true; 
				var re = /^[0-9]\d{5}(?!\d)?$/;
				var valid =  (re.test(value))?true:false;		
				return valid
			},
			/**
			* 校验元素的取值是否为匹配正则表达式
			* @param value:元素取值,单string
			* @param parameters:待比较的值
			* @return true/false:校验不通过为false
			**/
			regexp: function(value,paramters){
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				try{ 
					var re=new RegExp(paramters); 
					var valid =  (re.test(value))?true:false;				
					return valid;
				}catch(ex){}
				return true;
			},
			/**
			* 校验元素的取值是否为电话号码
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			phonenumber: function(value,paramters){
				if(paramters==false)return true;  
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
				var valid =  (re.test(value))?true:false;		
				return valid
			},
			/**
			* 附件上传可接收到文件类型
			* @param value:元素取值
			* @param parameters: 可接受的后缀参数 如:doc|xls|gif|jpg
			* @return true/false:校验不通过为false
			**/
			accept: function(value, paramters) {
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				paramters = typeof paramters == "string" ? paramters.replace(/,/g, '|') :"png|jpg|gif|doc|xls|pdf|xlsx|docx";
				var result = value.match(new RegExp(".(" + paramters + ")$", "i"));  
				valid = result?true:false;   
				return valid;
			},
			/**
			* 解决appframe对Number(如： Number(8,2)处理出现的问题;)
			* @param value:元素取值
			* @param parameters: 整数位和小数位（如: 6/2,6为整数位，2为小数位）
			* @return true/false:校验不通过为false
			**/
			decimal:function(value,parameters){	
				if(parameters == false||parameters ==null){
					return true;
				}
				var empty = $.trim(value).length?false:true;
				if(empty){
				   return true; 
				}
				
				var valuearray = parameters;
				if(valuearray != null && valuearray.length == 2){
					ivalue = parseInt(valuearray[0]);
					dvalue = parseInt(valuearray[1]);
					realvalue = value; 
					//如果小数位数为0 过滤掉.
					if(dvalue == 0){
						if(realvalue.indexOf('.') != -1){
							return false;
						}
					}
					
					if(realvalue.indexOf('.') != -1){
						//当传入的数字为小数时
						if(ivalue == 1){
							//当整数位为一时
							var regularExpression = '/^[0-9](\\.)(\\d){1,'+ dvalue +'}$/';
			    			return eval(regularExpression).test(realvalue);
						}else{
							//当整数位不为一时
							var regularExpression = '/^[1-9](\\d){1,'+ (ivalue-1) +'}(\\.)(\\d){1,'+ dvalue +'}$|^[0-9](\\.)(\\d){1,'+ dvalue +'}$/';
			    			return eval(regularExpression).test(realvalue);
						}
	    			}else{
	    				//当传入的数字为整数时
	    				var regularExpression = '/^[1-9](\\d){0,'+ (ivalue-1) +'}$|^[0]$/';
	                    var result =  eval(regularExpression).test(realvalue);
	                    return result;
	    			}		
				}else{
					return false;
				}				
			},
			/**
			* 校验元素的取值是否是number型（金额类型，金额大于0，保留两位小数）
			* @param value:元素取值,string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			moneyNumber: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				//var re = /^(-)?(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;//可有负数
			    var re = /^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
				var valid =  (re.test(value))?true:false;
				return valid;
			},
			/**
			* 校验是否符合表达式规则。
			* @param value:元素取值
			* @param parameters: 正则表达式
			* @return true/false:校验不通过为false
			**/
			rightexp:function(value,parameters){	
				if(parameters == false||parameters ==null){
					return true;
				}
				var empty = $.trim(value).length?false:true;
				if(empty){
				   return true; 
				}
				realvalue = value;
				return eval(parameters).test(value);						
			},
			/**
			* 校验输入的字符长度是否超过数据库的限制。
			* 1个中文在ORACLE占2个字节
			* @param value:元素取值
			* @param parameters: ORACLE要求的最大长度
			* @return true/false:校验不通过为false
			**/
			cnlength: function(value,parameters){
				/*当前输入的长度*/
				var vlength = $.trim(value).length;  
				/*判断数据库要求的长度是多少*/
				var dlength = parameters?parameters:0;
				if(!dlength)return true;
				/*正则表达式判断输入文本中包含的中文长度*/
				var reg=/[\u4E00-\u9FA5]/g 
				var cnArray = value.match(reg);
				var cnNum =cnArray?cnArray.length:0;
				/*按字节计算实际输入的长度*/
				var nCount = parseInt(vlength) + cnNum;
				/*判断实际的长度是否超出数据库的长度*/
				return nCount <= parseInt(dlength);
			},
			
			/**
			* 校验输入框不能包含中文
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			notContainSpecChar: function(value,paramters){
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				for (var ind=0;ind<paramters.length;ind++)
				{
					var re = "/^[^"+paramters[ind]+"]+$/";
					if(!(eval(re).test(value))){	
						return false;
					}
				}
				return true;
			},
			
			/**
			* 校验输入框不能包含中文
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			notContainSpecCharForAll: function(value,paramters){
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				for (var ind=0;ind<paramters.length;ind++)
				{
					var re = "/^[^"+paramters[ind]+"]+$/";
					if(!(eval(re).test(value))){	
						return false;
					}
				}
				return true;
			},
			
			/**
			* 校验输入框不能包含中文
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			notContainCN: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re = /^[^\u4e00-\u9fa5]+$/;
				var valid =  (re.test(value))?true:false;	
				return valid;
			},
			/**
			* 校验输入框不能包含空格
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			notContainSpace: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = $.trim(value).length?false:true;
				if(empty)return true;
				var re = /^[^\s]+$/;
				var valid =  (re.test(value))?true:false;		
				return valid;
			},
			/**
			* 校验输入框不能包含空格
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			notContainSpaceNotTrim: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = value.length?false:true;
				if(empty)return true;
				var re = /^[^\s]+$/;
				var valid =  (re.test(value))?true:false;		
				return valid;
			},
			
			/**
			* 校验输入框的字符前后不能包含空格
			* @param value:元素取值,单string
			* @param parameters:true/false
			* @return true/false:校验不通过为false
			**/
			notExitSpace: function(value,paramters){
				if(paramters==false)return true;
				/*如果参数值存在，则进行校验*/
				var empty = value.length?false:true;
				var valid =  value.length == $.trim(value).length?true:false;
				return valid;
			},
			/** 
			* add by yangpy 2011-10-27
			* 解决appframe对Oracle数据类型Number(如： Number(8,2)处理出现的问题;)
			* @param value:元素取值
			* @param parameters: 有效整数位数和有效的小数位数（如: 8/2,8为有效位，2为小数位且可以为负数）
			* @return true/false:校验不通过为false
			**/
			amount:function(value,parameters){	
				if(parameters == false||parameters ==null){
					return true;
				}
				var empty = $.trim(value).length?false:true;
				if(empty){
				   return true; 
				}
				
				var valuearray = parameters;
				if(valuearray != null && valuearray.length == 2){
					//获得有效位数
					ivalue = parseInt(valuearray[0]);
					//获得小数有效位数
					dvalue = parseInt(valuearray[1]);
					realvalue = value;
					
					//验证实数
					var reg = '/^(\\+|-)?(0|[1-9]\\d*)([.]\\d+)?$/';
					//是实数的情况
					if(eval(reg).test(realvalue)){
						//获得是整数还是小数
						if(realvalue.indexOf('.') == -1){
							//验证整数
							var reg = '/^(\\+|-)?(0|[1-9]\\d{0,'+(ivalue-1)+'})$/';
							return eval(reg).test(realvalue);
						}else{							
							//如果是小数则，获得有效位数
							var num = 0;
							if(realvalue.indexOf('+') != -1 || realvalue.indexOf('-') != -1){
								num = realvalue.length-2;
							}else{
								num = realvalue.length-1;
							}						
							//判断小数位数是不是大于所给最大有效小数位数
							var decimalNum = realvalue.length-realvalue.indexOf('.')-1;
							//判断整数位数是不是大于所给有效位数-所给最大有效小数位数
							var intNum = num - decimalNum;
							if(decimalNum-dvalue>0){
								return false;	
							}
							if(intNum-ivalue>0){
								return false;	
							}	
							return true;						
						}
					}else{
						return false;	
					}
				}else{
					return false;
				}				
			}
		}
	}); 
})(jQuery);

