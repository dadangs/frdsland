package com.hadepro.developer.controller.master;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hadepro.developer.controller.LocalDB;
import com.hadepro.developer.model.Developer;
import com.hadepro.developer.model.DummyMaster;
import com.hadepro.developer.model.Kategori;
import com.hadepro.developer.model.Perumahan;
import com.hadepro.developer.model.Produk;
import com.hadepro.developer.model.Proyek;
import com.hadepro.developer.model.Tenor;
import com.hadepro.developer.model.Tipe;
import com.hadepro.developer.vo.master.PerumahanListVO;
import com.hadepro.developer.vo.master.ProdukListVO;
import com.hadepro.developer.vo.master.ProyekListVO;

@Controller
@RequestMapping(value = "/protected/master/rumahs")
public class PerumahanController {
	@Autowired
    private HttpServletRequest request;
    
    private int maxResults;
    
    private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";
    
    @Autowired
    private MessageSource messageSource;
    
    @Resource(name="localDB")
    private LocalDB localDB;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome(HttpSession session) {
    	ModelAndView model = new ModelAndView("rumahsList");        
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
        ProdukListVO rumahList = listAll(page);
        addActionMessageToVO(rumahList, locale, messageKey, null);
        return returnListToUser(rumahList);
    }
    private ProdukListVO listAll(int page) {
    	maxResults = localDB.getMaxResultPage("maxCountPage");
      return localDB.getListAllProduk(page, maxResults);
    }
    private ProdukListVO addActionMessageToVO(ProdukListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    }
    private ResponseEntity<ProdukListVO> returnListToUser(ProdukListVO contactList) {
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/loadDeveloper", method = RequestMethod.GET, produces = "application/json")   
    public ResponseEntity<?> listDeveloper() {
    	List<Developer> List = localDB.getListDeveloper();
        return  new ResponseEntity<>(List, HttpStatus.OK);
    }   
    @RequestMapping(value = "/loadDeveloper/{id_developer}/proyek/", method = RequestMethod.GET, produces = "application/json")   
    public ResponseEntity<?> listProyek( @PathVariable("id_developer") int id_developer) {
    	List<DummyMaster> carabayarList = localDB.getListDummyProyekByDeveloper(id_developer);
        return  new ResponseEntity<>(carabayarList, HttpStatus.OK);
    }
    @RequestMapping(value = "/loadKategori", method = RequestMethod.GET, produces = "application/json")   
    public ResponseEntity<?> listKategori() {
    	List<Kategori> List =localDB.getListKategori();
    	
        return  new ResponseEntity<>(List, HttpStatus.OK);
    }  
    @RequestMapping(value = "/loadKategori/{id}/loadTipe", method = RequestMethod.GET, produces = "application/json")   
    public ResponseEntity<?> listTipe( @PathVariable("id") String id) {
    	List<Tipe> List =localDB.getListTipe(id);
    	
        return  new ResponseEntity<>(List, HttpStatus.OK);
    } 
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create(@ModelAttribute("contact") Produk contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) throws SQLException {
        localDB.insertProduk(contact);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.create.success");
        }

        return createListAllResponse(page, locale, "message.create.success");
    }
    private boolean isSearchActivated(String searchFor) {
        return !StringUtils.isEmpty(searchFor);
    }
    
    private ResponseEntity<?> search(String name, int page, Locale locale, String actionMessageKey) {
    	ProdukListVO contactListVO = localDB.findProdukByNameLike(page, maxResults, name);

        if (!StringUtils.isEmpty(actionMessageKey)) {
            addActionMessageToVO(contactListVO, locale, actionMessageKey, null);
        }

        Object[] args = {name};

        addSearchMessageToVO(contactListVO, locale, "message.search.for.active", args);

        return new ResponseEntity<ProdukListVO>(contactListVO, HttpStatus.OK);
    }
    
    
    private ProdukListVO addSearchMessageToVO(ProdukListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setSearchMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    }
    @RequestMapping(value = "/{id_produk}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("id_produk") int id_produk,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {


        try {
        	localDB.deleteProduk(id_produk);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
        }

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.delete.success");
        }

