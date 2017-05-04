<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/back/admin/orders/orders_list.js"></script>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/back/include_menu_item.jsp" />
		<div class="content-wrapper text-left">
			<!-- 此处编写需要显示的页面 --> 
			<div class="panel panel-success">
				<div class="panel-heading">
					<strong><span class="glyphicon glyphicon-list"></span>&nbsp;订单信息列表</strong>
				</div>
				<div class="panel-body">
					<jsp:include page="/pages/plugins/split_plugin_search_bar.jsp"/>
					<table class="table table-condensed">
						<thead>
							<tr>
								<th class="text-center"><strong>订单编号</strong></th>
								<th class="text-center"><strong>用户ID</strong></th>
								<th class="text-center"><strong>总价</strong></th>
								<th class="text-center"><strong>下单日期</strong></th>
								<th class="text-center"><strong>操作</strong></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${allOrderss}" var="orders">
								<tr>
									<td class="text-center">${orders.oid}</td>
									<td class="text-center"><a id="userBtn-${orders.mid}" onmouseover="this.style.cursor='hand'">${orders.mid}</a></td>
									<td class="text-center">${orders.price}</td>
									<td class="text-center">${orders.subdate}</td>
									<td class="text-center">
										<a type="button" class="btn btn-info btn-xs" href="pages/back/admin/orders/OrdersActionBack!show.action?oid=${orders.oid}">
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
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/pages/plugins/back/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/pages/plugins/back/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<jsp:include page="/pages/plugins/back/include_javascript_foot.jsp" />
	<jsp:include page="/pages/plugins/back/info/include_member_modal.jsp"/>
<jsp:include page="/pages/plugins/back/back_footer.jsp"/>
