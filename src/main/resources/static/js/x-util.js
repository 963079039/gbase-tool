/**
 * ajax请求
 * 
 * @param url
 *            提交地址
 * @param params
 *            参数(json格式,例{name:'xucheng',age:25})
 * @param callBack
 *            回调函数
 * @param sync
 *            默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
 */
jQuery.extend({
	postAjax : function(url, params, callBack, sync) {
		if (params == null) {
			params = {};
		}
		params.random = Math.random();
		$.ajax({
			type : 'POST',
			async : sync,
			cache : false,
			dataType : 'json',
			url : url,
			data : params,
            //contentType: "application/json",
			success : function(json) {
				if(json.result == 1){
                    callBack.call(this, json);
				}else{
                    callBack.call(this, null);
				}
			},
			error : function() {
				alert('对不起，系统错误！');
			}
		});
	},

    postJsonAjax : function(url, params, callBack, sync) {
        if (params == null) {
            params = {};
        }
        $.ajax({
            type : 'POST',
            async : sync,
            cache : false,
            dataType : 'json',
            url : url,
            data : params,
            contentType: "application/json",
            success : function(json) {
                if(json.result == 1){
                    callBack.call(this, json);
                }else{
                    callBack.call(this, null);
                }
            },
            error : function() {
                alert('对不起，系统错误！');
            }
        });
    }
});


var DomUtils = {
	
	/*
	 * jsonObj:必须是一个json对象;
	 * idArr:给指定id的dom节点设置html内容;
	 * prefix:dom节点id的前缀,除去前缀剩下的应和json的key相同;
	 * 
	 * 后两个参数不填,默认json的key和dom节点的id完全相同
	 */
	setHtml : function(jsonObj,prefix,idArr) {
		
		if($.isEmptyObject(jsonObj)){
			return;
		}
		if(idArr == null){
			idArr = Json.getKey(jsonObj);
		}
		if (idArr.length>0) {
			for ( var i = 0; i < idArr.length; i++) {
				var tobj = $('#'+(prefix==null?'':prefix) + idArr[i]);
				if (tobj) {
					tobj.html(jsonObj[idArr[i]]);
				}
			}
		}
	},
	clearHtml : function(prefix, idArr) {
		if (idArr) {
			for ( var i = 0; i < idArr.length; i++) {
				var tobj = $('#'+(prefix==null?'':prefix) + idArr[i]);
				if (tobj) {
					tobj.html('');
				}
			}
		}
	},
	// Handlebars渲染数据
	//参数：domId--html元素ID, tempId--Handlebars模板ID, dataList--要渲染的数据
	render : function(domId, tempId, data) {
	    //预编译模板
	    var template = Handlebars.compile($(tempId).html());
	    //输入模板
	    $(domId).html(template(data));
	}
}