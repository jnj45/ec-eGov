/**
 * 
 */
package net.ecbank.fwk.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * MultipartFile 관련 유틸리티 클래스
 * 
 * @author LYJ
 * @since 2020. 4. 28.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 4. 28. LYJ - 최초 작성.
 * </pre>
 */
public class MultipartFileUtils {
	
	/**
	 * 멀티파트파일들 중에서 주어진 필드명(<input type="file" name="필드명"/>)에 해당하는 파일만 구한다.
	 * @param fileMap
	 * @param fieldName
	 * @return
	 */
	public static Map<String, MultipartFile> getMulipartFileByFieldName(Map<String, MultipartFile> fileMap, String fieldName){
		Map<String, MultipartFile> result = new HashMap<String, MultipartFile>();
		
		Set<Entry<String,MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile mf = entry.getValue();
			if (mf instanceof CommonsMultipartFile) {
				if (fieldName.equals(((CommonsMultipartFile)mf).getFileItem().getFieldName())) {
					result.put(entry.getKey(), entry.getValue());
				}
			}else {
				throw new RuntimeException("MultipartFile is not instance of CommonsMultipartFile.");
			}
		}
		return result;
	}
}
