package vcmsa.ci.myflashcardapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)


        // QuestionActivity handles the flashcard quiz logic
        class QuestionActivity : AppCompatActivity() {

            // Array of 5 questions
            private val questions = arrayOf(
                "Nelson Mandela was the president in 1994.",
                "The Great Wall of China is located in India.",
                "The pyramids of Egypt are older than 2000 years.",
                "World War II ended in 1945.",
                "Christopher Columbus discovered America in 1492."
            )
            // Parallel array of answers
            private val answers = booleanArrayOf(
                true,  // Mandela president in 1994
                false, // Great Wall location
                true,  // Egyptian pyramids
                true,  // WWII end
                true   // Columbus discovery
            )

            private var currentIndex = 0 // Track current question
            private var score = 0        // Track score

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_question)

                val questionText: TextView = findViewById(R.id.questionText)
                val feedbackText: TextView = findViewById(R.id.feedbackText)
                val trueBtn: Button = findViewById(R.id.trueBtn)
                val falseBtn: Button = findViewById(R.id.falseBtn)
                val nextBtn: Button = findViewById(R.id.nextBtn)

                // Function to display the current question
                fun displayQuestion() {
                    questionText.text = questions[currentIndex]
                    feedbackText.text = ""
                    trueBtn.isEnabled = true
                    falseBtn.isEnabled = true
                    nextBtn.isEnabled = false
                }
                displayQuestion()

                // Handles answer selection
                fun handleAnswer(userAnswer: Boolean) {
                    trueBtn.isEnabled = false
                    falseBtn.isEnabled = false
                    nextBtn.isEnabled = true

                    // Check answer and provide feedback
                    if (userAnswer == answers[currentIndex]) {
                        feedbackText.text = "Correct!"
                        score++
                    } else {
                        feedbackText.text = "Incorrect"
                    }
                }

                trueBtn.setOnClickListener { handleAnswer(true) }
                falseBtn.setOnClickListener { handleAnswer(false) }

                // Move to next question or end quiz
                nextBtn.setOnClickListener {
                    currentIndex++
                    if (currentIndex < questions.size) {
                        displayQuestion()
                    } else {
                        // Quiz finished: go to ScoreActivity
                        val intent = Intent(this, ScoreActivity::class.java)
                        intent.putExtra("score", score)
                        intent.putExtra("questions", questions)
                        intent.putExtra("answers", answers)
                        startActivity(intent)
                        finish()
                    }
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