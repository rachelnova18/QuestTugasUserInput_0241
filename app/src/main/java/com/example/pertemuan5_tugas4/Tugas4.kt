package com.example.pertemuan5_tugas4

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BiodataScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var tglLahir by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val experienceOptions = listOf(
        stringResource(id = R.string.exp_1),
        stringResource(id = R.string.exp_2),
        stringResource(id = R.string.exp_3)
    )
    var isAndroidSelected by remember { mutableStateOf(false) }
    var isUiUxSelected by remember { mutableStateOf(false) }
    var isWebSelected by remember { mutableStateOf(false) }
    var showPopup by remember { mutableStateOf(false) }
    val isFormValid = namaDepan.isNotBlank() && namaBelakang.isNotBlank() &&
            email.isNotBlank() && tglLahir.isNotBlank() && experience.isNotBlank() &&
            (isAndroidSelected || isUiUxSelected || isWebSelected)
    if (showPopup) {
        LaunchedEffect(Unit) {
            delay(5000L)
            showPopup = false
        }
        AlertDialog(
            onDismissRequest = { showPopup = false },
            title = { Text(text = stringResource(id = R.string.popup_title), fontWeight = FontWeight.Bold) },
            text = { Text(text = stringResource(id = R.string.popup_message)) },
            confirmButton = {}
        )
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.bg_spider),
                contentDescription = stringResource(id = R.string.desc_bg_image),
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.45f).align(Alignment.TopCenter),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.72f)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
                    .shadow(elevation = 20.dp, shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 0.dp, bottomEnd = 0.dp))
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 0.dp, bottomEnd = 0.dp))
                    .background(Color.White)
                    .padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = stringResource(id = R.string.form_title), color = colorResource(id = R.color.purple_title), fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = stringResource(id = R.string.form_subtitle), color = colorResource(id = R.color.grey_subtitle), fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(24.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        OutlinedTextField(value = namaDepan, onValueChange = { namaDepan = it }, label = { Text(stringResource(id = R.string.first_name), fontSize = 12.sp) }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(16.dp), singleLine = true)
                        OutlinedTextField(value = namaBelakang, onValueChange = { namaBelakang = it }, label = { Text(stringResource(id = R.string.last_name), fontSize = 12.sp) }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(16.dp), singleLine = true)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text(stringResource(id = R.string.email_label), fontSize = 12.sp) }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), singleLine = true)
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    }

                    OutlinedTextField(
                        value = tglLahir, onValueChange = { }, readOnly = true, label = { Text(stringResource(id = R.string.dob_label), fontSize = 12.sp) },
                        trailingIcon = {
                            IconButton(onClick = {
                                DatePickerDialog(context, { _, year, month, dayOfMonth -> tglLahir = "$dayOfMonth/${month + 1}/$year" }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
                            }) { Icon(Icons.Default.DateRange, contentDescription = stringResource(id = R.string.desc_icon_calendar)) }
                        }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(16.dp), singleLine = true
                    )
                    }
                }