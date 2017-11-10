<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="<c:url value='/resources/lte/plugins/iCheck/flat/blue.css'/> ">  

<div class="content-wrapper"  ng-app  ng-controller="MyCtrl" id="xx">
    <section class="content-header" >
        <h1>List Menu</h1>       
    </section>
    <section class="content" >              
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">Add Menu</h3>
                <div class="box-tools">
                  <form name="searchContactForm" novalidate>
                      <div class="input-group input-group-sm" style="width: 150px;">
                          <select class="form-control  pull-right" ng-model="addsearchFor"
                                  name="addsearchFor" ng-init="addsearchFor=''">
                               <c:forEach var="roles" items="${roles}">
                                <option value=${roles}>${roles}</option>
                               </c:forEach>
                          </select>
                          <div class="input-group-btn">
                              <button type="submit" class="btn btn-default" id="addLine">
                                  <i class="fa fa-save"></i>
                              </button>
                          </div>
                      </div>
                  </form>                        
              </div>
            </div>
            <div class="box-body">
                <div id="loadingModal" class="modal hide fade in centering"
                     role="dialog"
                     aria-labelledby="deleteContactsModalLabel" aria-hidden="true">
                    <div id="divLoadingIcon" class="text-center">
                        <div class="icon-align-center loading"></div>
                    </div>
                </div>
                <form:form modelAttribute="contact"  action="">
                   <!-- <div class="input-group input-group-sm" style="width: 150px;" >
                        <button id="addLine" type="button">Add a line</button>
                        Tambah Menu Grup
                        <select class="form-control  pull-right ng-pristine ng-valid" ng-model="addsearchFor" name="addsearchFor"> 
                            <option value="1">Administrator</option>
                            <option value="2">User</option>
                        </select>
                        <div class="input-group-btn">
                            <button type="submit" class="btn btn-default" ng-click="addLine2()">
                                <i class="fa fa-save"></i>simpan
                            </button>
                        </div>
                    </div>-->
                    <div class="table-responsive mailbox-messages">
                        <table class="table table-hover table-striped" id="tableMenu">
                            <thead>
                                <tr>
                                    <td>
                                        <button id="btChkAll" type="button" class="btn btn-default btn-sm checkbox-toggle">
                                            <i class="fa fa-square-o"></i>
                                        </button>
                                    </td>
                                    <td>ID Menu</td>
                                    <td>Caption</td>
                                    <td>
                                        <button id="btChkisRead" type="button" class="btn btn-default btn-sm checkbox-toggle">
                                            <i class="fa fa-square-o"></i>
                                        </button>isRead</td>
                                    <td>
                                        <button id="btChkisEdit" type="button" class="btn btn-default btn-sm checkbox-toggle">
                                            <i class="fa fa-square-o"></i>
                                        </button>isEdit
                                    </td>
                                    <td>
                                        <button id="btChkisDel" type="button" class="btn btn-default btn-sm checkbox-toggle">
                                            <i class="fa fa-square-o"></i>
                                        </button>isDelete
                                    </td>
                                </tr>             
                            </thead>  
                            <tbody>  
                                <c:forEach var="menu" items="${listMenus}">
                                    <c:choose>
                                        <c:when test="${menu.hasChildren==1}">
                                            <tr>
                                                <td><input type="checkbox" class="chkAddAll"></td>
                                                <td>${menu.idMenu}</td>
                                                <td>${menu.captionMenu}</td>                   
                                                <td><input type="checkbox" class="chkisReadAll"></td>
                                                <td><input type="checkbox" class="chkisEditAll"></td>
                                                <td><input type="checkbox" class="chkisDelAll"></td>
                                            </tr> 
                                            <c:forEach var="subMenu1" items="${menu.subMenu}">  
                                                <c:choose>
                                                    <c:when test="${subMenu1.hasChildren==1}">
                                                        <tr>
                                                            <td><input type="checkbox" class="chkAddAll"></td>
                                                            <td>${subMenu1.idMenu}</td>  
                                                            <td>=>${subMenu1.captionMenu}</td>                   
                                                            <td><input type="checkbox" class="chkisReadAll"></td>
                                                            <td><input type="checkbox" class="chkisEditAll"></td>
                                                            <td><input type="checkbox" class="chkisDelAll"></td>
                                                        </tr> 
                                                        <c:forEach var="subMenu2" items="${subMenu1.subsubMenu}">   
                                                            <tr>
                                                                <td><input type="checkbox" class="chkAddAll"></td>
                                                                <td>${subMenu2.idMenu}</td>                   
                                                                <td>=>=>${subMenu2.captionMenu}</td>                   
                                                                <td><input type="checkbox" class="chkisReadAll"></td>
                                                                <td><input type="checkbox" class="chkisEditAll"></td>
                                                                <td><input type="checkbox" class="chkisDelAll"></td>
                                                            </tr> 
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <tr>
                                                            <td><input type="checkbox" class="chkAddAll"></td>
                                                            <td>${subMenu1.idMenu}</td>                   
                                                            <td>=>${subMenu1.captionMenu}</td>                   
                                                            <td><input type="checkbox" class="chkisReadAll"></td>
                                                            <td><input type="checkbox" class="chkisEditAll"></td>
                                                            <td><input type="checkbox" class="chkisDelAll"></td>
                                                        </tr> 
                                                    </c:otherwise>
                                                </c:choose>                          
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td><input type="checkbox" class="chkAddAll"></td>
                                                <td>${menu.idMenu}</td>   
                                                <td>${menu.captionMenu}</td>   
                                                <td><input type="checkbox" class="chkisReadAll"></td>
                                                <td><input type="checkbox" class="chkisEditAll"></td>
                                                <td><input type="checkbox" class="chkisDelAll"></td>
                                            </tr> 
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>
                            </tbody>
                        </table>   
                    </div>
                </form:form>
            </div>
        </div>
    </section>

