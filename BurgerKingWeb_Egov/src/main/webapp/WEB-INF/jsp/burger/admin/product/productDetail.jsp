<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>상품 상세 보기</h1>
	<table id="list" border="1">
		<tr>
			<th>상품분류</th>
			<td colspan="5">${kind1}</td>
			<th>사용유무</th>
			<td colspan="5">
				<c:if test="${productVO.USEYN == 1}">
					사용
				</c:if>
				<c:if test="${productVO.USEYN == 2}">
					미사용
				</c:if>
			</td>
		</tr>
		<tr>
			<th align="center">상품명</th>
			<td colspan="7">${productVO.PNAME}</td>
		</tr>
		<tr>
			<th>가격</th><td width="60">${productVO.PRICE1}</td>
			<th>분류번호</th><td width="60">${productVO.KIND2}</td>
			<th>세부</th><td>${kind3}</td>
			<th>등록일</th><td>${productVO.INDATE}</td>
		</tr>
		<tr>
			<th>상세설명</th>
			<td colspan="7"><pre>${productVO.CONTENT}</pre></td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td colspan="7" align="center">
				<img src="<c:url value='image/menu/product/${productVO.IMAGE}'/>" width="200px">
			</td>
		</tr>
	</table>
	<input type="button" class="btn" value="수정" onclick="go_mod('${productVO.PSEQ}')">
	<input type="button" class="btn" value="목록" onclick="go_mov()">
</article>

<%@ include file="../../admin/footer.jsp"%>