<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<%
	String userid = (String) session.getAttribute("userid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardWrite</title>
</head>
<script type="text/javascript">
	var addInputNum = 1;
	$j(document).ready(function(){
		
		$j("#submit").on("click",function(e){
			var $frm = $j('.boardWrite :input');
			var param = $frm.serialize();
			console.log($frm);
			$j.ajax({
			    url : "/board/boardWriteAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("작성완료");
					
					alert("메세지:"+data.success);
					
					location.href = "/board/boardList.do";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});
	
		$j("#add").on("click",function(){
			str="";
			str += '<tr class="addTr'+addInputNum+'"><td colspan="2" align="right" style="border : none"><input type="button" value="해당행 삭제" data-num="'+addInputNum+'"></td></tr>';
			str += '<tr class="addTr'+addInputNum+'"><td width="120" align="center">Type</td><td width="400"><select name="boardVoList['+addInputNum+'].boardType">';
			str += '<c:forEach items="${menuList}" var="menuList"><option value="${menuList.codeId}">${menuList.codeName}</option></c:forEach></select></td></tr>';
			str += '<tr class="addTr'+addInputNum+'"><td width="120" align="center">Title</td><td width="400"><input name="boardVoList['+addInputNum+'].boardTitle" type="text" size="50"> </td></tr>';
			str += '<tr class="addTr'+addInputNum+'"><td height="300" align="center">Comment</td><td valign="top"><textarea name="boardVoList['+addInputNum+'].boardComment"  rows="20" cols="55"></textarea></td></tr>';
			$j("#addInput").before(str);
			addInputNum += 1;
		})
		
		$j(".boardWrite").on("click","input[type='button']",function(){
			//console.log($j(".addTr"+$j(this).data("num")))
			$j(".addTr"+$j(this).data("num")).remove();
		})
	});
	

</script>
<body>
<table align="center">
		<tr>
			<td  width="533" align="right">
			<input id="submit" type="button" value="작성">
			<input id="add" type="button" value="행추가">
			</td>
		</tr>
</table>
<form class="boardWrite">
	<table align="center">
		<tr>
			<td>
				<table border ="1"> 
					<tr>
						<td width="120" align="center">
						Type
						</td>
						<td width="400">
						<select name='boardVoList[0].boardType'>
                        <c:forEach items="${menuList}" var="menuList">
							<option value="${menuList.codeId}">${menuList.codeName}</option>
						</c:forEach>
                     </select>  
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
						Title
						</td>
						<td width="400">
						<input name="boardVoList[0].boardTitle" type="text" size="50"> 
						</td>
					</tr>
					<tr>
						<td height="300" align="center">
						Comment
						</td>
						<td valign="top">
						<textarea name="boardVoList[0].boardComment"  rows="20" cols="55"></textarea>
						</td>
					</tr>
					<tr  id="addInput">
						<td align="center">
						Writer
						</td>
						<td>
							<c:if test="${!empty userid}">
								${userinfo.userName}	
							</c:if>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>	
<table align="center">
		<tr>
			<td  width="533" align="right">
				<a href="/board/boardList.do">List</a>
			</td>
		</tr>
</table>
</body>
</html>