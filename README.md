# Simple project

Starter project with functional modules on the next stack:
+ kotlin, coroutines
+ mvvm, hilt, compose, navigation component
+ retrofit 2, kotlinx-serialization, coil, room
+ junit, espresso
+ gitlab ci

## Has the following features

#### 1. Single Activity with Navigation Component
There is the single AppActivity, which contains navigation graph with nested feature graph from feature modules.
BottomBarFragment from feature-bottom-bar is responsible for working with one or more stacks of fragments, 
it can be launched by BottomBarFeatureProvider.

#### 2. Custom views and screens and resources for light/night mode
The project contains custom elements and screen templates (OnBoardingScreen, BottomBarScreen). 
For more information, see the app_demo. It is the module which demonstrates different opportunity of this template.

#### 3. Feature Toggle
Based on FirebaseRemoteConfig
+ FeatureToggleUpdater for receiving flags at application startup
+ Feature stores available flags with default values
+ FeatureToggle check flag status

#### 4. Billing
Based on Billing 4.0. SimpleBilling encapsulates the interaction with the library and is responsible for working with purchases.

#### 5. Analytics
Tracker implementation can work with one system for analytics, or with a group of systems. 
The project has an example wrapper for FirebaseAnalytics (FirebaseTracker). 
Access to the Tracker object is through AnalyticsTrackerHolder.

#### 6. A few more tools
+ AppUpdateProvider
+ LocationProvider
+ NetworkStateProvider
+ PreferencesProvider
+ FirebaseNotificationsService
+ BuildInfo (contains info of current build)
+ Resources containers (ColorValue, ImageValue, TextValue)
+ Effect (like Result in Kotlin for results of operations)
+ CacheProvider (with two implementations)
+ ApiConfig (for build retrofit instances)
+ apiCall(...) and cachedApiCall(...) (for network requests)
+ NotificationHelper
+ Navigation extensions (in nav_ext.kt)
+ and many other extensions for some routine