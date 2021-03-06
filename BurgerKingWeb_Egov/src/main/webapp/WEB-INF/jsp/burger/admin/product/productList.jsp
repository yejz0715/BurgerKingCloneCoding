<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>상품리스트</h1>
	<form name="frm" method="post">
		<table style="margin: 0 auto;">
			<tr>
				<td width="800">상품명<input type="text" name="key" value="${key}">
				<input class="btn" type="button" name="btn_search" value="검색" onclick="go_search();">
				<input class="btn" type="button" name="btn_total" value="전체보기" onclick="go_total();">
				<input class="btn" type="button" name="btn_wrt" value="목록별보기" onclick="location.href='adminShortProductList.do?page=1&key='">
				<input class="btn" type="button" name="btn_wrt" value="상품등록" onclick="go_wrt();">
			</tr>
		</table>
		<table id="productList">
			<tr>
				<th>번호</th>
				<th>상품명</th>
				<th>가격</th>
				<th>상품분류</th>
				<th>분류번호</th>
				<th>세부</th>
				<th>등록일</th>
				<th>사용유무</th>
				<th>삭제<input type="checkbox" name="deleteAll" onclick="deleteAllCheck(this);"></th>
			</tr>
			<c:forEach items="${productList}" var="productVO">
				<tr>
					<td height="23" align="center">${productVO.PSEQ}</td>
					<td style="text-align: left; padding-left: 50px;">
						<a href="#" onClick="go_detail('${productVO.PSEQ}')">${productVO.PNAME}</a>
					</td>
					<td><fmt:formatNumber value="${productVO.PRICE1}"/></td>
					<td><fmt:formatNumber value="${productVO.KIND1}"/></td>
					<td><fmt:formatNumber value="${productVO.KIND2}"/></td>
					<td><fmt:formatNumber value="${productVO.KIND3}"/></td>
					<td><fmt:formatDate value="${productVO.INDATE}"/></td>
					<td>
						<c:choose>
							<c:when test='${productVO.USEYN==2}'>미사용</c:when>
							<c:otherwise>사용</c:otherwise>
						</c:choose>
					</td>
					<td>
						<input type="checkbox" name="delete" value="${productVO.PSEQ}">
					</td>
				</tr>
			</c:forEach>
		</table><br>
		<input type="button" class="btn" style="float: right;" value="삭제" onclick="del_product();">
	</form>
	<jsp:include page="../../admin/paging/paging.jsp">
		<jsp:param name="page" value="${paging.page}"/>
		<jsp:param name="beginPage" value="${paging.beginPage}"/>
		<jsp:param name="endPage" value="${paging.endPage}"/>
		<jsp:param name="prev" value="${paging.prev}"/>
		<jsp:param name="next" value="${paging.next}"/>
		<jsp:param name="command" value="adminProductList"/>
	</jsp:include>
	
</article>

<%@ include file="../../admin/footer.jsp"%>