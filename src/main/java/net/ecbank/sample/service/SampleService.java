package net.ecbank.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ecbank.fwk.common.BaseService;
import net.ecbank.sample.dao.SampleDao;

/**
 * 샘플 서비스
 * 
 * @author LYJ
 * @since 2020. 4. 14.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 * 추가설명이 필요하면 기술.
 *  
 * << 개정이력(Modification Information) >>
 *   2020. 4. 14. 홍길동 - 최초 생성
 *   2020. 4. 14. 홍길동 - 수정내용 기술.
 * </pre>
 */
@Service
@Transactional
public class SampleService extends BaseService {

	private final Logger log = LoggerFactory.getLogger(SampleService.class);

	@Autowired
	SampleDao sampleDao;
	
	@Autowired
	NewTransactionService newTransactionService;
	
	public List<Map<String, Object>> selectAuthorList(Map<String, Object> paramMap) {
		return sampleDao.selectAuthorList(paramMap);
	}
	
	public int testUpdateAuthorName(Map<String, Object> paramMap) {
		log.debug("called testUpdateAuthorName ==========================================================================================");
		int r = sampleDao.updateAuthorName(paramMap);
		
		if ("3".equals(MapUtils.getString(paramMap, "AUTHOR_ID"))) {
			throw new RuntimeException("테스트 예외11");
		}
		return r;
	}
	
	public void testTransaction() {
		//항상 새로운 트랜잭션으로 실행하므로 이후 예외와 상관없이 commit
		//spring aop는 proxy 기반이므로 다른 빈의 required_new 메소드를 호출해야만 new transaction이 생성됨.
		newTransactionService.newTranServiceTest();
		
		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("AUTHOR_ID", 3);
		testMap.put("NAME",      "NAME3333");
		testUpdateAuthorName(testMap); //예외발생
	}
}
