function rumahsController($scope, $http) {	                    
	var getUrl = window.location;
	//var baseUrl = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
	var  app = window.location.pathname.split('/')[1];
    $scope.pageToGet = 0;

    $scope.state = 'busy';

    $scope.lastAction = '';

    $scope.url = "/"+app+"/protected/master/rumahs/";


    $scope.errorOnSubmit = false;
    $scope.errorIllegalAccess = false;
    $scope.displayMessageToUser = false;
    $scope.displayValidationError = false;
    $scope.displaySearchMessage = false;
    $scope.displaySearchButton = false;
    $scope.displayCreateContactButton = false;

    $scope.contact = {}

    $scope.searchFor = ""

    $scope.getContactList = function () {
        var url = $scope.url;
        $scope.lastAction = 'list';

        $scope.startDialogAjaxRequest();

        var config = {params: {page: $scope.pageToGet}};

        $http.get(url, config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, null, false);
            })
            .error(function () {
                $scope.state = 'error';
                $scope.displayCreateContactButton = false;
            });
    }

    $scope.populateTable = function (data) {
        if (data.pagesCount > 0) {
            $scope.state = 'list';

            $scope.page = {source: data.produks, currentPage: $scope.pageToGet, pagesCount: data.pagesCount, totalContacts : data.totalProduks};

            if($scope.page.pagesCount <= $scope.page.currentPage){
                $scope.pageToGet = $scope.page.pagesCount - 1;
                $scope.page.currentPage = $scope.page.pagesCount - 1;
            }

            $scope.displayCreateContactButton = true;
            $scope.displaySearchButton = true;
        } else {
            $scope.state = 'noresult';
            $scope.displayCreateContactButton = true;

            if(!$scope.searchFor){
                $scope.displaySearchButton = false;
            }
        }

        if (data.actionMessage || data.searchMessage) {
            
            $scope.displayMessageToUser = $scope.lastAction != 'search';

            //$scope.page.actionMessage = data.actionMessage;
            //$scope.page.searchMessage = data.searchMessage;
        } else {
            $scope.displayMessageToUser = false;
        }
    }

    $scope.changePage = function (page) {
        $scope.pageToGet = page;

        if($scope.searchFor){
            $scope.searchContact($scope.searchFor, true);
        } else{
            $scope.getContactList();
        }
    };

    $scope.exit = function (modalId) {
        $(modalId).modal('hide');

        $scope.contact = {};
        $scope.errorOnSubmit = false;
        $scope.errorIllegalAccess = false;
        $scope.displayValidationError = false;
    }

    $scope.finishAjaxCallOnSuccess = function (data, modalId, isPagination) {
        $scope.populateTable(data);
        $("#loadingModal").modal('hide');

        if(!isPagination){
            if(modalId){
                $scope.exit(modalId);
            }
        }

        $scope.lastAction = '';
    }

    $scope.startDialogAjaxRequest = function () {
        $scope.displayValidationError = false;
        $("#loadingModal").modal('show');
        $scope.previousState = $scope.state;
        $scope.state = 'busy';
    }

    $scope.handleErrorInDialogs = function (status) {
        $("#loadingModal").modal('hide');
        $scope.state = $scope.previousState;

        // illegal access
        if(status == 403){
            $scope.errorIllegalAccess = true;
            return;
        }

        $scope.errorOnSubmit = true;
        $scope.lastAction = '';
    }

    
   $scope.selectedContact = function (contact) {
        var selectedContact = angular.copy(contact);
        $scope.contact = selectedContact;
       //alert($scope.contact.id_proyek);
        //alert($scope.contact.nama);
         $scope.loadProyek();
         $scope.loadTipe();
                             
    }
    
   
    $scope.resetContact = function(){
        $scope.contact = {};
    };
    
    $scope.createContact = function (newContactForm) {
        if (!newContactForm.$valid) {
            $scope.displayValidationError = true;
            return;
        }

        $scope.lastAction = 'create';

        var url = $scope.url;

        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};

        $scope.addSearchParametersIfNeeded(config, false);

        $scope.startDialogAjaxRequest();

        $http.post(url, $.param($scope.contact), config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, "#addRumahModal", false);
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };
    
