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
	
		<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/invoice'}">class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/invoice"> 
				<i class="icon-double-angle-right"></i> 
				<span class="menu-text">ออกใบวางบิล</span>
			</a>
		</li>
		
		<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/travelingExpenses'}">class="active"</c:if>>
			<a href="${pageContext.request.contextPath}/travelingExpenses"> 
				<i class="icon-double-angle-right"></i> 
				<span class="menu-text">ออกใบจ่ายค่าพาหนะ</span>
			</a>
		</li>
		
		</c:if>
       				
       	<c:if test="${loginUser.position.id == 1 or loginUser.department.id == 2}">
       	
		<li <c:if test="${fn:startsWith(currentUrl, '/report')}">class="active open"</c:if>>
			<a href="#" class="dropdown-toggle">
				<i class="icon-tag"></i>
				<span class="menu-text">รายงาน</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li <c:if test="${fn:startsWith(currentUrl, '/report')}">class="active"</c:if>>
					<a href="${pageContext.request.contextPath}/report/statisticsSurvey"> 
						<i class="icon-double-angle-right"></i> 
						<span class="menu-text">สถิติงานสำรวจ</span>
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


