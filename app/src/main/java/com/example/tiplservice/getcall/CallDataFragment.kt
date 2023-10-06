package com.example.tiplservice.getcall


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiplservice.R
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class CallDataFragment : Fragment() {

    private lateinit var recyclerview: RecyclerView
    private lateinit var list: ArrayList<DataModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_data, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview=view.findViewById(R.id.recyclerview)

        list = ArrayList()

        val layoutManager = LinearLayoutManager(this.context)
        recyclerview.layoutManager=layoutManager

        val retrofit:Retrofit = Retrofit.Builder().baseUrl("https://scmcloudapi.azurewebsites.net/api/CallComplaint/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api: GAPI = retrofit.create(GAPI::class.java)

        val jsonObject = JsonObject()
        jsonObject.add("CustCode",JsonNull.INSTANCE)

        jsonObject.addProperty("SPCode", "00")
        jsonObject.add("ComplainantId", JsonNull.INSTANCE)
        jsonObject.add("FromDate", JsonNull.INSTANCE)
        jsonObject.add("ToDate", JsonNull.INSTANCE)
        jsonObject.addProperty("CallStatusId", 0)
        jsonObject.add("UserId", JsonNull.INSTANCE)
        jsonObject.add("ServiceLocationCode", JsonNull.INSTANCE)
        jsonObject.addProperty("Priority", 0)
        jsonObject.addProperty("CallReportedThrough", 0)
        jsonObject.addProperty("NatureOfCall", 0)
        jsonObject.add("ComplaintId", JsonNull.INSTANCE)
        jsonObject.add("CompaintDate", JsonNull.INSTANCE)
        jsonObject.add("CustLocationCode", JsonNull.INSTANCE)
        jsonObject.add("ServiceLocationName", JsonNull.INSTANCE)
        jsonObject.add("Complainant", JsonNull.INSTANCE)
        jsonObject.add("Subject", JsonNull.INSTANCE)
        jsonObject.add("Problem", JsonNull.INSTANCE)
        jsonObject.add("PriorityName", JsonNull.INSTANCE)
        jsonObject.add("CategoryName", JsonNull.INSTANCE)
        jsonObject.add("SubCategoryName", JsonNull.INSTANCE)
        jsonObject.add("CallStatusName", JsonNull.INSTANCE)

        val jsonString = jsonObject.toString()

        val call: Call<DMA> = api.getData(jsonObject)


        call.enqueue(object : Callback<DMA?>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<DMA?>,
                response: Response<DMA?>
            ) {
                if (response.isSuccessful){

                    Log.d("ApiResponse", response.body().toString())

                    val adapter = RecyclerAdapter(response.body()!!)
//                   adapter.notifyDataSetChanged()
                    recyclerview.adapter=adapter
                }
            }
            override fun onFailure(call: Call<DMA?>, t: Throwable) {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()

            }

        })


    }


}