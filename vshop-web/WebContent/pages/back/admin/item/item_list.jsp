<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/back/admin/item/item_list.js"></script>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/back/include_menu_item.jsp" />
		<div class="content-wrapper text-left">
			<div class="panel panel-success">
				<div class="panel-heading">
					<strong>商品分类列表</strong>
				</div>
				<div class="panel-body">
					<table class="table table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<td style="width:30%;" class="text-center"><strong>栏目名称</strong></td>
								<td style="width:10%;" class="text-center"><strong>操作</strong></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${allItems}" var="item">
								<tr>
									<td class="text-center">
										<input type="text" id="title-${item.iid}" name="title-${item.iid}" class="form-control input-sm" value="${item.title}">
									</td>
									<td class="text-center">
										<button class="btn btn-primary" id="updateBtn-${item.iid}"><span class="glyphicon glyphicon-edit"></span>&nbsp;修改</button>
										<a class="btn btn-warning" href="pages/back/admin/item/SubitemActionBack!list.action?iid=${item.iid }"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;管理子栏目</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
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
<jsp:include page="/pages/plugins/back/back_footer.jsp"/>
