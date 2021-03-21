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
<title>list</title>
</head>
<script type="text/javascript">
	var pageno=1;
	var pagech=false;
	$j(document).ready(function(){
	/*  	$j("#submit").click(function() {
			var str = "";
			$j("label input[type='checkbox']:checked").each(function(idx,obj){
				var job = $j(obj);
				console.log(job)
			//수집된 정보를 hidden태그로 작성		
				str+= "<input type='hidden' name='menuList["+idx+"].codeId' value='"+job.val()+"'>"			
				str+= "<input type='hidden' name='menuList["+idx+"].codeName' value='"+job.data("name")+"'>"						
			})
			var form = $j("#searchForm");
			form.append(str);
			form.submit();
		}) */
		$j("#submit").click(function() {
			var str = "";
			if(!pagech){
				pageno=1;
			}
			$j("#searchForm").empty();
			$j("label input[type='checkbox']:checked").each(function(idx,obj){
				var job = $j(obj);
			//수집된 정보를 hidden태그로 작성		
				str+= "<input type='hidden' name='menuList["+idx+"].codeId' value='"+job.val()+"'>"			
				str+= "<input type='hidden' name='menuList["+idx+"].codeName' value='"+job.data("name")+"'>"						
			})
			str += "<input type='hidden' name='pageNo' value='"+pageno+"'>"						
			$j("#searchForm").append(str);
			var $frm = $j('#searchForm :input');
			var param = $frm.serialize();
			$j.ajax({
			    url : "/board/boardSearchListAction.do",
			    dataType: "html",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {				
					$j("#boardListVeiw").empty();
					$j("#boardListVeiw").html(data);
					pagech=false;
					
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		})
		$j("#xlsDownload").click(function() {
			var $frm = $j('#searchForm');
			$frm.submit();
			});
		$j("#calendarDownload").click(function() {
			var $Cfrm = $j('#calenderForm');
			$Cfrm.submit();
			console.log($Cfrm);
			});
		$j(".pagechange").click(function(e) {
			e.preventDefault();
			pageno= Number($j(this).data("num"));
			console.log(pageno)
			pagech = true;
			$j("#submit").trigger('click');
			});
/* 		$j("#xlsDownload").click(function() {
			var $frm = $j('#searchForm :input');
			var param = $frm.serialize();
			$j.ajax({
			    url : "/board/xlsDownload.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data)
			    {				
					alert("다운로드 성공"+data)
			    },
			    error: function ()
			    {
			    	alert("실패");
			    }
			});
			}); */

/* 		$j("#submit").click(function() {
			var str = "";
			$j("#searchForm").empty();
			$j("label input[type='checkbox']:checked").each(function(idx,obj){
				var job = $j(obj);
			//수집된 정보를 hidden태그로 작성		
				str+= "<input type='hidden' name='menuList["+idx+"].codeId' value='"+job.val()+"'>"			
				str+= "<input type='hidden' name='menuList["+idx+"].codeName' value='"+job.data("name")+"'>"						
			})
			$j("#searchForm").append(str);
			var $frm = $j('#searchForm :input');
			var param = $frm.serialize();
			$j.ajax({
			    url : "/board/boardSearchListAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
			    	var str2 = "";
					str += '<tr><td><table id="boardTable" border = "1">'
					str += '<tr><td width="80" align="center">Type</td>'
					str += '<td width="40" align="center">No</td><td width="300" align="center">Title</td></tr>';
					for(var board of data.boardList){
						str += '<tr><td align="center">';
						for(var menu of data.menuList){
							if(board.boardType == menu.codeId){
								console.log(menu.codeId)
								console.log(menu.codeName)
								str += menu.codeName;
							}
						}
						str += '</td><td>'+board.boardNum+'</td><td><a href = "/board/'+board.boardType+'/'+board.boardNum+'/boardView.do?pageNo=${pageNo}">'+board.boardTitle+'</a>';
						str += '</td></tr>'
					}
					$j("#totalCnt").empty();
					$j("#totalCnt").append("total : "+data.totalCnt);
					
					$j("#boardListVeiw").empty();
					$j("#boardListVeiw").append(str);
					
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		}) */
		$j("input[name='total']").click(function() {
			if($j(this).prop("checked")) { 
				$j("input[type=checkbox]").prop("checked",true); 
				} else {$j("input[type=checkbox]").prop("checked",false); }
		});
		$j("label input[type=checkbox]").click(function() {
			var totalVal = false;
			$j("label input[type='checkbox']").each(function(idx,obj){
				var job = $j(obj);
				if(!job.prop("checked")){
					totalVal = false;
					return false
				}
				if(job.prop("checked")){
					totalVal = true;
				}
			})
				$j("input[name='total']").prop("checked",totalVal);
		});
	});

</script>
<body>
<form action="/board/xlsDownload.do" id="searchForm">
	
</form>
<div id="boardListVeiw">
<table align="center">
		<tr>
		<td >
			<table>
				<tr>
					<c:choose>
						<c:when test="${empty userid}">
							<td width="40" align="left">
								<a href ="/board/boardLogin.do">login</a>					
							</td>
							<td width="40" align="left">
								<a href ="/board/boardJoin.do">join</a>				
							</td>
					    </c:when>
					    <c:otherwise>
					        <td width="80" align="left">
								${userinfo.userName}				
							</td>
					    </c:otherwise>
					</c:choose>
					<td width="350" align="right" id="totalCnt">
						total : ${pagevo.totalCnt}					
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<table  align="center">
	<tr>
		<td>
			<table id="boardTable" border = "1">
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
				</tr>
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="center">
							<c:forEach items="${menuList}" var="menuList">
								<c:if test="${menuList.codeId == list.boardType}">
									${menuList.codeName}
								</c:if>
							</c:forEach>
						</td>
						<td>
							${list.boardNum}
						</td>
						<td>
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
						</td>
					</tr>	
				</c:forEach>
			</table>
		</td>
	</tr>
</table>
</div>
<table align="center">
	<tr>
		<td width="340" align="left">
			<input type="checkbox" name="total" value="">전체
			<c:forEach items="${menuList}" var="menuList">
				<label><input type="checkbox" name="${menuList.codeId}" value="${menuList.codeId}" data-name="${menuList.codeName}">${menuList.codeName}</label>
			</c:forEach>
			<input id="submit" type="button" value="검색">
		</td>
		<td width="110" align="right">
			<c:if test="${!empty userid}">
				<a href ="/board/boardWrite.do">글쓰기</a>
			</c:if>
			<c:if test="${!empty userid}">
				<a href ="/user/userLogoutAction.do">logout</a>
			</c:if>
		</td>
	</tr>
</table>
<table align="center">	
	<tr>
		<td width="340" align="center">
			<c:if test="${pagevo.prev}">
            	<a class="pagechange" data-num="${pagevo.startPage-1}" href=""><</a>
            </c:if>
            <c:forEach var="idx" begin="${pagevo.startPage}" end="${pagevo.endPage}">
            	<a class="pagechange" data-num="${idx}" href="">${idx}</a>
            </c:forEach>
            <c:if test="${pagevo.next}">
            	<a class="pagechange" data-num="${pagevo.endPage+1}" href="">></a>
            </c:if>
		</td>
	</tr>
	<tr>
		<td width="450" align="left">
			<input id="xlsDownload" type="button" value="엑셀다운">
		</td>
	</tr>
</table>
<form action="/board/calendarDownload.do" id="calendarForm">
<table align="center">
	<tr>
		<td width="450" align="left">
			<input name="calendarYear" type="text" style="width: 50px">년
			<input name="calendarManth" type="text" style="width: 50px">월
			<input id="calendarDownload" type="submit" value="달력출력">
		</td>
	</tr>
</table>
</form>
</body>
</html>