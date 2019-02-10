# 6831 - AO5 Annex - FRC 2019

Entirely new robot. Big electronics and control system change is that we are using
[TALON SRX motor controllers](#TALON-SRX-Motor-Controllers) and wiring
everything together using the CAN bus instead of PWM (see
[PWM and CAN](https://alex-spataru.gitbooks.io/frc-robot-programming/content/Book/Chapters/1.3.html) for what that
means). Big software change is that we are using the
[WPILib](https://wpilib.screenstepslive.com/s/currentCS/m/java/l/272787-frc-java-wpilib-api-documentation) framework
for programming the robot.

## Subsystems
These are our subsystems and programmer responsibilities:
* **Drive Train**: (Eva)
  * **methods**:
    * **arcade drive** - forward, rotation power
    * **tank drive** - right, left power
    * **shifting** - *figure this out*: manual (shift up, shift down), or automatic - it just happens.
  * **commands**:
    * **default command** - joystick drive, arcade mode. Work with the driver to get the best driver control operation.
    * **depot to rocket** -
    * **rocket to depot** -
    * **pickup cargo from floor** -
* **Arm**: (Jason) See [Arm Details](./ARM.md)
  * **methods**:
    * **power lower arm**
    * **power upper arm**
    * **get lower arm position**
    * **get upper arm position**
    * **move arm on path** - input is a list of (lower,upper) position values.
    * **move to position** - from positions HOME, LOW_HATCH, LOW_CARGO, MID_HATCH, MID_CARGO, HIGH_HATCH, HIGH_CARGO,
      PICKUP_FROM_FLOOR.
    * **move arm to** - inputs lower and upper positions
  * **commands**:
    * **default test command** - drive the arms, lower and upper, by buttons on the or using the joystick. Adjusting
      PID loop coefficients with the joystick (we could have 2 joysticks, right?, one just for the arm?)
    * **default competition command** - positioning buttons for HOME (the start position of the arm), and
      LOW_HATCH, LOW_CARGO, MID_HATCH, MID_CARGO, HIGH_HATCH, HIGH_CARGO, PICKUP_FROM_FLOOR.
* **Bucket**: (Allison)
  * **methods**
  * **commands**
    * **default command**
* **Pneumatics - compressor and tanks initialized**: seems like this is just part of the robot being on, and should
  not be a subsystem
* **Pneumatics-shift**: (Eva) seems like this is really part of the drive train subsystem.
* **Pneumatics-lift**: (??) this is it's own subsystem ued only by the lift command.
  * **methods**
    * **extend**
    * **retract**
  * **commands**
    * **default test command** - attach extend and retract to joystick buttons for testing.
    * **default command** - make sure the lifters don't drop - hit them every 30 seconds with full retract.
* **Vision**: (theo)

## Commands
Most :
* **Driver Control**: We need the ability to driver control most everything, though ultimately we want most everything
  to be autonomous to some degree. Full driver control is that each subsystem has a default command that lets the
  driver control it.
* **Autonomous Pickup - Depot to/from Rocket**:
  * Autonomously jump off the start platform and attach a hatch cover to the rocket.
  * Autonomously return and pickup a hatch cover from the depot.
  * Autonomously go from the depot to the rocket and attach the hatch cover.
* **Arm Positioning**:
* **Bucket Operation**:
* **Endgame Lift**: (??)
  * **Requires** - Drive, Arm, Pneumatics-lift

## TALON SRX Motor Controllers
To find out everything about these, start at
[TALON SRX](http://www.ctr-electronics.com/control-system/motor-control/talon-srx.html) - all the important
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

### TALON SRX Configuration
The deal here is that some of the configuration can be changed programmatically, but all of it can be burned into
the controller through configuration. What that means is that how limit switches are setup, how encoders are setup,
etc. will just be correct when the robot powers up, and configuration programming is then not required. These are
the high level details. Low level details are in the subsystem notes.
