<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="modal fade" id="addKonsumenModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabelUpd" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <form name="newKonsumenForm" novalidate>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabelUpd">Form Master Konsumen</h4>
            </div>
            <div class="modal-body">        
            	<fieldset>
    			<legend><h5><b>Sendiri</b></h5></legend>       
                <div class="row form-group">
                    <div class="col-md-2">NoKTP</div>
                    <div class="col-md-7"><input type="text" class="form-control"
                                                 required
                                                 ng-model="contact.noktp"
                                                 name="noktp"/></div>
                    <div class="has-error">
                        <label>
                                <span class="help-block"
                                      ng-show="displayValidationError && newKonsumenForm.noktp.$error.required">
                                        <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-2">Nama</div>
                    <div class="col-md-8">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.nama"
                               name="nama"/>
                    </div>
                    <div class="has-error">
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
                    <div class="has-error">
                        <label>
                                <span class="help-block"
                                      ng-show="displayValidationError && newKonsumenForm.alamat.$error.required">
                                        <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>          
                <!-- <div class="row form-group">
                    <div class="col-md-2">NoAKAD</div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.noakad"
                               name="noakad"/>
                    </div>
                </div> -->
                 <div class="row form-group">
                    <div class="col-md-2">NoHP</div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.nohp"
                               name="nohp"/>
                    </div>
                    <div class="has-error">
                        <label>
                                <span class="help-block"
                                      ng-show="displayValidationError && newKonsumenForm.nohp.$error.required">
                                        <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                </fieldset>
                <p>
                <fieldset>
    			<legend><h5><b>Pasangan/Wali</b></h5></legend>      
                <div class="row form-group">
                    <div class="col-md-2">NoKTP </div>
                    <div class="col-md-7"><input type="text" class="form-control"
                                                 required
                                                 ng-model="contact.noktp_pasangan"
                                                 name="noktp_pasangan"/></div>                    
                </div>
                <div class="row form-group">
                    <div class="col-md-2">Nama </div>
                    <div class="col-md-8">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.nama_pasangan"
                               name="nama_pasangan"/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-2">Alamat</div>
                    <div class="col-md-8">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.alamat_pasangan"
                               name="alamat"/>
                    </div>
                    <div class="has-error">
                        <label>
                                <span class="help-block"
                                      ng-show="displayValidationError && newKonsumenForm.alamat.$error.required">
                                        <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>  
                <div class="row form-group">
                    <div class="col-md-2">NoHP </div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.nohp_pasangan"
                               name="nohp_pasangan"/>
                    </div>
                </div>  
                </fieldset>                  
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="exit('#updateContactsModal');">Close</button>
                <button type="submit" class="btn btn-primary" ng-click="createContact(newKonsumenForm);">Save changes</button>
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
                <h4 class="modal-title" id="myModalLabelUpd">Update Master Konsumen</h4>
            </div>
            <div class="modal-body">
            	<fieldset>
    			<legend><h5><b>Sendiri</b></h5></legend>     
                <div class="row form-group">
                    <div class="col-md-2">NoKTP</div>
                    <div class="col-md-7"><input type="text" class="form-control"
                                                 required
                                                 ng-model="contact.noktp"
                                                 name="noktp"
                                                 value="{{contact.noktp}}"/></div>
                </div>
                <div class="row form-group">
                    <div class="col-md-2">Nama</div>
                    <div class="col-md-8">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.nama"
                               name="nama"
                              value="{{contact.nama}}"/>
                    </div>
                </div>      
                <div class="row form-group">
                    <div class="col-md-2">Alamat</div>
                    <div class="col-md-8">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.alamat"
                               name="alamat"
                              value="{{contact.alamat}}"/>
                    </div>
                </div>    
                <!-- <div class="row form-group">
                    <div class="col-md-2">NoAKAD</div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.noakad"
                               name="noakad"
                              value="{{contact.noakad}}"/>
                    </div>
                </div>-->
                <div class="row form-group">
                    <div class="col-md-2">NoHP</div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.nohp"
                               name="nohp"
                              value="{{contact.nohp}}"/>
                    </div>
                </div>
                </fieldset>
                <fieldset>
    			<legend><h5><b>Pasangan/Wali</b></h5></legend> 
                <div class="row form-group">
                    <div class="col-md-2">NoKTP</div>
                    <div class="col-md-7">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.noktp_pasangan"
                               name="noktp_pasangan"
                              value="{{contact.noktp_pasangan}}"/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-2">Nama </div>
                    <div class="col-md-8">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.nama_pasangan"
                               name="nama_pasangan"
                              value="{{contact.nama_pasangan}}"/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-2">Alamat</div>
                    <div class="col-md-8">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.alamat_pasangan"
                               name="alamat_pasangan"
                              value="{{contact.alamat_pasangan}}"/>
                    </div>
                </div>   
                <div class="row form-group">
                    <div class="col-md-2">NoHP </div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.nohp_pasangan"
                               name="nohp_pasangan"
                              value="{{contact.nohp_pasangan}}"/>
                    </div>
                </div>
                </fieldset>
                <div class="row  form-group">     
                    <span class="alert alert-error dialogErrorMessage"
                          ng-show="errorOnSubmit">
                        <spring:message code="request.error"/>
                    </span>  
                </div>    
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="exit('#updateContactsModal');">Close</button>
                <button type="submit" class="btn btn-primary" ng-click="updateContact(updateContactForm);">Save changes</button>
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