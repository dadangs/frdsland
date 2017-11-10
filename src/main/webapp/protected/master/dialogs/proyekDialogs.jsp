<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="modal fade" id="addProyekModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabelUpd" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
        <form  class="form-horizontal" name="newProyekForm" novalidate>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabelUpd">Form Master Proyek</h4>
            </div>
            <div class="modal-body">  
           	 	<div class="form-group" ng-init="loadDeveloper()">
                  <label  class="col-sm-2 control-label">Developer</label>
                  <div class="col-sm-8">
                    <select name="developer" ng-model="contact.id_developer" class="form-control" style="width: 100%;"  required>
							<option value="">Pilih Developer</option>  
				            <option ng-repeat="developer in developers" value="{{developer.id}}" >{{developer.nama}}</option>                    
				        </select>
                  </div>
                  <label class="col-sm-2 has-error">
	                                <span class="help-block"
	                                      ng-show="displayValidationError && newProyekForm.developer.$error.required">
	                                        <spring:message code="required"/>
	                                </span>
	                        </label>
                </div>   
                <div class="form-group">
                  <label  class="col-sm-2 control-label">Proyek</label>
                  <div class="col-sm-8">
                    <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.nama_proyek"
                               name="nama_proyek"/>
                  </div>
                  <label class="col-sm-2 has-error">
	                                <span class="help-block"
	                                      ng-show="displayValidationError && newProyekForm.nama_proyek.$error.required">
	                                        <spring:message code="required"/>
	                                </span>
	                        </label>
                </div>      
                <div class="form-group">
                  <label  class="col-sm-2 control-label">Lokasi</label>
                  <div class="col-sm-8">
                    <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.lokasi_proyek"
                               name="lokasi_proyek"/>
                  </div>
                  <label class="col-sm-2 has-error">
	                                <span class="help-block"
	                                      ng-show="displayValidationError && newProyekForm.lokasi_proyek.$error.required">
	                                        <spring:message code="required"/>
	                                </span>
	                        </label>
                </div>
            	 
                <div class="form-group">
                  <label  class="col-sm-2 control-label">Luas</label>
                  <div class="col-sm-4">
                     <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.luas"
                               name="luas"/>
                  </div>
                </div>
            </div>
            <div class="modal-footer">
              <div class="text-center">
                <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="exit('#addProyekModal');">Close</button>
                <button type="submit" class="btn btn-primary" ng-click="createContact(newProyekForm);">Save changes</button>
              </div> 
            </div>
        </form>                    
    </div>
  </div>
</div>
 
 

<div class="modal fade" id="updateContactsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabelUpd" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <form name="updateContactForm" novalidate>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabelUpd">Update Master Proyek</h4>
            </div>
            <div class="modal-body">
            	<div class="row form-group" >
                    <div class="col-md-2">Developer</div>
                    <div class="col-md-8"><input disabled type="text" class="form-control"
                                                 required                                                 
                                                 name="nama_developer"
                                                 value="{{contact.developer.nama}}"/></div>
                </div>
                <div class="row form-group" style="display:none">
                    <div class="col-md-2">ID Proyek</div>
                    <div class="col-md-8"><input type="text" class="form-control"
                                                 required
                                                 ng-model="contact.id_proyek"
                                                 name="id_proyek"
                                                 value="{{contact.id_proyek}}"/></div>
                </div>
                <div class="row form-group">
                    <div class="col-md-2">Proyek</div>
                    <div class="col-md-8">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.nama_proyek"
                               name="nama_proyek"
                              value="{{contact.nama_proyek}}"/>
                    </div>
                </div>      
                <div class="row form-group">
                    <div class="col-md-2">Lokasi</div>
                    <div class="col-md-8">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.lokasi_proyek"
                               name="lokasi_proyek"
                              value="{{contact.lokasi_proyek}}"/>
                    </div>
                </div>    
                <div class="row form-group">
                    <div class="col-md-2">Luas</div>
                    <div class="col-md-4">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.luas"
                               name="lokasi"
                              value="{{contact.luas}}"/>
                    </div>
                </div>
                <div class="row  form-group">     
                    <span class="alert alert-error dialogErrorMessage"
                          ng-show="errorOnSubmit">
                        <spring:message code="request.error"/>
                    </span>  
                </div>    
            </div>
            <div class="modal-footer">
            	<div class="text-center">
                <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="exit('#updateContactsModal');">Close</button>
                <button type="submit" class="btn btn-primary" ng-click="updateContact(updateContactForm);">Save changes</button>
                </div>
            </div>
        </form>                    
    </div>
  </div>
</div> 



    
<div class="modal fade" id="delContactsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Delete</h4>
      </div>
      <div class="modal-body">
        <div class="row  form-group">     
        <form name="deleteContactForm" novalidate>
            <p><spring:message code="delete.confirm"/>:&nbsp;Nama Proyek = {{contact.nama_proyek}} ?</p>

            <input type="submit"
                   class="btn btn-inverse"
                   ng-click="deleteContact();"
                   value='<spring:message code="delete"/>'/>
            <button class="btn btn-inverse"
                    data-dismiss="modal"
                    ng-click="exit('#delContactsModal');"
                    aria-hidden="true">
                <spring:message code="cancel"/>
            </button>
        </form>
        </div>    
        <div class="row  form-group">      
            <span class="alert alert-error dialogErrorMessage" ng-show="errorOnSubmit">
                <spring:message code="request.error"/>
            </span>
            <span class="alert alert-error dialogErrorMessage"      ng-show="errorIllegalAccess">
                <spring:message code="request.illegal.access"/>
            </span>    
        </div>
      </div>
      
    </div>
  </div>
</div>