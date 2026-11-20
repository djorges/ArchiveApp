package com.example.csvimport.presentation.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.csvimport.R
import com.example.csvimport.domain.DashboardImport
import androidx.compose.ui.tooling.preview.Preview
import com.example.csvimport.domain.ImportStatus
import com.example.csvimport.presentation.ui.theme.CsvImportTheme

@Composable
fun DashboardContent(
    modifier: Modifier = Modifier,
    imports: List<DashboardImport>,
    onUploadCsv: () -> Unit,
    onTemplateClick: () -> Unit,
    onDirectionsClick: () -> Unit,
    onValidateClick: () -> Unit,
    onViewAllClick: () -> Unit,
    onImportClick: (DashboardImport) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {

        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.menu_24),
                    contentDescription = "Menú",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = "CSV Master",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.person_24),
                    contentDescription = "Perfil",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Main content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            // Upload CSV Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clickable(onClick = onUploadCsv),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.outline_drive_folder_upload_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(34.dp)
                    )

                    Text(
                        text = "Subir CSV",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Arrastra y suelta o haz clic para explorar",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            // Statistics
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                @Composable
                fun Statistic(
                    @DrawableRes iconId: Int,
                    title: String,
                    value: String,
                    description: String
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(iconId),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(12.dp)
                            )

                            Spacer(Modifier.width(3.dp))

                            Text(
                                text = title,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }

                        Text(
                            text = value,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = description,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Statistic(
                    R.drawable.outline_location_on_24,
                    "DIRECCIONES",
                    "14,820",
                    "+1,245 hoy"
                )

                Statistic(
                    R.drawable.folder_open_24,
                    "ARCHIVOS",
                    "28",
                    "Importados"
                )

                Statistic(
                    R.drawable.verified_24,
                    "TASA ÉXITO",
                    "98.4%",
                    "Alta precisión"
                )
            }

            Spacer(Modifier.height(10.dp))

            // Quick Actions title
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.bolt_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = "Acciones Rápidas",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(6.dp))

            // Quick Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                @Composable
                fun QuickAction(
                    @DrawableRes iconId: Int,
                    title: String,
                    description: String,
                    onClick: () -> Unit
                ) {
                    Column(
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.surface,
                                RoundedCornerShape(8.dp)
                            )
                            .weight(1f)
                            .clickable(onClick = onClick)
                            .padding(vertical = 8.dp, horizontal = 3.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(iconId),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(30.dp)
                                .background(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    CircleShape
                                )
                                .padding(7.dp)
                        )

                        Spacer(Modifier.height(4.dp))

                        Text(
                            text = title,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )

                        Text(
                            text = description,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1
                        )
                    }
                }

                QuickAction(
                    R.drawable.download_24,
                    "Plantilla CSV",
                    "Formato estándar",
                    onTemplateClick
                )

                QuickAction(
                    R.drawable.map_24,
                    "Ver Direcciones",
                    "Explorar mapa",
                    onDirectionsClick
                )

                QuickAction(
                    R.drawable.outline_location_on_24,
                    "Validar CP",
                    "Geocodificación",
                    onValidateClick
                )
            }

            Spacer(Modifier.height(10.dp))

            // Recent Imports Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Importaciones Recientes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Ver Todo",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable(onClick = onViewAllClick)
                )
            }

            // Imports list
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                contentPadding = PaddingValues(bottom = 8.dp)
            ) {
                items(
                    items = imports,
                    key = { it.fileName }
                ) { import ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onImportClick(import)
                            },
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                painter = if (
                                    import.status == ImportStatus.ERROR
                                ) painterResource(R.drawable.error_24)
                                else painterResource(R.drawable.description_24),
                                contentDescription = null,
                                tint = if (
                                    import.status == ImportStatus.ERROR
                                ) MaterialTheme.colorScheme.error
                                else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier
                                    .size(30.dp)
                                    .background(
                                        MaterialTheme.colorScheme.primaryContainer,
                                        RoundedCornerShape(6.dp)
                                    )
                                    .padding(5.dp)
                            )

                            Spacer(Modifier.width(8.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = import.fileName,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Text(
                                    text = "${import.processed} procesadas • " +
                                            "${import.errors} fallos • " +
                                            import.fileSize,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Text(
                                    text = import.date,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = if (
                                        import.status == ImportStatus.ERROR
                                    ) "ERROR"
                                    else "COMPLETADO",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (
                                        import.status == ImportStatus.ERROR
                                    ) MaterialTheme.colorScheme.error
                                    else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "CSV Master Dashboard",
    device = "spec:width=360dp,height=800dp"
)
@Composable
fun DashboardPreview() {
    CsvImportTheme {
        DashboardScreen(
            imports = listOf(
                DashboardImport(
                    fileName = "direcciones_entregas_q3.csv",
                    date = "Hoy, 10:42 AM",
                    processed = 1245,
                    errors = 0,
                    fileSize = "12.4 MB",
                    status = ImportStatus.COMPLETED
                ),
                DashboardImport(
                    fileName = "clientes_sucursales_cdmx.csv",
                    date = "Ayer, 4:15 PM",
                    processed = 820,
                    errors = 0,
                    fileSize = "3.1 MB",
                    status = ImportStatus.COMPLETED
                ),
                DashboardImport(
                    fileName = "rutas_zona_norte_raw.csv",
                    date = "24 Oct, 09:12 AM",
                    processed = 95,
                    errors = 25,
                    fileSize = "45 KB",
                    status = ImportStatus.ERROR
                )
            ),
            onUploadCsv = {},
            onTemplateClick = {},
            onDirectionsClick = {},
            onValidateClick = {},
            onViewAllClick = {},
            onImportClick = {},
            onSettingsClick = {}
        )
    }
}