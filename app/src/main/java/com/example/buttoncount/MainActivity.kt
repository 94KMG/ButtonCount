package com.example.buttoncount

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import com.example.buttoncount.ui.theme.ButtonCountTheme
import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ButtonCountTheme {
                Column {
                    Spacer(modifier = Modifier.size(80.dp))
                    ButtonCount()
                }
            }
        }
    }
}

//@Composable
//fun ButtonCount(modifier: Modifier = Modifier) {
//    val activity = LocalContext.current as? Activity // 현재의 컨텍스트를 액티비티로 캐스팅
//    val sharedPref = remember {
//        activity?.getPreferences(Context.MODE_PRIVATE)      // sheredPreferences의 객체를 가져온다
//    }                                                       // SharedPreferences 파일이 이 앱에서만 사용 가능하도록 설정
//
//    var click by remember {
//        val saveClick = sharedPref?.getInt(
//            "lastClick",
//            0
//        ) // SharedPreferences에서 저장된 정수 값을 가져오는 메소드 // "lastClick"인 값을 가져오며, 값이 없으면 기본값 0을 반환합니다.
//        mutableStateOf(
//            saveClick ?: 0
//        ) // Compose에서 관찰 가능한 상태 변수를 만들 때 사용됩니다. 이 변수는 값이 변경될 때 UI를 재구성(recompose)합니다.
//    } //
//    Button(onClick = {
//        click++
//        sharedPref?.edit { // SharedPreferences의 값을 수정하기 위해 Editor 객체를 가져오는 메소드
//            putInt("lastClick", click) // SharedPreferences의 "lastClick" 키에 click 값을 저장합니다.
//        }
//    }) {
//        Text(text = "+ Button: $click")
//    }
//
//    Button(onClick = {
//        click--
//        sharedPref?.edit {
//            putInt("lastClick", click)
//        }
//    }) {
//        Text(text = "- Button: $click")
//    }
//
//
//    Button(onClick = {
//        click = 0
//        sharedPref?.edit {
//            putInt("lastClick", click)
//        }
//    }) {
//        Text(text = "Init Button: $click")
//    }
//
//    Button(onClick = {
//        click = 2 * click
//        sharedPref?.edit {
//            putInt("lastClick", click)
//        }
//    }) {
//        Text(text = "Double Button: $click")
//    }
//
//    Button(onClick = {
//        click = click / 2
//        sharedPref?.edit {
//            putInt("lastClick", click)
//        }
//    }) {
//        Text(text = "Half Button: $click")
//    }
//
//}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@Composable
fun ButtonCount(modifier: Modifier = Modifier) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    val activity = LocalContext.current as? Activity // 현재의 컨텍스트를 액티비티로 캐스팅

    val sharedPref = remember {
        activity?.getPreferences(Context.MODE_PRIVATE)      // sheredPreferences의 객체를 가져온다
    }                                                       // SharedPreferences 파일이 이 앱에서만 사용 가능하도록 설정

    var click by remember {
        val saveClick = sharedPref?.getInt(
            "lastClick",
            0
        ) // SharedPreferences에서 저장된 정수 값을 가져오는 메소드 // "lastClick"인 값을 가져오며, 값이 없으면 기본값 0을 반환합니다.
        mutableStateOf(
            saveClick ?: 0
        ) // Compose에서 관찰 가능한 상태 변수를 만들 때 사용됩니다. 이 변수는 값이 변경될 때 UI를 재구성(recompose)합니다.
    } //

    var time by remember {
        val savetime = sharedPref?.getString(
            "lastTime",
            ""
        )
        mutableStateOf(
            savetime ?: ""
        )
    }
    Button(onClick = {
        click++
        val currentTime = LocalTime.now()
        val time = currentTime.format(formatter).toString()

        sharedPref?.edit { // SharedPreferences의 값을 수정하기 위해 Editor 객체를 가져오는 메소드
            putInt("lastClick", click) // SharedPreferences의 "lastClick" 키에 click 값을 저장합니다.
            putString("lastTime", time)
        }
    }) {
        Text(text = "$click")
    }

    Button(onClick = {
        click--
        val currentTime = LocalTime.now()
        val time = currentTime.format(formatter).toString()
        sharedPref?.edit {
            putInt("lastClick", click)
            putString("lastTime", time)
        }
    }) {
        Text(text = "$click")
    }

    Button(onClick = {
        click = 2 * click
        val currentTime = LocalTime.now()
        val time = currentTime.format(formatter).toString()
        sharedPref?.edit {
            putInt("lastClick", click)
            putString("lastTime", time)
        }
    }) {
        Text(text = "$click")
    }

    Button(onClick = {
        click = click / 2
        val currentTime = LocalTime.now()
        val time = currentTime.format(formatter).toString()

        sharedPref?.edit {
            putInt("lastClick", click)
            putString("lastTime", time)
        }
    }) {
        Text(text = "$click")
    }

    Button(onClick = {
        click = 0
        val currentTime = LocalTime.now()
        val time = currentTime.format(formatter).toString()

        sharedPref?.edit {
            putInt("lastClick", click)
            putString("lastTime", time)
        }
    }) {
        Text(text = "$click")
    }

    Text(text = "$time")

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ButtonCountTheme {
        ButtonCount()
    }
}
