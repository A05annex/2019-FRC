# Robot Arm Details
The robot arm is a 2 part arm. In an ideal world, we would map the desired capture/deliver
positions for the arm into buttons on the joystick.

## Immediate Tasks and References
The first prototype of the arm has been constructed. These are the immediate tasks:
* potentiometer indexing - the arms have a travel range significantly less than
  360&deg;. Make sure the shaft coupling is indexed so the entire range of motion
  falls withing a rotation and does not cross the 0:max boundary.
* find the limits - Once the potentiometers are indexed, move the arms to the
  physical limits of motion and record those limits.
* run the motor and set (or map) directions so positive motion raises the arms.
* wire limit switches to the talon controllers and verify they work as expected.

These are the details:
* The arm is a 2-part arm supporting a collector/ejector head. We don't have the
  physical collector/ejector head yet. We need a guess on the geometry of the collector/ejector head.
* Each arm is powered by a [CIM motor](https://www.andymark.com/products/2-5-in-cim-motor),
  free speed 5310rpm. Each motor has a ??:? gearbox to a chain drive with a ?? gear ratio -
  for a combined ratio of ??:??, or free speed at the arm pivot of ??rpm or ??rps.
* Each motor is controlled by a Cross the Road Electronics (CTR) Talon SRX motor
  controller. This controller includes limit switch and encoder/potentiometer inputs
  to on-board PID and limit switch capability. These documents describe the controller
  capabilities along with calbration/use:
  * [TALON SRX - User’s Guide](http://www.ctr-electronics.com/Talon%20SRX%20User's%20Guide.pdf) - specifically:
    * page 17 - 1.4.1 Data connector pin description.
    * page 18 - 1.4.2 Analog sensor connection.
    * page 20 - 1.4.4 Limit switch connections.
  * [TALON SRX Software Reference Manual](https://www.ctr-electronics.com/downloads/pdf/Talon%20SRX%20Software%20Reference%20Manual-1.pdf) -
    specifically:
    * page 52 - 7.5.2. Analog Potentiometer / Analog Encoder - describing potentiometer
      control.
  * [Prepare Robot Controller](https://phoenix-documentation.readthedocs.io/en/latest/ch06_PrepRobot.html)
    - describes installing and usning software that lets you 
  * [Bring Up: Talon SRX Sensors](https://phoenix-documentation.readthedocs.io/en/latest/ch14_MCSensor.html) - describes the

Required tools - these need to be installed and hooked to the roborio

## Requirements
* The robot arm needs to be positioned to pickup hatches at a center height of 1'7" and deliver hatches:
  * at 1'7" for the cargo ship;
  * at 1'7", 3'11", and 6'3" for the rocket.
* The robot arm need to be positioned to pickup balls (cargo) either from the floor or from the dispatch
at a height of 3'7-1/2" and deliver cargo at:
  * at 2'3-1/2" for the cargo ship;
  * at 2'3-1/2",4'7-1/2", and 6'11-1/2" for the rocket.
* The robot arm needs to park at some stable 'home' position for driving.
* The robot arm needs to work in conjunction with the pneumatic lifts to liift and park the robot on the top platform at endgame (height 1'7").

## Physical Implementation

## Robot Arm Subsystem