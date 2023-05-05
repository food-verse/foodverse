# Foodiverse

An online delivery application built with Java Swing.

## 🏛️ Architecture

### `/app`

This is the entry point of the application.

### `/models`

This is where the data classes of the application lie.

### `/overlays`

The overlays of the application live here. Overlays are widgets that get their own id and can be later added to the router **with** their own underlying frame.

### `/pages`

The pages of the application live here. Pages are widgets that get their own id and can be later added to the router **without** an underlying frame.

### `/utility`

Utilities folder includes many helper classes for navigation, input validation, asset loading, file manipulation and database handling.

### `/views`

This is where the views of the application lie. Views are reusable blocks of widgets that describe a part of the user interface. Views are themselves widgets too.

### `/widgets`

The widgets of the application live here. Widgets are reusable blocks of code that implement a single method called `getRef()` which returns a reference to its underlying component. Widgets can also be nested into other widgets, pages or overlays accordingly.

## 📸 Screenshots

![Homepage of Foodiverse](assets/docs/home.webp)
