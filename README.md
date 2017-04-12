# Goliath-Overclocking-Utility-FX
Linux Nvidia GPU overclocking utility written in JavaFX - currently pre-alpha
---
## Download
You can download releases from [the download page](https://github.com/BlueGoliath/Goliath-Overclocking-Utility-FX/releases)
## Features(currently)
* Easily get supported Nvidia Settings CLI Attribute readouts
* Change PowerMizer mode
* Apply over or under clocks to graphics core and memory transfer rate
* Increase voltage
* Decrease or increase GPU power limit via Nvidia SMI
---
## How it works
The Goliath Overclocking utility uses custom made Java classes which utilize the Goliath Terminal class by pushing(setting) and pulling(getting) Nvidia Settings attributes.
This application creates an app folder in your home folder in order to store app settings, GPU info, and fan profiles.

The classes can be found [here](https://github.com/BlueGoliath/GoliathOCBackend) and the Terminal class can be found [here](https://github.com/BlueGoliath/Goliath-Terminal)
---
## Requirements
* Nvidia binary GPU driver
* Nvidia Settings CLI - may come with the Nvidia binary GPU driver depending on the distrobution you use
* X. Org config file with `cooltbits` set to `31` or some other value that allows Nvidia-Settings CLI access
* Java 8 JRE

Native packaged versions do not need Java as a JRE is automatically included
---
## Known issues
* Spinner UI components do not automatically update on key type
* Spinner exception is thrown if a user attempts to enter a non numeric number
* Application attribute update thread will crash if the computer goes to sleep while running
* Attribute updating is CPU intensive.
* User root password storing method may not be secure.
---
## Disclaimer

As per the license, I accept no responsiblity for any damages that happen by using this utility.

