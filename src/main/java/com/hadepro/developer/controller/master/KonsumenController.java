package com.hadepro.developer.controller.master;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.servlet.ModelAndView;

import com.hadepro.developer.controller.LocalDB;
import com.hadepro.developer.model.Konsumen;
import com.hadepro.developer.model.ModelBayar;
import com.hadepro.developer.model.Tenor;
import com.hadepro.developer.vo.master.KonsumenListVO;

@Controller
@RequestMapping(value = "/protected/master/konsumens")
public class KonsumenController {
    @Autowired
    private HttpServletRequest request;
    
    //@Value("3")
    private int maxResults;
    
    private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";
    
    @Autowired
    private MessageSource messageSource;
    
    @Resource(name="localDB")
    private LocalDB localDB;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome(HttpSession session) {
    	ModelAndView model = new ModelAndView("konsumensList");        
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
        KonsumenListVO proyekList = listAll(page);
        addActionMessageToVO(proyekList, locale, messageKey, null);
        return returnListToUser(proyekList);
    }
    
    private KonsumenListVO listAll(int page) {
    	maxResults = localDB.getMaxResultPage("maxCountPage");
      return localDB.getListAllKonsumen(page, maxResults);
    }
    
    private KonsumenListVO addActionMessageToVO(KonsumenListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    }
    private ResponseEntity<KonsumenListVO> returnListToUser(KonsumenListVO contactList) {
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create(@ModelAttribute("contact") Konsumen contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) throws SQLException {
        localDB.insertKonsumen(contact);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.create.success");
        }

        return createListAllResponse(page, locale, "message.create.success");
    }
    
    @RequestMapping(value = "/{noktp}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("noktp") String contactId,
                                    @RequestBody Konsumen contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
    	String id = contact.getNoktp();
        if (!contactId.equals(id)) {
            return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
        }

        localDB.updateKonsumen(contact);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.update.success");
        }

        return createListAllResponse(page, locale, "message.update.success");
    }
    
    @RequestMapping(value = "/{noktp}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("noktp") String noktp,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {


        try {
        	localDB.deleteKonsumen(noktp);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
        }

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.delete.success");
        }

        return createListAllResponse(page, locale, "message.delete.success");
    }

     
    private boolean isSearchActivated(String searchFor) {
        return !StringUtils.isEmpty(searchFor);
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> search(@PathVariable("name") String name,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
        return search(name, page, locale, null);
    }
    
    private ResponseEntity<?> search(String name, int page, Locale locale, String actionMessageKey) {
        KonsumenListVO contactListVO = localDB.findKonsumenByNameLike(page, maxResults, name);

        if (!StringUtils.isEmpty(actionMessageKey)) {
            addActionMessageToVO(contactListVO, locale, actionMessageKey, null);
        }

        Object[] args = {name};

        addSearchMessageToVO(contactListVO, locale, "message.search.for.active", args);

        return new ResponseEntity<KonsumenListVO>(contactListVO, HttpStatus.OK);
    }
    
    private KonsumenListVO addSearchMessageToVO(KonsumenListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setSearchMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    } 
    
    @RequestMapping(value = "/carabayar", method = RequestMethod.GET, produces = "application/json")   
    public ResponseEntity<?> listModelBayar() {
    	List<ModelBayar> carabayarList = localDB.getListModelBayar();
        return  new ResponseEntity<>(carabayarList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/carabayar/{modelbayar}/tenor/", method = RequestMethod.GET, produces = "application/json")   
    public ResponseEntity<?> listTenor( @PathVariable("modelbayar") int modelbayar) {
    	List<Tenor> carabayarList = localDB.getListTenor(modelbayar);
        return  new ResponseEntity<>(carabayarList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/carabayardp", method = RequestMethod.GET, produces = "application/json")   
    public ResponseEntity<?> listModelBayarDP() {
    	List<ModelBayar> carabayarList = localDB.getListModelBayarDP();
        return  new ResponseEntity<>(carabayarList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/carabayardp/{modelbayar}/tenordp/", method = RequestMethod.GET, produces = "application/json")   
    public ResponseEntity<?> listTenorDP( @PathVariable("modelbayar") int modelbayar) {
    	List<Tenor> carabayarList = localDB.getListTenorDP(modelbayar);
        return  new ResponseEntity<>(carabayarList, HttpStatus.OK);
    }

}
