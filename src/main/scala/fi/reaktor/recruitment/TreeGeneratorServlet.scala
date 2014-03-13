package fi.reaktor.recruitment

import org.scalatra._
import scalate.ScalateSupport

class TreeGeneratorServlet extends ScalatraServlet with ScalateSupport {

  get("/") {
    val seed = params.get("seed").map(_.toInt).getOrElse(scala.util.Random.nextInt())
    
    response.setHeader("Content-Disposition", "attachment; filename=tree.txt")

    "# seed: " + seed + "\n" + generate(seed)
  }

  get("/solve") {
    val isCorrect = for {
        seed <- params.get("seed").map(_.toInt)
        solution <- params.get("solution").map(_.toInt)
    } yield check(seed,solution)

    isCorrect.map(b => if (b) {
        "correct"
    } else {
        "wrong"
    }).getOrElse("need 'seed' and 'solution' parameters")
  }

  def generate(seed:Int, rows:Int = 100, max:Int = 100) = {
    val rnd = new scala.util.Random(seed)
    new Range(1, rows + 1, 1).map(to => new Range(0, to, 1).map(_ => rnd.nextInt(max)).mkString(" ")).mkString("\n")
  }

  def check(seed:Int, solution:Int):Boolean =
    generate(seed)
        .split("\n")
        .toList
        .reverse
        .map(_.split(" ").map(_.toInt).toList)
        .foldLeft(List[Int]()) { (memo:List[Int], list:List[Int]) =>
            list
                .zip(memo.sliding(2).map(_.max).toList.padTo(list.size, 0))
                .map((it:(Int, Int)) => it._1 + it._2)
        }
        .head == solution
}
