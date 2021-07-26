<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
	<link rel="stylesheet" type="text/css" href="${path }/resources/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${path }/resources/bootstrap/css/common.css">
	<script type="text/javascript" src="${path }/resources/bootstrap/js/jquery.js"></script>
	<script type="text/javascript" src="${path }/resources/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">

	.narrowWidth1 {
   	 width: 60%;
   	}
   	
   	.narrowWidth2 {
		width: 100%;
	}
	#img_box {
		float: left !important;
		
	}
	
	#inform_box {
		float: right !important;
		width : 55%;
	}
	 
	.thumbnail {
		height: 200px;
		width: 200px;
		margin-top: 15px;
	}
	
	.btn_SM {
		width: 90%;
		height: 40px;
		border: none;
		background: #242526;
		color: #eee;   
		font-size: 18px;
		text-align: center;
	}  
</style>


</head>
<body>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("탈퇴한 회원입니다.")
		self.close();
	</script>
</c:if>

<c:if test="${result == 1 }">
	<div align="center">
		<h2 class="title">${member.MB_nickName }님의 프로필</h2>
		<table class="table narrowWidth">
			<tr align="center">
				<th colspan="2" class="text-center">
					<img alt="" src="${path }/resources/memberImg/${member.MB_img}" height="300px" width="300px">
				</th>
			</tr>
			<tr>
				<td class="col md-2 text-center">이메일</td>
				<td class="col md-10">${member.MB_id }</td>
			</tr>
			<tr>
				<td class="col md-2 text-center">닉네임</td>
				<td class="col md-10">${member.MB_nickName }</td>
			</tr>
			
			<c:if test="${member.MB_gender == '1' || member.MB_gender == '3'}">
				<tr>
					<td class="col md-2 text-center">성별</td>
					<td class="col md-10">남자</td>
				</tr>	
			</c:if>
			<c:if test="${member.MB_gender == '2' || member.MB_gender == '4'}">
				<tr>
					<td class="col md-2 text-center">성별</td>
					<td class="col md-10">여자</td>
				</tr>	
			</c:if>	
			
			<tr>
				<td class="col md-2 text-center">가입일</td>
				<td class="col md-10">${member.MB_regDate }</td>
			</tr>
			
			<tr>
				<c:if test="${favo > 0 }">
					<td>
						<a class="glyphicon glyphicon-heart" aria-hidden="true"
							href="deleteFv.do?MB_numG=${sessionScope.MB_num }&MB_numT=${member.MB_num}&MB_nickName=${member.MB_nickName}"></a>
					</td>
				</c:if>
				
				<c:if test="${favo == 0 }">
					<td>
						<a class="glyphicon glyphicon-heart-empty" aria-hidden="true" 
							href="addFv.do?MB_numG=${sessionScope.MB_num }&MB_numT=${member.MB_num}&MB_nickName=${member.MB_nickName}"></a>
					</td>
				</c:if>
				
				<td>
					<a class="glyphicon glyphicon-envelope" aria-hidden="true" 
						onclick="window.open('msgWriteForm.do?MB_num=${member.MB_num }','쪽지 보내기',
						'width=430,height=400,location=no,status=no,scrollbars=yes');"></a>
				</td>
			</tr>
		</table>
		
		<c:if test="${result2 == -1 }">
			<h2 class="title">평점 : 0점</h2>
		</c:if>
		<c:if test="${result2 != -1 }">
			<h2 class="title">평점 : ${reviewAvg }점</h2>
		</c:if>

		<table class="table narrowWidth">
			<tr>
				<th class="col md-4 text-center">닉네임</th>
				<th class="col md-3 text-center">별점</th>
				<th class="col md-3 text-center">리뷰</th>
			</tr>
			<c:if test="${empty rvList }">
				<tr>
					<th colspan="3" class="text-center">등록된 리뷰가 없습니다.</th>
				</tr>		
			</c:if>
			<c:if test="${not empty rvList }">
				<c:forEach var="review" items="${rvList }">
					<tr>
							<td class="col md-4 text-center">${review.MB_nickName }</td>
							<td class="col md-3 text-center">${review.RV_star }</td>
							<td class="col md-3 text-center">${review.RV_content }</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	
	</div>
</c:if>
</body>
</html>