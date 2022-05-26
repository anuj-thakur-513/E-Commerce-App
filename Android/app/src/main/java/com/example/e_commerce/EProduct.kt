package com.example.e_commerce

class EProduct {
    var id: Int
    var name: String
    var price: Float
    var productPicture: Int

    constructor(id: Int, name: String, price: Float, productPicture: Int){
        this.id = id
        this.name = name
        this.price = price
        this.productPicture = productPicture
    }

}