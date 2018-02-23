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
package com.wudaosoft.spring.boot.autoconfigure.httpclient;

import java.nio.charset.Charset;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.wudaosoft.net.httpclient.HostConfig;
import com.wudaosoft.net.httpclient.HostConfigBuilder;
import com.wudaosoft.net.httpclient.Request;

/**
 * @author changsoul.wu
 *
 */

@Configuration
@ConditionalOnClass(name = "com.wudaosoft.net.httpclient.Request")
@ConditionalOnProperty(prefix = "wudaosoft.httpclient", value = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(WudaoHttpclientProperties.class)
public class WudaoHttpclientAutoConfiguration {

	@Bean
	@Primary
	@ConditionalOnProperty(prefix = "wudaosoft.httpclient", value = "target-host", matchIfMissing = false)
	public Request defaultRequest(WudaoHttpclientProperties properties) {

		HostConfig config = HostConfigBuilder.create(properties.getTargetHost())
				.setIsMulticlient(properties.isMulticlient())
				.setCharset(properties.getCharset() != null ? Charset.forName(properties.getCharset()) : null)
				.setConnectionRequestTimeout(properties.getConnectionRequestTimeout())
				.setConnectTimeout(properties.getConnectTimeout())
				.setSocketTimeout(properties.getSocketTimeout())
				.setPoolSize(properties.getPoolSize()).build();
		
		Request.Builder builder = Request.custom().setHostConfig(config);
		
		if(!properties.isKeepAlive())
			builder.withNoKeepAlive();
		
		if(properties.isTrustAll())
			builder.withTrustAll();
		
		return builder.build();
	}
	
	@Bean
	@ConditionalOnMissingBean(Request.class)
	public Request anyHostRequest(WudaoHttpclientProperties properties) {
		
		HostConfig config = HostConfigBuilder.create()
				.setIsMulticlient(properties.isMulticlient())
				.setCharset(properties.getCharset() != null ? Charset.forName(properties.getCharset()) : null)
				.setUserAgent(properties.getUserAgent())
				.setReferer(properties.getReferer())
				.setConnectionRequestTimeout(properties.getConnectionRequestTimeout())
				.setConnectTimeout(properties.getConnectTimeout())
				.setSocketTimeout(properties.getSocketTimeout())
				.setPoolSize(properties.getPoolSize()).build();
		
		Request.Builder builder = Request.custom().setHostConfig(config);
		
		if(!properties.isKeepAlive())
			builder.withNoKeepAlive();
		
		if(properties.isTrustAll())
			builder.withTrustAll();
		
		return builder.build();
	}

}
