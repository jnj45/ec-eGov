package net.ecbank.fwk.util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

//import com.ipro.common.util.PropertiesUtil;

import tradesign.crypto.provider.JeTS;
import tradesign.pki.asn1.ASN1Exception;
import tradesign.pki.pkcs.PKCSException;
import tradesign.pki.pkix.SignedData;
import tradesign.pki.pkix.X509Certificate;
import tradesign.pki.util.JetsUtil;

@Service("PKCS7Sign")
public class PKCS7Sign{

	private static String _PROPERTIES_PATH		= "";//PropertiesUtil.getProperty("tradesign.path");
	
	public static Map<String, String> sign(String orgData, String propertiesLocation, boolean isPasswordEnc, String passwordKey, int certIndex){

		Map<String, String> result = new HashMap<String, String>();
		SignedData signedData = null;
		X509Certificate[] certInfos = null;
		String strSignedMsg = "";
		String strSignTime = "";
		String strVerifyMsg = "";
		List<String> certDns = null;
		List<BigInteger> certSns = null;
		byte[] signedMsg = null;
		byte[] veryfyMsg = null;

		try{

			// 인증서의 비밀번호를 암호화해서 사용할 경우
			if(isPasswordEnc){
				JeTS.setPBEPassword(passwordKey);
			}

			JeTS.installProvider(propertiesLocation);

			// 서명 암호화
			signedData = new SignedData(orgData.getBytes(), true);
			signedData.setIncludeSigningTime(true); // 서명시간을 넣기 위해서는 해당 값 세팅 필요
			signedData.setsignCert(JeTS.getServerSignCert(certIndex), JeTS.getServerSignPriKey(certIndex), JeTS.getServerSignKeyPassword(certIndex));
			signedMsg = signedData.sign();
			certInfos = signedData.verify();

			// 인증서 정보 추출
			certDns = new ArrayList<String>();
			certSns = new ArrayList<BigInteger>();
			for(X509Certificate certInfo : certInfos){
				certDns.add(certInfo.getSubjectDNStr());
				certSns.add(certInfo.getSerialNumber());
			}

			// 서명 데이터
			strSignedMsg = new String(JetsUtil.encodeBase64(signedMsg));

			// 서명 검증
			veryfyMsg = signedData.getContent();
			strVerifyMsg = new String(veryfyMsg);

			if(!orgData.equals(strVerifyMsg)){

				result.put("resultFlag", "E");
				result.put("resultMessage", "서명한 데이터와 원문 데이터가 일치하지 않습니다.");

			}else{

				// 서명시간 추출
				//strSignTime = DateUtil.getGtmTimeZoneToLocalTimeZone(signedData.getSigningTime().toString());

				result.put("resultFlag", "S");
				result.put("resultMessage", "정상적으로 서명되었습니다.");
				result.put("signedMsg", strSignedMsg);
				result.put("signTime", strSignTime);
				result.put("dn", certDns.get(certDns.size() - 1));
				result.put("sn", certSns.get(certSns.size() - 1).toString());

			}

		}catch(GeneralSecurityException | IOException | ASN1Exception | PKCSException e){
			e.printStackTrace();
			result.put("resultFlag", "E");
			result.put("resultMessage", "서명 중 오류가 발생하였습니다.[" + e.getMessage() + "]");
		}
		return result;
	}
	
	
	public static Map<String, Object> signObj(String orgData, String propertiesLocation, boolean isPasswordEnc, String passwordKey, int certIndex){

		Map<String, Object> result = new HashMap<String, Object>();
		SignedData signedData = null;
		X509Certificate[] certInfos = null;
		String strSignedMsg = "";
		String strSignTime = "";
		String strVerifyMsg = "";
		List<String> certDns = null;
		List<BigInteger> certSns = null;
		byte[] signedMsg = null;
		byte[] veryfyMsg = null;

		try{

			// 인증서의 비밀번호를 암호화해서 사용할 경우
			if(isPasswordEnc){
				JeTS.setPBEPassword(passwordKey);
			}

			JeTS.installProvider(propertiesLocation);

			// 서명 암호화
			signedData = new SignedData(orgData.getBytes(), true);
			signedData.setIncludeSigningTime(true); // 서명시간을 넣기 위해서는 해당 값 세팅 필요
			signedData.setsignCert(JeTS.getServerSignCert(certIndex), JeTS.getServerSignPriKey(certIndex), JeTS.getServerSignKeyPassword(certIndex));
			signedMsg = signedData.sign();
			certInfos = signedData.verify();

			// 인증서 정보 추출
			certDns = new ArrayList<String>();
			certSns = new ArrayList<BigInteger>();
			for(X509Certificate certInfo : certInfos){
				certDns.add(certInfo.getSubjectDNStr());
				certSns.add(certInfo.getSerialNumber());
			}

			// 서명 데이터
			strSignedMsg = new String(JetsUtil.encodeBase64(signedMsg));

			// 서명 검증
			veryfyMsg = signedData.getContent();
			strVerifyMsg = new String(veryfyMsg);

			if(!orgData.equals(strVerifyMsg)){

				result.put("resultFlag", "E");
				result.put("resultMessage", "서명한 데이터와 원문 데이터가 일치하지 않습니다.");

			}else{

				// 서명시간 추출
				//strSignTime = DateUtil.getGtmTimeZoneToLocalTimeZone(signedData.getSigningTime().toString());

				result.put("resultFlag", "S");
				result.put("resultMessage", "정상적으로 서명되었습니다.");
				result.put("signedMsg", strSignedMsg);
				result.put("signTime", strSignTime);
				result.put("dn", certDns.get(certDns.size() - 1));
				result.put("sn", certSns.get(certSns.size() - 1).toString());

			}

		}catch(GeneralSecurityException | IOException | ASN1Exception | PKCSException e){
			e.printStackTrace();
			result.put("resultFlag", "E");
			result.put("resultMessage", "서명 중 오류가 발생하였습니다.[" + e.getMessage() + "]");
		}
		return result;
	}

