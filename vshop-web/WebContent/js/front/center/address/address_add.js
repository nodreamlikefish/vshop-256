var addResult = "" ;	// 保存地址信息
function fillAddr() {
	inputAddr = $(addr).val() ;	// 获取原始数据输入
	if (inputAddr.length > 0) {	// 表示现在有数据
		if (inputAddr.split(" ").length == 3) {
			addResult = inputAddr.split(" ") [2] ;
		}
	}
	var v ;	// 保存地址的数据信息 
	// 进行省份信息的内容显示
	if ($("#pid option:selected").val() != "") {
		v = $("#pid option:selected").text() + " " ;
	}
	if ($("#cid option:selected").val() != "") {
		v = v + $("#cid option:selected").text() + " " ;
	}
	if (undefined == addResult || addResult == "") {
		$(addr).val(v) ;
	} else {
		$(addr).val(v + addResult) ;
	}
}

$(function() {
	$(cid).on("change",function(){
		fillAddr() ;
	}) ;
	$(pid).on("change",function(){
		$.post("pages/front/center/address/CityActionFront!list.action",{pid:this.value},
				function(data) {
			$("#cid option:gt(0)").remove() ;
			for (x = 0 ; x < data.allCitys.length ; x ++) {
				$(cid).append("<option value='"+data.allCitys[x].cid+"'>"+data.allCitys[x].title+"</option>") ;
			}
			fillAddr() ;
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
			"receiver" : {
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