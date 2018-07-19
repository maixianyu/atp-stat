package AtpCsv
import scala.collection.mutable.Map
import scala.io.Source

/*********************************/
//********** score ***************/
//tourney_year_id,tourney_order,tourney_slug,tourney_url_suffix,tourney_round_name,
//round_order,match_order,winner_name,winner_player_id,winner_slug,loser_name,
//loser_player_id,loser_slug,winner_seed,loser_seed,match_score_tiebreaks,
//winner_sets_won,loser_sets_won,winner_games_won,loser_games_won,winner_tiebreaks_won,
//loser_tiebreaks_won,match_id,match_stats_url_suffix
/*********************************/
class ItemName{
  private val map: Map[String, Int] = Map()
  private var name = ""
  
  def init(filename: String) = {
    for ((line, i) <- Source.fromFile(filename).getLines().zipWithIndex){
      map += (line.toString() -> i)
      //print(line.toString() + ',')
    }
  }
  
  def setName(newName: String) = { name = newName }
  
  def getIdx: Int = {
    if (map.get(name) == None) println("itemname does exists.") 
    map.get(name).getOrElse(0) 
  }
  
  def getAmount: Int = map.size
}

class AtpCsv(val contentList: List[List[String]]){
  def +(that: AtpCsv): AtpCsv = {
    new AtpCsv(this.contentList ::: that.contentList)
  }
  
  def grep(itemname: ItemName, pattern: String, f: (List[String], Int, String) => Boolean): AtpCsv = {
    val idx = itemname.getIdx
    new AtpCsv(for {row <- contentList; if f(row, idx, pattern)} yield row)
  }
  
  def getAmount: Int = contentList.length
  
  def groupAmount(itemName: ItemName): List[(String, Int)] = {
    for {ele <- contentList.groupBy(_(itemName.getIdx)).toList} yield (ele._1, ele._2.length)
  }
  
}
   
