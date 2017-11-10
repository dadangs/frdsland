<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="addContactsModal"
     class="modal hide fade in centering insertAndUpdateDialogs"
     role="dialog"
     aria-labelledby="addContactsModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="addContactsModalLabel" class="displayInLine">
            <spring:message code="create"/>&nbsp;<spring:message code="contact"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="newContactForm" novalidate >
            <div class="pull-left">
                <div>
                    <div class="input-append">
                        <label>* <spring:message code="contacts.name"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               autofocus
                               ng-model="contact.name"
                               name="name"
                               placeholder="<spring:message code='contact'/>&nbsp;<spring:message code='contacts.name'/>"/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && newContactForm.name.$error.required">
                                        <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <label>* <spring:message code="contacts.email"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               ng-model="contact.email"
                               name="email"
                               placeholder="<spring:message code='sample.email'/> "/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && newContactForm.email.$error.required">
                                    <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <div>
                    <div class="input-append">
                        <label>* <spring:message code="contacts.phone"/>:</label>
                    </div>
                    <div class="input-append">
                        <input type="text"
                               required
                               ng-model="contact.phoneNumber"
                               name="phoneNumber"
                               placeholder="<spring:message code='sample.phone'/> "/>
                    </div>
                    <div class="input-append">
                        <label>
                                <span class="alert alert-error"
                                      ng-show="displayValidationError && newContactForm.phoneNumber.$error.required">
                                    <spring:message code="required"/>
                                </span>
                        </label>
                    </div>
                </div>
                <input type="submit"
                       class="btn btn-inverse"
                       ng-click="createContact(newContactForm);"
                       value='<spring:message code="create"/>'/>
                <button class="btn btn-inverse"
                        data-dismiss="modal"
                        ng-click="exit('#addContactsModal');"
                        aria-hidden="true">
                    <spring:message code="cancel"/>
                </button>
            </div>
        </form>
    </div>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit">
        <spring:message code="request.error"/>
    </span>
</div>


<div id="searchContactsModal"
     class="modal hide fade in centering"
     role="dialog"
     aria-labelledby="searchContactsModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <h3 id="searchContactsModalLabel" class="displayInLine">
            <spring:message code="search"/>
        </h3>
    </div>
    <div class="modal-body">
        <form name="searchContactForm" novalidate>
            <label><spring:message code="search.for"/></label>

            <div>
                <div class="input-append">
                    <input type="text"
                           autofocus
                           required
                           ng-model="searchFor"
                           name="searchFor"
                           placeholder="<spring:message code='contact'/>&nbsp;<spring:message code='contacts.name'/> "/>
                </div>
                <div class="input-append displayInLine">
                    <label class="displayInLine">
                        <span class="alert alert-error"
                              ng-show="displayValidationError && searchContactForm.searchFor.$error.required">
                            <spring:message code="required"/>
                        </span>
                    </label>
                </div>
            </div>
            <input type="submit"
                   class="btn btn-inverse"
                   ng-click="searchContact(searchContactForm, false);"
                   value='<spring:message code="search"/>'
                    />
            <button class="btn btn-inverse"
                    data-dismiss="modal"
                    ng-click="exit('#searchContactsModal');"
                    aria-hidden="true">
                <spring:message code="cancel"/>
            </button>
        </form>
    </div>
    <span class="alert alert-error dialogErrorMessage"
          ng-show="errorOnSubmit">
        <spring:message code="request.error"/>
    </span>
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
            <p><spring:message code="delete.confirm"/>:&nbsp;ID = {{contact.id}},Menu = {{contact.menu.captionMenu}}?</p>

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
 
 
<div class="modal fade" id="updateContactsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabelUpd" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <form name="updateContactForm" novalidate>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabelUpd">Update Akses Menu</h4>
            </div>
            <div class="modal-body">
                <div class="row form-group">
                    <div class="col-md-2">ID Menu</div>
                    <div class="col-md-2"><input type="text" class="form-control"
                                                 required
                                                 ng-model="contact.id"
                                                 name="id"
                                                 value="{{contact.id}}"/></div>
                </div>
                <div class="row form-group">
                    <div class="col-md-2">Caption</div>
                    <div class="col-md-6">
                        <input type="text" class="form-control"
                               autofocus
                               required
                               ng-model="contact.menu.captionMenu"
                               name="name"
                               placeholder="<spring:message code='contact'/>&nbsp;<spring:message code='contacts.menu.captionMenu'/> "/>
                    </div>
                </div>      
                <div class="row  form-group">      
                    <div class="col-md-2"></div>   
                    <div class="col-md-2"><input type="checkbox" ng-model="contact.is_read"  ng-true-value="1" ng-false-value="0" ng-checked="contact.is_read==1">is_read</label></div>         
                    <div class="col-md-2"><input type="checkbox" ng-model="contact.is_edit"  ng-true-value="1" ng-false-value="0" ng-checked="contact.is_edit==1">is_edit</label></div>         
                    <div class="col-md-2"><input type="checkbox" ng-model="contact.is_delete"  ng-true-value="1" ng-false-value="0" ng-checked="contact.is_delete==1">is_delete</label></div>         
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
                <button type="submit" class="btn btn-primary" ng-click="updateContact(updateContactForm);">Save changes</button>
            </div>
        </form>                    
    </div>
  </div>
                      
</div>
 