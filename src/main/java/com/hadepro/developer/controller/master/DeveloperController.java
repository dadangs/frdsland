package com.hadepro.developer.controller.master;

import java.sql.SQLException;
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
import com.hadepro.developer.model.Developer;

import com.hadepro.developer.vo.master.DeveloperListVO;


@Controller
@RequestMapping(value = "/protected/master/developers") 
public class DeveloperController {
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
    	ModelAndView model = new ModelAndView("developersList");        
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
        DeveloperListVO proyekList = listAll(page);
        addActionMessageToVO(proyekList, locale, messageKey, null);
        return returnListToUser(proyekList);
    }
    
    private DeveloperListVO listAll(int page) {
    	maxResults = localDB.getMaxResultPage("maxCountPage");
      return localDB.getListAllDeveloper(page, maxResults);
    }
    
    private DeveloperListVO addActionMessageToVO(DeveloperListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    }
    private ResponseEntity<DeveloperListVO> returnListToUser(DeveloperListVO contactList) {
        return new ResponseEntity<>(contactList, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create(@ModelAttribute("contact") Developer contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) throws SQLException {
        localDB.insertDeveloper(contact);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.create.success");
        }

        return createListAllResponse(page, locale, "message.create.success");
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id") int contactId,
                                    @RequestBody Developer contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
    	int id = contact.getId();
        if (contactId != id) {
            return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
        }

        localDB.updateDeveloper(contact);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.update.success");
        }

        return createListAllResponse(page, locale, "message.update.success");
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("id") int id,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {


        try {
        	localDB.deleteDeveloper(id);
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
    	DeveloperListVO contactListVO = localDB.findDeveloperByNameLike(page, maxResults, name);

        if (!StringUtils.isEmpty(actionMessageKey)) {
            addActionMessageToVO(contactListVO, locale, actionMessageKey, null);
        }

        Object[] args = {name};

        addSearchMessageToVO(contactListVO, locale, "message.search.for.active", args);

        return new ResponseEntity<DeveloperListVO>(contactListVO, HttpStatus.OK);
    }
    
    private DeveloperListVO addSearchMessageToVO(DeveloperListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setSearchMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    } 
    
}
