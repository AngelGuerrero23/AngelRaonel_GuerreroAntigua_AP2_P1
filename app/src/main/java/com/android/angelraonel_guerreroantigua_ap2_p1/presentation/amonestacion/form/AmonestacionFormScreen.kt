package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.angelraonel_guerreroantigua_ap2_p1.ui.theme.RegistroAmonestacionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmonestacionFormScreen(
    viewModel: AmonestacionFormViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(state.saved, state.deleted) {
        if (state.saved || state.deleted) {
            onBack()
        }
    }

    AmonestacionFormBody(
        state = state,
        onEvent = viewModel::onEvent,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmonestacionFormBody(
    state: AmonestacionFormUiState,
    onEvent: (AmonestacionFormUiEvent)-> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (state.isNew)
                            "Nueva Amonestacion"
                        else
                            "Editar Amonestacion"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.nombres,
                onValueChange = {
                    onEvent(
                        AmonestacionFormUiEvent.NombreChanged(it)
                    )
                },
                readOnly = !state.isNew,
                label = { Text("Nombre del empleado") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_amonestacion"),
                isError = state.nombreError != null,
                supportingText = {
                    state.nombreError?.let {
                        Text(it)
                    }
                }
            )
            OutlinedTextField(
                value = state.razon,
                onValueChange = {
                    onEvent(
                        AmonestacionFormUiEvent.RazonChanged(it)
                    )
                },
                label = {
                    Text("Razon")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_razon"),
                isError = state.razonError != null,
                supportingText = {
                    state.razonError?.let {
                        Text(it)
                    }
                }
            )

            OutlinedTextField(
                value = state.monto,
                onValueChange = {
                    onEvent(
                        AmonestacionFormUiEvent.MontoChanged(it)
                    )
                },
                label = {
                    Text("Monto")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_monto"),
                isError = state.montoError != null,
                supportingText = {
                    state.montoError?.let {
                        Text(it)
                    }
                },
                singleLine = true
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (!state.isNew) {
                    Button(
                        onClick = {
                            onEvent(AmonestacionFormUiEvent.Delete)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("btn_delete"),
                        enabled = !state.isDeleting,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError
                        )
                    ) {
                        if (state.isDeleting) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.onError,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Eliminar")
                        }
                    }
                }
                Button(
                    onClick = {
                        onEvent(
                            AmonestacionFormUiEvent.Save)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("btn_save"),
                    enabled = !state.isSaving
                ) {
                    if (state.isSaving) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Guardar")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AmonestacionScreenBodyPreview(){
    RegistroAmonestacionTheme() {
        AmonestacionFormBody(
            state = AmonestacionFormUiState(
                amonestacionId = 1,
                "Juan Perez",
                razon = "Llegó tarde",
                monto = "1500.00",
            ),
            onEvent = {},
            onBack = {}
        )
    }
}