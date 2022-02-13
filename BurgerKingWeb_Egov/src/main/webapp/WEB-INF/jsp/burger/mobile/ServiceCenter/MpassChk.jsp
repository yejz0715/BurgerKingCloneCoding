<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="outerpasschk">
<%@ include file="../include/header.jsp"%>
		<div style="text-align: center">
			<form name="frm" method="post" action="MpassChk.do">
				<input type="hidden" name="qseq" value="${qseq}">
				<span><br><br><br><br><br><br><br><br><br><br><br><br>
					<h1>작성 시 입력했던 비밀번호 4자리를 입력해주세요.</h1><br>
					<label style="color: red;">${message}</label><br><br>
					<input type="password" name="pass" size="4" style="height: 30px;">
					<input type="submit" class="qna_btn01" value="확인">
					<input type="button" class="qna_btn01" value="뒤로가기" onclick="location.href='MqnaForm.do'">
				</span>
			</form>
		</div>
</div>
<%@ include file="../include/footer.jsp" %>