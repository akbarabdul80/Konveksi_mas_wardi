package com.zero.myapplication.ui.client

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.myapplication.data.model.user.DataClient
import com.zero.myapplication.databinding.ListClientBinding

class ClientAdapter(
    val onClick: (DataClient) -> Unit
) : RecyclerView.Adapter<ViewHolder<ListClientBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ListClientBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ListClientBinding>, position: Int) {
        val dataClient = listClient[position]
        with(holder.binding) {
            tvTitle.text = dataClient.nama_client
            root.onClick { onClick.invoke(dataClient) }
        }
    }

    override fun getItemCount(): Int = listClient.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<DataClient>) {
        this.listClient.clear()
        this.listClient.addAll(data)
        notifyDataSetChanged()
    }

    private val listClient: MutableList<DataClient> = mutableListOf()

}