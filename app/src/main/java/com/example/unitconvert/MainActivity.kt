package com.example.unitconvert

import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvert.ui.theme.UnitConvertarTheme
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() { // MainActivity class // MainActivity sınıfı
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConvertarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter("Unit Converter(Birim Dönüştürücü)", Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable // Composable function // Composable fonksiyon // UnitConverter function // UnitConverter fonksiyonu
fun UnitConverter(name: String, modifier: Modifier = Modifier) {
    var inputvalue by remember { mutableStateOf("") } // MutableState variable // Değişken
    var outpuvalue by remember { mutableStateOf("") } // MutableState variable // Değişken
    var inputUnit by remember { mutableStateOf("Meters") } // MutableState variable // Değişken
    var outputUnit by remember { mutableStateOf("Meters") }// MutableState variable // Değişken
    var İExpended by remember { mutableStateOf(false) }//  MutableState variable // Değişken
    var OExpended by remember { mutableStateOf(false) }// MutableState variable // Değişken
    val  conversitonFactor = remember { mutableStateOf(1.00)}
    val oconversionFactor = remember { mutableStateOf(1.00)}

     val customtextstyles= TextStyle(

         fontFamily = FontFamily.SansSerif,
            color = Color.Black,
            fontSize = 20.sp


     )



    fun UnitConverterUnits() {
        val inputvalueDouble = inputvalue.toDoubleOrNull() ?: 0.0
        val result = (inputvalueDouble * conversitonFactor.value  *100.0 / oconversionFactor.value).roundToInt() / 100.0
        outpuvalue = result.toString()
        // Convert the input value to the output value // Giriş değerini çıkış değerine dönüştür
    }



    // MutableState variable // Değişken
    Column (
  modifier = modifier.fillMaxSize(), // Fill the entire screen (width and height) // Ekranın tamamını doldur (genişlik ve yükseklik)
      verticalArrangement = Arrangement.Center, // Center the content vertically,
      horizontalAlignment = Alignment.CenterHorizontally // Center the content horizontally // İçeriği yatay olarak ortalayın
// İçeriği dikey olarak ortalayın

  )
    {
        Text("Unit Converter(Birim Dönüştürücü)",  style = customtextstyles) // Text // Metin
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputvalue, onValueChange = { // OutlinedTextField // Dışbükey metin alanı
           inputvalue = it
            UnitConverterUnits()

        })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { İExpended=true}) {  // Button // Düğme
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")

                }
                DropdownMenu(expanded = İExpended, onDismissRequest = { İExpended=false }) { // DropdownMenu // Açılır Menü
                    DropdownMenuItem(text = {Text( "Centimeters") }, onClick = {
                        İExpended=false
                        inputUnit="Centimeters"
                        conversitonFactor.value=0.01
                        UnitConverterUnits()
                                })
                    DropdownMenuItem(text = {Text( "Meters") }, onClick = {
                        İExpended=false
                        inputUnit="Meters"
                        conversitonFactor.value=1.0
                        UnitConverterUnits() })
                    DropdownMenuItem(text = {Text( "Feet") }, onClick = {   İExpended=false
                        inputUnit="Feet"
                        conversitonFactor.value=0.3048
                        UnitConverterUnits()
                    })
                    DropdownMenuItem(text = {Text( "MiliMeters") }, onClick = {
                        inputUnit="MiliMeters"
                        conversitonFactor.value=0.001

                        UnitConverterUnits()
                    })



                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Box {
                Button(onClick = { OExpended=true}) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")

                }


                DropdownMenu(expanded =OExpended, onDismissRequest = { OExpended=false }) {
                    DropdownMenuItem(text = {Text( "Centimeters") }, onClick = {
                        OExpended=false
                        outputUnit="Centimeters"
                        oconversionFactor.value=0.01
                        UnitConverterUnits()
                    })
                    DropdownMenuItem(text = {Text( "Meters") }, onClick = {
                        OExpended=false
                        outputUnit="Meters"
                        oconversionFactor.value=1.0
                        UnitConverterUnits() })
                    DropdownMenuItem(text = {Text( "Feet") }, onClick = {   İExpended=false
                        OExpended=false
                        outputUnit="Feet"
                        oconversionFactor.value=0.3048
                        UnitConverterUnits()
                    })
                    DropdownMenuItem(text = {Text( "MiliMeters") }, onClick = {
                        OExpended=false
                        outputUnit="MiliMeters"
                        oconversionFactor.value=0.001
                        UnitConverterUnits()
                    })
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Display the result // Sonucu göster
        Text("Result: $outpuvalue $outputUnit",
        style = MaterialTheme.typography.headlineMedium
        ) // Text // Metin
    }
}



    @Preview(showBackground = true) // Show the background color of the preview // Önizleme arka plan rengini göster
    @Composable
    fun UnitConverThemePriew() {  // Preview function // Önizleme fonksiyonu
        UnitConverter("Unit Converter") // Call the UnitConverter function // UnitConverter fonksiyonunu çağır
    }
