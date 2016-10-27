define('app/jsp/serviceDesign/save', function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
	    Widget = require('arale-widget/1.2.0/widget'),
	    Dialog = require("optDialog/src/dialog"),
	    AjaxController = require('opt-ajax/1.0.0/index');
    require("jsviews/jsrender.min");
    require("jsviews/jsviews.min");
    require("my97DatePicker/WdatePicker");
    require("bootstrap-paginator/bootstrap-paginator.min");
    require("app/util/jsviews-ext");
    require("opt-paging/aiopt.pagination");
    
    require("jquery-validation/1.15.1/jquery.validate");
    require("app/util/aiopt-validate-ext");
    
    
    //实例化AJAX控制处理对象
    var ajaxController = new AjaxController();
    //定义页面组件类
    var prdlineAddPager = Widget.extend({
    	
    	//属性，使用时由类的构造函数传入
    	attrs: {
    	},
    	Statics: {
    	},
    	//事件代理
    	events: {
    		"click #submitSaveBtn":"_saveServiceDesign",
    		"click #cancelBtn":"_cancelSave"
        },
    	//重写父类
    	setup: function () {
    		prdlineAddPager.superclass.setup.call(this);
    	},
    	
    	/**
    	 * 选择服务对话框
    	 */
    	_selectService:function(){
    		var innerHtml = $("#selectServiceDialog").html();
			var d = Dialog({
    			title:"选择服务",
    			width:"800px",
    			height:"500px",
    			closeIconShow:true,
    			innerHtml:innerHtml,
    			okValue: '确 定',
				ok:function(){
					var srvApiId = $("input[name=CHEK_SERVICE]:checked").val();
					var srvApiName = $("input[name=CHEK_SERVICE]:checked").attr("srvApiName");
					$("#srvApiId").val(srvApiId);
					$("#srvApiName").val(srvApiName);
				},
				cancelValue:'取消',
				cancel:function(){
					this.close();
				}
    		});
    		d.show();
    	},
    
    	/**
    	 * 入参编辑按钮事件
    	 */
    	_modifyInputData:function(inputId,inputName,parentInputName,isRequired){
    		inputModifyArray.push(inputId);
    		$("#inputName_"+inputId).html(
    				"<input id='inputName_val_"+inputId+"' type='text' class='int-text int-small' value='"+inputName+"' maxLength='32'>");
    		$("#parentInputName_"+inputId).html(
    				"<input id='parentInputName_val_"+inputId+"' type='text' class='int-text int-small' value='"+parentInputName+"' maxLength='32'>");
    		$("#isRequired_"+inputId).html(
    				"<input id='isRequired_val_"+inputId+"' type='text' class='int-text int-small' value='"+isRequired+"' maxLength='1'>");
    	},
    	/**
    	 * 入参删除
    	 */
    	_deleteInputData:function(inputId){
    		inputDeleteArray.push(inputId);
    		var thisNode=document.getElementById("inputParams_"+inputId);
    		thisNode.parentNode.removeChild(thisNode)
    	},
    	/**
    	 * 入参 删除新增数据
    	 */
    	_deleteAddInputData:function(deleteAddIndex){
    		var thisNode=document.getElementById("addInput_"+deleteAddIndex);
    		thisNode.parentNode.removeChild(thisNode)
    	},
    	/**
    	 * 入参 新增
    	 */
    	_addInputData:function(){
    		$("#inputParamsData").append(
    			"<tr id='addInput_"+inputAddCount+"'>"
					+"<td id='add_inputName_"+inputAddCount+"'><input id='add_inputName_val_"+inputAddCount+"' type='text' class='int-text int-small'   maxLength='32'></td>"
					+"<td id='add_parentInputName_"+inputAddCount+"'><input id='add_parentInputName_val_"+inputAddCount+"' type='text' class='int-text int-small'   maxLength='32'></td>"
					+"<td id='add_isRequired_"+inputAddCount+"'><input id='add_isRequired_val_"+inputAddCount+"' type='text' class='int-text int-small'   maxLength='1'></td>"
                    +"<td><a href='JavaScript:void(0)' onclick='pager._deleteAddInputData("+inputAddCount+")' class='blue-border'>删除</a></td>"
				+"</tr>"
    		);
    		inputAddCount++;
    	},
    	
    	/**
    	 * 出参编辑按钮事件
    	 */
    	_modifyOutputData:function(outputId,outputName,parentOutputName){
    		outputModifyArray.push(outputId);
    		$("#outputName_"+outputId).html(
    				"<input id='outputName_val_"+outputId+"' type='text' class='int-text int-small'   value='"+outputName+"' maxLength='32'>");
    		$("#parentOutputName_"+outputId).html(
    				"<input id='parentOutputName_val_"+outputId+"' type='text' class='int-text int-small'   value='"+parentOutputName+"' maxLength='32'>");
    	},
    	/**
    	 * 出参删除
    	 */
    	_deleteOutputData:function(outputId){
    		outputDeleteArray.push(outputId);
    		var thisNode=document.getElementById("outputParams_"+outputId);
    		thisNode.parentNode.removeChild(thisNode)
    	},
    	/**
    	 * 出参 删除新增数据
    	 */
    	_deleteAddOutputData:function(deleteAddIndex){
    		var thisNode=document.getElementById("addOutput_"+deleteAddIndex);
    		thisNode.parentNode.removeChild(thisNode)
    	},
    	/**
    	 * 出参 新增
    	 */
    	_addOutputData:function(){
    		$("#outputParamsData").append(
    			"<tr id='addOutput_"+outputAddCount+"'>"
					+"<td id='add_outputName_"+outputAddCount+"'><input id='add_outputName_val_"+outputAddCount+"' type='text' class='int-text int-small'   maxLength='32'></td>"
					+"<td id='add_parentOutputName_"+outputAddCount+"'><input id='add_parentOutputName_val_"+outputAddCount+"' type='text' class='int-text int-small'   maxLength='32'></td>"
                    +"<td><a href='JavaScript:void(0)' onclick='pager._deleteAddOutputData("+outputAddCount+")' class='blue-border'>删除</a></td>"
				+"</tr>"
    		);
    		outputAddCount++;
    	},
    	/**
    	 * 获得入参修改数据
    	 */
    	_getInputModifyDataArray:function(){
    		var inputModifyDataArray=[];
			if(inputModifyArray.length>0){
				for(var i=0;i<inputModifyArray.length;i++){
					inputModifyDataArray.push({
						'inputId':inputModifyArray[i],
						'inputName':$("#inputName_val_"+inputModifyArray[i]).val(),
						'parentInputName':$("#parentInputName_val_"+inputModifyArray[i]).val(),
						'isRequired':$("#isRequired_val_"+inputModifyArray[i]).val()
					});
				}
			}
			return inputModifyDataArray;
    	},
    	/**
    	 * 获得入参新增数据
    	 */
    	_getInputAddDataArray:function(){
    		var inputAddDataArray = [];
			if(inputAddCount>0){
				for(var i=0;i<inputAddCount;i++){
					inputAddDataArray.push({
						'inputName':$("#add_inputName_val_"+i).val(),
						'parentInputName':$("#add_parentInputName_val_"+i).val(),
						'isRequired':$("#add_isRequired_val_"+i).val()
					});
				}
			}
			return inputAddDataArray;
    	},
    	/**
    	 * 获得出参修改数据
    	 */
    	_getOutputModifyDataArray:function(){
    		var outputModifyDataArray=[];
			if(outputModifyArray.length>0){
				for(var i=0;i<outputModifyArray.length;i++){
					outputModifyDataArray.push({
						'outputId':inputModifyArray[i],
						'outputName':$("#outputName_val_"+outputModifyArray[i]).val(),
						'parentOutputName':$("#parentOutputName_val_"+outputModifyArray[i]).val()
					});
				}
			}
			return outputModifyDataArray;
    	},
    	/**
    	 * 获得出参新增数据
    	 */
    	_getOutputAddDataArray:function(){
    		var outputAddDataArray = [];
			if(outputAddCount>0){
				for(var i=0;i<outputAddCount;i++){
					outputAddDataArray.push({
						'outputName':$("#add_outputName_val_"+i).val(),
						'parentOutputName':$("#add_parentOutputName_val_"+i).val()
					});
				}
			}
			return outputAddDataArray;
    	},
    	_cancelSave: function(){
    		window.history.go(-1);
			$('#serviceForm')[0].reset();
    	},
    	/**
    	 * 保存事件
    	 */
    	_saveServiceDesign: function(){
    		var validateForm = $("#serviceForm").validate();
    		if(!validateForm.form()){
				return;
			}
			var inputModifyDataArray=this._getInputModifyDataArray();
			var inputAddDataArray = this._getInputAddDataArray();
			var outputModifyDataArray=this._getOutputModifyDataArray();
			var outputAddDataArray = this._getOutputAddDataArray();
			ajaxController.ajax({
				type: "post",
				processing: true,
				message: "保存中，请等待...",
				url: _base+"/serviceDesign/save",
				data:{
					"srvApiId":$("#srvApiId").val(),
					"inputModifyArray":JSON.stringify(inputModifyDataArray),
					"inputDeleteArray":JSON.stringify(inputDeleteArray),
					"inputAddArray":JSON.stringify(inputAddDataArray),
					"outputModifyArray":JSON.stringify(outputModifyDataArray),
					"outputDeleteArray":JSON.stringify(outputDeleteArray),
					"outputAddArray":JSON.stringify(outputAddDataArray)
				},
				success: function(data){
					if("1"===data.statusCode){
						var d = Dialog({
							title:"提示",
							content:"保存成功",
							icon:'success',
							cancelValue: '确 定',
							cancel:function(){
								this.close();
								//保存成功,回退到进入的列表页
								window.history.go(-1);
								$('#serviceForm')[0].reset();
							}
						});
						d.show();
					}
				}
			});
    	}
    });
    
    module.exports = prdlineAddPager;
});

