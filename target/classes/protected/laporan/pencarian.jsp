 <%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <div class="content-wrapper" ng-controller="pencariansController">   
<section class="content-header" >
        <h1>Pencarian</h1>        
</section>
<section class="content">
	
<div class="error-page" >
 		 
        <div class="error-content">
         	<div ng-class="{'alert': state == 'noresult', 'none': state != 'noresult'}">
	          <h3><i class="fa fa-warning text-red"></i> Oops! Something went wrong. Data Not Found</h3>
			</div>
	          <p>
	            Masukkan nomor akad / PPJB
	          </p>
		 
          <form class="search-form" name="searchContactForm" novalidate>
            <div class="input-group">
              <input type="text"  ng-model="searchFor"  name="search" class="form-control" placeholder="Search">

              <div class="input-group-btn">
                <button type="submit" name="submit" class="btn btn-danger btn-flat" ng-click="searchContact(searchContactForm, false);">
                	<i class="fa fa-search"></i>
                </button>
              </div>
            </div>
            <!-- /.input-group -->
          </form>          
        </div>
</div>
<div class="box-body">     
                <div id="loadingModal" class="modal hide fade in centering"
                     role="dialog"
                     aria-labelledby="deleteContactsModalLabel" aria-hidden="true">
                    <div id="divLoadingIcon" class="text-center">
                        <div class="icon-align-center loading"></div>
                    </div>
                </div>        
<div id="gridContainer" ng-class="{'': state == 'list', 'none': state != 'list'}">        
<div class="table-responsive">
     				 <table class="table table-striped table-bordered" style="width: 1800px;">
                        <thead>
                            <tr>
                                <th scope="col" style="width:10%">AngsuranKe</th>
                                <th scope="col" style="width:10%">NoKTP</th>
                                <th scope="col" style="width:10%">Nama</th>
                                <th scope="col" style="width:10%">Alamat</th>
                                <th scope="col" style="width:10%">Produk</th>
                                <th scope="col" style="width:10%">Blok</th>
                                <th scope="col" style="width:10%">NoUnit</th>
                                <th scope="col" style="width:10%">Id Produk</th>
                                <th scope="col" style="width:2%">TanggalBayar</th>
                                <th scope="col" style="width:2%">JumlahBayar</th>
                                <th scope="col" style="width:2%">Sisa</th>                    
                                
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="contact in page.source">
                                <td class="tdContactsCentered">{{contact.angsuranke}}</td>
                                <td class="tdContactsCentered">{{contact.noktp}}</td>
                                <td class="tdContactsCentered">{{contact.transaksi.nama}}</td>
                                <td class="tdContactsCentered">{{contact.transaksi.alamat}}</td>
                                <td class="tdContactsCentered">{{contact.transaksi.nama_rumah}}</td>
                                <td class="tdContactsCentered">{{contact.transaksi.blok}}</td>
                                <td class="tdContactsCentered">{{contact.transaksi.nounit}}</td>
                                <td class="tdContactsCentered">{{contact.id_produk}}</td>
                                <td class="tdContactsCentered">{{contact.tanggal_bayar}}</td>
                                <td class="text-right">{{contact.jumlah_bayar | number:0}}</td>
                                <td class="text-right">{{contact.sisa | number:0}}</td>
                                
                            </tr>
                        </tbody>
                    </table>
                    </div>
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
</section>
 <script src="<c:url value="/resources/js/pages/laporan/pencarian.js" />"></script>
 </div>