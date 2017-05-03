$(function(){
	$(createData).on("click",function(){
		$.post("pages/back/admin/goods/GoodsActionBack!listDetails.action",{},
				function(data){
			operateAlert(data.trim() == "true","商品数据创建成功！","商品数据创建失败！") ;
		},"text") ;
	}) ;
	$("#selectAll").on("click",function(){
		checkboxSelectAll('gid',this.checked) ;
	}) ;
	$("#rmBtn").on("click",function(){	// 绑定用户锁定操作
		if (window.confirm("确定要删除这些商品信息吗？")) {
			deleteGid = "" ;
			gids = new Array() ;	// 建立一个数组
			foot = 0 ;
			$("#gid:checked").each(function(){
				deleteGid = deleteGid + this.value + "," ;
				gids[foot ++] = this.value ;
			}) ;
			if (deleteGid == "") {	// 没有要删除的信息
				operateAlert(false,"xxx","您还未选择删除的商品信息") ;
			} else {
				$.post("pages/back/admin/goods/GoodsActionBack!delete.action",
						{"gids":deleteGid},function(data){
							if (data.trim() == "true") {
								operateAlert(data.trim() == "true","商品信息删除成功！","商品信息删除失败！") ;
								for (x = 0 ; x < gids.length ; x ++) {
									$("#goods-" + gids[x]).remove() ;
								}
							}
						},"text") ;
			}
			console.log(deleteGid) ;
		}
	}) ;
	$("a[id*=showBtn-]").each(function(){
		// 拆分id数据
		var gid = this.id.split("-")[1] ;
		$(this).on("click",function(){
			// console.log("商品ID：" + gid) ;
			$.post("pages/back/admin/goods/GoodsActionBack!show.action",{"gid":gid},
					function(data){
				$("#modal-photo").attr("src","upload/goods/" + data.goods.photo) ;
				$("#modal-title").text(data.goods.title) ;
				$("#modal-item").text(data.item.title) ;
				$("#modal-subitem").text(data.subitem.title) ;
				$("#modal-price").text(data.goods.price) ;
				$("#modal-mid").text(data.goods.mid) ;
				$("#modal-pubdate").text(new Date(data.goods.pubdate.time).format("yyyy-MM-dd hh:mm:ss")) ;
				$("#modal-note").html(data.goods.note) ;
			},"json") ;
			// Ajax异步读取用户信息
			// 将异步加载信息填充到模态窗口的组件之中
			$("#goodsInfo").modal("toggle") ;	// 显示模态窗口
		})
	}) ;
}) ;