<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/pages/plugins/front/front_header.jsp"/>
<script type="text/javascript" src="js/front/center/member/member_password_edit.js"></script>
<%!
	public static final String MEMBER_EDIT_PASSWORD_URL = "pages/front/center/member/MemberCenterActionFront!editPasssword.action" ;
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
					<jsp:param value="3" name="ch"/>
				</jsp:include>
			</div> 
			<div class="col-md-10 col-xs-10">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<strong>修改登录密码</strong> 
					</div>
					<div class="panel-body">
						<form class="form-horizontal" id="myform" method="post" action="<%=MEMBER_EDIT_PASSWORD_URL%>">
							<fieldset> 
								<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
								<div class="form-group" id="oldpasswordDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="oldpassword">原始密码：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="password" id="oldpassword" name="oldpassword"
											class="form-control" placeholder="请输入原始登录密码">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="oldpasswordMsg"></div>
								</div>
								<div class="form-group" id="newpasswordDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="newpassword">新的密码：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="password" id="newpassword" name="newpassword"
											class="form-control" placeholder="请输入新的登录密码">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="newpasswordMsg"></div>
								</div>
								<div class="form-group" id="confpasswordDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="confpassword">确认密码：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="password" id="confpassword" name="confpassword"
											class="form-control" placeholder="请重复输入密码">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="confpasswordMsg"></div>
								</div>
								<div class="form-group">
									<div class="col-md-5 col-md-offset-3">
										<button type="submit" class="btn btn-primary">修改</button>
										<button type="reset" class="btn btn-warning">重置</button>
									</div>
								</div>
							</fieldset>
						</form>

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
