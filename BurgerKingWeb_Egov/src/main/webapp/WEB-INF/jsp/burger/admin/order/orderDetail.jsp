<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>주문 상세 보기</h1>
	<table id="list" border="1">
		<tr>
			<th>아이디(이름)</th>
			<td colspan="5">${ovo.ID}(${ovo.MNAME})</td>
		</tr>
		<tr>
			<th>주문 번호</th>
			<td colspan="2">${ovo.OSEQ}</td>
			<th>주문 상세 번호</th>
			<td colspan="2">${ovo.ODSEQ}</td>
		</tr>
		<tr>
			<th align="center">상품명</th>
			<td colspan="5">${ovo.PNAME}</td>
		</tr>
		<tr>
			<th>주문 시간</th><td>${ovo.INDATE}</td>
			<th>회원 구분</th>
			<td>
				<c:if test="${ovo.MEMBERKIND == 1}">
					회원
				</c:if>
				<c:if test="${ovo.MEMBERKIND == 2}">
					비회원
				</c:if>	
			</td>
			<c:choose>
				<c:when test="${ovo.RESULT == 1}">
					<th>처리상태</th><td>처리 전</td>
				</c:when>
				<c:when test="${ovo.RESULT == 2}">
					<th>처리상태</th><td>처리 중</td>
				</c:when>
				<c:when test="${ovo.RESULT == 3}">
					<th>처리상태</th><td>배달 중</td>
				</c:when>
				<c:otherwise>
					<th>처리상태</th><td>완료</td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<th>주소</th>
			<td colspan="5">${ovo.ZIP_NUM} - ${ovo.ADDRESS}</td>
		</tr>
		<tr>
			<th>주문 수량</th>
			<td colspan="2">${ovo.QUANTITY}</td>
			<th>주문 가격</th>
			<td colspan="2">${ovo.PRICE1}</td>
		</tr>
	</table>
	
	<h1>재료 추가</h1>
	<table id="list" border="1">
		<c:choose>
			<c:when test="${empty spseqAm}">
				<tr>
					<th>추가 재료 없음</th>
				</tr>
				<tr>
					<th colspan="6">총가격 : ${totalPrice}</th>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${spseqAm}" var="spseqAm">
					<c:if test="${spseqAm.ODSEQ == ovo.ODSEQ}">
						<tr>
							<th>주문 상세 번호</th>
							<td>${spseqAm.ODSEQ}</td>
							<th>추가 재료 번호</th>
							<td>${spseqAm.SPOSEQ}</td>
							<th>추가 재료</th>
							<td>${spseqAm.SNAME}</td>
						</tr>
						<tr>
							<th>추가 가격</th>
							<td colspan="4">${spseqAm.ADDPRICE}원</td>
							<td>
								<button onclick="deleteSpo('${spseqAm.SPOSEQ}', '${ovo.RESULT}',
								 '${kind}', '${spseqAm.ODSEQ}');">
									재료 취소
								</button>
							</td>
						</tr>
					</c:if>
				</c:forEach>
				<tr>
					<td colspan="6">총가격 : ${totalPrice}</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<input type="button" class="btn" value="목록" onclick="go_order_mov()">
</article>

<%@ include file="../../admin/footer.jsp"%>