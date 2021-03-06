<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>회원리스트</h1>
	<h2 style="color: red; font-weight: bold;">${message}</h2>
	<form name="frm" method="post">
		<table style="margin: 0 auto;">
			<tr>
				<td width="800">회원명<input type="text" name="key" value="${key}">
				<input class="btn" type="button" name="btn_search" value="검색" onclick="go_member_search();">
				<input class="btn" type="button" name="btn_total" value="전체보기" onclick="go_member_total();">
			</tr>
		</table>
		<table id="productList">
			<tr>
				<th>회원번호</th>
				<th>회원명</th>
				<th>아이디</th>
				<th>전화번호</th>
				<th>가입일</th>
				<th>마지막 로그인</th>
				<th>회원분류</th>
				<th>삭제<input type="checkbox" name="deleteAll" onclick="deleteAllCheck(this);"></th>
			</tr>
			<c:forEach items="${memberList}" var="memberVO">
				<tr>
					<td height="23" align="center">${memberVO.MSEQ}</td>
					<td style="text-align: left; padding-left: 50px;">
						<a href="#" onclick="go_member_update('${memberVO.MSEQ}');">${memberVO.NAME}</a>
					</td>
					<td align="center"><input type="hidden" name="id" value="${memberVO.ID}">${memberVO.ID}</td>
					<td align="center">${memberVO.PHONE}</td>
					<td><fmt:formatDate value="${memberVO.INDATE}"/></td>
					<td><fmt:formatDate value="${memberVO.LASTDATE}"/></td>
					<td>${memberVO.MEMBERKIND}</td>
					<td>
						<input type="checkbox" name="delete" value="${memberVO.MSEQ}">
					</td>
				</tr>
			</c:forEach>
		</table><br>
		<input type="button" class="btn" style="float: right;" value="삭제" onclick="del_member();">
	</form>
	<jsp:include page="../../admin/paging/paging.jsp">
		<jsp:param name="page" value="${paging.page}"/>
		<jsp:param name="beginPage" value="${paging.beginPage}"/>
		<jsp:param name="endPage" value="${paging.endPage}"/>
		<jsp:param name="prev" value="${paging.prev}"/>
		<jsp:param name="next" value="${paging.next}"/>
		<jsp:param name="command" value="adminMemberList"/>
	</jsp:include>
	
</article>

<%@ include file="../../admin/footer.jsp"%>