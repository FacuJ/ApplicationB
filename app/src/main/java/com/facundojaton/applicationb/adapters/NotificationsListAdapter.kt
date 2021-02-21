package com.facundojaton.applicationb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.facundojaton.applicationb.databinding.LayoutNotificationItemBinding

class NotificationsListAdapter :
    RecyclerView.Adapter<NotificationsListAdapter.NotificationListItemViewHolder>() {

    private var items: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationListItemViewHolder {
        val binding = LayoutNotificationItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NotificationListItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NotificationListItemViewHolder, position: Int) {
        val item = items[holder.adapterPosition]
        holder.binding.tvNotificationMessage.text = item
    }

    fun setNotifications(notifications: ArrayList<String>) {
        this.items = ArrayList()
        this.items.clear()
        this.items.addAll(notifications)
        notifyDataSetChanged()
    }

    class NotificationListItemViewHolder(val binding: LayoutNotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
