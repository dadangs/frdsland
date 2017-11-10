<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

 
<div class="box">
    <div class="box-header with-border">
        <h3 class="box-title">Info Produk Perumahan</h3>
    </div>
    <div class="form-horizontal">
        <div class="box-body">
	 		<!-- <input type="button" value="Show Hide DIV" ng-click="ShowHide()" /> -->
	        
        
            <div class="form-group">
                <label for="lidkonsumen" class="col-sm-3 control-label">ID
                    Produk</label>
                <div class="col-sm-7">
                    <div class="input-group">
                        <input class="form-control" name="id_produk" id="id_produk" ng-model="transaksi.id_produk" required disabled/>
                        <div class="input-group-btn">
                             <!-- <a  class="btn btn-default"  data-target="#lstPerumahanModal" data-toggle="modal" 
                                    ng-click="getPerumahanList();">
                                <i class="fa fa-search"></i>
                            </a> 
                            <a  class=" btn btn-primary"   data-target="#lstPerumahanModal" data-toggle="modal" 
                                    ng-click="getPerumahanList1();">
                                <i class="fa fa-search"></i>
                            </a> -->
                             <!-- <input type="button" value="Show Hide DIV" ng-click="getPerumahanList1();" /> -->
                        </div>
                    </div>
                </div>
                <div class="col-md-2 has-error">
                        <label>
                                <span class="help-block"
                                      ng-show="displayValidationError && newTransaksiForm.id_produk.$error.required">
                                        <spring:message code="required"/>
                                </span>
                         </label>
                 </div>  
            </div>

            <div class="form-group">
                <label for="lnama_rumah" class="col-sm-3 control-label">Nama
                </label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" ng-model="transaksi.nama_rumah" name="nama_rumah"
                           id="nama_rumah" disabled>
                </div>
            </div>

            <div class="form-group">
                <label for="ltipe" class="col-sm-3 control-label">Kategori/Tipe/Luas</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="kategori" id="kategori" ng-model="transaksi.kategori" disabled>
                </div>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="tipe_rumah" id="tipe_rumah" ng-model="transaksi.tipe_rumah" disabled>
                </div>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="luas_tanah" id="luas_tanah" ng-model="transaksi.luas_tanah" disabled>
                </div>
            </div>
			<div class="form-group">
                <label for="ltipe" class="col-sm-3 control-label">Blok/No.Unit</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="blok" id="blok" ng-model="transaksi.blok" disabled>
                </div>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="nounit" id="nounit" ng-model="transaksi.nounit" disabled>
                </div>
            </div>

        </div>
    </div>
</div>