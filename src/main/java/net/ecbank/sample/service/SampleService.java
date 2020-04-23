package net.ecbank.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import net.ecbank.fwk.common.BaseService;
import net.ecbank.fwk.common.IdGnrStrategy;
import net.ecbank.fwk.exception.BizRuntimeException;
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
@Transactional(rollbackFor=Exception.class)
public class SampleService extends BaseService {

	private static final Logger log = LoggerFactory.getLogger(SampleService.class);

	@Autowired
	private SampleDao sampleDao;
	
	@Resource(name="bookIdGnrService")
	private EgovIdGnrService bookIdGnrService;
	
	@Autowired
	private NewTransactionService newTransactionService;
	
	/**
	 * 작가 목록 조회
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> selectAuthorList(Map<String, Object> paramMap) {
		return sampleDao.selectAuthorList(paramMap);
	}
	
	/**
	 * 작가 상세 조회
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> selectAuthor(Map<String, Object> paramMap) {
		return sampleDao.selectAuthor(paramMap);
	}
	
	/**
	 * 작가 책 목록 조회
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> selectAuthorBookList(Map<String, Object> paramMap) {
		return sampleDao.selectAuthorBookList(paramMap);
	}
	
	/**
	 * 작가 정보 수정
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> updateAuthor(Map<String, Object> paramMap) throws FdlException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//작가정보 update
		int insertedCnt = sampleDao.updateAuthor(paramMap);
		
		//책 목록 저장
		if (paramMap.get("BOOK_LIST") != null) {
			//책 그리드 데이타
			Map<String, Object> bookList = (Map<String, Object>) paramMap.get("BOOK_LIST");
			//신규 데이타
			if (bookList.get("CREATED")!=null) {
				List<Map<String, Object>> listMap = (List<Map<String, Object>>)bookList.get("CREATED");
				for (Map<String, Object> map : listMap) {
					//ID채번
					long bookId = bookIdGnrService.getNextLongId();
					map.put("BOOK_ID", bookId);
					
					sampleDao.insertBook(map);
				}
			}
			//수정 데이타
			if (bookList.get("UPDATED")!=null) {
				List<Map<String, Object>> listMap = (List<Map<String, Object>>)bookList.get("UPDATED");
				for (Map<String, Object> map : listMap) {
					sampleDao.updateBook(map);
				}
			}
			//삭제 데이타
			if (bookList.get("DELETED")!=null) {
				List<Map<String, Object>> listMap = (List<Map<String, Object>>)bookList.get("DELETED");
				for (Map<String, Object> map : listMap) {
					sampleDao.deleteBook(map);
				}
			}
		}
		
		return resultMap; 
	}
	
	public int testUpdateAuthorName(Map<String, Object> paramMap){
		log.debug("called testUpdateAuthorName ==========================================================================================");
		int r = sampleDao.updateAuthorName(paramMap);
		
		if ("3".equals(MapUtils.getString(paramMap, "AUTHOR_ID"))) {
			throw new BizRuntimeException(messageSource, "errors.invalid", new String[]{"3"} );
		}
		return r;
	}
	
	public void testTransaction() {
		//newTranServiceTest() 는 항상 새로운 트랜잭션으로 실행하므로 이후 예외와 상관없이 commit
		//spring aop는 proxy 기반이므로 다른 빈의 required_new 메소드를 호출해야만 new transaction이 생성됨.
		newTransactionService.newTranServiceTest();
		
		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("AUTHOR_ID", 3);
		testMap.put("NAME",      "NAME3333");
		testUpdateAuthorName(testMap); //예외발생
	}
}
