<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<a class="menu-toggler" id="menu-toggler" href="#"> <span class="menu-text"></span></a>
			
<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
	<%-- 
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="icon-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="icon-pencil"></i>
			</button>

			<button class="btn btn-warning">
				<i class="icon-group"></i>
			</button>

			<button class="btn btn-danger">
				<i class="icon-cogs"></i>
			</button>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span> <span class="btn btn-warning"></span> <span
				class="btn btn-danger"></span>
		</div>
		--%>
	</div>

	<!-- #sidebar-shortcuts -->
	
	<c:set var="currentUrl" value="${requestScope['javax.servlet.forward.servlet_path']}"/>

	<ul class="nav nav-list">
		<c:if test="${loginUser.position.id == 1 or loginUser.department.id == 1}">
		
		<li <c:if test="${fn:startsWith(currentUrl, '/invoice')}">class="active open"</c:if>>
			<a href="#" class="dropdown-toggle">
				<i class="icon-tag"></i>
				<span class="menu-text">วางบิลทิพยฯ</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/invoiceSearchPage'}">class="active"</c:if>>
					<a href="${pageContext.request.contextPath}/invoiceSearchPage"> 
						<i class="icon-double-angle-right"></i> 
						<span class="menu-text">ค้นหาข้อมูลวางบิล</span>
					</a>
				</li>
				
				<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/invoice/group'}">class="active"</c:if>>
					<a href="${pageContext.request.contextPath}/invoice/group"> 
						<i class="icon-double-angle-right"></i> 
						<span class="menu-text">จัดชุดวางบิล</span>
					</a>
				</li>
			</ul>
		</li>
		
		<li <c:if test="${fn:startsWith(currentUrl, '/survey')}">class="active open"</c:if>>
			<a href="#" class="dropdown-toggle">
				<i class="icon-tag"></i>
				<span class="menu-text">จ่ายค่าสำรวจพนักงาน</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/survey/search'}">class="active"</c:if>>
					<a href="${pageContext.request.contextPath}/survey/search"> 
						<i class="icon-double-angle-right"></i> 
						<span class="menu-text">ค้นหาข้อมูลใบสำรวจ</span>
					</a>
				</li>
				
				<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/survey/create'}">class="active"</c:if>>
					<a href="${pageContext.request.contextPath}/survey/create"> 
						<i class="icon-double-angle-right"></i> 
						<span class="menu-text">สร้างใบจ่ายค่าสำรวจพนักงาน</span>
					</a>
				</li>
			</ul>
		</li>
		
		</c:if>
       				
       	<c:if test="${loginUser.position.id == 1 or loginUser.department.id == 2}">
       	
       	<li <c:if test="${fn:startsWith(currentUrl, '/report')}">class="active open"</c:if>>
			<a href="#" class="dropdown-toggle">
				<i class="icon-tag"></i>
				<span class="menu-text">ข้อมูลเคลม</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/report/claim'}">class="active"</c:if>>
					<a href="${pageContext.request.contextPath}/report/claim"> 
						<i class="icon-double-angle-right"></i> 
						<span class="menu-text">ตรวจสอบเคลม</span>
					</a>
				</li>
				
				<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/report/statisticsSurvey'}">class="active"</c:if>>
					<a href="${pageContext.request.contextPath}/report/statisticsSurvey"> 
						<i class="icon-double-angle-right"></i> 
						<span class="menu-text">รายงานสถิติงานสำรวจ</span>
					</a>
				</li>
			</ul>
		</li>

		</c:if>
	</ul>


	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	</div>

	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
	</script>
</div>


