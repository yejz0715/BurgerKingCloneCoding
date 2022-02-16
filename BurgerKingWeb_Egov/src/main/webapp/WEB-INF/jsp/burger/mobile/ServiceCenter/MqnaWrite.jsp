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
	<br><br><br><br>
	<div class="qna_webcon">
		<h3>1:1 고객 게시판</h3><br>
		<h2>고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h2>
		<form name="frm" method="post" action="MqnaWrite.do">
			<input type="hidden" name="command" value="qnaWrite.do">
			<input type="hidden" name="id" value="${id}">
			<fieldset style="border: 0px; background-color: #f2ebe6;">
				<label>제목</label><br>
				<input type="text" name="subject" size="50"><br><br>
				<label>질문내용</label><br>
				<textarea rows="10" cols="120" name="content"></textarea><br>
				<label>비밀번호</label>
				<input type="password" name="pass" size="4">
			</fieldset>
			<div class="clear"></div>
			 
			<div class="qnaWrite_button_area">
				<div class="tab_cont">
					<div class="faq_category">
					<label>${message}</label>
						<label class="switch01">
							<input type="radio" name="faqcategory" checked="checked" onclick="qna_write_chk()">
							<span>작성</span>
						</label>
						<label class="switch01">
							<input type="radio" name="faqcategory" onclick="location.href='MqnaForm.do'">
							<span>취소</span>
						</label>
					</div>
				</div>
			</div>
		</form>
	</div>
</article>
</div>
<%@ include file="../include/footer.jsp" %>