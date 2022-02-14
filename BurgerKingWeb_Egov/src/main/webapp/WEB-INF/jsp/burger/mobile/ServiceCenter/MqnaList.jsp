<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="outmobile">

<article>
	
	<div class="subtitWrap">
		<h3 class="page_tit">고객센터</h3>
		<ul id="terms_wrap_ul" style="right: 0; top:0">
			<li>
				<span>
					<a href="MfaqList1.do">FAQ</a>
				</span>
			</li>
			<li><span><a href="MqnaForm.do" style="color: red; text-decoration: underline;">문의</a></span></li>
			<li><span><a href="MappGuideForm.do">App이용안내</a></span></li>
		</ul>
	</div>
	 
	<div class="web_container1">
		<c:choose>
			<c:when test="${empty loginUser}">
				<img style="width: 100%;" src="<c:url value='image/main/qna.PNG'/>">
				<span style="color: #6a6a6a; font-size: 2rem; float: right;">
					QnA 작성 및 확인은 <a style="text-decoration: none;" href="MloginForm.do">로그인</a> 후 이용해주세요.
					</span>
			</c:when>
			<c:otherwise>
				<div class="qna_button_area">
					<div class="tab_cont">
						<div class="faq_category">
							<label class="switch01">
								<input type="radio" name="faqcategory" checked="checked" onclick="location.href='MqnaWriteForm.do'">
								<span>문의 작성</span>
							</label>
							<label class="switch01">
								<input type="radio" name="faqcategory" onclick="del_qna();">
								<span>문의 삭제</span>
							</label>
						</div>
					</div>
				</div>
				<table class="qna_table">
					<tr style="background-color: black; color: #fff;">
						<th>번호</th>
						<th>제목</th>
						<th>작성일</th>
						<th>작성자</th>
						<th>답변여부</th>
						<th>삭제</th>		
					</tr>
					<c:choose>
						<c:when test="${empty qnaList}">
							<tr align="center">
								<td colspan="6"> 
									<span>작성하신 문의가 없습니다.</span>
								</td>
							</tr>
						</c:when>
						<c:otherwise>  
							<form name="frm" method="post"> 
								<c:forEach items="${qnaList}" var="qnaVO">
									<tr align="center">
										<td> ${qnaVO.QSEQ}</td>    
							    		<td><a href="#" onclick="input_pass('${qnaVO.QSEQ}');">${qnaVO.SUBJECT}</a></td>      
							       		<td><fmt:formatDate value="${qnaVO.INDATE}" type="date"/></td>
							       		<td> ${qnaVO.ID}</td> 
							       		<td>
							       			<c:choose>
												<c:when test="${qnaVO.REP==1}"> no </c:when>
												<c:when test="${qnaVO.REP==2}"> yes </c:when>
											</c:choose>
										</td> 
										<td>
											<input type="checkbox" style="position:relative; opacity:1;" name="delete" value="${qnaVO.QSEQ}">
										</td>
									</tr>
								</c:forEach>
							</form>
						</c:otherwise>
					</c:choose>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
	
</article>
</div>

<%@ include file="../include/footer.jsp" %>