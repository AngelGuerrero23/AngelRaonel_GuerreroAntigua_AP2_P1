package com.android.angelraonel_guerreroantigua_ap2_p1.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.form.AmonestacionFormScreen
import com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.form.AmonestacionFormViewModel
import com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.list.AmonestacionListScreen
import com.android.angelraonel_guerreroantigua_ap2_p1.presentation.amonestacion.list.AmonestacionListViewModel

@Composable
fun RegistroNavHost(
    navController: NavHostController = rememberNavController(),
    innerPadding: PaddingValues
){
    NavHost(
        navController = navController,
        startDestination = Screen.AmonestacionList,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Screen.AmonestacionList>{
            AmonestacionListScreen(
                AmonestacionListViewModel(),
                onAddAmonestacion = {
                    navController.navigate(Screen.AmonestacionForm())
                },
                onEditAmonestacion = {id-> navController.navigate(Screen.AmonestacionForm(amonestacionId = id)) }
            )
        }

        composable<Screen.AmonestacionForm>{
            AmonestacionFormScreen(
                onBack = {navController.navigateUp()}
            )
        }
    }
}