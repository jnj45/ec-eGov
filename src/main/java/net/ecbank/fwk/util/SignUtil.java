package net.ecbank.fwk.util;

import java.util.Map;

public class SignUtil {
	
	public static Map<String,Object> serverSign(String orgData, String probFilePath, boolean isPwdEnc, String pwdKey) {
		
		//String propertiesLocation = "C:/srm/workspace/ipro/src/main/webapp/WEB-INF/PKI4JSERVER/config/tradesign3280.properties";
		//boolean isPasswordEnc = true;		// 암호화 된 키 내부 복호화
		//String passwordKey = "ecbank1203";  //추후 프로퍼티에서 불러 올 것 복호화 키 값
		int certIndex = 0;
		
		Map<String, Object> result = PKCS7Sign.signObj(orgData, probFilePath, isPwdEnc, pwdKey, certIndex);
		
		return result;
	}
	
	public static Map<String,Object> serverAddSign(String orgData, String signedData, String probFilePath, boolean isPwdEnc, String pwdKey) {
		
		/*String orgData = (String) paramMap.get("ORG_DATA");
		String signData = (String) paramMap.get("SERVER_SIGNDATA");
		String propertiesLocation = "C:/srm/workspace/ipro/src/main/webapp/WEB-INF/PKI4JSERVER/config/tradesign3280.properties";
		boolean isPasswordEnc = true;		// 암호화 된 키 내부 복호화
		String passwordKey = "ecbank1203";  //추후 프로퍼티에서 불러 올 것 복호화 키 값
*/		int certIndex = 0;
		
		Map<String, Object> result = PKCS7Sign.addSignObj(orgData, signedData, probFilePath, isPwdEnc, pwdKey, certIndex);
		return result;
		
	}
	
	
	
}
