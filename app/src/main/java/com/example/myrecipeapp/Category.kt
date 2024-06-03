package com.example.myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//create a type of object called Category that each item in the CategoriesResponse list will be
@Parcelize
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
):Parcelable

//the type of object that the json response will be set to.  it is made of a list of category items
data class CategoriesResponse(val categories: List<Category>)