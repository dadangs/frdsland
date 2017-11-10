<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="pt-BR" id="ng-app" ng-app="">
<head>
    <title><spring:message  code="project.title" /></title>
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="<c:url value='/resources/lte/bootstrap/css/bootstrap.min.css'/> ">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value='/resources/lte/dist/css/AdminLTE.min.css'/> ">
    <!-- iCheck -->
    <link rel="stylesheet" href="<c:url value='/resources/lte/plugins/iCheck/square/blue.css'/> ">
  <!-- bootstrap datepicker-->     
  <link rel="stylesheet" href="<c:url value='/resources/lte/plugins/daterangepicker/daterangepicker.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources/lte/plugins/datepicker/datepicker3.css'/>">

    <link href="<c:url value='/resources/css/cssku.css'  />" rel="stylesheet"/>
     <!-- jQuery 2.2.3 -->
        <script src="<c:url value='/resources/lte/plugins/jQuery/jquery-2.2.3.min.js'/>"></script>
        <script src="<c:url value='/resources/lte/angular.1.2.26/angular.js'/>"></script> 
        <!-- Bootstrap 3.3.6 -->
        <script src="<c:url value='/resources/lte/bootstrap/js/bootstrap.min.js'/>"></script>
          <!-- iCheck -->
        <script src="<c:url value='/resources/lte/plugins/iCheck/icheck.min.js'/>"></script>
      <script src="<c:url value='/resources/lte/dist/js/app.min.js'/>"></script>

<script type='text/javascript'>
function MyCtrl($scope) { 
    $scope.foo = "I'm foo!";
    $scope.lines = [];
    $scope.datas = [];
	
    $scope.addLine = function () {
		//alert($scope.lines.length);
		var jmrow = document.getElementById("myTable").rows.length;
		var jmcol = document.getElementById('myTable').rows[0].cells.length;
		/*for (i = 0; i < jmrow; i++) { 
		  $scope.datas.push({
            name: document.getElementById("myTable").rows[i].cells[0].innerHTML,
            desc: document.getElementById("myTable").rows[i].cells[1].innerHTML,
			val: document.getElementById("myTable").rows[i].cells[2].children[0].value,
			
			});
			
		}*/
		// alert(document.getElementById("myTable").rows[0].cells[2].getElementsByTagName("input")[0].value );
		 $('#myTable tr .chk1').each(function() {
			if (this.checked) {
				alert("a");
			}
		});
         $scope.lines.push($scope.lines.length);
		
    };
}
</script>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <tiles:insertAttribute name="header" />
            <tiles:insertAttribute name="sidebar" />            
            <tiles:insertAttribute name="body" />
        <tiles:insertAttribute name="footer" />
        </div>

    </body>
</html>