package com.vikalpchaurasia.customLauncher.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vikalpchaurasia.customLauncher.dayList.DayList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class HomeScreenViewModel : ViewModel() {
    private val _currentTime = MutableLiveData<String>()
    val currentTime: LiveData<String> get() = _currentTime

    private val _quote = MutableLiveData<String>()
    val quote: LiveData<String> get() = _quote


    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        startUpdatingTime()
        startUpdatingQuote()
    }

    private fun startUpdatingTime() {
        uiScope.launch {
            while (true) {
                _currentTime.value = getCurrentTime()
                delay(60 * 1000)
            }
        }
    }


    private fun startUpdatingQuote() {
        uiScope.launch {
            while (true) {
                _quote.value = getRandomQuote()
                delay(60 * 60 * 1000)  // Update every hour
            }
        }
    }

    private fun getCurrentTime(): String {
        val currentTime = System.currentTimeMillis()
        val dateFormat = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
        return dateFormat.format(currentTime)
    }

    private fun getRandomQuote(): String {
        // Get a random index within the range of the quotes list
        val randomIndex = Random.nextInt(quotes.size)

        // Return the quote at the randomly selected index
        return quotes[randomIndex]
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val weekDays = listOf(
        DayList("M", "Mon"),
        DayList("T", "Tue"),
        DayList("W", "Wed"),
        DayList("T", "Thu"),
        DayList("F", "Fri"),
        DayList("S", "Sat"),
        DayList("S", "Sun")
    )

    fun getCurrentDateAndDay(): Pair<String, String> {
        val currentDate = getCurrentDate()
        val currentDay = getCurrentDayOfWeek()

        return Pair(currentDate, currentDay)
    }

    private fun getCurrentDayOfWeek(): String {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val daysArray = arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        return daysArray[dayOfWeek - 1]
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd-MMM", Locale.getDefault())
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }

    private val quotes = listOf(
        "Believe you can and you're halfway there.",
        "The only way to do great work is to love what you do.",
        "Your time is limited, don't waste it living someone else's life."
    )
}