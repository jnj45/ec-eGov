/**
 * 
 */
package net.ecbank.fwk.common;

import org.springframework.beans.factory.annotation.Autowired;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * DAO 클래스들이 상속하는 클래스
 * 
 * @author LYJ
 * @since 2020. 4. 16.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 * 부모클래스(EgovComAbstractDAO)에 mybatis sqlSession을 이용한 select, insert, update 등 기본 메소드 정의되어 있음.
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 4. 16. LYJ - 최초 작성.
 * </pre>
 */
public class BaseDao extends EgovComAbstractDAO {
	
	@Autowired
	protected PropertyService propertyService;
	
	@Autowired
	protected CodeService codeService;
}
