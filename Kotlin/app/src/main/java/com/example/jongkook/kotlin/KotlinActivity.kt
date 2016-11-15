package com.example.jongkook.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

      //  sayHello("Kotlin")
        println(">>>> " + sum(1,2))

        var btn = findViewById(R.id.button)
        btn.setOnClickListener { event->{} }

    }

    fun sayHello(str: String){
        println(" hello " + str )
    }

    fun sum(a:Int, b:Int):Int{
        // val = final
        // val a = 1
        // val b = "abc"

        // var = 그냥 변수
        // var c = 3
        var d = "ddd"
        var e = 4

        // 타입
        return a+b
    }

}
