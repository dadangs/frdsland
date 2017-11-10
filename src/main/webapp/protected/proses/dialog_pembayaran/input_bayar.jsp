<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
    <label for="lnoppjb" class="control-label col-sm-4">Tanggal
        Bayar</label>
    <div class="form-group col-sm-6">
    	<div class="input-group">
    	<div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
        </div>
        <input type="text" class="form-control col-sm-3"
               name="tanggal_bayar"  id="tanggal_bayar" ng-model="transaksi.tanggal_bayar" required>
         </div>
    </div>
	<div class="col-md-2 has-error">
		<label> <span class="help-block"
			ng-show="displayValidationError && newTransaksiForm.tanggal_bayar.$error.required">
				<spring:message code="required" />
		</span>
		</label>
	</div>	
</div>
<div class="row">
    <label for="lnoppjb" class="control-label col-sm-4">Jumlah Bayar</label>
    <div class="form-group col-sm-6">
        <input type="text" class="form-control col-sm-3 simple-field-data-mask-reverse"  data-mask="#,###,###,###" data-mask-reverse="true" data-mask-maxlength="false" style="text-align:right" 
               name="jumlah_bayar" id="jumlah_bayar" ng-model="transaksi.jumlah_bayar" required>
    </div>
    <div class="col-md-2 has-error">
		<label> <span class="help-block"
			ng-show="displayValidationError && newTransaksiForm.jumlah_bayar.$error.required">
				<spring:message code="required" />
		</span>
		</label>
	</div>	
</div>


<script>
  $(function () {    
    $('#tanggal_bayar').datepicker({
      autoclose: true,
      format: 'dd-mm-yyyy'
    });
     
  });
</script>  