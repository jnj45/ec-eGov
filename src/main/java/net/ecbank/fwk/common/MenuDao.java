/**
 * 
 */
package net.ecbank.fwk.common;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * 메뉴정보 조회 dao
 * 
 * @author bio1215
 * @since 2020. 4. 23.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 4. 23. bio1215 - 최초 작성.
 * </pre>
 */
@Repository
public class MenuDao extends BaseDao {
	
	private static final Logger log = LoggerFactory.getLogger(MenuDao.class);
	
	/**
	 * 권한 별 메뉴 리스트
	 * @param paramMap {ROLE_LIST}
	 * @return
	 */
	/*@Cacheable("menuCache")*/
	public List<Map<String, Object>> selectUserMenuList(Map<String, Object> paramMap){
		return selectList("fwk.common.Menu.selectUserMenuList", paramMap);
	}
	
	@CacheEvict(value="menuCache", allEntries=true)
	public void clearMenuCache() {
		log.info("메뉴 캐쉬 초기화");
	}
}
