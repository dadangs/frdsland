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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hadepro.developer.controller.LocalDB;
import com.hadepro.developer.vo.master.DeveloperListVO;
import com.hadepro.developer.vo.proses.RekapPiutangListVO;

@Controller
@RequestMapping(value = "/protected/laporan/rekap_piutangs") 
public class RekapPiutangController {
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
	    	ModelAndView model = new ModelAndView("piutangsList");
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
	        RekapPiutangListVO proyekList = listAll(page);
	        addActionMessageToVO(proyekList, locale, messageKey, null);
	        return returnListToUser(proyekList);
	    }
	    
	    private RekapPiutangListVO listAll(int page) {
	    	maxResults = localDB.getMaxResultPage("maxCountPage");
	      return localDB.getListRekapPiutang(page, maxResults);
	    }
	    
	    private RekapPiutangListVO addActionMessageToVO(RekapPiutangListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
	        if (StringUtils.isEmpty(actionMessageKey)) {
	            return contactListVO;
	        }

	        contactListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

	        return contactListVO;
	    }
	    private ResponseEntity<RekapPiutangListVO> returnListToUser(RekapPiutangListVO contactList) {
	        return new ResponseEntity<>(contactList, HttpStatus.OK);
	    }
}
