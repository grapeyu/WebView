# WebView  
实验五：Intent  
=====================
实验内容  
--------------------
1、通过自定义的WebView加载URL来验证隐式Intent的使用  
输入URL网址，点击按钮，将发起浏览网页的行为，跳转之后，出现选择项，选择自定义的MyBrowser进行浏览  

主要代码  
-------------------  
1.activity_main.xml  
```  
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center_horizontal"
    tools:context=".MainActivity">

    <EditText
        android:id="@+id/edit"
        android:layout_marginTop="50dp"
        android:layout_width="300dp"
        android:layout_height="50dp"
        android:hint="请输入网址" />
    <Button
        android:id="@+id/button"
        android:layout_marginTop="20dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="浏览该网页" />

</LinearLayout>  
```  
2.MainActivity.java  
```  
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        button=(Button) findViewById(R.id.button);
        edit=(EditText) findViewById(R.id.edit);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:
                Intent intent=new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(edit.getText().toString()));
                startActivity(Intent.createChooser(intent, "请选择浏览器"));
                break;
        }
    }
}
```  
3.MyWebView.java  
```  
public class MyWebView extends AppCompatActivity {
    private WebView webView;
    private  String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        initView();
        setWebView();
    }

    private void initView(){
        webView=(WebView)findViewById(R.id.webview);
        Intent intent=getIntent();
        url=intent.getData().toString();
    }

    private void setWebView(){
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                webView.loadUrl(url);
                return true;
            }
        });
    }
}
```  
4.AndroidManifest.xml  
```  
<application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".MyWebView">
            <intent-filter >
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE"></category>
                <data android:scheme="http"/>
            </intent-filter>
        </activity>
    </application>  
```  

结果截图  
-----------------  

![image](https://github.com/grapeyu/WebView/blob/master/img/5.1.jpg)  


![image](https://github.com/grapeyu/WebView/blob/master/img/5.2.jpg)  


![image](https://github.com/grapeyu/WebView/blob/master/img/5.3.jpg)
