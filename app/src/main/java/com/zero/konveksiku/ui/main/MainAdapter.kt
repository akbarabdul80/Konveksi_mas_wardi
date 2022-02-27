package com.zero.konveksiku.ui.main

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zero.konveksiku.data.model.user.DataClient
import com.zero.konveksiku.data.model.user.DataResultAdapter
import com.zero.konveksiku.databinding.ListHistoryBinding
import com.zero.konveksiku.databinding.ListSubHistoryBinding

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return ViewHolder1(
                ListHistoryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else if (viewType == 2) {
            return ViewHolder2(
                ListSubHistoryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        return ViewHolder2(
            ListSubHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    var user: DataClient = DataClient("")
    override fun getItemViewType(position: Int): Int {
        return data[position].itemType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.e("TAG", "onBindViewHolder: $position")
        if (holder is ViewHolder1) {
            var total = 0
            data.forEach {
                if (it.user == data[position].user) {
                    total += it.result.qty
                }
            }
            total -= data[position].result.qty
            holder.bind(data[position], total)
        } else if (holder is ViewHolder2) {
            holder.bind(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder1(private val binding1: ListHistoryBinding) :
        RecyclerView.ViewHolder(binding1.root) {
        fun bind(dataResult: DataResultAdapter, total: Int) {
            binding1.tvTitle.text = dataResult.user.nama_user
            binding1.tvTotal.text = total.toString()
        }
    }

    class ViewHolder2(private val binding: ListSubHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(dataResult: DataResultAdapter) {
            binding.tvTitle.text = "${dataResult.client.nama_client} - ${dataResult.type.nama_type}"
            binding.tvTotal.text = dataResult.result.qty.toString()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<DataResultAdapter>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<DataResultAdapter> = ArrayList()
}