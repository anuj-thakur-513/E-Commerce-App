package com.example.onlinestore

class EProduct {
    var id: Int
    var name:String
    var price: Int
    var brand: String
    var pictureName: String

    constructor(id: Int, name: String, price: Int, brand: String, picture: String){
        this.id = id
        this.name = name
        this.price = price
        this.brand = brand
        this.pictureName = picture
    }
}