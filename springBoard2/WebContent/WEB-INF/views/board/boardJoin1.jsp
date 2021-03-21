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
		var postnoV = /^[0-9]{1,3}-[0-9]{1,3}$/;
		var phoneV = /^[0-9]{4}$/;
		var useridChk = false;
		var userid = $j("#userId") ,userpw = $j("#userPw"),userpwcheck = $j("#userPwCheck"),username = $j("#userName"),
			userphone2 = $j("#userPhone2"),userphone3 =$j("#userPhone3"),useraddr1=$j("#userAddr1"),
			useraddr2=$j("#userAddr2"),usercompany = $j("#userCompany");
		$j("#idcheck").click(function(e) {
			e.preventDefault();
			var param = userid.serialize();
			if(userid.val() == ""){
				alert("아이디를 입력해 주세요.");
				userid.focus();
				return false;
			}
			if(userid.val().length > 15){
				alert("아이디는 15자 이내로 입력해주세요.");
				userid.focus();
				return false;
			}
			$j.ajax({
			    url : "/user/userIdCheackAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {	
					if(data.cheack == "S"){
						alert("메세지: 사용이 가능한 아이디입니다.");
						useridChk = true;
					}else{
						alert("메세지: 중복되는 아이디입니다.");
					}
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		})
		
		$j("#submit").on("click",function(e){
			e.preventDefault();
			var $frm = $j('.joinForm :input');
			var param = $frm.serialize();
			if(!useridChk){
				alert("아이디 중복확인을 해주세요.");
				return false;
			}
			console.log(userpw.val());
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
			if(userpwcheck.val() != ""){
				if(userpw.val() != userpwcheck.val()){
					alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
					userpwcheck.focus();
					return false;
				}
			}else{
				alert("비밀번호체크를 입력해주세요.");
				userpwcheck.focus();
				return false;				
			}
			if(username.val() == ""){
				alert("이름을 입력해주세요.");
				username.focus();
				return false;
			}
			if(username.val().length > 8){
				alert("이름은 최대 8자 까지만 가능합니다.");
				username.focus();
				return false;
			}
			if(userphone2.val()==""){
				alert("전화번호를 입력해 주세요.");
				userphone2.focus();
				return false;
			}else if(userphone3.val()==""){
				alert("전화번호를 입력해 주세요.");
				userphone3.focus();
				return false;
			}
			if(userphone2.val()!=""||userphone3.val()!=""){
				if(!phoneV.test(userphone2.val())){
					alert("전화번호는 구간당 4자리만 입력해주세요.");
					userphone2.focus();
					return false;
				}else if(!phoneV.test(userphone3.val())){
					alert("전화번호는 구간당 4자리만 입력해주세요.");
					userphone3.focus();
					return false;
				}								
			}
			if(useraddr1.val() != ""){
				if(!postnoV.test(useraddr1.val())){
					alert("우편번호를 확인해주세요.(예시 : 000-000)");
					useraddr1.focus();
					return false;
				}				
			}
			if(useraddr2.val() != ""){
				if(useraddr2.val().length > 70){
					alert("주소는 70자 이내로 작성해주십시요");
					useraddr2.focus();
					return false;
				}				
			}
			if(usercompany.val() != ""){
				if(usercompany.val().length > 30){
					alert("회사명은 30자 이내로 작성해 주십시요");
					usercompany.focus();
					return false;
				}				
			}
			$j.ajax({
			    url : "/user/userJoinAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("가입완료");
					
					alert("메세지:"+data.success);
					
					location.href = "/board/boardList.do";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});
		userpwcheck.keyup(function() {
			userpwcheck.val(userpwcheck.val().substr(0,16));
			if(userpw.val() == userpwcheck.val()){
				$j("#pwmsg").empty();
				$j("#pwmsg").append("비밀번호와 일치합니다.")
			}else{
				$j("#pwmsg").empty();
				$j("#pwmsg").append("비밀번호와 일치하지 않습니다.")	
			}
		})
		userpw.keyup(function() {
			userpw.val(userpw.val().substr(0,16));
			if(userpwcheck.val()!=""){
				if(userpw.val() == userpwcheck.val()){
					$j("#pwmsg").empty();
					$j("#pwmsg").append("비밀번호와 일치합니다.")
				}else{
					$j("#pwmsg").empty();
					$j("#pwmsg").append("비밀번호와 일치하지 않습니다.")	
				}				
			}
		})
		useraddr2.keyup(function() {
			userpw.val(userpw.val().substr(0,70));
			
		})
		usercompany.keyup(function() {
			userpw.val(userpw.val().substr(0,30));
			
		})
		userid.keyup(function(event){
			useridChk = false;
			if(!(event.keyCode >=37 && event.keyCode<=40)) {
				var inputVal = $j(this).val();
			    $j(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
			    userid.val(userid.val().substr(0,15));
			}
		});
		username.keyup(function(event){
			if(!(event.keyCode >=37 && event.keyCode<=40)) {
				var inputVal = $j(this).val();
			    $j(this).val(inputVal.replace(/[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g,''));
			    username.val(username.val().substr(0,6));
			}
		});
		userphone2.keyup(function(event){
			if(!(event.keyCode >=37 && event.keyCode<=40)) {
				var inputVal = $j(this).val();
			    $j(this).val(inputVal.replace(/[^0-9]/gi,''));
			    userphone2.val(userphone2.val().substr(0,4));
			}
		});
		userphone3.keyup(function(event){
			if(!(event.keyCode >=37 && event.keyCode<=40)) {
				var inputVal = $j(this).val();
			    $j(this).val(inputVal.replace(/[^0-9]/gi,''));
			    userphone3.val(userphone3.val().substr(0,4));
			}
		});
		useraddr1.keydown(function(event){		
			if(event.keyCode >= 48 && event.keyCode<=57 && event.keyCode!=8) {
				var inputVal = $j(this).val();
			    if(inputVal.length == 3){
			    	$j(this).val(inputVal+"-");
			    }
			useraddr1.val(useraddr1.val().substr(0,6));
			}
			if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
				event.preventDefault();
			}

			
		});
		useraddr1.keyup(function(event){
			if(!(event.keyCode >=37 && event.keyCode<=40)) {
				var inputVal = $j(this).val();
			    $j(this).val(inputVal.replace(/[^0-9-]/g,''));
			}
		});
		
	});
	

</script>
<body>
	<form class="joinForm">
		<table align="center">
			<tr>
				<td align="left"><a href="/board/boardList.do">List</a></td>
			</tr>
			<tr>
				<td>
					<table border="1">
						<tr>
							<td width="120" align="center">id</td>
							<td width="400"><input type="text" id="userId" name="userId" pattern="[A-Za-z0-9]*">
								<button id="idcheck">중복확인</button>
							</td>
						</tr>
						<tr>
							<td width="120" align="center">pw</td>
							<td width="400"><input type="password" id="userPw" name="userPw">
							</td>
						</tr>
						<tr>
							<td width="120" align="center">pw check</td>
							<td width="400"><input type="password" id="userPwCheck"
								name="userPwCheck"><span id="pwmsg"></span></td>
						</tr>
						<tr>
							<td width="120" align="center">name</td>
							<td width="400"><input type="text" id="userName"
								name="userName"></td>
						</tr>
						<tr>
							<td width="120" align="center">phone</td>
							<td width="400">
								<select id="userPhone1" name='userPhone1'>
									<c:forEach items="${phoneList}"	var="phoneList">
										<option value="${phoneList.codeId}">${phoneList.codeName}</option>
									</c:forEach>
								</select>-
								<input type="text" id="userPhone2" name="userPhone2" style="width: 40px">-
								<input type="text" id="userPhone3" name="userPhone3" style="width: 40px">
							</td>
						</tr>
						<tr>
							<td width="120" align="center">postNo</td>
							<td width="400"><input type="text" id="userAddr1"
								name="userAddr1"></td>
								<!-- onKeyup="this.value=this.value.replace(/[^0-9-]/g,'');"  -->
						</tr>
						<tr>
							<td width="120" align="center">address</td>
							<td width="400"><input type="text" id="userAddr2"
								name="userAddr2"></td>
						</tr>
						<tr>
							<td width="120" align="center">company</td>
							<td width="400"><input type="text" id="userCompany"
								name="userCompany"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="right"><a href="" id="submit">join</a></td>
			</tr>
		</table>
	</form>
</body>
</html>