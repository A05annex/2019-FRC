# 6831 - AO5 Annex - FRC 2019

Entirely new robot. The big electronics and control system change is that we are using
[Talon SRX motor controllers](#TALON-SRX-Motor-Controllers) and wiring
everything together using the CAN bus instead of PWM (see
[PWM and CAN](https://alex-spataru.gitbooks.io/frc-robot-programming/content/Book/Chapters/1.3.html) for what that
means). The big software change is that we are using the
[WPILib](https://wpilib.screenstepslive.com/s/currentCS/m/java/l/272787-frc-java-wpilib-api-documentation) framework
for programming the robot.

## Current Controls
Control mappings change as drivers suggest changes that would make the robot more controllable. The current
control configuration uses a fancy joystick for the primary driving, and hatch capture/release and cargo
capture/release operations; and an auxiliary gamepad for arm and hatch-cargo bucket positioning.

The current control mappings - we want to move towards 2 peron control - driver, arm operator:
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
  
**Issues:**
* **premature endgame lift** - initiating endgame lift anytime before we are at endgame lift position completely
  compromises the robot. We need a failsafe. Some possibilities:
  * test that arm/bucket is in pre-endgame lift position
  * require simultaneous driver and arm operator button presses to initiate endgame lift sequence.
* **non-intuitive arm/bucket tuning** - tuning arm angles is not intuitive, remap this to moving the bucket
  up/down (the forward, backward axis), in/out (the right/left axis), and rotating the bucket (left trigger
  with forward/backward).
* **setting arm positioning for ball/hatch** If ball and hatch require different positions we can sense
  that we have a ball (ball limit switch is thrown), hatch grabber is activated, or
  that we are empty (returning to depot pickup position). That would eliminate any need for
  driver to control ball/hatch choice.
* **drive tuning** tuning speeds and sensitivity for best driver experience and controllability.

## Semi-Autonomous Operations
These are operations that require driver action to initiate, but complete autonomously once initiated. The driver
handles the logistical problem of getting the robot to the right places on field the without getting entangled with
other robots; and/or decides the operation that needs to be performed next, then hands details of performing the
operation to the robot.

These are the semi-autonomous activities performed by the robot:
* **Arm Positioning** - (done, needs testing and tuning) automatic arm positioning based on a table of target
  arm positions.
  * **Getting to a position**
    * **set target position** (done, tested) While this works, changes in arm are a bit violent.
    * **set a series of target positions by arm angle interpolation** (done, needs more testing) This seems to work
      pretty well, though we were only able to test this after the bucket was removed and before bagging. Need to
      complete testing.
    * **set a series of target positions by path interpolation** - (in progress)
  * **Tuning the position**
* **Cargo Pickup** - (prototypes, not tested, need new bucket installed)
* **Endgame Lift** - (done, needs tuning for new arm) Automatic endgame lift is programmed and has been tested. The
  most serious issue we have is getting the cylinders to deploy at the same rate so the robot does not fall over.
* **Auto-center for depot or rocket/cargo target** - (in progress) image recognition for position control is being
  worked on  but is not currently operational.

## Subsystems
All subsystems now exist and are connected to the driver station in some fashion.

## Commands


## Talon SRX Motor Controllers
To find out everything about these, start at
[Talon SRX](http://www.ctr-electronics.com/control-system/motor-control/talon-srx.html) - all the important
documentation links are in the
[Tech Resources](http://www.ctr-electronics.com/control-system/motor-control/talon-srx.html#product_tabs_technical_resources).
The cool things about these controllers are:
* **CAN bus** - the wiring of the robot is cleaner and easier
* **PID control in the controller** - The controller includes an on board PID processor and encoder/potentiometer
  input for the sensor driving the PID. This is important because the controller can sample and adjust at a very
  controlled rate not subject to what is happening in the Roborio.
* **Limit Switch in the controller** - limit switches can be connected directly to the controller. Again, this
  provides very reliable limit switch operation independent of what is happening in the Roborio.

The difficult thing is that the controller needs some setup configuration before you can expect it to work
as advertised. Specifically, we need the Phoenix Tuner (from
[Cross the Road Electronics](http://www.ctr-electronics.com/)). Specific setup notes are with the subsystem
notes (next section); the common stuff for all setup is in the next subsection.

### Talon SRX Configuration
The deal here is that some of the configuration can be changed programmatically, but all of it can be burned into
the controller through configuration. What that means is that how limit switches are setup, how encoders are setup,
etc. will just be correct when the robot powers up, and configuration programming is then not required. These are
the high level details. Low level details are in the subsystem notes. Documentation seems a bit mixed on that and
says ultimately it should all be done in software.

These are the documents we have found to be most useful relative to controlling the Talon SRX
  * [Talon SRX - User’s Guide](http://www.ctr-electronics.com/Talon%20SRX%20User's%20Guide.pdf) - specifically:
    * page 17 - 1.4.1 Data connector pin description.
    * page 18 - 1.4.2 Analog sensor connection.
    * page 20 - 1.4.4 Limit switch connections.
  * [Phoenix Documentation](https://phoenix-documentation.readthedocs.io/en/latest/index.html)
    * [BLOG: FRC 2019 Week 4](https://phoenix-documentation.readthedocs.io/en/latest/blog/blog-week4.html) - Updates
      for Phoenix Tuner and Talon SRX firmware released. WE NEED TO UPDATE THESE ON OUR ROBOT (did that).
    * [Prepare Robot Controller](https://phoenix-documentation.readthedocs.io/en/latest/ch06_PrepRobot.html) - 
      describes installing and using software that lets you setup/tune Talon SRX motor controllers.
    * [Bring Up: Talon SRX / Victor SPX](https://phoenix-documentation.readthedocs.io/en/latest/ch13_MC.html) -
      basically getting Talon SRX controllers configured on the CAN bus.
    * [Bring Up: Talon SRX Sensors](https://phoenix-documentation.readthedocs.io/en/latest/ch14_MCSensor.html) -
      describes how to setup/configure the Talon SRX motor controller with different sensors (like potentiometers,
      limit switches, and encoders).
    * [Motor Controller Closed Loop](https://phoenix-documentation.readthedocs.io/en/latest/ch16_ClosedLoop.html) -
      A good description of the various closed loop (PID) control modes available in the Talon SRX.
  * [Talon SRX Software Reference Manual](https://www.ctr-electronics.com/downloads/pdf/Talon%20SRX%20Software%20Reference%20Manual-1.pdf) -
    This seems a bit outdated. Only look here if you can't find what you need in the Phoenix docs.
