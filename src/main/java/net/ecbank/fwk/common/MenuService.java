package net.ecbank.fwk.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import oracle.sql.ARRAY;

/**
 * 
 * @author bio1215
 *
 */
@Service
public class MenuService extends BaseService {
	
	private final static Logger log = LoggerFactory.getLogger(MenuService.class);
	
	@Autowired
	MenuDao menuDao;
	
	public List<Map<String, Object>> selectUserMenuList(){
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		List<Map<String, Object>> selectList = null;
		
		if(auth != null) {
			
			List<String> roleList = new ArrayList<String>();
			
			List<GrantedAuthority> authList = (List<GrantedAuthority>) auth.getAuthorities();
			
			for(int i=0;i<authList.size();i++) {
				roleList.add(authList.get(i).getAuthority());
			}
			
			paramMap.put("ROLE_LIST", roleList);
			
			selectList = menuDao.selectUserMenuList(paramMap);
			
		}
		
		return selectList;
	}

	
}
