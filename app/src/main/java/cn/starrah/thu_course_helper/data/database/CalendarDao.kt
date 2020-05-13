package cn.starrah.thu_course_helper.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import cn.starrah.thu_course_helper.data.declares.*
import java.time.LocalDate

@Dao
abstract class CalendarDao {

    /**
     * 根据日期查找该日的所有日程
     * @param [days] 所有要查找的日期的[LocalDate]对象构成的列表
     * @return [days]中所有日期对应的所有日程构成的列表；返回[CalendarTimeDataWithItem]的列表，可以同时获得时间段数据和对应的日程数据。
     */
    @Transaction
    @Query(
        """
        SELECT * FROM CalendarTimeData
        INNER JOIN CalendarFastSearchHelpTable
        ON CalendarTimeData.id=CalendarFastSearchHelpTable.timeId
        WHERE CalendarFastSearchHelpTable.dayId=:dayIds
    """
    )
    abstract fun findTimesByDays(dayIds: List<Int>): LiveData<List<CalendarTimeDataWithItem>>

    @Query(
        """
        SELECT * FROM CalendarTimeData
        WHERE CalendarTimeData.id=:timeIds
    """
    )
    protected abstract fun _findTimesByIds(timeIds: List<Int>): List<CalendarTimeData>

    @Query(
        """
        SELECT * FROM CalendarTimeData
        WHERE CalendarTimeData.item_id=:itemId
    """
    )
    protected abstract fun _findTimesByItem(itemId: Int): List<CalendarTimeData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun _insertTimes(times: List<CalendarTimeData>)

    @Update
    protected abstract fun _updateTimes(times: List<CalendarTimeData>): Int

    @Insert
    protected abstract fun _insertFastSearch(l: List<CalendarFastSearchHelpTable>)

    @Delete
    protected abstract fun _deleteTimes(times: List<CalendarTimeData>): Int

    /**
     * 插入或更新时间段的数据，并维护快速查找表[CalendarFastSearchHelpTable]。
     *
     * 具体行为是：对每个时间段，首先判断时间段数据改变是否包括日期的改变、需要调整[CalendarFastSearchHelpTable]快速查找表中的内容：
     * 如果不需要，那么直接使用UPDATE语句更新[CalendarTimeData]中的数据；
     * 如果需要，则使用INSERT OR REPLACE语句。对于此前不存在的时间段，该语句会插入数据；
     * 对于已存在的时间段，该语句等价于首先DELETE掉原来的记录（同时[CalendarFastSearchHelpTable]快速查找表中的内容由于CASCADE而自然删除），
     * 然后INSERT进新的记录。最后，计算每个被INSERT的项的日期数据，插入快速查找表中。
     *
     * 注意：对于调整某个[CalendarItemData]的时间段的情况，不应使用本函数，而是应使用[updateTimesInItem]函数，
     * 因为本函数不会删除[CalendarItemData]中原来有、但是[times]中没有传入的时间段。
     * @param [times] 要更新或插入的所有时间段的列表
     */
    @Transaction
    fun updateOrInsertTimes(times: List<CalendarTimeData>) {
        _updateOrInsertTimes(times, _findTimesByIds(times.filter { it.id != 0 }.map { it.id }))
    }

    /**
     * 对于指定的[CalendarItemData]，更新其的所有子时间段。
     *
     * 具体的，对于传入的[times]的每一项，如果它在原来的[CalendarItemData]中不存在，则插入，否则则更新；
     * 对于原来的[CalendarItemData]中的每一个时间段，如果不存在于[times]中，则删除该记录。
     * 无论何种情况，都会自动维护[CalendarFastSearchHelpTable]快速查找表中的记录。
     * @param [times] 修改后，[item]的所有日程构成的列表。
     * @param [item] 日程项
     */
    @Transaction
    fun updateTimesInItem(times: List<CalendarTimeData>, item: CalendarItemData) {
        val oldTimes = _findTimesByItem(item.id)
        val newTimesMap = times.associateBy { it.id }
        val oldToUpdateOnes = oldTimes.filter { newTimesMap.containsKey(it.id) }
        val oldToDeleteOnes = oldTimes - oldToUpdateOnes

        _deleteTimes(oldToDeleteOnes)
        _updateOrInsertTimes(times, oldToUpdateOnes)
    }

    protected fun _updateOrInsertTimes(
        times: List<CalendarTimeData>,
        oldToUpdateOnes: List<CalendarTimeData>
    ) {
        var toUpdateOnes = times.filter { it.id != 0 }
        assert(oldToUpdateOnes.size == toUpdateOnes.size)
        val oldToUpdateOnesMap = oldToUpdateOnes.associateBy { it.id }
        toUpdateOnes = toUpdateOnes.filter {
            oldToUpdateOnesMap[it.id]?.calculateDayIdsInTerm() == it.calculateDayIdsInTerm()
        }
        val toInsertOnes = times - toUpdateOnes

        val updatedCount = _updateTimes(toUpdateOnes)
        assert(updatedCount == toUpdateOnes.size)

        val fastDatas = mutableListOf<CalendarFastSearchHelpTable>()
        toInsertOnes.forEach { t ->
            fastDatas += t.calculateDayIdsInTerm().map { CalendarFastSearchHelpTable(it, t.id) }
        }
        _insertTimes(toInsertOnes)
        _insertFastSearch(fastDatas)
    }


    /**
     * 根据id查询时间段。
     * @param [timeIds] id的列表
     * @return 对应的含Item时间段（[CalendarTimeDataWithItem]）的列表
     */
    @Transaction
    @Query(
        """
        SELECT * FROM CalendarTimeData
        WHERE CalendarTimeData.id=:timeIds
    """
    )
    abstract fun findTimesByIds(timeIds: List<Int>): LiveData<List<CalendarTimeDataWithItem>>

    /**
     * 根据id查询日程。
     * @param [itemIds] id的列表
     * @return 对应的含Times日程项（[CalendarItemDataWithTimes]）的列表
     */
    @Transaction
    @Query(
        """
        SELECT * FROM CalendarItemData
        WHERE CalendarItemData.id=:itemIds
    """
    )
    abstract fun findItemsByIds(itemIds: List<Int>): LiveData<List<CalendarItemDataWithTimes>>

    /**
     * 根据日程项查询时间段。
     *
     * Notes: 建议直接通过调用[CalendarItemData.queryTimes]得到某个日程下的所有时间段；这与调用本函数是等价的。
     * @param [itemId] 日程项的id
     * @return 该日程下所有含时间段（[CalendarTimeData]）的列表
     */
    @Transaction
    @Query(
        """
        SELECT * FROM CalendarTimeData
        WHERE CalendarTimeData.item_id=:itemId
    """
    )
    abstract fun findTimesByItem(itemId: Int): LiveData<List<CalendarTimeData>>

    /**
     * 根据日程项查询时间段。
     *
     * Notes: 建议直接通过调用[CalendarTimeData.queryItem]得到时间段对应的日程；这与调用本函数是等价的。
     * @param [timeId] 时间段的id
     * @return 该日程下所有含时间段（[CalendarTimeData]）的列表
     */
    @Transaction
    @Query(
        """
        SELECT * FROM CalendarItemData
        INNER JOIN CalendarTimeData
        ON CalendarItemData.id=CalendarTimeData.item_id
        WHERE CalendarTimeData.id=:timeId
    """
    )
    abstract fun findItemByTime(timeId: Int): LiveData<CalendarItemData>
}