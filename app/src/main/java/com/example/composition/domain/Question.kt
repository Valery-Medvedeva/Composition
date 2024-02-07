package com.example.composition.domain

data class Question (
    val sum: Int,
    val visibleNumber: Int,
    val options: List<Int>
)