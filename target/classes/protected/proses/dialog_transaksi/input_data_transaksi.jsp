<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
    <label for="lnoppjb" class="control-label col-sm-4">No
        PPJB</label>
    <div class="form-group col-sm-6">
        <input type="text" class="form-control col-sm-3"
               name="noppjb"  id="noppjb" ng-model="transaksi.noppjb" required>
    </div>
<div class="col-md-2 has-error">
		<label> <span class="help-block"
			ng-show="displayValidationError && newTransaksiForm.noppjb.$error.required">
				<spring:message code="required" />
		</span>
		</label>
	</div>	
</div>
<div class="row">
    <label for="lnoppjb" class="control-label col-sm-4">Harga
        Kesepakatan</label>
    <div class="form-group col-sm-6">
        <input type="text" class="form-control col-sm-3 simple-field-data-mask-reverse"  data-mask="#,###,###,###" data-mask-reverse="true" data-mask-maxlength="false" style="text-align:right" 
               name="harga_kesepakatan" id="harga_kesepakatan" ng-model="transaksi.harga_kesepakatan" ng-keyup="updateHargaKesepakatanPlusPenambahan();updateHargaTotal()">
    </div>
    
</div>
<div class="row" style="display: none">
    <label for="lnoppjb" class="control-label col-sm-4">Harga
        Kesepakatan+Penambahan</label>
    <div class="form-group col-sm-6">
        <input type="text" class="form-control col-sm-3 simple-field-data-mask-reverse"  data-mask="#,###,###,###" style="text-align:right" 
               name="totalHargaPlusPenambahan" id="totalHargaPlusPenambahan"  ng-model="transaksi.totalHargaPlusPenambahan"  disabled>
    </div>
</div>
 
<div class="row">
    <label for="lnoppjb" class="control-label col-sm-4">Model
        Bayar</label>
    <div class="form-group col-sm-6" ng-init="loadModelBayar()">
        <select name="model_bayar" ng-model="transaksi.model_bayar" class="form-control " style="width: 100%;" ng-change="loadTenor();" required>
			<option value="">Pilih Model Bayar</option>  
            <option ng-repeat="carabayar in carabayars" value="{{carabayar.kode_bayar}}">{{carabayar.keterangan}}</option>                    
        </select>
    </div>
    <div class="col-md-2 has-error">
		<label> <span class="help-block"
			ng-show="displayValidationError && newTransaksiForm.model_bayar.$error.required">
			<spring:message code="required" />
		</span>
		</label>
	</div>
</div>
<div class="row">
    <label for="lnoppjb" class="control-label col-sm-4">Tenor</label>
    <div class="form-group col-sm-6">
        <select name="tenor" ng-model="transaksi.tenor" class="form-control" style="width: 100%;" required>
			<option value="">Pilih Tenor</option>  
            <option ng-repeat="tenor in tenors" value="{{tenor.kode_tenor}}"  >{{tenor.keterangan}}</option>                    
        </select>
    </div>
    <div class="col-md-2 has-error">
		<label> <span class="help-block"
			ng-show="displayValidationError && newTransaksiForm.tenor.$error.required">
			<spring:message code="required" />
		</span>
		</label>
	</div>
</div>

<div class="row">
    <label for="lnoppjb" class="control-label col-sm-4">Cicilan perBulan</label>
    <div class="form-group col-sm-4">
        <input type="text" class="form-control col-sm-3 simple-field-data-mask-reverse"  data-mask="#,###,###,###" data-mask-reverse="true" data-mask-maxlength="false" style="text-align:right" 
               name="cicilan_perbulan" id="cicilan_perbulan" ng-model="transaksi.cicilan_perbulan" required>
    </div>
    <div class="col-md-2 has-error">
		<label> <span class="help-block"
			ng-show="displayValidationError && newTransaksiForm.cicilan_perbulan.$error.required">
			<spring:message code="required" />
		</span>
		</label>
	</div>
</div>
<div class="row">
    <label for="lnoppjb" class="control-label col-sm-4">Tgl Jatuh Tempo</label>
    <div class="form-group col-sm-4">
        <input type="text" class="form-control col-sm-3 simple-field-data-mask-reverse" data-mask="00" style="text-align:right" 
               name="tgl_jatuhtempo" id="tgl_jatuhtempo" ng-model="transaksi.tgl_jatuhtempo" required>
    </div>
    <div class="col-md-2 has-error">
		<label> <span class="help-block"
			ng-show="displayValidationError && newTransaksiForm.tgl_jatuhtempo.$error.required">
			<spring:message code="required" />
		</span>
		</label>
	</div>
</div> 	

  