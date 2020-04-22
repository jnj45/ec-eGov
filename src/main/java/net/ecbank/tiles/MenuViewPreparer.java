package net.ecbank.tiles;

import java.util.List;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;

public class MenuViewPreparer implements ViewPreparer {
	
	@Autowired
    private EgovMenuManageService menuManageService;
	
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		System.out.println(loginVO.getId());
		
		System.out.println("execute Preparer================== bio");
		
	}

}
