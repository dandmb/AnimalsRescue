package com.dmb25.jpcompose_practice.domain.model

import androidx.annotation.DrawableRes

data class Pet(
    val name:String,
    val gender:String,
    val age:String,
    val breed:String,
    val color:String,
    val location:String,
    @DrawableRes val image:Int,
    val description:String,
    val owner: Owner,
    val id:Int
)