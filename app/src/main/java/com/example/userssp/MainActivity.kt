package com.example.userssp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userssp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayout: RecyclerView.LayoutManager

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //PREFERENCES
        val preferences = getPreferences(MODE_PRIVATE)
        val firstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        Log.i("SP", "${getString(R.string.sp_first_time)} = $firstTime")

        //SET VALUE IN PREFERENCES
        preferences.edit().putBoolean(getString(R.string.sp_first_time), false).commit()

        //PREFERENCES

        //this is about comunnication between adapter and activity
        userAdapter = UserAdapter(getUsers(), this)

        linearLayout = LinearLayoutManager(this)

        binding.recyclerView.apply {
            // this is about optimization of recyclerview because we have a fix value
            setHasFixedSize(true)
            layoutManager = linearLayout
            adapter = userAdapter
        }
    }

    private fun getUsers(): List<User>{
        val users = mutableListOf<User>()

        val luigi = User(id = 1, name = "Luigi", lastName = "Avila", url = "https://i.etsystatic.com/26789127/r/il/7eaf67/3161272233/il_fullxfull.3161272233_de6l.jpg")
        val leo = User(id = 2, name = "Leo", lastName = "LeetCode", url = "https://media.licdn.com/dms/image/C5603AQFmG8UwYiYEEg/profile-displayphoto-shrink_800_800/0/1517246933124?e=2147483647&v=beta&t=ji6_sqDQU8OrkM8el1Vl2VjXplcowAPVpwakmceDdaM")
        val miguel = User(id = 3, name = "Miguel", lastName = "LeetCode", url = "https://media.licdn.com/dms/image/C5603AQEbGrxPLveN5g/profile-displayphoto-shrink_800_800/0/1544850161788?e=2147483647&v=beta&t=4zySEFoAQuXvT6s0jujafoLsdUWxxH-3r2dAsMC5xp4")
        val rosa = User(id = 4, name = "Rosa", lastName = "Gomez", url = "https://images.nintendolife.com/28b0544667b71/545x385.jpg")

        users.add(luigi)
        users.add(leo)
        users.add(miguel)
        users.add(rosa)
        users.add(luigi)
        users.add(leo)
        users.add(miguel)
        users.add(rosa)
        users.add(luigi)
        users.add(leo)
        users.add(miguel)
        users.add(rosa)
        users.add(luigi)
        users.add(leo)
        users.add(miguel)
        users.add(rosa)
        users.add(rosa)
        users.add(luigi)
        users.add(leo)
        users.add(miguel)
        users.add(rosa)
        users.add(luigi)
        users.add(leo)
        users.add(miguel)
        users.add(rosa)

        return users
    }

    // this function extend from interface created
    override fun onClick(user: User, position: Int) {
        Toast.makeText(this, "$position : ${user.getFullName()}", Toast.LENGTH_SHORT).show()
    }
}