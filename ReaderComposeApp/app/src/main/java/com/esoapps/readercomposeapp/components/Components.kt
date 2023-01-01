package com.esoapps.readercomposeapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.esoapps.readercomposeapp.model.Book
import com.esoapps.readercomposeapp.navigations.ReaderScreens
import com.google.firebase.auth.FirebaseAuth


@Composable
fun ReaderAppBar(
    title: String,
    showProfile: Boolean = true,
    navController: NavHostController
) {
    TopAppBar(title = {

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (showProfile) {

                Icon(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .scale(.5F),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Logo Icon",
                )
            }
            Text(
                text = "A.Reader",
                color = Color.Red.copy(alpha = .5F),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.width(150.dp))


        }


    }, actions = {
        IconButton(modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .scale(.5F),

            onClick = {
                FirebaseAuth.getInstance().signOut().run {
                    navController.navigate(ReaderScreens.CreateAccountScreen.name)

                }
            }) {
            Icon(
                modifier = Modifier.size(25.dp),
                imageVector = Icons.Filled.Logout,
                contentDescription = "Log Out",
                tint = Color.Green.copy(alpha = 0.5F)
            )
        }

    }, backgroundColor = Color.Transparent, elevation = 0.dp
    )

}


@Composable
fun TitleSection(
    modifier: Modifier = Modifier, label: String
) {
    Surface(modifier = modifier.padding(start = 5.dp, top = 1.dp)) {

        Text(
            text = label,
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Left,
        )

    }

}


@Composable
fun BookRating(score: Double = 3.5) {

    Surface(
        modifier = Modifier
            .height(75.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(55.dp), elevation = 5.dp, color = Color.White
    ) {


        Column(modifier = Modifier.padding(5.dp)) {
            Icon(
                modifier = Modifier
                    .size(25.dp),
                imageVector = Icons.Default.StarBorder,
                contentDescription = "Stars Icon",
                tint = MaterialTheme.colors.secondaryVariant
            )

            Text(
                text = "$score",
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black.copy(.8F),
                fontSize = 16.sp

            )

        }


    }


}


@Composable
fun ListCard(
    book: Book = Book("id", "title", "author", "notes"),
    onPressDetails: (String) -> Unit
) {

    val context = LocalContext.current
    val displayMatrix = context.resources.displayMetrics
    //ACTUAL SCREEN WIDTH:
    val screenWidth = displayMatrix.widthPixels / displayMatrix.density
    val spacing = 10.dp

    Card(
        modifier = Modifier
            .padding(15.dp)
            .height(250.dp)
            .width(200.dp)
            .clickable {
                onPressDetails.invoke(book.title.toString())
            },
        shape = RoundedCornerShape(25.dp),
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            horizontalAlignment = Alignment.Start
        ) {

            Row(horizontalArrangement = Arrangement.Center) {

                Column() {
                    Image(
                        modifier = Modifier
                            .height(150.dp)
                            .width(100.dp)
                            .padding(5.dp),
                        painter = rememberImagePainter(
                            data = "https://i.ytimg.com/vi/Hzk4OoKVnj4/mqdefault.jpg",

                            ), contentDescription = "Book Image"
                    )

                    Spacer(modifier = Modifier.width(25.dp))



                }
                Column(
                    modifier = Modifier.padding(top = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {

                            },
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Fav Icon",
                        tint = MaterialTheme.colors.secondaryVariant
                    )


                    BookRating(score = 3.5)

                }
            }

            Text(
                text = "Book Title: ${book.title}",
                style = MaterialTheme.typography.caption,
                color = Color.Black.copy(.8F),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,

                )

            Text(
                text = "Author: ${book.authors}",
                style = MaterialTheme.typography.caption,
                color = Color.Black.copy(.6F),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )





        }
        Row(horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom) {
            RoundedButton(label = "Reading", radius = 70){

            }
        }


    }


}



@Composable
fun RoundedButton(label: String,
                  radius: Int = 30, onPress: () -> Unit) {

    Surface(
        modifier = Modifier.clip(
            RoundedCornerShape(
                bottomEndPercent = radius,
                topStartPercent = radius
            )
        ), color = Color(0xff92cbdf)
    ) {

        Column(modifier = Modifier
            .width(90.dp)
            .heightIn(40.dp)
            .clickable {
                onPress()
            }, verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "$label",
                style = MaterialTheme.typography.subtitle1,
                color = Color.White, fontSize = 15.sp,

                )
        }

    }
}
@Composable
fun FabContent(onTap: () -> Unit) {

    FloatingActionButton(
        onClick = { onTap() },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color(0xff92cbdf)
    ) {

        Icon(
            modifier = Modifier.size(25.dp),
            imageVector = Icons.Default.Add, contentDescription = "Add a Book",
            tint = Color.White,
        )


    }


}







