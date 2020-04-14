package net.ecbank.sample.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import net.ecbank.sample.dao.SampleDao;

/**
 * 샘플 서비스
 * 
 * @author LYJ
 * @since 2020. 4. 14.
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 상세설명 >>
 * 추가설명이 필요하면 기술.
 *  
 * << 개정이력(Modification Information) >>
 *   2020. 4. 14. 홍길동 - 최초 생성
 *   2020. 4. 14. 홍길동 - 수정내용 기술.
 *      </pre>
 */
@Service
public class SampleService extends EgovAbstractServiceImpl {

	private final Logger log = LoggerFactory.getLogger(SampleService.class);

	@Autowired
	SampleDao sampleDao;

	public List<Map<String, Object>> selectAuthorList(Map<String, Object> paramMap) {
		log.debug("paramMap======={}", paramMap);
		return sampleDao.selectAuthorList(paramMap);
	}
}
