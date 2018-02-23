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

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.wudaosoft.net.httpclient.HostConfig;
import com.wudaosoft.net.httpclient.HostConfigBuilder;
import com.wudaosoft.net.httpclient.Request;
import com.wudaosoft.weixinsdk.ApiUrlConstants;
import com.wudaosoft.weixinsdk.aes.AesException;
import com.wudaosoft.weixinsdk.config.WeiXinConfig;
import com.wudaosoft.weixinsdk.material.MaterialApi;
import com.wudaosoft.weixinsdk.menu.MenuApi;
import com.wudaosoft.weixinsdk.message.send.CustomMsgSender;
import com.wudaosoft.weixinsdk.message.send.article.ArticlesApi;
import com.wudaosoft.weixinsdk.oauth2.OAuth2Api;
import com.wudaosoft.weixinsdk.qrcode.QRCodeApi;
import com.wudaosoft.weixinsdk.usermanage.UserApi;
import com.wudaosoft.weixinsdk.usermanage.UserGroupApi;

/**
 * @author changsoul.wu
 *
 */

@Configuration
@ConditionalOnClass(name = "com.wudaosoft.weixinsdk.config.WeiXinConfig")
@ConditionalOnProperty(prefix = "wudaosoft.weixin", value = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(WudaoWeixinProperties.class)
@PropertySource("classpath:/com/wudaosoft/spring/boot/autoconfigure/weixinsdk/httpclient.properties")
public class WudaoWeixinAutoConfiguration {
	
	@Bean(name="defaultWeixinConfig")
	@Primary
	@ConditionalOnProperty(prefix = "wudaosoft.weixin", value = {"appid", "appsecret"}, matchIfMissing = false)
	public WeiXinConfig defaultWeixinConfig(WudaoWeixinProperties properties) throws AesException {

		HostConfig config = HostConfigBuilder.create(ApiUrlConstants.WEIXIN_API_SERVER_HOST)
				.setIsMulticlient(false)
				.setConnectionRequestTimeout(properties.getConnectionRequestTimeout())
				.setConnectTimeout(properties.getConnectTimeout())
				.setSocketTimeout(properties.getSocketTimeout())
				.setPoolSize(properties.getPoolSize()).build();
		
		WeiXinConfig wexin = new WeiXinConfig(Request.createDefault(config))
				.setAppId(properties.getAppid())
				.setAppsecret(properties.getAppsecret());
		
		if(properties.getToken() != null)
			wexin.setToken(properties.getToken());
		
		if(properties.getEncodingAesKey() != null)
			wexin.setEncodingAesKey(properties.getEncodingAesKey());
		
		return wexin;
	}

	@Bean
	@ConditionalOnBean(WeiXinConfig.class)
	public MaterialApi officialMaterialApi(WeiXinConfig officialWeixinConf) {
		return new MaterialApi(officialWeixinConf);
	}

	@Bean
	@ConditionalOnBean(WeiXinConfig.class)
	public MenuApi officialMenuApi(WeiXinConfig officialWeixinConf) {
		return new MenuApi(officialWeixinConf);
	}

	@Bean
	@ConditionalOnBean(WeiXinConfig.class)
	public CustomMsgSender officialCustomMsgSender(WeiXinConfig officialWeixinConf) {
		return new CustomMsgSender(officialWeixinConf);
	}

	@Bean
	@ConditionalOnBean(WeiXinConfig.class)
	public ArticlesApi officialArticlesApi(WeiXinConfig officialWeixinConf) {
		return new ArticlesApi(officialWeixinConf);
	}

	@Bean
	@ConditionalOnBean(WeiXinConfig.class)
	public OAuth2Api officialOAuth2Api(WeiXinConfig officialWeixinConf) {
		return new OAuth2Api(officialWeixinConf);
	}

	@Bean
	@ConditionalOnBean(WeiXinConfig.class)
	public QRCodeApi officialQRCodeApi(WeiXinConfig officialWeixinConf) {
		return new QRCodeApi(officialWeixinConf);
	}

	@Bean
	@ConditionalOnBean(WeiXinConfig.class)
	public UserApi officialUserApi(WeiXinConfig officialWeixinConf) {
		return new UserApi(officialWeixinConf);
	}

	@Bean
	@ConditionalOnBean(WeiXinConfig.class)
	public UserGroupApi officialUserGroupApi(WeiXinConfig officialWeixinConf) {
		return new UserGroupApi(officialWeixinConf);
	}
	
}
