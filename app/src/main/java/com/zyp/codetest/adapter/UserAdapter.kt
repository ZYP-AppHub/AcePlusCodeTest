package com.zyp.codetest.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zyp.codetest.view.UserDetailActivity
import com.zyp.codetest.databinding.ListItemUserBinding
import com.zyp.codetest.model.User

class UserAdapter constructor(val context: Context) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var userList = mutableListOf<User>()

    class ViewHolder(val binding: ListItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        holder.binding.txtUsername.text = "Name : " + user.name
        holder.binding.txtEmail.text = "Email : " + user.email
        holder.binding.itemCardUser.setOnClickListener {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra("user_data", user)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setItemList(userList: List<User>) {
        this.userList = userList.toMutableList()
        notifyDataSetChanged()
    }

}