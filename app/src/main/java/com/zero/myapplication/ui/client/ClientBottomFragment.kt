package com.zero.myapplication.ui.client

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.myapplication.R
import com.zero.myapplication.data.model.user.DataClient
import com.zero.myapplication.databinding.FragmentClientBottomBinding

class ClientBottomFragment : SuperBottomSheetFragment() {

    private val binding: FragmentClientBottomBinding by viewBinding()
    private lateinit var parent: BottomSheet

    private var dataClient: DataClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dataClient = it.getParcelable("data")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            if (dataClient != null) {
                etClient.setText(dataClient!!.nama_client)
                tvTitle.text = resources.getString(R.string.edit_client)

                btnSubmit.onClick {
                    when {
                        etClient.text.toString().isEmpty() -> {
                            etClient.error = resources.getString(R.string.error_filed)
                        }

                        else -> {
                            parent.onUpdate(
                                DataClient(
                                    etClient.text.toString(),
                                    dataClient!!.id_client
                                )
                            )
                            dismiss()
                        }
                    }
                }
            } else {
                btnSubmit.onClick {
                    when {
                        etClient.text.toString().isEmpty() -> {
                            etClient.error = resources.getString(R.string.error_filed)
                        }

                        else -> {
                            parent.onSubmit(DataClient(etClient.text.toString()))
                            dismiss()
                        }
                    }
                }
            }

        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parent = context as BottomSheet
    }


    override fun getExpandedHeight(): Int {
        return -2
    }

    override fun isSheetAlwaysExpanded(): Boolean {
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ClientBottomFragment()

        @JvmStatic
        fun newInstance(data: DataClient) =
            ClientBottomFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("data", data)
                }
            }
    }
}

interface BottomSheet {
    fun onSubmit(data: DataClient)
    fun onUpdate(data: DataClient)
    fun onDelete(data: DataClient)
}
