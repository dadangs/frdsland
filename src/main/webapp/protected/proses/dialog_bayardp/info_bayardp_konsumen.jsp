<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="lstDPTransaksiModal.jsp"/>  
<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">Input Data Konsumen</h3>
    </div>
    <div class="form-horizontal">
        <div class="box-body">        		
            <div class="form-group">
                <label for="lidkonsumen" class="col-sm-3 control-label">NO
                    KTP</label>
                <div class="col-sm-7">               		 
                    <div class="input-group">
                        <input class="form-control" name="noktp" id="noktp" ng-model="transaksi.noktp" required disabled/>
                        <div class="input-group-btn">
                            <a  class="btn btn-primary" data-target="#lstKonsumenModal" data-toggle="modal" ng-click="getContactList();" >
                                <i class="fa fa-search"></i>
                            </a>
                        </div>                        
                    </div>
                </div>
                <div class="col-md-2 has-error">
                        <label>
                                <span class="help-block"
                                      ng-show="displayValidationError && newTransaksiForm.noktp.$error.required">
                                        <spring:message code="required"/>
                                </span>
                         </label>
                 </div>  
            </div>

            <div class="form-group">
                <label for="lnama" class="col-sm-3 control-label">Nama</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="nama" id="nama" ng-model="transaksi.nama"  disabled >
                </div>
            </div>

            <div class="form-group">
                <label for="lalamat" class="col-sm-3 control-label">Alamat</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="alamat" ng-model="transaksi.alamat"
                           id="alamat" disabled>
                </div>
            </div>


        </div>
    </div>
</div>