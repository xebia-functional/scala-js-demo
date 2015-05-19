package demo.webapp

import org.scalajs.jquery._
import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenMap
import scala.scalajs.js.JSApp

object DemoApp extends JSApp {

  def main(): Unit = {
    jQuery.ajaxSetup(Map("cache" -> false))
    steps.foreach(s => drawStepButton(s))
    jQuery("#learn-more").click { (event: JQueryEventObject) => selectStep(steps.head) }
  }

  val steps = Seq(
    Step(1, "step1", "one", "step01.html"),
    Step(2, "step2", "one", "step02.html"),
    Step(3, "step3", "one", "step03.html"),
    Step(4, "step4", "double", "step04.html", Option("step04_exp.html")),
    Step(5, "step5", "double", "step05.html", Option("step05_exp.html")),
    Step(6, "step6", "double", "step06.html", Option("step06_exp.html")),
    Step(7, "step7", "one", "step07.html"))

  case class Step(
      id: Int,
      name: String,
      layout: String,
      content_left: String,
      content_right: Option[String] = None)

  def drawStepButton(s: Step): Unit = {

    val button = jQuery("<div></div>")
        .attr(Map("type" -> "button", "class" -> "btn btn-circle").toJSDictionary)
        .addClass(s.name)
        .text(s.id.toString)
        .click { (event: JQueryEventObject) => selectStep(s) }

    val item = jQuery("<div></div>")
        .attr("class", "stepwizard-step")
        .append(button)

    jQuery("#stepwizardWrapper").append(item)
  }

  def selectStep(step: Step): Unit = {
    markSelected(step.name)
    setLayout(step.layout)
    fillLeft(step.content_left)
    step.content_right match {
      case Some(url) => fillRight(url)
      case _ => Unit
    }
  }

  def markSelected(name: String): Unit = {
    jQuery(".stepwizard-step>.btn").removeClass("active")
    jQuery(".stepwizard-step>." + name).addClass("active")
  }

  def setLayout(layOut: String): Unit = jQuery("body").removeClass().addClass(layOut)

  def fillLeft(path: String): Unit = {
    jQuery("#left_wrapper").fadeOut(500, "linear", callback = { (data: js.Any) =>
      jQuery.get(url = s"subpages/$path", success = { (data: js.Any) =>
        jQuery("#left_wrapper").html(data.toString).fadeIn(500)
      })
    })
  }

  def fillRight(path: String): Unit = {
    jQuery("#right_wrapper").fadeOut(100, "linear", callback = { (data: js.Any) =>
      jQuery("#right_wrapper").attr("src", "subpages/" + path).fadeIn(900)
    })
  }


}