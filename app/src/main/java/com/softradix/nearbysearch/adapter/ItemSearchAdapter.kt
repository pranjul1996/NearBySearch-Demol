package com.softradix.nearbysearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softradix.nearbysearch.data.Businesse
import com.softradix.nearbysearch.databinding.SearchItemLayoutBinding
import com.softradix.nearbysearch.utils.loadImageWithoutShimmer

class ItemSearchAdapter(var itemList: List<Businesse>) :
    RecyclerView.Adapter<ItemSearchAdapter.ItemHolder>() {

    private lateinit var bindiing: SearchItemLayoutBinding

    inner class ItemHolder(var binding: SearchItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(businesse: Businesse?) {
            binding.nameTv.text = businesse?.name
            binding.addressTv.text = businesse?.location?.address1
            binding.locationImg.loadImageWithoutShimmer(
                businesse?.imageUrl ?: "",
                binding.progressBar
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemSearchAdapter.ItemHolder {
        bindiing = SearchItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemHolder(bindiing)
    }

    override fun onBindViewHolder(holder: ItemSearchAdapter.ItemHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size
}