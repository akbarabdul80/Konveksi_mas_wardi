package com.zero.myapplication.ui.user

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
import com.zero.myapplication.data.model.user.DataUser
import com.zero.myapplication.databinding.FragmentUserBottomBinding

class UserBottomFragment : SuperBottomSheetFragment() {

    private val binding: FragmentUserBottomBinding by viewBinding()
    private lateinit var parent: BottomSheet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnSubmit.onClick {
                when {
                    etUser.text.toString().isEmpty() -> {
                        etUser.error = resources.getString(R.string.error_filed)
                    }

                    else -> {
                        parent.onSubmit(DataUser(etUser.text.toString()))
                        dismiss()
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
    }
}

interface BottomSheet {
    fun onSubmit(data: DataUser)
}
