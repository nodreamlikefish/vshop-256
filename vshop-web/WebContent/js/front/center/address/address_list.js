$(function() {
	$("#selectAll").on("click",function(){
		checkboxSelectAll('aid',this.checked) ;
	}) ;
	$(defBtn).on("click",function(){	// 绑定用户锁定操作
		adid = $("#adid:checked").val() ;	// 取得要设置为当前默认地址的编号
		$.post("pages/front/center/address/MemberAddressActionFront!editDeflag.action", {"adid":adid},function(data){
			operateAlert(data.trim() == "true","默认配送地址修改成功！","默认配送地址修改失败！") ;
		},"text") ;
	}) ;
})