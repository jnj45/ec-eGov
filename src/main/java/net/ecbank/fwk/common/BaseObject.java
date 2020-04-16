package net.ecbank.fwk.common;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 모델에 해당하는 Java Bean 클래스들은 기본적으로 BaseObject를 상속받아야한다.<br/>
 * toString(), equals(), hashCode() 에 대한 재정의 리플렉팅을 자동으로 수행한다.
 */

/**
 * 
 * entity, dto, vo 클래스들이 상속하는 클래스
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
public class BaseObject implements Serializable {

	private static final long serialVersionUID = 8819880562867698806L;
	
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public boolean equals(Object o)
	{	
		return EqualsBuilder.reflectionEquals(this, 0);
	}
	
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
