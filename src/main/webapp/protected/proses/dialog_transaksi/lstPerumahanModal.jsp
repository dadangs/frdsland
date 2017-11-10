<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
#lstPerumahanModal .modal-dialog {
	width: 75%;
}
</style>
<div class="modal fade" id="lstPerumahanModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabelUpd" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form name="newRumahForm" novalidate>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabelUpd">Master Perumahan</h4>
				</div>
				<div class="modal-body">
					<div ng-class="{'alert alert-info': state == 'noresult', 'none': state != 'noresult'}">
			            <h4><i class="icon-info-sign"></i> <spring:message code="contacts.emptyData"/></h4><br/>
			
			            <p><spring:message code="contacts.emptyData.text"/></p>
			        </div>
             		<div id="gridContainer" ng-class="{'': state == 'list', 'none': state != 'list'}">     
					<table class="table table-bordered table-striped">
						<thead>
                            <tr>
                                <!-- <th scope="col">ID Proyek</th>
                                <th scope="col">ID Perumahan</th>
                                <th scope="col">Nama Perumahan</th>
                                <th scope="col">Jumlah Unit</th>
                                <th scope="col">Tipe</th> -->
                                <th scope="col" style="display: none">id Developer</th>                                
                                <th scope="col" style="display: none">id Proyek</th>
                                <th scope="col">Developer</th>
                                <th scope="col">Proyek</th>
                                <th scope="col">Id Produk</th>
                                <th scope="col">Blok</th>
                                <th scope="col">No.Unit</th>                                     
                                <th scope="col">Kategori</th>
                                <th scope="col">Tipe</th>
                                <th scope="col">Luas</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="contact in page.source">
                                <td class="tdContactsCentered" style="display: none">{{contact.id_developer}}</td>
                                <td class="tdContactsCentered" style="display: none">{{contact.id_proyek}}</td>
                                <td class="tdContactsCentered">{{contact.developer}}</td>
                                <td class="tdContactsCentered">{{contact.proyek}}</td>
                                <td class="tdContactsCentered">{{contact.id_produk}}</td>
                                <td class="tdContactsCentered">{{contact.blok}}</td>
                                <td class="tdContactsCentered">{{contact.nounit}}</td>
                                <td class="tdContactsCentered">{{contact.id_kategori}}</td>
                                <td class="tdContactsCentered">{{contact.tipe_rumah}}</td>
                                <td class="tdContactsCentered">{{contact.luas_tanah}}</td>
								<td class="width15">
									<div class="text-center">
										<a 
											ng-click="selectedContactRumah(contact);" role="button"
											title="Pilih" data-toggle="modal"> <i class="fa fa-edit"></i>
										</a>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						ng-click="exit('#lstPerumahanModal');">Close</button>
				</div>
			</form>
		</div>
	</div>
</div>
