package com.ph.bittelasia.meshtv.tv.glasshoteldemo.ui.alert

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.R
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.core.BaseFragment
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.databinding.FragmentAlertBinding
import kotlinx.coroutines.launch


class Alert : BaseFragment<FragmentAlertBinding>() {
    override fun addContents() {
        super.addContents()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.apply {
                    broadcast.setText("THIS IS A TEST OF EMERGENCY ALERT SYSTEM. THIS IS A TEST ONLY THIS IS A TEST OF EMERGENCY ALERT SYSTEM. THIS IS A TEST ONLY")
                    broadcast.start()
                }
            }
        }
    }
    override fun getLayout(): Int = R.layout.fragment_alert
}