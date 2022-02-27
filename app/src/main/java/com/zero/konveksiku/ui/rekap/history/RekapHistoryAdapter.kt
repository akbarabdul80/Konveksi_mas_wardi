package com.zero.konveksiku.ui.rekap.history

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oratakashi.viewbinding.core.binding.recyclerview.ViewHolder
import com.oratakashi.viewbinding.core.binding.recyclerview.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.zero.konveksiku.data.model.user.DataRekap
import com.zero.konveksiku.databinding.ListHistoryRekapBinding

class RekapHistoryAdapter(
    val onClick: (DataRekap) -> Unit
) : RecyclerView.Adapter<ViewHolder<ListHistoryRekapBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ListHistoryRekapBinding> = viewBinding(parent)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder<ListHistoryRekapBinding>, position: Int) {
        val dataRekap = listRekap[position]
        with(holder.binding) {
            tvTitle.text = "${dataRekap.start_date} -\n${dataRekap.end_date}"
            tvTotal.text = dataRekap.qty.toString()
            root.onClick { onClick.invoke(dataRekap) }
        }
    }

    override fun getItemCount(): Int = listRekap.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<DataRekap>) {
        this.listRekap.clear()
        this.listRekap.addAll(data)
        notifyDataSetChanged()
    }

    private val listRekap: MutableList<DataRekap> = mutableListOf()

}