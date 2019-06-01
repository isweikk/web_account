package tools;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 
 * @ClassName: StringUtils   
 * @Description: string类的工具类
 */
public class StringUtils {

	/**
	 * 
	 * @Title: isEmpty
	 * @author:WANG   
	 * @param @param string 需要判断的字符串
	 * @param @return      
	 * @return boolean  是否是空的结果  
	 * @time:2017年3月9日 下午3:19:28
	 * @Description: 判断字符串是否为空
	 */
	public static boolean isEmpty(String string){
		if (string == null || string.trim().equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @Title: listToString
	 * @param @param ids
	 * @param @return      
	 * @return String      
	 * @Description:把集合转为字符串
	 */
	public static String listToString(List<Long> ids){
		if (ids != null) {
			StringBuilder sb = new StringBuilder();
			int size = ids.size();
			for (int i = 0; i < size; i++) {
				if (i != size - 1) {
					sb.append(ids.get(i)+",");
				}else {
					sb.append(ids.get(i));
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: isoToUtf
	 * @param @param string
	 * @param @throws UnsupportedEncodingException      
	 * @return String      
	 * @Description:iso8859-1转化为utf-8
	 */
	public static String isoToUtf(String string) throws UnsupportedEncodingException{
		if (!isEmpty(string)) {
			return new String(string.getBytes("iso8859-1"),"utf-8");
		}
		return null;
	}
	
}
