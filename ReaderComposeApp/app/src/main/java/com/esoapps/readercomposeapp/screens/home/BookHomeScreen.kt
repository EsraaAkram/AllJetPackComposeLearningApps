package com.esoapps.readercomposeapp.screens.home

import android.widget.HorizontalScrollView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.esoapps.readercomposeapp.components.*
import com.esoapps.readercomposeapp.model.Book
import com.esoapps.readercomposeapp.navigations.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navController: NavHostController) {


    Scaffold(topBar = {
        ReaderAppBar(
            title = "A.Reader",
            navController = navController
        )

    }, floatingActionButton = {

        FabContent {}

    }) {

        Surface(modifier = Modifier.fillMaxSize()) {

            HomeContent(navController = navController)
        }
    }

}


@Composable
fun HomeContent(navController: NavHostController) {

    val listOfBooks = listOf(
        Book(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null),
        Book(id = "dadfa", title = " Again", authors = "All of us", notes = null),
        Book(id = "dadfa", title = "Hello ", authors = "The world us", notes = null),
        Book(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null),
        Book(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null)
    )

    val currentUserMail = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName =
        if (!currentUserMail.isNullOrEmpty()) {
            currentUserMail.split("@")[0]
        } else {
            "N/A"
        }
    Column(
        modifier = Modifier.padding(3.dp),
        verticalArrangement = Arrangement.Top,//SpaceEvenly
    ) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {

            TitleSection(modifier = Modifier, label = "Your Readings \n Right Now: ")

            Spacer(modifier = Modifier.fillMaxWidth(fraction = .06F))

            Column {
                Icon(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                        },
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile Icon",
                    tint = MaterialTheme.colors.secondaryVariant
                )
                Text(
                    modifier = Modifier.padding(3.dp),
                    text = "$currentUserName",
                    style = MaterialTheme.typography.overline,
                    color = Color.Red,
                    fontSize = 21.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,

                    )

                Divider()


            }

        }

        ReadingRightNowArea(navController=navController,books= listOf())


        TitleSection(modifier = Modifier, label = "Reading List ")

        BookListArea(navController=navController,listOfBooks=listOfBooks )//emptyList()
    }


}



@Composable
fun ReadingRightNowArea(
    navController: NavHostController,
    books: List<Book>,

) {
    ListCard(){

    }

}


@Composable
fun BookListArea(navController: NavHostController,
                 listOfBooks: List<Book>) {

    HorizontalScrollViewComponent(listOfBooks){

    }

}

@Composable
fun HorizontalScrollViewComponent(listOfBooks: List<Book>,onCardPressed:(String)->Unit) {

    var scrollState= rememberScrollState()

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .horizontalScroll(scrollState)) {

        for (book in listOfBooks){
            ListCard(book=book){
                onCardPressed(it)
            }
        }


    }


}
