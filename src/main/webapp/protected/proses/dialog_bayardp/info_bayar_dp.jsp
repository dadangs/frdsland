<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
         

<div class="row">
    <label for="lnoppjb" class="control-label col-sm-4">Model
        Bayar</label>
    <div class="form-group col-sm-6" ng-init="loadModelBayar()">
        <select name="model_bayar" ng-model="transaksi.model_bayar" class="form-control " style="width: 100%;" ng-change="loadTenor();" required disabled>
			<option value="">Pilih Model Bayar</option>  
            <option ng-repeat="carabayar in carabayars" value="{{carabayar.kode_bayar}}"  ng-selected="{{ carabayar.kode_bayar ==  transaksi.model_bayar}}">{{carabayar.keterangan}}</option>                    
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
    <div class="form-group col-sm-6" >
        <select name="tenor" ng-model="transaksi.tenor" class="form-control" style="width: 100%;" required disabled>
			<option value="">Pilih Tenor</option>  
            <option ng-repeat="tenor in tenors" value="{{tenor.kode_tenor}}" ng-selected="{{ tenor.kode_tenor==  transaksi.tenor}}" >{{tenor.keterangan}}</option>                    
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
