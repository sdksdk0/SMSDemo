package cn.zhilinghui.h;

import java.util.HashMap;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button registBtn;
	String APPKET="e25fbbbc36d3";
	String APPSECRCT="95ac47416300218060d45848b3d4d91a";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SMSSDK.initSDK(this,APPKET,APPSECRCT);
		registBtn=(Button) this.findViewById(R.id.regist);
		
		registBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//打开注册页面
				RegisterPage registerPage = new RegisterPage();
				registerPage.setRegisterCallback(new EventHandler() {
				public void afterEvent(int event, int result, Object data) {
				// 解析注册结果
				if (result == SMSSDK.RESULT_COMPLETE) {
				@SuppressWarnings("unchecked")
				HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
				String country = (String) phoneMap.get("country");
				String phone = (String) phoneMap.get("phone"); 

				// 提交用户信息
				registerUser(country, phone);
				}
				}
				});
				registerPage.show(MainActivity.this);
		    }
			
		});
			
	}
		private void registerUser(String country, String phone) {
			Random rnd = new Random();
			int id = Math.abs(rnd.nextInt());
			String uid = String.valueOf(id);
			String nickName = "zhilinghiH" + uid;
			SMSSDK.submitUserInfo(uid, nickName,null, country, phone);
		}

}
