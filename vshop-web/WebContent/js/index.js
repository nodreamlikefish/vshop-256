$(function() {

	
	$(goodsDiv).empty() ;
	$.get("goods.json",function(data){
		for (x = 0 ;  x < data.allGoodss.length ; x ++) {
			itemData = 
				"<div class='col-md-3 text-center'>" +
				"	<p>" +
				"		<a href='pages/front/goods/GoodsActionFront!show.action?gid="+data.allGoodss[x].gid+"'>" +
				"			<img src='upload/goods/"+data.allGoodss[x].photo+"' style='width:100px; height:100px;'></a></p>" +
				"		<span class='text-warning h4'><strong>￥"+data.allGoodss[x].price+"</strong></span>" +
				"	<p><a href='pages/front/goods/GoodsActionFront!show.action?gid="+data.allGoodss[x].gid+"''>"+data.allGoodss[x].title+"</a></p>" +
				"	<button id='addCar-"+data.allGoodss[x].gid+"' class='btn btn-primary btn-xs'>" +
				"		<span class='glyphicon glyphicon-shopping-cart'></span>&nbsp;加入购物车</button>" +
				"</div>" ;
			$(goodsDiv).append(itemData) ;
		}
		bindAddcar() ;	// 调用事件绑定处理函数
	},"json") ;
	
	$.get("item.json",{},function(data){
		for (x = 0 ; x < data.items.length ; x ++){
			itemData = 
				"<div class='panel panel-primary'>" +
				"		<div class='panel-heading'>" + 
				"			<h4 class='panel-title'>" +
				"				<a data-toggle='collapse' data-parent='#item' href='#content-"+data.items[x].item.iid+"'>" + 
				"					" + data.items[x].item.title +
				"				</a>" +
				"			</h4>" +
				"		</div>" +
				"		<div id='content-"+data.items[x].item.iid+"' class='panel-collapse collapse "+(x == 0 ? "in" : "")+"'>" +
				"			<div class='panel-body'>" +
				"				<div class='row'>"  ;
			for (y = 0 ; y < data.items[x].subitems.length ; y ++) {
				itemData += "<div class='col-md-4'><a href='pages/front/goods/GoodsActionFront!list.action?sid="+data.items[x].subitems[y].sid+"'>"+data.items[x].subitems[y].title+"</a></div>" ;
			}
			itemData += "				</div>" +
						"			</div>" +
						"		</div>" +
						"	</div>" ;
			$(item).append(itemData) ;
		}
	},"json") ;

}) 