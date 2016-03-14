package io.rong.app;

import android.graphics.Typeface;

import java.util.Arrays;
import java.util.List;

public class AppConst {

	// 系統常量
	public static final String APP_FIRST_IN = "app_first_in";// 判断app是否第一次进入
	public static final String APP_FIRST_IN_S = "app_first_in_str";// 判断app是否第一次进入
	public static final String APP_LOCATION_KEY = "location_key";// 定位信息对应的key
	public static final String APP_ISLOGIN_KEY = "islogin_key";// 定位信息对应的key,值为boolean
	public static final String APP_USERNAME_KEY = "username_key";// 用户名对应的key,值为string
	public static final String APP_PWD_KEY = "password_key";// 用户密码对应的key,值为string
	public static final String APP_CHECK_UPDATE = "check_update_key";// 检查版本更新对应的key,值为string
	public static final String APP_MAINACT_ISAWAKE = "isMainATyAwake";// 检查版本更新对应的key,值为string
	public static final String APP_CUSTOMER_SYSNO = "customerSysno";
	public static final String APP_SELECT_SITE = "selectSite"; // 用户选择的位置
	public static final String APP_SELECT_PROVINCE_CODE = "selectProvinceCode";// 选择的省Code
	public static final String APP_SELECT_CITY_CODE = "selectCityCode";// 选择的城市Code
	public static final String APP_SELECT_DISTRICT_CODE = "selectDistrictCode";// 选择的区县Code
	public static final String APP_SITE_LIST_JSON = "siteListJson";// 地区列表JSON
	/** 用户头像连接 */
	public static final String USERPHOTO_LINK = "userphoto_link";

	// 站点
	public static final String DEFAULT_CITY_NAME = "北京";
	public static final String APP_SITE_CITYCODE = "CityCode";// 设备当前站点名稱的key
	public static final String APP_SITE_CITYNUMBER = "CityNo";// 设备当前站点编号
	public static final String APP_SITE_LONGITUDE = "longitude";// 站点精度key
	public static final String APP_SITE_LATITUDE = "latitude";// 站点纬度key
	public static final String APP_SITE_CURECOMMENDATION = "curRecommendation";// 上次推荐站点
	public static final String APP_SITE_CURECOMMEND_CITYNO = "recommendCityNo";// 上次推荐站点城市号

	
	/**
	 * 列表显示参数
	 */
	// 与DEFAULT_LIMIT常量重复
	public final static String PAGE_COUNT = "10";


	// / <summary>
	// / DateFormat = "yyyy-MM-dd"
	// / </summary>
	public final static String DateFormat = "yyyy-MM-dd";
	// / <summary>
	// / DateFormatLong = "yyyy-MM-dd HH:mm:ss"
	// / </summary>
	public final static String DateFormatYMD = "yyyy-MM-dd ";
	public final static String DateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss ";
	public final static String DateFormatYMDHMSS = "yyyy-MM-dd HH:mm:ss SSS";
	public final static String DateFormatYMDCH = "yyyy年MM月dd日";
	public final static String DateFormatYMDPoint = "yyyy.MM.dd";
	public final static String DateFormatYMDHM = "yyyy-MM-dd HH:mm";
	public final static String DateFormatYMDZ = "EEE, dd-MMM-yyyy HH:mm:ss z";
	public final static String DateFormatMDHMSZ = " MM-dd HH:mm:ss z";
	public final static String DateFormatMDHM = " MM-dd HH:mm";
	/**
	 * String: ""
	 */
	public static final String STR_NULL = "";

	/**
	 * String: ","
	 */
	public static final String STR_COMMA = ",";

	/**
	 * String : -1
	 * 
	 */
	public static final String STR_MINUS_ONE = "-1";

	/**
	 * int : null
	 * 
	 */
	public static final int NUMBERNULL = 0;
	/**
	 * Integer : 0
	 */
	public static final Integer ZERO = 0;

	/**
	 * String : 0
	 */
	public static final String STR_ZERO = "0";

	/**
	 * Integer : 1
	 */
	public static final Integer ONE = 1;

	/**
	 * String:1
	 */
	public static final String STR_ONE = "1";

	/**
	 * Integer : 2
	 */
	public static final Integer TWO = 2;
	/**
	 * String : 2
	 */
	public static final String STR_TWO = "2";

	/**
	 * Integer : 3
	 */
	public static final Integer THREE = 3;

	/**
	 * String : 3
	 */
	public static final String STR_THREE = "3";

	/**
	 * String : 4
	 */
	public static final String STR_FOUR = "4";

	/**
	 * String : 5
	 */
	public static final String STR_FIVE = "5";

	

	public static final String STR_HUNDRED = "100";

	/*********** 共通常量 End ****************/

	// 缓存购物车数量的健
	public final static String CACHE_CART_NUM = "cartNum";

	public final static String PAYTYPE_WX = "50";
	public final static String PAYTYPE_ALIWAP = "27";
	public final static String PAYTYPE_ALICLIENT = "7";

	public final static String ANDROID = "3";
	public final static String VERSION = "1.0";
	/**
	 * 网络请求错误200
	 */
	public final static String REQNETWORK_ERROR = "200";
	// public static final String REQNETWORK_ERRORMSG = "网络请求错误 ";
	public static final String REQNETWORK_ERRORMSG = "网络不稳定，请稍后再试 ";
	/**
	 * 返回内容错误201
	 */
	public final static String REQTEXT_ERROR = "201";
	public static final String REQTEXT_ERRORMSG = "网络连接错误";
	public static final String REQ_OK = "0";
	public static final String REQ_NOLOGIN = "100";

	/**
	 * 网络不可用
	 */
	public static final String NETWORK_UNAVAILABLE = "300";
	public static final String NETWORK_UNAVAILABLE_MSG = "网络不可用，请设置网络。";

	public static final int REQUEST_TIMEOUT = 50 * 1000;

	public final static String AUTOLOGIN_KEY = "AUTOLOGIN";

	public final static String MONEYNULL = "0.00";

	public final static String CHARSETNAME = "UTF-8";
	// 拆单订单类型
	public final static String ORDERTYPE_GP = "gp";
	// 普通订单类型
	public final static String ORDERTYPE_SO = "so";
	// 礼金卡订单类型
	public final static String ORDERTYPE_GC = "gc";
	// wap URL
	public final static String WAPURL = "http://www.fulianwang.com/";
	// web URL
	public final static String WEBURL = "http://www.fulianwang.com/";

	// 支付前返回商品
	public final static String ALIPAYCALL_BEFORE = WEBURL
			+ "shoppings/AliPayCall_Before.aspx";

	public final static String HOMEFRAGMENT = "home";
	public final static String SORTFRAGMENT = "sort";
	public final static String NEWPRODUCTFRAGMENT = "new";
	public final static String CENTERFRAGMENT = "center";
	public final static String CARTFRAGMENT = "cart";
	public final static List<String> SYNC_COOKIES = Arrays.asList(
			"WebSiteSysNo", "DeliverySysNo", "city", "AppCity",
			"curRecommendation", "RecommendCityStatus", "userID",
			"ZDCustomerLogin", "ZDCustomerPwd", "fuliancart1", "fuliancart2",
			"fuliancart3", "SaleRuleCart1", "SaleRuleCart2", "SaleRuleCart3");

	public static Typeface tfScore;
	public static int screenWidth, screenHeight;
}
