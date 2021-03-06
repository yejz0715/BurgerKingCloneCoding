<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../include/Delivery/deli_header.jsp"%>
<div class="clear"></div>

<article>
	
	<div class="web_profileUpdate">
		<h1 class="subTit">회원정보변경</h1>
		<h2 style="color: red; font-weight: bold;">${message}</h2>
		<form name="frm" method="post" action="MupdateMember.do">
			<fieldset>
				<legend>기본정보</legend>
				<label>이메일</lazbel>
				<input type="text" name="id" value="${MemberVO.ID}" readonly /><br> 
				<label>비밀번호</label>
				<input type="password" name="pwd" value="${MemberVO.PWD}" /><br> 
				<label>이름</label>
				<input type="text" name="name" value="${MemberVO.NAME}" /><br>
				<label>핸드폰</label>
				<input type="text" name="phone" value="${MemberVO.PHONE}" /><br>
			</fieldset>

			<fieldset>
				<legend>마케팅 정보 수신동의</legend>
				<label>
					<input type="checkbox" style="opacity: 1; cursor: pointer;" />
					<span class="check">이메일을 통한 이벤트/ 주문 정보의 수신에 동의합니다.</span>
				</label><br> 
				<label>
					<input type="checkbox" style="opacity: 1; cursor: pointer;" />
					<span class="check">SMS를 통한 이벤트/ 주문 정보의 수신에 동의합니다.</span>
				</label>
			</fieldset>


			<div class="c_btnItem2">
				<input type="reset" class="btn01 red" value="취소" onclick="history.go(-1)"/> 
				<input type="submit" class="btn01 m" value="변경" />
			</div>
		</form>

		<div class="resign_container">
			<form name="deletefrm" method="post" action="MmemberDelete.do">
				<input type="hidden" name="mseq" value="${MemberVO.MSEQ}"> 
				<input type="submit" value="회원탈퇴" onClick="return resign()" />
			</form>

		</div>
	</div>
</article>
<%@ include file="../include/footer.jsp"%>
