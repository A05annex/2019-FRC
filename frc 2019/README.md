# 6831 - AO5 Annex - FRC 2019

Entirely new robot. Big electronics and control system change is that we are using TALON SRX controllers and wiring
everything together using the CAN bus instead of PWM (see
[PWM and CAN](https://alex-spataru.gitbooks.io/frc-robot-programming/content/Book/Chapters/1.3.html) for what that
means). Big software change is that we are using the
[WPILib](https://wpilib.screenstepslive.com/s/currentCS/m/java/l/272787-frc-java-wpilib-api-documentation) framework
for programming the robot.

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
etc. will just be correct when the robot powers up, and configuration programming is then not required.

## Subsystems
These are our subsystems and programmer responsibilities:
* **Drive Train**: (Eva)
  * **default command**
* **Arm**: (Jason)
  * **default command**
* **Bucket**: (Allison)
  * **default command**
* **Pneumatics**: (??)
  * **default command**
* **Vision**: (theo)

## Commands
These are the commands we are building and why:
* **Driver Control**: We need the ability to driver control most everything, though ultimately we want most everything
  to be autonomous to some degree.
* **Autonomous Pickup to/from Rocket**:
  * Autonomously jump off the start platform and attach a hatch cover to the rocket.
  * Autonomously return and pickup a hatch cover from the depot.
  * Autonomously go from the depot to the rocket and attach the hatch cover.
* **Arm Positioning**:
* **Bucket Operation**:
* **Endgame Lift**:


