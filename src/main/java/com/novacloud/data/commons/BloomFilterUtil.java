package com.novacloud.data.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.common.io.Files;

/**
 * BloomFilter工具类,Bloom Fliter采用guava实现.
 * 
 * @author <a href="mailto:wuzhiqiang@novacloud.com">wuzq</a>
 * @date 2013-5-28上午9:34:36
 * @version Revision: 1.3
 */
public class BloomFilterUtil {
	private static final Logger LOG = LoggerFactory
			.getLogger(BloomFilterUtil.class.getName());
	private static BloomFilter<CharSequence> bloomFilter = BloomFilter.create(
			Funnels.stringFunnel(Charsets.UTF_8), 10000000, 0.0001);
	private static String indexPath;

	public static void add(String input) {
		bloomFilter.put(input);
	}

	public static boolean contains(String input) {
		return bloomFilter.mightContain(input);
	}

	/**
	 * 把索引序列化到本地.
	 */
	public static void save() {
		LOG.info("开始写入本地...");
		long start = System.currentTimeMillis();
		File file = new File(indexPath);
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file, false));
			oos.writeObject(bloomFilter);
			oos.flush();
		} catch (FileNotFoundException e) {
			LOG.error("找不到文件'{}", file.getName(), e);
		} catch (IOException e) {
			LOG.error("写入文件失败'{}", file.getName(), e);
		} finally {
			try {
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				LOG.error("写入文件失败'{}", file.getName(), e);
			}
		}
		LOG.info("写入本地完成,耗时{}...", (System.currentTimeMillis() - start));
	}

	/**
	 * 从本地读取索引文件.
	 */
	@SuppressWarnings("unchecked")
	public static void readIndex() {
		LOG.info("开始读取本地文件...");
		long start = System.currentTimeMillis();
		File file = new File(indexPath);
		ObjectInputStream oos = null;
		try {
			if (!file.exists()) {
				Files.touch(file);
				save();
				return;
			}
			oos = new ObjectInputStream(new FileInputStream(file));
			bloomFilter = (BloomFilter<CharSequence>) oos.readObject();
		} catch (FileNotFoundException e) {
			LOG.error("找不到文件'{}", file.getName(), e);
		} catch (IOException e) {
			LOG.error("读取文件失败'{}", file.getName(), e);
		} catch (ClassNotFoundException e) {
			LOG.error("找不到指定类'{}", file.getName(), e);
		} finally {
			try {
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				LOG.error("读取文件失败'{}", file.getName(), e);
			}
		}
		LOG.info("读取数据完毕,耗时{}...", (System.currentTimeMillis() - start));
	}
}
