<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>


<article>
	
	<div class="web_container1">
		<div class="welcomeBox">
			<h3 class="page_tit02">환영합니다!</h3>
			<div class="txt_welcome"> 
				<p class="txt_welcome">
					<span class="br" class="txt_welcome"><strong>이메일 주소</strong> 혹은 <strong>SNS
							계정</strong>으로</span> 간편하게 <strong><em class="txt_welcome">회원가입</em></strong> 하세요!
				</p>
				<p class="info">버거킹 서비스는 만 14세 이상부터 이용 하실 수 있습니다.</p>
			</div>
		</div>
		<div class="weblogin_ui"> 
			<div class="login">
				<h3 class="tit01 tit_icon key02"  >이메일 회원가입</h3><br><br><br> 
				<div class="c_btn" style="none">
					<a href="Mcontract.do" class="btn02 st003" ><span>버거킹 회원가입</span></a>
				</div>
			</div>
			<div id="base" style="background-color: white;">
				<div class="imgs" id="view1"><img src="<c:url value='image/menu/2/doublewhitegarlic.png' />" style="width: 600px;">
				<div class="loginimg_name"><span>더블화이트갈릭와퍼</span></div>				</div>
        		<div class="imgs" id="view2"><img src="<c:url value='image/menu/2/guinnessquattrocheese.png' />" style="width: 600px;">
        		<div class="loginimg_name"><span style="color:black;text-align:center;font-weight:bold;">기네스콰트로치즈와퍼</span></div></div>
        		<div class="imgs" id="view3"><img src="<c:url value='image/menu/4/shrimpwhopperjr.png' />" style="width: 600px;">
        		<div class="loginimg_name"><span style="color:black;text-align:center;font-weight:bold;">아기상어새우버거</span></div></div>
        		<div class="imgs" id="view4"><img src="<c:url value='image/menu/5/longchicken.png' />" style="width: 600px;">
        		<div class="loginimg_name"><span style="color:black;text-align:center;font-weight:bold;">롱치킨버거</span></div></div>
        		<div class="imgs" id="view5"><img src="<c:url value='image/menu/6/crispyking8sauce.png' />" style="width: 600px;">
        		<div class="loginimg_name"><span style="color:black;text-align:center;font-weight:bold;">바삭킹</span></div></div>
			</div>
		</div>
	</div>
</article>

<%@ include file="../include/footer.jsp" %>