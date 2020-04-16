package net.ecbank.sample.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import net.ecbank.sample.dao.SampleDao;

/**
 * 항상 새로운 트랜잭션이 필요한 서비스
 * @author LYJ
 *
 */
@Service
public class NewTransactionService extends EgovAbstractServiceImpl {
	
	private final Logger log = LoggerFactory.getLogger(NewTransactionService.class);
	
	@Autowired
	SampleDao sampleDao;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void newTranServiceTest() {
		log.debug("newTranServiceTest =======================================================");
		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("AUTHOR_ID", 2);
		testMap.put("NAME",      "newTran");
		
		sampleDao.updateAuthorName(testMap);
	}
}
