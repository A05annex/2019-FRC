# Attaching Hatch Panels
## Sequence of Events:
* Center to rocket
  * How will we center?
  * Physical?
  * Vision?
* Arm go to LOW_HATCH/MID_HATCH/HIGH_HATCH
  * Use SetAndWaitForArmPosition to check
* Drives forward to make sure it's velcroed to the rocket
* Move  servo
  * Probably judge if is done with timer
* Back up from rocket