package com.zero.konveksiku.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.startActivity
import com.oratakashi.viewbinding.core.tools.visible
import com.zero.konveksiku.R
import com.zero.konveksiku.data.db.RoomDB
import com.zero.konveksiku.data.model.user.DataResultAdapter
import com.zero.konveksiku.data.model.user.DataUser
import com.zero.konveksiku.databinding.ActivityMainBinding
import com.zero.konveksiku.ui.client.ClientActivity
import com.zero.konveksiku.ui.rekap.RekapActivity
import com.zero.konveksiku.ui.type.TypeActivity
import com.zero.konveksiku.ui.user.UserActivity
import org.koin.android.viewmodel.ext.android.viewModel
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

    @SuppressLint("SetTextI18n")
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
            llRekap.onClick { startActivity(RekapActivity::class.java) }

            fab.onClick {
                MainBottomFragment.newInstance().show(supportFragmentManager, "Main Bottom")
            }
        }

        viewModel.listenResult().observe(this) {
            binding.rvHistory.visible()
            binding.shDashboard.gone()

            if (it.isEmpty()) {
                binding.llNull.visible()
            } else {
                binding.llNull.gone()
            }
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
        }

    }
}