<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/front/front_header.jsp"/>
<script type="text/javascript" src="js/index.js"></script>
<body>  
	<div class="container contentback">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div style="height: 60px;"></div> 
		<div id="contentDiv" class="row">
			<div class="col-md-3">
				<jsp:include page="/pages/plugins/front/include_menu_item.jsp"/>
			</div>
			<div class="col-md-9">
				<div class="row">
					<%-- <jsp:include page="/pages/plugins/split_plugin_search_bar.jsp"/> --%>
				</div>
				<div class="row" id="goodsDiv">
				</div> 
			</div>
			<div id="splitBarDiv" style="float:right">
				<%-- <jsp:include page="/pages/plugins/split_plugin_page_bar.jsp"/> --%> 
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
