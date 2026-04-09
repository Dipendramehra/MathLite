package com.dipendra.mathlite

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import android.webkit.WebViewClient

class DiagramView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : WebView(context, attrs) {

    private var pageLoaded = false
    private var pendingJson: String? = null

    init {
        settings.javaScriptEnabled = true
        settings.allowFileAccess = true
        settings.domStorageEnabled = true

        setBackgroundColor(0x00000000)

        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                pageLoaded = true
                pendingJson?.let {
                    drawDiagram(it)
                    pendingJson = null
                }
            }
        }

        loadUrl("file:///android_asset/diagram_template.html")
    }

    fun setDiagram(json: String) {
        if (pageLoaded) {
            drawDiagram(json)
        } else {
            pendingJson = json
        }
    }

    private fun drawDiagram(json: String) {
        // 1. Strip all newlines and carriage returns to prevent JS syntax errors
        val safeJson = json.replace("\n", "").replace("\r", "")

        // 2. Wrap it in a try-catch block inside evaluateJavascript so it doesn't fail silently
        val jsCommand = "try { drawDiagram($safeJson); } catch(e) { console.error('JS Error: ' + e); }"

        evaluateJavascript(jsCommand, null)
    }
}