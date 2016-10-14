(function($) {	
	$.fn.fonts = function(option){
		option = $.extend({},$.fn.fonts.option,option);
		return this.each(function(){
		var objString = $(this).text(),
		    objLength = $(this).text().length,
			num = option.fontNum;
		if(objLength > num){
            objString = $(this).text(objString.substring(0,num) + "…");
		}
		 })
	}
	// default options
	$.fn.fonts.option = {
	fontNum:100 //font num
	};
})(jQuery);