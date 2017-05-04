function calAllPrice() {
	allPrice = 0.0 ;
	$(addBtn).on("click",function() {
		gid = "" ;
		$("#gid:checked").each(function(){
			gid += $(this).val() + "," ;
		}) ;
		if (gid == "") {
			operateAlert(false,"","请选择要购买的商品！") ;
		} else {
			window.location = "pages/front/center/orders/OrdersActionFront!createPre.action?gid="+gid ;
		}
	}) ;
	
	$("span[id^=price-]").each(function(){
		id = this.id.split("-")[1] ;
		price = parseFloat($(this).text()) ;
		amount = $("#amount-" + id).val() ;	// 当前数量
		allPrice += price * amount ;
	}) ;
	$("#allPrice").text(round(allPrice,2)) ;
}
$(function() {
	calAllPrice() ;
	
	$("#rmBtn").on("click",function(){	// 绑定用户锁定操作
		gid = "" ;
		$("#gid:checked").each(function(){
			gid += $(this).val() + "," ;
		}) ;
		if (gid != "") {
			if (window.confirm("亲，您真的不要了吗？不要啊。。。不要的。。。")) {
				$.post("pages/front/center/shopcar/ShopcarActionFront!delete.action",{"sc" : gid},function(data){
					if (data.trim() == "true") {
						operateAlert(true,"购物车信息删除成功！","购物车信息删除失败！") ;
						$("#gid:checked").each(function(){
							$("#shopcar-" + $(this).val()).remove() ;
						}) ;
						calAllPrice() ;
					}
				},"text") ;
			}
		} else {
			operateAlert(false,"","对不起，你还未选择要删除的商品！") ;
		}
	}) ;
	
	$(editBtn).on("click",function(){
		sc = "" ;	// 发送的字符串
		$(".btn-warning").each(function(){
			gid = this.id.split("-")[1] ;	// 商品编号
			sc += gid + ":" + $("#amount-" + gid).val() + ",";
		}) ;
		$.post("pages/front/center/shopcar/ShopcarActionFront!editAmount.action",{"sc" : sc},function(data){
			if (data.trim() == "true") {
				operateAlert(true,"购物车信息更新成功！","购物车信息更新失败！") ;
				$(".btn-warning").each(function(){
					gid = this.id.split("-")[1] ;	// 商品编号
					amount = parseInt($("#amount-" + gid).val()) ;
					if (amount == 0) {
						$("#shopcar-" + gid).remove() ;
					} else {
						$("#updateBtn-" + gid).attr("class","btn btn-primary") ;
					}
				}) ;
			}
		},"text") ;
	}) ;
	
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
})