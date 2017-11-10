<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
.row form-group {
	margin-top: 3px;
	margin-bottom: 3px
}
.clearfix{*zoom:1;}.clearfix:before,.clearfix:after{display:table;content:"";line-height:0;}
.clearfix:after{clear:both;}
.hide-text{font:0/0 a;color:transparent;text-shadow:none;background-color:transparent;border:0;}
.input-block-level{display:block;width:100%;min-height:30px;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;}
.btn-file{overflow:hidden;position:relative;vertical-align:middle;}.btn-file>input{position:absolute;top:0;right:0;margin:0;opacity:0;filter:alpha(opacity=0);transform:translate(-300px, 0) scale(4);font-size:23px;direction:ltr;cursor:pointer;}
.fileupload{margin-bottom:9px;}.fileupload .uneditable-input{display:inline-block;margin-bottom:0px;vertical-align:middle;cursor:text;}
.fileupload .thumbnail{overflow:hidden;display:inline-block;margin-bottom:5px;vertical-align:middle;text-align:center;}.fileupload .thumbnail>img{display:inline-block;vertical-align:middle;max-height:100%;}
.fileupload .btn{vertical-align:middle;}
.fileupload-exists .fileupload-new,.fileupload-new .fileupload-exists{display:none;}
.fileupload-inline .fileupload-controls{display:inline;}
.fileupload-new .input-append .btn-file{-webkit-border-radius:0 3px 3px 0;-moz-border-radius:0 3px 3px 0;border-radius:0 3px 3px 0;}
.thumbnail-borderless .thumbnail{border:none;padding:0;-webkit-border-radius:0;-moz-border-radius:0;border-radius:0;-webkit-box-shadow:none;-moz-box-shadow:none;box-shadow:none;}
.fileupload-new.thumbnail-borderless .thumbnail{border:1px solid #ddd;}
.control-group.warning .fileupload .uneditable-input{color:#a47e3c;border-color:#a47e3c;}
.control-group.warning .fileupload .fileupload-preview{color:#a47e3c;}
.control-group.warning .fileupload .thumbnail{border-color:#a47e3c;}
.control-group.error .fileupload .uneditable-input{color:#b94a48;border-color:#b94a48;}
.control-group.error .fileupload .fileupload-preview{color:#b94a48;}
.control-group.error .fileupload .thumbnail{border-color:#b94a48;}
.control-group.success .fileupload .uneditable-input{color:#468847;border-color:#468847;}
.control-group.success .fileupload .fileupload-preview{color:#468847;}
.control-group.success .fileupload .thumbnail{border-color:#468847;}
</style>

<div class="modal fade" id="importRumahModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabelUpd" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			 
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabelUpd">import file xlsx</h4>
				</div>
			 				
 				
				<div class="modal-body">	
					<form name="userForm"  novalidate >				
						
					  	<table id="fileTable">
				                <tr>
				                     <td>Nama file :</td>
									<td>									    
										<div class="fileupload fileupload-new" data-provides="fileupload">
    										<span class="btn btn-default btn-file">
    											<span class="fileupload-new">Select file</span>
    											<span class="fileupload-exists">Change</span>         
    											<input type='file' accept=".xlsx" name='file' id='file' ng-file='uploadfiles'>
    											<div class="col-sm-2 has-error">
														<label> <span class="help-block"
															ng-show="displayValidationError && importXls.namafile.$error.required">
																<spring:message code="required" />
														</span>
														</label>
												</div>
    										</span>
    										<span class="fileupload-preview"></span>
    										<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none">×</a>
  										</div>
									</td> 
				                </tr>				                    
				            </table>
							<input type='button' value='ImportExcel' id='upload' ng-click='upload()' class="btn btn-primary fileupload" >				            
					</form> 
					<!-- <form novalidate method="post" action="<c:url value='/protected/master/rumahs/importExcel'/>" name="importXls" enctype="multipart/form-data"> 
				           <table id="fileTable">
				                <tr>
				                     <td>Nama file :</td>
									<td>									    
										<div class="fileupload fileupload-new" data-provides="fileupload">
    										<span class="btn btn-default btn-file">
    											<span class="fileupload-new">Select file</span>
    											<span class="fileupload-exists">Change</span>         
    											<input type="file" accept=".xlsx" name="namafile" fileModel="namafile"    />
    											<div class="col-sm-2 has-error">
														<label> <span class="help-block"
															ng-show="displayValidationError && importXls.namafile.$error.required">
																<spring:message code="required" />
														</span>
														</label>
												</div>
    										</span>
    										<span class="fileupload-preview"></span>
    										<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none">×</a>
  										</div>
									</td> 
				                </tr>
				                    
				            </table>
						    <br>
						    <br>				
				            <input type="submit" value="importExcel" class="btn btn-primary fileupload" />
				            input id="addFile" type="button" value="Add File" />
				     </form>-->
				</div>
			 
		
			
		</div>		
	</div>
</div>
<div class="modal fade" id="addRumahModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabelUpd" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form name="newRumahForm" novalidate>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabelUpd">Form Master
						Produk Perumahan</h4>
				</div>
				<div class="modal-body">
					<div class="row  form-group" ng-init="loadDeveloper()">
						<label for="lnoppjb" class="control-label col-sm-2">Developer</label>
						<div class="form-group col-sm-8">
							<select id="developer" name="developer"
								ng-model="contact.id_developer" class="form-control"
								style="width: 100%;" required ng-change="loadProyek();">
								<option value="">Pilih Developer</option>
								<option ng-repeat="developer in developers"
									value="{{developer.id}}">{{developer.nama}}</option>
							</select>							
						</div>
						<div class="col-sm-2 has-error">
								<label> <span class="help-block"
									ng-show="displayValidationError && newRumahForm.developer.$error.required">
										<spring:message code="required" />
								</span>
								</label>
						</div>
					</div>

					<div class="row  form-group">
						<label for="lnoppjb" class="control-label col-sm-2">Proyek</label>
						<div class="form-group col-sm-8">
							<select id="proyek" name="proyek" ng-model="contact.id_proyek"
								class="form-control" style="width: 100%;" required>
								<option value="">Pilih Proyek</option>
								<option ng-repeat="proyek in proyeks" value="{{proyek.kode}}">{{proyek.nama}}</option>
							</select>							
						</div>
						<div class="col-sm-2 has-error">
								<label> <span class="help-block"
									ng-show="displayValidationError && newRumahForm.proyek.$error.required">
										<spring:message code="required" />
								</span>
								</label>
							</div>
					</div>
					<div class="row form-group">
						 <label for="lnoppjb" class="control-label col-sm-2"> Blok</label>
						<div class="col-md-6">
							<input type="text" class="form-control" required
								ng-model="contact.blok" name="blok" />
						</div>
						<div class="col-md-2 has-error">
							<label> <span class="help-block"
								ng-show="displayValidationError && newRumahForm.blok.$error.required">
									<spring:message code="required" />
							</span>
							</label>
						</div>
					</div>
					<div class="row form-group">
						 <label for="lnoppjb" class="control-label col-sm-2"> No Unit</label>
						<div class="col-md-4">
							<input type="text" class="form-control" autofocus required
								ng-model="contact.nounit" name="nounit" />
						</div>
						<div class="col-md-2 has-error">
							<label> <span class="help-block"
								ng-show="displayValidationError && newRumahForm.nounit.$error.required">
									<spring:message code="required" />
							</span>
							</label>
						</div>
					</div>
					<div class="row  form-group" ng-init="loadKategori()">
						<label for="lnoppjb" class="control-label col-sm-2">Kategori</label>
						<div class="form-group col-sm-6">
							<select name="kategori" ng-model="contact.id_kategori"
								class="form-control" style="width: 100%;" required
								ng-change="loadTipe()"">
								<option value="">Pilih Kategori</option>
								<option ng-repeat="kategori in kategoris"
									value="{{kategori.id}}">{{kategori.nama}}</option>
							</select>							
						</div>
						<div class="col-sm-2 has-error">
								<label> <span class="help-block"
									ng-show="displayValidationError && newRumahForm.kategori.$error.required">
										<spring:message code="required" />
								</span>
								</label>
							</div>
					</div>
					<div class="row  form-group">
						<label for="lnoppjb" class="control-label col-sm-2">Tipe</label>
						<div class="form-group col-sm-6">
							<select name="tipe" ng-model="contact.tipe_rumah"
								class="form-control" style="width: 100%;"required ">
								<option value="">Pilih Tipe</option>
								<option ng-repeat="tipe in tipes" value="{{tipe.id}}">{{tipe.nama}}</option>
							</select>							
						</div>
						<div class="col-sm-2 has-error">
								<label> <span class="help-block"
									ng-show="displayValidationError && newRumahForm.tipe.$error.required">
										<spring:message code="required" />
								</span>
								</label>
							</div>
					</div>
					<div class="row form-group">
					   <label for="lnoppjb" class="control-label col-sm-2">  Luas Tanah</label>
						<div class="col-md-6">
							<input type="text" class="form-control" required
								ng-model="contact.luas_tanah" name="luas_tanah" />
						</div>
						<div class="col-md-2 has-error">
							<label> <span class="help-block"
								ng-show="displayValidationError && newRumahForm.luas_tanah.$error.required">
									<spring:message code="required" />
							</span>
							</label>
						</div>
					</div>
					
				</div>
				<div class="modal-footer">
					<div class="text-center">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						ng-click="exit('#addRumahModal');">Close</button>
					<button type="submit" class="btn btn-primary"
						ng-click="createContact(newRumahForm);">Save changes</button>
					</div>	
				</div>
			</form>
		</div>
	</div>
</div>



<div class="modal fade" id="updateContactsModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabelUpd" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form name="updateContactForm" novalidate>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabelUpd">Update Master
						Produk Perumahan</h4>
				</div>
				<div class="modal-body">
					<div class="row  form-group" ng-init="loadDeveloper();">
						<label for="lnoppjb" class="control-label col-sm-2">Developer</label>
						<div class="form-group col-sm-8">
							<select name="developer" ng-model="contact.id_developer"
								class="form-control" style="width: 100%;" required
								ng-change="loadProyek();">
								<option value="">Pilih Developer</option>
								<option ng-repeat="developer in developers"
									value="{{developer.id}}">{{developer.nama}}</option>
							</select>							
						</div>
						<div class="col-sm-2 has-error">
								<label> <span class="help-block"
									ng-show="displayValidationError && newRumahForm.developer.$error.required">
										<spring:message code="required" />
								</span>
								</label>
						</div>
					</div>
					<div class="row  form-group">
						<label for="lnoppjb" class="control-label col-sm-2">proyek</label>
						<div class="form-group col-sm-8">
							<select name="proyek" ng-model="contact.updid_proyek"
								class="form-control" style="width: 100%;" required>

								<option value="">Pilih Proyek</option>
								<option ng-repeat="proyek in proyeks" value="{{proyek.kode}}"  ng-selected="{{ proyek.kode ==  contact.id_proyek }}">{{proyek.nama}}</option>

							</select>							
						</div>
						<div class="col-sm-2 has-error">
								<label> <span class="help-block"
									ng-show="displayValidationError && newRumahForm.proyek.$error.required">
										<spring:message code="required" />
								</span>
								</label>
						</div>
					</div>

					<div class="row  form-group" ng-init="loadKategori()">
						<label for="lnoppjb" class="control-label col-sm-2">Kategori</label>
						<div class="form-group col-sm-6">
							<select name="kategori" ng-model="contact.id_kategori"
								class="form-control" style="width: 100%;" required
								ng-change="loadTipe()"">
								<option value="">Pilih Kategori</option>
								<option ng-repeat="kategori in kategoris"
									value="{{kategori.id}}">{{kategori.nama}}</option>
							</select>							
						</div>
						<div class="col-sm-2 has-error">
								<label> <span class="help-block"
									ng-show="displayValidationError && newRumahForm.kategori.$error.required">
										<spring:message code="required" />
								</span>
								</label>
							</div>
					</div>
					<div class="row  form-group">
						<label for="lnoppjb" class="control-label col-sm-2">Tipe</label>
						<div class="form-group col-sm-6">
							<select name="tipe" ng-model="contact.tipe_rumah"
								class="form-control" style="width: 100%;"required ">
								<option value="">Pilih Tipe</option>
								<option ng-repeat="tipe in tipes" value="{{tipe.id}}"  ng-selected="{{ tipe.id ==  contact.tipe_rumah }}">{{tipe.nama}}</option>
							</select>							
						</div>
						<div class="col-sm-2 has-error">
								<label> <span class="help-block"
									ng-show="displayValidationError && newRumahForm.tipe.$error.required">
										<spring:message code="required" />
								</span>
								</label>
							</div>
					</div>
					<div class="row form-group">
						<label for="lnoppjb" class="control-label col-sm-2">Luas Tanah</label>
						<div class="col-md-6">
							<input type="text" class="form-control" required
								ng-model="contact.luas_tanah" name="luas_tanah" />
						</div>
						<div class="col-md-2 has-error">
							<label> <span class="help-block"
								ng-show="displayValidationError && newContactForm.luas.$error.required">
									<spring:message code="required" />
							</span>
							</label>
						</div>
					</div>
					<div class="row form-group">
						<label for="lnoppjb" class="control-label col-sm-2">Blok</label>
						<div class="col-md-6">
							<input type="text" class="form-control" required
								ng-model="contact.blok" name="blok" />
						</div>
						<div class="col-sm-2 has-error">
							<label> <span class="help-block"
								ng-show="displayValidationError && newContactForm.blok.$error.required">
									<spring:message code="required" />
							</span>
							</label>
						</div>
					</div>
					<div class="row form-group">
						<label for="lnoppjb" class="control-label col-sm-2">No Unit</label>
						<div class="col-md-4">
							<input type="text" class="form-control" autofocus required
								ng-model="contact.nounit" name="nounit" />
						</div>
					</div>					
				</div>
				<div class="row  form-group">
					<span class="alert alert-error dialogErrorMessage"
						ng-show="errorOnSubmit"> <spring:message
							code="request.error" />
					</span>
				</div>
		
		<div class="modal-footer">
			<div class="text-center">
			<button type="button" class="btn btn-default" data-dismiss="modal"
				ng-click="exit('#updateContactsModal');">Close</button>
			<button type="submit" class="btn btn-primary"
				ng-click="updateContact(updateContactForm);">Save changes</button>
			</div>	
		</div>
		</form>
		</div>
	</div>
</div>





<div class="modal fade" id="delContactsModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Delete</h4>
			</div>
			<div class="modal-body">
				<div class="row  form-group">
					<form name="deleteContactForm" novalidate>
						<p>
							<spring:message code="delete.confirm" />
							:&nbsp;Id Produk= {{contact.id_produk}} ?
						</p>

						<input type="submit" class="btn btn-inverse"
							ng-click="deleteContact();"
							value='<spring:message code="delete"/>' />
						<button class="btn btn-inverse" data-dismiss="modal"
							ng-click="exit('#delContactsModal');" aria-hidden="true">
							<spring:message code="cancel" />
						</button>
					</form>
				</div>
				<div class="row  form-group">
					<span class="alert alert-error dialogErrorMessage"
						ng-show="errorOnSubmit"> <spring:message
							code="request.error" />
					</span> <span class="alert alert-error dialogErrorMessage"
						ng-show="errorIllegalAccess"> <spring:message
							code="request.illegal.access" />
					</span>
				</div>
			</div>

		</div>
	</div>
</div>

<script>
!function(e){var t=function(t,n){this.$element=e(t),this.type=this.$element.data("uploadtype")||(this.$element.find(".thumbnail").length>0?"image":"file"),this.$input=this.$element.find(":file");if(this.$input.length===0)return;this.name=this.$input.attr("name")||n.name,this.$hidden=this.$element.find('input[type=hidden][name="'+this.name+'"]'),this.$hidden.length===0&&(this.$hidden=e('<input type="hidden" />'),this.$element.prepend(this.$hidden)),this.$preview=this.$element.find(".fileupload-preview");var r=this.$preview.css("height");this.$preview.css("display")!="inline"&&r!="0px"&&r!="none"&&this.$preview.css("line-height",r),this.original={exists:this.$element.hasClass("fileupload-exists"),preview:this.$preview.html(),hiddenVal:this.$hidden.val()},this.$remove=this.$element.find('[data-dismiss="fileupload"]'),this.$element.find('[data-trigger="fileupload"]').on("click.fileupload",e.proxy(this.trigger,this)),this.listen()};t.prototype={listen:function(){this.$input.on("change.fileupload",e.proxy(this.change,this)),e(this.$input[0].form).on("reset.fileupload",e.proxy(this.reset,this)),this.$remove&&this.$remove.on("click.fileupload",e.proxy(this.clear,this))},change:function(e,t){if(t==="clear")return;var n=e.target.files!==undefined?e.target.files[0]:e.target.value?{name:e.target.value.replace(/^.+\\/,"")}:null;if(!n){this.clear();return}this.$hidden.val(""),this.$hidden.attr("name",""),this.$input.attr("name",this.name);if(this.type==="image"&&this.$preview.length>0&&(typeof n.type!="undefined"?n.type.match("image.*"):n.name.match(/\.(gif|png|jpe?g)$/i))&&typeof FileReader!="undefined"){var r=new FileReader,i=this.$preview,s=this.$element;r.onload=function(e){i.html('<img src="'+e.target.result+'" '+(i.css("max-height")!="none"?'style="max-height: '+i.css("max-height")+';"':"")+" />"),s.addClass("fileupload-exists").removeClass("fileupload-new")},r.readAsDataURL(n)}else this.$preview.text(n.name),this.$element.addClass("fileupload-exists").removeClass("fileupload-new")},clear:function(e){this.$hidden.val(""),this.$hidden.attr("name",this.name),this.$input.attr("name","");if(navigator.userAgent.match(/msie/i)){var t=this.$input.clone(!0);this.$input.after(t),this.$input.remove(),this.$input=t}else this.$input.val("");this.$preview.html(""),this.$element.addClass("fileupload-new").removeClass("fileupload-exists"),e&&(this.$input.trigger("change",["clear"]),e.preventDefault())},reset:function(e){this.clear(),this.$hidden.val(this.original.hiddenVal),this.$preview.html(this.original.preview),this.original.exists?this.$element.addClass("fileupload-exists").removeClass("fileupload-new"):this.$element.addClass("fileupload-new").removeClass("fileupload-exists")},trigger:function(e){this.$input.trigger("click"),e.preventDefault()}},e.fn.fileupload=function(n){return this.each(function(){var r=e(this),i=r.data("fileupload");i||r.data("fileupload",i=new t(this,n)),typeof n=="string"&&i[n]()})},e.fn.fileupload.Constructor=t,e(document).on("click.fileupload.data-api",'[data-provides="fileupload"]',function(t){var n=e(this);if(n.data("fileupload"))return;n.fileupload(n.data());var r=e(t.target).closest('[data-dismiss="fileupload"],[data-trigger="fileupload"]');r.length>0&&(r.trigger("click.fileupload"),t.preventDefault())})}(window.jQuery)
</script>