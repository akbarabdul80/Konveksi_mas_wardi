package com.zero.myapplication.ui.rekap.history.detail

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
import com.zero.myapplication.data.model.user.DataResultAdapter
import com.zero.myapplication.data.model.user.DataResultClient
import com.zero.myapplication.data.model.user.DataUser
import com.zero.myapplication.databinding.FragmentDetailRekapBinding
import com.zero.myapplication.ui.main.MainAdapter
import com.zero.myapplication.ui.rekap.RekapAdapter
import com.zero.myapplication.ui.rekap.RekapViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailRekapFragment : Fragment() {

    private val binding: FragmentDetailRekapBinding by viewBinding()
    private val viewModel: RekapViewModel by viewModel()
    private val adapterUser: MainAdapter = MainAdapter()
    private val adapterClinet: RekapAdapter = RekapAdapter()

    private val data: MutableList<DataResultAdapter> = ArrayList()
    private val dataClient: MutableList<DataResultClient> = ArrayList()

    private var idResult: Int = 0
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt("position")
            idResult = it.getInt("id_result")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
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
        viewModel.listenResultClinetByID(idResult).observe(viewLifecycleOwner, {
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
        viewModel.listenResultByID(idResult).observe(viewLifecycleOwner, { list ->
            binding.rvData.visible()

            if (list.isEmpty()) {
                binding.llNull.visible()
            } else {
                binding.llNull.gone()
            }

            data.clear()
            var userNow = DataUser("")
            list.sortedBy { it.user.id_user }
            var totalPengerjaan = 0
            list.forEach { clientUserType ->
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


    companion object {
        @JvmStatic
        fun newInstance(position: Int, id_result: Int) =
            DetailRekapFragment().apply {
                arguments = Bundle().apply {
                    putInt("position", position)
                    putInt("id_result", id_result)
                }
            }
    }
}