<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>딜리버리</title>
<link href="<c:url value='/css/mobile.css'/> " rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="<c:url value='/script/mburger.js'/> " type="text/javascript"></script>
<script src="<c:url value='/script/index.js'/> " type="text/javascript"></script>
<link rel="icon" href="<c:url value='/image/main/favicon.ico'/> ">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Nanum+Myeongjo:wght@800&family=Noto+Sans+KR&display=swap" rel="stylesheet">
</head>
<body>
	<div class="popbox03">
		<div class="M_headerWrap">
			<div class="titleBar">
				<div class="page_tit w_alignL"><span>메뉴 선택</span></div>
			</div>
		</div>
		<div class="popcont nopadding">
			<div class="prd_intro">
				<div class="prd_img">
					<span><img src="<c:url value='/image/menu/product/${productVO.IMAGE}'/> " style="display: inline; opacity: 1;"></span>
				</div>
				<div class="intro_txt">
					<h3 class="tit"><span>${productVO.PNAME }</span></h3>
					<p class="subtxt">${productVO.CONTENT }</p>
				</div>
			</div>
		</div>
		<ul class="menu_sub_list02" style="background: #f2ebe6;">
			<c:forEach var="list2" items="${list1}">
				<li>
					<div class="prd_img">
						<span><img src="<c:url value='/image/menu/${list2.KIND1}/${list2.IMAGE}'/> " style="display: inline; opacity: 1;"></span>
					</div>
					<div class="cont">
						<div class="tit"><strong>${list2.PNAME}</strong></div>
						<div class="set"><span>${list2.CONTENT }</span></div>
						<div class="price" ><strong style="color:red;font-weight:bold">&#8361;${list2.PRICE1}</strong></div>
					</div>
					<div>
						<button type="button" class="btn_detail" onclick="add_or_cart('${list2.KIND1}','${list2.PSEQ}')"></button>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>