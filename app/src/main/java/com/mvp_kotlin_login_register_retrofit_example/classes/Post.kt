package com.mvp_kotlin_login_register_retrofit_example.classes

class Post(
    var id: Int,
    var name: String,
    var desc: String,
    var lat: String,
    var lng: String
)

var posts = arrayListOf(
    Post(0,"p270ya", "lawn parking", "",""),
    Post(1,"p270ya", "parking in the wrong place", "", ""),
    Post(2,"p270ya", "wrong parking", "", ""),
    Post(3,"p270ya", "lawn parking", "", ""),
    Post(4,"p270ya", "parking in the wrong place", "", "")
)