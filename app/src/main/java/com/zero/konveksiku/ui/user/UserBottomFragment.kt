package com.zero.konveksiku.ui.user

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.konveksiku.R
import com.zero.konveksiku.data.model.user.DataUser
import com.zero.konveksiku.databinding.FragmentUserBottomBinding

class UserBottomFragment : SuperBottomSheetFragment() {

    private val binding: FragmentUserBottomBinding by viewBinding()
    private lateinit var parent: BottomSheet
    private var dataUser: DataUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dataUser = it.getParcelable("data")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            if (dataUser != null) {
                etUser.setText(dataUser!!.nama_user)
                tvTitle.text = resources.getString(R.string.edit_user)
            }
            btnSubmit.onClick {
                when {
                    etUser.text.toString().isEmpty() -> {
                        etUser.error = resources.getString(R.string.error_filed)
                    }

                    else -> {
                        if (dataUser == null) {
                            parent.onSubmit(DataUser(etUser.text.toString()))
                            dismiss()
                        } else {
                            parent.onUpdate(
                                DataUser(
                                    etUser.text.toString(),
                                    dataUser!!.id_user
                                )
                            )
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
            UserBottomFragment()

        @JvmStatic
        fun newInstance(data: DataUser) =
            UserBottomFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("data", data)
                }
            }
    }
}

interface BottomSheet {
    fun onSubmit(data: DataUser)
    fun onUpdate(data: DataUser)
}
