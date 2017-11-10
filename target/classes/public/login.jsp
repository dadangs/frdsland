<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<body class="hold-transition login-page">
<div class="login-box">

<div class="login-box-body" ng-controller="loginController">
    <p class="login-box-msg">Sign in to start your session</p>
     <div class="alert alert-error" ng-class="{'': displayLoginError == true, 'none': displayLoginError == false}">
            <spring:message code="login.error" />
     </div>
     <form method="post" action="j_spring_security_check">
      <div class="form-group has-feedback">
        <input name="j_username" id="j_username" type="text" class="form-control" placeholder="User Name">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input name="j_password" id="j_password" type="password" class="form-control" placeholder="Password">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-8">
         
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
        </div>
        <!-- /.col -->
      </div>
    </form>

    

  </div>
  <!-- /.login-box-body -->    
</div>    
<script src="<c:url value='/resources/js/pages/login.js' />"></script>
</body>