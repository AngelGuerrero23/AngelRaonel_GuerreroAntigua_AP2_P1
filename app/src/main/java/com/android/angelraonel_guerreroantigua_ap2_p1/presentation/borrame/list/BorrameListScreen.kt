package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.borrame.list

import android.R.id.message
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.android.angelraonel_guerreroantigua_ap2_p1.domain.borrame.model.Borrame

@Composable
fun BorrameListScreen(
    viewModel: BorrameListViewModel,
    onAddBorrame: () -> Unit,
    onEditBorrame: (Int) -> Unit
){

}

@Composable
fun BorrameBody(

){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BorrameListBody(
    onAddBorrame: () -> Unit,
    onEditBorrame: (Int) -> Unit,

){
    Scaffold(
        snackbarHost ={ SnackbarHostState()},
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddBorrame,
                modifier = Modifier.testTag("fab_add")
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar Borrame"
                )
            }
        }
    ) {padding ->
        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize()
        ){
            Text(
                text = "No hay borrame",
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag("empty message"),
                style = MaterialTheme.typography.bodyLarge
            )
        }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.SpaceAround
            ){

        }
    }
}





@Preview(showBackground = true)
@Composable
private fun BorrameListBodyPreview(){
    MaterialTheme{
        val state = BorrameListUiState(

        )
    }

    BorrameListBody({},{})}