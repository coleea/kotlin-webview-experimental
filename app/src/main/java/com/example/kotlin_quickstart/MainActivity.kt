package com.example.kotlin_quickstart
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_view)

        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true ;
//        val webSettings = webView.settings
//        webSettings.javaScriptEnabled = true

        webView.addJavascriptInterface(WebAppInterface(this), "Android")
        webView.loadUrl("https://next-test-for-webview.vercel.app/test")
    }
}

// 자바스크립트에서 호출할 수 있는 인터페이스 클래스
class WebAppInterface(private val context: Context) {

    // 자바스크립트에서 호출 가능한 메소드
    @JavascriptInterface
    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun performAction() {
        // 여기에 자바스크립트에서 실행할 코틀린 코드 작성
        Log.d("WebAppInterface", "Action performed from JavaScript")
        // 예: 데이터베이스 저장, 알림 생성 등의 기능
    }

    @JavascriptInterface
    fun sendDataToKotlin(data: String): String {
        Log.d("WebAppInterface", "Data received from JavaScript: $data")
        // 데이터 처리 로직
        return "코틀린에서 받은 데이터: $data"
    }
}