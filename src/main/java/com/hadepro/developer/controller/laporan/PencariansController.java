package com.hadepro.developer.controller.laporan;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hadepro.developer.controller.LocalDB;
import com.hadepro.developer.vo.master.ProyekListVO;
import com.hadepro.developer.vo.proses.PembayaranListVO;

@Controller
@RequestMapping(value = "/protected/laporan/pencarians") 
public class PencariansController {
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
	    	ModelAndView model = new ModelAndView("pencariansList");
	    	return model;
	 }
	 
	 @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
	    public ResponseEntity<?> search(@PathVariable("name") String name,
	                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
	                                    Locale locale) {
	        return search(name, page, locale, null);
	    }
	 
	 private ResponseEntity<?> search(String name, int page, Locale locale, String actionMessageKey) {
		 int searchBy = 1;
		 maxResults = localDB.getMaxResultPage("maxCountPage");
		 PembayaranListVO contactListVO = localDB.findPembayaranBy(page, maxResults, searchBy, name);

	        if (!StringUtils.isEmpty(actionMessageKey)) {
	            addActionMessageToVO(contactListVO, locale, actionMessageKey, null);
	        }

	        Object[] args = {name};

	        addSearchMessageToVO(contactListVO, locale, "message.search.for.active", args);

	        return new ResponseEntity<PembayaranListVO>(contactListVO, HttpStatus.OK);
	    }
	 private PembayaranListVO addActionMessageToVO(PembayaranListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
	        if (StringUtils.isEmpty(actionMessageKey)) {
	            return contactListVO;
	        }

	        contactListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

	        return contactListVO;
	    }
	 private PembayaranListVO addSearchMessageToVO(PembayaranListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
	        if (StringUtils.isEmpty(actionMessageKey)) {
	            return contactListVO;
	        }

	        contactListVO.setSearchMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

	        return contactListVO;
	    } 
}
