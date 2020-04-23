package net.ecbank.sample.dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import net.ecbank.fwk.common.BaseDao;

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
 *  
 * << 개정이력(Modification Information) >>
 *   2020. 4. 14. 홍길동 - 최초 생성
 * </pre>
 */
@Repository
public class SampleDao extends BaseDao {
	
	private static final Logger log = LoggerFactory.getLogger(SampleDao.class);
	
	/**
	 * 작가 목록 조회
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> selectAuthorList(Map<String, Object> paramMap){
		return selectList("fwk.sample.Sample.selectAuthorList", paramMap);
	}

	/**
	 * 작가 상세 조회
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> selectAuthor(Map<String, Object> paramMap) {
		return selectOne("fwk.sample.Sample.selectAuthor", paramMap);
	}
	
	/**
	 * 작가 책 목록
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> selectAuthorBookList(Map<String, Object> paramMap){
		return selectList("fwk.sample.Sample.selectAuthorBookList", paramMap);
	}
	
	/**
	 * 작가 정보 수정
	 * @param paramMap
	 * @return
	 */
	public int updateAuthor(Map<String, Object> paramMap) {
		return update("fwk.sample.Sample.updateAuthor", paramMap);
	}
	
	/**
	 * 책 등록
	 * @param paramMap
	 * @return
	 */
	public int insertBook(Map<String, Object> paramMap) {
		return insert("fwk.sample.Sample.insertBook", paramMap);
	}
	/**
	 * 책 수정
	 * @param paramMap
	 * @return
	 */
	public int updateBook(Map<String, Object> paramMap) {
		return update("fwk.sample.Sample.updateBook", paramMap);
	}
	/**
	 * 책 삭제
	 * @param paramMap
	 * @return
	 */
	public int deleteBook(Map<String, Object> paramMap) {
		return delete("fwk.sample.Sample.deleteBook", paramMap);
	}
	
	public int updateAuthorName(Map<String, Object> paramMap) {
		return update("fwk.sample.Sample.updateAuthorName", paramMap);
	}
}
