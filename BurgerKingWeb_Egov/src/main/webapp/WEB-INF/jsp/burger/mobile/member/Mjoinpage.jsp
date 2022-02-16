<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>
<script src="<c:url value='member.js" type="text/javascript' />"></script>



<article class="joinpageatc">

	<script type="text/javascript">
		
	
	function idcheck(){
		if( document.MjoinpageForm.id.value=="" ){
			alert("아이디를 입력하세요" );
			documnet.MjoinpageForm.id.focus();
			return;
		}
		var url = "Midcheck.do?id=" + document.MjoinpageForm.id.value;
		var opt = "toolbar=no, menubar=no, resizable=no, width=400, height=350";
		window.open(url, "idcheck", opt);
	}
		
	function go_save(){
		
		if (document.MjoinpageForm.id.value == "") {
			alert("아이디를 입력하여 주세요."); 	    
		    document.MjoinpageForm.id.focus();
		} else if(document.MjoinpageForm.name.value == "") {
		    alert("이름을 입력해 주세요.");	    
		    document.MjoinpageForm.name.focus();
		} else if(document.MjoinpageForm.phone.value == "") {
		    alert("휴대폰번호을 입력해 주세요.");	   
		    document.MjoinpageForm.phone.focus();
		} else if(document.MjoinpageForm.pwd.value == "") {
		    alert("비밀번호를 입력해 주세요.");	    
		    document.MjoinpageForm.pwd.focus();
		} else if(document.MjoinpageForm.pwd.value != document.MjoinpageForm.pwdCheck.value) {
		    alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");	    
		    document.MjoinpageForm.pwd.focus();
		} else{
			/* document.joinpageForm.action ="burger.do"; */
			document.MjoinpageForm.submit(); 
		}
	}
	
	
	
	</script>



	<form method="post" name="MjoinpageForm" action="Mjoinpage.do">
	<fieldset>
		<div class="contentsBox01">
			<div class="web_container03">
				<div class="subtitWrap m_bg_basic">
					<h2 class="page_tit">회원가입</h2>
				</div>
				<div class="container01">
					<h3 class="tit001 tit_ico man">
						<span>기본정보</span>
					</h3><br><br>
					<div class="container02">
						<div class="titboxx">
							<p>회원정보를 입력해 주세요</p><br>
							<label style="color: red;">${message}</label>
						</div>
						<div class="dlist01">
							<dl>
								<dt class="WEB vtop77">이메일 아이디</dt>
								<dd>
									<div class="inpbox">
                              <label>
                              <input type="email" placeholder="이메일 아이디"class="st02" name="id" value="${memberVO.ID}"></label>
                              <p class="txt77"><input type="button" width="40" value="중복확인" class="dup reid" onclick="idcheck()">  사용 가능한 이메일 주소를 입력해 주세요. (예:
                                 name@mail.com)<input type="hidden" name="reid" value="${reid }"></p>
                           </div>
								</dd>
							</dl>
							<dl>
								<dt class="WEB vtop77">이름</dt>
								<dd>
									<div class="inpbox">
										<label><input type="text" placeholder="이름"
											class="st02" id="name" name="name" value="${memberVO.NAME}">
											<button type="button" class="btn_del01"
												style="display: none;">
											</button></label>
										<p class="txt77">이름을 입력해 주세요.</p>
									</div>
								</dd>
							</dl>
							<dl>
								<dt class="WEB vtop77">휴대폰 번호</dt>
								<dd>
									<div class="inpbox">
										<label><input type="text" placeholder="휴대폰 번호"
											class="st02" id="phone" name="phone" value="${memberVO.PHONE}">
											<button type="button" class="btn_del01"
												style="display: none;">
											</button></label>
										<p class="txt77">사용중인 휴대폰번호를 입력해 주세요.</p>
									</div>
								</dd>
							</dl>
						</div>
					</div>
					<h2 class="tit001 tit_ico key">
						<span>비밀번호 입력</span>
					</h2>
					<div class="container02">
						<div class="dlist01">
							<dl>
								<dt class="WEB vtop77">비밀번호</dt>
								<dd>
									<div class="inpbox">
										<label><span class="hide">비밀번호</span><input
											placeholder="비밀번호를 입력해주세요"
											type="password" class="st02" name="pwd" size="10" > </label>
										<p class="txt77">
											<span>사용할 비밀번호를 입력해 주세요 </span>
										</p>
									</div>
								</dd>
							</dl>
							<dl>
								<dt class="WEB vtop77">비밀번호 확인
									<span class="hide">비밀번호 확인</span>
								</dt>
								<dd>
									<div class="inpbox">
										<label><span class="hide">비밀번호 확인</span><input
											placeholder="비밀번호 확인" type="password" class="st02" 
											name="pwdCheck" size="10" >
											</label>
										<p class="txt77">
											<span>비밀번호를 다시한번 입력해 주세요.</span>
										</p>
									</div>
								</dd>
							</dl>
						</div>
					</div>
					
					<div class="c_btn">
						<button type="button"  onclick="go_save();" class="btn77 btn01_m">
							<span>회원가입</span><br>
						</button>
					</div>
				</div>
			</div>			
		</div>
	</fieldset>
	</form>
</article>

<%@ include file="../include/footer.jsp" %>