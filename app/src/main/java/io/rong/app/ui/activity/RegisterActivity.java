package io.rong.app.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sea_monster.exception.BaseException;
import com.sea_monster.exception.InternalException;
import com.sea_monster.network.AbstractHttpRequest;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import io.rong.app.DemoContext;
import io.rong.app.R;
import io.rong.app.model.Status;
import io.rong.app.ui.widget.EditTextHolder;
import io.rong.app.ui.widget.WinToast;
import io.rong.app.utils.CommonUtils;
import io.rong.app.utils.NetUtils;
import io.rong.app.utils.StringFunction;

/**
 * Created by Bob on 2015/2/6.
 */
public class RegisterActivity extends BaseApiActivity implements View.OnClickListener, EditTextHolder.OnEditTextFocusChangeListener, Handler.Callback {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private static final int HANDLER_REGIST_HAS_NO_FOCUS = 1;
    private static final int HANDLER_REGIST_HAS_FOCUS = 2;
    /***获取电话验证码*/
    private Button Tele_button;
    /**验证码长度*/
    private final int textsize = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.de_ac_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();//隐藏ActionBar
        Tele_button=(Button)findViewById(R.id.Tele_button);
        mRegistEmail = (EditText) findViewById(R.id.et_register_mail);

        mRegistPassword = (EditText) findViewById(R.id.et_register_password);
        mRegistNickName = (EditText) findViewById(R.id.et_register_nickname);
        mRegistTelePhone = (EditText) findViewById(R.id.et_register_telephone);
        mRegistTelePhoneYzm = (EditText) findViewById(R.id.et_register_yzm);
        mRegistTelePhone.setInputType(InputType.TYPE_CLASS_PHONE);
        mRegisteUserAgreement = (TextView) findViewById(R.id.register_user_agreement);
        mRegisteButton = (Button) findViewById(R.id.register_agree_button);
        mRegistReminder = (TextView) findViewById(R.id.de_regist_reminder);
        mLogoImg = (ImageView) findViewById(R.id.de_logo);
        mLeftTitle = (TextView) findViewById(R.id.de_left);
        mRightTitle = (TextView) findViewById(R.id.de_right);
        mImgBackgroud = (ImageView) findViewById(R.id.de_img_backgroud);
        mIsShowTitle = (RelativeLayout) findViewById(R.id.de_merge_rel);
        mEmailDeleteFramelayout = (FrameLayout) findViewById(R.id.et_register_delete);
        mTelephoneyzmDeleteFramelayout = (FrameLayout) findViewById(R.id.et_register_yzm_delete);
        mTelephoneDeleteFramelayout = (FrameLayout) findViewById(R.id.et_telephone_delete);
        mPasswordDeleteFramelayout = (FrameLayout) findViewById(R.id.et_password_delete);
        mNickNameDeleteFramelayout = (FrameLayout) findViewById(R.id.et_nickname_delete);
        mHandler = new Handler(this);
        mSoftManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.translate_anim);
                mImgBackgroud.startAnimation(animation);
            }
        });
        initData();
    }

    protected void initData() {
        mRegisteButton.setOnClickListener(this);
        mRegisteUserAgreement.setOnClickListener(this);
        mLeftTitle.setOnClickListener(this);
        mRightTitle.setOnClickListener(this);
        Tele_button.setOnClickListener(this);
        mEditUserNameEt = new EditTextHolder(mRegistEmail, mEmailDeleteFramelayout, null);
        mEditTeleNameEt = new EditTextHolder(mRegistTelePhone, mTelephoneDeleteFramelayout, null);
        mEditTeleYzm = new EditTextHolder(mRegistTelePhoneYzm, mTelephoneyzmDeleteFramelayout, null);
        mEditNickNameEt = new EditTextHolder(mRegistNickName, mNickNameDeleteFramelayout, null);
        mEditPassWordEt = new EditTextHolder(mRegistPassword, mPasswordDeleteFramelayout, null);
        mRegistTelePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mRegistTelePhoneYzm.setEnabled(true);
                Tele_button.setEnabled(true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mRegistTelePhoneYzm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String yzm = mRegistTelePhoneYzm.getText().toString();
                if(yzm.length()==textsize){
                    BmobSMS.verifySmsCode(getApplication(),mRegistTelePhone.getText().toString(), yzm, new VerifySMSCodeListener() {
                        @Override
                        public void done(BmobException ex) {
                            // TODO Auto-generated method stub
                            if(ex==null){//短信验证码已验证成功
                                Log.i("bmob", "验证通过");
                                mRegistTelePhoneYzm.setEnabled(false);
                                Tele_button.setEnabled(false);
                            }else{
                                Log.i("bmob", "验证失败：code ="+ex.getErrorCode()+",msg = "+ex.getLocalizedMessage());
                            }
                        }
                    });
                }
            }
        });
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRegistEmail.setOnClickListener(RegisterActivity.this);
                mRegistNickName.setOnClickListener(RegisterActivity.this);
                mRegistPassword.setOnClickListener(RegisterActivity.this);
                mRegistTelePhone.setOnClickListener(RegisterActivity.this);
                mEditUserNameEt.setmOnEditTextFocusChangeListener(RegisterActivity.this);
                mEditNickNameEt.setmOnEditTextFocusChangeListener(RegisterActivity.this);
                mEditPassWordEt.setmOnEditTextFocusChangeListener(RegisterActivity.this);
                mEditTeleNameEt.setmOnEditTextFocusChangeListener(RegisterActivity.this);
                mEditTeleYzm.setmOnEditTextFocusChangeListener(RegisterActivity.this);
            }
        }, 200);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        event.getKeyCode();
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.KEYCODE_ESCAPE:
                Message mess = Message.obtain();
                mess.what = HANDLER_REGIST_HAS_NO_FOCUS;
                mHandler.sendMessage(mess);
                break;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                mSoftManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                Message mess = Message.obtain();
                mess.what = HANDLER_REGIST_HAS_NO_FOCUS;
                mHandler.sendMessage(mess);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case HANDLER_REGIST_HAS_NO_FOCUS:
                mIsShowTitle.setVisibility(View.GONE);
                mRegistReminder.setVisibility(View.VISIBLE);
                mLogoImg.setVisibility(View.VISIBLE);
                break;
            case HANDLER_REGIST_HAS_FOCUS:
                mLogoImg.setVisibility(View.GONE);
                mRegistReminder.setVisibility(View.GONE);
                mIsShowTitle.setVisibility(View.VISIBLE);
                mLeftTitle.setText(R.string.app_sign_in);
                mRightTitle.setText(R.string.app_fogot_password);
                break;
        }

        return false;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_agree_button://注册button
                String email = mRegistEmail.getText().toString();
                String password = mRegistPassword.getText().toString();
                String phone = mRegistTelePhone.getText().toString();
                String nickName = mRegistNickName.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(nickName)) {
                    WinToast.toast(this, R.string.register_is_null);
                    return;
                } else if (!CommonUtils.isEmail(email)) {
                    WinToast.toast(this, R.string.register_email_error);
                    return;
                }else  if(Tele_button.isEnabled()){
                    WinToast.toast(this, R.string.register_get_yzm);
                    return;
                }
                if (DemoContext.getInstance() != null)
                    httpRequest = DemoContext.getInstance().getDemoApi().register(email, nickName, phone, password, this);
