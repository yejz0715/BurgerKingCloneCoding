<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
function popup3() {
	
	window.open("Mpopup3.do", "a",
			"width=620, height=900, resizable=no");}

 
</script>


<article>
<div class="clear"></div>
<div class="contentsWrap">
	
	<div class="prd_intro">
		<div class="web_container4">
			<div class="intro_txt"><strong class="tit"><span>${productVO.PNAME}</span></strong>
			<p class="subtxt"><span></span></p></div>
			<div class="prd_img"><img src="<c:url value='/image/menu/product/${productVO.IMAGE}'/> "/></div>
		</div>
	</div>
	<div class="menu_subinfoWrap">
		<div class="web_container4">
			<a class="btn_back" href="MmenuListForm.do?kind1=${productVO.KIND1}"><span>메뉴 목록으로 돌아가기</span></a>
			<div class="nutrition_info"><button type="button" class="btn_nutrition" onClick="popup3();">
			<span>영양분석표, 알레르기 유발성분</span></button></div>
		</div>
	</div>
	<div class="bg_w">
		<div class="web_container5">
			<ul class="prdmenu_list prd_submenu"> 
			<c:forEach var="list2" items="${list2}">
				<li>
				<div class="prd_img"><img src="<c:url value='/image/menu/product/${list2.IMAGE}'/> " width="296" height="193"/></div>
				<div class="cont">
					<div class="tit"><strong>${list2.PNAME}</strong></div>
					<div class="set"><strong>${list2.CONTENT}</strong></div>
				</div>
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
</div>
</article>

<%@ include file="../include/footer.jsp" %>