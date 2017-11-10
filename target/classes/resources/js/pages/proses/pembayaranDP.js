function pembayaransController($scope, $http) {
	 $scope.IsVisiblePerumahan = false;
     $scope.IsVisible = false;

	 $scope.pageToGet = 0;

    $scope.state = 'busy';

    $scope.lastAction = '';

	var getUrl = window.location;
	//var baseUrl = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
	var  app = window.location.pathname.split('/')[1];
    $scope.url = "/"+app+"/protected/proses/pembayarans_dp/";    

    $scope.errorOnSubmit = false;
    $scope.errorIllegalAccess = false;
    $scope.displayMessageToUser = false;
    $scope.displayValidationError = false;
    $scope.displaySearchMessage = false;
    $scope.displaySearchButton = false;
    $scope.displayCreateContactButton = false;

    $scope.contact = {}
    $scope.transaksi = {}
    $scope.searchFor = ""

    $scope.getContactList = function () {
        $scope.url = "/"+app+"/protected/proses/pembayarans_dp/";    
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

            $scope.page = {source: data.transaksis, currentPage: $scope.pageToGet, pagesCount: data.pagesCount, totalContacts : data.totaltransaksis};

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
    $scope.finishAjaxCallOnSuccessRumah = function (data, modalId, isPagination) {
        $scope.populateTableRumah(data);
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
        $scope.transaksi.idtransaksi = $scope.contact.idtransaksi;
        $scope.transaksi.noktp = $scope.contact.noktp;
        $scope.transaksi.alamat = $scope.contact.alamat;
        $scope.transaksi.nama = $scope.contact.nama ;
        $scope.transaksi.id_produk = $scope.contact.id_produk;
        $scope.transaksi.nama_rumah = $scope.contact.nama_rumah;
        $scope.transaksi.tipe_rumah = $scope.contact.tipe_rumah ;
        $scope.transaksi.kategori = $scope.contact.kategori;
        $scope.transaksi.luas_tanah = $scope.contact.luas_tanah;
        $scope.transaksi.blok = $scope.contact.blok;
        $scope.transaksi.nounit = $scope.contact.nounit;
        $scope.transaksi.model_bayar = $scope.contact.model_bayar;
        $scope.loadTenor();
        $scope.transaksi.tenor = $scope.contact.tenor;
        $scope.transaksi.cicilan_perbulan= $scope.contact.cicilan_perbulan;
        $scope.transaksi.tgl_jatuhtempo = $scope.contact.tgl_jatuhtempo;
        $scope.exit("#lstKonsumenModal");
    }

    
    $scope.resetContact = function(){
        $scope.contact = {};
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
    
     
    $scope.loadModelBayar = function(){
    	$scope.url = "/"+app+"/protected/master/konsumens/";
        var url = $scope.url+"carabayar/";
    	 $http.get(url)  
        .success(function(data){  
             $scope.carabayars = data;  
        })  
    }
    
    $scope.loadTenor = function(){
    	$scope.url = "/"+app+"/protected/master/konsumens/";
        var url = $scope.url+"carabayar/"+$scope.transaksi.model_bayar+"/tenor/";
    	//var config = {params: {modelbayar: $scope.model_bayar}};
    	 $http.get(url)  
        .success(function(data){  
             $scope.tenors = data;  
            // $scope.tenor = ($scope.tenors[0].kode_tenor);

        })  
    }
    
   
     $scope.createTransaksi = function (newTransaksiForm) {
    	 if (!newTransaksiForm.$valid) {
             $scope.displayValidationError = true;
             return;
         }
    	 $scope.lastAction = 'create';
    	 $scope.url = "/"+app+"/protected/proses/pembayarans_dp/";  
         var url = $scope.url;

         var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};

         $scope.addSearchParametersIfNeeded(config, false);

         $scope.startDialogAjaxRequest();
         $scope.transaksi.jumlah_bayar = $scope.transaksi.jumlah_bayar.replace(/,/g,"");
         
         $http.post(url, $.param($scope.transaksi), config)
             .success(function (data) {
                 $scope.finishAjaxCallOnSuccess(data, "#addRumahModal", false);
                 $scope.transaksi={};                               
                 alert("insert data sukses");
             })
             .error(function(data, status, headers, config) {
                 $scope.handleErrorInDialogs(status);
             });
          
     }
     
     $scope.getContactListBayar = function () {
         $scope.url = "/"+app+"/protected/proses/pembayarans_dp/";    
         var url = $scope.url+"listBayar";
         $scope.lastAction = 'list';

         $scope.startDialogAjaxRequest();

         var config = {params: {page: $scope.pageToGet}};

         $http.get(url, config)
             .success(function (data) {
                 $scope.finishAjaxCallOnSuccessBayar(data, null, false);
             })
             .error(function () {
                 $scope.state = 'error';
                 $scope.displayCreateContactButton = false;
             });
     }

     $scope.finishAjaxCallOnSuccessBayar = function (data, modalId, isPagination) {
         $scope.populateTableBayar(data);
         $("#loadingModal").modal('hide');

         if(!isPagination){
             if(modalId){
                 $scope.exit(modalId);
             }
         }

         $scope.lastAction = '';
     }

     $scope.populateTableBayar = function (data) {
         if (data.pagesCount > 0) {
             $scope.state = 'list';

             $scope.page = {source: data.pembayarans, currentPage: $scope.pageToGet, pagesCount: data.pagesCount, totalContacts : data.totalPembayarans};

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

     $scope.getContactListBayar();
     
     $scope.ShowHide = function () {
         //If DIV is visible it will be hidden and vice versa.
         $scope.IsVisible = $scope.IsVisible ? false : true;
     }
      
}
