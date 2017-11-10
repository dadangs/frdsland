<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

                    <div class="table-responsive">
     				 <table class="table table-striped table-bordered" style="width: 1800px;">
                        <thead>
                            <tr>
                                <th scope="col" style="width:10%">AngsuranKe</th>
                                <th scope="col" style="width:10%">NoKTP</th>
                                <th scope="col" style="width:10%">Nama</th>
                                <th scope="col" style="width:10%">Alamat</th>
                                <th scope="col" style="width:10%">Produk</th>                                
                                <th scope="col" style="width:10%">Id Produk</th>
                                <th scope="col" style="width:10%">Blok</th>
                                <th scope="col" style="width:10%">NoUnit</th>                                
                                <th scope="col" style="width:2%">TanggalBayar</th>
                                <th scope="col" style="width:2%">JumlahBayar</th>
                                <th scope="col" style="width:2%">Sisa</th>                    
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="contact in page.source">
                                <td class="tdContactsCentered">{{contact.angsuranke}}</td>
                                <td class="tdContactsCentered">{{contact.noktp}}</td>
                                <td class="tdContactsCentered">{{contact.transaksi.nama}}</td>
                                <td class="tdContactsCentered">{{contact.transaksi.alamat}}</td>
                                <td class="tdContactsCentered">{{contact.transaksi.nama_rumah}}</td>
                                <td class="tdContactsCentered">{{contact.id_produk}}</td>                                
                                <td class="tdContactsCentered">{{contact.transaksi.blok}}</td>
                                <td class="tdContactsCentered">{{contact.transaksi.nounit}}</td>                                
                                <td class="tdContactsCentered">{{contact.tanggal_bayar}}</td>
                                <td class="text-right">{{contact.jumlah_bayar | number:0}}</td>
                                <td class="text-right">{{contact.sisa | number:0}}</td>
                                <td class="width15">
                                    <div class="text-center">   
                                            <a data-target="#updateContactsModal"
                                               ng-click="selectedContact(contact);"
                                               role="button"
                                               title="edit" 
                                               data-toggle="modal"> 
                                                <i class="fa fa-edit"></i> 
                                            </a>
                                            <a data-target="#delContactsModal"
                                               ng-click="selectedContact(contact);"
                                               role="button"
                                               title="delete"
                                               data-toggle="modal"> 
                                                <i class="fa fa-close"></i>
                                            </a> 
                                    </div>
                                </td>
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
 	 