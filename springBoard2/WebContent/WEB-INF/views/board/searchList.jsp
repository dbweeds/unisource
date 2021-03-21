<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<%
	String userid = (String) session.getAttribute("userid");
%>
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
						total : ${totalCnt}					
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<table align="center">	
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
