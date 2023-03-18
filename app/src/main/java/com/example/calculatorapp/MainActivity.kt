package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private   var last_dot:Boolean=false
    private   var last_num:Boolean=false

    lateinit private  var inputtv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputtv=findViewById(R.id.inputtvid)
        inputtv.setText("")
    }
    fun ondigit(view:View){
        inputtv.append((view as Button).text)
        last_num=true
        //this implies that we the button which we have clicked recently will be our last_num one in the buffer quite logical
        //last_num in the input buffer and its quite logical to say this thing!!
        last_dot=false
    }
    fun onclear(view: View){
        inputtv.setText("")
    }
    fun ondecimalpoint(view: View) {
        inputtv.append(".")
    }

    fun onoperatorclick(view: View){

        inputtv.text.let {
            if(last_num==true and isoperatoradded(it.toString())==false){
                inputtv.append((view as Button).text)
                last_num=false
            }
        }

    }

    fun isoperatoradded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("+")||value.contains("-")||value.contains("/")||value.contains("*")
        }
    }
    fun  onequal(view:View){
        if(last_num==true){
            var tv_value=inputtv.text.toString()
            var prefix=""
            try {
                if(tv_value.startsWith("-")){
                    Toast.makeText(this,"yes u are called",Toast.LENGTH_LONG).show()
                    prefix="-"
                    tv_value=tv_value.substring(1)
                }
                if(tv_value.contains("-")){
                    //this is creating the minus functionality:
                    var split_value=tv_value.split("-")
                    var one_operand=split_value[0]
                    var second_operand=split_value[1]

                    if(prefix.isNotEmpty()){
                        one_operand=one_operand+prefix
                    }
                    var res=(one_operand.toDouble()-second_operand.toDouble()).toString()
                    inputtv.text=""
                    inputtv.text=res
                }
                else if(tv_value.contains("+")){
                    //this is creating the addition  functionality:
                    var split_value=tv_value.split("+")
                    var one_operand=split_value[0]
                    var second_operand=split_value[1]

                    if(prefix.isNotEmpty()){
                        one_operand=one_operand+prefix
                    }
                    var res=(one_operand.toDouble()+second_operand.toDouble()).toString()
                    inputtv.text=""
                    inputtv.append(res)
                }
                else if(tv_value.contains("/")){
                    //this is for the division functionality
                    var split_value=tv_value.split("/")
                    var one_operand=split_value[0]
                    var second_operand=split_value[1]

                    if(prefix.isNotEmpty()){
                        one_operand=one_operand+prefix
                    }
                    var res=(one_operand.toDouble()/second_operand.toDouble()).toString()
                    inputtv.text=""
                    inputtv.append(res)

                }
                else if(tv_value.contains("*")){
                    //this is for the multiplication functionality
                    var split_value=tv_value.split("*")
                    var one_operand=split_value[0]
                    var second_operand=split_value[1]

                    if(prefix.isNotEmpty()){
                        one_operand=one_operand+prefix
                    }
                    var res=(one_operand.toDouble()*second_operand.toDouble()).toString()
                    inputtv.text=""
                    inputtv.append(res)
                }


            }catch (e:ArithmeticException){

            }
        }
    }
}