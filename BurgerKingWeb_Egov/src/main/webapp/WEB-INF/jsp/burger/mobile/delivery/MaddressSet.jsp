<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/Delivery/deli_header.jsp"%>
<div class="clear"></div>

<article>
	
	<div class="webAddress">
		<h1 id="addresstext">주소지를 설정해주세요.<br><b>주소를 설정하셔야 주문이 가능합니다.</b></h1>
		<form method="post" name="address" action="MmyAddress.do">
			<fieldset>
				<legend>MY배달지</legend>
				<label>Zip Code</label><input type="text" name="zip_num" size="10" style="width: 200px;">
				<input type="button" value="주소 찾기" onclick="post_zip()"><br>
				<label>Address</label><input type="text" name="addr1" size="50"><br>
				<label>&nbsp;</label><input type="text" name="addr2" size="25">
				<br>
			</fieldset>
			<div class="c_btnItem2"> 
				<input type="reset" class="btn01 red" value="취소" /> 
				<input type="submit" class="btn01 m" value="저장하기" />
			</div>
		</form>
	</div>
</article>
<%@ include file="../include/footer.jsp"%>