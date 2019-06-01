package tools;

public class Constant {
	
	//用户
	public static final String USER = "uname";
	
	//手机号
	public static final String PHONE = "cellphone";
	
	//手机验证码
	public static final String CODE = "code";
	
	//手机验证码
	public static final String OPPENID = "oppenId";
	
	//设备超时时间
	public static final Integer EQUIPMENT_OVER_TIME = 12;
	
	//警告
	public static final String WARNING = "00000001";
	
	//正在处理
	public static final String CONTINUE = "C";
	
	public static String getUser() {
		return USER;
	}

	public static String getPhone() {
		return PHONE;
	}

	public static String getCode() {
		return CODE;
	}

	public static String getOppenid() {
		return OPPENID;
	}

	public static String getWarning() {
		return WARNING;
	}

	public static String getContinue() {
		return CONTINUE;
	}

	public static String getYes() {
		return YES;
	}

	public static String getNo() {
		return NO;
	}

	//运行正常
	public static final String YES = "00000000";
	
	//运行错误
	public static final String NO = "00000002";
}
