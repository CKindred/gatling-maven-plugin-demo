import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicMothSimulation extends Simulation {

  val httpProtocol = http
    // Here is the root for all relative URLs
    .baseUrl("host.docker.internal:3007/")
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
