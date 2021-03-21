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
	$j(function() {
		$j("#submit").click(function() {
			var $frm = $j('.boardWrite :input');
			var param = $frm.serialize();
			
			$j.ajax({
			    url : "/board/ProductGroupInfoWrite1.do",
			    dataType: "html",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					$j("body").empty();
					$j("body").html(data);					
					
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});
		
		$j("input[type='file']").change(function() {
			var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp|pdf)$/;
			var files = $j(this)[0].files;
			
			if(!$j(this).val().match(fileForm)){
				alert("이미지파일만 업로드 가능합니다.");
		        $j(this).focus();
		        return false;
			}
			
			var formData = new FormData();
			formData.append("uploadFile", files[0]);
			$j.ajax({
				url: '/board/uploadFile.do',
				type: 'post',
				processData: false,//데이터를 query string 형태로 보낼것인지 결정(기본값은 application/x-www-form-urlencoded임)
				contentType: false,//기본값은 application/x-www-form-urlencoded임(파일첨부임으로 multipart/form-data로 보내야 함)
				data: formData,
				dataType: "text",
				success: function(result) {
					$j('.boardWrite div').empty();
					var str = ""
					str+="<input name='PRDGRIMG' type='hidden' value='"+result+"'>";
					$j('.boardWrite div').append(str);
				},
				error: function(xhr, statues, error) {
					alert("파일업로드 실패");
				}
			})
		})
	})
		
</script>
<body>
<form class="boardWrite" method="post">
<table align="center">
		<tr>
			<td  width="533" align="right">
			<input id="submit" type="button" value="다음">
			</td>
		</tr>
</table>
<div></div>
	<table align="center">
		<tr>
			<td>
				<table border ="1"> 
					<tr>
						<td width="120" align="center" height="30px">
						기획전 이름
						</td>
						<td width="400">
                        	<input name="PRDGRNAME" type="text" size="50"> 
						</td>
					</tr>
					<tr>
						<td align="center"height="30px">
						상품이미지
						</td>
						<td>
							<input name="" type="file" size="50"> 
						</td>
					</tr>
					<tr>
						<td width="120" align="center"height="30px">
						상품페턴
						</td>
						<td width="400">
							<select name="PRDPTTYPENUM">
							    <option value="1">1개</option>
							    <option value="2">2개</option>
							    <option value="3">3개</option>
							    <option value="4">4개</option>
							</select>							
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
				<a href="">List</a>
			</td>
		</tr>
</table>
</body>
</html>