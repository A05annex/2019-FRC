# 6831 - A05 Annex - FRC 2019

This branch has 1 goal - letting drivers tune control for best drivability. Tuning involves one *driver* with an
optional *tuner*. The driver drives using the 3 axis joystick, and buttons on the joystick to alter the drive
tuning parameters; alternately a tuner changes the parameters of drive
using a gamepad as the driver instructs. At the end of a session the the *best drive* parameters are recorded
and the software is modified to start with those settings. **REMEMBER TO RECORD YOUR FAVORITE SETTINGS SO WE
CAN UPDATE THE PROGRAMMED DEFAULTS FOR THE COMPETITION SOFTWARE**

## Drive Tuning Controls
We use the main joystick for driving. All other buttons are mapped to changing drive parameters, so a single
driver can control everything from one stick. Buttons on the gamepad are also mapped to changing drive parameters
so the driver can work with a `tuner` who adjusts parameters as the driver requests.

The current control mappings:
* joystick (driver)
  * driving:  
    - joystick Y to forward-backward speed
    - joystick twist to turn speed
    - joystick thumb button - fine movement mode when pressed (is this useful)
  * shifting: - has been disabled. We see no gain in this competition to having a high gear.  
  * button 3 & 5 - speed gain - adjusts the maximum speed. `sg:` in the parameter display - range: 0.1 (almost no
    forward speed at full stick) to 1.0 (fastest speed possible at full stick).
  * button 4 & 6 - turn gain - adjusts the maximum turn rate when the robot has no forward/backward speed. `tg:` 
    in the parameter display - range: 0.1 (almost no turn at full twist) to 1.0 (fastest turn possible at full twist).
  * button 7 & 8 - turn gain at speed - adjusts the maximum turn rate at full speed. `tgs:` in the parameter display -
    range: 0.05 (almost no turn at full stick and full twist) to 0.5 (faster than reasonable turn at full stick and full twist).
  * button 9 & 10 - sensitivity at center. give you more sensitivity at slow speeds. `sac:` in the display - range: 1.0
    (linear stick position to speed) to 3.0 (way more sensitivity at center).
  * button 11 & 12 - drive dead band - how much the stick can move/twist before power is applied. This is helpful in
    making sure pushing a button does not also result in unwanted movement. `db:` in the display - range: 0
    (no deadband) to 0.1 (need to move
    10% of the stick/twist range before power is applied)

* Gamepad (drive tunner) - the driver or game coach knows what needs to happen. The arm operator reduces the
  cognitive load on the driver so the driver can focus on driving. The arm operator does these:
  * button A & Y: see 3 & 5 above
  * button B & X: see 4 & 6 above
  * dpad forward & back: see 7 & 8 above
  * dpad right & left: see 9 & 10 above
  * bumper right & left: see 11 & 12 above
  
