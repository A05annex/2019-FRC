# End Game Lift
## Sequence of events:
* Driver positions robot for lift (back up to platform)
* Driver triggers the get on platform command
  * Set the arm target to PRE_ENDGAME_LIFT
     * How do we know we are at position?
     * Timer
     * Potentiometers report back that we are at position
     
  * Activate pneumatics for lift and set the arm target to DURING_LIFT
  * Set arm target to ENDGAME_LIFT
  * Retract lift pneumatics and set arm target to ENDGAME_LAND
  * Activate wheels to propell robot onto platform and set arm target to ENDGAME_PARK
    * May need command for wheels to go bakcwards for a set ammount of time
    * Woo I made that command. If that doesn't work can change. 
  * Set arm target to POST_ENDGAME_PARK