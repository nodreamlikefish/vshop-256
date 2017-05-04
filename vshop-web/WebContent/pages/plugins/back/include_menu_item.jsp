<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="upload/member/nophoto.png" class="img-circle"
					alt="User Image">
			</div>
			<div class="pull-left info">
				<p>${name}</p>
			</div> 
		</div>
		<!-- /.search form -->
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header"><i class="fa fa-slack"></i> 微商城管理系统</li>
			<c:if test="${fn:contains(allRoles,'member') }">
				<li class="treeview"><a href="${basePath}pages/back/index.jsp"> <i
						class="fa fa-folder-open"></i> <span>用户管理</span> <i
						class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<c:if test="${fn:contains(allActions,'member:list') }">
							<li><a href="pages/back/admin/member/MemberActionBack!list.action"><i class="fa fa-circle-o"></i>
								用户列表</a></li>
						</c:if>
					</ul></li>
			</c:if>
			<c:if test="${fn:contains(allRoles,'goods') }">
				<li class="treeview"><a href="${basePath}pages/back/index.jsp"> <i class="fa  fa-folder-open"></i>
						<span>商品管理</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<c:if test="${fn:contains(allActions,'goods:item') }">
						<li><a href="pages/back/admin/item/ItemActionBack!list.action"><i
							class="fa fa-circle-o"></i> 商品分类</a></li>
					</c:if>
					<c:if test="${fn:contains(allActions,'goods:list') }">
						<li><a href="pages/back/admin/goods/GoodsActionBack!list.action"><i
							class="fa fa-circle-o"></i> 商品列表</a></li>
					</c:if>
					<c:if test="${fn:contains(allActions,'goods:add') }">
						<li><a href="pages/back/admin/goods/GoodsActionBack!addPre.action"><i
							class="fa fa-circle-o"></i> 商品上架</a></li>
					</c:if>
				</ul></li>
			</c:if>
			<c:if test="${fn:contains(allRoles,'orders') }">
				<li class="treeview"><a href="${basePath}pages/index.jsp"> <i class="fa  fa-folder-open"></i>
						<span>订单管理</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<c:if test="${fn:contains(allActions,'orders:list') }">
						<li><a href="pages/back/admin/orders/OrdersActionBack!list.action"><i 
							class="fa fa-circle-o"></i> 订单列表</a></li>
					</c:if>
				</ul></li>
			</c:if>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>