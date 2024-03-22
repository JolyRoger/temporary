package content

import scala.annotation.tailrec
import scala.language.implicitConversions

class Trie {

  case class Node(v: Char, var left: Node, var mid: Node, var right: Node, var word: Boolean = false)

  var root: Node = null
  implicit def exit(node: Node): Boolean = false

  @tailrec
  private def recursiveSearch(word: String, node: Node)(implicit exit: Node => Boolean): Boolean = {
    if (node == null) return false
    if (word.head == node.v) if (word.tail.isEmpty) exit(node) else recursiveSearch(word.tail, node.mid)
    else if (word.head < node.v) recursiveSearch(word, node.left)
    else recursiveSearch(word, node.right)
  }

  def insert(word: String): Unit = {
    def insertRec(word: String, node: Node): Node = {
      if (word.isEmpty) return node
      var newNode = node
      if (newNode == null) newNode = Node(word.head, null, null, null)
      if (newNode.v < word.head) newNode.right = insertRec(word, newNode.right)
      else if (newNode.v > word.head) newNode.left = insertRec(word, newNode.left)
      else if (word.length > 1) newNode.mid = insertRec(word.tail, newNode.mid)
      else newNode.word = true
      newNode
    }
    root = insertRec(word, root)
  }

  def search(word: String): Boolean = {
    recursiveSearch(word, root)(_.word)
  }

  def startsWith(prefix: String): Boolean = {
    recursiveSearch(prefix, root)(_ => true)
  }
}

object Run extends App {
  val trie = new Trie()
  val td = Array("the", "shit", "tank")
//  td.combinations(2)
//  val td = Array("","apple", "apple","app","app","app","app")

  td.foreach(str => trie.insert(str))

  val word = "th"
  val exist = trie.search(word)
  val starts = trie.startsWith(word)

  println(s"\"$word\" exist: $exist")
  println(s"\"$word\" starts with: $starts")

//  println(s"${trie.root}")
}
