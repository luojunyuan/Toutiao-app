package com.example.toutiaoapplication.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.icu.text.SimpleDateFormat
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.toutiaoapplication.repo.entities.Notice
import com.example.toutiaoapplication.repo.entities.User
import java.text.ParseException
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Context.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, duration).show()
}

fun Fragment.toast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(resId: Int) {
    Toast.makeText(activity, resId, Toast.LENGTH_SHORT).show()
}

fun String.isValid(): Boolean = this.matches(Regex("^.{4,20}$")) // 长度为3到20的所有字符 $?

fun saveUserInfo(context: Context, user: User) {
    val sp = context.getSharedPreferences("user_config", MODE_PRIVATE)
    sp.edit().apply {
        putInt("aid", user.aid)
        putString("udesc", user.descriptor)
        putInt("uid", user.uid)
        putString("umail", user.email)
        putString("uname", user.username)
        putInt("ustat", user.ban)
        putLong("utime", user.register_day)
        putBoolean("save_flag", true)
        putString("cookie", user.cookie)
        apply()
    }
}

fun isAlreadyLogged(context: Context): Boolean {
    val sp = context.getSharedPreferences("user_config", MODE_PRIVATE)
    return sp.getBoolean("save_flag", false)
}

fun loadSavedUserInfo(context: Context): User {
    val sp = context.getSharedPreferences("user_config", MODE_PRIVATE)
    return User(
        aid = sp.getInt("aid", -1),
        descriptor = sp.getString("udesc", "")!!,
        uid = sp.getInt("uid", -1),
        email = sp.getString("umail", "")!!,
        username = sp.getString("uname", "")!!,
        ban = sp.getInt("ustat", -1),
        register_day = sp.getLong("utime", -1),
        cookie = sp.getString("cookie", "no cookie")!!
    )
}

fun clearUserInfo(context: Context) {
    val sp = context.getSharedPreferences("user_config", MODE_PRIVATE)
    sp.edit().apply {
        remove("aid")
        remove("udesc")
        remove("uid")
        remove("umail")
        remove("uname")
        remove("ustat")
        remove("utime")
        remove("cookie")
        putBoolean("save_flag", false)
        apply()
    }
}

fun saveNoticeId(context: Context, notices: List<Notice>) {
    val sp = context.getSharedPreferences("notice_config", MODE_PRIVATE)
    val listId = mutableSetOf<String>()
    for (notice in notices) {
        listId.add(notice.nid.toString())
    }
    sp.edit().apply {
        putStringSet("list_id", listId)
        apply()
    }
}

fun loadNoticeId(context: Context): MutableSet<String>? {
    val sp = context.getSharedPreferences("notice_config", MODE_PRIVATE)
    val default =  setOf<String>()
    return sp.getStringSet("list_id", default)
}

fun getPortSP(context: Context): String? {
    val sp = context.getSharedPreferences("config", MODE_PRIVATE)
    return sp.getString("port_address", URL)
}

fun setPortSP(context: Context, addr: String) {
    saveStringSP(context, "port_address", addr)
}

fun saveStringSP(context: Context, key: String, value: String) {
    val sp = context.getSharedPreferences("config", MODE_PRIVATE)
    sp.edit().apply {
        putString(key, value)
        apply()
    }
}

fun transUnixTime(timestamp: Long): String {
    val trans = timestamp / 1000 - 46800
    return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        .withLocale(Locale.SIMPLIFIED_CHINESE)
        .withZone(ZoneId.of("Asia/Shanghai"))
        .format(java.time.Instant.ofEpochSecond(trans))
    // SimpleDateFormat.getDateTimeInstance().format(timestamp)
}
