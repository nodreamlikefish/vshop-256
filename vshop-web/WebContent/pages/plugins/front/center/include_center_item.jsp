<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%!
	public static final String MEMBER_BASE_EDIT_URL = "pages/front/center/member/MemberCenterActionFront!eidtBasePre.action" ;
	public static final String MEMBER_ADDRESS_LIST_URL = "pages/front/center/address/MemberAddressActionFront!list.action" ;
	public static final String SHOPCAR_LIST_URL = "pages/front/center/shopcar/ShopcarActionFront!list.action" ;
	public static final String ORDERS_LIST_URL = "pages/front/center/orders/OrdersActionFront!list.action" ;
%>
<ul class="nav nav-pills nav-stacked">									<!-- 定义导航 -->
	<li class="${param.ch == 1 ? "active" : ""}"><a href="<%=ORDERS_LIST_URL%>">我的订单</a></li>			<!-- 活跃导航项 -->
	<li class="${param.ch == 2 ? "active" : ""}"><a href="<%=MEMBER_BASE_EDIT_URL%>">个人信息</a></li>
	<li class="${param.ch == 3 ? "active" : ""}"><a href="pages/front/center/member/member_password_edit.jsp">修改密码</a></li>
	<li class="${param.ch == 4 ? "active" : ""}"><a href="<%=SHOPCAR_LIST_URL%>">购物车</a></li>			<!-- 禁用导航项 -->
	<li class="${param.ch == 5 ? "active" : ""}"><a href="<%=MEMBER_ADDRESS_LIST_URL%>">地址管理</a></li>
</ul>
