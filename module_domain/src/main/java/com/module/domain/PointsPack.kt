package com.module.domain

data class PointsPack(
    val list: List<Point>,
)

data class Point(
    val x: X,
    val y: Y,
)

@JvmInline
value class X(val value: Double)

@JvmInline
value class Y(val value: Double)