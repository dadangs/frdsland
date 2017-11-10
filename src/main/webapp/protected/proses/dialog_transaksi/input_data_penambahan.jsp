<div class="box">
    <div class="box box-default collapsed-box">
        <div class="box-header with-border">
            <h3 class="box-title">Penambahan</h3>

            <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool"
                        data-widget="collapse">
                    <i class="fa fa-plus"></i>
                </button>
            </div>
            <!-- /.box-tools -->
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <div class="row">
                <label for="lluas_tanah" class="control-label col-sm-4">Luas
                    Tanah</label>
                <div class="form-group col-sm-6">
                    <input type="text" class="form-control col-sm-3 simple-field-data-mask-reverse"  data-mask="#,###,###,###.00" data-mask-reverse="true" data-mask-maxlength="false" style="text-align:right" name="luas_tanah"
                           id="luas_tanah" ng-model="transaksi.luas_tanah"   ng-change="updateHargaTanah();updateTotalPenambahan();updateHargaKesepakatanPlusPenambahan();updateHargaTotal()" value={{luas_tanah}}>
                </div>
            </div>
            <div class="row">
                <label for="lharga_permeter" class="control-label col-sm-4">Harga/M2</label>
                <div class="form-group col-sm-6">
                    <input type="text" class="form-control col-sm-3 simple-field-data-mask-reverse"  data-mask="#,###,###,###" data-mask-reverse="true" data-mask-maxlength="false"  style="text-align:right" name="harga_permeter"
                           id="harga_permeter" ng-model="transaksi.harga_permeter"   ng-change="updateHargaTanah();updateTotalPenambahan();updateHargaKesepakatanPlusPenambahan();updateHargaTotal()" >
                </div>
            </div>            
            <div class="row">
                <label for="lnoppjb" class="control-label col-sm-4">Harga Tanah</label>
                <div class="form-group col-sm-6">
                    <input type="text" class="form-control col-sm-3"  style="text-align:right" name="harga_tanah" 
                           id="harga_tanah" ng-model="transaksi.harga_tanah" disabled>
                </div>
            </div>
            <div class="row">
                <label for="lpenambahan_lain" class="control-label col-sm-4">Penambahan     Lain</label>
                <div class="form-group col-sm-6">
                    <input type="text" class="form-control col-sm-3"  style="text-align:right" name="penambahan_lain"  id="penambahan_lain" ng-model="transaksi.penambahan_lain" value={{penambahan_lain}}>
                </div>
            </div>
            <div class="row">
                <label for="lharga_lain" class="control-label col-sm-4">Harga Lain</label>
                <div class="form-group col-sm-6">
                    <input type="text" class="form-control col-sm-3 simple-field-data-mask-reverse"  data-mask="#,###,###,###" data-mask-reverse="true" data-mask-maxlength="false"   style="text-align:right" name="harga_lain" 
                           id="harga_lain" ng-model="transaksi.harga_lain" ng-change="updateTotalPenambahan();updateHargaKesepakatanPlusPenambahan();updateHargaTotal()" >
                </div>
            </div>
            <div class="row">
                <label for="ltotal_penambahan" class="control-label col-sm-4">Total
                    Penambahan</label>
                <div class="form-group col-sm-6">
                    <input type="text" class="form-control col-sm-3" style="text-align:right" 
                           name="total_penambahan" id="total_penambahan" ng-model="transaksi.total_penambahan"
                           disabled>
                </div>
            </div>
        </div>
        <!-- /.box-body -->
    </div>
</div>
<!-- /.box -->