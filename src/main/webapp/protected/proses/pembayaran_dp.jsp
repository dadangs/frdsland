<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="content-wrapper" >
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>Pembayaran Down Payment</h1>
     </section> 
    <section class="content content-header " ng-controller="pembayaransController" >

      <div class="row" >
        <div class="col-xs-12">
        
          <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#fa-icons" data-toggle="tab">Entri</a></li>
              <li><a href="#glyphicons" data-toggle="tab">Report</a></li>
            </ul>
            <div class="tab-content">
               
              <div class="tab-pane active" id="fa-icons" >
                <jsp:include page="E_pembayaranDP.jsp" />
              </div>
              <!-- /#fa-icons -->

              <!-- glyphicons-->
              <div class="tab-pane" id="glyphicons">
				<jsp:include page="R_pembayaranDP.jsp" />
              </div>
              <!-- /#ion-icons -->

            </div>
            <!-- /.tab-content -->
          </div>
          <!-- /.nav-tabs-custom -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
</div>    