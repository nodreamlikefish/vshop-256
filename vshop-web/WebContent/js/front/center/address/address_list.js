$(function() {
	$("#selectAll").on("click",function(){
		checkboxSelectAll('aid',this.checked) ;
	}) ;
	$(defBtn).on("click",function(){	// 绑定用户锁定操作  
		operateAlert(false,"默认配送地址修改成功！","默认配送地址修改失败！") ;
	}) ;
})