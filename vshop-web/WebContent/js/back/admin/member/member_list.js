$(function(){
	editMid = null ;
	$("#selectAll").on("click",function(){
		checkboxSelectAll('mid',this.checked) ;
	}) ;
	$("#lockBtn").on("click",function(){	// 绑定用户锁定操作
		operateChecked("确定要锁定这些用户吗？","mid",'pages/jsp/admin/UserActionBack!lock.action?p=p') ;
	}) ;
	$("a[id*=userBtn-]").each(function(){
		// 拆分id数据
		var mid = this.id.split("-")[1] ;
		// console.log("用户ID：" + mid) ;
		$(this).on("click",function(){
			// Ajax异步读取用户信息
			// 将异步加载信息填充到模态窗口的组件之中
			$("#userMid").text(mid) ;
			$("#userid").val(mid) ;
			$("#userInfo").modal("toggle") ;	// 显示模态窗口
		})
	}) ;
	$("a[id*=editBtn-]").each(function(){
		// 拆分id数据
		var mid = this.id.split("-")[1] ;
		$(this).on("click",function(){
			// Ajax异步读取管理员信息
			// 将异步加载信息填充到模态窗口的组件之中
			$("#userMid2").text(mid) ;
			editMid = mid ;
			$("#userPassword").modal("toggle") ;	// 显示模态窗口
		}) ;
	}) ;
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			$.post("pages/back/admin/member/MemberActionBack!editPassword.action",
					{"mid":editMid,"password":$(password).val()},function(data){
						$("#userPassword").modal("toggle") ;
						operateAlert(data.trim() == "true","用户密码修改成功！","用户密码修改失败！") ;
					},"text") ;
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
			"password" : {
				required : true
			}
		}
	});
})

