package scalikejdbc.free

import scalikejdbc._


sealed abstract class Query[A] {

}


object Query {

  case class GetSeq[A](sql: SQLToList[A, HasExtractor]) extends Query[Seq[A]]
  case class GetFirst[A](sql: SQLToOption[A, HasExtractor]) extends Query[Option[A]]
  case class GetSingle[A](sql: SQLToOption[A, HasExtractor]) extends Query[Option[A]]
  case class Fold[A](sql: SQL[_, NoExtractor], init: A, f: (A, WrappedResultSet) => A) extends Query[A]

  case class Update(sql: SQLUpdate) extends Query[Int]
  case class GenerateKey(sql: SQLUpdateWithGeneratedKey) extends Query[Long]
  case class Execute(sql: SQLExecution) extends Query[Boolean]

}