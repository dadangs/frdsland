/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadepro.developer.controller.master;


import com.hadepro.developer.controller.LocalDB;
import com.hadepro.developer.controller.Utils;
import com.hadepro.developer.model.Developer;
import com.hadepro.developer.model.ModelBayar;
import com.hadepro.developer.model.Proyek;
import com.hadepro.developer.vo.master.ProyekListVO;






import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */

@Controller
@RequestMapping(value = "/protected/master/proyeks")
public class ProyekController {
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
    	ModelAndView model = new ModelAndView("proyeksList");        
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
        ProyekListVO proyekList = listAll(page);
        addActionMessageToVO(proyekList, locale, messageKey, null);
        return returnListToUser(proyekList);
    }
    
    private ProyekListVO listAll(int page) {
    	maxResults = localDB.getMaxResultPage("maxCountPage");
      return localDB.getListAllProyek(page, maxResults);
    }
    
    private ProyekListVO addActionMessageToVO(ProyekListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    }
    private ResponseEntity<ProyekListVO> returnListToUser(ProyekListVO contactList) {
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create(@ModelAttribute("contact") Proyek contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) throws SQLException {
        localDB.insertProyek(contact);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.create.success");
        }

        return createListAllResponse(page, locale, "message.create.success");
    }
    
    @RequestMapping(value = "/{id_proyek}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id_proyek") int contactId,
                                    @RequestBody Proyek contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
    	Integer id = contact.getId_proyek();
        if (contactId != id) {
            return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
        }

        localDB.updateProyek(contact);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.update.success");
        }

        return createListAllResponse(page, locale, "message.update.success");
    }
    
    @RequestMapping(value = "/{id_proyek}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("id_proyek") String id_proyek,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {


        try {
        	localDB.deleteProyek(id_proyek);
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
        ProyekListVO contactListVO = localDB.findProyekByNameLike(page, maxResults, name);

        if (!StringUtils.isEmpty(actionMessageKey)) {
            addActionMessageToVO(contactListVO, locale, actionMessageKey, null);
        }

        Object[] args = {name};

        addSearchMessageToVO(contactListVO, locale, "message.search.for.active", args);

        return new ResponseEntity<ProyekListVO>(contactListVO, HttpStatus.OK);
    }
    
    private ProyekListVO addSearchMessageToVO(ProyekListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setSearchMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    } 
    
    @RequestMapping(value = "/loadDeveloper", method = RequestMethod.GET, produces = "application/json")   
    public ResponseEntity<?> listModelBayar() {
    	List<Developer> List = localDB.getListDeveloper();
        return  new ResponseEntity<>(List, HttpStatus.OK);
    }   
}
