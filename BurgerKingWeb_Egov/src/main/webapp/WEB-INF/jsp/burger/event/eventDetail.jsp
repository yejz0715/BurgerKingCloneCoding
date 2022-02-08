<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<head profile="http://www.w3.org/2005/10/profile" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/event.css'/> "/>

<article>
	<div class="location">
	<div class="web_container1">
		<ul>
			<li><a href="main.do">HOME</a>&nbsp;>&nbsp;</li>
			<li><a href="eventListForm.do">이벤트</a>&nbsp;>&nbsp;</li>
			<li>상세</li>
		</ul>
	</div>		
	</div>
	<div class="event_web_container">
	<div class="subtit">
			<h1 class="event_tit">이벤트</h1>
	<form name="frm" method="post" action="burder.do">
	<ul>
	<li>
	<div class="detailTit">
	<input type="hidden" name="eseq" value="${EventVO.ESEQ}" />
	<h2>${EventVO.SUBJECT}</h2>
	<p>${EventVO.STARTDATE.substring(0,10)} ~ <!--substring:문자열 자르기(0부터 10까지)  -->
	${EventVO.ENDDATE.substring(0,10)}
	</p>
	</div>
	<div class="dt_ImgCon">
	<img class="detailImg" src="<c:url value='/image/main/event/${EventVO.IMAGE} '/> "/>
	<p>${EventVO.CONTENT}</p></div>
	</li>
	</ul>
	</form>
	</div>
	</div>
	
<div class="qr">

	<p><strong style="color:red;">버거킹 APP </strong>다운받고
	<br>
	<strong style="color:red;">더 많은 할인 혜택</strong>을
	<br>
	만나보세요!
	</p>
	<div class="qrCode">
	<a href="https://play.google.com/store/apps/details.do?id=kr.co.burgerkinghybrid&hl=ko">
	<img src="<c:url value='/image/main/appQR.png'/> "/>
	</a>
	</div>
	
	</div>
	<div class="c_btnItem2">
		<input type="button" class="btn01 m" onclick="location.href='eventListForm.do'" value="목록으로"/>
	</div>
	</article>
	
	<%@ include file="../include/undermenu.jsp" %>
<%@ include file="../include/footer.jsp"%>