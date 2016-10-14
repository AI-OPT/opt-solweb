define(function (require, exports, module) {

	require("jsviews/jsrender");
	require("jsviews/jsviews");
/**
 * 单个商品总价-购物车
 */
$.views.helpers({
	"shopCartPrices": function (salePrice, buyNum){
		var li = accMul(salePrice, buyNum);
		var result = '0.00';
		if(isNaN(li) || !li){
			return result;
		}
        return fmoney(parseInt(li)/1000, 2);
		}
});
	
/**
 * 格式化金额
 */
$.views.helpers({
	"formatMoney": function(money,n){
		return fmoney(money,n);
	}
});

function fmoney(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(),
	r = s.split(".")[1];
	t = "";
	for(i = 0; i < l.length; i ++ ){   
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}

function fmoneyNoSplit(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	return s;
}
	
/**
 * jsview标签 去除时间后两位 .0
 */
$.views.helpers({
	"removeDateLastTwo":function(time){
		 if(time.length>19){
		    time = time.substr(0,19);
		}
		 return time;
	}
});

/**
 * 毫秒转时间
 */
$.views.helpers({
	"timesToFmatter":function(times){
		var format = function(time, format){ 
			var t = new Date(time); 
			var tf = function(i){return (i < 10 ? '0' : '') + i}; 
			return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){ 
			switch(a){ 
			case 'yyyy': 
			return tf(t.getFullYear()); 
			break; 
			case 'MM': 
			return tf(t.getMonth() + 1); 
			break; 
			case 'mm': 
			return tf(t.getMinutes()); 
			break; 
			case 'dd': 
			return tf(t.getDate()); 
			break; 
			case 'HH': 
			return tf(t.getHours()); 
			break; 
			case 'ss': 
			return tf(t.getSeconds()); 
			break; 
			}; 
			}); 
			}; 
		return format(times,"yyyy-MM-dd HH:mm:ss");
	}
});

/**
 * 对订单的受理时间进行格式化.
 */
$.views.helpers({
	"formatDate":function(applyTime){
		var millisecond = applyTime.time;
		//构造日期对象
		var dateObj = new Date(millisecond);
		
		var year = 0;
		var month = 0;
		var day = 0;
		var hour = 0;
		var minute = 0;
		var second = 0;
		
		var dateStr = ""; 
		//初始化时间 
		year= dateObj.getFullYear();//ie火狐下都可以 
		month= dateObj.getMonth()+1; 
		day = dateObj.getDate(); 
		hour = dateObj.getHours(); 
		minute = dateObj.getMinutes(); 
		second = dateObj.getSeconds(); 
		
		dateStr += year + "-"; 
		
		if(month >= 10 ){ 
		  dateStr += month + "-"; 
		} else { 
		  dateStr += "0" + month + "-"; 
		}
		
		if(day>=10){
		  dateStr += day+" ";
		}else{
		  dateStr += "0" + day+" ";
		}
		
		if(hour>=10){
		   dateStr += hour+":";
		}else{
		   dateStr += "0" + hour+":";
		}
		
		if(minute>=10){
		   dateStr += minute+":";
		}else{
		   dateStr += "0" + minute+":";
		}
		
		if(second>=10){
		   dateStr += second;
		}else{
		   dateStr += "0" + second;
		}
		
		return dateStr;
	}
});

/**
 * 订单金额的转换类（分->元）
 */
$.views.helpers({
	"fenToYuan":function(fen){
		var result = '0.00';
		if(isNaN(li) || !li){
			return result;
		}
        return fmoney(parseInt(li)/100, 2);
	}
});

/**
 * 订单金额的转换类（厘->元）
 */
$.views.helpers({
	"liToYuan":function(li){
		var result = '0.00';
		if(isNaN(li) || !li){
			return result;
		}
        return fmoney(parseInt(li)/1000, 2);
	}
});

/**
 * 金额的转换类（厘->元） 无数据不转换
 */
$.views.helpers({
	"liToYuan2":function(li){
		if(isNaN(li) || !li){
			return null;
		}
        return fmoneyNoSplit(parseInt(li)/1000, 2);
	}
});

RegExp.prototype.liToYuan = function(li) {
	var result = '0.00';
	if(isNaN(li) || !li){
		return result;
	}
    return fmoney(parseInt(li)/1000, 2);
};

/** 
 * Timestamp转化为指定格式的时间字符串
 * @param  {string} format    格式 
 * @param  {int}    timestamp 要格式化的时间
 * @return {string}           格式化后的时间字符串 
 */
$.views.helpers({
	"timestampToDate":function(format, timestamp){
		if(timestamp!=null){
			return (new Date(parseFloat(timestamp))).format(format);
		}else{
			return null;
		}
	}
});

function timestampToDate(format, timestamp){
	if(timestamp!=null){
		return (new Date(parseFloat(timestamp))).format(format);
	}else{
		return null;
	}
}

/**
 * 定义格式方法
 */
Date.prototype.format = function(format) {
	var o = {
		"M+": this.getMonth() + 1,
		"d+": this.getDate(),
		"h+": this.getHours(),
		"m+": this.getMinutes(),
		"s+": this.getSeconds(),
		"q+": Math.floor((this.getMonth() + 3) / 3),
		"S": this.getMilliseconds()
	};
	if (/(y+)/.test(format) || /(Y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

/**
 ** 加法函数，用来得到精确的加法结果
 ** 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
 ** 调用：accAdd(arg1,arg2)
 ** 返回值：arg1加上arg2的精确结果
 **/
function accAdd(arg1, arg2) {
    var r1, r2, m, c;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    c = Math.abs(r1 - r2);
    m = Math.pow(10, Math.max(r1, r2));
    if (c > 0) {
        var cm = Math.pow(10, c);
        if (r1 > r2) {
            arg1 = Number(arg1.toString().replace(".", ""));
            arg2 = Number(arg2.toString().replace(".", "")) * cm;
        } else {
            arg1 = Number(arg1.toString().replace(".", "")) * cm;
            arg2 = Number(arg2.toString().replace(".", ""));
        }
    } else {
        arg1 = Number(arg1.toString().replace(".", ""));
        arg2 = Number(arg2.toString().replace(".", ""));
    }
    return (arg1 + arg2) / m;
}
//给Number类型增加一个add方法，调用起来更加方便。
Number.prototype.add = function (arg) {
    return accAdd(this, arg);
};

/**
 ** 减法函数，用来得到精确的减法结果
 ** 说明：javascript的减法结果会有误差，在两个浮点数相减的时候会比较明显。这个函数返回较为精确的减法结果。
 ** 调用：accSub(arg1,arg2)
 ** 返回值：arg1加上arg2的精确结果
 **/
function accSub(arg1, arg2) {
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2)); //last modify by deeka //动态控制精度长度
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m).toFixed(n);
}
// 给Number类型增加一个sub方法，调用起来更加方便。
Number.prototype.sub = function (arg) {
    return accSub(this, arg);
};

/**
 ** 乘法函数，用来得到精确的乘法结果
 ** 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
 ** 调用：accMul(arg1,arg2)
 ** 返回值：arg1乘以 arg2的精确结果
 **/
function accMul(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length;
    }
    catch (e) {
    }
    try {
        m += s2.split(".")[1].length;
    }
    catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
}
// 给Number类型增加一个mul方法，调用起来更加方便。
Number.prototype.mul = function (arg) {
    return accMul(this, arg);
};

