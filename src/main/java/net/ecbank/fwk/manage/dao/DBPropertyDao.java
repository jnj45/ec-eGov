/**
 * 
 */
package net.ecbank.fwk.manage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.ecbank.fwk.common.BaseDao;

/**
 * 클래스명
 * 
 * @author LYJ
 * @since 2020. 4. 16.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 4. 16. LYJ - 최초 작성.
 * </pre>
 */
@Repository
public class DBPropertyDao extends BaseDao {
	
	public List<Map<String, String>> selectValidAllPropertyListByProfile(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("PROFILE", "");
		
		return selectList("fwk.manage.DBProperty.selectValidAllPropertyListByProfile", paramMap);
	}
}
