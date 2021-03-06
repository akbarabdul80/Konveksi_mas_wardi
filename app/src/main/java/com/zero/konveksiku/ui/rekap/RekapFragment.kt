package com.zero.konveksiku.ui.rekap

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.visible
import com.zero.konveksiku.data.model.user.DataResultAdapter
import com.zero.konveksiku.data.model.user.DataResultClient
import com.zero.konveksiku.data.model.user.DataUser
import com.zero.konveksiku.databinding.FragmentRekapBinding
import com.zero.konveksiku.ui.main.MainAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class RekapFragment : Fragment() {

    private val binding: FragmentRekapBinding by viewBinding()
    private val viewModel: RekapViewModel by viewModel()
    private val adapterUser: MainAdapter = MainAdapter()
    private val adapterClinet: RekapAdapter = RekapAdapter()

    private val data: MutableList<DataResultAdapter> = ArrayList()
    private val dataClient: MutableList<DataResultClient> = ArrayList()

    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt("position")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvData.also {
                if (position == 0) it.adapter = adapterUser else it.adapter = adapterClinet
                it.layoutManager = LinearLayoutManager(requireContext())
            }

            if (position == 0) initListenerUser() else initListenerClient()

        }
    }

    private fun initListenerClient() {
        viewModel.listenResultClinet().observe(viewLifecycleOwner, {
            binding.rvData.visible()

            if (it.isEmpty()) {
                binding.llNull.visible()
            } else {
                binding.llNull.gone()
            }

            dataClient.clear()

            var clintNow = ""
            it.forEach { clientUserType ->
                if (clintNow == clientUserType.client_id) {
                    dataClient.add(
                        clientUserType.apply {
                            itemType = 2
                        }
                    )
                } else {
                    dataClient.add(
                        DataResultClient(
                            clientUserType.nama_client,
                            clientUserType.client_id,
                            clientUserType.all_qty,
                            clientUserType.nama_type,
                            1
                        )
                    )

                    dataClient.add(
                        clientUserType.apply {
                            itemType = 2
                        }
                    )
                    clintNow = clientUserType.client_id
                }

                Log.e("Item", clintNow)

            }

            Log.e("TAG", "initListenerClient: $dataClient")

            adapterClinet.submitData(dataClient)
        })
    }

    private fun initListenerUser() {
        viewModel.listenResult().observe(viewLifecycleOwner, {
            binding.rvData.visible()

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

            adapterUser.submitData(data)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(position: Int) =
            RekapFragment().apply {
                arguments = Bundle().apply {
                    putInt("position", position)
                }
            }
    }
}