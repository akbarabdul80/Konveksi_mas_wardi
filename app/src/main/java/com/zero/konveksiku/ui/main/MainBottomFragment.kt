package com.zero.konveksiku.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast
import com.zero.konveksiku.R
import com.zero.konveksiku.data.model.user.*
import com.zero.konveksiku.databinding.FragmentMainBottomBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class MainBottomFragment : SuperBottomSheetFragment() {

    private val binding: FragmentMainBottomBinding by viewBinding()
    private val viewModel: MainViewModel by viewModel()
    val dataUser: MutableList<DataUser> = mutableListOf()
    val dataClient: MutableList<DataClient> = mutableListOf()
    val dataType: MutableList<DataType> = mutableListOf()

    lateinit var dataUserSelected: DataUser
    lateinit var dataClientSelected: DataClient
    lateinit var dataTypeSelected: DataType

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")

        initListener()
        with(binding) {

            ddUser.setOnItemClickListener { _, _, position, _ ->
                dataUserSelected = dataUser[position]
                menuUser.isErrorEnabled = false
            }

            ddClient.setOnItemClickListener { _, _, position, _ ->
                dataClientSelected = dataClient[position]
                menuClient.isErrorEnabled = false
            }

            ddType.setOnItemClickListener { _, _, position, _ ->
                dataTypeSelected = dataType[position]
                menuType.isErrorEnabled = false
            }

            btnSubmit.onClick {
                when {
                    !this@MainBottomFragment::dataUserSelected.isInitialized -> {
                        menuUser.error = "Silahkan Pilih User"
                    }
                    !this@MainBottomFragment::dataClientSelected.isInitialized -> {
                        menuClient.error = "Silahkan Pilih Client"

                    }
                    !this@MainBottomFragment::dataTypeSelected.isInitialized -> {
                        menuType.error = "Silahkan Pilih Type"

                    }
                    etJumlah.text.toString().isEmpty() -> {
                        etJumlah.error = "Silahkan masukan jumlah"
                    }

                    else -> {

                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Menambah Hasil Pekerjaan?")
                            .setMessage("Apakah input hasil pekerjaan sudah benar?")
                            .setNegativeButton(resources.getString(R.string.title_cancel)) { dialog, _ ->
                                dialog.dismiss()
                            }
                            .setPositiveButton(resources.getString(R.string.title_yes)) { _, _ ->
                                viewModel.addResult(
                                    DataResult(
                                        dataClientSelected.id_client!!,
                                        dataUserSelected.id_user!!,
                                        dataTypeSelected.id_type!!,
                                        sdf.format(Date()),
                                        etJumlah.text.toString().toInt()
                                    )
                                )
                                toast("Data Berhasil disimpan")
                                dismiss()
                            }
                            .show()

                    }
                }
            }
        }
    }

    private fun initListener() {
        viewModel.listenResultUser().observe(this, {
            val items: MutableList<String> = mutableListOf()
            dataUser.addAll(it)
            it.forEach { data -> items.add(data.nama_user) }
            val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
            (binding.menuUser.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        })
        viewModel.listenResultClient().observe(this, {
            val items: MutableList<String> = mutableListOf()
            dataClient.addAll(it)
            it.forEach { dataClient -> items.add(dataClient.nama_client) }
            val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
            (binding.menuClient.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        })
        viewModel.listenResultType().observe(this, {
            val items: MutableList<String> = mutableListOf()
            dataType.addAll(it)
            it.forEach { data -> items.add(data.nama_type) }
            val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
            (binding.menuType.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        })
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


    override fun getExpandedHeight(): Int {
        return -2
    }

    override fun isSheetAlwaysExpanded(): Boolean {
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainBottomFragment()
    }
}
