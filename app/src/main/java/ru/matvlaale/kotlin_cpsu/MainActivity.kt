package ru.matvlaale.kotlin_cpsu

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), IMainView, SeekBar.OnSeekBarChangeListener {
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var viewModel: MainViewModel
    private lateinit var seekBar: SeekBar
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelOptions()
        findViews()
        addListeners()
    }

    private fun viewModelOptions() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(this, Observer { value ->
            showDateTime(value)
        })
    }

    private fun findViews() {
        button = findViewById(R.id.buttonGetter)
        textView = findViewById(R.id.textShower)
        seekBar = findViewById(R.id.seekBar)
    }

    private fun changeEnable(enable: Boolean) {
        seekBar.isEnabled = enable
        button.isEnabled = enable
    }

    private fun addListeners() {
        seekBar.setOnSeekBarChangeListener(this)
        button.setOnClickListener {
            changeEnable(false)
            val n = seekBar.progress
            thread {
                for (i in 0..n) {
                    viewModel.btnShowDateTime()
                    Thread.sleep(1000)
                }
                handler.post { changeEnable(true) }
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        textView.text = String.format(getString(R.string.chbl_seconds), progress)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    override fun showDateTime(dateTime: String) {
        textView.text = dateTime
    }
}