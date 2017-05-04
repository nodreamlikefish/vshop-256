<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%!
	public static final String GOODS_SEARCH_URL = "pages/front/goods/GoodsActionFront!search.action" ;
	public static final String MEMBER_BASE_EDIT_URL = "pages/front/center/member/MemberCenterActionFront!eidtBasePre.action" ;
	public static final String LOGOUT_URL = "MemberLoginActionFront!logout.action" ;
	public static final String SHOPCAR_LIST_URL = "pages/front/center/shopcar/ShopcarActionFront!list.action" ;
	public static final String ORDERS_LIST_URL = "pages/front/center/orders/OrdersActionFront!list.action" ;
	public static final String ADDRESS_LIST_URL = "pages/front/center/address/MemberAddressActionFront!list.action" ;
%>
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
	<div class="navbar-header">
		<a class="navbar-brand" href="index.jsp"><strong>微商城</strong></a>
	</div>
	<ul class="nav navbar-nav">
		<c:if test="${mid==null}"> 
			<li><a href="login.jsp"><span class="glyphicon glyphicon-certificate"></span>&nbsp;登录</a></li>
		</c:if>
		<c:if test="${mid!=null}"> 
			<c:if test="${fn:contains(allRoles,'center')}">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><span class="glyphicon glyphicon-globe"></span>&nbsp;个人中心<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<%=MEMBER_BASE_EDIT_URL%>">
							<span class="glyphicon glyphicon-user"></span>&nbsp;个人资料</a></li>
						<li><a href="<%=ADDRESS_LIST_URL%>">
							<span class="glyphicon glyphicon-plane"></span>&nbsp;地址管理</a></li>
						<li class="divider">&nbsp;</li>
						<li><a href="<%=ORDERS_LIST_URL%>">
							<span class="glyphicon glyphicon-list-alt"></span>&nbsp;订单列表</a></li>
					</ul></li>
			</c:if>
			<c:if test="${fn:contains(allRoles,'shopcar')}">
			<li><a href="<%=SHOPCAR_LIST_URL%>">
				<span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;我的购物车</a></li>
			</c:if>
		</c:if>
	</ul> 
	<form class="navbar-form navbar-left" action="<%=GOODS_SEARCH_URL%>" method="post">
		<div class="form-group"> 
			<input type="text" name="kw" id="kw" value="${keyWord}" class="form-control input-xs" placeholder="请输入商品关键字..." style="width:600px;background: #F5F5F5;height:30px;">
			<button class="btn btn-danger" style="height:30px;">搜索</button>
		</div>
	</form>

	<c:if test="${mid!=null}"> 
	<ul class="nav navbar-nav navbar-right">
		<li class="dropdown"><a href="javascript:;"
			class="dropdown-toggle" data-toggle="dropdown"> <i
				class="glyphicon glyphicon-user"></i>&nbsp;${name}&nbsp;<span
				class="glyphicon glyphicon-chevron-down"></span></a>
			<ul class="dropdown-menu main-list">
				<li><a href="pages/front/center/member/member_password_edit.jsp"><i class="glyphicon glyphicon-edit"></i>&nbsp;修改密码</a></li>
				<c:if test="${fn:contains(allRoles,'admin')}">
					<li><a href="pages/back/index.jsp"><i class="glyphicon glyphicon-home"></i>&nbsp;管理中心</a></li>
				</c:if>
				<li class="divider"></li>
				<li><a href="<%=LOGOUT_URL%>"><i class="glyphicon glyphicon-off"></i>&nbsp;登录注销</a></li>
			</ul></li>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
	</ul>
	</c:if>
</nav>
