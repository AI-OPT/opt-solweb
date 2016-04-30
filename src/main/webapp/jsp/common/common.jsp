
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%
    String _base = request.getContextPath();
    request.setAttribute("_base", _base);

    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "No-cache");
%>
<!-- bootstrap-原生css -->
<link rel="stylesheet" type="text/css" href="${_base }/resources/bootstrap/bootstrap/dist/css/bootstrap.css"/>
<!-- centaurus相关css -->
<link rel="stylesheet" type="text/css" href="${_base }/resources/bootstrap/centaurus/css/libs/font-awesome.css"/>
<link rel="stylesheet" type="text/css" href="${_base }/resources/bootstrap/centaurus/css/libs/nanoscroller.css"/> 
<link rel="stylesheet" type="text/css" href="${_base }/resources/bootstrap/centaurus/css/compiled/theme_styles.css"/> 
<link rel="stylesheet" href="${_base }/resources/bootstrap/centaurus/css/libs/fullcalendar.css" type="text/css"/>
<link rel="stylesheet" href="${_base }/resources/bootstrap/centaurus/css/compiled/calendar.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${_base }/resources/bootstrap/centaurus/css/libs/morris.css" type="text/css"/>
<link rel="stylesheet" href="${_base }/resources/bootstrap/centaurus/css/libs/daterangepicker.css" type="text/css"/>
<link rel="stylesheet" href="${_base }/resources/bootstrap/centaurus/css/libs/timeline.css" type="text/css"/>

<!-- jquery相关js -->
<script src="${_base}/resources/jquery/jquery-2.1.4.min.js" ></script>
<script src="${_base}/resources/jquery/jquery.metadata.js" ></script>
<!-- bootstrap原生js -->
<script src="${_base }/resources/bootstrap/bootstrap/dist/js/bootstrap.min.js"></script>

<%-- <script src="${_base }/resources/bootstrap/centaurus/js/jquery-ui.custom.min.js"></script>
<script src="${_base }/resources/bootstrap/centaurus/js/jquery.slimscroll.min.js"></script>
<script src="${_base }/resources/bootstrap/centaurus/js/morris.min.js"></script>
<script src="${_base }/resources/bootstrap/centaurus/js/moment.min.js"></script>
<script src="${_base }/resources/bootstrap/centaurus/js/daterangepicker.js"></script>
 --%>
 
<script src="${_base }/resources/bootstrap/centaurus/js/scripts.js"></script>
<script src="${_base }/resources/bootstrap/centaurus/js/pace.min.js"></script>

<!-- jsrender相关js -->
<script src="${_base}/resources/jsviews/jsrender.min.js"></script>
<script src="${_base}/resources/jsviews/jsviews.min.js"></script>

<!-- bootbox相关js -->
<script src="${_base}/resources/bootbox/bootbox.js"></script>

<!-- ajax 封装相关css和js -->
<link href="${_base}/resources/ajaxhelper/css/jquery.pagcontroller.css" rel="stylesheet"/>
<script src="${_base}/resources/ajaxhelper/jquery.form.min.js"></script>
<script src="${_base}/resources/ajaxhelper/jquery.pagcontroller.js"></script>

<!-- 悬浮框相关js -->
<script src="${_base}/resources/bootstrap/twitter-bootstrap/bootstrap-tooltip.js"></script>
<script src="${_base}/resources/bootstrap/twitter-bootstrap/bootstrap-popover.js"></script>

<script src="${_base }/resources/bootstrap/centaurus/js/demo.js"></script>
<script src="${_base }/resources/bootstrap/centaurus/js/demo-rtl.js"></script>
<script src="${_base }/resources/bootstrap/centaurus/js/jquery.nanoscroller.min.js"></script>

<!-- 分页相关js -->
<script src="${_base }/resources/bootstrap/pagination/bootstrap-paginator.min.js"></script>

<script>
    var _base = "${_base}";
</script>

<script type="text/javascript">
	$(document).ready(function(){
		
	});
</script>
