package code.snippet

import scala.xml._
import net.liftweb.builtin.snippet.Form

class formAjax {

  def render(elements: NodeSeq) : NodeSeq = {
    val ajaxForm = Form.render(elements)
    val ajaxFormWithOriginalAttrs = copyAttributes(elements, ajaxForm,
      attrName => (attrName != "id" && attrName != "onsubmit" && attrName != "action" && attrName != "form"))
    ajaxFormWithOriginalAttrs
  }

  protected def copyAttributes(source: NodeSeq, target: NodeSeq, predicate: String => Boolean) : NodeSeq = {
  	
    val combinedAttrs = for {
      s <- source(0) if source.length > 0
      t <- target(0) if target.length > 0
      sAttr <- s.attributes if predicate(sAttr.key)
    } yield {
      val sAttrMetadata = new UnprefixedAttribute(sAttr.key, sAttr.value, Null)
      t.attributes.asAttrMap.foldLeft[MetaData](sAttrMetadata) {
        case (md, (name, value)) => new UnprefixedAttribute(name, value, md)
      }
    }

    if (target.length > 0 && combinedAttrs.length > 0) {
      Elem(null, target(0).label, combinedAttrs(0), TopScope, target(0).isEmpty, target(0).child : _*)
    } else {
      target
    }

  }

}