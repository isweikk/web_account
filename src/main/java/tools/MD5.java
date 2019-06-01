package tools;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5 {
	private static MD5 md5 = null;

	public static MD5 getInstance() {
		if (md5 == null) {
			return new MD5();
		} else {
			return md5;
		}
	}

	public static final  String getMD5(String sourceStr) {
		
		String s = null;
		// 用来将字节转换成16进制表示的字符R
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
			// MD5的计算结果是一个128位的长整数，用字节表示为16个字节
			byte[] tmp = md.digest();
			// 每个字节用16进制表示的话，使用2个字符(高4位一个,低4位一个)，所以表示成16进制需要32个字符
			char[] str = new char[16 * 2];
			int k = 0;// 转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) {// 对MD5的每一个字节转换成16进制字符
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];// 对字节高4位进行16进制转换
				str[k++] = hexDigits[byte0 & 0xf]; // 对字节低4位进行16进制转换
			}
			s = new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return s;
		
	}
	
	/**
	 * 
	 * @Title: getPassword
	 * @author:WANG   
	 * @param @param userId
	 * @param @param password
	 * @param @return      
	 * @return String      
	 * @time:2017年4月9日 下午9:13:23
	 * @Description:获取加密后的密码
	 */
	public static final String getPassword(Long userId,String password){
		if (!StringUtils.isEmpty(password)) {
			return getMD5(getMD5(password+userId+"ycwl"));
		}
		return null;
	}

}