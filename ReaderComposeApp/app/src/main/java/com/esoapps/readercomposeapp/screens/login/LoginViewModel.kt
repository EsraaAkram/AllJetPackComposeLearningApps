package com.esoapps.readercomposeapp.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esoapps.readercomposeapp.model.CurrentUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    //var loading= MutableStateFlow(LoadingState.IDLE)

    private var _loading = MutableLiveData(false)
    var loading: LiveData<Boolean> = _loading

    private var auth = Firebase.auth

    fun signInByEmailAndPassword(email: String, password: String, home: () -> Unit) {
        viewModelScope.launch {


            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        home()
                    }

                }


        }


    }


    fun createUserByEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit
    ) {
        viewModelScope.launch {

            if (_loading.value == false) {
                _loading.value = true
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            //home()

                            addUserToDb(home)


                        }

                        _loading.value = false

                    }


            }


        }


    }

    private fun addUserToDb(home: () -> Unit) {


        var userMap = mutableMapOf<String, Any>()
        userMap["user_id"] = auth.currentUser?.uid.toString()
        userMap["mail"] = auth.currentUser?.email.toString()


        val userId = auth.currentUser?.uid

        val displayName = auth.currentUser?.email?.split('@')?.get(0)

        val user = CurrentUser(
            userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "Life is great",
            profession = "Android Developer",
            id = null
        ).toMap()


        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    home()
                }

            }


    }


}