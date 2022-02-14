<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>

<article>
	
	<div class="web_myPageContainer">
	<div class="subTit_myPage">
	<h1 class="pageTit">MY킹</h1>
	</div>
	<div class="contentBox">
	<p class="user"><strong>${MemberVO.NAME}님!&nbsp;</strong>반갑습니다.</p>
	<p class="user"><a href="MmemberUpdateForm.do" style="color:#000;"><strong>정보변경&nbsp;</strong></a></p>
	<p class="user"><a href="MmyAddressForm.do" style="color:#000;"><strong>MY배달지</strong></a></p>
	</div>

		
	</div>
</article>

<%@ include file="../include/footer.jsp" %>