package com.techmonad.repository

import com.techmonad.connection.{DBComponent, MySqlDBComponent}
import slick.lifted.ProvenShape

import scala.concurrent.Future

trait BankRepository extends BankTable {
  this: DBComponent =>

  import driver.api._


  /**
    * @param bank
    * create new bank
    */
  def create(bank: Bank): Future[Int] = db.run {
    bankTableAutoInc += bank
  }

  /**
    * @param bank
    * update existing bank
    */
  def update(bank: Bank): Future[Int] = db.run {
    bankTableQuery.filter(_.id === bank.id.get).update(bank)
  }

  /**
    * @param id
    * Get bank by id
    */
  def getById(id: Int): Future[Option[Bank]] = db.run {
    bankTableQuery.filter(_.id === id).result.headOption
  }

  /**
    * @return
    * Get all banks
    */
  def getAll(): Future[List[Bank]] = db.run {
    bankTableQuery.to[List].result
  }

  /**
    * @param id
    * delete bank by id
    */
  def delete(id: Int): Future[Int] = db.run {
    bankTableQuery.filter(_.id === id).delete
  }

}

private[repository] trait BankTable {
  this: DBComponent =>

  import driver.api._

  protected val bankTableQuery = TableQuery[BankTable]

  protected def bankTableAutoInc = bankTableQuery returning bankTableQuery.map(_.id)

  private[BankTable] class BankTable(tag: Tag) extends Table[Bank](tag, "bank") {
    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val name = column[String]("name")

   def * : ProvenShape[Bank] = (name, id.?) <> (Bank.tupled, Bank.unapply)

  }

}

object BankRepository extends BankRepository with MySqlDBComponent

case class Bank(name: String, id: Option[Int] = None)
