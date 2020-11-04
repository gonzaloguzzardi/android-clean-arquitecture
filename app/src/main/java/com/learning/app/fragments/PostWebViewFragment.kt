package com.learning.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.learning.app.databinding.FragmentPostWebViewBinding

class PostWebViewFragment: Fragment() {

    private val args: PostWebViewFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.actionBar?.setHomeButtonEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostWebViewBinding.inflate(inflater, container, false)
        setupWebView(binding.postWebView, binding.loadingProgressBar)
        setupBackButton(binding.actionBarBackButton)
        return binding.root
    }

    private fun setupWebView(webView: WebView, loadingProgressBar: ProgressBar) {
        loadingProgressBar.visibility = View.VISIBLE
        webView.webViewClient = object : WebViewClient() {
            override fun onPageCommitVisible(view: WebView, url: String) {
                loadingProgressBar.visibility = GONE
                super.onPageCommitVisible(view, url)
            }
        }
        webView.loadUrl(args.url)
    }

    private fun setupBackButton(view: View) {
        view.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}