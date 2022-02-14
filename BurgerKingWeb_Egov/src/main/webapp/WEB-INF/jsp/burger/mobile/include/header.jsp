<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
    <meta charset="UTF-8">
    <title>버거킹</title>
	<link href="<c:url value='css/mobile.css' />" rel="stylesheet">
	<script src="<c:url value='http://code.jquery.com/jquery-latest.js' />"></script>
	<script src="<c:url value='script/mburger.js"' /> type="text/javascript"></script>
	<script src="<c:url value='script/index.js'/>" type="text/javascript"></script>
	<link rel="icon" href="<c:url value='image/main/favicon.ico' />">
	<link rel="preconnect" href="<c:url value='https://fonts.googleapis.com' />">
	<link rel="preconnect" href="<c:url value='https://fonts.gstatic.com' />" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Nanum+Myeongjo:wght@800&family=Noto+Sans+KR&display=swap" rel="stylesheet">
	<script type="text/javascript">
	$(document).ready(function(){
		  $('.h_menu_open').click(function(){
		    $('#header_menubar').toggle('500');
		  });
		});
	</script>
	</head>
	<body> 
		<header id=h_header> 
			<div class="header_container" style="width: 100%;">
				<div id="web_container"> 
					<a class="h_menu_open">
						<img src="<c:url value='image/main/HeaderMenu_icon.png'/>">
					</a>
					
					<a id="logo" href="mobilemain.do"> 
						<img src="<c:url value='image/main/burgerkinglogo.png' />" style="vertical-align: bottom; width:83; height:82;">
					</a>
					
			       	<c:choose>
						<c:when test="${empty loginUser}">
							<a id="order_btn" href="MloginForm.do"> 
								<img src="<c:url value='image/main/delivery1.png '/>" class="header_deli_img">
							</a>
						</c:when>
						<c:otherwise>
							<a id="order_btn" href="MdeliveryForm.do?kind1=1"> 
								<img src="<c:url value='image/main/delivery1.png ' />" class="header_deli_img">
							</a>
						</c:otherwise>
					</c:choose>
				</div> 
			</div>
			<div id="header_menubar">
					<ul id="ul1">
						<li class="h_menu_item"><span>메뉴소개</span>
							<ul class="header_submenu">
								<li onclick="location.href='MmenuListForm.do?kind1=1'"><a><span>스페셜&amp;할인팩</span></a></li>
								<li onclick="location.href='MmenuListForm.do?kind1=2'"><a><span>프리미엄</span></a></li>
								<li onclick="location.href='MmenuListForm.do?kind1=3'"><a><span>와퍼</span></a></li>
								<li onclick="location.href='MmenuListForm.do?kind1=4'"><a><span>주니어&amp;버거</span></a></li>
								<li onclick="location.href='MmenuListForm.do?kind1=5'"><a><span>올데이킹&amp;치킨버거</span></a></li> 
								<li onclick="location.href='MmenuListForm.do?kind1=6'"><a><span>사이드</span></a></li>
								<li onclick="location.href='MmenuListForm.do?kind1=7'"><a><span>음료&amp;디저트</span></a></li>
								<li onclick="location.href='MmenuListForm.do?kind1=8'"><a><span>독퍼</span></a></li>
							</ul> 
						</li>
						<li class="h_menu_item"><span>이벤트</span>
							<ul class="header_submenu">
								<li onclick="location.href='MeventListForm.do'"><a><span>이벤트</span></a></li>
							</ul>
						</li>
						<li class="h_menu_item">
							<span>브랜드스토리</span>
							<ul class="header_submenu">
								<li onclick="location.href='MbrandStroyForm.do'"><a><span>버거킹스토리</span></a></li>
							</ul>
						</li>
						<li class="h_menu_item">
							<span>고객센터</span>
							<ul class="header_submenu">
								<li onclick="location.href='MfaqListForm.do?fnum=1'"><a><span>FAQ</span></a></li>
								<li onclick="location.href='MqnaForm.do'"><a><span>문의</span></a></li>
								<li onclick="location.href='MappGuideForm.do'"><a><span>버거킹 앱 이용안내</span></a></li>
							</ul>
						</li>
					</ul>
				</div>  
		</header>