package com.imooc.imooc_voice.view.login;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.imooc.imooc_voice.R;
import com.imooc.imooc_voice.R2;
import com.imooc.imooc_voice.api.RequestCenter;
import com.imooc.imooc_voice.model.newapi.LoginBean;
import com.imooc.imooc_voice.util.ScreenUtils;
import com.imooc.imooc_voice.util.SharePreferenceUtil;
import com.imooc.imooc_voice.view.home.BaseDelegate;
import com.imooc.lib_common_ui.delegate.NeteaseDelegate;
import com.imooc.lib_network.listener.DisposeDataListener;

import butterknife.BindView;
import butterknife.OnClick;

public class PhoneLoginDelegate extends NeteaseDelegate {

	@BindView(R2.id.et_login_phone)
	TextInputEditText mPhone;
	@BindView(R2.id.rv_login_password)
	LinearLayout mLvPassword;
	@BindView(R2.id.rv_login_phone)
	RelativeLayout mRlPhone;
	@BindView(R2.id.et_login_password)
	EditText mEtPassword;
	@Override
	public Object setLayout() {
		return R.layout.delegate_phone_login;
	}

	@Override
	public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View view) throws Exception {
		ScreenUtils.setStatusBarColor(getProxyActivity(), Color.parseColor("#ffffffff"));
	}



	@OnClick(R2.id.tv_login_next)
	void onClickNext(){
		//TODO 校验手机号位数  判断手机号是否已经注册  若未注册过则直接注册
		if(mPhone.getText().toString().equals("")){
			Toast.makeText(getContext(), "请先输入手机号",Toast.LENGTH_SHORT).show();
		}else{
			mRlPhone.setVisibility(View.GONE);
			mLvPassword.setVisibility(View.VISIBLE);
		}

	}

	@OnClick(R2.id.tv_login_login)
	void onClickLogin(){
		/*
		 * phone :13940200592
		 * password :wdj1234567
		 */
		final String phone = mPhone.getText().toString();
		final String password = mEtPassword.getText().toString();
		RequestCenter.login(phone, password, new DisposeDataListener() {
			@Override
			public void onSuccess(Object responseObj) {
				LoginBean bean = (LoginBean) responseObj;
				Toast.makeText(getContext(), bean.getProfile().getNickname()+ "登陆成功", Toast.LENGTH_LONG).show();
				SharePreferenceUtil.getInstance(getContext()).saveUserInfo(bean, phone);
				getSupportDelegate().startWithPop(new BaseDelegate());
			}

			@Override
			public void onFailure(Object reasonObj) {
				Toast.makeText(getContext(), "密码或用户名输入错误",Toast.LENGTH_SHORT).show();
			}
		});
	}
}
