# Simple project

Starter project with functional modules on the next stack:
+ kotlin, coroutines
+ mvvm, hilt, view binding
+ retrofit 2, gson, glide, room
+ junit, espresso
+ gitlab ci

## Has the following features

#### 1. Single Activity
BottomBarHistoryManager is responsible for working with one or more stacks of fragments on a Single Activity. 
Each stack contains a fragment that implements ContainerFragment.

ContainerFragment is responsible for displaying fragments within itself. ContainerFragment.navigateTo(...) replaces 
the current fragment with a new one.

The remaining fragments are recommended to inherit from SimpleFragment, which provides a convenient way 
to initialize the fragment. Through the SimpleFragment.getContainer(...) method, you can access the parent container 
that contains the current one

#### 2. Custom views and screens and resources for light/night mode
The project contains custom elements and screen templates. 
For more information, see the app_demo

#### 3. Universal Lists
Each list item inherits from Populatable and has a view object that inherits a ViewType. 
The adapter uses the ViewTypesAdapter class, which takes a List<ViewType>. 
An example of work can be seen on the WidgetsFragment screen

#### 4. Feature Toggle
Based on FirebaseRemoteConfig
+ FeatureToggleUpdater for receiving flags at application startup
+ Feature stores available flags with default values
+ FeatureToggle check flag status

#### 5. Billing
Based on Checkout lib. 
BillingWrapper encapsulates the interaction with the library and is responsible for working with purchases

#### 6. Analytics
Tracker implementation can work with one system for analytics, or with a group of systems. 
The project has an example wrapper for FirebaseAnalytics (FirebaseTracker). 
Access to the Tracker object is through AnalyticsTrackerHolder