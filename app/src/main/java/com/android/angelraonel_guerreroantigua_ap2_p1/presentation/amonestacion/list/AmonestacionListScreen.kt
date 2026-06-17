package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.amonestacion.model.Amonestacion
import com.android.angelraonel_guerreroantigua_ap2_p1.ui.theme.RegistroAmonestacionTheme

@Composable
fun AmonestacionListScreen(
    viewModel: AmonestacionListViewModel = hiltViewModel(),
    onAddAmonestacion: () -> Unit,
    onEditAmonestacion: (Int) -> Unit
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.navigateToCreate){
      if(state.navigateToCreate){
          onAddAmonestacion()
          viewModel.onEvent(AmonestacionListUiEvent.NavigateToCreate)
      }
    }
    LaunchedEffect(state.navigateToEditId) {
        state.navigateToEditId?.let { id ->
            onEditAmonestacion(id)
            viewModel.onEvent(AmonestacionListUiEvent.NavigateToEdit)
        }
    }
    AmonestacionListBody(
        state = state,
        viewModel::onEvent,
        onAddAmonestacion = onAddAmonestacion,
        onEditClick = onEditAmonestacion
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmonestacionListBody(
    state: AmonestacionListUiState,
    onEvent: (AmonestacionListUiEvent)-> Unit,
    onAddAmonestacion: () -> Unit,
    onEditClick: (Int) -> Unit,
){

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.message) {
        state.message?.let {
            message -> snackbarHostState.showSnackbar(message)
            onEvent(AmonestacionListUiEvent.ClearMessage)
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Amonestaciones")}
            )
        },

        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton =
            {
            FloatingActionButton(
                onClick = {onEvent(AmonestacionListUiEvent.CreateNew)},
                modifier = Modifier.testTag("fab_add")
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Amonestar Empleado"
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if(state.amonestacion.isEmpty())
            {
                Text(
                    text = "No hay Empleados amonestados",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag("empty message"),
                    style = MaterialTheme.typography.bodyLarge
                )
            }else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items =state.amonestacion,
                        key = {it.amonestacionId.toString()}
                    ){amonestacion->
                        AmonestacionItem(
                            amonestacion = amonestacion,
                            onEdit = {amonestacion.amonestacionId?.let { onEvent(
                                AmonestacionListUiEvent.Edit(it)) }}
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AmonestacionItem(
    amonestacion: Amonestacion,
    onEdit:() -> Unit
){
    ElevatedCard(
        onClick = onEdit,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("amonestacion_Item${amonestacion.amonestacionId}")
    ) { Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = amonestacion.nombre.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = amonestacion.razon.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "RD${amonestacion.monto}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
    }

}

@Preview(showBackground = true)
@Composable
private fun AmonestacionListBodyPreview(){
    RegistroAmonestacionTheme {
            val state = AmonestacionListUiState(
                isLoading = false,
                amonestacion = listOf(
                    Amonestacion(1,"Jose Miguel","Llegó tarde",1500.00)
                )
            )
        AmonestacionListBody(state, {}, {}, {})
    }
}