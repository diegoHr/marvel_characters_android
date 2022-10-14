package com.herev.diego.marvelcharacters.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.herev.diego.marvelcharacters.R
import com.herev.diego.marvelcharacters.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHost =  supportFragmentManager
            .findFragmentById(R.id.container_fragment) as NavHostFragment


        val graph = navHost.navController.navInflater.inflate(R.navigation.nav_graph)
        navHost.navController.graph = graph

    }
}