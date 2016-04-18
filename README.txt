This was a group project for an object oriented design course at SJSU.
I had three other group members, Ai Nakamura, Richard Lack, and Aaron Mednick.

We were attempting to create an android app that would be a puzzle type alarm clock, in order to make it harder to stay in bed and ignore an easily silenced alarm.

Since most of the other group members were not entirely familiar with android and Android studio, the project ended more about being a crash course in android for them and a lot of android studio syncing and debugging.
The final product was their demos and tutorial products being stitched together by the alarm framework that I made, it definitely wasn't pretty, and it wasn't easy but it did function and was very hard to turn off.

The most interesting bit of programming I added was the way the alarm sound itself worked. On android a process can be made to run after the screen has been locked, and to keep the implementation simple and deliver on time, I ended up creating a loop would run for 30 minutes in the background playing an alarm sound through the MP3 player function in order to allow the alarm to keep itself at max volume (since if you turned down the alarm made by an explicit ALARM function it would only run at 1/2 volume and could not raise itself any higher). The loop process would only be ended upon sucessful completetion of however many puzzles you had set up for yourself and so even if you exited the app and turned off the screen you would not be able to 'hit snooze'. 