//                fillData(email,password,nickName,phone);

                break;
            case R.id.register_user_agreement://用户协议

                break;
            case R.id.et_register_mail:
            case R.id.et_register_password:
            case R.id.et_register_nickname:
                Message mess = Message.obtain();
                mess.what = HANDLER_REGIST_HAS_FOCUS;
                mHandler.sendMessage(mess);
                break;
            case R.id.Tele_button:
               if(!StringFunction.isMobile(mRegistTelePhone.getText().toString())){
                   WinToast.toast(this, "手机号格式有误");
                   mRegistTelePhone.setFocusable(true);
                   return;
               }else{
                   BmobSMS.requestSMSCode(getApplicationContext(), mRegistTelePhone.getText()+"", "mode1", new RequestSMSCodeListener() {

                       @Override
                       public void done(Integer smsId, BmobException ex) {
                           // TODO Auto-generated method stub
                           if (ex == null) {//验证码发送成功
                               Log.i("bmob", "短信id：" + smsId);//用于查询本次短信发送详情
                           }
                       }
                   });
               }
            break;

            case R.id.de_left://登录
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.de_right://忘记密码
                WinToast.toast(this, "忘记密码");
                break;
        }
    }


    private void fillData() {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                Map<String, String> requestParameter = new HashMap<String, String>();

                requestParameter.put("", "");

                String result = NetUtils.sendPostRequest("", requestParameter);
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                Log.e(TAG, "--------onPostExecute-----+" + result);
            }
        }.execute();
    }


    protected void onPause() {
        super.onPause();
        if (mSoftManager == null) {
            mSoftManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        if (getCurrentFocus() != null) {
            mSoftManager.hideSoftInputFromWindow(getCurrentFocus()
                    .getWindowToken(), 0);// 隐藏软键盘
        }
    }

    @Override
    public void onCallApiSuccess(AbstractHttpRequest request, Object obj) {
        if (httpRequest!=null && httpRequest.equals(request)) {

            if (obj instanceof Status) {
                Status status = (Status) obj;
                if (status.getCode() == 200) {
                    WinToast.toast(this, R.string.register_success);

                    Intent intent = new Intent();
                    intent.putExtra(LoginActivity.INTENT_IMAIL, mRegistEmail.getText().toString());
                    intent.putExtra(LoginActivity.INTENT_PASSWORD, mRegistPassword.getText().toString());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else if (status.getCode() == 101) {
                    WinToast.toast(this, R.string.register_user_exits);
                } else {
                    WinToast.toast(this, R.string.register_failure);
                }
            }
        }
    }

    @Override
    public void onCallApiFailure(AbstractHttpRequest request, BaseException e) {

        if (httpRequest!=null && httpRequest.equals(request)) {

            if (e instanceof InternalException) {
                InternalException ie = (InternalException) e;

                Log.e(TAG, "--RegisterActivity-:" + ie.getMessage());
                if (ie.getCode() == 403) {
                    WinToast.toast(this, R.string.register_user_exits);
                }
            }
        }
    }

    /**
     * 本地判断确认密码是否合法
     *
     * @return
     */
    private boolean isUserPasswordRepeateLegal() {
        String strRegisterPassWordRepeat = mRegistPassword.getText()
                .toString();
        if (TextUtils.isEmpty(strRegisterPassWordRepeat)) {
            return false;
        }
        int lenP = strRegisterPassWordRepeat.length();
        if (lenP < 6) {
            return false;
        }
        if (!TextUtils.isEmpty(strRegisterPassWordRepeat)
                && !mRegistPassword.equals(strRegisterPassWordRepeat)) {
            return false;
        }
        return true;
    }

    @Override
    public void onEditTextFocusChange(View v, boolean hasFocus) {

        switch (v.getId()) {

            case R.id.et_register_mail:
            case R.id.et_register_password:
            case R.id.et_register_nickname:
                Message mess = Message.obtain();
                if (hasFocus) {
                    mess.what = HANDLER_REGIST_HAS_FOCUS;
                }
                mHandler.sendMessage(mess);
                break;
        }
    }


    /**
     * 注册邮箱
     */
    private EditText mRegistEmail;
    /**
     * 密码
     */
    private EditText mRegistPassword;
    /**
     * 昵称
     */
    private EditText mRegistNickName;
    /**
     * 手机号
     */
    private EditText mRegistTelePhone;
    /**
     * 验证码
     */
    private EditText mRegistTelePhoneYzm;
    /**
     * 注册button
     */
    private Button mRegisteButton;
    /**
     * 用户协议
     */
    private TextView mRegisteUserAgreement;
    /**
     * 输入邮箱删除按钮
     */
    private FrameLayout mEmailDeleteFramelayout;  /**
     * 输入电话号码删除按钮
     */
    private FrameLayout mTelephoneDeleteFramelayout;
    /**
    * 输入验证码
     */
    private FrameLayout mTelephoneyzmDeleteFramelayout;
    /**
     * 输入密码删除按钮
     */
    private FrameLayout mPasswordDeleteFramelayout;
    /**
     * 输入昵称删除按钮
     */
    private FrameLayout mNickNameDeleteFramelayout;
    /**
     * 提示消息
     */
    private TextView mRegistReminder;
    /**
     * logo
     */
    private ImageView mLogoImg;
    /**
     * 左侧title
     */
    private TextView mLeftTitle;
    /**
     * 右侧title
     */
    private TextView mRightTitle;
    /**
     * backgroud
     */
    private ImageView mImgBackgroud;
    EditTextHolder mEditUserNameEt;
    EditTextHolder mEditTeleNameEt;
    EditTextHolder mEditPassWordEt;
    EditTextHolder mEditNickNameEt;
    EditTextHolder mEditTeleYzm;
    private Handler mHandler;
    /**
     * 软键盘的控制
     */
    private InputMethodManager mSoftManager;
    private AbstractHttpRequest<Status> httpRequest;
    /**
     * 是否展示title
     */
    private RelativeLayout mIsShowTitle;
}
