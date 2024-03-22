package content

import com.typesafe.scalalogging.Logger
import org.scalatest.flatspec.AnyFlatSpec

import scala.io.Source

class SolutionTest extends AnyFlatSpec {

  val log = Logger(getClass.getName)

  "The Solution" should "perform simple search" in {
    val testData1 = Array("cat","dog","catdog")
    log.debug(s"Test data: $testData1")
    assertResult(List("catdog")) {
      Solution.findAllConcatenatedWordsInADict(testData1)
    }
    log.debug(s"Result found")
  }

  "The Solution" should "search among big similar words" in {
    val filename = "/home/torquemada/Softdev/test-failed-case.txt"
    val bufferedSource = Source.fromFile(filename)
    val data = bufferedSource.getLines.toArray
    log.debug(s"Test data size: ${data.length}")
    assertResult(721) {
      val result = Solution.findAllConcatenatedWordsInADict(data)
      result.length
    }
    log.debug(s"Result found!")
  }
}
