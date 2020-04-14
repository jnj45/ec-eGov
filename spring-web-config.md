# 스프링 Web MVC 관련 설정

* web mvc 기본 초기화 설정은 Servlet3.x WebApplicationInitializer 기능으로 처리
  : EgovWebApplicationInitializer 클래스
    
## egov-com-servlet.xml 수정
  
* Json data 리턴을 위해 Message Converter 등록
        
	'''xml
    <!-- Message Converter 등록 -->
    <mvc:annotation-driven>
        <mvc:argument-resolvers>
            <bean class="net.ecbank.fwk.model.ParamMapArgumentResolver"/>
        </mvc:argument-resolvers>
        <mvc:message-converters>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>application/json;charset=UTF-8</value>
                    </list>
                </property>         
                <property name="objectMapper">
                    <bean class="com.fasterxml.jackson.databind.ObjectMapper"></bean>
                    <!-- <bean class="com.ipro.common.model.json.CustomObjectMapper">
                        <property name="serializationInclusion">
                            <value type="com.fasterxml.jackson.annotation.JsonInclude.Include">NON_NULL</value>
                        </property>
                    </bean> -->                 
                </property>
            </bean>         
        </mvc:message-converters>   
	'''

  	  
