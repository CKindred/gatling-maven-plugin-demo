gatling-maven-plugin-demo
=========================


Create an `application.properties` file inside `src/test/resources` with the text `jenkins=false` for running locally or `jenkins=true` for running on Jenkins.

Simple proof of concept showcasing the use of Gatling to benchmark the MOT history service.

To test it out, simply execute the following command:

    $mvn gatling:test -Dgatling.simulationClass=BasicMothSimulation

or simply:

    $mvn gatling:test
