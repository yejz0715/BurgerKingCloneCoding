<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../admin/header.jsp" %>
<%@ include file="../../admin/sub_menu.jsp"%>

<article>
	<h1>주문리스트</h1>
	<form name="frm" method="post">
		<table style="margin: 0 auto;">
			<tr>
				<td>
					주문자 이름 <input type="text" name="key" value="${key}">
					<input type="button" class="btn" value="검색" onClick="go_search_order('${kind}');">
					<input type="button" class="btn" value="회원 구분 변경" onClick="memberKindChange('${kind}');">
					<input type="button" class="btn" name="btn_total" value="전체보기" onClick="go_total_order('${kind}');">
				</td>
			</tr>
		</table>
		<table id="orderList">
			<tr>
				<th>주문번호(<input type="checkbox" name="resultAll" onclick="resultAllCheck(this);">처리여부)</th>
				<th>주문자</th>
				<th>상품명</th>
				<th>수량</th>
				<th>전화</th>
				<th>주문일</th>
				<th>주문자 구분</th>
				<th>삭제<input type="checkbox" name="deleteAll" onclick="deleteAllCheck(this);"></th>
			</tr>
			<c:forEach items="${orderList}" var="orderVO">
				<tr>
					<td>
						<c:choose>
							<c:when test='${orderVO.RESULT=="1"}'>
								<span style="font-weight: bold; color: blue">${orderVO.OSEQ}</span>
								(<input type="checkbox" name="result" value="${orderVO.ODSEQ}">미처리)
							</c:when>
							<c:when test='${orderVO.RESULT=="2"}'>
								<span style="font-weight: bold; color: blue">${orderVO.OSEQ}</span>
								(<input type="checkbox" name="result" value="${orderVO.ODSEQ}">처리중)
							</c:when>
							<c:when test='${orderVO.RESULT=="3"}'>
								<span style="font-weight: bold; color: blue">${orderVO.OSEQ}</span>
								(<input type="checkbox" name="result" value="${orderVO.ODSEQ}">배달중)
							</c:when>
							<c:otherwise>
								<span style="font-weight: bold; color: red">${orderVO.OSEQ}</span>
								(<input type="checkbox" checked="checked" disabled="disabled">완료)
							</c:otherwise>
						</c:choose>
					</td>
					<td>${orderVO.MNAME}</td>
					<td><a href="adminOrderDetailForm.do?kind=${kind}&seq=${orderVO.ODSEQ}">${orderVO.PNAME}</a></td>
					<td>${orderVO.QUANTITY}</td>
					<td>${orderVO.PHONE}</td>
					<td><fmt:formatDate value="${orderVO.INDATE}"/></td>
					<td>
						<c:if test="${orderVO.MEMBERKIND == 1}">
							회원
						</c:if>
						<c:if test="${orderVO.MEMBERKIND == 2}">
							비회원
						</c:if>
					</td>
					<td>
						<input type="checkbox" name="delete" value="${orderVO.ODSEQ}">
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="clear"></div>
		<input type="button" class="btn" style="width: 200px" value="다음단계 진행" onclick="go_order_save('${kind}')">
		<input type="button" class="btn" style="float: right;" value="삭제" onclick="del_order('${kind}');">
	</form>
	
	<br><br>
	
	<jsp:include page="../../admin/paging/paging2.jsp">
		<jsp:param name="page" value="${paging.page}"/>
		<jsp:param name="beginPage" value="${paging.beginPage}"/>
		<jsp:param name="endPage" value="${paging.endPage}"/>
		<jsp:param name="prev" value="${paging.prev}"/>
		<jsp:param name="next" value="${paging.next}"/>
		<jsp:param name="kind" value="${kind}"/>
		<jsp:param name="command" value="adminOrderList.do"/>
	</jsp:include>
</article>

<%@ include file="../../admin/footer.jsp"%>