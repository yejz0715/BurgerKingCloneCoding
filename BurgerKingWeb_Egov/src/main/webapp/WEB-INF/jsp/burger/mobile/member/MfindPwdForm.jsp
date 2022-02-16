<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>

<script type="text/javascript">
function find_pwd(){
	if (document.frm.name.value == "") {
		alert("이름을 입력하여 주세요."); 	    
	    document.frm.name.focus();
	} else if(document.frm.id.value == "") {
	    alert("아이디(이메일)를 입력해 주세요.");	    
	    document.frm.id.focus();
	} else{
		document.frm.action ="MfindPwd.do";
		document.frm.submit(); 
	}
  }
</script> 
<article style="background-color: #f2ebe6;">
	
	<div class="web_container03">
		<form name="frm" action="MfindPwd.do" method="post">
			
			<ul id="terms_wrap_ul">
				<li>
					<span>
						<a href="MfindIdForm.do" >아이디 찾기</a>
					</span> 
				</li>
				<li><span><a href="MfindPwdForm.do" style="color: red; text-decoration: underline;">비밀번호 찾기</a></span></li>
			</ul>
			<h1 class="fbig_h1" style="margin-bottom: 30px;">아이디/비밀번호 찾기</h1>
			<div class="btnarea" style="text-align: center;">
				<c:if test="${empty message}">
					<span style="font-family: 'Noto Sans KR', sans-serif;">회원님의 정보(이메일)로 비밀번호 재설정을 위한 경로를 보내 드립니다.</span>
				</c:if>
				<c:if test="${!empty message}">
					<span class="coment_text">${message}</span>
				</c:if>
			</div>
			<div class="textarea" style="max-height: 700px;">
				<div class="inpbox" style="margin-bottom: 30px;">
					<div class="vtop">이름</div>
					<label>
						<span class="hide">ID</span>
						<input type="text" placeholder="이름" id="name" name="name" value="${name}" onblur="check_input1();">
						<span id="name_coment_id" class="coment_text">이름을 입력해 주세요.</span>
					</label>
				</div>
				<div class="inpbox">
				<div class="vtop">아이디(이메일)</div>
					<label>
						<span class="hide">ID(email)</span>
						<input placeholder="아이디(이메일)" type="email" name="id" value="${id}" id="id" onblur="check_input3();">
						<span id="id_coment_id" class="coment_text">아이디를 입력해 주세요.</span>
					</label>
				</div>
				<ul class="find_id_text">
					<li>ㆍ가입시 입력한 이메일로 비밀번호 재설정을 위한 경로가 발송 됩니다.</li>
					<li>ㆍ네이버, 카카오톡, 삼성 앱카드, 애플아이디로 가입하신 회원님께서는 해당 서비스에서 아이디/비밀번호 찾기를 진행해 주세요.</li>
				</ul>
			</div>
			
			<div class="c_btn item2 findidalign">
				<input type="button" class="btn01 m red" value="비밀번호 찾기" onClick="find_pwd()"/>
				<input type="button" class="btn01 m" value="돌아가기" onClick="location.href='MloginForm.do'"/>
			</div>
		</form>
	</div>
</article>
<%@ include file="../include/footer.jsp" %>