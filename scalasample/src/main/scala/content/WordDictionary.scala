package content

import scala.collection.mutable

object WordDictionary extends App {

  case class Node(v: Char, neighs: mutable.Map[Char, Node] = mutable.Map.empty, var word: Boolean = false)

  var root: Node = Node('\n')

  def addWord(word: String) {
    var node = root

    word.foreach { sym =>
      node = node.neighs.getOrElseUpdate(sym, Node(sym))
    }
    node.word = true
  }

  def search(word: String) = {
    def searchRec(word: String, node: Option[Node]): Boolean =
      node.exists { n =>
        if (word.isEmpty) n.word else
        if (word.head == '.') n.neighs.keys.exists(sym => searchRec(word.tail, n.neighs.get(sym)))
        else searchRec(word.tail, n.neighs.get(word.head))
      }
    searchRec(word, Some(root))
  }


  val td = Array("the", "shit", "tank")
  td.foreach(str => addWord(str))

  val word = ".h.."
  val exist = search(word)

  println(s"Root: \"$root\"")
  println(s"\"$word\" exist: $exist")
}
