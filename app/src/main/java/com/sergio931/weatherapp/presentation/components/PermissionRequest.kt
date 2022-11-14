package com.sergio931.weatherapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.sergio931.weatherapp.R
import com.sergio931.weatherapp.presentation.theme.Pink40
import com.sergio931.weatherapp.presentation.theme.ralewayFontFamily

@ExperimentalMaterialApi
@ExperimentalPermissionsApi
@Composable
fun PermissionRequest(
    permissions: String,
    permissionDeniedMessage: String,
    navigateToSettingsScreen: () -> Unit,
    content: @Composable () -> Unit
) {
    var doNotShowRationale by remember { mutableStateOf(false) }

    val permissionState =
        rememberPermissionState(permission = permissions)

    PermissionRequired(
        permissionState = permissionState,
        permissionNotGrantedContent = {
            if (doNotShowRationale) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = stringResource(R.string.unavailable_feature),
                        fontFamily = ralewayFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column {
                        Text(
                            text = stringResource(R.string.location_rationale),
                            fontFamily = ralewayFontFamily,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                        ) {
                            Button(
                                onClick = { doNotShowRationale = true },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Pink40
                                ),
                                modifier = Modifier.weight(2f)
                            ) {
                                Text(
                                    text = stringResource(R.string.nope),
                                    fontFamily = ralewayFontFamily,
                                    fontSize = 17.sp,
                                    color = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.weight(0.5f))
                            Button(
                                onClick = { permissionState.launchPermissionRequest() },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Pink40
                                ),
                                modifier = Modifier.weight(2f)
                            ) {
                                Text(
                                    text = stringResource(R.string.ok),
                                    fontFamily = ralewayFontFamily,
                                    fontSize = 17.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        },
        permissionNotAvailableContent = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column {
                    Text(
                        text = permissionDeniedMessage,
                        fontFamily = ralewayFontFamily,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { navigateToSettingsScreen() },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Pink40
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            stringResource(R.string.open_settings),
                            fontFamily = ralewayFontFamily,
                            fontSize = 17.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }) {
        content()
    }
}