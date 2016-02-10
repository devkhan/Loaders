# Loaders

[![Build Status](https://travis-ci.org/devkhan/Loaders.svg?branch=main)](https://travis-ci.org/devkhan/Loaders)

==========================

This project aims at creating an animation library for different types of loading animations, progress dialogs, etc.

The current structure is:

![Phone Screenshot](https://raw.githubusercontent.com/devkhan/Loaders/main/Screenshots/cocentric_circle2.gif)

![Phone Screenshot](https://raw.githubusercontent.com/devkhan/Loaders/main/Screenshots/cocentric_circles.gif)

![Phone Screenshot](https://raw.githubusercontent.com/devkhan/Loaders/main/Screenshots/heart_shape.gif)

![Phone Screenshot](https://raw.githubusercontent.com/devkhan/Loaders/main/Screenshots/heart_beat.gif)

![Phone Screenshot](https://raw.githubusercontent.com/devkhan/Loaders/main/Screenshots/crossword_grid.gif)

Project: Loaders  
>        |   settings.gradle  
>        +   app/  
>            |   build.gradle  
>            |   java  
>            |   res  
>        +   lib/  
>            |   build.gradle  
>            |   java  
>            |   res  

# Usage

Add dependency 
```gradle
dependencies {
    compile 'teamdapsr.loaders:lib:1.0'
}
```

XML Code:

```xml
<teamdapsr.loaders.lib.ConcentricCircleView
		android:layout_height="wrap_content"
		android:layout_width="wrap_content"
		android:id="@+id/view"
		custom:min_radius="40"
		custom:max_radius="120"
		custom:duration="3000"
		custom:animate_color="false"
		custom:circles_count="15"/>
```

### Contribute

If you want to contribute, please add new class in Loaders/lib/java/.

_Namaste_
