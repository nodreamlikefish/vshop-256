<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/front/front_header.jsp"/>
</head>
<body>
	<div class="container"> 
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div style="height: 60px;"></div>
		<div id="contentDiv" class="row">
			<div class="col-md-2 col-xs-2">
				<jsp:include page="/pages/plugins/front/center/include_center_item.jsp">
					<jsp:param value="1" name="ch"/>
				</jsp:include>
			</div>
			<div class="col-md-10 col-xs-10">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-eye-open"></span>&nbsp;订单详情</strong>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3"><strong>订单编号：</strong></div>
							<div class="col-md-9 col-md-pull-1">${orders.oid}</div>
						</div>
						<div class="row">
							<div class="col-md-3"><strong>下单用户：</strong></div>
							<div class="col-md-9 col-md-pull-1">${orders.mid}</div>
						</div>
						<div class="row">
							<div class="col-md-3"><strong>下单日期：</strong></div>
							<div class="col-md-9 col-md-pull-1">${orders.subdate}</div>
						</div>
						<div class="row">
							<div class="col-md-3"><strong>总金额：</strong></div>
							<div class="col-md-9 col-md-pull-1">${orders.price}</div>
						</div>
						<div class="row">
							<div class="col-md-3"><strong>购买商品总数：</strong></div>
							<div class="col-md-9 col-md-pull-1">${fn:length(allGoodss) }</div>
						</div>
						<div class="row">
							<table class="table table-condensed">
							<thead>
								<tr>
									<th class="text-center"><strong>商品名称</strong></th>
									<th class="text-center"><strong>价格</strong></th>
									<th class="text-center"><strong>购买数量</strong></th>
									<th class="text-center"><strong>总额</strong></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allGoodss}" var="goods">
									<tr>
										<td class="text-center">
											<a href="pages/front/goods/GoodsActionFront!show.action?gid=${goods.gid}" onmouseover="this.style.cursor='hand'">${goods.title}</a>
										</td>
										<td class="text-center">${goods.price}</td>
										<td class="text-center">${allDetailss[goods.gid]}</td>
										<td class="text-center">${allDetailss[goods.gid] * goods.price}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
					</div>
					<div class="panel-footer">
						<jsp:include page="/pages/plugins/include_alert.jsp"/>
					</div>
				</div>
			</div>
		</div>
		<div id="footDiv" class="row navbar-fixed-bottom">
			<jsp:include page="/pages/plugins/front/include_foot.jsp" />
		</div>
	</div>
<jsp:include page="/pages/plugins/front/front_footer.jsp"/>
