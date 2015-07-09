package com.novacloud.data.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * hash工具.
 * 
 * @author <a href="mailto:wuzhiqiang@novacloud.com">wuzq</a>
 * @date 2013-11-7下午1:45:07
 * @version Revision: 1.3
 */
public class HashUtils {
	private final static Logger logger = LoggerFactory
			.getLogger(HashUtils.class);
	private static HashFunction hashFunction = Hashing.md5();
	// ！）（，：；？"#!&'$%*+()./,-;:?>=<@—]\_’^‘[”“。、`…￥【】×~～》《}|{
	private static String specialString = "  ！）（，：；？\"#!&'$%*+()./,-;:?>=<@—]\\_’^‘[”“。、`…￥【】×~～》《}|{";
	static {
		specialString = org.apache.commons.lang3.StringUtils
				.lowerCase(specialString);
		logger.debug("special_char:{}", specialString);
	}

	/**
	 * trim,小写,去除特殊符号然后md5.
	 * 
	 * @param text
	 * @return
	 */
	public static String hash(String text) {
		if (StringUtils.isNullOrEmpty(text)) {
			return "";
		}
		text = org.apache.commons.lang3.StringUtils.lowerCase(text);
		text = org.apache.commons.lang3.StringUtils.trim(text);
		text = CharMatcher.anyOf(specialString).removeFrom(text);
		logger.trace("processed title:{}", text);
		return hashFunction.hashString(text, Charsets.UTF_8).toString();
	}
}
