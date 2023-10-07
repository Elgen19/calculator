package com.prestosa.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.prestosa.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCalc()
    }



    private fun setupCalc()
    {
        binding.clearB.setOnClickListener {
            binding.expressionET.text.clear()
            binding.resultTV.text = ""
        }

        binding.plusMinusB.setOnClickListener {
            val currentText = binding.expressionET.text.toString()

            if (currentText.isNotEmpty()) {
                try {
                    // Parse the current text to a double
                    val currentNumber = currentText.toDouble()

                    // Toggle the sign of the number
                    val newNumber = -currentNumber

                    // Format the number to remove decimal for whole numbers
                    val formattedNumber = if (newNumber % 1 == 0.0) {
                        newNumber.toInt().toString()
                    } else {
                        newNumber.toString()
                    }

                    // Update the text in expressionET with the new formatted number
                    binding.expressionET.text.clear()
                    binding.expressionET.append(formattedNumber)
                } catch (e: NumberFormatException) {
                    // Handle the case where the current text is not a valid number
                    Toast.makeText(this, "Invalid Number!", Toast.LENGTH_LONG).show()
                }
            }
        }



        binding.percentB.setOnClickListener {
            val expressionStr = binding.expressionET.text.toString()

            if (expressionStr.isNotEmpty()) {
                try {
                    // Check if the expression already contains a percentage symbol
                    if (!expressionStr.contains("%")) {
                        // If not, append the percentage symbol and evaluate the expression
                        binding.expressionET.append("%")
                        val expression = ExpressionBuilder(expressionStr).build()
                        val result = expression.evaluate()

                        // Divide the result by 100 to convert it to a percentage
                        val percentageResult = result / 100.0

                        // Display the result with the percentage symbol
                        binding.expressionET.text.clear()
                        binding.expressionET.append("$percentageResult%")
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Invalid Expression!", Toast.LENGTH_LONG).show()
                }
            }
        }


        binding.divideB.setOnClickListener {
            val currentText = binding.expressionET.text.toString()

            if (currentText.isNotEmpty() && currentText.last().isDigit()) {
                binding.expressionET.append(getString(R.string.division_text))
            }
        }

        binding.sevenB.setOnClickListener {
            binding.expressionET.append(getString(R.string.seven_text))
        }

        binding.eightB.setOnClickListener {
            binding.expressionET.append(getString(R.string.eight_text))
        }

        binding.nineB.setOnClickListener {
            binding.expressionET.append(getString(R.string.nine_text))
        }

        binding.multiplyB.setOnClickListener {
            val currentText = binding.expressionET.text.toString()

            if (currentText.isNotEmpty() && currentText.last().isDigit()) {
                binding.expressionET.append(getString(R.string.multiply_text))
            }
        }


        binding.fourB.setOnClickListener {
            binding.expressionET.append(getString(R.string.four_text))
        }

        binding.fiveB.setOnClickListener {
            binding.expressionET.append(getString(R.string.five_text))
        }

        binding.sixB.setOnClickListener {
            binding.expressionET.append(getString(R.string.six_text))
        }

        binding.subtractB.setOnClickListener {
            val currentText = binding.expressionET.text.toString()

            // Check if the current expression is not empty and if the last character is a digit
            if (currentText.isNotEmpty() && currentText.last().isDigit()) {
                // Append the subtraction symbol
                binding.expressionET.append(getString(R.string.subtract_text))
            }
        }


        binding.oneB.setOnClickListener {
            binding.expressionET.append(getString(R.string.one_text))
        }

        binding.twoB.setOnClickListener {
            binding.expressionET.append(getString(R.string.two_text))
        }

        binding.threeB.setOnClickListener {
            binding.expressionET.append(getString(R.string.three_text))
        }

        binding.addB.setOnClickListener {
            val currentText = binding.expressionET.text.toString()

            // Check if the current expression is not empty and if the last character is a digit
            if (currentText.isNotEmpty() && currentText.last().isDigit()) {
                // Append the addition symbol
                binding.expressionET.append(getString(R.string.add_text))
            }
        }


        binding.zeroB.setOnClickListener {
            binding.expressionET.append(getString(R.string.zero_text))
        }

        binding.dotB.setOnClickListener {
            val currentText = binding.expressionET.text.toString()

            // Check if the current expression is not empty and if the last part doesn't already contain a decimal point
            if (currentText.isNotEmpty() && !currentText.substringAfterLast(' ').contains(getString(R.string.dot_text))) {
                // Append the decimal point
                binding.expressionET.append(getString(R.string.dot_text))
            }
        }

        binding.delB.setOnClickListener {
            if (binding.expressionET.text.isNotEmpty())
                 binding.expressionET.text.delete(binding.expressionET.text.length - 1, binding.expressionET.text.length)

            else
                Toast.makeText(this, "Already Empty!", Toast.LENGTH_LONG).show()
        }

        binding.equalB.setOnClickListener {
            val expressionStr = binding.expressionET.text.toString()

            if (expressionStr.isNotEmpty()) {
                try {
                    val expression = ExpressionBuilder(expressionStr).build()
                    val result = expression.evaluate()

                    // Check if the result is a whole number
                    val isWholeNumber = result % 1 == 0.0

                    binding.expressionET.text.clear()

                    // If it's a whole number, append it without the decimal part
                    if (isWholeNumber) {
                        binding.expressionET.append(result.toInt().toString())
                    } else {
                        binding.expressionET.append(result.toString())
                    }

                    binding.resultTV.text = ""
                } catch (e: Exception) {
                    Toast.makeText(this, "Invalid Expression!", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.expressionET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Not needed
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Not needed
            }

            override fun afterTextChanged(p0: Editable?) {
                try {
                    val expressionStr = binding.expressionET.text.toString()
                    if (expressionStr.isNotEmpty()) {
                        val expression = ExpressionBuilder(expressionStr).build()
                        val result = expression.evaluate()

                        // Check if the result is a whole number
                        val isWholeNumber = result % 1 == 0.0

                        // If it's a whole number, set the text without the decimal part
                        if (isWholeNumber) {
                            binding.resultTV.text = result.toInt().toString()
                        } else {
                            binding.resultTV.text = result.toString()
                        }
                    }
                } catch (e: Exception) {
                    binding.resultTV.text = ""
                }
            }
        })
    }

}