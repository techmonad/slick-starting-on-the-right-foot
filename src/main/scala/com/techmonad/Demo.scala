package com.techmonad

import com.techmonad.repository._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util._

object Demo extends App {

  val bankId = BankRepository.create(Bank("ICICI bank"))

  bankId.onComplete {
    case Success(id) =>

      BankProductRepository.create(BankProduct("car loan", id))
      BankInfoRepository.create(BankInfo("Goverment", 1000, id))
      BankRepository.create(Bank("SBI Bank"))
    case _ => println("Error ...........")
  }

  BankInfoRepository.getAllBankWithInfo().foreach(println)

  BankProductRepository.getAllBankWithProduct().foreach(println)

  Thread.sleep(10 * 1000)

}