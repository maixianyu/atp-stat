import com.github.tototoshi.csv._
import java.io._
import AtpCsv._
import scala._

object Main {
  def main(args: Array[String]) {
    print("Hello.")
    
    val itemScore = new ItemName
    itemScore.init("match_scores_column_titles.txt")
    
    val atpcsv_0 = new AtpCsv(CSVReader.open(new File("match_scores_2018-2018.csv")).all())
    println(atpcsv_0.getAmount)
    val atpcsv_1 = new AtpCsv(CSVReader.open(new File("match_scores_2018-2018.csv")).all())
    println(atpcsv_1.getAmount)
    println((atpcsv_1 + atpcsv_0).getAmount)
    
    
    val grepFunc = (row: List[String], idx: Int, pattern: String) => {if (row(idx) == pattern) true else false}
    itemScore.setName("winner_name")
    val pattern = "Roger Federer"
    
    val atpcsv_player = atpcsv_0.grep(itemScore, pattern, grepFunc)
    //println(atpcsv_player.contentList)
    println(atpcsv_player.getAmount)
    
    itemScore.setName("tourney_slug")
    println(atpcsv_player.groupAmount(itemScore))
  }
}