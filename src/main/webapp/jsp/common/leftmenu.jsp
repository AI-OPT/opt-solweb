<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="nav-col">
<section id="col-left" class="col-left-nano">
<div id="col-left-inner" class="col-left-nano-content">

<div class="collapse navbar-collapse navbar-ex1-collapse" id="sidebar-nav">
<ul class="nav nav-pills nav-stacked">
<!-- <li id="make-small-nav" >
<a id="make-small-nav" >
<i class="fa fa-bars"></i>
<span>菜单</span>
</a>
</li> -->
</li>
<li id="m_home" >
<a href="${_base}/api/index.html">
<i class="fa fa-home"></i>
<span>接入统计</span>
</a>
<li>
</li>
</li>
<li id="m_api">
<a href="${_base}/api/tosearch.html?activemenu=m_api">
<i class="fa fa-th-large"></i>
<span>服务搜索</span>
</a>
</li>

<li id="m_api">
<a href="${_base}/serviceDefine/list">
<i class="fa fa-th-large"></i>
<span>服务查询</span>
</a>
</li>

<li id="m_api">
<a href="${_base}/serviceDefine/add">
<i class="fa fa-th-large"></i>
<span>服务定义</span>
</a>
</li>

<li id="m_api">
<a href="${_base}/serviceDesign/add">
<i class="fa fa-th-large"></i>
<span>服务逻辑设计</span>
</a>
</li>

<li id="m_api">
<a href="${_base}/prdline/add">
<i class="fa fa-th-large"></i>
<span>产品线新增</span>
</a>
</li>
<li id="m_api">
<a href="${_base}/prdline/list">
<i class="fa fa-th-large"></i>
<span>产品线查询</span>
</a>
</li>


</ul>
</div>
</div>
</section>
</div>
<script type="text/javascript">
 //活动菜单
 var activemenu="${param.activemenu}";
 //活动菜单的父级菜单
 //var parentmenu="${param.parentmenu}";
 //如果活动菜单为空，则默认为首页
 if(activemenu==undefined || activemenu==""){
	 activemenu="m_home";
 }
 //如果父级菜单不为空，将父级菜单展开，并显示子菜单，并将活动菜单高亮显示
 /* if(parentmenu!=undefined && parentmenu!=""){
	 $("#"+parentmenu).addClass("open");
	 $("#"+parentmenu+" ul").show();;
 } */
 //高亮显示活动菜单
 $("#"+activemenu).addClass("active");
</script>