	public static Map<String, String> addSign(String orgData, String firstSignedData, String propertiesLocation, boolean isPasswordEnc, String passwordKey, int certIndex){

		Map<String, String> result = new HashMap<String, String>();
		SignedData signedData = null;
		X509Certificate[] certInfos = null;
		String strSignedMsg = "";
		String strSignTime = "";
		String strVerifyMsg = "";
		List<String> certDns = null;
		List<BigInteger> certSns = null;
		byte[] signedMsg = null;
		byte[] veryfyMsg = null;

		try{

			// 인증서의 비밀번호를 암호화해서 사용할 경우
			if(isPasswordEnc){
				JeTS.setPBEPassword(passwordKey);
			}

			JeTS.installProvider(propertiesLocation);

			// 서명 암호화
			signedData = new SignedData();
			signedData.setIncludeSigningTime(true); // 서명시간을 넣기 위해서는 해당 값 세팅 필요

			signedMsg = signedData.addSigner(JetsUtil.base64ToBytes(firstSignedData), JeTS.getServerSignCert(certIndex), JeTS.getServerSignPriKey(certIndex), JeTS.getServerSignKeyPassword(certIndex));
			certInfos = signedData.verify();

			// 인증서 정보 추출
			certDns = new ArrayList<String>();
			certSns = new ArrayList<BigInteger>();
			for(X509Certificate certInfo : certInfos){
				certDns.add(certInfo.getSubjectDNStr());
				certSns.add(certInfo.getSerialNumber());
			}

			// 서명 데이터
			strSignedMsg = new String(JetsUtil.encodeBase64(signedMsg));

			// 서명 검증
			veryfyMsg = signedData.getContent();
			strVerifyMsg = new String(veryfyMsg);

			if(!orgData.equals(strVerifyMsg)){

				result.put("resultFlag", "E");
				result.put("resultMessage", "서명한 데이터와 원문 데이터가 일치하지 않습니다.");

			}else{
				// 서명시간 추출
				//strSignTime = DateUtil.getGtmTimeZoneToLocalTimeZone(signedData.getSigningTime().toString());

				result.put("resultFlag", "S");
				result.put("resultMessage", "정상적으로 서명되었습니다.");
				result.put("signedMsg", strSignedMsg);
				result.put("signTime", strSignTime);
				result.put("dn", certDns.get(certDns.size() - 1));
				result.put("sn", certSns.get(certSns.size() - 1).toString());
			}

		}catch(GeneralSecurityException | IOException | ASN1Exception | PKCSException e){
			e.printStackTrace();
			result.put("resultFlag", "E");
			result.put("resultMessage", "서명 중 오류가 발생하였습니다.[" + e.getMessage() + "]");
		}

		return result;
	}
	
