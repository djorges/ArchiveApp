package com.example.csvimport.presentation.ui.screen

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.csvimport.R
import com.example.csvimport.domain.DashboardImport
import com.example.csvimport.presentation.viewmodel.AddressViewModel
import org.koin.compose.viewmodel.koinViewModel


private val csvMimeTypes = arrayOf(
    "text/csv",
    "text/*",
    "application/vnd.ms-excel"
)

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: AddressViewModel = koinViewModel(),
    imports: List<DashboardImport> = emptyList(),
    onUploadCsv: () -> Unit = {},
    onTemplateClick: () -> Unit = {},
    onDirectionsClick: () -> Unit= {},
    onValidateClick: () -> Unit = {},
    onViewAllClick: () -> Unit = {},
    onImportClick: (DashboardImport) -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val context = LocalContext.current

    val openCsvLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ){ uri ->
        uri?.let{
            //persist permission
            val contentResolver = context.contentResolver
            try {
                contentResolver.takePersistableUriPermission(it, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }catch (e: SecurityException) {
                Log.e("AddressScreen", "Persist permission failed", e)
            }
            viewModel.importCsv(contentResolver, it)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 2.dp
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.dashboard_24),
                            contentDescription = "Dashboard"
                        )
                    },
                    label = { Text("Dashboard") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onSettingsClick,
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.settings_24),
                            contentDescription = "Ajustes"
                        )
                    },
                    label = { Text("Ajustes") }
                )
            }
        }
    ) { innerPadding ->

        DashboardContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            imports = imports,
            onUploadCsv = {
                openCsvLauncher.launch(csvMimeTypes)
            },
            onTemplateClick = onTemplateClick,
            onDirectionsClick = onDirectionsClick,
            onValidateClick = onValidateClick,
            onViewAllClick = onViewAllClick,
            onImportClick = onImportClick
        )
    }
}