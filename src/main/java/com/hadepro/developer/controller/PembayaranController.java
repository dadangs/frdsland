package com.hadepro.developer.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hadepro.developer.model.Pembayaran;
import com.hadepro.developer.model.Transaksi;
import com.hadepro.developer.service.DownloadService;
import com.hadepro.developer.vo.master.ProdukListVO;
import com.hadepro.developer.vo.proses.PembayaranListVO;
import com.hadepro.developer.vo.proses.TransaksiListVO;


@Controller
@RequestMapping(value = "/protected/proses/pembayarans")
public class PembayaranController {
	@Resource(name="localDB")
    private LocalDB localDB;
	
	@Autowired
    private MessageSource messageSource;
	private int maxResults;
	protected static Logger logger = Logger.getLogger("controller");
	
	
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome(HttpSession session) {
    	ModelAndView model = new ModelAndView("bayarAngsuransList");
    	return model;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
       
        return createListAllResponse(page, locale);
    }
    private ResponseEntity<?> createListAllResponse(int page, Locale locale) {
        return createListAllResponse(page, locale, null);
    }
    private ResponseEntity<?> createListAllResponse(int page, Locale locale, String messageKey) {
        TransaksiListVO rumahAvailList = listAll(page);
        addActionMessageToVO(rumahAvailList, locale, messageKey, null);
        return returnListToUser(rumahAvailList);
    }
    private TransaksiListVO listAll(int page) {
    	maxResults = localDB.getMaxResultPage("maxCountPage");
    	int lunas= 0;
      return localDB.getListTransaksi(lunas , page, maxResults);
    } 
    private TransaksiListVO addActionMessageToVO(TransaksiListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    }
    private ResponseEntity<TransaksiListVO> returnListToUser(TransaksiListVO contactList) {
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    
    public ResponseEntity<?>  create(@ModelAttribute("transaksi") Pembayaran transaksi) throws SQLException,  ServletException, IOException,  ClassNotFoundException, JRException {
   	 //logger.debug("Received request to download Excel report");		
        int r = localDB.UpdateAngsuran(transaksi);
        return new ResponseEntity<>(r,HttpStatus.OK);
    }
    
    @RequestMapping(value="/listBayar",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listBayar(@RequestParam int page, Locale locale) {
       
        return createListAllResponseListBayar(page, locale);
    }
    private ResponseEntity<?> createListAllResponseListBayar(int page, Locale locale) {
        return createListAllResponseListBayar(page, locale, null);
    }
    private ResponseEntity<?> createListAllResponseListBayar(int page, Locale locale, String messageKey) {
        PembayaranListVO rumahAvailList = listAllBayar(page);
        addActionMessageToVOBayar(rumahAvailList, locale, messageKey, null);
        return returnListToUserBayar(rumahAvailList);
    }
    
    private PembayaranListVO listAllBayar(int page) {
    	maxResults = localDB.getMaxResultPage("maxCountPage");
    	int lunas= 0;
      return localDB.getListPembayaran(page, maxResults);
    } 
    
    private PembayaranListVO addActionMessageToVOBayar(PembayaranListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    }
    private ResponseEntity<PembayaranListVO> returnListToUserBayar(PembayaranListVO contactList) {
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }

}