	public static Map<String, Object> addSignObj(String orgData, String firstSignedData, String propertiesLocation, boolean isPasswordEnc, String passwordKey, int certIndex){

		Map<String, Object> result = new HashMap<String, Object>();
		SignedData signedData = null;
		X509Certificate[] certInfos = null;
		String strSignedMsg = "";
		String strSignTime = "";
		String strVerifyMsg = "";
		List<String> certDns = null;
		List<BigInteger> certSns = null;
		byte[] signedMsg = null;
		byte[] veryfyMsg = null;

		try{

			// 인증서의 비밀번호를 암호화해서 사용할 경우
			if(isPasswordEnc){
				JeTS.setPBEPassword(passwordKey);
			}

			JeTS.installProvider(propertiesLocation);

			// 서명 암호화
			signedData = new SignedData();
			signedData.setIncludeSigningTime(true); // 서명시간을 넣기 위해서는 해당 값 세팅 필요

			signedMsg = signedData.addSigner(JetsUtil.base64ToBytes(firstSignedData), JeTS.getServerSignCert(certIndex), JeTS.getServerSignPriKey(certIndex), JeTS.getServerSignKeyPassword(certIndex));
			certInfos = signedData.verify();

			// 인증서 정보 추출
			certDns = new ArrayList<String>();
			certSns = new ArrayList<BigInteger>();
			for(X509Certificate certInfo : certInfos){
				certDns.add(certInfo.getSubjectDNStr());
				certSns.add(certInfo.getSerialNumber());
			}

			// 서명 데이터
			strSignedMsg = new String(JetsUtil.encodeBase64(signedMsg));

			// 서명 검증
			veryfyMsg = signedData.getContent();
			strVerifyMsg = new String(veryfyMsg);

			if(!orgData.equals(strVerifyMsg)){

				result.put("resultFlag", "E");
				result.put("resultMessage", "서명한 데이터와 원문 데이터가 일치하지 않습니다.");

			}else{
				// 서명시간 추출
				//strSignTime = DateUtil.getGtmTimeZoneToLocalTimeZone(signedData.getSigningTime().toString());

				result.put("resultFlag", "S");
				result.put("resultMessage", "정상적으로 서명되었습니다.");
				result.put("signedMsg", strSignedMsg);
				result.put("signTime", strSignTime);
				result.put("dn", certDns.get(certDns.size() - 1));
				result.put("sn", certSns.get(certSns.size() - 1).toString());
			}

		}catch(GeneralSecurityException | IOException | ASN1Exception | PKCSException e){
			e.printStackTrace();
			result.put("resultFlag", "E");
			result.put("resultMessage", "서명 중 오류가 발생하였습니다.[" + e.getMessage() + "]");
		}

		return result;
	}

	public static void main(String args[]){
		String orgData = "TEST DATA";
		String propertiesLocation = "C:/srm/workspace/ipro/src/main/webapp/PKI4JSERVER/config/tradesign3280.properties";
		boolean isPasswordEnc = true;
		String passwordKey = "ecbank1203";
		String signData = "";
		String addSignData = "";
		int certIndex = 0;

		Map<String, String> result = sign(orgData, propertiesLocation, isPasswordEnc, passwordKey, certIndex);

		for(Entry<String, String> entry : result.entrySet()){
			System.out.println(entry.getKey() + "=" + entry.getValue());
			if(entry.getKey().toString().equals("signedMsg")){
				signData = entry.getValue();
			}
		}

		System.out.println("signData : " + signData);

		result = addSign(orgData, signData, propertiesLocation, isPasswordEnc, passwordKey, certIndex);

		for(Entry<String, String> entry : result.entrySet()){
//			System.out.println(entry.getKey() + "=" + entry.getValue());
			
			if(entry.getKey().toString().equals("signedMsg")){
				addSignData = entry.getValue();
			}
		}
		
		System.out.println("addSignData : " + addSignData);
	}

}
