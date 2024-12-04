package vn.edu.hust.studentman

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import vn.edu.hust.studentman.AddStudentFragment
import vn.edu.hust.studentman.EditStudentFragment
import vn.edu.hust.studentman.R
import vn.edu.hust.studentman.StudentModel

class MainActivity : AppCompatActivity() {


  val students = mutableListOf(
    StudentModel("Nguyễn Văn An", "SV001"),
    StudentModel("Trần Thị Bảo", "SV002"),
    StudentModel("Lê Hoàng Cường", "SV003"),
    StudentModel("Phạm Thị Dung", "SV004"),
    StudentModel("Đỗ Minh Đức", "SV005"),
    StudentModel("Đỗ Tiến Đạt", "SV006"),
    StudentModel("Trần Văn Hạnh", "SV007"),
    StudentModel("Nguyễn Cao Cường", "SV008"),

  )


  lateinit var studentAdapter: StudentAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


    studentAdapter = StudentAdapter(
      this,
      students,
      onEditClick = { student ->

        openEditStudentFragment(student)
      },
      onRemoveClick = { student ->

        students.remove(student)
        studentAdapter.notifyDataSetChanged()
        Toast.makeText(this, "Removed ${student.name}", Toast.LENGTH_SHORT).show()
      }
    )

    val listView = findViewById<ListView>(R.id.list_view_students)
    listView.adapter = studentAdapter


    registerForContextMenu(listView)

    // Set OnItemClickListener cho ListView
    listView.setOnItemClickListener { _, _, position, _ ->
      val student = students[position]
      openEditStudentFragment(student)
    }


    findViewById<Button>(R.id.btn_add_new).setOnClickListener {
      openAddStudentFragment()
    }
  }


  private fun openAddStudentFragment() {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.container, AddStudentFragment())
    transaction.addToBackStack(null)
    transaction.commit()
  }


  private fun openEditStudentFragment(student: StudentModel) {
    val transaction = supportFragmentManager.beginTransaction()
    val fragment = EditStudentFragment.newInstance(student)
    transaction.replace(R.id.container, fragment)
    transaction.addToBackStack(null)
    transaction.commit()
  }


  fun addStudent(newStudent: StudentModel) {
    students.add(newStudent)
    studentAdapter.notifyDataSetChanged()
  }
}


