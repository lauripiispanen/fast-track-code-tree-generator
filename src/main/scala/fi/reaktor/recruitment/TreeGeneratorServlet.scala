package fi.reaktor.recruitment

import org.scalatra._
import scalate.ScalateSupport

class TreeGeneratorServlet extends ScalatraServlet with ScalateSupport {

  get("/") {
    val seed = params.get("seed").map(_.toInt).getOrElse(scala.util.Random.nextInt())
    val rnd = new scala.util.Random(seed)
    val rows = 100
    val max = 100    
    val treeStr = new Range(1, rows + 1, 1).map(to => new Range(0, to, 1).map(_ => rnd.nextInt(max)).mkString(" ")).mkString("\n")
    
    response.setHeader("Content-Disposition", "attachment; filename=tree.txt")

    "# seed: " + seed + "\n" + treeStr
  }
}
