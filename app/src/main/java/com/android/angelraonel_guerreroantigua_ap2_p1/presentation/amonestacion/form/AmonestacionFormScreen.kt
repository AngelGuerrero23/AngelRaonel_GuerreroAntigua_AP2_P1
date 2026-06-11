package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.model.Amonestacion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmonestacionFormScreen(
    onBack: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Listado")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                    }
                }
            )
        }
    ) {padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {
            Text("Formulario Amonestacion")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AmonestacionScreenBodyPreview(){
    AmonestacionFormScreen {}
}