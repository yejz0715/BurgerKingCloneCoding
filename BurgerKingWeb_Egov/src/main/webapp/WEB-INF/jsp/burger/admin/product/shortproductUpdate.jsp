<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>상품 수정</h1>
	<form name="frm" method="post" action="adminShortProductUpdate" enctype="multipart/form-data">
		<input type="hidden" name="pseq" value="${productVO.PSEQ}">
		<input type="hidden" name="oldImage" value="${productVO.IMAGE}">
		<input type="hidden" name="k1" value="${k1}">
		<table id="list" width="500" border="1">
			<tr>
				<th>상품분류</th>
				<td width="50">
					<select name="kind1">
						<c:forEach items="${kindList1}" var="kind1" varStatus="status">
							<c:choose>
								<c:when test="${productVO.KIND1==status.count}">
									<option value="${status.count}" selected="selected">${kind1}</option>
								</c:when>
								<c:otherwise>
									<option value="${status.count}">${kind1}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
				<th>분류번호</th>
				<td>
					<input type="text" name="kind2" size="3" maxlength="10-" value="${productVO.KIND2}">
				</td>
				<th>세부</th>
				<td>
					<input type="text" name="kind3" size="3" maxlength="10-" value="${productVO.KIND3}">
				</td>
				<th>사용유무</th>
				<td>
					<c:choose>
						<c:when test='${productVO.USEYN == "1" }'>
							<input type="checkbox" name="useyn" value="1" checked="checked">
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="useyn" value="2">
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			
			<tr>
				<th>상품명</th>
				<td colspan="9">
					<input type="text" name="pname" size="80" maxlength="10-" value="${productVO.PNAME}">
				</td>
			</tr>
			<tr>
			<th>상품이미지</th>
				<td colspan="9">
					<img src="<c:url value='image/menu/product/${productVO.IMAGE}'/>" width="200px"><br>
      				<input type="file" name="image" >
				</td>
			</tr>
		</table>
		<input type="button" class="btn" value="수정" onclick="go_mod_save2(${k1})">
		<input type="button" class="btn" value="취소" onclick=
		"location.href='adminProductDetail.do?pseq=${productVO.PSEQ}'">
	</form>
</article>

<%@ include file="../../admin/footer.jsp"%>