package com.plcoding.meditationuiyoutube.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.meditationuiyoutube.BottomMenuContent
import com.plcoding.meditationuiyoutube.Feature
import com.plcoding.meditationuiyoutube.R
import com.plcoding.meditationuiyoutube.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun HomeScreen(){
    Box(modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()){
        Column {
            GreetingSection()
            ChipSection(chips = listOf("Sweet sleep","Insomnia", "Depression"))
            CurrentMedication()
            FeatureSection(features = listOf(
                Feature (
                    title = "Sleep meditation",
                    R.drawable.ic_headphone,
                    BlueViolet1,

                ),
                Feature (
                    title = "Tips for sleeping",
                    R.drawable.ic_videocam,
                    LightGreen1,
                ),
                Feature (
                    title = "Night island",
                    R.drawable.ic_headphone,
                    OrangeYellow1,

                ),
                Feature (
                    title = "Calming sound",
                    R.drawable.ic_headphone,
                    Beige1,
                ),
                Feature (
                        title = "Calm",
                        R.drawable.ic_videocam,
                        OrangeYellow3,
                ),
                Feature(
                    title = "Peace",
                    R.drawable.ic_bubble,
                    BlueViolet3
                )
            ))
        }
        BottomMenu(items = listOf(
            BottomMenuContent("Home",R.drawable.ic_home),
            BottomMenuContent("Meditate",R.drawable.ic_bubble),
            BottomMenuContent("Music",R.drawable.ic_music),
            BottomMenuContent("Profile",R.drawable.ic_home)
        ),modifier = Modifier.align(Alignment.BottomCenter )
            )
    }
}

@Composable
fun BottomMenu(
    items : List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor : Color = ButtonBlue,
    activeTextColor : Color = Color.White,
    inactiveTextColor : Color = AquaBlue,
    initialSelectedItemIndex : Int = 0
) {
         var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
         }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)) {
        items.forEachIndexed { index, item -> 
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor){
                selectedItemIndex = index
            }
        }

    }

}

@Composable
fun BottomMenuItem(
    item : BottomMenuContent,
    isSelected : Boolean = false,
    activeHighlightColor : Color = ButtonBlue,
    activeTextColor : Color = Color.White,
    inactiveTextColor : Color = AquaBlue,
    onItemClick : () -> Unit // Understand this

) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.clickable {
        onItemClick
    }) {
        Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) activeHighlightColor else Color.Transparent)
            .padding(10.dp)) {
            Icon(painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if(isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected)activeTextColor else inactiveTextColor
        )
    }
}
@Composable
fun GreetingSection(
    name : String = "Usman")
{
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            ){
        Column(
            verticalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.greet) + name,
                style = MaterialTheme.typography.h2
            )
            Text(
                text = stringResource(id = R.string.Tagline),
                style = MaterialTheme.typography.body1
            )

        }
        Icon(painter = painterResource(id = R.drawable.ic_search),
            contentDescription ="Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)

            )
    }
}
@Composable
fun ChipSection(
    chips : List<String>
){
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow{
        items(chips.size){
            Box(modifier = Modifier
                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                .clickable {
                    selectedChipIndex = it
                }
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (selectedChipIndex == it) ButtonBlue
                    else DarkerButtonBlue
                )
                .padding(15.dp)
            ){
              Text(text = chips[it],color = TextWhite)
            }
        }
    }
}
@Composable
fun CurrentMedication(
    color : Color = LightRed
){
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()) {
        Column {
            Text(
                text = stringResource(id = R.string.Heading),
                style = MaterialTheme.typography.h2
            )
            Text(
                text = stringResource(id = R.string.SubHeading),
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
@ExperimentalFoundationApi
@Composable
fun FeatureSection(features : List<Feature>){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.Feature_Heading),
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 7.dp,end = 7.dp,bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()){
            items(features.size){
                FeatureItem(features = features[it])// doubt why we did this
            }

        }
    }
}
@Composable
fun FeatureItem(
    features: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(features.lightColor)
    ) {
//
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {

            Text(
                text = features.title,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = features.iconId),
                contentDescription = features.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(text = stringResource(id = R.string.Button_Name),
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        //Handle the click
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)

            )
        }

    }
}
