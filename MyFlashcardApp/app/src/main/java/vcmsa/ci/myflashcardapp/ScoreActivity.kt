package vcmsa.ci.myflashcardapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)


        // ScoreActivity shows the total score and personalized feedback
        class ScoreActivity : Activity() {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_score)

                val score = intent.getIntExtra("score", 0)
                val questions = intent.getStringArrayExtra("questions") ?: arrayOf()
                val answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()

                val scoreText: TextView = findViewById(R.id.scoreText)
                val feedbackText: TextView = findViewById(R.id.feedbackText)
                val reviewBtn: Button = findViewById(R.id.reviewBtn)
                val exitBtn: Button = findViewById(R.id.exitBtn)

                scoreText.text = "You scored $score out of ${questions.size}"

                // Personalized feedback
                feedbackText.text = if (score >= 3) "Great job!" else "Keep practising!"

                // Review all flashcards with answers
                reviewBtn.setOnClickListener {
                    val intent = Intent(this, ReviewActivity::class.java)
                    intent.putExtra("questions", questions)
                    intent.putExtra("answers", answers)
                    startActivity(intent)
                }

                // Exit the app
                exitBtn.setOnClickListener {
                    finishAffinity()
                }
            }
        }







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}