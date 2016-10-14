<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="uedroot" value="${pageContext.request.contextPath}/template/default"/>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
	<title>运营管理平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<%@include file="/inc/inc.jsp" %>

</head>
<body class="theme-white">
 <!--/提示弹出框操作结束-->
<div id="theme-wrapper">
    <header class="navbar" id="header-navbar">
        <div class="container">
            <a href="index.html" id="logo" class="navbar-brand"><img  src="${uedroot}/img/logo.png" alt="" class="normal-logo logo-white"/></a>
        <div class="clearfix">
        <!--操作菜单-->
            <button class="navbar-toggle" data-target=".navbar-ex1-collapse" data-toggle="collapse" type="button">
            <span class="sr-only"></span>
            <span class="fa fa-bars"></span>
            </button>
         <!--隐藏菜单icon-->
            <div class="nav-no-collapse navbar-left pull-left hidden-sm hidden-xs">
                <ul class="nav navbar-nav pull-left">
               	 <li><a class="btn" id="make-small-nav"><i class="fa fa-bars"></i></a></li>
                </ul>   
            </div>
        <!--/隐藏菜单icon结束-->
        <!--右侧导航-->
            <div class="nav-no-collapse pull-right" id="header-nav">
            <ul class="nav navbar-nav pull-right">
        <!--搜索-->
            <li class="mobile-search">
            	<a class="btn"><i class="fa fa-search"></i></a>
            <div class="drowdown-search">
                <form role="search">
                    <div class="form-group"><input type="text" class="form-control" placeholder="搜索"><i class="fa fa-search nav-search-icon"></i></div>
                </form>
            </div>
            </li>
         <!--/搜索结束-->
         <!--待办事项-->
            <li class="dropdown hidden-xs">
                <a class="btn dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i><span class="count">8</span></a>
         <!--待办事项隐藏区-->   
                <ul class="dropdown-menu notifications-list">
                    <li class="pointer">
                        <div class="pointer-inner">
                        <div class="arrow"></div>
                        </div>
                    </li>
                    <li class="item-header">代办提醒</li>
                    <li class="item"><a href="#"><i class="fa fa-circle"></i><span class="content">待办提醒事务1</span></a></li>
                    <li class="item"><a href="#"><i class="fa fa-circle"></i><span class="content">待办提醒事务1</span></a></li>
                    <li class="item"><a href="#"><i class="fa fa-circle"></i><span class="content">待办提醒事务1</span></a></li>
                    <li class="item"><a href="#"><i class="fa fa-circle"></i><span class="content">待办提醒事务1</span></a></li>
                </ul>
            </li>
         <!--/待办事项-->
         <!--颜色设置-->     
      	 <li class="dropdown hidden-xs notifications-list">
                 <a class="btn dropdown-toggle" data-toggle="dropdown"><i class="fa fa-cog"></i></a>
		 <ul class="dropdown-menu color-pifu" id="skin-colors" >
               	 	<li>
		            <a class="skin-changer" data-skin="theme-whbl" data-toggle="tooltip" title="蓝色" style="background-color: #3498db;height:20px;width:10px;padding:0 10px;float:left">
		            </a>
		            </li>
		            <li>
		            <a class="skin-changer" data-skin="theme-white" data-toggle="tooltip" title="绿色" style="background-color: #2ecc71;height:20px;width:10px;padding:0 10px;float:left">
		            </a>
		            </li>
		             <li>
		            <a class="skin-changer" data-skin="theme-amethyst" data-toggle="tooltip" title="紫色" style="background-color: #9b59b6;height:20px;width:10px;padding:0 10px;float:left">
		            </a>
		            </li>
		            <!--
		             <li>
		            <a class="skin-changer" data-skin="" data-toggle="tooltip" title="black" style="background-color: #34495e;height:20px;width:10px;padding:0 10px;float:left">
		            </a>
		            </li>
		            <li>
		            <a class="skin-changer blue-gradient" data-skin="theme-blue-gradient" data-toggle="tooltip" title="Gradient"  style="background:#3498db;height:20px;width:10px;padding:0 10px;float:left">
		            </a>
		            </li>
		            <li>
		            <a class="skin-changer" data-skin="theme-turquoise" data-toggle="tooltip" title="Green Sea" style="background-color: #1abc9c;height:20px;width:10px;padding:0 10px;float:left">
		            </a>
		            </li>
		           
		            <li>
		            <a class="skin-changer" data-skin="theme-blue" data-toggle="tooltip" title="Blue" style="background-color: #2980b9;height:20px;width:10px;padding:0 10px;float:left">
		            </a>
		            </li>
		            <li>
		            <a class="skin-changer" data-skin="theme-red" data-toggle="tooltip" title="Red" style="background-color: #e74c3c;height:20px;width:10px;padding:0 10px;float:left">
		            </a>
		            </li>
		            -->
                </ul>
      	 </li>
         <!--/颜色设置结束-->  
           <li class="dropdown hidden-xs"><a class="btn dropdown-toggle" data-toggle="dropdown"><i class="fa fa-info-circle"></i></a></li>
         <!--用户信息-->
            <li class="dropdown profile-dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <img src="${uedroot}/img/robert-300.jpg" alt=""/>
                <span class="hidden-xs">熊熊熊二</span> <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#"><i class="fa fa-user"></i>个人中心</a></li>
                    <li><a href="#"><i class="fa fa-key"></i>修改密码</a></li>
                    <li><a href="#"><i class="fa fa-cog"></i>系统设置</a></li>
                </ul>
            </li>
         <!--/用户信息结束-->
         <!--退出icon-->    
            <li class="hidden-xxs"><a class="btn"><i class="fa fa-power-off"></i></a></li>
         <!--/退出icon-->       
            </ul>
            </div>
            </div>
        </div>
    </header>
