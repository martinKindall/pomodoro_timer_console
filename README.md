# Pomodoro Timer

The goals of this project are:

- Take care of the back spine, by standing and streching the body from time to time using the __pomodoro technique__.
- Put into practice the [Dependency Rule](https://www.informit.com/articles/article.aspx?p=2832399) from _Clean Architecture_.


## Usage

Just run the main function, located in _MainComponent.java_. You have to pass a single _arg_ containing the path to the following python script __AudioPlayer.py__ (you can locate it anywhere in your system) :

```python
from playsound import playsound

import sys

if len(sys.argv) != 2:
    raise ValueError("One argument needed")

wavFile = sys.argv[1]

playsound(wavFile)

```

In the same path of the python script, there must be two ._wav_ files (sound files), called _break.wav_ and _work.wav_. Each one will play when the pomodoro break starts and finishes. 

__Important__: The python script must be called __AudioPlayer.py__, the same goes for the name of the .wav files.

## Example

After building the fat-jar file, you can run the program like this:

```
java -jar pomodoro_with_dependencies.jar /path/to/python/script/and/wav
```

Afterwards, there will appear a prompt that asks for work time in minutes:

```
Enter work time in minutes: 
``` 

And a similar prompt for break time in minutes. Then, the work time will immediately start running. Happy Work!