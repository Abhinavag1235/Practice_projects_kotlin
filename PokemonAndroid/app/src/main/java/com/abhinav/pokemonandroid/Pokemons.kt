package com.abhinav.pokemonandroid

import android.location.Location

class Pokemons{
    var name:String?=null
    var desc:String?=null
    var image:Int?=null
    var power:Double?=null
    var location:Location?=null
    var catch:Boolean?=false

    constructor(image:Int,name:String,desc:String,power:Double,lat:Double,lon:Double){
        this.name=name
        this.desc=desc
        this.image=image
        this.power=power
        this.location!!.latitude=lat
        this.location!!.longitude=lon
        this.catch=false
    }


}