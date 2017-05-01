$(function() {
	$(pid).on("change",function(){
		$.post("pages/front/center/address/CityActionFront!list.action",{pid:this.value},
				function(data) {
			$("#cid option:gt(1)").empty() ;
			for (x = 0 ; x < data.allCitys.length ; x ++) {
				$(cid).append("<option value='"+data.allCitys[x].cid+"'>"+data.allCitys[x].title+"</option>") ;
			}
		},"json") ;
	}) ;
	
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			form.submit(); // 提交表单
		},
		errorPlacement : function(error, element) {
			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
		},
		highlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
				});

			})
		},
		unhighlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1,function() {
						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",
		rules : {
			"name" : {
				required : true,
			},
			"phone" : {
				required : true ,
				digits : true
			} ,
			"pid" : {
				required : true 
			},
			"cid" : {
				required : true 
			},
			"addr" : {
				required : true 
			}
		}
	});
})