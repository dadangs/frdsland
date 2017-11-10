<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Rekap Piutang           
        </h1>
    </section>

    <!-- Main content -->
    <section class="content ">
        <div class="box"  ng-controller="rekap_piutangsController">
            <div class="box-header">
                List Piutang Perumahan
            </div>            
            <div class="box-body">
                <div id="loadingModal" class="modal hide fade in centering"
                     role="dialog"
                     aria-labelledby="deleteContactsModalLabel" aria-hidden="true">
                    <div id="divLoadingIcon" class="text-center">
                        <div class="icon-align-center loading"></div>
                    </div>
                </div>
                <div ng-class="{'alert alert-block alert-error': state == 'error', 'none': state != 'error'}">
                    <h4><i class="icon-info-sign"></i> <spring:message code="error.generic.header"/></h4><br/>

                    <p><spring:message code="error.generic.text"/></p>
                </div>
                <div ng-class="{'alert alert-info': state == 'noresult', 'none': state != 'noresult'}">
                    <h4><i class="icon-info-sign"></i> <spring:message code="contacts.emptyData"/></h4><br/>

                    <p><spring:message code="contacts.emptyData.text"/></p>
                </div>
                <div id="gridContainer" ng-class="{'': state == 'list', 'none': state != 'list'}">  
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                            	<th scope="col">ID</th>
                                <th scope="col">Nama</th>
                                <th scope="col">Total Harga</th>
                                <th scope="col">Pembayaran Angsuran</th>
                                <th scope="col">Pembayaran DP</th>                    
                                <th scope="col">Sisa Piutang</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="contact in page.source">
                            	<td class="tdContactsCentered">{{contact.pembayaran.transaksi.id_produk}}</td>
                                <td class="tdContactsCentered">{{contact.pembayaran.transaksi.nama_rumah}}</td>
                                <td class="text-right">{{contact.pembayaran.transaksi.harga_kesepakatan | number:0}}</td>
                                <td class="text-right">{{contact.pembayaran.jumlah_bayar | number:0}}</td>
                                <td class="text-right">{{contact.pembayaran_dp.jumlah_bayar | number:0}}</td>
                                <td class="text-right">{{contact.pembayaran.transaksi.harga_kesepakatan - contact.pembayaran.jumlah_bayar - contact.pembayaran_dp.jumlah_bayar | number:0}}</td>
                                 
                            </tr>
                        </tbody>
                    </table>
                    
                    <div class="box-footer clearfix">         
                        <ul class="pagination pagination-sm no-margin pull-right">
                            <li><a href="#" class="btn btn-inverse" ng-class="{'btn-inverse': page.currentPage != 0, 'disabled': page.currentPage == 0}"
                                   ng-disabled="page.currentPage == 0" ng-click="changePage(0)" title='<spring:message code="pagination.first"/>'>«</a></li>
                            <li><a href="#" class="btn btn-inverse"
                                   ng-class="{'btn-inverse': page.currentPage != 0, 'disabled': page.currentPage == 0}"
                                   ng-disabled="page.currentPage == 0" class="btn btn-inverse"
                                   ng-click="changePage(page.currentPage - 1)" title='<spring:message code="pagination.back"/>'>&lt</a></li>
                            <li><span>{{page.currentPage + 1}} <spring:message code="pagination.of"/> {{page.pagesCount}}</span></li>
                            <li><a href="#" class="btn btn-inverse"
                                   ng-class="{'btn-inverse': page.pagesCount - 1 != page.currentPage, 'disabled': page.pagesCount - 1 == page.currentPage}"
                                   ng-click="changePage(page.currentPage + 1)"
                                   ng-disabled="page.pagesCount - 1 == page.currentPage" title='<spring:message code="pagination.next"/>'>&gt;</a></li>
                            <li><a href="#" class="btn btn-inverse" ng-class="{'btn-inverse': page.pagesCount - 1 != page.currentPage, 'disabled': page.pagesCount - 1 == page.currentPage}"
                                   ng-disabled="page.pagesCount - 1 == page.currentPage"
                                   ng-click="changePage(page.pagesCount - 1)" title='<spring:message code="pagination.last"/>'>»</a></li>
                        </ul>
                    </div>   
					</div> 

                </div>
                
                
         
        </div>


    </section>
    <!-- /.content -->
    <script src="<c:url value="/resources/js/pages/laporan/rekap_piutang.js" />"></script>
</div>
<!-- /.content-wrapper -->