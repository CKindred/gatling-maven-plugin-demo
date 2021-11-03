import scala.concurrent.duration._
import java.util.Properties
import scala.io.Source

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicMothSimulation extends Simulation {

  val url = getClass.getResource("application.properties")
  val properties: Properties = new Properties()

  if (url != null) {
    val source = Source.fromURL(url)
    properties.load(source.bufferedReader())
  }
  else {
    println("properties file cannot be loaded")
  }

  val isJenkins = properties.getProperty("jenkins")
  val host =  if(isJenkins == "true") "http://host.docker.internal:3007/" else "http://127.0.0.1:3007/"
  
  val httpProtocol = http
    // Here is the root for all relative URLs
    .baseUrl(host)
    // Here are the common headers
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  // A scenario is a chain of requests and pauses
  val scn = scenario("Benchmark_1")
    .exec(
      http("request_home")
        .get("/")
    )
    .pause(5)
    .exec(
        http("request_vehicle")
        .get("/?registrationNo=FNZ6110")
    )
  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
