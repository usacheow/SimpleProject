package com.usacheow.coreui.billing

enum class Sku(val code: String, val isConsumable: Boolean, val type: ProductType) {
    ITEM("sku_id", false, ProductType.SUBSCRIPTIONS);

    companion object {
        fun byCode(code: String) = values().firstOrNull { it.code == code }

        fun byType(type: ProductType) = values().filter { it.type == type }
    }
}