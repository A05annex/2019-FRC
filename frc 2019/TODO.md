# Stuff To Do


## 2-mar-2019: 2<sup>nd</sup> 2hr Un-Bag

### Pre Un-Bag ###
- [ ] Install drive tuning code on the practice robot - make it ready for driver testing.
- [ ] Build team:
  - [ ] Battery hold-down fixed so it wont catch and tear off arm pneumatics and wiring.
  - [ ] Lift cylinders fixed so we can drive the robot.
- [ ] Resolve github issue with ```commandGroup``` and ```commandgroup``` - if you clone from
      github it is ```commandGroup```
- [ ] Review the **During Un-Bag** list and agree on order.

### During Un-Bag ###
- [ ] Verify drive works.
- [ ] Verify lift starts in retracted position.
- [ ] Verify/recalibrate arm pots (positions) - NOTE: upper arm 90&deg; should be when the
      lower/upper arm pivot and upper/bucket pivot are at the same height.
- [ ] Measure and set (in Constants) arm lengths.
- [ ] Verify starting position for bucket encoder reset.
- [ ] Using current positions table, tune arm PID gains - move them to Constants. Determine
      a reasonable tolerance for the arm being at the requested position.
- [ ] Tune arm position table:
  - [ ] Home position
  - [ ] Lower position
  - [ ] Middle position
  - [ ] Upper position
  - [ ] Ball pickup
- [ ] Endgame Lift Tuning - uncomment the step-wise execution of lift:
  - [ ] Pre-Lift position - where is the bucket when you get on the lift.
  - [ ] Ready for lift position - arm down on platform, almost ready to lift - completely
        reverses the integral correction.
  - [ ] Lift:
    - [ ] Cylinder extension, how long does it really take? - let's time this.
    - [ ] Reliability of lift - how often does the robot fall over?
    - [ ] Arm end position in lift, time to end position.
  - [ ] Arm pull onto platform
    - [ ] Drive dime/distance
    - [ ] Arm position
    - [ ] Drive back-off
  - [ ] Retract lifters
  - [ ] Full drive onto platform:
    - [ ] final arm position
    - [ ] Drive dime/distance
    
### Post Un-Bag ###
- [ ] Review:
  - [ ] mechanical issues that need resolution
  - [ ] programming issues that need resolution, plan for who does those
  
  


## 8-feb-2019: Corvallis scrimmage 1 week away
- [x] Have a drive program running so the robot can be driven - *the `master` branch will always be
  drivable. Things don't get checked into `master` unless they are drivable
- [x] Have 3 arm positions available on buttons:
  - [x] hatch pickup
  - [x] home position for driving
  - [x] hatch delivery for the lowest height
- [x] define and task all subsystems and commands
- [ ] Work through drive control issues
- [x] work through arm control issues
- [x] Test lift code:
  - [x] get the direction correct
  - [x] save the state so you know whether you need to fire the solenoid (once fired it holds - i.e. these
    valves only have extend and retract positions). Seems like there should be some minimum duration
    for the command to make sure the action has happened
- [x] LiftToPlatform (or similar) command:
  - [x] The sequence is the drive backs the robot to the platform, then activates the lift.
  
- [ ] Automatic lo-high shift, the driver should not need to do this. Think about the automatic transmission
  in your car:
  * While you are accelerating you get to a sufficiently high engine speed that the transmission
    decides to up-shift.
  * If you apply full power, the car downshifts if:
    * the car cannot accelerate in the current gear;
  * if you stop, the car downshifts to low gear anticipating that you will start again.## 2-mar-2019: 2<sup>nd</sup> 2 hr Un-Bag
