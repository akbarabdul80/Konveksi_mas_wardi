package com.zero.myapplication.ui.user

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.myapplication.data.model.user.DataUser
import com.zero.myapplication.databinding.ListUserBinding

class UserAdapter(
    val onClick: (DataUser) -> Unit
) : RecyclerView.Adapter<ViewHolder<ListUserBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ListUserBinding> = viewBinding(parent)

    override fun onBindViewHolder(holder: ViewHolder<ListUserBinding>, position: Int) {
        val dataUser = listClient[position]
        with(holder.binding) {
            tvTitle.text = dataUser.nama_user
            root.onClick { onClick.invoke(dataUser) }
        }
    }

    override fun getItemCount(): Int = listClient.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<DataUser>) {
        this.listClient.clear()
        this.listClient.addAll(data)
        notifyDataSetChanged()
    }

    private val listClient: MutableList<DataUser> = mutableListOf()

}