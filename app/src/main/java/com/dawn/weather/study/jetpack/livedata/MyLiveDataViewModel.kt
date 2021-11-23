package com.dawn.weather.study.jetpack.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dawn.weather.study.data.User

/**
 *  @author: LXL
 *  @description: description
 *  @date: 2021/11/22 10:12
 *  原来我们一直使用的都是在Activity中手动获取ViewModel中的数据这种交互方式，但是ViewModel却无法将数据的变化主动通知给Activity
 *  ViewModel的生命周期是长于Activity的，如果把Activity的实例传给ViewModel，就很有可能会因为Activity无法释放而造成内存泄漏，
 *  这是一种非常错误的做法
 *
 *  ，LiveData之所以能够成为Activity与ViewModel之间通信的桥梁，并且还不会有内存泄漏的风险，
 *  靠的就是Lifecycles组件。LiveData在内部使用了Lifecycles组件来自我感知生命周期的变化，
 *  从而可以在Activity销毁的时候及时释放引用，避免产生内存泄漏的问题。
 */
class MyLiveDataViewModel(countReserved: Int) : ViewModel() {
    val counter: LiveData<Int> get() = _counter
    private val _counter = MutableLiveData<Int>()

    //map 转换成LiveData
    /**
     * map()方法，这个方法的作用是将实际包含数据的LiveData和仅用于观察数据的LiveData进行转换。
     */
    private val userLiveData = MutableLiveData<User>()
    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        "${user.firstName} ${user.lastName}"
    }

    //switchMap
    private val userIdLiveData = MutableLiveData<String>()
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }
}



