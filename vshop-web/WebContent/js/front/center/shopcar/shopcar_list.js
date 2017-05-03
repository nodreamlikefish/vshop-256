function calAllPrice() {
	allPrice = 0.0 ;
	$("span[id^=price-]").each(function(){
		id = this.id.split("-")[1] ;
		price = parseFloat($(this).text()) ;
		amount = $("#amount-" + id).val() ;	// 当前数量
		allPrice += price * amount ;
	}) ;
	$("#allPrice").text(allPrice) ;
}
$(function() {
	calAllPrice() ;
	
	$("button[id^=updateBtn-]").each(function(){
		$(this).on("click",function(){
			gid = this.id.split("-")[1] ;
			amount = parseInt($("#amount-" + gid).val()) ;
			$.post("pages/front/center/shopcar/ShopcarActionFront!edit.action",
					{"gid":gid,"amount":amount},function(data){
						if (data.trim() == "true") {
							if (amount == 0) {
								$("#shopcar-" + gid).remove() ;	// 删除当前行
								operateAlert(true,"购物车商品删除成功！","购物车商品删除失败！") ;
							} else {
								operateAlert(true,"购物车信息更新成功！","购物车信息更新失败！") ;
							}
							$("#updateBtn-" + gid).attr("class","btn btn-primary") ;
						}
					},"text") ;
		}) ;
	}) ;
	
	// 绑定所有的加法处理操作
	$("button[id^=add-]").each(function(){
		$(this).on("click",function(){
			gid = this.id.split("-")[1] ;
			amount = parseInt($("#amount-" + gid).val()) ;
			$("#amount-" + gid).val(amount + 1) ;
			$("#updateBtn-" + gid).attr("class","btn btn-warning") ;
			calAllPrice() ;
		}) ;
	}) ;
	// 绑定所有的减法处理操作
	$("button[id^=sub-]").each(function(){
		$(this).on("click",function(){
			gid = this.id.split("-")[1] ;
			amount = parseInt($("#amount-" + gid).val()) ;
			if (amount - 1 < 0) {
				
			} else {
				$("#amount-" + gid).val(amount - 1) ;
			}
			calAllPrice() ;
			$("#updateBtn-" + gid).attr("class","btn btn-warning") ;
		}) ;
	}) ;
	$("#selectAll").on("click",function(){
		checkboxSelectAll('gid',this.checked) ;
	}) ;
	$("#rmBtn").on("click",function(){	// 绑定用户锁定操作
		operateChecked("确定要删除这些商品吗？","gid",'pages/back/admin/goods/GoodsActionBack!rm.action?p=p') ;
	}) ;
})