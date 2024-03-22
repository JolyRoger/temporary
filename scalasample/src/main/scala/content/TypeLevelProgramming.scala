package content

import javax.swing.JFrame
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

object TypeLevelProgramming extends App {

  import scala.reflect.runtime.universe._

  def show[T](value: T)(implicit tag: TypeTag[T]) = tag.toString()

  println(show((1, "Hello")))

  class Account
  abstract class SavingsAccount extends Account with Logger {

    val maxLength = 20
    val balance = 22
    def withdraw(amount: Double) {
      if (amount > balance) log("Insufficient funds")
      else log("Done")
    }
  }

  trait SampleTrait

  trait Logger {
    def log(msg: String) // An abstract method
  }

  trait ConsoleLogger extends Logger {
    def log(msg: String) {
      println(msg)
    }
  }

  trait TimestampLogger extends ConsoleLogger {
    override def log(msg: String) {
      super.log(s"${java.time.Instant.now()} $msg")
    }
  }

  trait ShortLogger extends ConsoleLogger {
    val maxLength: Int
    override def log(msg: String) {
      super.log(
        if (msg.length <= maxLength) msg else s"${msg.substring(0, 12)}...")
    }
  }

  trait DeathLogger extends ConsoleLogger {
    this: Account =>
    override def log(msg: String) {
      super[ConsoleLogger].log("D" + msg)
    }
  }

  val acct1 = new SavingsAccount with TimestampLogger with ShortLogger with DeathLogger
  val acct2 = new SavingsAccount with DeathLogger with ShortLogger with TimestampLogger

//  acct1.withdraw(25)
//  acct2.withdraw(25)

  def f1 = Future {
    Console.err.println(s"f1 starts")
    Thread.sleep(5000)
    Console.err.println(s"f1 works")
    42
  }

  def f2 = Future {
    Console.err.println(s"f2 starts")
    Thread.sleep(1000)
    Console.err.println(s"f2 works")
    "ABC"
  }

  val res = for {
    res1 <- f1
    res2 <- f2
  } yield res1 + res2

  res.onComplete {
    case Success(value) => println(s"This is ${value}")
    case Failure(_) => Console.err.println(s"Failer!")
  }

  Await.result(res, 10.seconds)
}