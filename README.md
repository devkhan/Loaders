# Loaders

[![Build Status](https://travis-ci.org/devkhan/Loaders.svg?branch=main)](https://travis-ci.org/devkhan/Loaders)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Loaders-blue.svg?style=flat)](http://android-arsenal.com/details/1/3130)

An animation library consisting of different types of loading animations, progress dialogs, etc.


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

### License

![WTFPL Badge](http://www.wtfpl.net/wp-content/uploads/2012/12/wtfpl-badge-4.png)

_Namaste_
