/**
 *    Copyright 2009-2018 Wudao Software Studio(wudaosoft.com)
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.wudaosoft.spring.boot.autoconfigure.web;

import javax.servlet.Servlet;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.wudaosoft.commons.mvc.rest.RestExceptionControllerAdvice;

/**
 * @author changsoul.wu
 *
 */
@Configuration
@ConditionalOnClass({RestExceptionControllerAdvice.class, Servlet.class, DispatcherServlet.class })
@ConditionalOnWebApplication
@ConditionalOnResource(resources="classpath:/com/wudaosoft/spring/boot/starter/resterror/resterror.enabled")
@ConditionalOnProperty(prefix="wudaosoft.web", name="rest-error", matchIfMissing=true)
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
public class RestErrorAutoConfiguration {

	@Bean
	public BasicErrorController basicErrorController(ErrorAttributes errorAttributes,
			ServerProperties serverProperties) {
		return new RestErrorController(errorAttributes, serverProperties.getError());
	}

	@Bean
	@ConditionalOnMissingBean(RestExceptionControllerAdvice.class)
	public RestExceptionControllerAdvice restExceptionControllerAdvice() {
		return new RestExceptionControllerAdvice();
	}
}
