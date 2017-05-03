<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/front/front_header.jsp"/>
<script type="text/javascript" src="js/front/goods/goods_list.js"></script>
<body>
	<div class="container contentback">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div style="height: 60px;"></div> 
		<div id="contentDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-edit"></span>&nbsp;商品信息</strong>
					</div>
					<div class="panel-body">
						<div class="row">
							<c:forEach items="${allGoodss}" var="goods">
								<div class="col-md-3 text-center">
									<p>
										<a href="pages/front/goods/GoodsActionFront!show.action?gid=${goods.gid}">
											<img src="upload/goods/${goods.photo}" style="width:100px;height:100px;"></a></p>
									<span class="text-warning h4"><strong>￥${goods.price}</strong></span>
									<p><a href="pages/front/goods/GoodsActionFront!show.action?gid=${goods.gid}">${goods.title}</a></p>
									<button id="addCar-${goods.gid}" class="btn btn-primary btn-xs">
										<span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;加入购物车</button>
								</div>
							</c:forEach>
						</div> 
						<div id="splitBarDiv" style="float:right">
							<jsp:include page="/pages/plugins/split_plugin_page_bar.jsp"/>
						</div>
					</div>
					<div class="panel-footer">
						<jsp:include page="/pages/plugins/include_alert.jsp"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="height:50px;">
			<jsp:include page="/pages/plugins/include_alert.jsp"/>
		</div>
		<div id="footDiv" class="row navbar-fixed-bottom">
			<jsp:include page="/pages/plugins/front/include_foot.jsp" />
		</div>
	</div>
<jsp:include page="/pages/plugins/front/front_footer.jsp"/>
