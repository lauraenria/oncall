package controllers

import models.{Date, User}
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.mvc.{EssentialAction, Result}
import play.api.test._
import play.api.test.Helpers._

import java.util.Calendar
import scala.concurrent.Future

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class DateControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "DateController GET" should {

    val laura = User("Laura Enria")


    val controller = new DateController(stubControllerComponents())

    "if we select to yesterday we get that day" in {
      val yesterday = Date("18-2-2021")
      val home = controller.info(yesterday.date).apply(FakeRequest(GET, s"/date/info/${yesterday.date}"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/plain")
      contentAsString(home) must include(s"${laura.name}, ${yesterday.date}")
    }

    "If don't pass a specific date (empty) - return today" in {
      val today = Date("19-2-2021")

      val requestNoParameter: Future[Result] = controller.info(date = "").apply(FakeRequest(GET, s"/date/info/"))
      status(requestNoParameter) mustBe OK
      contentType(requestNoParameter) mustBe Some("text/plain")
      contentAsString(requestNoParameter) must include(s"${laura.name}, ${today.date}")

    }

    "if we select to tomorrow we get that day" in {
      val tomorrow = Date("20-2-2021")
      val home = controller.info(tomorrow.date).apply(FakeRequest(GET, s"/date/info/${tomorrow.date}"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/plain")
      contentAsString(home) must include(s"${laura.name}, ${tomorrow.date}")
    }

    "If we pass a specific date - get that date" in {

    }

    "If we pass a date range - get those dates" in {

    }



    //    "render the index page from the application" in {
    //      val controller = inject[DateController]
    //      val home = controller.index().apply(FakeRequest(GET, "/"))
    //
    //      status(home) mustBe OK
    //      contentType(home) mustBe Some("text/html")
    //      contentAsString(home) must include("Welcome to Play")
    //    }
    //
    //    "render the index page from the router" in {
    //      val request = FakeRequest(GET, "/")
    //      val home = route(app, request).get
    //
    //      status(home) mustBe OK
    //      contentType(home) mustBe Some("text/html")
    //      contentAsString(home) must include("Welcome to Play")
    //    }
  }
}
