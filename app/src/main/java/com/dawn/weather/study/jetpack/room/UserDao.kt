package com.dawn.weather.study.jetpack.room

import androidx.room.*
import com.dawn.weather.study.data.User

/**
 *  @author: LXL
 *  @description: 数据库操作Dao
 *  注意必须使用接口，这点和Retrofit是类似的
 *  @date: 2021/11/22 13:51
 */
@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers(): List<User>

    @Query("select * from User where age > :age")
    fun loadUsersOlderThan(age: Int): List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from User where lastName = :lastName")
    fun deleteUserByLastName(lastName: String): Int

}