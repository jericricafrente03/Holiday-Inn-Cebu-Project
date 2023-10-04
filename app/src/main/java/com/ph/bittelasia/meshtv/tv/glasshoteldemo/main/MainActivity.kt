package com.ph.bittelasia.meshtv.tv.glasshoteldemo.main


import android.view.KeyEvent
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.R
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.core.BaseActivity
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.databinding.ActivityMainBinding
import com.ph.bittelasia.meshtv.tv.glasshoteldemo.ui.alert.Alert

class MainActivity :BaseActivity<ActivityMainBinding>() {
    private val alert by lazy { Alert() }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
            KeyEvent.KEYCODE_1 -> { attachFragment(alert, R.id.viewData, "alert") }
            KeyEvent.KEYCODE_2 -> { attachFragment(alert, R.id.viewData, "alert") }
        }
        return super.onKeyUp(keyCode, event)
    }

    override fun getLayout() = R.layout.activity_main
}