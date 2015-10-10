package com.whitecross.whitecross

import android.view.View
import android.widget.Button
import android.widget.TextView

/**
 * Created by Mrdae on 22.08.2015.
 */
public class StartPageFragment : PageFragment() {

    override var layoutId: Int = R.layout.start_page_fragment

    override fun initUI() {
        val uInfo = getActivity().findViewById(R.id.university_info) as TextView
        val cInfo = getActivity().findViewById(R.id.course_info) as TextView
        val tInfo = getActivity().findViewById(R.id.teacher_info) as TextView
        val uButton = getActivity().findViewById(R.id.button_university) as Button
        val cButton = getActivity().findViewById(R.id.button_course) as Button
        val tButton = getActivity().findViewById(R.id.button_teacher) as Button
        val fButton = getActivity().findViewById(R.id.button_files) as Button

        //university button text
        uButton.setText(getString(R.string.select) + " " + getString(R.string.university))

        //teacher button & university info text
        if ((getActivity() as MainActivity).idUniversity > -1){
            uInfo.setText(getString(R.string.university) + ": ${"getUniversityById(${(getActivity() as MainActivity).idUniversity})"}" )
            tButton.setText(getString(R.string.select) + " " + getString(R.string.teacher))
        }else{
            uInfo.setText(getString(R.string.university) + ": ${getString(R.string.not_selected)}" )
            tButton.setText(getString(R.string.find) + " " + getString(R.string.teacher))
        }

        //course info text
        if ((getActivity() as MainActivity).idCourse > -1){
            cInfo.setText(getString(R.string.course) + ": ${"getCourseById(${(getActivity() as MainActivity).idCourse})"}" )
        }else{
            cInfo.setText(getString(R.string.course) + ": ${getString(R.string.not_selected)}" )
        }

        //course button & teacher info text
        if ((getActivity() as MainActivity).idTeacher > -1){
            tInfo.setText(getString(R.string.teacher) + ": ${"getTeacherById(${(getActivity() as MainActivity).idTeacher})"}" )
            cButton.setText(getString(R.string.select) + " " + getString(R.string.course))
        }else{
            tInfo.setText(getString(R.string.teacher) + ": ${getString(R.string.not_selected)}" )
            cButton.setText(getString(R.string.find) + " " + getString(R.string.course))
        }

        //listener class
        private class NavigationSelector(val itemId : Int) : View.OnClickListener {

            override fun onClick(v: View) {
                var activity = (getActivity() as MainActivity)
                activity.addToBackStack(0)
                activity.selectItem(itemId)
            }

        }

        //sending listeners to buttons
        uButton.setOnClickListener(NavigationSelector(1))
        cButton.setOnClickListener(NavigationSelector(2))
        tButton.setOnClickListener(NavigationSelector(3))
        fButton.setOnClickListener(NavigationSelector(4))
    }

}
