package com.android.angelraonel_guerreroantigua_ap2_p1.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.angelraonel_guerreroantigua_ap2_p1.presentation.borrame.form.BorrameFormScreen
import com.android.angelraonel_guerreroantigua_ap2_p1.presentation.borrame.list.BorrameListScreen

@Composable
fun RegistroNavHost(
    navController: NavHostController = rememberNavController(),
    innerPadding: PaddingValues
){
    NavHost(
        navController = navController,
        startDestination = Screen.BorrameList,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Screen.BorrameList>{
            BorrameListScreen(
                onAddBorrame = {
                    navController.navigate(Screen.BorrameForm())
                },
                onEditBorrame = {id-> navController.navigate(Screen.BorrameForm(borrameId = id)) }
            )
        }

        composable<Screen.BorrameForm>{
            BorrameFormScreen(
                onBack = {navController.navigateUp()}
            )
        }
    }
}