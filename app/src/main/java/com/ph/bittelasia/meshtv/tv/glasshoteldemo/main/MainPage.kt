package com.ph.bittelasia.meshtv.tv.glasshoteldemo.main

import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.bumptech.glide.Glide
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.utils.ViewModelFactory
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.R
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.controller.main.MainAdapter
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.core.BaseFragment
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.data.ProjectData
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.db.GlassDB
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.database.repository.Repository
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.databinding.FragmentMainPageBinding
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.utils.GlassViewModel
import kotlinx.coroutines.launch

class MainPage : BaseFragment<FragmentMainPageBinding>() {
    private lateinit var myViewModel: GlassViewModel

    override fun addContents() {
        super.addContents()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val data = ProjectData()
                val repo = Repository(data, GlassDB.db(requireContext()),requireContext())
                val ui = MainAdapter()
                val factoryVm = ViewModelFactory(repo)
                myViewModel = ViewModelProvider(this@MainPage, factoryVm)[GlassViewModel::class.java]
                launch {
                    Glide.with(this@MainPage).load(R.drawable.n1).into(binding.ivBackground)
                    binding.apply {
                        val typeface = ResourcesCompat.getFont(requireContext(), R.font.selawksl)
                        ivLogo.load(R.drawable.logo)
                        tc1.typeface = typeface
                        tc2.typeface = typeface
                        tc3.typeface = typeface
                        uiRv.apply {
                            adapter = ui
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            myViewModel.mainUi.observe(viewLifecycleOwner) {
                                ui.submitList(it.data)
                            }
                        }
                    }
                }
            }
        }
    }
    override fun getLayout() = R.layout.fragment_main_page

}