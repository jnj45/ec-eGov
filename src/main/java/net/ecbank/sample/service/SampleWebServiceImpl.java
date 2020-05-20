/**
 * 
 */
package net.ecbank.sample.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.CollectionUtils;

/**
 * 클래스명
 * 
 * @author I21362
 * @since 2020. 5. 18.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 5. 18. I21362 - 최초 작성.
 * </pre>
 */
@WebService(endpointInterface="net.ecbank.sample.service.SampleWebService")
public class SampleWebServiceImpl implements SampleWebService {
	
	@Override
	public String sayHi(String text) {
		System.out.println("sayHi called");
		return "Hello " + text;
	}

	@Override
	public AuthorDto findAuthor(AuthorDto authorDto) {
		authorDto.setAuthorHistory(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		return authorDto;
	}
	
	@Override
	public List<AuthorDto> findAuthorList(AuthorDto authorDto) {
		List<AuthorDto> list = new ArrayList<AuthorDto>();
		
		authorDto.setAuthorHistory(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		list.add(authorDto);
		
		AuthorDto a1 = new AuthorDto();
		a1.setAuthorId(2);
		a1.setAuthorName("22");
		a1.setAuthorHistory(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		list.add(a1);
		
		return list;
	}
	
}
