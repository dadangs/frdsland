function transaksisController($scope, $http) {
	 $scope.IsVisiblePerumahan = false;
     $scope.IsVisible = false;

	 $scope.pageToGet = 0;

    $scope.state = 'busy';

    $scope.lastAction = '';

	var getUrl = window.location;
	//var baseUrl = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
	var  app = window.location.pathname.split('/')[1];
    $scope.url = "/"+app+"/protected/proses/transaksis/";    

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
    	$scope.url = "/"+app+"/protected/master/konsumens/";
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
    $scope.getPerumahanList = function () {
    	$scope.url = "/"+app+"/protected/master/rumahs/";
        var url = $scope.url;
        $scope.lastAction = 'list';

        $scope.startDialogAjaxRequest();

        var config = {params: {page: $scope.pageToGet}};

        $http.get(url, config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccessRumah(data, null, false);
            })
            .error(function () {
                $scope.state = 'error';
                $scope.displayCreateContactButton = false;
            });
    }

    $scope.getTransaksiList = function () {
    	$scope.url = "/"+app+"/protected/proses/transaksis/transaksi_avail";
        var url = $scope.url;
        $scope.lastAction = 'list';

        $scope.startDialogAjaxRequest();

        var config = {params: {page: $scope.pageToGet}};

        $http.get(url, config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccessTransaksi(data, null, false);
            })
            .error(function () {
                $scope.state = 'error';
                $scope.displayCreateContactButton = false;
            });
    }
    
    $scope.populateTable = function (data) {
        if (data.pagesCount > 0) {
            $scope.state = 'list';

            $scope.page = {source: data.konsumens, currentPage: $scope.pageToGet, pagesCount: data.pagesCount, totalContacts : data.totalKonsumens};

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

    $scope.populateTableRumah = function (data) {
        if (data.pagesCount > 0) {
            $scope.state = 'list';

            //$scope.page = {source: data.perumahans, currentPage: $scope.pageToGet, pagesCount: data.pagesCount, totalContacts : data.totalPerumahans};
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
    
    $scope.populateTableTransaksi = function (data) {
        if (data.pagesCount > 0) {
            $scope.state = 'list';

            $scope.page = {source: data.transaksis, currentPage: $scope.pageToGet, pagesCount: data.pagesCount, totalContacts : data.totalTransaksis};

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
    
    $scope.finishAjaxCallOnSuccessTransaksi = function (data, modalId, isPagination) {
        $scope.populateTableTransaksi(data);
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
        $scope.transaksi.noktp = $scope.contact.noktp;
        $scope.transaksi.alamat = $scope.contact.alamat;
        $scope.transaksi.nama = $scope.contact.nama ;
        $scope.exit("#lstKonsumenModal");
    }
   $scope.selectedContactRumah = function (contact) {
       var selectedContact = angular.copy(contact);
       $scope.contact = selectedContact;
       $scope.transaksi.id_produk = $scope.contact.id_produk;
       $scope.transaksi.nama_rumah = $scope.contact.proyek;
       $scope.transaksi.tipe_rumah = $scope.contact.tipe_rumah ;
       $scope.transaksi.kategori = $scope.contact.id_kategori;
       $scope.transaksi.luas_tanah = $scope.contact.luas_tanah;
       $scope.transaksi.blok = $scope.contact.blok;
       $scope.transaksi.nounit = $scope.contact.nounit;
       $scope.exit("#lstPerumahanModal");
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
    
    $scope.updateTotalMinDP = function(){    	 
    	var num1= $("#totalHargaPlusPenambahan").val().replace(/,/g,"");
    	var num2 = $("#dp").val().replace(/,/g,"");

    	 //var harga_total= (parseFloat(num1) - parseFloat(num2)).toFixed(2);    	 
    	var harga_total= (parseFloat(num1) - parseFloat(num2));
    	 $scope.transaksi.harga_total =$scope.toRp(harga_total); 
         //$("#harga_total").val($scope.toRp(harga_total));
    };
    
    $scope.updateHargaKesepakatanPlusPenambahan = function() {
    	var num=  $("#harga_kesepakatan").val().replace(/,/g,"");
    	var total_penambahan = $scope.transaksi.total_penambahan.replace(/,/g,"")||0;
        var totalHargaPlusPenambahan = +parseFloat(num||0) + +parseFloat( total_penambahan);
        $scope.transaksi.totalHargaPlusPenambahan = $scope.toRp(totalHargaPlusPenambahan);

    };
    
    $scope.updateHargaTotal= function() {
    	var num1 = $scope.transaksi.totalHargaPlusPenambahan.replace(/,/g,"")||0;
    	var num2 = $scope.transaksi.dp.replace(/,/g,"")||0;
        //var harga_total= parseFloat(+num1 - +num2).toFixed(2);
    	var harga_total= parseFloat(+num1 - +num2);
        $scope.transaksi.harga_total=$scope.toRp(harga_total);
    }
    $scope.updateTotalPenambahan = function() {
    	var num1 = $("#harga_tanah").val().replace(/,/g,"");
    	var num2 = $("#harga_lain").val().replace(/,/g,"");
        //var total_penambahan= (parseFloat(num1||0) + parseFloat(num2||0)).toFixed(2);
    	var total_penambahan= (parseFloat(num1||0) + parseFloat(num2||0));
        //$("#total_penambahan").val($scope.toRp(total_penambahan));
        $scope.transaksi.total_penambahan = $scope.toRp(total_penambahan);
    };  
    $scope.updateHargaTanah = function() {
    	var num1 =  $("#luas_tanah").val();
    	var num2 =  $("#harga_permeter").val();
    	
    	var num3= num1.replace(/,/g,"");
    	 
    	var num4= num2.replace(/,/g,"");
 
    	var harga_tanah=  (num3  * num4).toFixed(2);      	
    	var x = $scope.toRp(harga_tanah);    	    	
        $("#harga_tanah").val(x);
        $scope.transaksi.harga_tanah=x;
               
    };  
        
    $scope.init = function() {
    	$scope.transaksi.luas_tanah = "0.00";
    	$scope.transaksi.harga_permeter ="0";
    	$scope.transaksi.harga_tanah = "0";
    	$scope.transaksi.penambahan_lain = "-";
    	$scope.transaksi.harga_lain = "0";
    	$scope.transaksi.total_penambahan="0";
    	$scope.transaksi.harga_kesepakatan = "0";
    	$scope.transaksi.totalHargaPlusPenambahan = "0";
    	$scope.transaksi.dp = "0";
    	$scope.transaksi.harga_total = "0";
    }
    $scope.init();
    $scope.getTransaksiList();
    
    $scope.loadModelBayar = function(){
    	$scope.url = "/"+app+"/protected/master/konsumens/";
        var url = $scope.url+"carabayar/";
    	 $http.get(url)  
        .success(function(data){  
             $scope.carabayars = data;  
        })  
    }
    
    $scope.loadModelBayarDP = function(){
    	$scope.url = "/"+app+"/protected/master/konsumens/";
        var url = $scope.url+"carabayardp/";
    	 $http.get(url)  
        .success(function(data){  
             $scope.carabayarsdp = data;  
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

    $scope.loadTenorDP = function(){
    	$scope.url = "/"+app+"/protected/master/konsumens/";
        var url = $scope.url+"carabayardp/"+$scope.transaksi.model_bayardp+"/tenordp/";
    	//var config = {params: {modelbayar: $scope.model_bayar}};
    	 $http.get(url)  
        .success(function(data){  
             $scope.tenorsdp = data;  
            // $scope.tenor = ($scope.tenors[0].kode_tenor);

        })  
    }

     $scope.toRp = function(angka){    	    
        var rev     = parseInt(angka, 10).toString().split('').reverse().join('');
        var rev2    = '';
        for(var i = 0; i < rev.length; i++){
            rev2  += rev[i];
            if((i + 1) % 3 === 0 && i !== (rev.length - 1)){
                rev2 += ',';
            }
        }
        var hasil = rev2.split('').reverse().join('');
        /*var n =angka.toString().indexOf(".");
        var hasil="";
       
        if (n>0){
        	hasil = rev2.split('').reverse().join('')+angka.toString().substring(n,angka.toString().length);
        }
        else {
        	hasil = rev2.split('').reverse().join('')+'.00';
        }*/
        return hasil;
    }
     
     $scope.createTransaksi = function (newTransaksiForm) {
    	 if (!newTransaksiForm.$valid) {
             $scope.displayValidationError = true;
             return;
         }
    	 $scope.lastAction = 'create';
    	 $scope.url = "/"+app+"/protected/proses/transaksis/";  
         var url = $scope.url;

         var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};

         $scope.addSearchParametersIfNeeded(config, false);

         $scope.startDialogAjaxRequest();
         $scope.transaksi.cicilan_perbulan = $scope.transaksi.cicilan_perbulan.replace(/,/g,"");
         $scope.transaksi.cicilan_perbulandp = $scope.transaksi.cicilan_perbulandp.replace(/,/g,"");
         $scope.transaksi.dp = $scope.transaksi.dp.replace(/,/g,"");
         $scope.transaksi.harga_kesepakatan = $scope.transaksi.harga_kesepakatan.replace(/,/g,"");
         $scope.transaksi.harga_total = $scope.transaksi.harga_total.replace(/,/g,"");
         $scope.transaksi.totalHargaPlusPenambahan = $scope.transaksi.totalHargaPlusPenambahan.replace(/,/g,"");
         $http.post(url, $.param($scope.transaksi), config)
             .success(function (data) {
                 $scope.finishAjaxCallOnSuccess(data, "#addRumahModal", false);
                 $scope.transaksi={};
                 alert("insert data sukses");
                 location.reload();
             })
             .error(function(data, status, headers, config) {
                 $scope.handleErrorInDialogs(status);
             });
          
     }

     $scope.getPerumahanList1 = function () {    	    	 
         //$scope.IsVisiblePerumahan = $scope.IsVisiblePerumahan ? false : true;
    	 $scope.url = "/"+app+"/protected/proses/transaksis/";    
         var url = $scope.url+"produk_avail";
         $scope.lastAction = 'list';

         $scope.startDialogAjaxRequest();

         var config = {params: {page: $scope.pageToGet}};

         $http.get(url, config)
             .success(function (data) {
                 $scope.finishAjaxCallOnSuccessRumah(data, null, false);
             })
             .error(function () {
                 $scope.state = 'error';
                 $scope.displayCreateContactButton = false;
             });
     }

     $scope.ShowHide = function () {
         //If DIV is visible it will be hidden and vice versa.
         $scope.IsVisible = $scope.IsVisible ? false : true;
     }
     
     
}
