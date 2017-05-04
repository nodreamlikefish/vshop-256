<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/front/front_header.jsp"/>
<script type="text/javascript" src="js/front/center/orders/orders_list.js"></script>
<%!
	public static final String ORDERS_DETAILS_SHOW_URL = "pages/front/center/orders/OrdersActionFront!details.action"; 
%>
<body class="back">
	<div class="container contentback">
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
						<strong><span class="glyphicon glyphicon-list"></span>&nbsp;订单信息列表</strong>
					</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th class="text-center"><strong>订单编号</strong></th>
									<th class="text-center"><strong>总价</strong></th>
									<th class="text-center"><strong>下单日期</strong></th>
									<th class="text-center"><strong>操作</strong></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allOrderss}" var="orders">
									<tr>
										<td class="text-center">${orders.oid}</td>
										<td class="text-center">${orders.price}</td>
										<td class="text-center">${orders.subdate}</td>
										<td class="text-center"> 
											<a type="button" class="btn btn-primary btn-xs" href="<%=ORDERS_DETAILS_SHOW_URL%>?oid=${orders.oid}">
												<span class="glyphicon glyphicon-list-alt"></span>&nbsp;查看详情</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
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
		<div id="footDiv" class="row navbar-fixed-bottom">
			<jsp:include page="/pages/plugins/front/include_foot.jsp" />
		</div>
	</div>
<jsp:include page="/pages/plugins/front/front_footer.jsp"/>
