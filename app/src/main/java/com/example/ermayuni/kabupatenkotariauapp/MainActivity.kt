package com.example.ermayuni.kabupatenkotariauapp

import KabKota
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.GridLayoutManager
import android.content.Intent
import com.example.ermayuni.kabupatenkotariauapp.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var title: String = "Mode List View"
    private var listData: ArrayList<KabKota> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBarTitle(title)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        listData.addAll(KabKotaData.listDataKabKota)
        showRecyclerList()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List View"
                showRecyclerList()
            }
            R.id.action_grid -> {
                title = "Mode Grid View"
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                title = "Mode Card View"
                showRecyclerCardView()
            }
        }
        setActionBarTitle(title)
    }
    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
    private fun showRecyclerList() {
        binding.rvKabKota.layoutManager = LinearLayoutManager(this)
        val kabKotaAdapter = ListKabKotaAdapter(listData)
        binding.rvKabKota.adapter = kabKotaAdapter
        kabKotaAdapter.setOnItemClickCallback(object :
            ListKabKotaAdapter.OnItemClickCallback {
            override fun onItemClicked(data: KabKota) {
                showDataKabKota(data)
            }
        })
    }
    private fun showRecyclerGrid() {
        binding.rvKabKota.layoutManager = GridLayoutManager(this,
            2)
        val gridKabKotaAdapter = GridKabKotaAdapter(listData)
        binding.rvKabKota.adapter = gridKabKotaAdapter
        gridKabKotaAdapter.setOnItemClickCallback(object :
            GridKabKotaAdapter.OnItemClickCallback{
            override fun onItemClicked(data: KabKota) {
                showDataKabKota(data)
            }
        })
    }
    private fun showRecyclerCardView() {
        binding.rvKabKota.layoutManager = LinearLayoutManager(this)
        val cardKabKotaAdapter = CardKabKotaAdapter(listData)
        binding.rvKabKota.adapter = cardKabKotaAdapter
        cardKabKotaAdapter.setOnItemClickCallback(object :
            CardKabKotaAdapter.OnItemClickCallback{
            override fun onItemClicked(data: KabKota) {
                showDataKabKota(data)
            }
        })
    }
    private fun showDataKabKota(data: KabKota){
        val moveWithObjectIntent = Intent(this@MainActivity,
            DetailKabKotaActivity::class.java)
        moveWithObjectIntent.putExtra(DetailKabKotaActivity.EXTRA_KAB_KOTA,
            data)
        startActivity(moveWithObjectIntent)
    }
}