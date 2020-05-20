package net.ecbank.sample.service;

/**
 * 
 * 클래스명
 * 
 * @author I21362
 * @since 2020. 5. 19.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 5. 19. I21362 - 최초 작성.
 * </pre>
 */
public class AuthorDto {
	
    private java.lang.String authorHistory;

    private long authorId;

    private java.lang.String authorName;

	/**
	 * @return the authorHistory
	 */
	public java.lang.String getAuthorHistory() {
		return authorHistory;
	}

	/**
	 * @param authorHistory the authorHistory to set
	 */
	public void setAuthorHistory(java.lang.String authorHistory) {
		this.authorHistory = authorHistory;
	}

	/**
	 * @return the authorId
	 */
	public long getAuthorId() {
		return authorId;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	/**
	 * @return the authorName
	 */
	public java.lang.String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(java.lang.String authorName) {
		this.authorName = authorName;
	}

    
}
