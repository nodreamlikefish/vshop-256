<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/back/admin/goods/goods_list.js"></script>
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
					<strong><span class="glyphicon glyphicon-list"></span>&nbsp;商品信息列表</strong>
				</div>
				<div class="panel-body">
					<jsp:include page="/pages/plugins/split_plugin_search_bar.jsp"/>
					<table class="table table-condensed">
						<thead>
							<tr>
								<th class="text-center">
									<input type="checkbox" id="selectAll">
								</th>
								<th class="text-center"><strong>商品名称</strong></th>
								<th class="text-center"><strong>发布者</strong></th>
								<th class="text-center"><strong>价格</strong></th>
								<th class="text-center"><strong>发布日期</strong></th>
								<th class="text-center"><strong>操作</strong></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${allGoodss}" var="goods">
							<tr id="goods-${goods.gid}">
								<td class="text-center">
									<input type="checkbox" id="gid" name="gid" value="${goods.gid}">
								</td>
								<td class="text-center">
									<a id="showBtn-${goods.gid}" onmouseover="this.style.cursor='hand'">${goods.title}</a>
								</td>
								<td class="text-center">${goods.mid}</td>
								<td class="text-center">
									<fmt:formatNumber value="${goods.price}"/>
								</td>
								<td class="text-center"><fmt:formatDate value="${goods.pubdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td class="text-center">
									<a type="button" class="btn btn-info btn-xs" href="pages/back/admin/goods/GoodsActionBack!editPre.action?gid=${goods.gid}">
										<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</a>
								</td>
							</tr> 
							</c:forEach>
						</tbody>
					</table>
					<div>
						<button class="btn btn-danger" id="rmBtn">删除商品</button>
						<button id="createData" class="btn btn-info"><span class="glyphicon glyphicon-file"></span>&nbsp;生成前台商品数据</button>
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
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/pages/plugins/back/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/pages/plugins/back/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<jsp:include page="/pages/plugins/back/include_javascript_foot.jsp" />
	<jsp:include page="/pages/plugins/back/info/include_goods_modal.jsp"/>
<jsp:include page="/pages/plugins/back/back_footer.jsp"/>
