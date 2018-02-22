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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author changsoul.wu
 *
 */
@ConfigurationProperties(prefix = "wudaosoft.weixin")
public class WudaoWeixinProperties {
	
	private boolean enabled = true;

	private String appid;
	
	private String appsecret;
	
	private String token;
	
	private String encodingAesKey;

	private int connectionRequestTimeout = 500;

	private int connectTimeout = 6000;

	private int socketTimeout = 8000;

	private int poolSize = 170;
	
	@Value("${weixin.jssign-path:/api/weixin/jssign}")
	private String jssignPath = "/api/weixin/jssign";
	
	@Value("${weixin.message-path:/api/weixin/msgserver}")
	private String messagePath = "/api/weixin/msgserver";

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid;
	}

	/**
	 * @param appid the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * @return the appsecret
	 */
	public String getAppsecret() {
		return appsecret;
	}

	/**
	 * @param appsecret the appsecret to set
	 */
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the encodingAesKey
	 */
	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	/**
	 * @param encodingAesKey the encodingAesKey to set
	 */
	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}

	/**
	 * @return the connectionRequestTimeout
	 */
	public int getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	/**
	 * @param connectionRequestTimeout the connectionRequestTimeout to set
	 */
	public void setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	/**
	 * @return the connectTimeout
	 */
	public int getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * @param connectTimeout the connectTimeout to set
	 */
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * @return the socketTimeout
	 */
	public int getSocketTimeout() {
		return socketTimeout;
	}

	/**
	 * @param socketTimeout the socketTimeout to set
	 */
	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	/**
	 * @return the poolSize
	 */
	public int getPoolSize() {
		return poolSize;
	}

	/**
	 * @param poolSize the poolSize to set
	 */
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	/**
	 * @return the jssignPath
	 */
	public String getJssignPath() {
		return jssignPath;
	}

	/**
	 * @param jssignPath the jssignPath to set
	 */
	public void setJssignPath(String jssignPath) {
		this.jssignPath = jssignPath;
	}

	/**
	 * @return the messagePath
	 */
	public String getMessagePath() {
		return messagePath;
	}

	/**
	 * @param messagePath the messagePath to set
	 */
	public void setMessagePath(String messagePath) {
		this.messagePath = messagePath;
	}

}
