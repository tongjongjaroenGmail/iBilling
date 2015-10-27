
<div class="navbar navbar-default" id="navbar">
	<script type="text/javascript">
		try {
			ace.settings.check('navbar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand"> <small> <img alt="" src="img/logo.png"> Claim Recovery Application
			</small>
			</a>
			<!-- /.brand -->
		</div>
		<!-- /.navbar-header -->

		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="light-orange"><a data-toggle="dropdown" href="#" class="dropdown-toggle"> 
					<%-- <img class="nav-user-photo" src="assets/avatars/user.jpg" alt="Jason's Photo" /> --%>
					<span class="user-info"> 
					<small>Welcome,</small>
							${sessionScope.loginUser.name}&nbsp;&nbsp;${sessionScope.loginUser.lastName}
					</span> 
					<i class="icon-caret-down"></i>
				</a>

					<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

						<li><a href="${pageContext.request.contextPath}/mainPage"> <i class="icon-user"></i>Profile
						</a></li>

						<li class="divider"></li>

						<li><a href="${pageContext.request.contextPath}/j_spring_security_logout"> <i class="icon-off"></i>
								Logout
						</a></li>
					</ul></li>
			</ul>
			<!-- /.ace-nav -->
		</div>
		<!-- /.navbar-header -->
	</div>
	<!-- /.container -->
</div>