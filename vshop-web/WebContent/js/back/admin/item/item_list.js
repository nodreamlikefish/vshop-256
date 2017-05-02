$(function() {
	$("button[id*='updateBtn-']").each(function(){
		var iid = this.id.split("-")[1];	// 分离出id信息
		$(this).on("click",function(){
			var title = $("#title-" + iid).val() ;
			// console.log("actid = " + iid + "，title = " + title) ;
			// 编写Ajax异步更新操作
			$.post("pages/back/admin/item/ItemActionBack!edit.action",{"iid":iid,"title":title},function(data){
				operateAlert(data.trim() == "true","栏目信息修改成功！","栏目信息修改失败！") ;
			},"text") ;
		}) ;
	}) ;
})