</div>

<script>
    $(function () {
        //Enable iCheck plugin for checkboxes
        //iCheck for checkbox and radio inputs
        $('.mailbox-messages input[class="chkAddAll"]').iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });

        //Enable check and uncheck all functionality
        $("#btChkAll").click(function () {
            var clicks = $(this).data('clicks');
            if (clicks) {
                //Uncheck all checkboxes
                $(".mailbox-messages input[class='chkAddAll']").iCheck("uncheck");
                $(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
            } else {
                //Check all checkboxes
                $(".mailbox-messages input[class='chkAddAll']").iCheck("check");
                $(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
            }
            $(this).data("clicks", !clicks);
        });

        $('.mailbox-messages input[class="chkisReadAll"]').iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });

        //Enable check and uncheck all functionality
        $("#btChkisRead").click(function () {
            var clicks = $(this).data('clicks');
            if (clicks) {
                //Uncheck all checkboxes
                $(".mailbox-messages input[class='chkisReadAll']").iCheck("uncheck");
                $(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
            } else {
                //Check all checkboxes
                $(".mailbox-messages input[class='chkisReadAll']").iCheck("check");
                $(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
            }
            $(this).data("clicks", !clicks);
        });

        $('.mailbox-messages input[class="chkisEditAll"]').iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });

        //Enable check and uncheck all functionality
        $("#btChkisEdit").click(function () {
            var clicks = $(this).data('clicks');
            if (clicks) {
                //Uncheck all checkboxes
                $(".mailbox-messages input[class='chkisEditAll']").iCheck("uncheck");
                $(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
            } else {
                //Check all checkboxes
                $(".mailbox-messages input[class='chkisEditAll']").iCheck("check");
                $(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
            }
            $(this).data("clicks", !clicks);
        });

        $('.mailbox-messages input[class="chkisDelAll"]').iCheck({
            checkboxClass: 'icheckbox_flat-blue',
            radioClass: 'iradio_flat-blue'
        });

        //Enable check and uncheck all functionality
        $("#btChkisDel").click(function () {
            var clicks = $(this).data('clicks');
            if (clicks) {
                //Uncheck all checkboxes
                $(".mailbox-messages input[class='chkisDelAll']").iCheck("uncheck");
                $(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
            } else {
                //Check all checkboxes
                $(".mailbox-messages input[class='chkisDelAll']").iCheck("check");
                $(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
            }
            $(this).data("clicks", !clicks);
        });

    });
</script>


<script type='text/javascript'>
    $(function () {
        $("#addLine").click(function () {
            //angular.element('#xx').scope().addLine2();
            var lines = [];
            $('#tableMenu tbody tr .chkAddAll').each(function (index) {
                if (this.checked) {  
                    lines.push({
                        id_role: angular.element('#xx').scope().addsearchFor,
                        id_menu: document.getElementById("tableMenu").rows[index+1].cells[1].innerHTML,
                        is_read: document.getElementById("tableMenu").rows[index+1].cells[3].children[0].children[0].checked?1:0,
                        is_edit:document.getElementById("tableMenu").rows[index+1].cells[4].children[0].children[0].checked?1:0,
                        is_delete: document.getElementById("tableMenu").rows[index+1].cells[5].children[0].children[0].checked?1:0
                    });
                    //alert(document.getElementById("tableMenu").rows[index+1].cells[1].innerHTML);               
                }
            });
            if (lines.length > 0) {

                $.ajax({
                    url: "/tranmon/protected/access_menu/saveadd",
                    //data: JSON.stringify({ id: "12", name: "Boston" }),
                    //data: JSON.stringify([{ id: "12", name: "Boston"},{ id: "412", name: "jakarta"}]), //model1
                    //data: JSON.stringify({ conctact: lines}),
                    //data: JSON.stringify({ id:"41", id_role:"2",id_menu:"adf", is_read:"11",is_edit:"22",is_delete:"33"}),
                    /*data: JSON.stringify({ "contact":
                     { "id":"41", "id_role":"2","id_menu":"adf", "is_read":"11","is_edit":"22","is_delete":"33"},
                     //{ id:"61", id_role:"6",id_menu:"66f", is_read:"16",is_edit:"62",is_delete:"36"}
                     }),*/
                    /*data: JSON.stringify({ "contact":
                     { "id":"41", "id_role":"2","id_menu":"adf", "is_read":"11","is_edit":"22","is_delete":"33"},
                     
                     }), model2*/
                    data: JSON.stringify(lines),
                    method: "POST",
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    beforeSend: function (xhr) {
                        //xhr.setRequestHeader("Accept", "application/json");
                        //xhr.setRequestHeader("Content-Type", "application/json");                

                        $("#loadingModal").modal('show');
                    },
                    success: function (result) {
                        alert('sukses');
                        window.location.href = "/tranmon/protected/access_menu";
                    },
                    error: function () {
                        $("#loadingModal").modal('hide');
                        alert('failure');
                        return;
                    }
                })
                        .done(function (data) {
                            $("#loadingModal").modal('hide');
                        });

            }
            //console.table(lines);
        });
    });

    function MyCtrl($scope) {
        $scope.foo = "I'm foo!";
        $scope.lines = [];
        $scope.datas = [];

        $scope.addLine2 = function () {
         var lines = [];
            $('#tableMenu tbody tr .chkAddAll').each(function (index) {
                if (this.checked) {
                    lines.push({
                        id_role: $scope.addsearchFor,
                        id_menu: document.getElementById("tableMenu").rows[index + 1].cells[1].innerHTML,
                        is_read: document.getElementById("tableMenu").rows[index + 1].cells[3].innerHTML,
                        is_edit: document.getElementById("tableMenu").rows[index + 1].cells[4].innerHTML,
                        is_delete: document.getElementById("tableMenu").rows[index + 1].cells[5].innerHTML
                    });
                    //alert(document.getElementById("tableMenu").rows[index+1].cells[1].innerHTML);               
                }
            });    
            if (lines.length > 0 && $scope.addsearchFor !='') {
                    $.ajax({
                        url: "http://localhost:8080/tranmon/protected/access_menu/saveadd",
                        data: JSON.stringify(lines),
                        method: "POST",
                        contentType: 'application/json; charset=utf-8',
                        dataType: 'json',
                        beforeSend: function (xhr) {
                            $("#loadingModal").modal('show');
                        },
                        success: function (result) {
                            alert('sukses');
                            window.location.href = "/tranmon/protected/access_menu";
                        },
                        error: function () {
                            $("#loadingModal").modal('hide');
                            alert('failure');
                            window.location.href = "/tranmon/protected/access_menu";
                            return;
                        }
                    })
                        .done(function (data) {
                            $("#loadingModal").modal('hide');
                        });

            }
           else {
               alert('data tidak lengkap');
           }
        };
    }
</script>