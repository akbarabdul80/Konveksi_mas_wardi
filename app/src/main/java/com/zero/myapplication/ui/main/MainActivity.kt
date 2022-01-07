package com.zero.myapplication.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.visible
import com.zero.myapplication.R
import com.zero.myapplication.data.db.RoomDB
import com.zero.myapplication.data.model.user.DataResultAdapter
import com.zero.myapplication.data.model.user.DataUser
import com.zero.myapplication.databinding.ActivityMainBinding
import com.zero.myapplication.ui.client.ClientActivity
import com.zero.myapplication.ui.type.TypeActivity
import com.zero.myapplication.ui.type.TypeBottomFragment
import com.zero.myapplication.ui.user.UserActivity
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var db: RoomDB

    private val viewModel: MainViewModel by viewModel()
    private val binding: ActivityMainBinding by viewBinding()
    private val adapter: MainAdapter by lazy {
        MainAdapter()
    }

    private val data: MutableList<DataResultAdapter> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(binding) {
            rvHistory.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            swDashboard.setOnRefreshListener {
                swDashboard.isRefreshing = false
                viewModel.listenResult()
            }

            llClient.onClick { startActivity(ClientActivity::class.java) }
            llUser.onClick { startActivity(UserActivity::class.java) }
            llType.onClick { startActivity(TypeActivity::class.java) }

            fab.onClick {
                MainBottomFragment.newInstance().show(supportFragmentManager, "Main Bottom")
            }
        }

        viewModel.listenResult().observe(this, {
            binding.rvHistory.visible()
            binding.shDashboard.gone()
            data.clear()
            var userNow = DataUser("")
            it.sortedBy { it.user.id_user }
            var totalPengerjaan = 0
            it.forEach { clientUserType ->
                totalPengerjaan += clientUserType.result.qty
                if (userNow == clientUserType.user) {
                    data.add(
                        DataResultAdapter(
                            clientUserType.result,
                            clientUserType.client,
                            clientUserType.user,
                            clientUserType.type,
                            2
                        )
                    )
                } else {
                    data.add(
                        DataResultAdapter(
                            clientUserType.result,
                            clientUserType.client,
                            clientUserType.user,
                            clientUserType.type,
                            1
                        )
                    )


                    data.add(
                        DataResultAdapter(
                            clientUserType.result,
                            clientUserType.client,
                            clientUserType.user,
                            clientUserType.type,
                            2
                        )
                    )

                    userNow = clientUserType.user
                }

            }

            data.sortBy { it.user.id_user }

            adapter.submitData(data)

            binding.tvTotal.text = "$totalPengerjaan Baju"
        })

    }
}