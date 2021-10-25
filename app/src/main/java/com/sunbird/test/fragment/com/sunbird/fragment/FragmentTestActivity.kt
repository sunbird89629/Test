package com.sunbird.test.fragment.com.sunbird.fragment

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sunbird.test.R
import com.sunbird.test.fragment.com.sunbird.fragment.ui.first.FirstFragment
import com.sunbird.test.fragment.com.sunbird.fragment.ui.second.ItemFragment
import org.xutils.common.util.LogUtil

class FragmentTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_test_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FirstFragment.newInstance())
                .commitNow()
        }


        findViewById<Button>(R.id.btn_log_fragments).setOnClickListener {
            supportFragmentManager.fragments.forEachIndexed { index, fragment ->
                LogUtil.i("${index}:${fragment::class.java.simpleName}")
            }
        }

        findViewById<Button>(R.id.btn_add_list_fragment).setOnClickListener {
            val itemFragment = ItemFragment.newInstance(1)
//            itemFragment.lifecycle.addObserver()
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, itemFragment)
                    .commitNow()
            }
        }
//        supportFragmentManager.beginTransaction()
//        supportFragmentManager.getfragment
//        supportFragmentManager.addOnBackStackChangedListener {
//            LogUtil.i("called.....")
//        }
//        supportFragmentManager.add
    }
}