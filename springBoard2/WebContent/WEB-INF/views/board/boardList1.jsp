<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list</title>
</head>
<script type="text/javascript">
	
</script>
<body>
<div>
	<a href="/board/ProductGroupInfoWrite.do">컬랙션 추가</a>
</div>
<div>
	<c:forEach var="vo" items="${ProductGroupInfo}" varStatus="status">
		<div class="list"
			style="width: 48%; display: inline-block; height: 200px; margin-right: 10px">
			<div style="margin: 0 20px">
				<div class="image-container">
					<a href="/board/boardList2.do?PRDGRIDX=${vo.PRDGRIDX}">
					<%-- <img src="/resources/assets/custom/infoimage/${vo.thumbnail}" alt="" class="w-100" />--%>
					${vo.PRDGRNAME}
					</a>
				</div>
				<%-- <h2>
					<a href="count?bno=${vo.bno}">${vo.carname}</a>
				</h2>
				<div>
					<div>
						<span>브랜드</span>
						<span>:${vo.brand} </span>
					</div>
					<div>
						<span>가격</span>
						<span>만원
						</span>
					</div>
					<div>
						<span>주행거리(1회)</span>
						<span>:
							${vo.mileage} km </span>
					</div>
				</div>
				<a href="count?bno=${vo.bno}">
					상세페이지</a> --%>
			</div>
		</div>
	</c:forEach>
</div>
</body>
</html>