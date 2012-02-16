package foo

import java.io.File
import scala.io.Source



// http://codekata.pragprog.com/2007/01/kata_fourteen_t.html

object Program {
  
  def main(args : Array[String]) {
    val str = """it was in the wind that was what he thought was his companion. I think would be a good one and accordingly the ship their situation improved. Slowly so slowly that it beat the band! YouÕd think no one was a low voice. "DonÕt take any of the elements and the inventors of the little Frenchman in the enclosed car or cabin completely fitted up in front of the gas in the house and wringing her hands. "IÕm sure theyÕll fall!"
She looked up at them. He dug a mass of black vapor which it had refused to accept any. As for Mr. Swift as if it goes too high IÕll warn you and you can and swallow frequently. That will make the airship was shooting upward again and just before the raid wouldnÕt have been instrumental in capturing the scoundrels right out of jail."""
    
    def parse(str : String) = {//: Map[(String, String), Set[String]] = {
      val words = str.toLowerCase.split(" ")
      val triples = words.zip(words.tail).zip(words.tail.tail)//.map(t => (t._1._1, t._1._2, t._2))
      val hopefullyMap = triples.groupBy(_._1).map(s => (s._1, s._2.map(_._2).toSet))
      
      //println(hopefullyMap.mkString(", "))
      hopefullyMap
    }
    
    def getFile = Source.fromFile("src/pg97.txt").getLines.mkString(" ")
    
    def whatcomesnext(firstTwoWords : (String,String) , response : Map[(String, String), Set[String]]) = {
      val r = new scala.util.Random
      val s = response(firstTwoWords)
      val i = r.nextInt(s.size)
      s.toList(i)
      
      
      
      
    }
    
    def generate(sentence : List[String], response : Map[(String, String), Set[String]], n : Int) : List[String] = {
      //val sb = new StringBuilder
      if (n == 0) {
        sentence
      }
      else {
        sentence match {
          case x :: y :: tail => {
            val firstTwoWords = (y, x)
            val next = whatcomesnext(firstTwoWords, response)
            val nextTwo = (firstTwoWords._2, next)
            generate(next :: sentence, response, n - 1)
          }
        }
      
      }
    }
    
    def doIt(str : String, firstTwoWords : (String, String), n : Int) : String = {
    generate(List(firstTwoWords._2, firstTwoWords._1), parse(str), n).reverse.mkString(" ")
    }
    
    
    println(doIt(getFile, ("the", "line"), 100))
    
    //parse(str)
    
    
    ()
    //println(43)
  }
}