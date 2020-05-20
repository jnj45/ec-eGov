/**
 * SampleWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.ecbank.sample.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface SampleWebService extends java.rmi.Remote {

	/**
	 * @param text
	 * @return
	 */
	String sayHi(@WebParam(name="param1") String text);

	/**
	 * @param authorDto
	 * @return
	 */
	AuthorDto findAuthor(@WebParam(name="authorDto") AuthorDto authorDto);

	/**
	 * @param authorDto
	 * @return
	 */
	List<AuthorDto> findAuthorList(@WebParam(name="authorDto") AuthorDto authorDto);
    
}
