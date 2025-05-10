package com.aliozdemir.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aliozdemir.superheroes.data.HeroesRepository.heroes
import com.aliozdemir.superheroes.model.Hero
import com.aliozdemir.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperheroesApp()
                }
            }
        }
    }
}

@Composable
fun SuperheroesApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar()
        }
    ) {
        LazyColumn(
            contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            items(heroes) { hero ->
                HeroItem(
                    hero = hero
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineMedium
            )
        },
        modifier = modifier
    )
}

@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column (modifier = Modifier.weight(1f)){
                HeroName(hero.nameRes)
                HeroDescription(hero.descriptionRes)
            }

            Spacer(modifier = Modifier.width(16.dp))

            HeroImage(hero.imageRes, hero.descriptionRes)
        }
    }
}

@Composable
fun HeroName(@StringRes nameRes: Int) {
    Text(
        text = stringResource(nameRes),
        style = MaterialTheme.typography.displaySmall
    )
}

@Composable
fun HeroDescription(@StringRes descriptionRes: Int) {
    Text(
        text = stringResource(descriptionRes),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun HeroImage(
    @DrawableRes imageRes: Int,
    @StringRes contentDescriptionRes: Int
) {
    Image(
        painter = painterResource(imageRes),
        contentDescription = stringResource(contentDescriptionRes),
        modifier = Modifier
            .size(72.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuperheroesPreview() {
    SuperheroesTheme {
        SuperheroesApp()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuperheroesDarkThemePreview() {
    SuperheroesTheme(darkTheme = true) {
        SuperheroesApp()
    }
}
