package com.github.reposview.presentation.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.reposview.R
import com.github.reposview.presentation.state.RepositoriesViewState
import com.github.reposview.presentation.ui.adapter.ListRepositoriesAdapter
import com.github.reposview.presentation.viewmodel.ListRepositoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListRepositoriesActivity : AppCompatActivity() {

    private val viewModel: ListRepositoriesViewModel by viewModel()
    private val repositoryAdapter = ListRepositoriesAdapter()

    private lateinit var progressBar: ProgressBar
    private lateinit var repoListRecycleView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViews()
        setupRecycleView()
        handleViewState()

        viewModel.getRepositories()
    }

    private fun setupViews() {
        repoListRecycleView = findViewById(R.id.repoListRecycleView)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun setupRecycleView(){
        repoListRecycleView.apply {
            layoutManager = LinearLayoutManager(this@ListRepositoriesActivity)
            adapter = repositoryAdapter
        }
    }

    private fun handleViewState(){
        viewModel.viewState.observe(this) { state ->
            when (state) {
                is RepositoriesViewState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is RepositoriesViewState.Success -> {
                    progressBar.visibility = View.GONE
                    repositoryAdapter.submitList(state.repositories)
                }

                is RepositoriesViewState.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Erro: ${state.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
