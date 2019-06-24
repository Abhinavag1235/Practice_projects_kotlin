package com.abhinav.pokemonandroid

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import java.lang.Exception

class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkPermission()
    }
var ACCESSLOCATION=123
    fun checkPermission(){
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat
                    .checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),ACCESSLOCATION)
                return
            }
        }
        getUserLocation()
    }
    @SuppressLint("ServiceCast")
    fun getUserLocation(){
        Toast.makeText(this,"Use Location access on",Toast.LENGTH_LONG).show()
        var mylocation= MYlocationListner()
        var locationManager= getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,mylocation)

        var mythread=myThread()
        mythread.start()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            ACCESSLOCATION->{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getUserLocation()
                }else{
                    Toast.makeText(this,"We cannot access your Location",Toast.LENGTH_LONG).show()
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney)
            .title("Me")
            .snippet("Here is my loation")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,2f))


        for(i in 0..listPokemon.size-1){
            var pokemon=listPokemon[i]
            if(pokemon.catch==false){
                var pokemons = LatLng(pokemon.location!!.latitude,pokemon.location!!.longitude)
                mMap.addMarker(MarkerOptions().position(pokemons)
                    .title(pokemon.name!!)
                    .snippet(pokemon.desc!!+",power:"+pokemon.power!!)
                    .icon(BitmapDescriptorFactory.fromResource(pokemon.image!!)))


                if(locations!!.distanceTo(pokemon.location!!)<2){
                    pokemon.catch=true
                    listPokemon[i]=pokemon
                    playrepower += pokemon.power!!
                    Toast.makeText(this,"Player power is now $playrepower",Toast.LENGTH_LONG).show()

                }
            }
        }
    }
    var playrepower:Double=0.0
    var locations:Location?=null

    inner class MYlocationListner:LocationListener{


        constructor(){
            locations= Location("Start")
            locations!!.longitude=0.0
            locations!!.latitude=0.0
        }
        override fun onLocationChanged(location: Location?) {
            locations=location
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(provider: String?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(provider: String?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    var oldLocation:Location?=null
    inner class myThread:Thread{

        constructor():super(){
            oldLocation= Location("Start")
            oldLocation!!.latitude=0.0
            oldLocation!!.longitude=0.0

        }
        override fun run(){
            while(true){
                try{
                    loaddata()
                    if(oldLocation!!.distanceTo(locations)==0f){
                        continue
                    }
                    oldLocation= this@MapsActivity.locations
                    runOnUiThread {
                        mMap.clear()
                        val sydney = LatLng(locations!!.latitude,locations!!.longitude)
                        mMap.addMarker(MarkerOptions().position(sydney)
                            .title("Me")
                            .snippet("Here is my loation")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,2f))

                    }
                    Thread.sleep(1000)
                }catch (ex:Exception) { }            }
            }
        }

    var listPokemon=ArrayList<Pokemons>()

    fun loaddata(){
        listPokemon.add(Pokemons(R.drawable.charmander,
            "Charmander", "Charmander living in jaipur", 55.0, 26.922070, 75.778885))
        listPokemon.add(Pokemons(R.drawable.bulbasaur,
            "Bulbasaur", "Bulbasaur living in jaipur", 90.5, 26.838386, 75.833859))
        listPokemon.add(Pokemons(R.drawable.squirtle,
            "Squirtle", "Squirtle living in jaipur", 33.5, 26.888456, 75.888573))
    }
}

