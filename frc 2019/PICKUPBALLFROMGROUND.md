# Pick Up Ball From Ground
## Sequence of events:
* Driver moves to position
* Driver angle to ball
  * Do we want driver control for this?
* Trigger command to get ball from ground
  * Set arm target angle to PICKUP_FROM_FLOOR
  * Make bucket wheels spin to pickup
    * May need specific command that makes wheels run for desired ammount of time
    * I don't know if there's another way to know we have intaken the ball
  * Make arm go to HOME (or whatever position is the one we use for moving around)