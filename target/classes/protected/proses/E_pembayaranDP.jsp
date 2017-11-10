<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper2">
    <!-- Content Header (Page header) -->
    <section class="content content-header" ng-controller="pembayaransController">
         <form name="newTransaksiForm" novalidate>
        <div class="row">
            <div class="col-md-6">                       
                <jsp:include page="dialog_bayardp/info_bayardp_konsumen.jsp"/>                    
            </div>
            <div class="col-md-6">
                <jsp:include page="dialog_bayardp/info_bayardp_perumahan.jsp"/>                     
            </div>
        </div>
        
	        <!-- <div ng-show = "IsVisible">My DIV</div>
	        <div ng-show = "IsVisiblePerumahan">My DIV</div> -->
        <div class="row">
            <div class="col-md-12">
            	
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Input Data Bayar</h3>
                        
                    </div>
                    <div class="form-horizontal">
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-6">                                
                                	<jsp:include page="dialog_bayardp/info_bayar_dp.jsp"/>  
                                </div>
                                <div class="col-md-6">
                                   <jsp:include page="dialog_bayardp/input_bayardp.jsp"/>  
                                   
                                </div>
                            </div>
                        </div>
                    </div>
					<div class="box-footer">
		                <button type="submit" class="btn btn-primary pull-right" ng-click="createTransaksi(newTransaksiForm);">Simpan Bayar</button>
		        	</div>
                </div>                
            </div>            
        </div> 		
        </form>
    </section>
    <script src="<c:url value="/resources/js/pages/proses/pembayaranDP.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.mask.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.price_format.min.js" />"></script>
      <a href="/firdausydev/protected/proses/transaksis/jrreport">Download PDF</a>
</div>
<!-- /.content-wrapper -->