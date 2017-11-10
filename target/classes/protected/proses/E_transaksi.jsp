<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Content Wrapper. Contains page content -->
<style>

</style>		
<div class="content-wrapper2">
    <!-- Content Header (Page header) -->
    <section class="content content-header" ng-controller="transaksisController">
        
         <form name="newTransaksiForm" novalidate>
        <div class="row">
            <div class="col-md-6">                         
                <jsp:include page="dialog_transaksi/input_data_konsumen.jsp"/>                    
            </div>
            <div class="col-md-6">
                <jsp:include page="dialog_transaksi/input_data_perumahan.jsp"/>                     
            </div>
            <div class="col-md-12">
            	
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">Input Kesepakatan Transaksi</h3>
                        
                    </div>
                    <div class="form-horizontal">
                        <div class="box-body">
                            <div class="row">        
                                                
                                <div class="col-md-6">
									<fieldset>    	
										<legend>Angsuran</legend>					
										<div class="panel panel-default">
											<div class="panel-body">
												<jsp:include page="dialog_transaksi/input_data_transaksi.jsp"/>
											</div>
										</div>				
									</fieldset>				
									<!-- 
									<div class="clearfix"></div>                                                                        
                                   <jsp:include page="dialog_transaksi/input_data_transaksi.jsp"/>
                                    -->  
                                </div>
                                <div class="col-md-6">
                                	<fieldset>    	
										<legend>DP</legend>					
										<div class="panel panel-default">
											<div class="panel-body">
												<jsp:include page="dialog_transaksi/input_data_modelbayar.jsp"/>
											</div>
										</div>				
									</fieldset>
									<div  align="right">
									    <div><p></div>										
									    <label for="lnoppjb" class="control-label col-md-6" >Sisa</label>
									    <div class="form-group col-md-6">
									        <input type="text" class="form-control simple-field-data-mask-reverse"  data-mask="#,###,###,###" style="text-align:right" 
									               name="harga_total" id="harga_total" ng-model="transaksi.harga_total" disabled>
									    </div>
									</div>   	 									  									
                                </div>
                            </div>
                        </div>
                    </div>
					<div class="box-footer">
		                <button type="submit" class="btn btn-primary pull-right" ng-click="createTransaksi(newTransaksiForm);">Simpan Transaksi</button>
		        	</div>
                </div>                
            </div> 
        </div>
        
	        <!-- <div ng-show = "IsVisible">My DIV</div>
	        <div ng-show = "IsVisiblePerumahan">My DIV</div> -->
         
                       
       
        </form>
    </section>
 </div>
    <script src="<c:url value="/resources/js/pages/proses/transaksi.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.mask.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.price_format.min.js" />"></script>
      <a href="/firdausydev/protected/proses/transaksis/jrreport">Download PDF</a>
