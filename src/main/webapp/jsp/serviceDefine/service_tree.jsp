<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <script src="${_base}/resources/ztree/js/jquery.ztree.all-3.5.min.js"></script>
    <link rel="stylesheet" href="${_base}/resources/ztree/css/zTreeStyle/zTreeStyle.css"/>
</head>
<body>
<ul id="treeDemo" class="ztree"></ul>
<input type="hidden" id="selectId" value=""/>
<input type="hidden" id="selectName" value=""/>
<script>
    var zTreeNodes;
    var setting = {
    	isSimpleData : true,              //数据是否采用简单 Array 格式，默认false  
    	treeNodeKey : "categoryId",               //在isSimpleData格式下，当前节点id属性  
    	treeNodeParentKey : "parentCategoryId",        //在isSimpleData格式下，当前节点的父节点id属性  
    	showLine : true,    
        async: {
            enable: true,
            url: "${_base}/category/treeData",
            autoParam: ["categoryId"],
            dataFilter: filter
        }, 
        callback: {
            onClick: function (event, treeId, treeNode) {
                $("#selectName").val(treeNode.categoryName);
                $("#selectId").val(treeNode.categoryId)
            }
        },
        view: {
            showIcon: true
        }
    };

    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null
        for (var i = 0, l = childNodes.length; i < l; i++) {
            childNodes[i].name = childNodes[i].categoryName;
            childNodes[i].isParent = true;
        }
        return childNodes;
    }

    $(document).ready(function () {
        $.fn.zTree.init($("#treeDemo"), setting);
    });
  

</script>
</body>
</html>