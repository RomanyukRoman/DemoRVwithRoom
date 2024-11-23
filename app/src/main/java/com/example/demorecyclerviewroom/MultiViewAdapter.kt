package com.example.demorecyclerviewroom

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demorecyclerview.databinding.CompanyBinding
import com.example.demorecyclerview.databinding.UserBinding
import com.example.demorecyclerviewroom.room.AppViewModel

class MultiViewAdapter(private val appViewModel: AppViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: ArrayList<ItemTypeInterface> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: ArrayList<ItemTypeInterface>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ItemTypeInterface.USER_TYPE ->
                UserViewHolder(UserBinding.inflate(LayoutInflater.from(parent.context), parent, false), appViewModel)
            ItemTypeInterface.COMPANY_TYPE ->
                CompanyViewHolder(CompanyBinding.inflate(LayoutInflater.from(parent.context), parent, false), appViewModel)
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when(list[position].getType()){
            ItemTypeInterface.USER_TYPE ->
                (holder as UserViewHolder).bind(user = list[position] as User)
            ItemTypeInterface.COMPANY_TYPE ->
                (holder as CompanyViewHolder).bind(company = list[position] as Company)
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].getType()
    }
    class UserViewHolder(private val binding: UserBinding, private val appViewModel: AppViewModel): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.userImage.setImageResource(user.image)
            binding.userName.text = user.name
            binding.userEmail.text = user.email
            binding.userCompanyId.text = user.companyId.toString()

            itemView.setOnClickListener {
                appViewModel.deleteItem(user)
            }
        }
    }

    class CompanyViewHolder(private val binding: CompanyBinding, private val appViewModel: AppViewModel): RecyclerView.ViewHolder(binding.root) {
        fun bind(company: Company) {
            binding.companyId.text = company.id.toString()
            binding.companyName.text = company.name
            binding.companyNumber.text = company.number
            binding.companyEmail.text = company.email

            itemView.setOnClickListener {
                appViewModel.deleteItem(company)
            }
        }
    }
}
