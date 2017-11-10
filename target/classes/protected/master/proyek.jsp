<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Kelola Data Proyek
        </h1>

    </section>

    <!-- Main content -->
    <section class="content ">
        <div class="box"  ng-controller="proyeksController">
            <div class="box-header">
              <h3 class="box-title">List Proyek</h3>

              <div class="box-tools">
                <form name="searchContactForm" novalidate>
                      <div class="input-group input-group-sm" style="width: 150px;">
                          <input class="form-control  pull-right" ng-model="searchFor" name="searchFor" placeholder="Search"/>
                          <div class="input-group-btn">
                              <button type="submit" class="btn btn-default" ng-click="searchContact(searchContactForm, false);">
                                  <i class="fa fa-search"></i>
                              </button>
                          </div>
                      </div>
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
                                <th scope="col">Developer</th>
                                <th scope="col">Nama Proyek</th>
                                <th scope="col">Id Proyek</th>
                                <th scope="col">Lokasi</th>
                                <th scope="col">Luas</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="contact in page.source">
                                <td class="tdContactsCentered">{{contact.developer.nama}}</td>
                                <td class="tdContactsCentered">{{contact.nama_proyek}}</td>
                                <td class="tdContactsCentered">{{contact.id_proyek}}</td>
                                <td class="tdContactsCentered">{{contact.lokasi_proyek}}</td>
                                <td class="tdContactsCentered">{{contact.luas}}</td>
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
                    <jsp:include page="dialogs/proyekDialogs.jsp"/>      
               
                <div style="text-align:center">    
                        <a class="btn btn-app" data-target="#addProyekModal" ng-click="resetContact();" data-toggle="modal">   
                            <i class="fa fa-plus"></i> Tambah 
                        </a>                         
                </div>
                
            </div>
        </div>


    </section>
    <!-- /.content -->
    <script src="<c:url value="/resources/js/pages/master/proyek.js" />"></script>
</div>
<!-- /.content-wrapper -->