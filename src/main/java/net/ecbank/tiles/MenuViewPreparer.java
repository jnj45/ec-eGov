package net.ecbank.tiles;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

public class MenuViewPreparer implements ViewPreparer {
	
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		
		System.out.println("execute Preparer================== bio");
		
	}

}
