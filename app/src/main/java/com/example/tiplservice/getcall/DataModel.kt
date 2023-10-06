package com.example.tiplservice.getcall

data class DataModel(
    val CallReportedThrough: Int,
    val CallStatusId: Int,
    val CallStatusName: String,
    val CategoryName: String,
    val CompaintDate: String,
    val Complainant: String,
    val ComplainantId: Any,
    val ComplaintId: String,
    val CustCode: Any,
    val CustLocationCode: Any,
    val FromDate: Any,
    val NatureOfCall: Int,
    val Priority: Int,
    val PriorityName: Any,
    val Problem: String,
    val SPCode: Any,
    val ServiceLocationCode: Any,
    val ServiceLocationName: String,
    val SubCategoryName: String,
    val Subject: String,
    val ToDate: Any,
    val UserId: Any
)