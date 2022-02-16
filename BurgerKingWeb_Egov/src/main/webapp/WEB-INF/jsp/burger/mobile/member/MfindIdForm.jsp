<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>

<script type="text/javascript">
function find_id(){
	if (document.frm.name.value == "") {
		alert("이름을 입력하여 주세요."); 	    
	    document.frm.name.focus();
	} else if(document.frm.phone.value == "") {
	    alert("휴대폰번호을 입력해 주세요.");	    
	    document.frm.phone.focus();
	} else{
		document.frm.action ="MfindId.do";
		document.frm.submit(); 
	}
  }
</script>   
<article style="background-color: #f2ebe6;">
	
	<div class="web_container04">
		<form name="frm" action="MfindId.do" method="post">
			
			<ul id="terms_wrap_ul">
				<li>
					<span>
						<a href="MfindIdForm.do" style="color: red; text-decoration: underline;">아이디 찾기</a>
					</span>
				</li>
				<li><span><a href="MfindPwdForm.do">비밀번호 찾기</a></span></li>
			</ul>
			<h1 class="fbig_h1" style="margin-bottom: 30px; ">아이디/비밀번호 찾기</h1>
			<div class="btnarea" style="text-align: center;">
				<c:if test="${empty message}">
					<span style="font-family: 'Noto Sans KR', sans-serif;">가입시 회원정보로 등록한 이름과 휴대폰 번호를 입력해 주세요.</span>
				</c:if>
				<c:if test="${!empty message}">
					<span class="coment_text">${message}</span>
				</c:if>
			</div> 
			<div class="textarea" style="max-height: 730px; overflow: hidden;">
				<div class="inpbox" style="margin-bottom: 30px;">
					<div class="vtop">이름</div>
					<label>
						<span class="hide">ID</span>
						<input type="text" placeholder="이름" id="name" name="name" value="${memberVO.NAME}" onblur="check_input1();" >
						<span class="coment_text" style="position: relative;">이름을 입력해 주세요.</span>
					</label>
				</div>
				<div class="inpbox">
				<div class="vtop">휴대폰 번호</div>
					<label>
						<span class="hide">Phone</span>
						<input placeholder="휴대폰 번호" type="text" name="phone" id="phone" value="${memberVO.PHONE}" onblur="check_input2();">
						<span class="coment_text" style="position: relative;">휴대폰 번호를 입력해 주세요.</span>
					</label>
				</div>
				<ul class="find_id_text">
					<li>ㆍ가입 시 입력한 이름과 휴대폰번호로 아이디 정보를 찾을 수 있습니다.</li>
					<li>ㆍ네이버, 카카오톡, 삼성 앱카드, 애플아이디로 가입하신 회원님께서는 해당 서비스에서 아이디/비밀번호 찾기를
					진행해 주세요.</li>
				</ul>
			</div>
			<div class="c_btn item2 findidalign">
				<input type="button" class="btn01 m red" value="아이디 찾기" onClick="find_id()"/>
				<input type="button" class="btn01 m" value="돌아가기" onClick="location.href='MloginForm.do'"/>
			</div>
		</form>
	</div>
	
</article>
<%@ include file="../include/footer.jsp" %>