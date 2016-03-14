package io.rong.app.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.rong.app.AppConst;


public class StringFunction {
	
	public static boolean isNotNull(String str) {
		if (str == null || str.trim().length() == 0) {
			return false;
		}
		return true;
	}
	public static boolean isNotNull(Object obj) {
		if (obj == null || obj.toString().trim().length() == 0) {
			return false;
		}
		return true;
	}
	
	public static boolean isNull(Object obj) {
		if (obj == null | obj.toString().trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotNull(int number) {
		if (number == AppConst.NUMBERNULL) {
			return false;
		}
		return true;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getCurTime() {
		SimpleDateFormat df = new SimpleDateFormat(AppConst.DateFormatYMD);// 设置日期格式
		return df.format(new Date());
	}
	
	/**
	 * 获取当前时间 毫秒级
	 * @return
	 */
	public static String getCurTimeMS() {
		SimpleDateFormat df = new SimpleDateFormat(AppConst.DateFormatYMDHMSS);// 设置日期格式
		return df.format(new Date());
	}
	
	/**
	 * 获取当前时间 毫秒级
	 * @return
	 */
	public static java.sql.Date getCurSQLDate() {		
		Date utilDate=new Date();
		return new java.sql.Date(utilDate.getTime());		
	}
	
	
	 /**
     * 将数字类型日期格式化
     * @param timestampString
     * @return
     */
    public static String timeStampDate(String timestampString, String format) {

        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(format).format(new Date(timestamp));
        return date;
    }
    
    public static String timeStampDate(long timestamp, String format) {
        String date = new SimpleDateFormat(format).format(new Date(timestamp));
        return date;
    }

    /**
     * 将时间unix转换为int类型
     * @param timeString
     * @param format
     * @return
     */
    public static int dateToInt(String timeString, String format) {
    	
        int time = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.US);
            Date date = sdf.parse(timeString);
            String strTime = date.getTime() + "";
            strTime = strTime.substring(0, 10);
            time = Integer.parseInt(strTime);

        }catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
    
    /**
     * 保留两位小数
     * @param
     * @return
     */
    public static String formatDecimal(BigDecimal bigValue){
    	DecimalFormat dValue = new DecimalFormat("##0.00");
    	if(bigValue.floatValue() >0){    		
    		String sValue=dValue.format(bigValue); 
    		return sValue;
    	}else{
    		return "0.00";
    	}
    }
    
    public static String formatDecimal(float bigValue){
    	DecimalFormat dValue = new DecimalFormat("##0.00");
    	if(bigValue > 0){    		
    		String sValue=dValue.format(bigValue);
    		return sValue;
    	}else{
    		return "0.00";
    	}
    }
    
    public static String formatDecimal(double bigValue){
    	DecimalFormat dValue = new DecimalFormat("##0.00");
    	if(bigValue > 0){    		
    		String sValue=dValue.format(bigValue);
    		return sValue;
    	}else{
    		return "0.00";
    	}
    }
    /**
     * 保留一位小数
     * @param bigValue
     * @return
     */
    public static String formatDecimalForOne(float bigValue){
    	DecimalFormat dValue = new DecimalFormat("##0.0");
    	if(bigValue > 0){    		
    		String sValue=dValue.format(bigValue);
    		return sValue;
    	}else{
    		return "0.00";
    	}
    }
    /**
     * 将object 数据转换成String 空 为 “”
     * @param str
     * @return
     */
    public static String getObjectToString(Object str){
    	String result = "";
    			if(str instanceof String){
    		   		if(str!=null){
    				result = (String)str;
    		   		}
    			}else if(str instanceof Integer || str instanceof Float || str instanceof Double){
    				if(str!=null){
    					result = str+"";
    				}else{
    					result = str+"0";
    				}
    			}else if( str instanceof Date){
    				if(str!=null){
    					result = str+"";
    				}
    			}
    	return result;
    	
    	
    }
    
    
    
    
	
	/**
	 * 检验是否符合正则规则
	 * @param str   字符串内容
	 * @param regex 正则表达式规则
	 * @return
	 */
	public static boolean checkRegex(String str, String regex) {
		boolean flag = false;
		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(str);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	// public static String repalceRegex(String str,String regex) {
	//
	// Pattern pat = Pattern.compile(regex);
	// Matcher matcher = pat.matcher(str);
	// while (matcher.find()) {
	// String temp = str.substring(matcher.start(), matcher.end());
	// str = str.replaceAll(temp, temp.substring(0, temp.lastIndexOf("条"))
	// + "行");
	// }
	// System.out.println(str);
	// return str;
	// }

	public static String subString(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			// System.out.println(matcher.group(0));
			String value = matcher.group(0);
			// System.out.println(matcher.group());
			return value;
		}

		return AppConst.STR_NULL;
	}
	
	public static String subByteString(String str,int bytelen){
		String result = null;
		byte[] bytes = str.getBytes();
		
        if (bytes.length <= bytelen) {
            result = str;
        } else if (bytelen > 0) {
            result = new String(bytes, 0, bytelen);
            int length = result.length();
            if (str.charAt(length - 1) != result.charAt(length - 1)) {
                if (length < 2) {
                    result = null;
                } else {
                    result = result.substring(0, length - 1);
                }
            }
        }
        
        return result;
        
	}

	/**
	 * 字符串编码转换的实现方法
	 * 
	 * @param str
	 *            待转换编码的字符串
	 * @param oldCharset
	 *            原编码
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String oldCharset,
			String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}
	 
	 
	public static String subString(String str,int len){
		String result = null;
//		byte[] bytes = str.getBytes();
		
        if (str.length() <= len) {
            result = str;
        } else if (len > 0) {
        	result = str.substring(0,len);
        }
        return result;
        
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	public static int getRandomNumber() {
		Random r = new Random();
		return r.nextInt(1000);
	}
	
	/**
	 * 手机号验证
	 * 
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) { 
		Pattern p = null;
		Matcher m = null;
		boolean b = false; 
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches(); 
		return b;
	}
	/**
	 * 卡号验证
	 * 
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isCardNo(String str) { 
		boolean b = false; 
		b = str.matches("^(\\d{16}|\\d{19})$");
		return b;
	}
	/**
	 * 电话号码验证
	 * 
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) { 
		Pattern p1 = null,p2 = null;
		Matcher m = null;
		boolean b = false;  
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
		if(str.length() >9)
		{	m = p1.matcher(str);
 		    b = m.matches();  
		}else{
			m = p2.matcher(str);
 			b = m.matches(); 
		}  
		return b;
	}
	/**
	 * 账号长度校验
	 * 
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean checkUserNameLgh(String str) { 
		boolean b = false;
		if(str.length() <=40&&str.length()>=4 )
		{	
			b =true;
		}else{
			b = false;
		}  
		return b;
	}
	
	public static String convertNotNull(String value){
		if(value==null){
			return AppConst.STR_NULL;
		}else if(value.equals("")){
			return AppConst.STR_NULL;
		}else{
			return value;
		}
	}
	
	public static Integer convertNotNull(Integer value){
		if(value == null){
			return AppConst.ZERO;
		}else{
			return value;
		}
	}

	public static void main(String[] args) {
//		String regex = "19233,[0-9]{1,2}";
//		String str1 = "19233,1";
//		String str2 = "19233,1|11646,3";
//		String str3 = "11656,2|19233,1|11646,3";
//		String str4 = "11656,2|11646,3|19233,1";
//		String str5 = "11656,2|11646,3";
//		// repalceRegex(str, regex);
//		// System.out.println(str3);
//		System.out.println(str1.replaceAll(regex, "19233,5"));
//		System.out.println(str2.replaceAll(regex, "19233,5"));
//		System.out.println(str3.replaceAll(regex, "19233,5"));
//		System.out.println(str4.replaceAll(regex, "19233,5"));
//		System.out.println(str5.replaceAll(regex, "19233,5"));
//
//		// System.out.println(str4.replaceFirst("([0-9]*,[0-9]{1,2}\\|?){2}",
//		// ""));
//
		String str6 = "JSESSIONID=E1AC36E48292A973F160033290B11A89; curCity=2; userGuid=1e5cc861-0794-4f94-b138-e62a881df38b";
//		System.out.println("subString:" + subString(regex, str1));
//		System.out.println("subString:" + subString(regex, str2));
//		System.out.println("subString:" + subString(regex, str3));
//		System.out.println("subString:" + subString(regex, str4));
		System.out.println("subString:" + subString("userGuid=[\\w]*;?", str6));
		System.out.println("subString:" + str6.replaceFirst("JSESSIONID=[\\w]*;?", ""));
//		// System.out.println("19233,[0-9]{1,2}".matches(regex));
//		
		
//		System.out.println(checkRegex("12,99_123456,1","^(\\d+,\\d{1,2}_)*(\\d+,\\d{1,2})"));
		
//		String sData = "{\"error\":\"100\",\"message\":\"用户未登录\",\"data\":null,\"a\":\"value\"}";
//		BaseInfo baseInfo = JSONObject.toJavaObject(JSON.parseObject(sData), BaseInfo.class);
//		JSON.parseObject(sData,BaseInfo.class);
//		System.out.println(baseInfo.getMessage());
		
		
//		System.out.println("fuliancart1=123,1;a123".replaceAll("fuliancart\\d=(\\d+,\\d{1,2}_)*(\\d+,\\d{1,2})*;", ""));
		
//		System.out.println(dateToInt("14-Jun-2014 03:54:35", "EEE, dd-MMM-yyyy HH:mm:ss z"));
//		System.out.println(dateToInt("Sat, 14-Jun-2014 03:54:35 GMT", "EEE, dd-MMM-yyyy HH:mm:ss z"));
		
//		Date date = new Date("Sat, 14-Jun-2014 03:54:35 GMT");
//		Date.parse("Sat, 14-Jun-2014 03:54:35 GMT");
//		String strTime =  date.getTime()+"";
//		System.out.println(strTime);
//		strTime = strTime.substring(0, 10);
//        int time = Integer.parseInt(strTime);
//        System.out.println(time);1401444577
		System.out.println(timeStampDate("1401444577000","YYYY-MM-dd"));
		System.out.println("/fulianWap/shopping/checkValidate".startsWith("/fulianWap/shopping"));
		System.out.println("匹配："+"测试".matches("([\\w\\W]*,)*测试(,[\\w\\W]*)*"));
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");// 设置日期格式
		System.out.println(df.format(new Date(System.currentTimeMillis())));
		System.out.println(StringFunction.timeStampDate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss SSS"));
		
	}
}