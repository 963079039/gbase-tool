Handlebars.registerHelper({
	    //长字符截断
	    'substr' : function(value, length) {
	    	if(value != null && value != ""){
				if(value.length > length){
					var temp = value.substring(0, length);
					return temp + "...";
				}else{
					return value;
				}
			}else{
				return '';
			}
	    },
	    'dateFormat' : function(value, format) {
	    	return dateFormat(value,format)
	    },
	    'addOne' : function(value) {
	    	return parseInt(value)+1;
	    }
	    
});
// date: 需要格式化的日期(日期类型)
function dateFormat(date, format) {
    /*
     * format="yyyy-MM-dd hh:mm:ss";
     */
    if (date != null) {
        var dateTemp = new Date(date);
        var o = {
            "M+" : dateTemp.getMonth() + 1,
            "d+" : dateTemp.getDate(),
            "h+" : dateTemp.getHours(),
            "m+" : dateTemp.getMinutes(),
            "s+" : dateTemp.getSeconds(),
            "q+" : Math.floor((dateTemp.getMonth() + 3) / 3),
            "S" : dateTemp.getMilliseconds()
        }

        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (dateTemp.getFullYear() + "").substr(4 - RegExp.$1.length));
        }

        for ( var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    }
    return "";
}