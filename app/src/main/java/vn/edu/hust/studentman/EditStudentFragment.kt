package vn.edu.hust.studentman

import vn.edu.hust.studentman.MainActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class EditStudentFragment : Fragment(R.layout.fragment_edit_student) {

    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var updateButton: Button

    companion object {
        private const val ARG_STUDENT = "student"

        fun newInstance(student: StudentModel): EditStudentFragment {
            val fragment = EditStudentFragment()
            val args = Bundle()
            args.putSerializable(ARG_STUDENT, student)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        nameEditText = view.findViewById(R.id.edit_student_name)
        idEditText = view.findViewById(R.id.edit_student_id)
        updateButton = view.findViewById(R.id.btn_update_student)


        val student = arguments?.getSerializable(ARG_STUDENT) as StudentModel
        nameEditText.setText(student.name)
        idEditText.setText(student.id)


        updateButton.setOnClickListener {
            student.name = nameEditText.text.toString()
            student.id = idEditText.text.toString()


            (activity as MainActivity).studentAdapter.notifyDataSetChanged()


            fragmentManager?.popBackStack()
        }
    }
}

private fun Any.notifyDataSetChanged() {
    TODO("Not yet implemented")
}

