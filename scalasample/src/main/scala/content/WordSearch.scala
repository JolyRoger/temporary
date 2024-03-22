package content

import scala.collection.mutable

object WordSearch extends App {

  var rowNum = -1
  var colNum = -1

  @inline def toIndex(rowCol: (Int, Int)) = rowCol._1 * colNum + rowCol._2
  @inline def toCoord(index: Int) = (index / colNum, index % colNum)
  def rawNeighbours(index: Int): Set[Int] = {
    val (row, col) =  toCoord(index)
    Set((row + 1, col), (row, col + 1), (row - 1, col), (row, col - 1)).
      withFilter(rowCol => {
        rowCol._1 < rowNum &&
        rowCol._2 < colNum &&
        rowCol._1 >= 0 &&
        rowCol._2 >= 0
      }).map(toIndex)
  }

  def findWord(board: Array[Array[Char]], word: String) = {
    var stack = List.empty[Int]

    stack = word.head :: stack
    var rest = word.tail

    while (stack.nonEmpty) {
      val element = stack.head
      stack = stack.tail
      val neighs = rawNeighbours(element).map(toCoord).filter(sym => board(sym._1)(sym._2) == rest.head)
      stack = neighs.map(toIndex).toList ::: stack
    }
  }

  case class Node(v: Char, neighs: /*mutable.Map[Char, Node] = mutable.Map.empty, */mutable.Set[Int] = mutable.Set.empty[Int])

  def preprocess(board: Array[Array[Char]], words: Array[String]) {
    rowNum = board.length
    colNum = board(0).length

    var arr = Array.ofDim[Node](rowNum * colNum)

    for (row <- 0 until rowNum; col <- 0 until colNum) {
      val index = toIndex(row, col)
      arr(index) = Node(board(row)(col))
    }
    arr.indices.foreach(index => {
      rawNeighbours(index)
    })
  }

  def add(sym: Int, board: Array[Array[Char]], words: Array[String]) {
    var stack = List((sym, Set.empty[Int]))
    val root = Node(sym)

    while (stack.nonEmpty) {
      val element = stack.head
      stack = stack.tail

      val elemNeighs = rawNeighbours(element._1).intersect(element._2)
      val stackAdd = elemNeighs.map(newElem => (newElem, element._2 + element._1 ))
      val stackAddList = stackAdd.toList
      stack = stackAddList ::: stack
    }

  }

  def findWords(board: Array[Array[Char]], words: Array[String]): List[String] = {
    rowNum = board.length
    colNum = board(0).length

    val res = for (row <- 0 until rowNum; col <- 0 until colNum) yield {
      (board(row)(col), toIndex(row, col))
    }
    val symMap = res.groupMap(_._1)(_._2)
//    val symMap = res.toMap()

    val neighs = symMap('a').flatMap(s => rawNeighbours(s)).map(toCoord)
    val neighs2 = symMap('a').flatMap(s => rawNeighbours(s))
    println(s"$symMap")
    println("----")
    println(s"$neighs")
    println("----")
    println(s"$neighs2")
    List()
  }

  val board = Array(Array('o','a','a','n'), Array('e','t','a','e'), Array('i','h','k','r'), Array('i','f','l','v'))
  val words = Array("oath","pea","eat","rain")
  //  board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
  //  words = ["oath","pea","eat","rain"]

  findWords(board, words)
}
