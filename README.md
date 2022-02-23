# Simple project

Starter project with functional modules on the next stack:
+ kotlin, coroutines
+ mvvm, hilt, view binding, navigation component
+ retrofit 2, kotlinx-serialization, glide, room
+ junit, espresso
+ gitlab ci

## Has the following features

#### 1. Single Activity with Navigation Component
There is the single MainScreen activity, which contains navigation graph with nested feature graph from feature modules.
BottomBarFragment from feature-bottom-bar is responsible for working with one or more stacks of fragments, it can be launched by BottomBarMediator.

The remaining fragments are recommended to inherit from SimpleFragment/SimpleModalFragment/SimpleBottomSheetDialogFragment,
which provides a convenient way to initialize the fragment.

#### 2. Custom views and screens and resources for light/night mode
The project contains custom elements (and on compose too) and screen templates (Sign In, Sing Up, Pin Code, OnBoarding, Purchase...). 
For more information, see the app_demo. It is the module which demonstrates different opportunity of this template.

#### 3. Universal Lists
Each list item inherits from Populatable and has a view object that inherits a ViewState. 
The adapter uses the ViewStatesAdapter class, which takes a List<ViewState>. 
An example of work can be seen in the app_demo.

#### 4. Feature Toggle
Based on FirebaseRemoteConfig
+ FeatureToggleUpdater for receiving flags at application startup
+ Feature stores available flags with default values
+ FeatureToggle check flag status

#### 5. Billing
Based on Billing 4.0.
SimpleBilling encapsulates the interaction with the library and is responsible for working with purchases.

#### 6. Analytics
Tracker implementation can work with one system for analytics, or with a group of systems. 
The project has an example wrapper for FirebaseAnalytics (FirebaseTracker). 
Access to the Tracker object is through AnalyticsTrackerHolder.

#### 7. A few more tools
AppUpdateProvider
LocationProvider
NetworkStateProvider
PreferencesProvider
FirebaseNotificationsService
BuildInfo (contains info of current build)
Resources containers (ColorSource, ImageSource, TextSource)
Effect (like Result in Kotlin for results of operations)
CacheProvider (with two implementations)
ApiConfig (for build retrofit instances)
apiCall(...) and cachedApiCall(...) (for network requests)
NotificationHelper
BiometricManagerWrapper
Navigation extensions (in nav_ext.kt)
and many other extensions for some routine