# Goliath-Overclocking-Utility-FX
Linux Nvidia GPU overclocking utility written in JavaFX - currently Alpha 2 - Alpha 3

## Download
You can download from the [releases](https://github.com/BlueGoliath/Goliath-Overclocking-Utility-FX/releases) page.

## Features(currently)
* Easily get supported Nvidia Settings CLI Attribute readouts
* Change PowerMizer mode
* Apply over or under clocks to graphics core and memory transfer rate
* Increase voltage
* Decrease or increase GPU power limit via Nvidia SMI
* Apply a fan profile(experimental)

## How it works
The Goliath Overclocking utility uses custom made Java Controller classes to push and pull Nvidia attribute data from the command line via the Goliath Terminal class. Graphics core, memory, voltage, and fan are done via nvidia-settings interface while Power limit is done via the nvidia-smi CLI interface. 
This application creates an app folder in your home folder in order to store app settings, GPU info, and fan profiles.
The classes can be found [here](https://github.com/BlueGoliath/GoliathOCBackend) and the Terminal class can be found [here](https://github.com/BlueGoliath/Goliath-Terminal)

## Tweaks

You can tweak values in the application config file 'app.csv' in the folder 'GoliathOU' in your home folder after first use. Currently only `show_extra_attribute_data` is in use which displays raw command line attributes and values as they show in the command line.

## Requirements

* Nvidia binary GPU driver
* Nvidia Settings CLI - may come with the Nvidia binary GPU driver depending on the distrobution you use
* X. Org config file with `cooltbits` set to `31` or some other value that allows Nvidia-Settings CLI access
* Java 8 JRE

Native packaged versions do not need Java as a JRE is automatically included

## Known issues
* Fan is not set back to auto on app 
* Application attribute update thread will crash if the computer goes to sleep while running
* Attribute updating is CPU intensive.
* User root password storing method may not be secure.

## Disclaimer

As per the license, I accept no responsiblity for any damages that happen by using this utility.

