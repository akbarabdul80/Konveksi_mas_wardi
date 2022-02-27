package com.zero.konveksiku.ui.type

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.konveksiku.data.model.user.DataType
import com.zero.konveksiku.databinding.ListTypeBinding

class TypeAdapter(
    val onClick: (DataType) -> Unit
) : RecyclerView.Adapter<ViewHolder<ListTypeBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ListTypeBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ListTypeBinding>, position: Int) {
        val dataType = listType[position]
        with(holder.binding) {
            tvTitle.text = dataType.nama_type
            root.onClick { onClick.invoke(dataType) }
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