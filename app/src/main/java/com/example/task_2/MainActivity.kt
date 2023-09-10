package com.example.task_2

import android.media.Image
import android.media.ImageReader
import android.media.ImageWriter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task_2.ui.theme.TASK_2Theme

private fun generatePlaces(): List<Place> {
    return listOf(
        Place("Amsterdam", "Dam", R.drawable.amsterdam_dam),
        Place("Amsterdam", "Weesperplein", R.drawable.amsterdam_weesperplein),
        Place("Rotterdam", "Euromast", R.drawable.rotterdam_euromast),
        Place("Den Haag", "Binnenhof", R.drawable.den_haag_binnenhof),
        Place("Utrecht", "Dom", R.drawable.utrecht_dom),
        Place("Groningen", "Martinitoren", R.drawable.groningen_martinitoren),
        Place("Maastricht", "Vrijthof", R.drawable.maastricht_vrijthof),
        Place("New York", "Vrijheidsbeeld", R.drawable.new_york_vrijheidsbeeld),
        Place("San Francisco", "Golden Gate", R.drawable.san_francisco_golden_gate),
        Place("Yellowstone", "Old Faithful", R.drawable.yellowstone_old_faithful),
        Place("Yosemite", "Half Dome", R.drawable.yosemite_half_dome),
        Place("Washington", "White House", R.drawable.washington_white_house),
        Place("Ottawa", "Parliament Hill", R.drawable.ottawa_parliament_hill),
        Place("Londen", "Tower Bridge", R.drawable.london_tower_bridge),
        Place("Brussel", "Manneken Pis", R.drawable.brussel_manneken_pis),
        Place("Berlijn", "Reichstag", R.drawable.berlijn_reichstag),
        Place("Parijs", "Eiffeltoren", R.drawable.parijs_eiffeltoren),
        Place("Barcelona", "Sagrada Familia", R.drawable.barcelona_sagrada_familia),
        Place("Rome", "Colosseum", R.drawable.rome_colosseum),
        Place("Napels", "Pompeii", R.drawable.pompeii),
        Place("Kopenhagen", "", R.drawable.kopenhagen),
        Place("Oslo", "", R.drawable.oslo),
        Place("Stockholm", "", R.drawable.stockholm),
        Place("Helsinki", "", R.drawable.helsinki),
        Place("Moskou", "Rode Plein", R.drawable.moskou_rode_plein),
        Place("Beijing", "Verboden Stad", R.drawable.beijing_verboden_stad),
        Place("Kaapstad", "Tafelberg", R.drawable.kaapstad_tafelberg),
        Place("Rio de Janeiro", "Copacabana", R.drawable.rio_de_janeiro_copacabana),
        Place("Sydney", "Opera", R.drawable.sydney_opera),
        Place("Hawaii", "Honolulu", R.drawable.hawaii),
        Place("Alaska", "Denali", R.drawable.alaska_denali)
    )
}

class Place(cityName: String, placeOf: String, image: Int) {
    var cityName = cityName
    var placeOf = placeOf
    var image = image
}



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TASK_2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Struct()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Struct() {
    var texto by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
                TopAppBar(
                    title = {
                        Row (
                            modifier = Modifier.background(Color.Magenta),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            TextField(
                                value = texto,
                                onValueChange = { texto = it },
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Search
                                ),
                                leadingIcon = { Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = null,
                                    tint = Color.White,
                                )},
                                colors = TextFieldDefaults.textFieldColors(
                                    textColor = Color.White, disabledTextColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent, containerColor = Color.Transparent,
                                    focusedSupportingTextColor = Color.Transparent, unfocusedSupportingTextColor = Color.Transparent,
                                    cursorColor = Color.White
                                ),
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.Magenta)
                )
        },
        content = { padding -> GridScreen(Modifier.padding(padding), texto) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridScreen(modifier: Modifier, texto: String) {
    var lugares = generatePlaces()
    LazyVerticalGrid(
        userScrollEnabled = true,
        modifier = Modifier.fillMaxHeight().padding(top = 80.dp),
        columns = GridCells.Adaptive(150.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        if (texto == "") {
            items(lugares) { currentLug ->
                Column {
                    Image(
                        painter = painterResource(id = currentLug.image),
                        contentDescription = "", // decorative element
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .fillMaxSize()
                    )
                    Text(
                        text = currentLug.cityName,
                        modifier = Modifier.padding(start = 10.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                    Text(
                        text = currentLug.placeOf,
                        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
                    )
                }
            }
        } else {
            val lugaresFil = lugares.filter { it.cityName.lowercase().contains(texto) == true || it.placeOf.lowercase().contains(texto) == true}
            items(lugaresFil) { currentLug ->
                    Column {
                        Image(
                            painter = painterResource(id = currentLug.image),
                            contentDescription = "", // decorative element
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .fillMaxSize()
                        )
                        Text(
                            text = currentLug.cityName,
                            modifier = Modifier.padding(start = 10.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                        Text(
                            text = currentLug.placeOf,
                            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
                        )
                    }
            }
        }
    }
}


