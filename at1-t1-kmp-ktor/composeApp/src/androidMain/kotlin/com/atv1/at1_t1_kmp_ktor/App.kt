package com.atv1.at1_t1_kmp_ktor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import com.atv1.at1_t1_kmp_ktor.model.Plant
import com.atv1.at1_t1_kmp_ktor.model.Species
import at1_t1_kmp_ktor.composeapp.generated.resources.Res
import at1_t1_kmp_ktor.composeapp.generated.resources.compose_multiplatform
import com.atv1.at1_t1_kmp_ktor.fetchPlants

@Composable
@Preview
fun App() {
    MaterialTheme {
        var plants by remember { mutableStateOf<List<Plant>>(emptyList()) }
        var currentIndex by remember { mutableStateOf(0) }
        var isLoading by remember { mutableStateOf(true) }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        LaunchedEffect(Unit) {
            try {
                plants = fetchPlants()
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE8F5E9))
                .safeContentPadding()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Catálogo de Plantas",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF1B5E20),
                modifier = Modifier.padding(bottom = 20.dp)
            )

            when {
                isLoading -> {
                    Text("Carregando plantas...")
                }

                errorMessage != null -> {
                    Text("Erro ao carregar: $errorMessage")
                }

                plants.isEmpty() -> {
                    Text("Nenhuma planta encontrada")
                }

                else -> {
                    val plant = plants[currentIndex]

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFC8E6C9)
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = "Informações da Planta",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color(0xFF2E7D32)
                            )

                            Text(
                                text = "Nome científico: ${plant.scientificName}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF1B5E20)
                            )

                            Text(
                                text = "Nome popular: ${plant.popularName}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF1B5E20)
                            )

                            Text(
                                text = "ID da espécie: ${plant.speciesId}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF1B5E20)
                            )

                            Text(
                                text = "Luminosidade: ${plant.luminosity}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF1B5E20)
                            )

                            Text(
                                text = "Quantidade: ${plant.totalPlants ?: 0}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF1B5E20)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            currentIndex = (currentIndex + 1) % plants.size
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2E7D32),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Próxima planta")
                    }
                }
            }
        }
    }
}