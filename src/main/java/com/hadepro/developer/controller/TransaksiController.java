package com.hadepro.developer.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hadepro.developer.controller.LocalDB;
import com.hadepro.developer.dao.SalesDAO;
import com.hadepro.developer.model.Konsumen;
import com.hadepro.developer.model.Transaksi;
import com.hadepro.developer.service.DownloadService;
import com.hadepro.developer.vo.master.ProdukListVO;
import com.hadepro.developer.vo.proses.TransaksiListVO;


@Controller
@RequestMapping(value = "/protected/proses/transaksis")
public class TransaksiController {
	@Resource(name="localDB")
    private LocalDB localDB;
	
	@Autowired
    private MessageSource messageSource;
	private int maxResults;
	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="downloadService")
	private DownloadService downloadService;
	
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome(HttpSession session) {
    	ModelAndView model = new ModelAndView("transaksisList");
    	return model;
    }
    
    @RequestMapping(value = "/produk_avail", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
       
        return createListAllResponse(page, locale);
    }

    @RequestMapping(value = "/transaksi_avail", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAlltransaksi(@RequestParam int page, Locale locale) {
       
        return createListAllResponseTransaksi(page, locale);
    }
    
    private ResponseEntity<?> createListAllResponse(int page, Locale locale) {
        return createListAllResponse(page, locale, null);
    }
    
    private ResponseEntity<?> createListAllResponseTransaksi(int page, Locale locale) {
        return createListAllResponseTransaksi(page, locale, null);
    }
    private ResponseEntity<?> createListAllResponse(int page, Locale locale, String messageKey) {
        ProdukListVO rumahAvailList = listAll(page);
        addActionMessageToVO(rumahAvailList, locale, messageKey, null);
        return returnListToUser(rumahAvailList);
    }
    
    private ResponseEntity<?> createListAllResponseTransaksi(int page, Locale locale, String messageKey) {
        TransaksiListVO rumahAvailList = listAllTransaksi(page);
        addActionMessageToVOTransaksi(rumahAvailList, locale, messageKey, null);
        return returnListToUserTransaksi(rumahAvailList);
    }
    
    private ProdukListVO addActionMessageToVO(ProdukListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    }
    
    private TransaksiListVO addActionMessageToVOTransaksi(TransaksiListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    }
    
    
    private ProdukListVO listAll(int page) {
    	maxResults = localDB.getMaxResultPage("maxCountPage");
      return localDB.getListAvailProduk(page, maxResults);
    }
    
    private TransaksiListVO listAllTransaksi(int page) {
    	maxResults = localDB.getMaxResultPage("maxCountPage");
      return localDB.getListTransaksi(0,page, maxResults);
    }

    private ResponseEntity<ProdukListVO> returnListToUser(ProdukListVO contactList) {
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }
    
    private ResponseEntity<TransaksiListVO> returnListToUserTransaksi(TransaksiListVO contactList) {
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)     
    public ResponseEntity<?>  create(@ModelAttribute("transaksi") Transaksi transaksi) throws SQLException,  ServletException, IOException,  ClassNotFoundException, JRException {
   	 //logger.debug("Received request to download Excel report");		
        int r = localDB.insertTransaksi(transaksi);
        return new ResponseEntity<>(r,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/download/pdf", method = RequestMethod.GET)
    public ModelAndView doSalesReportPDF(ModelAndView modelAndView) 
		 {
		logger.debug("Received request to download PDF report");
		
		// Retrieve our data from a custom data provider
		// Our data comes from a DAO layer
		SalesDAO dataprovider = new SalesDAO();
		
		// Assign the datasource to an instance of JRDataSource
		// JRDataSource is the datasource that Jasper understands
		// This is basically a wrapper to Java's collection classes
		JRDataSource datasource  = dataprovider.getDataSource();
		
		// In order to use Spring's built-in Jasper support, 
		// We are required to pass our datasource as a map parameter
		// parameterMap is the Model of our application
		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", datasource);
		
		// pdfReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		 modelAndView = new ModelAndView("pdfReport", parameterMap);
		
		// Return the View and the Model combined
		return modelAndView;
	}
    
    @RequestMapping(value = "/jrreport", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) throws JRException {
    	JRDataSource jrDatasource;
    	StudentDataSource dsStudent =  new StudentDataSource();
		jrDatasource = dsStudent.create(null);
		model.addAttribute("datasource", jrDatasource);
		model.addAttribute("format", "pdf");
		return "multiViewReport";
	}

}
