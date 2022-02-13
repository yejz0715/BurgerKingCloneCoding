<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>

<article>
	<div class="web_container1">
		<h2 class="page_tit02 bg_w">
			<em>WHERE TASTE IS KING</em><span>어서오세요! 버거킹입니다.</span>
		</h2>
		<div class="weblogin_ui">
			<form class="login" name="frm" method="post" action="Mlogin.do">
				<h3 class="tit_ico tit01">일반 로그인</h3>
				<div class="cont"> 
					<div class="inpbox"> 
						<label> 
							<span class="hide">ID</span>
							<input type="email" placeholder="아이디 (이메일)" id="userId" name="id">
							<button type="button" tabindex="-1" class="btn_del01" style="display: none;">
								<span>입력 텍스트 삭제</span>
							</button>
						</label>
					</div> 
					<div class="inpbox">
						<label>
							<span class="hide">Password</span>
							<input placeholder="비밀번호" type="password" name="pwd">
						</label>
					</div><br>
					<lable style="color: red;">${message} </lable>
					<div class="c_btn item2">
						<input type="submit" class="btn01 m red" value="로그인" onclick="return login_chk()"/> 
						<input type="button" class="btn01 m" onclick="location.href='MjoinForm.do'" value="회원가입" />
					</div> 
					<div class="login_menu">
						<a href="MfindIdForm.do"><span>아이디 찾기</span></a> 
						<a href="MfindPwdForm.do"><span>비밀번호 찾기</span></a>
					</div>  
				</div> 
			</form> 
			<div id="base" style="background-color: #f2ebe6;">
			<div id="nonmember_MenuBar"> 
				<ul id="nonmenuBar_ul">
					<li><button id="non_Btn1" onclick="shownonArea1()">비회원 주문</button></li>
					<li><button id="non_Btn2" onclick="shownonArea2()">비회원 주문내역</button></li>
				</ul>
			</div>
			${message2}
			<!-- non_Btn1 showing view -->
			<div class="nonmember_Textarea" id="nonArea1">
				<div id="nonmember_Text">회원가입 없이 비회원으로 주문이 가능합니다.</div>
				<br><br><br><br> 
				<c:if test="${empty memberkind}">
					<input type="button" class="btn01 m" value="비회원 주문" onclick="location.href='MguestLoginForm.do'">
				</c:if>
			</div> 
			<!-- non_Btn2 showing view -->
			<div class="nonmember_Textarea" id="nonArea2">
				<div id="nonmember_Text2">
					<form action="MnonOrderList.do" method="post" name="frm2" style="padding-top: 20px;">
						<div class="inpbox">
							<label> 
								<span>주문번호</span> 
								<input type="text" placeholder="주문번호" name="oseq" style="width: 200px;">
							</label>
						</div>
						<div class="inpbox">
							<label> 
								<span>비밀번호</span> 
								<input placeholder="비밀번호" type="password" name="pwd2" style="width: 200px;">
							</label>
						</div>
						<br><br><br>
						<input type="submit" class="btn01 m" value="주문 조회" onclick="return nonOrderChk();">
					</form>
				</div>
			</div>
		</div>
		</div>
		
	</div>
</article>

<%@ include file="../include/footer.jsp"%>