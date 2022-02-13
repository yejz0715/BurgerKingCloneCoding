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
	<div class="pageMove">
	<a href="MmemberUpdateForm.do"><span>정보변경&nbsp;</span></a>
	<a href="MmyAddressForm.do"><span>MY배달지</span></a>
	</div>
	</div>

		
	</div>
</article>

<%@ include file="../include/footer.jsp" %>