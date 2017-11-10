<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="modal fade" id="addKonsumenModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabelUpd" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
   
        <form name="newKonsumenForm" novalidate>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabelUpd">Form Master Developer</h4>
            </div>
            <div class="modal-body">        
                <div class="row form-group">
                    <div class="col-md-2">Nama</div>
                    <div class="col-md-8">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.nama"
                               name="nama"/>                             
                    </div>
                    <div class="col-md-2 has-error">
                        <label>
                                <span class="help-block"
                                      ng-show="displayValidationError && newKonsumenForm.nama.$error.required">
                                        <spring:message code="required"/>
                                </span>
                         </label>
                   		</div>  
                </div>      
                <div class="row form-group">
                    <div class="col-md-2">Alamat</div>
                    <div class="col-md-8">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.alamat"
                               name="alamat"/>                                    
                    </div>
                    <div class="col-md-2 has-error">
		                        <label>
		                                <span class="help-block"
		                                      ng-show="displayValidationError && newKonsumenForm.nama.$error.required">
		                                        <spring:message code="required"/>
		                                </span>
		                         </label>
		            </div>   
                </div>          
                <div class="row form-group">
                    <div class="col-md-2">NPWP</div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus                                
                               ng-model="contact.npwp"
                               name="npwp"/>
                    </div>
                </div>
                 <div class="row form-group">
                    <div class="col-md-2">NoTelp</div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.notelp"
                               name="notelp"/>                                   
                    </div>
                    <div class="col-md-2 has-error">
                        <label>
                                <span class="help-block"
                                      ng-show="displayValidationError && newKonsumenForm.nama.$error.required">
                                        <spring:message code="required"/>
                                </span>
                         </label>
                   		</div>   
                </div>           
            </div>
            <div class="modal-footer">
            	<div class="text-center">
                <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="exit('#addKonsumenModal');">Close</button>
                <button type="submit" class="btn btn-primary" ng-click="createContact(newKonsumenForm);">Save changes</button>
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
                <h4 class="modal-title" id="myModalLabelUpd">Update User Login</h4>
            </div>
            <div class="modal-body">                
                <div class="row form-group">
                    <div class="col-md-2">Nama</div>
                    <div class="col-md-8">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.name"
                               name="nama"
                              value="{{contact.name}}"/>
                    </div>
                </div>      
                <div class="row form-group">
                    <div class="col-md-2">Password</div>
                    <div class="col-md-8">
                        <input type="password" class="form-control"
                               autofocus
                               required
                               ng-model="contact.password"
                               name="password"
                              value="{{contact.password}}"/>
                    </div>
                </div>    
                <div class="row form-group">
                    <div class="col-md-2">Role</div>
                    <div class="form-group col-sm-6" ng-init="getRoleList()">
							<select name="role" ng-model="contact.role_id"
								class="form-control" style="width: 100%;" required>
								<option value="">Pilih Role</option>
								<option ng-repeat="role in roles"
									value="{{role.role_id}}">{{role.role_description}}</option>
							</select>							
						</div>
                </div>
                <div class="row form-group">
                    <div class="col-md-2">NoTelp</div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.role_id"
                               name="nohp"
                              value="{{contact.role_id}}"/>
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
            <p><spring:message code="delete.confirm"/>:&nbsp;Nama  = {{contact.nama}} ?</p>

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