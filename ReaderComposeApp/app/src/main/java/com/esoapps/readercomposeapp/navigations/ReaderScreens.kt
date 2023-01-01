package com.esoapps.readercomposeapp.navigations


enum class ReaderScreens {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    ReaderHomeScreen,
    SearchScreen,
    DetailsScreen,
    UpdateScreen,
    ReaderStatsScreen;


    companion object {
        fun fromRoute(route:String?):ReaderScreens=

            when(route?.substringBefore("/")){
                SplashScreen.name->SplashScreen
                LoginScreen.name->LoginScreen
                CreateAccountScreen.name->CreateAccountScreen
                ReaderHomeScreen.name->ReaderHomeScreen
                SearchScreen.name->SearchScreen
                DetailsScreen.name->DetailsScreen
                UpdateScreen.name->UpdateScreen
                ReaderStatsScreen.name->ReaderStatsScreen
                null->ReaderHomeScreen
                else->throw java.lang.IllegalArgumentException("The Route You ARE Trying to get to is not right")
            }

    }


}