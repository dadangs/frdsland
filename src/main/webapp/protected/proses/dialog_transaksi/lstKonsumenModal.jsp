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
<table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th scope="col">NoKTP</th>
                                <th scope="col">Nama</th>
                                <th scope="col">Alamat</th>
                                <th scope="col">NoAkad</th>
                                <th scope="col">NoHP</th>                    
                                <th scope="col">NoKTP Pasangan</th>
                                <th scope="col">NamaPasangan</th>
                                <th scope="col">NoHPPasangan</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="contact in page.source">
                                <td class="tdContactsCentered">{{contact.noktp}}</td>
                                <td class="tdContactsCentered">{{contact.nama}}</td>
                                <td class="tdContactsCentered">{{contact.alamat}}</td>
                                <td class="tdContactsCentered">{{contact.noakad}}</td>
                                <td class="tdContactsCentered">{{contact.nohp}}</td>
                                <td class="tdContactsCentered">{{contact.noktp_pasangan}}</td>
                                <td class="tdContactsCentered">{{contact.nama_pasangan}}</td>
                                <td class="tdContactsCentered">{{contact.nohp_pasangan}}</td>
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
 