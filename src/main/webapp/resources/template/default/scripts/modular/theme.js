
String.prototype.getParameter = function (key) {
	var re = new RegExp(key + '=([^&]*)(?:&)?');
	return this.match(re) && this.match(re)[1];
	}; 


	function getcookie(name){  
		
	    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));  
	    if(arr != null){  
	        return (arr[2]);  
	    }else{  
	        return "";  
	    }  
	}

	function setCookie(c_name, value, expiredays){
	　　　　var exdate=new Date();
	　　　　exdate.setDate(exdate.getDate() + expiredays);
	　　　　document.cookie=c_name+ "=" + escape(value) + ((expiredays==null) ? "" : ";expires="+exdate.toGMTString()+";path=/;");
	　　}
	
	
	
$(document).ready(function(){
	var theme = document.URL.getParameter("theme");
	var mgmtPath = document.URL.getParameter("mgmtPath");
	if(theme !=null && theme!="null"){
		setCookie("theme_index", theme, 60*60*24);	
	}
	if(theme== null|| theme=="null"){		
		theme=getcookie("theme_index");		
	}
	if(theme==null||theme=="null"||theme==""){
		theme="theme-whbl"
	}
	
	if(mgmtPath !=null && mgmtPath!="null"){
		setCookie("mgmtPath", mgmtPath, 60*60*24);	
	}
	if(mgmtPath== null|| mgmtPath=="null"){	
		
		mgmtPath=unescape(getcookie("mgmtPath"));		
	}
	$("body").attr("id",theme);
	$("body").attr("class",theme);

	$("<iframe id='myifr' style='display:none' src='"+mgmtPath+"/static/agentifram.jsp'></iframe>").prependTo("body"); 
	$("<script type='text/javascript' src='"+mgmtPath+"/static/iframhight.js'></script>").prependTo("body");
})
