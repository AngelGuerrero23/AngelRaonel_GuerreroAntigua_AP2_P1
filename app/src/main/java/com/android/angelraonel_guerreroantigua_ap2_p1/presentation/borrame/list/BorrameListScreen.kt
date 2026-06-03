package com.android.angelraonel_guerreroantigua_ap2_p1.presentation.borrame.list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BorrameListScreen(
    viewModel: BorrameListViewModel,
    onAddBorrame: () -> Unit,
    onEditBorrame: (Int) -> Unit
){

}

@Preview(showBackground = true)
@Composable
private fun BorrameListBodyPreview(){

}