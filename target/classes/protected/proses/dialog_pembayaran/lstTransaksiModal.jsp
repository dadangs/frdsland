<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<style>
  #lstKonsumenModal .modal-dialog  {width:75%; }
  .modal-body{
  width: 100%;
  height: 100%;

  /* spacing as needed */
  padding: 20px 50px 20px 20px;

  /* let it scroll */
  overflow: auto;}
</style>

<div class="modal fade" id="lstKonsumenModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabelUpd" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
       
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabelUpd">Daftar Konsumen</h4>
                {{contact.nama}}
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
                            	<th scope="col" style="display: none">idtransaksi</th>
                                <th scope="col">NoKTP</th>
                                <th scope="col">Nama</th>
                                <th scope="col">Alamat</th>
                                <th scope="col">Perumahan</th>
                                <th scope="col">Blok</th>
                                <th scope="col">No Unit</th>
                                <th scope="col">Kategori</th>
                                <th scope="col">Tipe</th>                    
                                <th scope="col">Luas</th>
                                <th scope="col" style="display: none">ModelBayar</th>
                                <th scope="col">Cicilan</th>
                                <th scope="col" style="display: none">Tenor</th>
                                <th scope="col">JtTempo</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="contact in page.source">
                             	<td class="tdContactsCentered" style="display: none">{{contact.idtransaksi}}</td>
                                <td class="tdContactsCentered">{{contact.noktp}}</td>
                                <td class="tdContactsCentered">{{contact.nama}}</td>
                                <td class="tdContactsCentered">{{contact.alamat}}</td>
                                <td class="tdContactsCentered">{{contact.nama_rumah}}</td>
                                <td class="tdContactsCentered">{{contact.blok}}</td>
                                <td class="tdContactsCentered">{{contact.nounit}}</td>
                                <td class="tdContactsCentered">{{contact.kategori}}</td>
                                <td class="tdContactsCentered">{{contact.tipe_rumah}}</td>
                                <td class="tdContactsCentered">{{contact.luas_tanah}}</td>
                                <td class="tdContactsCentered" style="display: none">{{contact.model_bayar}}</td>
                                <td class="text-right">{{contact.cicilan_perbulan | number:0}}</td>
                                <td class="tdContactsCentered" style="display: none">{{contact.tenor}}</td>
                                <td class="text-right">{{contact.tgl_jatuhtempo}}</td>
                                <td class="width15">
                                    <div class="text-center">   
                                            <a  
                                               ng-click="selectedContact(contact);"
                                               role="button"
                                               title="Pilih" 
                                               data-toggle="modal"> 
                                                <i class="fa fa-edit"></i> 
                                            </a>
                                           
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>                                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="exit('#lstKonsumenModal');">Close</button>
            </div>
          </div>           
    </div>
  </div>
</div>
 