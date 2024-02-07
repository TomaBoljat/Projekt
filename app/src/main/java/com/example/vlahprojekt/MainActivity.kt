package com.example.vlahprojekt

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.pp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var temperatureSeekBar: SeekBar
    private lateinit var temperatureTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            lineChart.gradientFillColors = intArrayOf(
                Color.parseColor("#64B5F6"),
                Color.TRANSPARENT
            )
            lineChart.animation.duration = animationDuration
            lineChart.animate(lineSet)
            lineChart.onDataPointTouchListener = {index, _, _ ->
            }
        }
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the Up button for backward navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.redSeekBar.setOnSeekBarChangeListener(SeekBarListener())
        binding.greenSeekBar.setOnSeekBarChangeListener(SeekBarListener())
        binding.blueSeekBar.setOnSeekBarChangeListener(SeekBarListener())

        val kuhinjaButton = binding.kuhinjaButton
        val kapijaButton = binding.kapijaButton
        val sobaButton = binding.sobaButton
        val garazaButton = binding.garazaButton

        kuhinjaButton.setOnClickListener {
            kuhinjaButton.setBackgroundColor(Color.RED)
        }

        kapijaButton.setOnClickListener {
            kapijaButton.setBackgroundColor(Color.YELLOW)
        }

        sobaButton.setOnClickListener {
            sobaButton.setBackgroundColor(Color.GREEN)
        }

        garazaButton.setOnClickListener {
            garazaButton.setBackgroundColor(Color.BLUE)
        }
        temperatureSeekBar = findViewById(R.id.temperatureSeekBar)
        temperatureTextView = findViewById(R.id.temperatureTextView)
        temperatureTextView.text = "${temperatureSeekBar.progress}°C"
        temperatureSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                temperatureTextView.text = "$progress°C"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Handle touch start
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Handle touch stop
            }
        })
    }

    companion object {
        private val lineSet = listOf(
            "LABEL1" to 0F,
            "LABEL2" to 4.5F,
            "LABEL3" to 6F,
            "LABEL4" to 2F,
            "LABEL5" to 3F,
            "LABEL6" to 4.5F,
            "LABEL7" to 5.5F,
        )
        private const val animationDuration = 1000L
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Handle back button click
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }
    inner class SeekBarListener : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            val red = binding.redSeekBar.progress
            val green = binding.greenSeekBar.progress
            val blue = binding.blueSeekBar.progress

            // Update color preview
            binding.colorPreview.setBackgroundColor(Color.rgb(red, green, blue))
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }
}