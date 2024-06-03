package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    //private variable that gets manipulated
    private val _categoriesState = mutableStateOf(RecipeState())
    //public variable that exposes private variable's value
    val categoriesState: State<RecipeState> = _categoriesState

    //runs on initialization, i.e. when this viewmodel is created
    init {
        fetchCategories()
    }

    //gets categories list from api
    private fun fetchCategories() {
        //allows coroutine to be launched in the viewmodel scope
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    //data class that contains state info
    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}