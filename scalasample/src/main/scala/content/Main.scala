package content

import java.time.LocalTime
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}

object Main extends App {
  for (n1 <- Future { println(s"print 2"); Thread.sleep(1100) ; 2 };
       n2 <- Future { println(s"print 40"); Thread.sleep(1000); 40 }) {
    println(n1 + n2)
  }
  Thread.sleep(5000)
}
