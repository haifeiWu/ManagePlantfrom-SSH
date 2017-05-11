package com.haifeiWu.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Properties;


public class PropertiesWriteUtils {
	private static String PREFIX = File.separator +"u";
	/**
	 * 传入字符串，返回ASCII码
	 * @param str
	 * @return ascii
	 */
	public static String native2Ascii(String str) {
		char[] chars = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			sb.append(char2Ascii(chars[i]));
		}
		return sb.toString();
	}

	/**
	 * Native character to ascii string.
	 * 
	 * @param c
	 *            native character
	 * @return ascii string
	 */
	private static String char2Ascii(char c) {
		if (c > 255) {
			StringBuilder sb = new StringBuilder();
			sb.append(PREFIX);
			int code = (c >> 8);
			String tmp = Integer.toHexString(code);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
			code = (c & 0xFF);
			tmp = Integer.toHexString(code);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
			return sb.toString();
		} else {
			return Character.toString(c);
		}
	}
	
	/** 
     * Ascii to native string 
     *  
     * @param str 
     *            ascii string 
     * @return native string 
     */  
    public static String ascii2Native(String str) {  
        StringBuilder sb = new StringBuilder();  
        int begin = 0;  
        int index = str.indexOf(PREFIX);  
        while (index != -1) {  
            sb.append(str.substring(begin, index));  
            sb.append(ascii2Char(str.substring(index, index + 6)));  
            begin = index + 6;  
            index = str.indexOf(PREFIX, begin);  
        }  
        sb.append(str.substring(begin));  
        return sb.toString();  
    }  
    
    /** 
     * Ascii to native character. 
     *  
     * @param str 
     *            ascii string 
     * @return native character 
     */  
    private static char ascii2Char(String str) {  
        if (str.length() != 6) {  
            throw new IllegalArgumentException(  
                    "Ascii string of a native character must be 6 character.");  
        }  
        if (!PREFIX.equals(str.substring(0, 2))) {  
            throw new IllegalArgumentException(  
                    "Ascii string of a native character must start with \"\\u\".");  
        }  
        String tmp = str.substring(2, 4);  
        int code = Integer.parseInt(tmp, 16) << 8;  
        tmp = str.substring(4, 6);  
        code += Integer.parseInt(tmp, 16);  
        return (char) code;  
    }  
	
	/**
	 * 传递键值对的Map，更新properties文件
	 * 
	 * @param fileName
	 *            文件名(放在resource源包目录下)，需要后缀
	 * @param keyValueMap
	 *            键值对Map
	 */
	public static void updateProperties(String fileName,
			Map<String, String> keyValueMap) {
		// InputStream
		// inputStream=PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);//输入流
		String filePath = PropertiesWriteUtils.class.getClassLoader()
				.getResource(fileName).getFile();// 文件的路径
		System.out.println("propertiesPath:" + filePath);
		Properties props = new Properties();
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			// 从输入流中读取属性列表（键和元素对）
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					filePath), "UTF-8"));
			props.load(br);
			br.close();
			// 写入属性文件
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath), "UTF-8"));
			// props.clear();// 清空旧的文件
			for (String key : keyValueMap.keySet())
				props.setProperty(key, keyValueMap.get(key));
			props.store(bw, "");
			bw.close();
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating " + ""
					+ " value error");
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
