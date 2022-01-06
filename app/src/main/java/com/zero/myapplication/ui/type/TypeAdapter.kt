package com.zero.myapplication.ui.type

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.zero.myapplication.data.model.user.DataType
import com.zero.myapplication.databinding.ListTypeBinding

class TypeAdapter : RecyclerView.Adapter<ViewHolder<ListTypeBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ListTypeBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ListTypeBinding>, position: Int) {
        val dataType = listType[position]
        with(holder.binding) {
            tvTitle.text = dataType.nama_type
        }
    }

    override fun getItemCount(): Int = listType.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<DataType>) {
        this.listType.clear()
        this.listType.addAll(data)
        notifyDataSetChanged()
    }

    private val listType: MutableList<DataType> = mutableListOf()

}