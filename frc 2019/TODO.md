# Stuff To Do

## 8-feb-2019: Corvallis scrimmage 1 week away
**9-feb-2019 tasks**:
* (done) Have a drive program running so the robot can be driven - *the `master` branch will always be
  drivable. Things don't get checked into `master` unless they are drivable
* Have 3 arm positions available on buttons:
  * hatch pickup
  * home position for driving
  * hatch delivery for the lowest height
* define and task all subsystems and commands
* Work through drive control issues
* work through arm control issues
* Test lift code:
  * get the direction correct
  * save the state so you know whether you need to fire the solenoid (once fired it holds - i.e. these
    valves only have extend and retract positions). Seems like there should be some minimum duration
    for the command to make sure the action has happened
* LiftToPlatform (or similar) command:
  * The sequence is the drive backs the robot to the platform, then activates the lift.
  
* Automatic lo-high shift, the driver should not need to do this. Think about the automatic transmission
  in your car:
  * While you are accelerating you get to a sufficiently high engine speed that the transmission
    decides to up-shift.
  * If you apply full power, the car downshifts if:
    * the car cannot accelerate in the current gear;
  * if you stop, the car downshifts to low gear anticipating that you will start again.