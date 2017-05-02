$(function() {
	$("button[id^=deleteBtn]").each(function() {
		$(this).on("click",function() {
			adid = this.id.split("-")[1] ;
			if (window.confirm("您确定要删除此地址信息吗？")) {
				$.post("pages/front/center/address/MemberAddressActionFront!delete.action",{"adid":adid},function(data){
					operateAlert(data.trim() == "true","配送地址删除成功！","配送地址删除失败！") ;
					if (data.trim() == "true") {
						$("#address-" + adid).remove() ;	// 删除当前行元素
					}
				},"text") ;
			}
		}) ;
	})
	
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