/** 
 ** 除法函数，用来得到精确的除法结果
 ** 说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
 ** 调用：accDiv(arg1,arg2)
 ** 返回值：arg1除以arg2的精确结果
 **/
function accDiv(arg1, arg2) {
    var t1 = 0, t2 = 0, r1, r2;
    try {
        t1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
    }
    try {
        t2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
    }
    with (Math) {
        r1 = Number(arg1.toString().replace(".", ""));
        r2 = Number(arg2.toString().replace(".", ""));
        return (r1 / r2) * pow(10, t2 - t1);
    }
}
//给Number类型增加一个div方法，调用起来更加方便。
Number.prototype.div = function (arg) {
    return accDiv(this, arg);
};

$.views.helpers({
	"subStrLessThan30": function(str){
		return subStrLessThan30(str);
	}
});

function subStrLessThan30(str){
	if(str!=null){
		var strLength = str.length;
		if(strLength > 30){
			var beginIndex = strLength - 20;
			str = str.substr(0, 10) + '...' + str.substr(beginIndex, strLength);
		}
	}
	return str;
}
$.views.helpers({
	"subStr": function(strlength,str){
		return subStr(strlength,str);
	}
});

function subStr(strlength,str){
	if(str!=null){
		var strLength = str.length;
		if(strLength > strlength){
			var beginIndex = strlength;
			str = str.substr(0, strlength) + '...';
		}else{
			str = str;
		}
	}
	return str;
}
});
