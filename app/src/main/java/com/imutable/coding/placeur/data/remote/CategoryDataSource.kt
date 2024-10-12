package com.imutable.coding.placeur.data.remote

import com.imutable.coding.placeur.data.remote.services.CategoryService
import com.imutable.coding.placeur.model.Category
import javax.inject.Inject

class CategoryDataSource @Inject constructor(private val categoryService: CategoryService){

    suspend fun fetchAllCategories() = categoryService.fetchAllCategory()

    suspend fun saveCategory(category: Category) = categoryService.saveCategory(category)
}