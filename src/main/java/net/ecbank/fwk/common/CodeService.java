/**
 * 
 */
package net.ecbank.fwk.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdk.internal.org.jline.utils.Log;

/**
 * 공통 코드 조회 서비스
 * 
 * @author LYJ
 * @since 2020. 4. 21.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 4. 21. LYJ - 최초 작성.
 * </pre>
 */
@Service
public class CodeService extends BaseService {
	
	private final static Logger log = LoggerFactory.getLogger(CodeService.class);
	
	@Autowired
	CodeDao codeDao;
	
	/**
	 * 코드그룹의 사용중인 코드목록 조회
	 * @param codeGrp
	 * @return
	 */
	public List<Map<String, Object>> selectCodeList(String codeGrp){
		return codeDao.selectCodeList(codeGrp);
	}
	
	public List<Map<String, Object>> getCodeList(String codeGrp){
		return selectCodeList(codeGrp);
	}
	
	/**
	 * 코드조회
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> selectCode(String codeGrp, String code){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("CODE_GRP", codeGrp);
		paramMap.put("CODE", 	 code);
		return codeDao.selectCode(paramMap);
	}
	
	public Map<String, Object> getCode(String codeGrp, String code){
		return selectCode(codeGrp, code);
	}
}
