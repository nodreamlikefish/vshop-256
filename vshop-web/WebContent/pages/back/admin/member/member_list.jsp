<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/back/admin/member/member_list.js"></script>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/back/include_menu_item.jsp" />
		<div class="content-wrapper text-left">
			<div class="col-md-12 col-xs-12">
				<div class="panel panel-success">
					<div class="panel-heading">
						<strong>用户列表</strong>
					</div>
					<div class="panel-body">
						<div id="splitSearchDiv">
							<jsp:include page="/pages/plugins/split_plugin_search_bar.jsp"/>
							<br>
						</div>
						<table class="table table-condensed">
							<thead>
								<tr>
									<th class="text-center">
										<input type="checkbox" id="selectAll">
									</th>
									<th class="text-center"><strong>用户ID</strong></th>
									<th class="text-center"><strong>姓名</strong></th>
									<th class="text-center"><strong>电话</strong></th>
									<th class="text-center"><strong>邮箱</strong></th>
									<th class="text-center"><strong>注册日期</strong></th>
									<th class="text-center"><strong>最后一次活动日期</strong></th>
									<th class="text-center"><strong>状态</strong></th>
									<th class="text-center"><strong>操作</strong></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allMembers}" var="member">
									<tr>
										<td class="text-center">
											<input type="checkbox" id="mid" name="mid" value="${member.mid}">
										</td>
										<td class="text-center">
											<a id="userBtn-${member.mid}" onmouseover="this.style.cursor='hand'">${member.mid}</a>
										</td>
										<td class="text-center">${member.name}</td>
										<td class="text-center">${member.phone}</td>
										<td class="text-center">${member.email}</td>
										<td class="text-center">${member.regdate}</td>
										<td class="text-center">${member.lastdate}</td>
										<td class="text-center">
											<span id="lock-${member.mid}">
												${member.locked == 1 ? "锁定" : "活跃"}
											</span>
										</td>
										<td class="text-center">
											<a id="editBtn-${member.mid}" class="btn btn-xs btn-info" onmouseover="this.style.cursor='hand'">
														<span class="glyphicon glyphicon-edit"></span>&nbsp;变更密码</a>
										</td>
									</tr>
								</c:forEach> 
							</tbody>
						</table>
						<div>
							<button class="btn btn-danger" id="lockBtn">锁定用户</button>
							<button class="btn btn-info" id="unlockBtn">解锁用户</button>
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
		</div>
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/pages/plugins/back/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/pages/plugins/back/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<div class="modal fade" id="userPassword"  tabindex="-1" aria-labelledby="modalTitle" aria-hidden="true" data-keyboard="true">
		<div class="modal-dialog" style="width: 1000px">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h3 class="modal-title">
						<span class="glyphicon glyphicon-cog"></span>
						<strong>修改“<span id="userMid2"></span>”用户密码</strong></h3>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="myform" method="post">
						<fieldset>
							<div class="form-group" id="passwordDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="user.password">登录密码：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<input type="password" id="password" name="password"
										class="form-control" placeholder="请输入新的登录密码">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="passwordMsg"></div>
							</div>
							<div class="form-group">
								<div class="col-md-5 col-md-offset-3">
									<button type="submit" class="btn btn-primary">修改密码</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/pages/plugins/back/back_footer.jsp"/>
