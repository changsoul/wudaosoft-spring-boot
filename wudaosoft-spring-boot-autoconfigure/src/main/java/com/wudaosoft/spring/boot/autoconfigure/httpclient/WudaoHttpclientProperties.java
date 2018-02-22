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

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author changsoul.wu
 *
 */
@ConfigurationProperties(prefix = "wudaosoft.httpclient")
public class WudaoHttpclientProperties {
	
	private boolean enabled = true;

	private String targetHost;
	
	private String charset;
	
	private String userAgent = "Wudaosoft Http Tools/1.0";
	
	private String referer;

	private int connectionRequestTimeout = 500;

	private int connectTimeout = 10000;

	private int socketTimeout = 10000;

	private int poolSize = 70;

	private boolean multiclient = false;

	private boolean keepAlive = true;

	private boolean trustAll = false;

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
	 * @return the targetHost
	 */
	public String getTargetHost() {
		return targetHost;
	}

	/**
	 * @param targetHost the targetHost to set
	 */
	public void setTargetHost(String hostUrl) {
		this.targetHost = hostUrl;
	}

	/**
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * @param charset the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the referer
	 */
	public String getReferer() {
		return referer;
	}

	/**
	 * @param referer the referer to set
	 */
	public void setReferer(String referer) {
		this.referer = referer;
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
	 * @return the multiclient
	 */
	public boolean isMulticlient() {
		return multiclient;
	}

	/**
	 * @param multiclient the multiclient to set
	 */
	public void setMulticlient(boolean multiclient) {
		this.multiclient = multiclient;
	}

	/**
	 * @return the keepAlive
	 */
	public boolean isKeepAlive() {
		return keepAlive;
	}

	/**
	 * @param keepAlive the keepAlive to set
	 */
	public void setKeepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
	}

	/**
	 * @return the trustAll
	 */
	public boolean isTrustAll() {
		return trustAll;
	}

	/**
	 * @param trustAll the trustAll to set
	 */
	public void setTrustAll(boolean trustAll) {
		this.trustAll = trustAll;
	}

}
