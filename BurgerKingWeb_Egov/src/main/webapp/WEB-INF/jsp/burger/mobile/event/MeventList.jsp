<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/event.css'/> "/>

<article>
	
	<div class="event_web_container">
		<div class="subtit">
			<h1 class="event_tit">이벤트</h1>
			<ul class="submenu_right">
				<li><span style="border-bottom: 3px solid red;"><a
						href="MeventListForm.do" style="color: red;">전체</a></span></li>
				<li><span><a href="MeventTab2.do">진행중</a></span></li>
				<li><span><a href="MeventTab3.do">종료</a></span></li>
			</ul>
		</div>

		<form name="frm" method="post" action="Mburder.do">
			<div class="eventarea">
				<ul>
					<c:forEach var="EventVO" items="${eventList}">
						<li><a
							href="MeventDetailForm.do?eseq=${EventVO.ESEQ}">
								<input type="hidden" name="eseq" value="${EventVO.ESEQ}" /> 
								<img class="eventImg" src="<c:url value='/image/main/event/${EventVO.THUMBNAIL}'/>" />
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
