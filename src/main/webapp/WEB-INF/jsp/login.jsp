<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><spring:message code="label.login.title"></spring:message>
</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<style type="text/css">

</style>

<!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
<!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
<!--script src="js/less-1.3.3.min.js"></script-->
<!--append #!watch to the browser URL, then refresh the page. -->

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="img/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="img/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="img/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="img/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="img/favicon.png">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/scripts.js"></script>
</head>

<body>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="/">Coding Saint</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						
				   </ul>
					
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Language<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="?language=en">English</a>
								</li>
								<li>
									<a href="?language=hi">Hindi | हिंदी</a>
								</li>
								
								<li>
									<a href="?language=ch">Chinese | 中国</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				
			</nav>
		</div>
	</div>
	<div class="container">

		<div class="row clearfix">
			<div class="row clearfix">
			
				<div class="col-md-6 col-md-offset-6 column">
				<div class="panel panel-success">
				<div class="panel-heading">
					<h3 class="panel-title">
						<spring:message code="label.login.title"></spring:message>
					</h3>
				</div>
				<div class="panel-body">
					<form id="form" class="form-horizontal" method="POST"
						action="<c:url value='/login.do'></c:url>">
						<div class="form-group">
							<div class="col-sm-12">
								<input class="form-control" id="username" type="text"
									name="username"
									placeholder='<spring:message  code="label.login.username"></spring:message>' />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<input class="form-control" id="inputPassword3" type="password"
									name="password"
									placeholder="<spring:message   code="label.login.password"></spring:message>" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<div class="checkbox">
									<label><input type="checkbox" /> <spring:message
											code="label.login.rememberMe"></spring:message> </label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class=" col-sm-12">
								<button type="submit" class="btn btn-success">
									<spring:message code="label.login.button.signin"></spring:message>
								</button>
								<button type="reset" class="btn btn-default">
									<spring:message code="label.login.button.clear"></spring:message>
								</button>

							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>

				</div>
				
					<c:if test="${not empty error}">
					<div class="panel-footer">
			<div class="error">${error}</div>
			</div>
		</c:if>
		<c:if test="${not empty msg}">
		<div class="panel-footer">
			<div class="msg">${msg}</div>
			</div>
		</c:if>
				
			</div>
				
										<hr />
				</div>


			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-6 col-md-offset-6 column">
				<spring:message code="label.login.signInThrough"></spring:message>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-3 col-md-offset-6 column">
				<i class="fa fa-5x fa-facebook-official "></i>
			</div>
			
			<div class="col-md-3 column">
				<i class="fa fa-5x fa-twitter"></i>
			</div>
		</div>
	</div>

</body>
</html>