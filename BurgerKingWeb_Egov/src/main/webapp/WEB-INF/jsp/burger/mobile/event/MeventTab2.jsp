<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/event.css'/> "/>
<head profile="http://www.w3.org/2005/10/profile" />

<article>
	<div class="location">
	<div class="web_container1">
		<ul>
			<li><a href="mobilemain.do">HOME</a>&nbsp;>&nbsp;</li>
			<li><a href="MeventListForm.do">이벤트</a>&nbsp;>&nbsp;</li>
			<li>진행중</li>
		</ul>
	</div>
	</div>
	<div class="event_web_container">
		<div class="subtit">
			<h1 class="event_tit">이벤트</h1>
			<ul class="submenu_right">
				<li><span><a href="MeventListForm.do">전체</a></span></li>
				<li><span style="border-bottom: 3px solid red;"><a
						href="MeventTab2.do" style="color: red;">진행중</a></span></li>
				<li><span><a href="MeventTab3.do">종료</a></span></li>
			</ul>
		</div>
		<form name="frm" method="post" action="Mburder.do">

			<div class="eventarea">
				<ul>
					<c:forEach var="EventVO" items="${eventList}">
						<li><a href="MeventDetailForm.do?eseq=${EventVO.ESEQ}"> <input
								type="hidden" name="eseq" value="${EventVO.ESEQ}" /> <img
								class="eventImg" src="<c:url value='/image/main/event/${EventVO.THUMBNAIL}'/>" />
						</a>
							<p>
								${EventVO.STARTDATE}
								~
								${EventVO.ENDDATE}
							</p></li>
					</c:forEach>
				</ul>
			</div>
		</form>
	</div>
</article>


<%@ include file="../include/footer.jsp"%>
