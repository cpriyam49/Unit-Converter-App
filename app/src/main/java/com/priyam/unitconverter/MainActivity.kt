package com.priyam.unitconverter

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.priyam.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import androidx.compose.material.icons.filled.ArrowDropDown as ArrowDropDown1

class MainActivity :
    ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(//background of our screen
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt()/ 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //ui elements stacked below each other
        Spacer(modifier = Modifier.height(128.dp))
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
            label = { Text(text = "Enter Value") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //ui elements stacked next to each other
            Box {
                Button(//input button
                    onClick = { iExpanded = true }) {
                    Text(inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown1,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(
                    expanded = iExpanded,
                    onDismissRequest = { iExpanded = false })
                {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {
                            iExpanded =  false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters")},
                        onClick = {
                            iExpanded =  false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            iExpanded =  false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters")},
                        onClick = {
                            iExpanded =  false
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Inches")},
                        onClick = {
                            iExpanded =  false
                            inputUnit = "Inches"
                            conversionFactor.value = 0.0254
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Yards")},
                        onClick = {
                            iExpanded =  false
                            inputUnit = "Yards"
                            conversionFactor.value = 0.9144
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick = {
                    val swap = inputUnit
                    inputUnit = outputUnit
                    outputUnit = swap

                    val swapp = conversionFactor.value
                    conversionFactor.value = oConversionFactor.value
                    oConversionFactor.value = swapp
                    Log.d("inputunit",inputUnit)
                    Log.d("outputunit",outputUnit)
                    convertUnits()
                }){
                    Icon(
                    Icons.Default.Refresh,
                    contentDescription = "Arrow Forward"
                )}


            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(//output button
                    onClick = { oExpanded = true }) {
                    Text(outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown1,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(
                    expanded = oExpanded,
                    onDismissRequest = { oExpanded = false })
                {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {
                            oExpanded =  false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters")},
                        onClick = {
                            oExpanded =  false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            oExpanded =  false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters")},
                        onClick = {
                            oExpanded =  false
                            outputUnit = "Millimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Inches")},
                        onClick = {
                            oExpanded =  false
                            outputUnit = "Inches"
                            oConversionFactor.value = 0.0254
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Yards")},
                        onClick = {
                            oExpanded =  false
                            outputUnit = "Yards"
                            oConversionFactor.value = 0.9144
                            convertUnits()
                        }
                    )
                }
            }
//            val context = LocalContext.current
//            Button(
//                onClick = { Toast.makeText(
//                    context, "Thanks for clicking!", Toast.LENGTH_LONG).show() }) {
//                Text("Press")
//            }


        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Result :\n $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
//            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}