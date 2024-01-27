package com.vikalpchaurasia.customLauncher.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vikalpchaurasia.customLauncher.R
import com.vikalpchaurasia.customLauncher.dayList.DayList

class CalendarAdapter(
    private val formattedCalendar: List<DayList>,
    private val currentDays: String
) :
    RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {
    private var currentPosition = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayTextView: TextView = itemView.findViewById(R.id.dayTextView)
        val linearitem: LinearLayout = itemView.findViewById(R.id.linearitem)
        val view: View = itemView.findViewById(R.id.view)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val week = formattedCalendar[position]
        holder.dayTextView.text = week.name
        if (currentDays == week.days) {

            Log.d("days", week.days)
            currentPosition = holder.layoutPosition
            holder.view.visibility = View.VISIBLE

        }

        when (currentPosition) {
            0 -> {
                holder.linearitem.setBackgroundResource(R.drawable.greencircle)
                holder.dayTextView.setTextColor(Color.WHITE)
            }

        }
    }


    override fun getItemCount(): Int {
        return formattedCalendar.size
    }
}