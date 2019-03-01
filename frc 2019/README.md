# 6831 - A05 Annex - FRC 2019

This branch has 1 goal - letting drivers tune control for best drivability. Tuning involves one driver, and
one tuner. the driver drives using the 3 axis joystick; and the tuner changes the parameters of drive
using a gamepad as the driver instructs. At the end of a session the the *best drive* parameters are recorded
and the software is modified to start with those settings.
## Current Controls
Control mappings change as drivers suggest changes that would make the robot more controllable. The current
control configuration uses a fancy joystick for the primary driving, and hatch capture/release and cargo
capture/release operations; and an auxiliary gamepad for arm and hatch-cargo bucket positioning.

The current control mappings:
* joystick (driver)
  * driving:  
    - joystick Y to forward-backward speed
    - joystick twist to turn speed
  * shifting: - has been disabled. We see no gain in this competition to having a high gear.  
  * button 3 & 5 - cargo (ball) collect and expel respectively - right now they just run the wheels. In
    competition, button 3, could initiate the pickup cargo sequence that positions the arm/bucket for
    pickup, turns on
    the pickup wheels, ends when the pickup limit switch is activated and moves the bucket to the lower
    cargo (ball) score position.
  * button 4 & 6 - hatch cover grab and release respectively.
  * button 7 & 8 - endgame, 7 lifts arm/bucket to position to drive the robot against the pedestal for endgame
    lift, and 8 initiates the lift sequence.
  * button 9 & 10 - unused
  * button 11 & 12 - unused

* Gamepad (arm operator) - the driver or game coach knows what needs to happen. The arm operator reduces the
  cognitive load on the driver so the driver can focus on driving. The arm operator does these:
  * button A: move arm/bucket to lower position
    * without right trigger - move to lower hatch (more hatches than balls)
    * with right trigger - move to lower cargo (ball)
  * button B: move arm/bucket to middle position
    * without right trigger - move to middle hatch (more hatches than balls)
    * with right trigger - move to middle cargo (ball)
  * button Y: move arm/bucket to top position
    * without right trigger - move to upper hatch (more hatches than balls)
    * with right trigger - move to upper cargo (ball)
  * button X: move arm/bucket to the home (inside the robot perimeter) position. This is most stable for driving.
  * Position Tuning - this is fine tuning the position of the arm/bucket once it has been sent to a lower,
    middle, upper position. Fine tuning updates the reference position so it will return to the tuned position
    next time the position is requested. 
    * dpad forward:
      * without left trigger - lower arm down
      * with left trigger - upper arm down
    * dpad back:
      * without left trigger - lower arm up
      * with left trigger - upper arm up
    * dpad right: bucket up/back
    * dpad left: bucket down/forward  
  
