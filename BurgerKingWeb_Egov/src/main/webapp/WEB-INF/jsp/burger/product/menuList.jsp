<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<div class="clear"></div>
<script>
$(function(){
	$( 'document' ).ready(function() {
		  $('#pk${kind1}').css("color", "red");
		  $('#pk${kind1}').css("text-decoration", "underline");
	});
});
</script>
<article>
	
	<div class="location">
	<div class="web_container1">
		<ul>
			<li><a href="main.do">HOME</a>&nbsp;>&nbsp;</li>
			<li>메뉴소개</li>
		</ul>
		</div>
	</div>
	<div class="subtitWrap">
		<h3 class="page_tit">메뉴소개</h3>
		<div class="tab01" >
			<ul>
				<li><a href="menuListForm.do?kind1=1">
					<button type="button"><span id="pk1">스페셜&amp;할인팩</span></button></a></li>
				<li><a href="menuListForm.do?kind1=2">
					<button type="button"><span id="pk2">프리미엄</span></button></a></li>
				<li><a href="menuListForm.do?kind1=3">
					<button type="button"><span id="pk3">와퍼</span></button></a></li>
				<li><a href="menuListForm.do?kind1=4">
					<button type="button"><span id="pk4">주니어&amp;버거</span></button></a></li>
				<li><a href="menuListForm.do?kind1=5">
					<button type="button"><span id="pk5">올데이킹&amp;치킨버거</span></button></a></li> 
				<li><a href="menuListForm.do?kind1=6">
					<button type="button"><span id="pk6">사이드</span></button></a></li>
				<li><a href="menuListForm.do?kind1=7">
					<button type="button"><span id="pk7">음료&amp;디저트</span></button></a></li>
				<li><a href="menuListForm.do?kind1=8">
					<button type="button"><span id="pk8">독퍼</span></button></a></li>
			</ul>
		</div>
	</div>
	<div class="menuarea">
		<ul>
			<c:forEach var="productVO" items="${productList}">
				<c:choose>
					<c:when test="${productVO.KIND3==4 && productVO.USEYN==1}">
					<li><a href="menudetailForm.do?pseq=${productVO.PSEQ}">
						<div class="menuImgdiv">
							<img class="menuImg" src="<c:url value='/image/menu/product/${productVO.IMAGE}'/> "/>					
						</div>
						<div class="menuContdiv">
							<p>${productVO.PNAME}</p>
						</div>
						</a></li>
					</c:when>
				</c:choose>
			</c:forEach>	
		</ul>
	</div>
	<!-- DB구조 변동에 따라 위 코드는 변경가능성 존재 -->
	
</article>

<%@ include file="../include/undermenu.jsp" %>
<%@ include file="../include/footer.jsp" %>