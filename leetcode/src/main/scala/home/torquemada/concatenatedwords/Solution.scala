package home.torquemada.concatenatedwords

import com.typesafe.scalalogging.Logger
import scala.io.Source
import scala.language.postfixOps

object Solution {

  case class TSTNode(var data: Char, var isEnd: Boolean = false, var left: Option[TSTNode] = None,
                      var middle: Option[TSTNode] = None, var right: Option[TSTNode] = None)

  private object TST {
    var root: Option[TSTNode] = None

    def clear = root = None

    def insert(word: String) = {
      if (word.nonEmpty) {
        if (root.isEmpty) {
          root = Some(TSTNode(word.charAt(0)))
        }

        var prev: Option[TSTNode] = None
        var current = root
        var i = 0
        while (i < word.length) {
          val data = word.charAt(i)
          val node = current.getOrElse(TSTNode(data))
          if (data < node.data) {
            if (node.left.isEmpty) node.left = Some(TSTNode(data))
            current = node.left
          } else if (data > node.data) {
            if (node.right.isEmpty) node.right = Some(TSTNode(data))
            current = node.right
          } else {
            if (i == word.length - 1) {
              node.isEnd = true
            }
            prev.foreach(node => if (node.middle.isEmpty) node.middle = Some(TSTNode(data, word.length - 1 == i)))
            if (current.nonEmpty) {
              prev = current
            } else {
              prev = prev.get.middle
            }
            current = node.middle
            i += 1
          }
        }
      }
    }

    def search(word: String): Boolean = {
      if (root.isEmpty || word.isEmpty) return false

      var current = root
      var i = 0
      while (i < word.length && current.isDefined) {
        val data = word.charAt(i)
        val node = current.get
        if (data < node.data) {
          current = node.left
        } else if (data > node.data) {
          current = node.right
        } else {
          if (i == word.length - 1) {
            return node.isEnd
          }
          current = node.middle
          i += 1
        }
      }
      false
    }
  }







  private def rest(word: String) = {
    var currentNodeOpt = TST.root
    var stack = List.empty[String]
    var symCount = 0

    while (symCount < word.length && currentNodeOpt.nonEmpty) {
      val currentNode = currentNodeOpt.get
      val sym = word(symCount)
      currentNodeOpt = if (sym == currentNode.data) {
        symCount = symCount + 1
        if (currentNode.isEnd) {
          val restWord = word.drop(symCount)
          stack = if (restWord.isEmpty) stack else restWord :: stack
        }
        currentNode.middle
      } else if (sym < currentNode.data) currentNode.left else currentNode.right
    }
    stack
  }

  private def checkWord(word: String, secondary: Boolean): Either[Boolean, List[String]] = {
    if (secondary && TST.search(word)) Left(true) else {
      val words = rest(word)
//      println(s"REST: \t$word:\t$words")
      if (words.isEmpty) Left(false) else Right(words)
    }
  }

  private def checkWord(word: String): Boolean = {
    var found = false
    var secondary = false
    var stack = List(word)

    while (stack.nonEmpty) {
      stack = checkWord(stack.head, secondary) match {
        case Left(res) => if (res) {
          found = true
          List.empty
        } else stack.tail
        case Right(words) => words ::: stack.tail
      }
      secondary = true
    }
    found
  }

  def findAllConcatenatedWordsInADict(words: Array[String]): List[String] = {
    TST.clear
    words.foreach(TST.insert)
    words.filter(checkWord).toList
  }

}
