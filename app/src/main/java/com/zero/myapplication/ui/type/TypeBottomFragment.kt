package com.zero.myapplication.ui.type

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
import com.zero.myapplication.data.model.user.DataType
import com.zero.myapplication.databinding.FragmentTypeBottomBinding

class TypeBottomFragment : SuperBottomSheetFragment() {

    private val binding: FragmentTypeBottomBinding by viewBinding()
    private lateinit var parent: BottomSheet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnSubmit.onClick {
                when {
                    etTipe.text.toString().isEmpty() -> {
                        etTipe.error = resources.getString(R.string.error_filed)
                    }

                    else -> {
                        parent.onSubmit(DataType(etTipe.text.toString()))
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
            TypeBottomFragment()
    }
}

interface BottomSheet {
    fun onSubmit(data: DataType)
}
