package com.lurenjia534.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.ContentAlpha
import com.lurenjia534.myapplication.IPinfo.Ipinfo
import com.lurenjia534.myapplication.IPinfo.RetrofitClient
import com.lurenjia534.myapplication.deviceInfo.DeviceDetails
import com.lurenjia534.myapplication.deviceInfo.getSystemInfo
import com.lurenjia534.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.provider.Settings;
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.core.content.ContextCompat
import com.lurenjia534.myapplication.notificationnotification.sendNotification

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    // 创建 DrawerState 来控制抽屉的打开和关闭
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableIntStateOf(0) }

    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState, // 将 drawerState 传递给 ModalNavigationDrawer
        drawerContent = {
            ModalDrawerSheet(
            ) {
                Text(
                    "My tool box", modifier = Modifier.padding(16.dp), style = TextStyle(
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        fontFamily = FontFamily.Default
                    )
                )
                HorizontalDivider(
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                )
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "IP info Inquire",
                                fontWeight = if (selectedItem == 0) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedItem == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        },
                        selected = selectedItem == 0,
                        onClick = {
                            selectedItem = 0
                            navController.navigate("screen1") {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                        ),
                        icon = {
                            Icon(Icons.Filled.Info, contentDescription = null)
                        }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Device Info",
                                fontWeight = if (selectedItem == 1) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedItem == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        },
                        selected = selectedItem == 1,
                        onClick = {
                            selectedItem = 1
                            navController.navigate("screen2") {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                        ),
                        icon = {
                            Icon(Icons.Filled.Search, contentDescription = null)
                        }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "notification notification",
                                fontWeight = if (selectedItem == 2) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedItem == 2) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        },
                        selected = selectedItem == 2,
                        onClick = {
                            selectedItem = 2
                            navController.navigate("screen3") {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                        ),
                        icon = {
                            Icon(Icons.Filled.Notifications, contentDescription = null)
                        }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Http request",
                                fontWeight = if (selectedItem == 3) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedItem == 3) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        },
                        selected = selectedItem == 3,
                        onClick = {
                            selectedItem = 3
                            navController.navigate("screen4") {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                        ),
                        icon = {
                            Icon(Icons.Filled.Create, contentDescription = null)
                        }
                    )
                }
                // 其他抽屉项...
            }
        },
        gesturesEnabled = true // 启用手势
    ) {
        MaterialTheme {
            Scaffold(
                topBar = {
                    CustomizeTopBar {
                        // 当导航图标被点击时，打开抽屉
                        scope.launch {
                            drawerState.open()
                        }
                    }
                },
                content = { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ) {
                        NavHost(navController, startDestination = "screen1") {
                            composable("screen1") { Screen1() }
                            composable("screen2") { Screen2() }
                            composable("screen3") { Screen3() }
                            composable("screen4") { Screen4() }
                        }
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizeTopBar(onNavigationIconClick: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) { // 当导航图标被点击时，调用 onNavigationIconClick
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        },
        title = {
            Text(text = "My tool box")
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Create, contentDescription = null)
            }
        },
    )
}


@Composable
fun Screen1() {

    var ipInfo by remember { mutableStateOf<Ipinfo?>(null) }   // 用于存储IP信息
    var errorMessage by remember { mutableStateOf<String?>(null) }  // 用于存储错误信息
    var isLoading by remember { mutableStateOf(false) }  // 用于存储加载状态
    var text by remember { mutableStateOf("") }  // 用于存储输入的文本
    var isError by remember { mutableStateOf(false) }  // 用于存储错误状态
    val scope = rememberCoroutineScope()  // 创建一个CoroutineScope实例

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                isError = it.isEmpty()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            readOnly = false,
            textStyle = TextStyle.Default,
            label = { Text("Input IP address") },
            placeholder = { Text("IP address") },
            trailingIcon = {
                IconButton(onClick = { text = "" }) {
                    if (text.isNotEmpty()) {
                        Icon(Icons.Filled.Clear, contentDescription = null)
                    }
                }
            },
            singleLine = true,
            shape = MaterialTheme.shapes.small,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled),
                errorBorderColor = MaterialTheme.colorScheme.error
            )
        )
        Button(
            onClick = {
                isLoading = true
                ipInfo = null
                errorMessage = null
                scope.launch {
                    getIpInfo(text) { result, error ->
                        isLoading = false
                        ipInfo = result
                        errorMessage = error
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = " Inquire ")
        }

        if (isLoading) {
            LinearProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }

        if (ipInfo != null) {
            val context = LocalContext.current
            IpInfoDisplay(ipInfo!!)
            TextButton(onClick = {
                val url = "https://ipinfo.io/"
                val intent = Intent(Intent.ACTION_VIEW)  // 创建一个Intent对象
                intent.data = Uri.parse(url)  // 设置Intent的data属性
                ContextCompat.startActivity(context, intent, null)  // 启动Intent
            }) {
                Text(
                    text = "Powered by IPinfo.io",
                    modifier = Modifier
                        .padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = ContentAlpha.high),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        if (errorMessage != null) {
            Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IpInfoDisplay(ipInfo: Ipinfo) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        // elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IpInfoText(label = "IP", value = ipInfo.ip)
            IpInfoText(label = "City", value = ipInfo.city)
            IpInfoText(label = "Region", value = ipInfo.region)
            IpInfoText(label = "Country", value = ipInfo.country)
            IpInfoText(label = "Location", value = ipInfo.loc)
            IpInfoText(label = "Org", value = ipInfo.org)
            IpInfoText(label = "Postal", value = ipInfo.postal)
            IpInfoText(label = "Timezone", value = ipInfo.timezone)
        }
    }
}

@Composable
fun IpInfoText(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Default
        )
    }
}

