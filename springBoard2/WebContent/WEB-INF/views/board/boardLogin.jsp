<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardWrite</title>
</head>
<script type="text/javascript">
	$j(document).ready(function(){
		var userid = $j("#userId");
		var userpw = $j("#userPw");
		userid.keyup(function(event){
			if(!(event.keyCode >=37 && event.keyCode<=40)) {
				var inputVal = $j(this).val();
			    $j(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
			}
		});
		$j("#submit").on("click",function(e){
			e.preventDefault();
			var $frm = $j('.loginForm :input');
			var param = $frm.serialize();
			if(userid.val() == ""){
				alert("아이디를 입력해 주세요.");
				userid.focus();
				return false;
			}
			if(userpw.val() != ""){
				if(userpw.val().length < 6 || userpw.val().length > 16){
					alert("비밀번호는 6자리~12자리로 입력해주세요.");
					userpw.focus();
					return false;
				}
			}else{				
				alert("비밀번호를 입력해주세요.");
				userpw.focus();
				return false;
			}
			$j.ajax({
			    url : "/user/userLoginAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					if(data.result == "idCheck"){
						alert("아이디를 확인해 주세요.");
						userid.val("");
						userpw.val("");
						userid.focus();
					}else if(data.result == "N"){
						alert("비밀번호를 확인해 주세요.")
						userpw.val("");
						userpw.focus();
					}else{
						alert("로그인에 성공하셨습니다.")						
						location.href = "/board/boardList.do";
					}
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("로그인 실패");
			    }
			});
		});
		
	});
	

</script>
<body>
	<form class="loginForm">
		<table align="center">
			<tr>
				<td>
					<table border="1">
						<tr>
							<td width="120" align="center">id</td>
							<td width="400">
								<input type="text" id="userId" name="userId">
							</td>
						</tr>
						<tr>
							<td width="120" align="center">pw</td>
							<td width="400">
								<input type="password" id="userPw" name="userPw">
							</td>
						</tr>		
					</table>
				</td>
			</tr>
			<tr>
				<td align="right"><a href="" id="submit">login</a></td>
			</tr>
		</table>
	</form>
</body>
</html>