package com.example.todo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels {
        TaskViewModelFactory(TaskDatabase.getDatabase(this).taskDao())
    }
    private val adapter = TaskAdapter(
        onTaskCheckedChange = { task -> viewModel.update(task) },
        onTaskDelete = { task -> viewModel.delete(task) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.allTasks.observe(this) { tasks ->
            adapter.submitList(tasks)
        }

        binding.addButton.setOnClickListener {
            val title = binding.taskInput.text.toString()
            if (title.isNotBlank()) {
                viewModel.insert(Task(title = title))
                binding.taskInput.text?.clear()
            }
        }
    }
}

class TaskViewModel(private val dao: TaskDao) : ViewModel() {
    val allTasks = dao.getAllTasks()

    fun insert(task: Task) {
        CoroutineScope(Dispatchers.IO).launch { dao.insert(task) }
    }

    fun update(task: Task) {
        CoroutineScope(Dispatchers.IO).launch { dao.update(task) }
    }

    fun delete(task: Task) {
        CoroutineScope(Dispatchers.IO).launch { dao.delete(task) }
    }
}

class TaskViewModelFactory(private val dao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
