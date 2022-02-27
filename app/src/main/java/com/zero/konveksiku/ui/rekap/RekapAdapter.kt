package com.zero.konveksiku.ui.rekap

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zero.konveksiku.data.model.user.DataResultClient
import com.zero.konveksiku.databinding.ListRekapBinding
import com.zero.konveksiku.databinding.ListSubRekapBinding

class RekapAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return ViewHolder1(
                ListRekapBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else if (viewType == 2) {
            return ViewHolder2(
                ListSubRekapBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        return ViewHolder2(
            ListSubRekapBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].itemType!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.e("TAG", "onBindViewHolder: $position")
        if (holder is ViewHolder1) {
            var total = 0
            data.forEach {
                if (it.client_id == data[position].client_id) {
                    total += it.all_qty
                }
            }
            total -= data[position].all_qty
            holder.bind(data[position], total)
        } else if (holder is ViewHolder2) {
            holder.bind(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder1(private val binding1: ListRekapBinding) :
        RecyclerView.ViewHolder(binding1.root) {
        fun bind(dataResultClient: DataResultClient, total: Int) {
            binding1.tvTitle.text = dataResultClient.nama_client
            binding1.tvTotal.text = total.toString()
        }
    }

    class ViewHolder2(private val binding: ListSubRekapBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(dataResultClient: DataResultClient) {
            binding.tvTitle.text = dataResultClient.nama_type
            binding.tvTotal.text = dataResultClient.all_qty.toString()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<DataResultClient>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    private val data: MutableList<DataResultClient> = ArrayList()
}