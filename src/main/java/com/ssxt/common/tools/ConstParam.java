package com.ssxt.common.tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <b>类名称：</b>ConstParam<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>杨培新<br/>
 * <b>修改人：</b>杨培新<br/>
 * 
 * <b>修改时间：</b>2016年6月12日 上午9:20:03<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 */
public class ConstParam {
	private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ConstParam.class);

	public static Properties config = new Properties();
	// private static Properties settings = new Properties();
	/**
	 * 通用的配置
	 */
	private static final String DEFAULT_CONFIG_FILE = "config.properties";

	static {
		/**
		 * 开发环境使用配置文件，相当于DEFAULT_CONFIG_FILE+DEFAULT_SETTINGS_FILE
		 */

		try {
			config.load(ConstParam.class.getClassLoader().getResourceAsStream(DEFAULT_CONFIG_FILE));

		} catch (IOException e) {
			log.error("加载配置文件出错!", e);
		}
	}

	// 雨晴路径
	public static final String saveRainFile = config.getProperty("file.saveRainFile");
	// 河道采集
	public static final String saveRiverFile = config.getProperty("file.saveRiverFile");
	// 水库
	public static final String saveRsvrFile = config.getProperty("file.saveRsvrFile");

	public static String getKey(String key) {
		return config.getProperty(key);
	}
}
