package com.kzdev.searchview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kzdev.searchview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var adapter: ArrayAdapter<String>
    lateinit var programmingList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        itensList()
        programingAdapterList()
        searchBarConfig()
    }
    
    private fun itensList() {
        programmingList = ArrayList()
        programmingList.add("Filtro limpo SA")
        programmingList.add("Extintores e Cia")
        programmingList.add("Encanamento Mario")
        programmingList.add("Contabiliodade na Média")
        programmingList.add("Seguro quase tudo")
        programmingList.add("Botânica Verdinho")
        programmingList.add("Elevadores Eleven")
        programmingList.add("Portas Fortal")
    }

    private fun programingAdapterList() {
        adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            programmingList
        )

        val programmingLV = binding.idLVProgramming
        programmingLV.adapter = adapter
    }

    private fun searchBarConfig() {
        val searchView = binding.searchBar

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (programmingList.contains(query)) {

                    adapter.filter.filter(query)
                } else {
                    Toast.makeText(this@MainActivity, "Sem Fornecedor...", Toast.LENGTH_LONG)
                        .show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }
}