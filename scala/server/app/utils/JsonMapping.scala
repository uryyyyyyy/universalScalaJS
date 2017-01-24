package utils

import com.github.uryyyyyyy.scalajsddd.sample.domain.ToDo
import play.api.libs.json.Json

object JsonMapping {
  implicit val residentReads = Json.reads[ToDo]
  implicit val residentWrites = Json.writes[ToDo]
}
