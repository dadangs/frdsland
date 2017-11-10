<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="modal fade" id="addBillerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabelUpd" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <form name="newBillerForm" novalidate>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabelUpd">Update Akses Menu</h4>
            </div>
            <div class="modal-body">               
                <div class="row form-group">
                    <div class="col-md-2">ID</div>
                    <div class="col-md-2"><input type="text" class="form-control"
                                                 required
                                                 ng-model="contact.csc_bi_id"
                                                 name="id"/></div>
                    <div class="has-error">
                        <label>
                                <span class="help-block"
                                      ng-show="displayValidationError && newBillerForm.id.$error.required">
                                        <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-2">Name</div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.csc_bi_name"
                               name="name"/>
                    </div>
                </div>      
                <div class="row form-group">
                    <div class="col-md-2">Address</div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.csc_bi_address"
                               name="name"/>
                    </div>
                </div>          
                <div class="row form-group">
                    <div class="col-md-2">Phone</div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.csc_bi_phone"
                               name="name"/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-2">PIC Name</div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.csc_bi_pic_name"
                               name="name"/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-2">PIC Phone</div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.csc_bi_pic_phone"
                               name="name"/>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-2">PAN</div>
                    <div class="col-md-2">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.csc_bi_pan"
                               name="name"/>
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
                <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="exit('#updateContactsModal');">Close</button>
                <button type="submit" class="btn btn-primary" ng-click="createContact(newBillerForm);">Save changes</button>
            </div>
        </form>                    
    </div>
  </div>
                      
</div>
 