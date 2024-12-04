package vn.edu.hust.studentman

import java.io.Serializable


data class StudentModel(
    var name: String,  // name
    var id: String     // id
) : Serializable