<!--头部-->
<div id="page-wrapper" class="container">
    <!---->
    <div class="row">
    <!--左侧菜单-->
    <%@include file="/inc/leftmenu.jsp" %>
    <!--/左侧菜单结束-->
    
    <div id="content-wrapper"><!--右侧灰色背景-->
    <!--右侧-->
     <div class="row"><!--外围框架-->
     	<div class="col-lg-12"><!--删格化-->
             <div class="row"><!--内侧框架-->
	                 <div class="col-lg-12"><!--删格化-->
	                    <div class="main-box clearfix"><!--白色背景-->
	                    		<div class="notice col-lg-12">
	                    			<p class="gongg"><A href="#">［公告］:</A></p>
           						 <div  id="elem">
						            <ul id="elem1">
						                <li><A href="#">公告位置！比如说系统维护，哪些功能在什么时间段可能不可用之类的，针对后台</A></li>
						                <li><A href="#">公告位置！比如说系统维护，哪些功能在什么时间段可能不可用之类的，针对后台</A></li>
						                <li><A href="#">公告位置！比如说系统维护，哪些功能在什么时间段可能不可用之类的，针对后台</A></li>
						                <li><A href="#">公告位置！比如说系统维护，哪些功能在什么时间段可能不可用之类的，针对后台</A></li>
						            </ul>
						            <ul id="elem2">
						            </ul>
						            </div>
            						 <p class="dclose"><A href="#"><i class="icon-remove"></i></A></p>
	                    		</div>
	         			</div>
	                	</div>
              </div>
         </div>
     </div>	
     <!--右侧-->
     
     <iframe class="content-wrapper-iframe" id="mainFrame" name="mainFrame"  src="" style="overflow:visible;"
             frameborder="0"  scrolling="no" marginheight="0" width="100%" marginwidth="0" ></iframe>

    <%@include file="/inc/foot.jsp" %>
    </div>
    </div>
</div>
</div>
 <script src="${uedroot}/scripts/modular/iFrame-height-auto.js"></script>
</body>

</html>