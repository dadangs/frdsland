<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
         
<div class="row">
    <label for="lnoppjb" class="control-label col-sm-4">Down
        Payment</label>
    <div class="form-group col-sm-6">
        <input type="text" class="form-control col-sm-3 simple-field-data-mask-reverse"  data-mask="#,###,###,###" data-mask-reverse="true" data-mask-maxlength="false" style="text-align:right" 
               name="dp" id="dp" ng-model="transaksi.dp"   ng-keyup="updateTotalMinDP();"  >
    </div>
</div>
<div class="row">
    <label for="lnoppjb" class="control-label col-sm-4">Model
        Bayar</label>
    <div class="form-group col-sm-6" ng-init="loadModelBayarDP()">
        <select name="model_bayardp" ng-model="transaksi.model_bayardp" class="form-control " style="width: 100%;" ng-change="loadTenorDP();" required>
			<option value="">Pilih Model Bayar</option>  
            <option ng-repeat="carabayar in carabayarsdp" value="{{carabayar.kode_bayar}}">{{carabayar.keterangan}}</option>                    
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
        <select name="tenordp" ng-model="transaksi.tenordp" class="form-control" style="width: 100%;" required>
			<option value="">Pilih Tenor</option>  
            <option ng-repeat="tenor in tenorsdp" value="{{tenor.kode_tenor}}"  >{{tenor.keterangan}}</option>                    
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
               name="cicilan_perbulandp" id="cicilan_perbulandp" ng-model="transaksi.cicilan_perbulandp" required>
    </div>
    <div class="col-md-2 has-error">
		<label> <span class="help-block"
			ng-show="displayValidationError && newTransaksiForm.cicilan_perbulan.$error.required">
			<spring:message code="required" />
		</span>
		</label>
	</div>
</div>
<!-- <div class="row">
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
<div class="row">
    <label for="lnoppjb" class="control-label col-sm-6">Harga Appraisal </label>
    <div class="form-group col-sm-6">
         <input type="text" class="form-control col-sm-3 simple-field-data-mask-reverse"  data-mask="#,###,###,###" data-mask-reverse="true" data-mask-maxlength="false"  style="text-align:right"
               name="harga_appraisal" id="harga_appraisal" ng-model="transaksi.harga_appraisal">
    </div>
</div> --> 



