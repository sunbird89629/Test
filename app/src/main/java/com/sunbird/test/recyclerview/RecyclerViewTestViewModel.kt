package com.sunbird.test.recyclerview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 作者：王豪
 * 日期：2021/11/14
 * 描述：<必填>
 */
class RecyclerViewTestViewModel : ViewModel() {
    val dataStore = MutableLiveData<List<ItemModel>>(listOf())

    init {
        dataStore.value = listOf(
            ItemModel(0, "item0"),
            ItemModel(1, "item1"),
            ItemModel(2, "item2"),
            ItemModel(3, "item3"),
            ItemModel(4, "item4"),
            ItemModel(5, "item5"),
            ItemModel(6, "item6"),
            ItemModel(7, "item7"),
            ItemModel(8, "item8"),
            ItemModel(9, "item9"),
            ItemModel(10, "item10"),
            ItemModel(11, "item11"),
            ItemModel(12, "item12"),
            ItemModel(13, "item13"),
            ItemModel(14, "item14"),
            ItemModel(15, "item15"),
            ItemModel(16, "item16"),
            ItemModel(17, "item17"),
        )
    }

    fun changeItem(position: Int, changer: (model: ItemModel) -> Unit) {
        val itemList = dataStore.value ?: return
        val model = itemList[position]
        val targetModel = model.copy()
        changer.invoke(targetModel)
        dataStore.postValue(mutableListOf<ItemModel>().apply {
            addAll(itemList)
            set(position, targetModel)
        })
    }
}