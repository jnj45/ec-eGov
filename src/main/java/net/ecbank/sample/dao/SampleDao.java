package net.ecbank.sample.dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 샘플 DAO
 *
 * @author 홍길동
 * @since 2020. 4. 14.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 * 추가설명이 필요하면 기술.
 * 부모클래스(EgovComAbstractDAO)에 mybatis sqlSession을 이용한 select, insert, update 등 기본 메소드 정의되어 있음.
 *  
 * << 개정이력(Modification Information) >>
 *   2020. 4. 14. 홍길동 - 최초 생성
 *   2020. 4. 14. 홍길동 - 수정내용 기술.
 * </pre>
 */
@Repository
public class SampleDao extends EgovComAbstractDAO {
	
	private final Logger log = LoggerFactory.getLogger(SampleDao.class);
	
	/**
	 * 작가 목록 조회
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> selectAuthorList(Map<String, Object> paramMap){
		log.debug("paramMap:{}", paramMap);
		return selectList("fwk.sample.Sample.selectAuthorList", paramMap);
	}
	
}
