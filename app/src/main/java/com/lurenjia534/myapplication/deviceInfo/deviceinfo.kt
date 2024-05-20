package com.lurenjia534.myapplication.deviceInfo

import android.Manifest
import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.telephony.TelephonyManager
import android.content.pm.PackageManager

class getSystemInfo(private val context: Context) {
    fun getDeviceInfo() : DeviceDetails {
        val deviceName: String = android.os.Build.MODEL // 设备名称
        val deviceManufacturer: String = android.os.Build.MANUFACTURER // 设备制造商
        val deviceVersion: String = android.os.Build.VERSION.RELEASE // 设备系统版本
        val deviceSDK: Int = android.os.Build.VERSION.SDK_INT  // 设备SDK版本
        val deviceBrand: String = android.os.Build.BRAND  // 设备品牌
        val deviceProduct: String = android.os.Build.PRODUCT  // 设备产品
        val deviceBoard: String = android.os.Build.BOARD  // 设备主板
        val deviceHardware: String = android.os.Build.HARDWARE  // 设备硬件
        val deviceFingerprint: String = android.os.Build.FINGERPRINT  // 设备指纹
        val deviceHost: String = android.os.Build.HOST  // 设备主机
        val deviceID: String = android.os.Build.ID  // 设备ID
        val deviceTags: String = android.os.Build.TAGS  // 设备标签
        val deviceType: String = android.os.Build.TYPE // 设备类型
        val deviceUser: String = android.os.Build.USER  // 设备用户
        val deviceDisplay: String = android.os.Build.DISPLAY  // 设备显示
        val deviceBootloader: String = android.os.Build.BOOTLOADER  // 设备引导程序
        val deviceRadio: String = Build.getRadioVersion() ?: "Unknown" // 设备无线电版本
        val deviceSerial: String = if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            try {
                Build.getSerial() ?: "Unknown"
            } catch (e: SecurityException) {
                "Permission denied"
            }
        } else {
            "Permission not granted"
        }
        val deviceTime: Long = android.os.Build.TIME // 设备时间
        val deviceSecurityPatch: String = android.os.Build.VERSION.SECURITY_PATCH  // 设备安全补丁
        val deviceCodename: String = android.os.Build.VERSION.CODENAME  // 设备代号
        val deviceIncremental: String = android.os.Build.VERSION.INCREMENTAL  // 设备增量
        val deviceBaseOS: String = android.os.Build.VERSION.BASE_OS  // 设备基础操作系统
        val deviceVersionCodeName: String = android.os.Build.VERSION.CODENAME  // 设备版本代号
        val deviceVersionRelease: String = android.os.Build.VERSION.RELEASE  // 设备版本发布
        val deviceVersionSDK: Int = android.os.Build.VERSION.SDK_INT  // 设备版本SDK
        val deviceVersionSecurityPatch: String = android.os.Build.VERSION.SECURITY_PATCH  // 设备版本安全补丁
        val deviceVersionIncremental: String = android.os.Build.VERSION.INCREMENTAL  // 设备版本增量
        val deviceVersionBaseOS: String = android.os.Build.VERSION.BASE_OS  // 设备版本基础操作系统
        val deviceVersionPreviewSDK: Int = android.os.Build.VERSION.PREVIEW_SDK_INT // 设备版本预览SDK

        return DeviceDetails(
            deviceName,
            deviceManufacturer,
            deviceVersion,
            deviceSDK,
            deviceBrand,
            deviceProduct,
            deviceBoard,
            deviceHardware,
            deviceFingerprint,
            deviceHost,
            deviceID,
            deviceTags,
            deviceType,
            deviceUser,
            deviceDisplay,
            deviceBootloader,
            deviceRadio,
            deviceSerial,
            deviceTime,
            deviceSecurityPatch,
            deviceCodename,
            deviceIncremental,
            deviceBaseOS,
            deviceVersionCodeName,
            deviceVersionRelease,
            deviceVersionSDK,
            deviceVersionSecurityPatch,
            deviceVersionIncremental,
            deviceVersionBaseOS,
            deviceVersionPreviewSDK
        )
    }
}