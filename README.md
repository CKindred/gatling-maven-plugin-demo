gatling-maven-plugin-demo
=========================

Simple proof of concept showcasing the use of Gatling to benchmark the MOT history service.

To test it out, simply execute the following command:

    $mvn gatling:test -Dgatling.simulationClass=BasicMothSimulation

or simply:

    $mvn gatling:test
