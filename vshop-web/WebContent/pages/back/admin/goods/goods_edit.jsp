<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/back/admin/goods/goods_edit.js"></script>
<%!
	public static final String GOODS_EDIT_URL = "pages/back/admin/goods/GoodsActionBack!edit.action" ;
%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/back/include_menu_item.jsp" />
		<div class="content-wrapper text-left">
			<div class="panel panel-success">
				<div class="panel-heading">
					<strong><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑商品信息</strong>
				</div>
				<div class="panel-body">
					<form class="form-horizontal" action="<%=GOODS_EDIT_URL%>" id="myform" method="post" enctype="multipart/form-data">
						<fieldset>
							<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
							<div class="form-group" id="titleDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="goods.title">商品名称：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<input type="text" id="title" name="title" class="form-control"
										placeholder="请输入商品名称" value="${goods.title}">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="titleMsg"></div>
							</div>
							<div class="form-group" id="iidDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="goods.iid">所属类别：</label>
								<div class="col-md-5">
									<select id="iid" name="iid" class="form-control">
										<option value="">====== 请选择商品的所属分类 ======</option>
										<c:forEach items="${allItems}" var="item">
											<option value="${item.iid}" ${item.iid==goods.iid?"selected":""}>${item.title}</option>
										</c:forEach>
									</select>
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="iidMsg"></div>
							</div>
							<div class="form-group" id="sidDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="sid">所属子类别：</label>
								<div class="col-md-5">
									<select id="sid" name="sid" class="form-control">
										<option value="">====== 请选择商品的所属子分类 ======</option>
										<c:forEach items="${allSubitems}" var="subitem">
											<option value="${subitem.sid}" ${subitem.sid==goods.sid?"selected":""}>${subitem.title}</option>
										</c:forEach>
									</select>
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="sidMsg"></div>
							</div>
							<div class="form-group" id="priceDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="price">商品价格：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<input type="text" id="price" name="price" class="form-control"
										placeholder="商品价格" value="<fmt:formatNumber value="${goods.price}"/>">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="priceMsg"></div>
							</div>
							<div class="form-group" id="photoDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="photo">商品图片：</label>
								<div class="col-md-5">
									<img src="upload/goods/${goods.photo}" style="width:200px;height:200px;">
									<!-- 定义表单输入组件 -->
									<input type="file" id="photo" name="photo" class="form-control"
										placeholder="请选择商品宣传图片">
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="photoMsg"></div>
							</div>
							<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
							<div class="form-group" id="noteDiv">
								<!-- 定义表单提示文字 -->
								<label class="col-md-3 control-label" for="note">项目描述：</label>
								<div class="col-md-5">
									<!-- 定义表单输入组件 -->
									<textarea id="note" name="note"
										class="form-control" placeholder="请输入商品描述信息" rows="10">${goods.note}</textarea>
								</div>
								<!-- 定义表单错误提示显示元素 -->
								<div class="col-md-4" id="noteMsg"></div>
							</div> 
							<div class="form-group">
								<div class="col-md-5 col-md-offset-3">
									<input type="hidden" name="oldphoto" id="oldphoto" value="${goods.photo}">
									<input type="hidden" name="gid" id="gid" value="${goods.gid}">
									<button type="submit" class="btn btn-primary">编辑</button>
									<button type="reset" class="btn btn-warning">重置</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="panel-footer">
					<div class="alert alert-success" id="alertDiv" style="display: none;">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <span id="alertText"></span>
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
	<jsp:include page="/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/pages/plugins/back/back_footer.jsp"/>
