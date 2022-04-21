package com.example.portalnoticias

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.*

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.example.portalnoticias.fragments.adapters.ViewPagerAdapter
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.FirebaseFirestore
import androidx.appcompat.widget.Toolbar
import com.example.portalnoticias.fragments.*


class MainActivity : AppCompatActivity() {
    lateinit var drawer:ActionBarDrawerToggle
    lateinit var DesignDrawer: DrawerLayout
    lateinit var NavigationView : NavigationView
    var db = FirebaseFirestore.getInstance()




    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.mainToolbar)
        //Botones de atras y acerca de
        val imgabout = findViewById<ImageView>(R.id.aboutimg)




        //hacer los botones clickeables
        imgabout.setClickable(true);



        val textCustomTitle = findViewById<TextView>(R.id.custom_title)
        val customFont = Typeface.createFromAsset(this.assets, "fonts/RobotoSlab-Bold.ttf")

        // Set custom title
        textCustomTitle.setTypeface(customFont)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setTitle("")

        NavigationView=findViewById(R.id.navigation)
        DesignDrawer=findViewById(R.id.drawer_layout)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        drawer = ActionBarDrawerToggle(this, DesignDrawer, R.string.open, R.string.close)
        DesignDrawer.addDrawerListener(drawer)
        drawer.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpTabs()


        imgabout.setOnClickListener(){
            setContentView(R.layout.fragment_about)

        }




        NavigationView.setNavigationItemSelectedListener {
            val video_view: VideoView =findViewById(R.id.videoView)



            val controlvideo = MediaController(this)
            controlvideo.setAnchorView(video_view)
            video_view.setVideoPath("https://cdn.discordapp.com/attachments/871178674328719390/872031095447773214/V2.mp4")

            video_view.setMediaController(controlvideo)
            video_view.requestFocus()
            video_view.start()
            var titulonoticia:String = ""

            val titulo = findViewById<TextView>(R.id.titulo)
            val descricpion = findViewById<TextView>(R.id.descripcion)
            val fecha = findViewById<TextView>(R.id.fecha)

            when (it.itemId){

                R.id.op1 -> {
                    var datos = ""


//Do query to get nickname (from users collection with user's mail)

                    val query = db.collection("noticias").document("coronavirus").get()
                        .addOnSuccessListener {
                                document ->
                            if (document != null) {
                                Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                                val data = document.data as Map<String, String>

                                descricpion.text = data["descripcion"]
                                fecha.text = data["fecha"]
                                titulo.text = data["nombre"]
                                val videourl = data["url"]
                                video_view.setVideoPath(videourl)
                                textCustomTitle.text = "Coronavirus"
                            } else {
                                Log.d(TAG, "No such document")
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.d(TAG, "get failed with ", exception)
                        }

                }

                R.id.op2 -> {var datos = ""


//Do query to get nickname (from users collection with user's mail)

                    val query = db.collection("noticias").document("nacional").get()
                        .addOnSuccessListener {
                                document ->
                            if (document != null) {
                                Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                                val data = document.data as Map<String, String>

                                descricpion.text = data["descripcion"]
                                fecha.text = data["fecha"]
                                titulo.text = data["nombre"]
                                val videourl = data["url"]
                                video_view.setVideoPath(videourl)
                                textCustomTitle.text = "Nacional"
                            } else {
                                Log.d(TAG, "No such document")
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.d(TAG, "get failed with ", exception)
                        } }
                R.id.op3 -> {val query = db.collection("noticias").document("internacional").get()
                    .addOnSuccessListener {
                            document ->
                        if (document != null) {
                            Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                            val data = document.data as Map<String, String>

                            descricpion.text = data["descripcion"]
                            fecha.text = data["fecha"]
                            titulo.text = data["nombre"]
                            val videourl = data["url"]
                            video_view.setVideoPath(videourl)
                            textCustomTitle.text = "Internacional"
                        } else {
                            Log.d(TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "get failed with ", exception)
                    }}
                R.id.op4 -> {val query = db.collection("noticias").document("estados").get()
                    .addOnSuccessListener {
                            document ->
                        if (document != null) {
                            Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                            val data = document.data as Map<String, String>

                            descricpion.text = data["descripcion"]
                            fecha.text = data["fecha"]
                            titulo.text = data["nombre"]
                            val videourl = data["url"]
                            video_view.setVideoPath(videourl)
                            textCustomTitle.text = "Estados"
                        } else {
                            Log.d(TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "get failed with ", exception)
                    }}
                R.id.op5 -> {val query = db.collection("noticias").document("entretenimiento").get()
                    .addOnSuccessListener {
                            document ->
                        if (document != null) {
                            Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                            val data = document.data as Map<String, String>

                            descricpion.text = data["descripcion"]
                            fecha.text = data["fecha"]
                            titulo.text = data["nombre"]
                            val videourl = data["url"]
                            video_view.setVideoPath(videourl)
                            textCustomTitle.text = "Entretenimiento"
                        } else {
                            Log.d(TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "get failed with ", exception)
                    }}
                R.id.op6 -> {val query = db.collection("noticias").document("Red").get()
                    .addOnSuccessListener {
                            document ->
                        if (document != null) {
                            Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                            val data = document.data as Map<String, String>

                            descricpion.text = data["descripcion"]
                            fecha.text = data["fecha"]
                            titulo.text = data["nombre"]
                            val videourl = data["url"]
                            video_view.setVideoPath(videourl)
                            textCustomTitle.text = "Red"
                        } else {
                            Log.d(TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "get failed with ", exception)
                    }}
                R.id.op7 -> {val query = db.collection("noticias").document("varios").get()
                    .addOnSuccessListener {
                            document ->
                        if (document != null) {
                            Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                            val data = document.data as Map<String, String>

                            descricpion.text = data["descripcion"]
                            fecha.text = data["fecha"]
                            titulo.text = data["nombre"]
                            val videourl = data["url"]
                            video_view.setVideoPath(videourl)
                            textCustomTitle.text = "Varios"
                        } else {
                            Log.d(TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "get failed with ", exception)
                    }}
                R.id.op8 -> {val query = db.collection("noticias").document("reportajes-especiales").get()
                    .addOnSuccessListener {
                            document ->
                        if (document != null) {
                            Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                            val data = document.data as Map<String, String>

                            descricpion.text = data["descripcion"]
                            fecha.text = data["fecha"]
                            titulo.text = data["nombre"]
                            val videourl = data["url"]
                            video_view.setVideoPath(videourl)
                            textCustomTitle.text = "Report. Especiales"
                        } else {
                            Log.d(TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "get failed with ", exception)
                    }}
                R.id.op9 -> {val query = db.collection("noticias").document("un-minuto").get()
                    .addOnSuccessListener {
                            document ->
                        if (document != null) {
                            Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                            val data = document.data as Map<String, String>

                            descricpion.text = data["descripcion"]
                            fecha.text = data["fecha"]
                            titulo.text = data["nombre"]
                            val videourl = data["url"]
                            video_view.setVideoPath(videourl)
                            textCustomTitle.text = "N. en un min"
                        } else {
                            Log.d(TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "get failed with ", exception)
                    }}
                R.id.op10 -> {val query = db.collection("noticias").document("extra").get()
                    .addOnSuccessListener {
                            document ->
                        if (document != null) {
                            Log.d(TAG, "DocumentSnapshot data: ${document.data}")

                            val data = document.data as Map<String, String>

                            descricpion.text = data["descripcion"]
                            fecha.text = data["fecha"]
                            titulo.text = data["nombre"]
                            val videourl = data["url"]
                            video_view.setVideoPath(videourl)

                        } else {
                            Log.d(TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "get failed with ", exception)
                    }  }
                else -> {video_view.setVideoPath("https://cdn.discordapp.com/attachments/871178674328719390/872031095447773214/V2.mp4")}

            }

            true
        }






    }
//
    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabs = findViewById<TabLayout>(R.id.tabs)
        adapter.addFragment(HomeFragment(),"Inicio")
        adapter.addFragment(CovidFragment(), "Covid-19")
        adapter.addFragment(ClimaFragment(), "Clima")
        adapter.addFragment(TokyoFragment(), "Tokyo 2020")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_home)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_virus)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_weather)
        tabs.getTabAt(3)!!.setIcon(R.drawable.ic_olympics)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(drawer.onOptionsItemSelected(item))
            true
        return super.onOptionsItemSelected(item)

    }

    

}


