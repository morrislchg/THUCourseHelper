package cn.starrah.thu_course_helper

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import cn.starrah.thu_course_helper.data.database.CREP
import cn.starrah.thu_course_helper.data.declares.calendarEntity.CalendarItemData
import cn.starrah.thu_course_helper.data.declares.calendarEntity.CalendarTimeData
import cn.starrah.thu_course_helper.data.declares.calendarEnum.CalendarItemLegalDetailKey
import cn.starrah.thu_course_helper.data.declares.calendarEnum.CalendarItemType
import cn.starrah.thu_course_helper.data.declares.calendarEnum.CalendarTimeType
import cn.starrah.thu_course_helper.data.declares.time.TimeInCourseSchedule
import cn.starrah.thu_course_helper.data.declares.time.TimeInHour
import cn.starrah.thu_course_helper.onlinedata.backend.BACKEND_SITE
import cn.starrah.thu_course_helper.utils.configNotificationChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import kotlin.system.exitProcess

/**
 * 描述：显示一张图片，在这里完成各种加载，然后再跳转到主界面
 */
class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading)

        BACKEND_SITE = try {
            Properties().apply { load(applicationContext.assets.open("backend.properties")) }
                .getProperty("BACKEND_SITE")
        }
        catch (e: Exception) {
            ""
        }

        lifecycleScope.launch {
            val sp = PreferenceManager.getDefaultSharedPreferences(this@LoadingActivity)
            sp.edit {
                //进入时清空当前周
                putInt("currentWeekCourseTable", 0)
                putInt("currentWeekTimeTable", 0)
            }
            val term_id = sp.getString("term_id", null)

            try {
                val errMsg = CREP.initializeDefault(this@LoadingActivity)
                if (errMsg != null) Toast.makeText(this@LoadingActivity, errMsg, Toast.LENGTH_SHORT)
                    .show()
            }
            catch (e: Exception) {
                Toast.makeText(
                    this@LoadingActivity,
                    R.string.errmsg_change_term_fail_exit,
                    Toast.LENGTH_SHORT
                ).show()
                delay(3000)
                exitProcess(0)
            }

            // TODO 测试数据
            loadTestData()

            val the_intent = Intent()
            the_intent.setClass(this@LoadingActivity, MainActivity::class.java)

            if (CREP.term.termId != term_id) the_intent.putExtra(
                "SHOW_TOAST",
                "${resources.getText(R.string.info_auto_term)}${CREP.term.chineseName}"
            )
            startActivity(the_intent)
            finish()
        }

    }


    /**
    描述:测试函数，上传初始化日程
     */
    suspend fun loadTestData() {

        val detail1: MutableMap<CalendarItemLegalDetailKey, String> =
            mutableMapOf<CalendarItemLegalDetailKey, String>()
        detail1[CalendarItemLegalDetailKey.COURSEID] = "114514"
        detail1[CalendarItemLegalDetailKey.COMMENT] = "3学分硬课"
        val item1: CalendarItemData = CalendarItemData(id = 1, name = "数据库", detail = detail1)

        val detail2: MutableMap<CalendarItemLegalDetailKey, String> =
            mutableMapOf<CalendarItemLegalDetailKey, String>()
        detail2[CalendarItemLegalDetailKey.TEACHER] = "王继良"
        detail2[CalendarItemLegalDetailKey.COMMENT] = "大作业写不完了qwqwqwqwqwqwqwq，sgltcltcl"

        val item2: CalendarItemData = CalendarItemData(id = 2, name = "移动软件开发", detail = detail2)

        val detail3: MutableMap<CalendarItemLegalDetailKey, String> =
            mutableMapOf<CalendarItemLegalDetailKey, String>()
        detail3[CalendarItemLegalDetailKey.ORGANIZATION] = "软件学院学生会"
        detail3[CalendarItemLegalDetailKey.COMMENT] = "并没有这种时间的社工，我单纯测试一下"

        val item3: CalendarItemData = CalendarItemData(
            id = 3,
            name = "社工",
            type = CalendarItemType.SOCIALWORK,
            detail = detail3
        )

        val time1: TimeInCourseSchedule = TimeInCourseSchedule(
            startBig = 6,
            startOffsetSmall = 0.0f,
            lengthSmall = 2.0f,
            date = LocalDate.parse("2020-05-19"),
            dayOfWeek = LocalDate.parse("2020-05-19").dayOfWeek
        )
        val data1: CalendarTimeData = CalendarTimeData(
            name = "摸鱼", type = CalendarTimeType.SINGLE_COURSE
            , timeInCourseSchedule = time1, item_id = 1, place = "李文正馆"
        )

        val time2: TimeInCourseSchedule = TimeInCourseSchedule(
            dayOfWeek = DayOfWeek.TUESDAY, startBig = 2, startOffsetSmall = 0.0f,
            lengthSmall = 3.0f
        )
        val data2: CalendarTimeData = CalendarTimeData(
            name = "上课", type = CalendarTimeType.REPEAT_COURSE
            , timeInCourseSchedule = time2, repeatWeeks = mutableListOf(14, 15, 16), item_id = 1
        )

        val list1 = listOf<CalendarTimeData>(data1, data2)


        val time3: TimeInCourseSchedule = TimeInCourseSchedule(
            startBig = 5,
            startOffsetSmall = 0.0f,
            lengthSmall = 2.0f,
            date = LocalDate.parse("2020-05-20"),
            dayOfWeek = LocalDate.parse("2020-05-20").dayOfWeek
        )
        val data3: CalendarTimeData = CalendarTimeData(
            name = "摸鱼", type = CalendarTimeType.SINGLE_COURSE
            , timeInCourseSchedule = time3, item_id = 2, comment = "不能再摸鱼了qwqquq"
        )

        val time4: TimeInCourseSchedule = TimeInCourseSchedule(
            dayOfWeek = DayOfWeek.WEDNESDAY, startBig = 2, startOffsetSmall = 0.0f,
            lengthSmall = 2.0f
        )
        val data4: CalendarTimeData = CalendarTimeData(
            name = "上课", type = CalendarTimeType.REPEAT_COURSE
            , timeInCourseSchedule = time4, repeatWeeks = mutableListOf(14, 15, 16), item_id = 2
        )

        val list2 = listOf<CalendarTimeData>(data3, data4)


        val time5: TimeInCourseSchedule = TimeInCourseSchedule(
            startBig = 6,
            startOffsetSmall = 0.0f,
            lengthSmall = 3.0f,
            date = LocalDate.parse("2020-05-21"),
            dayOfWeek = LocalDate.parse("2020-05-21").dayOfWeek
        )
        val data5: CalendarTimeData = CalendarTimeData(
            name = "摸鱼", type = CalendarTimeType.SINGLE_COURSE
            , timeInCourseSchedule = time5, item_id = 3
        )

        val time6: TimeInCourseSchedule = TimeInCourseSchedule(
            dayOfWeek = DayOfWeek.THURSDAY, startBig = 3, startOffsetSmall = 0.0f,
            lengthSmall = 3.0f
        )
        val data6: CalendarTimeData = CalendarTimeData(
            name = "开会", type = CalendarTimeType.REPEAT_COURSE
            , timeInCourseSchedule = time6, repeatWeeks = mutableListOf(12, 14, 16), item_id = 3
        )

        val time7: TimeInHour = TimeInHour(
            startTime = LocalTime.parse("08:00"), endTime = LocalTime.parse("09:30"),
            dayOfWeek = LocalDate.parse("2020-05-24").dayOfWeek
        )
        val data7: CalendarTimeData = CalendarTimeData(
            name = "吃kebab", type = CalendarTimeType.REPEAT_HOUR
            , timeInHour = time7, item_id = 3, repeatWeeks = mutableListOf(12, 14, 15, 16)
        )
        val list3 = listOf<CalendarTimeData>(data5, data6, data7)


        CREP.updateItemAndTimes(item1, list1)
        CREP.updateItemAndTimes(item2, list2)
        CREP.updateItemAndTimes(item3, list3)


    }


}