package code.snippet

import net.liftweb._
import http._
import common._
import util.Helpers._
import js._
import JsCmds._
import JE._

class SimpleTest {
	
	def render = {

		var firstName = ""
		var lastName = ""

		def process() : JsCmd = {
			S.notice("Full name: " + firstName + " " + lastName)
		}

		"#first_name" #> SHtml.text(firstName, firstName = _, "id" -> "first_name") &
		"#last_name" #> (SHtml.text(lastName, lastName = _, "id" -> "last_name") ++ SHtml.hidden(process))

	}

}