$scope.addSearchParametersIfNeeded = function(config, isPagination) {
        if(!config.params){
            config.params = {};
        }

        config.params.page = $scope.pageToGet;

        if($scope.searchFor){
            config.params.searchFor = $scope.searchFor;
        }
    }
    $scope.searchContact = function (searchContactForm, isPagination) {
        /*if (!($scope.searchFor) && (!searchContactForm.$valid)) {
            $scope.displayValidationError = true;
            return;
        }*/

        $scope.lastAction = 'search';

        var url = $scope.url +  $scope.searchFor;

        $scope.startDialogAjaxRequest();

        var config = {};

        if($scope.searchFor){
            $scope.addSearchParametersIfNeeded(config, isPagination);
        }

        $http.get(url, config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, "#searchContactsModal", isPagination);
                $scope.displaySearchMessage = true;
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };   
    
    $scope.updateContact = function (updateContactForm) {
        /*if (!updateContactForm.$valid) {
            $scope.displayValidationError = true;
            return;
        }*/

        $scope.lastAction = 'update';

        var url = $scope.url + $scope.contact.id_produk;

        $scope.startDialogAjaxRequest();

        var config = {}

        $scope.addSearchParametersIfNeeded(config, false);

        $http.put(url, $scope.contact, config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, "#updateContactsModal", false);
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };
    
    $scope.deleteContact = function () {
        $scope.lastAction = 'delete';

        var url = $scope.url + $scope.contact.id_produk;

        $scope.startDialogAjaxRequest();

        var params = {searchFor: $scope.searchFor, page: $scope.pageToGet};

        $http({
            method: 'DELETE',
            url: url,
            params: params
        }).success(function (data) {
                $scope.resetContact();
                $scope.finishAjaxCallOnSuccess(data, "#delContactsModal", false);
            }).error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };
    
    $scope.tambah = function () {
        var url = $scope.url +  "loadpage/"+"addpage";
        $scope.lastAction = 'tambah';
        /*$("#loadingModal").modal('show');
         var htmlcontent = $('#loadAddAksesMenu');
        htmlcontent.load(url,function(respons,status,xhr){
            if (status=="success"){                
            }            
            $("#loadingModal").modal('hide');
        })*/
        $scope.startDialogAjaxRequest();

        $http({
            method: 'GET',
            url: url,
        }).success(function (data) {
            alert(data);
                $scope.finishAjaxCallOnSuccess(data, null, false);
            }).error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    }
    
    $scope.loadDeveloper = function(){
        var url = $scope.url+"loadDeveloper/";
    	 $http.get(url)  
        .success(function(data){  
             $scope.developers = data;  
        })  
    }
    $scope.loadProyek= function(){
        var url = $scope.url+"loadDeveloper/"+$scope.contact.id_developer+"/proyek/";
    	//var config = {params: {modelbayar: $scope.model_bayar}};
    	 $http.get(url)  
        .success(function(data){  
             $scope.proyeks = data;  
            // $scope.tenor = ($scope.tenors[0].kode_tenor);

        })  
    }
    $scope.loadKategori = function(){
        var url = $scope.url+"loadKategori/";
    	 $http.get(url)  
        .success(function(data){  
             $scope.kategoris = data;  
        })  
    }
    $scope.loadTipe = function(){
        var url = $scope.url+"loadKategori/"+$scope.contact.id_kategori+"/loadTipe/";
    	 $http.get(url)  
        .success(function(data){  
             $scope.tipes = data;  
        })  
    }

    $scope.importExcel = function (importXls){
    	alert("1");
    	if (!importXls.$valid) {
    		alert("2");
            $scope.displayValidationError = true;
            return;
        }
    	alert("3");
        $scope.lastAction = 'import';

        var url = $scope.url+"importExcel";
        alert(url);
        //var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};
        var config ={};
        //$scope.addSearchParametersIfNeeded(config, false);

        $scope.startDialogAjaxRequest();
        var fd = new FormData();
        fd.append('namafile', $scope.namafile);
        //fd.append('user', "dadang");
        //$http.post(url, $.param($scope.namafile), config)
        $http.post(url, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
         })
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, "#addRumahModal", false);
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });    	
    }
   
    $scope.getContactList();
    
    $scope.upload = function(){
    	 var uploadUrl = $scope.url+"import";
    	  var fd = new FormData();
    	  var files = document.getElementById('file').files[0];
    	  fd.append('file',files);
    	  // AJAX request
    	  $http({
    	   method: 'post',
    	   url: uploadUrl,
    	   data: fd,
    	   transformRequest: angular.identity,
    	   headers: {'Content-Type': undefined},
    	  }).then(function successCallback(response) { 
    	    // Store response data
    		  alert(response.data);
    		  $scope.exit("#importRumahModal");
    		  $scope.getContactList();
    	  });
    	 }
     
}

 