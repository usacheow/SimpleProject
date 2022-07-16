package com.usacheow.basebilling.model

enum class Sku(val code: String, val isConsumable: Boolean, val type: ProductType) {

    ITEM("sku_id", false, ProductType.SUBSCRIBE);

    companion object {
        fun byCode(code: String) = values().firstOrNull { it.code == code }

        fun byType(type: ProductType) = values().filter { it.type == type }.map { it.code }
    }
}