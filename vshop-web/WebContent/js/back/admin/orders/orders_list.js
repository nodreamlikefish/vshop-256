$(function() {
	$("a[id*=userBtn-]").each(function(){
		// 拆分id数据
		var mid = this.id.split("-")[1] ;
		// console.log("用户ID：" + mid) ;
		$(this).on("click",function(){ 
			$.post("pages/back/admin/member/MemberActionBack!details.action",
				{"mid":mid},function(data){
					console.log(data) ;
					// 将异步加载信息填充到模态窗口的组件之中
					$("#userMid").text(mid) ;
					$("#userid").val(mid) ;
					// 向窗口之中进行内容的回填
					$("#modal-mid").text(data.member.mid) ;
					$("#modal-name").text(data.member.name) ;
					$("#modal-phone").text(data.member.phone) ;
					$("#modal-email").text(data.member.email) ;
					$("#modal-regdate").text(new Date(data.member.regdate.time).format("yyyy-MM-dd hh:mm:ss")) ;
					// 处理用户的所有地址信息
					$("#addressTable tr:gt(0)").remove() ;
					for (x = 0 ; x < data.allAddress.length ; x ++) {
						addressVal = 
							"<tr>" + 
							"	<td class='text-center'>" + 
							"		<input type='radio' id='flag' name='flag' " + (data.allAddress[x].deflag == 1 ? "checked" : "") + ">" + 
							"	</td>" + 
							"	<td class='text-center'>"+data.allAddress[x].receiver+"</td>" + 
							"	<td class='text-center'>"+data.allAddress[x].phone+"</td>" + 
							"	<td class='text-center'>"+data.allAddress[x].addr+"</td>" + 
							"</tr>" ;
						$("#addressTable").append(addressVal) ;
					}
					
					$("#userInfo").modal("toggle") ;	// 显示模态窗口
				},"json") ;
			// Ajax异步读取用户信息
		})
	}) ;
})