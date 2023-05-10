package com.example.userssp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userssp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

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
        Log.i(
            "SP",
            "${getString(R.string.sp_username)} = ${
                preferences.getString(
                    getString(R.string.sp_username),
                    "NA"
                )
            }"
        )

        //SET VALUE IN PREFERENCES
        val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)

        Log.i("SP", "Valor de first time $firstTime")

        if (firstTime) {

            // Basic usage of alert dialog
            /*MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.dialog_title))
                .setView(dialogView)
                .setCancelable(false)
                .setNeutralButton(getString(R.string.dialog_user_guest), null)
                .setPositiveButton(getString(R.string.dialog_confirm)) { _, _ ->
                    val username =
                        dialogView.findViewById<TextInputEditText>(R.id.etUsername).text.toString()
                    with(preferences.edit()) {
                        putBoolean(getString(R.string.sp_first_time), false)
                        putString(getString(R.string.sp_username), username).apply()
                    }
                    Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_SHORT)
                        .show()
                }
                .show()
             */

            // Alert dialog with validation
            val dialog = MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.dialog_title))
                .setView(dialogView)
                .setCancelable(false)
                .setNeutralButton(getString(R.string.dialog_user_guest), null)
                .setPositiveButton(getString(R.string.dialog_confirm)) { _, _ -> }
                .create()

            dialog.show()

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val etUserName = dialogView.findViewById<TextInputEditText>(R.id.etUsername)
                val username = etUserName.text.toString()
                if (username.isBlank()) {
                    etUserName.setText("")
                    Toast.makeText(this, getString(R.string.register_invalid), Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Log.i("SP", "Se guarda el nombre de $username")
                    with(preferences.edit()) {
                        putBoolean(getString(R.string.sp_first_time), false)
                        putString(getString(R.string.sp_username), username)
                            .apply()
                    }
                    Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_SHORT)
                        .show()
                    dialog.dismiss()
                }
            }

        } else {
            val username = preferences.getString(
                getString(R.string.sp_username),
                getString(R.string.hint_username)
            )
            Toast.makeText(this, "Bienvenido $username", Toast.LENGTH_SHORT).show()
        }

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

        // configuration about swipe to left to delete user
        val swipeHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean = false

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                   userAdapter.remove(viewHolder.adapterPosition)
                }

            })
        swipeHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun getUsers(): MutableList<User> {
        val users = mutableListOf<User>()

        val luigi = User(
            id = 1,
            name = "Luigi",
            lastName = "Avila",
            url = "https://i.etsystatic.com/26789127/r/il/7eaf67/3161272233/il_fullxfull.3161272233_de6l.jpg"
        )
        val leo = User(
            id = 2,
            name = "Leo",
            lastName = "LeetCode",
            url = "https://media.licdn.com/dms/image/C5603AQFmG8UwYiYEEg/profile-displayphoto-shrink_800_800/0/1517246933124?e=2147483647&v=beta&t=ji6_sqDQU8OrkM8el1Vl2VjXplcowAPVpwakmceDdaM"
        )
        val miguel = User(
            id = 3,
            name = "Miguel",
            lastName = "LeetCode",
            url = "https://media.licdn.com/dms/image/C5603AQEbGrxPLveN5g/profile-displayphoto-shrink_800_800/0/1544850161788?e=2147483647&v=beta&t=4zySEFoAQuXvT6s0jujafoLsdUWxxH-3r2dAsMC5xp4"
        )
        val rosa = User(
            id = 4,
            name = "Rosa",
            lastName = "Gomez",
            url = "https://images.nintendolife.com/28b0544667b71/545x385.jpg"
        )

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