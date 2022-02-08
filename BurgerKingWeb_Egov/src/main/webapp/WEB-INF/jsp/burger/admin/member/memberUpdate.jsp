<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>회원 정보 수정</h1>
	<form name="frm" method="post" action="adminMemberUpdate.do">
		<input type="hidden" name="mseq" value="${memberVO.MSEQ}">
		<input type="hidden" name="id" value="${memberVO.ID}">
		<table id="list" width="500" border="1">
			<tr>
				<th>아이디</th>
				<td>
					${memberVO.ID}
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td colspan="3">
					<input type="text" name="name" value="${memberVO.NAME}">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="pwd" size="10" value="">
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" name="pwd_chk" size="10" value="">
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					<input type="text" name="phone" value="${memberVO.PHONE}">
				</td>
			</tr>
		</table>
		<input type="submit" class="btn" value="수정" >
		<input type="button" class="btn" value="취소" onclick=
		"location.href='adminMemberList.do'">
		<h4 style="color: red">${message}</h4>
	</form>
</article>

<%@ include file="../../admin/footer.jsp"%>