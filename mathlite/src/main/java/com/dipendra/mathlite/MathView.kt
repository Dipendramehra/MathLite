package com.dipendra.mathlite

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import android.webkit.WebViewClient

class MathView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : WebView(context, attrs) {

    private var pageLoaded = false
    private var pendingLatex: String? = null

    // NEW: scroll control flag
    private var isScrollable: Boolean = true

    init {
        settings.javaScriptEnabled = true
        settings.allowFileAccess = true
        settings.domStorageEnabled = true

        setBackgroundColor(0x00000000)

        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                pageLoaded = true
                pendingLatex?.let {
                    renderLatex(it)
                    pendingLatex = null
                }
            }
        }

        loadUrl("file:///android_asset/template.html")
    }

    // PUBLIC API
    fun setLatex(latex: String) {
        if (pageLoaded) {
            renderLatex(latex)
        } else {
            pendingLatex = latex
        }
    }

    // NEW: user can control scroll behavior
    fun setScrollable(scrollable: Boolean) {
        isScrollable = scrollable
    }

    private fun renderLatex(latex: String) {
        val escaped = latex
            .replace("\\", "\\\\")
            .replace("'", "\\'")
            .replace("\n", "\\n")

        val scrollFlag = if (isScrollable) "true" else "false"

        evaluateJavascript("renderMath('$escaped', $scrollFlag);", null)
    }
}