        return createListAllResponse(page, locale, "message.delete.success");
    }
    
    @RequestMapping(value = "/{id_produk}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id_produk") int contactId,
                                    @RequestBody Produk contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
    	int id = contact.getId_produk();
        if (contactId != id ) {
            return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
        }

        localDB.updateProduk(contact);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.update.success");
        }

        return createListAllResponse(page, locale, "message.update.success");
    }
    
    @RequestMapping(value="/importExcel", method = RequestMethod.POST, produces = "application/json")
    public ModelAndView importXls(@RequestParam("namafile") MultipartFile namafile,
    		@RequestParam(required = false) String searchFor,
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
            Locale locale) {
      int i = 0;	
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(namafile.getInputStream());
			// Creates a worksheet object representing the first sheet
			XSSFSheet worksheet = workbook.getSheetAt(0);
			// Reads the data in excel file until last row is encountered
			while (i <= worksheet.getLastRowNum()) {
				// Creates an object for the UserInfo Model
				Produk p = new Produk();
				// Creates an object representing a single row in excel
				XSSFRow row = worksheet.getRow(i++);
				// Sets the Read data to the model class
				p.setId_developer((int) row.getCell(0).getNumericCellValue());
				p.setId_proyek((int) row.getCell(1).getNumericCellValue());
				p.setBlok(row.getCell(2).getStringCellValue());
				p.setNounit(row.getCell(3).getStringCellValue());
				p.setId_kategori(row.getCell(4).getStringCellValue());
				p.setTipe_rumah(row.getCell(5).getStringCellValue());
				p.setLuas_tanah(row.getCell(6).getStringCellValue());
				// user.setInputDate(row.getCell(2).getDateCellValue());
				// persist data into database in here
				localDB.insertProduk(p);
			}
			workbook.close();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new ModelAndView("redirect:./");
    }
    
    @RequestMapping(value="/import", method = RequestMethod.POST)
    public ResponseEntity<?>  x( @RequestPart("file") MultipartFile file) {
    	 int i = 0;
    	 String r="sukses";
    	 try {
 			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
 			// Creates a worksheet object representing the first sheet
 			XSSFSheet worksheet = workbook.getSheetAt(0);
 			Produk p = new Produk();
 			// Reads the data in excel file until last row is encountered
 			while (i <= worksheet.getLastRowNum()) {
 				// Creates an object for the UserInfo Model
 				
 				// Creates an object representing a single row in excel
 				XSSFRow row = worksheet.getRow(i++);
 				// Sets the Read data to the model class
 				p.setId_developer((int) row.getCell(0).getNumericCellValue());
 				p.setId_proyek((int) row.getCell(1).getNumericCellValue());
 				p.setBlok(row.getCell(2).getStringCellValue());
 				p.setNounit(row.getCell(3).getStringCellValue());
 				p.setId_kategori(row.getCell(4).getStringCellValue());
 				p.setTipe_rumah(row.getCell(5).getStringCellValue());
 				p.setLuas_tanah(row.getCell(6).getStringCellValue());
 				// user.setInputDate(row.getCell(2).getDateCellValue());
 				// persist data into database in here
 				localDB.insertProduk(p);
 			}
 			workbook.close();
 			 
 		} catch (Exception e) {
 			r="gagal insert baris "+i;
 			e.printStackTrace();
 		}
    	 return new ResponseEntity(r, HttpStatus.OK);  
         
    }
   /* private ResponseEntity<?> createListAllResponse(int page, Locale locale, String messageKey) {
        PerumahanListVO rumahList = listAll(page);
        addActionMessageToVO(rumahList, locale, messageKey, null);
        return returnListToUser(rumahList);
    }
    
    private PerumahanListVO listAll(int page) {
    	maxResults = localDB.getMaxResultPage("maxCountPage");
      return localDB.getListAllPerumahan(page, maxResults);
    }
    
    private PerumahanListVO addActionMessageToVO(PerumahanListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    }
    
    private ResponseEntity<PerumahanListVO> returnListToUser(PerumahanListVO contactList) {
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create(@ModelAttribute("contact") Perumahan contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) throws SQLException {
        localDB.insertPerumahan(contact);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.create.success");
        }

        return createListAllResponse(page, locale, "message.create.success");
    }
    
    private boolean isSearchActivated(String searchFor) {
        return !StringUtils.isEmpty(searchFor);
    }
    
    private ResponseEntity<?> search(String name, int page, Locale locale, String actionMessageKey) {
        PerumahanListVO contactListVO = localDB.findPerumahanByNameLike(page, maxResults, name);

        if (!StringUtils.isEmpty(actionMessageKey)) {
            addActionMessageToVO(contactListVO, locale, actionMessageKey, null);
        }

        Object[] args = {name};

        addSearchMessageToVO(contactListVO, locale, "message.search.for.active", args);

        return new ResponseEntity<PerumahanListVO>(contactListVO, HttpStatus.OK);
    }
    
    private PerumahanListVO addSearchMessageToVO(PerumahanListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setSearchMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    }
    
    @RequestMapping(value = "/{id_proyek}/{id_perumahan}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id_proyek") String contactId,
    								@PathVariable("id_perumahan") String contactId2,
                                    @RequestBody Perumahan contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
    	String id = contact.getId_proyek();
    	String id2 = contact.getId_perumahan();
        if (!contactId.equals(id) && !contactId2.equals(id2)) {
            return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
        }

        localDB.updatePerumahan(contact);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.update.success");
        }

        return createListAllResponse(page, locale, "message.update.success");
    }
    
    @RequestMapping(value = "/{id_proyek}/{id_perumahan}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("id_proyek") String id_proyek,
    								@PathVariable("id_perumahan") String id_perumahan,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {


        try {
        	localDB.deletePerumahan(id_proyek,id_perumahan);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
        }

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.delete.success");
        }

        return createListAllResponse(page, locale, "message.delete.success");
    }*/
}
