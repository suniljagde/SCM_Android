package com.example.tiplservice.getcall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiplservice.R

class RecyclerAdapter(val list: ArrayList<DataModel>):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val View = LayoutInflater.from(parent.context).inflate(R.layout.design_layout,parent,false)
        return ViewHolder(View)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val currentItem = list[position]

        holder.apply {

            if (currentItem.UserId != null){
                CallReportedThroughtxt.text=currentItem.UserId.toString()
            }

            if (currentItem.CallStatusId != null){
                CallStatusIdtxt.text=currentItem.CallStatusId.toString()
            }

            if (currentItem.CallStatusName != null){
                CallStatusNametxt.text=currentItem.CallStatusName.toString()
            }

            if (currentItem.CategoryName != null){
                CategoryNametxt.text=currentItem.CategoryName.toString()
            }

            if (currentItem.CompaintDate != null){
                CompaintDate.text=currentItem.CompaintDate.toString()
            }
            if (currentItem.Complainant != null){
                Complainanttxt.text=currentItem.Complainant.toString()
            }
            if (currentItem.ComplainantId != null){
                ComplainantIdtxt.text=currentItem.ComplainantId.toString()
            }
            if (currentItem.ComplaintId != null){
                ComplaintIdtxt.text=currentItem.ComplaintId.toString()
            }

            if (currentItem.CustCode != null){
                CustCodetxt.text=currentItem.CustCode.toString()
            }

            if (currentItem.CustLocationCode != null){
                CustLocationCodetxt.text=currentItem.CustLocationCode.toString()
            }

            if (currentItem.FromDate != null){
                FromDatetxt.text=currentItem.FromDate.toString()
            }

            if (currentItem.NatureOfCall != null){
                NatureOfCalltxt.text=currentItem.NatureOfCall.toString()
            }

            if (currentItem.Priority != null){
                Priority.text=currentItem.Priority.toString()
            }

            if (currentItem.PriorityName != null){
                PriorityNametxt.text=currentItem.PriorityName.toString()
            }

            if (currentItem.Problem != null){
                Problemtxt.text= currentItem.Problem
            }

            if (currentItem.SPCode != null){
                SPCodetxt.text=currentItem.SPCode.toString()
            }

            if (currentItem.ServiceLocationCode != null){
                ServiceLocationCodetxt.text=currentItem.ServiceLocationCode.toString()
            }

            if (currentItem.ServiceLocationName != null){
                ServiceLocationNametxt.text= currentItem.ServiceLocationName
            }

            if (currentItem.SubCategoryName != null){
                SubCategoryNametxt.text=currentItem.SubCategoryName
            }

            if (currentItem.Subject != null){
                Subjecttxt.text= currentItem.Subject
            }
            if (currentItem.ToDate != null){
                ToDatetxt.text=currentItem.ToDate.toString()
            }

            if (currentItem.UserId != null){
                UserIdtxt.text=currentItem.UserId.toString()
            }


        }
    }

    override fun getItemCount(): Int {
    return list.size
    }

    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

        val CallReportedThroughtxt: TextView =itemView.findViewById(R.id.CallReportedThrough_txt)
        val CallStatusIdtxt: TextView =itemView.findViewById(R.id.CallStatusId_txt)
        val CallStatusNametxt: TextView =itemView.findViewById(R.id.CallStatusName_txt)
        val CategoryNametxt: TextView =itemView.findViewById(R.id.CategoryName_txt)
        val CompaintDate: TextView =itemView.findViewById(R.id.CompaintDate_txt)
        val Complainanttxt: TextView =itemView.findViewById(R.id.Complainant_txt)
        val ComplainantIdtxt: TextView =itemView.findViewById(R.id.ComplainantId_txt)
        val ComplaintIdtxt: TextView =itemView.findViewById(R.id.Complainant_txt)
        val CustCodetxt: TextView =itemView.findViewById(R.id.CustCode_txt)
        val CustLocationCodetxt: TextView =itemView.findViewById(R.id.CustLocationCode_txt)
        val FromDatetxt: TextView =itemView.findViewById(R.id.FromDate_txt)
        val NatureOfCalltxt: TextView =itemView.findViewById(R.id.NatureOfCall_txt)
        val Priority: TextView =itemView.findViewById(R.id.Priority_txt)
        val PriorityNametxt: TextView =itemView.findViewById(R.id.PriorityName_txt)
        val Problemtxt: TextView =itemView.findViewById(R.id.Problem_txt)
        val SPCodetxt: TextView =itemView.findViewById(R.id.SPCode_txt)
        val ServiceLocationCodetxt: TextView =itemView.findViewById(R.id.ServiceLocationCode_txt)
        val ServiceLocationNametxt: TextView =itemView.findViewById(R.id.ServiceLocationName_txt)
        val SubCategoryNametxt: TextView =itemView.findViewById(R.id.SubCategoryName_txt)
        val Subjecttxt: TextView =itemView.findViewById(R.id.Subject_txt)
        val ToDatetxt: TextView =itemView.findViewById(R.id.ToDate_txt)
        val UserIdtxt: TextView =itemView.findViewById(R.id.UserId_txt)

    }


}