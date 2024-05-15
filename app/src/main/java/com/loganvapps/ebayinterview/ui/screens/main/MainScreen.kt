package com.loganvapps.ebayinterview.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loganvapps.ebayinterview.data.models.Earthquake

@Composable
fun MainScreenController(viewModel: EarthquakeViewModel, modifier: Modifier = Modifier) {
   val state = viewModel.state.collectAsStateWithLifecycle()

    when(state.value){
        EarthquakeState.Loading -> LoadingScreen()
        is EarthquakeState.Error -> ErrorScreen(data = (state.value as EarthquakeState.Error).message)
        is EarthquakeState.Success -> MainScreen(data = (state.value as EarthquakeState.Success).data)
    }
}

@Composable
fun MainScreen(data:List<Earthquake>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(count = data.size) {
            EarthquakeCard(earthquake = data[it])
        }
    }
}

@Composable
fun EarthquakeCard(earthquake: Earthquake){
    Card(onClick = { /*TODO*/ }) {
        Column {
            Text(text = earthquake.eqid)
            Text(text = earthquake.magnitude.toString())
        }
    }
}


@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(data: String) {
    Column(Modifier.fillMaxSize()) {
        Text(text = data)
    }
}