fun getIpInfo(ip: String, callback: (Ipinfo?, String?) -> Unit) {
    val call = RetrofitClient.apiService.getIpInfo(ip)

    call.enqueue(object : Callback<Ipinfo> {
        override fun onResponse(call: Call<Ipinfo>, response: Response<Ipinfo>) {
            if (response.isSuccessful) {
                callback(response.body(), null)
            } else {
                callback(null, "Failed to retrieve data")
            }
        }

        override fun onFailure(call: Call<Ipinfo>, t: Throwable) {
            callback(null, t.message)
        }
    })
}

@Composable
fun Screen2() {
    LazyColumn {
        item() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedCard(
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // 创建一个getDeviceInfo的实例
                        val deviceInfoProvider = getSystemInfo(context = LocalContext.current)
                        // 调用getDeviceInfo()方法来获取设备信息
                        val deviceDetails: DeviceDetails = deviceInfoProvider.getDeviceInfo()

                        IpInfoText(label = "Device", value = deviceDetails.deviceName)
                        IpInfoText(label = "Manufacturer", value = deviceDetails.deviceManufacturer)
                        IpInfoText(label = "Version", value = deviceDetails.deviceVersion)
                        IpInfoText(label = "SDK", value = deviceDetails.deviceSDK.toString())
                        IpInfoText(label = "Brand", value = deviceDetails.deviceBrand)
                        IpInfoText(label = "Product", value = deviceDetails.deviceProduct)
                        IpInfoText(label = "Board", value = deviceDetails.deviceBoard)
                        IpInfoText(label = "Hardware", value = deviceDetails.deviceHardware)
                        IpInfoText(label = "Fingerprint", value = deviceDetails.deviceFingerprint)
                        IpInfoText(label = "Host", value = deviceDetails.deviceHost)
                        IpInfoText(label = "ID", value = deviceDetails.deviceID)
                        IpInfoText(label = "Tags", value = deviceDetails.deviceTags)
                        IpInfoText(label = "Build Type", value = deviceDetails.deviceType)
                        IpInfoText(label = "User", value = deviceDetails.deviceUser)
                        IpInfoText(label = "Display", value = deviceDetails.deviceDisplay)
                        IpInfoText(label = "Bootloader", value = deviceDetails.deviceBootloader)
                        IpInfoText(label = "Radio", value = deviceDetails.deviceRadio)
                        IpInfoText(label = "Serial", value = deviceDetails.deviceSerial)
                        IpInfoText(label = "Time", value = deviceDetails.deviceTime.toString())
                        IpInfoText(
                            label = "Security Patch",
                            value = deviceDetails.deviceSecurityPatch
                        )
                        IpInfoText(label = "Codename", value = deviceDetails.deviceCodename)
                        IpInfoText(label = "Incremental", value = deviceDetails.deviceIncremental)
                        IpInfoText(label = "Base OS", value = deviceDetails.deviceBaseOS)
                        IpInfoText(
                            label = "Version Code Name",
                            value = deviceDetails.deviceVersionCodeName
                        )
                        IpInfoText(
                            label = "Version Release",
                            value = deviceDetails.deviceVersionRelease
                        )
                        IpInfoText(
                            label = "Version SDK",
                            value = deviceDetails.deviceVersionSDK.toString()
                        )
                        IpInfoText(
                            label = "Version Security Patch",
                            value = deviceDetails.deviceVersionSecurityPatch
                        )
                        IpInfoText(
                            label = "Version Incremental",
                            value = deviceDetails.deviceVersionIncremental
                        )
                        IpInfoText(
                            label = "Version Base OS",
                            value = deviceDetails.deviceVersionBaseOS
                        )
                        IpInfoText(
                            label = "Version Preview SDK",
                            value = deviceDetails.deviceVersionPreviewSDK.toString()
                        )

                    }
                }
            }
        }

    }
}

@Composable
fun Screen3() {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val context = LocalContext.current
    val hasNotificationPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        NotificationManagerCompat.from(context).areNotificationsEnabled()  // 检查通知权限
    } else {
        true  // 如果设备版本低于Tiramisu，则默认为true
    }

    if (hasNotificationPermission)  //
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = title ,
                onValueChange = { title=it },
                label = { Text("Title") },
                leadingIcon = {
                    Icon(Icons.Filled.Check, contentDescription = null)
                }
            )
            OutlinedTextField(value = content, onValueChange = { content=it }, label = { Text("Content") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    sendNotification(context, title, content)
                }
            }) {
                Text(text = "Send notification")
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Notification permission is required to use this feature",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)  // 如果设备版本高于Tiramisu
                    {
                        if (!NotificationManagerCompat.from(context).areNotificationsEnabled()) {
                            val intent = Intent().apply {
                                action = Settings.ACTION_APP_NOTIFICATION_SETTINGS  // 打开通知设置页面
                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)  // 添加标志
                                putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)  // 设置包名
                            }
                            context.startActivity(intent)
                        } else {
                            // 提示用户通知权限已开启
                            Toast.makeText(context, "通知权限已开启", Toast.LENGTH_SHORT).show()
                        }
                    }
                }) {
                    Text(text = "Grant permission")
                }
            }
        }
    }
}

@Composable
fun Screen4() {
    Column {
        Text("Screen 4")
    }
}