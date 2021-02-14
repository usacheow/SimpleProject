package com.usacheow.coreui.utils.date

enum class DateFormat(val code: String) {
    dd("dd"),
    mm("mm"),
    yyyy("yyyy"),
    yy("yy"),
    HH_mm("HH:mm"),
    yyyy__MM__dd("yyyy-MM-dd"),
    yyyy__MM("yyyy-MM"),
    dd___MM("dd.MM"),
    MM__yyyy("MM ''yyyy"),
    MMMM__yy("MMMM ''yy"),
    MM("MM"),
    MMMM("MMMM"),
    d_MMMM("d MMMM"),
    dd__MM__yyyy("dd.MM.yyyy"),
    dd_MMMM_yyyy("dd MMMM yyyy"),
    yyyy__MM__dd_T_hh_mm_ss("yyyy-MM-ddTHH:mm:ss"),
}