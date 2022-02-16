<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>

<article style="background-color: #f2ebe6;">
	<div class="contentsBox01">
	<div class="web_container03">
		
		<ul id="terms_wrap_ul" style="left: 0px; position:relative;">
			<li>
				<span>
					<a href="MfindIdForm.do" style="font-size: 1.45em;">아이디 찾기</a>
				</span>
			</li>
			<li><span><a href="MfindPwdForm.do?id=${memberVO.ID}&name=${memberVO.NAME}" style="color: red; text-decoration: underline; font-size: 1.45em;">비밀번호 찾기</a></span></li>
		</ul>
		<h1 class="tit01">아이디/비밀번호 찾기</h1>
		<div class="btnarea" style="height:0px; margin-top: 120px;"></div>
		<div class="textarea">
			<div class="msgBox">
				<span class="showid_text">비밀번호 수정</span><br>
				<span class="showid_text"><small>새로운 비밀번호를 설정해주세요.</small></span>
			</div>
			<div class="inpbox">
				<c:choose>
					<c:when test="${empty memberVO}">
						
					</c:when>
					<c:otherwise>
						<form method="post" name="frm" action="MupdatePwd.do">
							<input type="hidden" name="mseq" value="${memberVO.MSEQ}">
							<div class="textareapwd" style="position:relative">
								<div class="inpbox" style="margin-bottom: 30px;">
									<div class="vtop">비밀번호</div>
									<label>
										<span class="hide">비밀번호</span>
										<input type="password" placeholder="비밀번호" id="pwd" name="pwd" onblur="check_input4();">
										<span class="coment_text" style="position: relative;">새 비밀번호를 입력해 주세요.</span>
									</label>
								</div>
								<div class="inpbox">
								<div class="vtop">비밀번호 확인</div>
									<label>
										<span class="hide">비밀번호 확인</span>
										<input placeholder="비밀번호 확인" type="password" name="pwd_chk" id="pwd_chk" onblur="check_input5();">
										<span class="coment_text" style="position: relative;">비밀번호 재확인</span>
									</label>
								</div>
							</div>
							<div class="c_btn item2 findidalign" style="position:relative;">
								<input type="submit" class="btn01 m red" value="비밀번호 수정" 
								style="position:relative; margin: 0 25%;" onclick="return updatepwdChk();"/>
							</div>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	</div>
</article>

<%@ include file="../include/footer.jsp" %>