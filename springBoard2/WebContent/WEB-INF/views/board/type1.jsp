<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<script type="text/javascript">
	var img = "<input name='' type='file' data-type='PTDETAILIMG' data-num="
	var desc1 = "<textarea name='ProductPatternDetailList["
	var desc2 = "].PTDETAILDESC'  rows='20' cols='55'></textarea>"
	var mv = "<input name='' type='file' data-type='PTDETAILMV' data-num="
	var oro = ">"
	
	$j(function() {
		
		$j(".radio").click(function() {
			console.log($j(this).val())
			str = '<td width="120" align="center"height="30px">상품페턴입력</td><td width="400">';
			if($j(this).val() == 'img'){
				str+=img+$j(this).data("num")+oro+'</td></tr>'
				$j("#selectType"+$j(this).data("num")).empty();
				$j("#selectType"+$j(this).data("num")).append(str);
			}else if($j(this).val() == 'desc'){
				str+=desc1+$j(this).data("num")+desc2+'</td></tr>'
				$j("#selectType"+$j(this).data("num")).empty();
				$j("#selectType"+$j(this).data("num")).append(str);
			}else if($j(this).val() == 'mv'){
				str+=mv+$j(this).data("num")+oro+'</td></tr>'
				$j("#selectType"+$j(this).data("num")).empty();
				$j("#selectType"+$j(this).data("num")).append(str);
			}			
		})
		
		
		$j("#submit").click(function() {
			var $frm = $j('.boardWrite :input');
			var param = $frm.serialize();
			console.log(param);
			$j.ajax({
			    url : "/board/ProductGroupInfoWrite2.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("등록이 완료되셨습니다");						
					
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});
		
		$j(".updataFile").on("change","td input[type='file']",function() {
			//var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp|pdf)$/;
			var files = $j(this)[0].files;
			var point = $j(this).data("num");
			var pointType = $j(this).data("type");
			console.log(point);
			var point1 = '.boardWrite #xx input[data-num="'+point+'"]';
			/* 			
			if(!$j(this).val().match(fileForm)){
				alert("이미지파일만 업로드 가능합니다.");
		        $j(this).focus();
		        return false;
			} */
			
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
					$j(point1).remove;
					var str = ""
					str+="<input name='ProductPatternDetailList["+point+"]."+pointType+"' type='hidden' data-num = '"+point+"' value='"+result+"'>";
					$j('.boardWrite #xx').append(str);
					console.log(str)
				},
				error: function(xhr, statues, error) {
					alert("파일업로드 실패");
				}
			})
		})
	})
		
</script>
<form class="boardWrite" method="post">
<div id="updataF"></div>
<table align="center">
		<tr>
			<td  width="533" align="right">
			<input id="submit" type="button" value="입력">
			</td>
		</tr>
</table>
<div id="xx">
	<input type="hidden" name="D_PRDGRIDX" value="${PGVo.PRDGRIDX}">
	<input type="hidden" name="D_PRDPTIDX" value="${PTVo.PRDPTIDX}">
	<input type="hidden" name="ProductPatternDetailList[${PRDPTTYPENUM}].PTDETAILTYPE" value="list">
</div>
	<table align="center">
		<tr>
			<td>
			<c:forEach var="i" begin="0" end="${PRDPTTYPENUM - 1}">
				<table border ="1"> 
					<tr>
						<td width="120" align="center"height="30px">
						상품페턴상세타입
						</td>
						<td width="400">
							<input class="radio" name="ProductPatternDetailList[${i}].PTDETAILTYPE" type="radio" value="img" data-num="${i}" size="50">이미지
							<input class="radio"  name="ProductPatternDetailList[${i}].PTDETAILTYPE" type="radio" value="desc" data-num="${i}" size="50">상세설명
							<input class="radio"  name="ProductPatternDetailList[${i}].PTDETAILTYPE" type="radio" value="mv" data-num="${i}" size="50">동영상					
						</td>
					</tr>
					<tr id="selectType${i}" class="updataFile">
						
					</tr>
				</table>
			</c:forEach>
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