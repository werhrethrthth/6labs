package com.example.millionaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity(),View.OnClickListener {
        private var currentRound = 0
        private lateinit var tvQuestion: TextView
        private lateinit var tvValue: TextView
        private lateinit var btn1: Button
        private lateinit var btn2: Button
        private lateinit var btn3: Button
        private lateinit var btn4: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvQuestion: TextView = findViewById<TextView>(R.id.tvQuestion)
        val tvValue: TextView = findViewById(R.id.tvValue)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)

        getString(R.string.app_name)
        btn1.setOnClickListener{
            processRound(1)
        }
        btn2.setOnClickListener{
            processRound(2)
        }
        btn3.setOnClickListener{
            processRound(3)
        }
        btn4.setOnClickListener{
            processRound(4)
        }
        fillRounds()
        updateUI()
    }
    override  fun onClick(v:View?){
v?.let{
    when(it.id){
        R.id.btn1 -> processRound(1)
        R.id.btn2 -> processRound(2)
        R.id.btn3 -> processRound(3)
        R.id.btn4 -> processRound(4)
else -> return

    }
  }
}

    private val rounds = mutableListOf<Round>()

    private fun updateUI(){
        tvQuestion.text= rounds[currentRound].question
        tvValue.text = rounds[currentRound].value.toString()
        btn1.text=rounds[currentRound].answer1
        btn2.text=rounds[currentRound].answer2
        btn3.text=rounds[currentRound].answer3
        btn3.text=rounds[currentRound].answer4
    }

    private fun checkAnswer(givenId:Int) = (givenId == rounds[currentRound].rightId)

    private fun goNextRound():Boolean{
        if (currentRound >= rounds.size -1) return false
        currentRound++
        updateUI()
        return true
    }

    private fun processRound(givenId: Int){
        if (checkAnswer((givenId))) {
            if (!goNextRound()) {
                Toast.makeText(this,getString(R.string.wintext), Toast.LENGTH_SHORT).show()
                finish()
            }
        } else {
            Toast.makeText(this,getString(R.string.loosetext),Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun fillRounds() {
        rounds.run {

            add(Round("Кто из этих ученых открыл теорию относительности?", "Исаак Ньютон", "Альберт Эйнштейн", "Майкл Фарадей", "Никола Тесла", 2, 500))
            add(
                Round(
                    "Какая из этих стран не является членом Европейского союза?",
                    "Франция",
                    "Германия",
                    "Швейцария",
                    "Италия",
                    3,
                    5000
                )
            )
            add(Round("Какой из этих животных является насекомым?   ",
                "Крыса", "Лось", "Кошка", "Пчела",
                4, 50000))
            add(
                Round(
                    "Кто был первым человеком, полетевшим в космос??",
                    "Юрий Гагарин",
                    "Нил Армстронг",
                    "Джон Глен",
                    "Алексей Леонов",
                    1,
                    500000
                )
            )
            add(
                Round(
                    "Какой газ составляет большую часть воздуха??",
                    "Азот",
                    "Гелий",
                    "Кислород",
                    "Углекислый газ",
                    1,
                    5000000
                )
            )


        }
    }

    fun buttonClick(view: View) {
        try {
            val id = view.tag.toString().toInt()
            processRound(id)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

}