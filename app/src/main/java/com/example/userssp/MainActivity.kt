package com.example.userssp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userssp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayout: RecyclerView.LayoutManager

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter(getUsers())
        linearLayout = LinearLayoutManager(this)

        binding.recyclerView.apply {
            layoutManager = linearLayout
            adapter = userAdapter
        }
    }

    private fun getUsers(): List<User>{
        val users = mutableListOf<User>()

        users.add(User(id = 1, name = "Luigi", lastName = "Avila", url = ""))
        users.add(User(id = 2, name = "Leo", lastName = "LeetCode", url = ""))
        users.add(User(id = 3, name = "Miguel", lastName = "LeetCode", url = ""))

        return users
    }
}