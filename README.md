# Foodiverse

An online delivery application built with Java Swing.

## 📸 Screenshots

![Onboarding page of Foodiverse](assets/docs/onboarding.webp)
![Home page of Foodiverse](assets/docs/home.webp)

## 🏛️ Architecture

### [`/app`](src/main/java/com/foodverse/app)

This is the entry point of the application.

### [`/models`](src/main/java/com/foodverse/models)

This is where the data classes of the application lie.

### [`/overlays`](src/main/java/com/foodverse/overlays)

The overlays of the application live here. Overlays are widgets that get their own id and can be later added to the router **with** their own underlying frame.

### [`/pages`](src/main/java/com/foodverse/pages)

The pages of the application live here. Pages are widgets that get their own id and can be later added to the router **without** an underlying frame.

### [`/props`](src/main/java/com/foodverse/props)

This is where the properties of widgets lie. Properties are read-only objects required to build various widgets.

### [`/state`](src/main/java/com/foodverse/state)

This is where the global state of the application is stored.

### [`/utility`](src/main/java/com/foodverse/utility)

Utilities folder includes many helper classes for navigation, input validation, asset loading, file manipulation and database handling.

### [`/views`](src/main/java/com/foodverse/views)

This is where the views of the application lie. Views are reusable blocks of widgets that describe a part of the user interface. Views are themselves widgets too.

### [`/widgets`](src/main/java/com/foodverse/widgets)

The widgets of the application live here. Widgets are reusable blocks of code that implement a single method called [`getRef()`](https://github.com/food-verse/foodverse/blob/a39006f15a34a7f5f0dc05d1d0fe42e768ecebaf/src/main/java/com/foodverse/utility/core/Widget.java#L13) which returns a reference to its underlying component. Widgets can also be nested into other widgets, pages or overlays accordingly.
