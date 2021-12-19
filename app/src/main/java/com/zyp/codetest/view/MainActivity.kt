package com.zyp.codetest.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.zyp.codetest.R
import com.zyp.codetest.adapter.UserAdapter
import com.zyp.codetest.api.ApiClient
import com.zyp.codetest.api.ApiService
import com.zyp.codetest.databinding.ActivityMainBinding
import com.zyp.codetest.viewmodel.MainActivityViewModel
import com.zyp.codetest.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainActivityViewModel

    private lateinit var apiService: ApiService

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutSwipeRefresh.setColorSchemeResources(R.color.purple_500)
        binding.layoutSwipeRefresh.isRefreshing = true

        apiService = ApiClient.getInstance()
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(apiService)
        ).get(MainActivityViewModel::class.java)

        userAdapter = UserAdapter(this)
        binding.recyclerUser.layoutManager = GridLayoutManager(this, 1)
        binding.recyclerUser.adapter = userAdapter

        viewModel.userList.observe(this, Observer {
            userAdapter.setItemList(it)
            binding.layoutSwipeRefresh.isRefreshing = false
        })

        viewModel.errorMessage.observe(this, Observer {
            showToast(this, it)
            binding.layoutSwipeRefresh.isRefreshing = false
        })
        viewModel.getAllUsers()

        binding.layoutSwipeRefresh.setOnRefreshListener {
            Handler(mainLooper).postDelayed(
                Runnable {
                    binding.layoutSwipeRefresh.isRefreshing = true
                    viewModel.getAllUsers()
                }, 1000
            )
        }

    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}