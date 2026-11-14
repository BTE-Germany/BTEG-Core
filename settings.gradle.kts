rootProject.name = "BTEG-Core"
include(":core-paper")
include(":core-velocity")
include(":core-common")
project(":core-paper").projectDir = file("Paper")
project(":core-velocity").projectDir = file("Velocity")
project(":core-common").projectDir = file("Common")