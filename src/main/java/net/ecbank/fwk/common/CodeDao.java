/**
 * 
 */
package net.ecbank.fwk.common;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * 공통코드 조회 dao
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
@Repository
public class CodeDao extends BaseDao {
	
	/**
	 * 코드그룹의 사용중인 코드목록 조회.
	 * @param codeGrp
	 * @return
	 */
	//@Cacheable
	public List<Map<String, Object>> selectCodeList(String codeGrp){
		return selectList("fwk.common.Code.selectCodeList", codeGrp);
	}
	
	/**
	 * 코드조회
	 * @param paramMap {CODE_GRP, CODE}
	 * @return
	 */
	//@Cacheable
	public Map<String, Object> selectCode(Map<String, Object> paramMap){
		return selectOne("fwk.common.Code.selectCode", paramMap);
	}
}
