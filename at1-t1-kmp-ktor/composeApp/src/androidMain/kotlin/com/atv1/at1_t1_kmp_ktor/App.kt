package com.atv1.at1_t1_kmp_ktor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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

@Composable
@Preview
fun App() {
    MaterialTheme {
        val species = remember {
            Species(
                id = 1,
                name = "Monstera deliciosa"
            )
        }

        val plant = remember {
            Plant(
                id = 1,
                scientificName = "Monstera deliciosa",
                popularName = "Costela-de-adão",
                speciesId = species.id!!,
                luminosity = "Meia sombra"
            )
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
                        text = "Espécie: ${species.name}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF1B5E20)
                    )

                    Text(
                        text = "Luminosidade: ${plant.luminosity}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF1B5E20)
                    )
                }
            }
        }
    }
}