enum class Modules(val path: String) {

    APP(":app"),
    APP_STATE(":app-state"),
    APP_DEMO(":app-demo"),

    CORE(":core"),
    CORE_DATA(":core-data"),
    CORE_UI(":core-ui"),
    CORE_MEDIATOR(":core-mediator"),
    CORE_UNIT_TEST(":core-unit-test"),

    BASE_OTP(":base-otp"),
    BASE_BILLING(":base-billing"),

    FEATURE_AUTH(":feature-auth"),
    FEATURE_MAIN(":feature-main"),
    FEATURE_ON_BOARDING(":feature-onboarding"),
    FEATURE_PURCHASE(":feature-purchase"),
    FEATURE_OTP(":feature-otp");

    companion object {
        fun all() = values().map { it.path }.toTypedArray()
    }
}