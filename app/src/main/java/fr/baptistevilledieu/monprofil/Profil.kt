package fr.baptistevilledieu.monprofil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController){
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        maPhoto()
        maDescription(name = "Baptiste Villedieu")
        Spacer(Modifier.height(50.dp))
        mesContacts()
        Spacer(Modifier.height(100.dp))
        Bouton(navController)
    }
}

@Composable
fun maDescription(name: String){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = name, style= MaterialTheme.typography.h4, modifier = Modifier.padding(10.dp), fontWeight = FontWeight.Bold)
        Text("Etudiant en Informatique de la Santé")
        Text("Ecole d'ingénieur ISIS - INU Champollion", fontStyle = FontStyle.Italic)
    }
}

@Composable
fun maPhoto(){
    Column(modifier = Modifier.padding(10.dp)) {
        Image(painterResource(R.drawable.photolinkedin),
            contentDescription = "maPhoto",
            modifier = Modifier
                .clip(CircleShape)
                .size(250.dp)
                .border(width = 1.dp, color = Color.Black, shape = CircleShape),
        )
    }
}

@Composable
fun Logo(image:Int, desc:String){
    Image(
        painterResource(id = image),
        contentDescription = desc,
        modifier = Modifier.size(20.dp)
    )
}

@Composable
fun mesContacts(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row() {
            Logo(R.drawable.logomail, desc = "Logo Mail")
            Spacer(Modifier.width(10.dp))
            Text("baptiste.villedieu2000@gmail.com")
        }
        Row(){
            Logo(R.drawable.logolinkedin, desc = "Logo Linkedin")
            Spacer(Modifier.width(10.dp))
            Text("www.linkedin.com/in/baptiste-villedieu")
        }
        }
    }

@Composable
fun Bouton(navController: NavController){
    Button(onClick = { navController.navigate("films") }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue
    )) {
        Text(text = "Démarrer", color = Color.White)
    }
}

@Composable
fun Profil(windowClass: WindowSizeClass, navController: NavController) {
    when (windowClass.heightSizeClass) {
        //écran compact== paysage
        WindowHeightSizeClass.Compact -> {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column() {
                    maPhoto()
                    maDescription(name = "Baptiste Villedieu")
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    mesContacts()
                    Spacer(Modifier.height(20.dp))
                    Bouton(navController)
                }
            }
        }
        else -> {
            Home(navController)
        }
    }
}