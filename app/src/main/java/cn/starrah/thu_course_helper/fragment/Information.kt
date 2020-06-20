package cn.starrah.thu_course_helper.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import cn.starrah.thu_course_helper.R
import cn.starrah.thu_course_helper.information.ExamShowActivity
import cn.starrah.thu_course_helper.information.HomeworkShowActivity
import cn.starrah.thu_course_helper.onlinedata.backend.BackendAPIInfo
import com.alibaba.fastjson.JSONObject
import kotlinx.coroutines.launch

class Information : Fragment() {

    private lateinit var buttonPlace: LinearLayout
    private lateinit var classroomJSONItem:List<JSONObject>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.information, container, false)
    }

    override fun onStart() {
        super.onStart()
        buttonPlace = requireActivity().findViewById(R.id.information_place)
        buttonPlace.removeAllViews()
        lifecycleScope.launch {
            loadFromBackend()
            loadOriginalButtons()
        }



    }

    /**
     * 描述：调用后端接口，读取空教室用到的json和其他功能的name-api列表
     * 参数：无
     */
    suspend fun loadFromBackend() {
        var string_classroom = requireActivity().getString(R.string.show_classroom)
        var all_data = BackendAPIInfo()

        for(item in all_data) {
            if((string_classroom.equals(item["name"] as? String)) && (item["children"] as? List<JSONObject> != null)) {
                classroomJSONItem = item["children"] as List<JSONObject>
            }
            else {
                var name:String? = item["name"] as? String
                var url:String? = item["url"] as? String
                if(name != null && url != null) {
                    addOneButton(name, url)
                }
            }
        }
    }

    /**
     * 描述：根据读取后端返回json结果，建立一个按钮并且绑定跳转事件
     * 参数：名称，url
     * 返回：无
     */
    fun addOneButton(name:String, url:String) {
        //显示空教室
        var button_place: View = LayoutInflater.from(requireActivity()).inflate(R.layout.information_button, null)
        var button:Button = button_place.findViewById(R.id.button_this)
        button.setText("查看" + name)
        button.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.data = Uri.parse(url)
            startActivity(intent)
        })
        buttonPlace.addView(button_place)
    }


    /**
     * 描述：读取原有的button--显示空教室，考试，作业
     * 参数：无
     * 返回：无
     */
    fun loadOriginalButtons() {
        //显示空教室
        var button_classroom_place: View = LayoutInflater.from(requireActivity()).inflate(R.layout.information_button, null)
        var button_classroom:Button = button_classroom_place.findViewById(R.id.button_this)
        var string_classroom = requireActivity().getString(R.string.show_classroom)
        button_classroom.setText("查看" + string_classroom)
        button_classroom.setOnClickListener(View.OnClickListener {

        })
        buttonPlace.addView(button_classroom_place)


        //显示作业
        var button_homework_place: View = LayoutInflater.from(requireActivity()).inflate(R.layout.information_button, null)
        var button_homework:Button = button_homework_place.findViewById(R.id.button_this)
        var string_homework = requireActivity().getString(R.string.show_homework)
        button_homework.setText(string_homework)
        button_homework.setOnClickListener(View.OnClickListener {
            var intent = Intent(requireActivity(), HomeworkShowActivity::class.java)
            requireActivity().startActivity(intent)
        })
        buttonPlace.addView(button_homework_place)

        //显示考试
        var button_exam_place: View = LayoutInflater.from(requireActivity()).inflate(R.layout.information_button, null)
        var button_exam:Button = button_exam_place.findViewById(R.id.button_this)
        var string_exam = requireActivity().getString(R.string.show_exam)
        button_exam.setText(string_exam)
        button_exam.setOnClickListener(View.OnClickListener {
            var intent = Intent(requireActivity(), ExamShowActivity::class.java)
            requireActivity().startActivity(intent)
        })
        buttonPlace.addView(button_exam_place)
    }
}