package net.ecbank.fwk.exception;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.MessageSource;

import egovframework.rte.fdl.cmmn.exception.BaseRuntimeException;

/**
 * 비즈니스 공통예외 및 임의생성 예외
 * @author LYJ
 *
 */
@SuppressWarnings("serial")
public class BizRuntimeException extends BaseRuntimeException{
	
	/**
	 * BizRuntimeException 기본 생성자.
	 */
	public BizRuntimeException() {
		this("BizRuntimeException without message", null, null);
	}

	/**
	 * BizRuntimeException 생성자.
	 * 
	 * @param defaultMessage 메세지 지정
	 */
	public BizRuntimeException(String defaultMessage) {
		this(defaultMessage, null, null);
	}

	/**
	 * BizRuntimeException 생성자.
	 * 
	 * @param wrappedException  원인 Exception
	 */
	public BizRuntimeException(Throwable wrappedException) {
		this("BizRuntimeException without message", null, wrappedException);
	}

	/**
	 * BizRuntimeException 생성자.
	 * 
	 * @param defaultMessage 메세지 지정
	 * @param wrappedException 원인 Exception
	 */
	public BizRuntimeException(String defaultMessage, Throwable wrappedException) {
		this(defaultMessage, null, wrappedException);
	}

	/**
	 * BizRuntimeException 생성자.
	 * 
	 * @param defaultMessage 메세지 지정(변수지정)
	 * @param messageParameters 치환될 메세지 리스트
	 * @param wrappedException 원인 Exception
	 */
	public BizRuntimeException(String defaultMessage, Object[] messageParameters, Throwable wrappedException) {
		super(wrappedException);

		String userMessage = defaultMessage;
		if (messageParameters != null) {
			userMessage = MessageFormat.format(defaultMessage, messageParameters);
		}
		
		this.message = userMessage;
	}

	/**
	 * BizRuntimeException 생성자.
	 * 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 */
	public BizRuntimeException(MessageSource messageSource, String messageKey) {
		this(messageSource, messageKey, null, null, Locale.getDefault(), null);
	}

	/**
	 * BizRuntimeException 생성자.
	 * 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 */
	public BizRuntimeException(MessageSource messageSource, String messageKey, Throwable wrappedException) {
		this(messageSource, messageKey, null, null, Locale.getDefault(), wrappedException);
	}

	/**
	 * BizRuntimeException 생성자.
	 * 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 * @param locale 국가/언어지정
	 * @param wrappedException 원인 Exception
	 */
	public BizRuntimeException(MessageSource messageSource, String messageKey, Locale locale, Throwable wrappedException) {
		this(messageSource, messageKey, null, null, locale, wrappedException);
	}
	
	/**
	 * BizRuntimeException 생성자.
	 * 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 * @param messageParameters 치환될 메세지 리스트
	 */
	public BizRuntimeException(MessageSource messageSource, String messageKey, Object[] messageParameters) {
		this(messageSource, messageKey, messageParameters, null, Locale.getDefault(), null);
	}
	
	/**
	 * BizRuntimeException 생성자.
	 * 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 * @param messageParameters 치환될 메세지 리스트
	 * @param locale 국가/언어지정
	 * @param wrappedException 원인 Exception
	 */
	public BizRuntimeException(MessageSource messageSource, String messageKey, Object[] messageParameters, Locale locale, Throwable wrappedException) {
		this(messageSource, messageKey, messageParameters, null, locale, wrappedException);
	}

	/**
	 * BizRuntimeException 생성자.
	 * 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 * @param messageParameters 치환될 메세지 리스트
	 * @param wrappedException 원인 Exception
	 */
	public BizRuntimeException(MessageSource messageSource, String messageKey, Object[] messageParameters, Throwable wrappedException) {
		this(messageSource, messageKey, messageParameters, null, Locale.getDefault(), wrappedException);
	}

	/**
	 * BizRuntimeException 생성자.
	 * 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 * @param messageParameters 치환될 메세지 리스트
	 * @param defaultMessage 메세지 지정(변수지정)
	 * @param wrappedException 원인 Exception
	 */
	public BizRuntimeException(MessageSource messageSource, String messageKey, Object[] messageParameters, String defaultMessage, Throwable wrappedException) {
		this(messageSource, messageKey, messageParameters, defaultMessage, Locale.getDefault(), wrappedException);
	}

	/**
	 * BizRuntimeException 생성자.
	 * 
	 * @param messageSource 메세지 리소스
	 * @param messageKey 메세지키값
	 * @param messageParameters 치환될 메세지 리스트
	 * @param defaultMessage 메세지 지정(변수지정)
	 * @param locale 국가/언어지정
	 * @param wrappedException 원인 Exception
	 */
	public BizRuntimeException(MessageSource messageSource, String messageKey, Object[] messageParameters, String defaultMessage, Locale locale, Throwable wrappedException) {
		super(wrappedException);

		this.messageKey = messageKey;
		this.messageParameters = messageParameters;
		this.message = messageSource.getMessage(messageKey, messageParameters, defaultMessage, locale);
	}
}
