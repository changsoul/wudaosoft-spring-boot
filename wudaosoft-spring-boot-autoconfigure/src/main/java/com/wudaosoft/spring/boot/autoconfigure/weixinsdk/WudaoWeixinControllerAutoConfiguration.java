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
package com.wudaosoft.spring.boot.autoconfigure.weixinsdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wudaosoft.weixinsdk.WeiXinMessageProcess;
import com.wudaosoft.weixinsdk.config.WeiXinConfig;
import com.wudaosoft.weixinsdk.controller.MessageController;
import com.wudaosoft.weixinsdk.controller.SignJsApiController;
import com.wudaosoft.weixinsdk.handler.WeiXinMessageHandler;
import com.wudaosoft.weixinsdk.handler.WeiXinMessageHandlerAdapter;
import com.wudaosoft.weixinsdk.servlet.WeiXinMessageServlet;

/**
 * @author changsoul.wu
 *
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(name = {"com.wudaosoft.weixinsdk.config.WeiXinConfig", "javax.servlet.Servlet"})
@ConditionalOnProperty(prefix = "wudaosoft.weixin", value = "enabled", matchIfMissing = true)
@AutoConfigureAfter(WudaoWeixinAutoConfiguration.class)
public class WudaoWeixinControllerAutoConfiguration {

	private static final Logger log = LoggerFactory.getLogger(WudaoWeixinControllerAutoConfiguration.class);

	@Bean
	@ConditionalOnBean(value = WeiXinConfig.class, search = SearchStrategy.CURRENT)
	public SignJsApiController signJsApiController() {
		return new SignJsApiController();
	}

	@Bean
	@ConditionalOnBean(value = WeiXinConfig.class, search = SearchStrategy.CURRENT)
	@ConditionalOnMissingBean(WeiXinMessageHandler.class)
	@ConditionalOnProperty(prefix = "wudaosoft.weixin", value = "token", matchIfMissing = false)
	public WeiXinMessageHandler weiXinMessageHandler() {
		log.info("Use DefaultWeiXinMessageHandler..");
		return new WeiXinMessageHandlerAdapter() {};
	}

	@Bean
	@ConditionalOnBean(WeiXinMessageHandler.class)
	public WeiXinMessageProcess officialWeiXinMessageProcess(WeiXinConfig officialWeixinConf,
			WeiXinMessageHandler messageHandler) {
		return new WeiXinMessageProcess(officialWeixinConf, messageHandler);
	}

//	@Bean
//	@ConditionalOnBean(WeiXinMessageProcess.class)
//	@ConditionalOnMissingBean(MessageController.class)
//	public WeiXinMessageController officialWeiXinMessageController(WeiXinMessageProcess officialWeiXinMessageProcess) {
//		return new WeiXinMessageController(officialWeiXinMessageProcess);
//	}

	@Bean
	@ConditionalOnBean(WeiXinMessageProcess.class)
	@ConditionalOnMissingBean(MessageController.class)
	public ServletRegistrationBean weiXinMessageServletRegistration(WeiXinMessageProcess officialWeiXinMessageProcess,
			WudaoWeixinProperties properties) {
		return new ServletRegistrationBean(new WeiXinMessageServlet(officialWeiXinMessageProcess),
				properties.getMessagePath());
	}
}
