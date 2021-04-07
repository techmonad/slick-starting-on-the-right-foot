package com.techmonad

import com.techmonad.repository._
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util._

object Demo extends App {

  val logger = LoggerFactory.getLogger(this.getClass)

  val bankId = BankRepository.create(Bank("ICICI bank"))

  bankId.onComplete {
    case Success(id) =>

      BankProductRepository.create(BankProduct("car loan", id))
      val bankId = 1000
      BankInfoRepository.create(BankInfo("Goverment", bankId, id))
      BankRepository.create(Bank("SBI Bank"))
    case _ => logger.info("Error ...........")
  }

  BankInfoRepository.getAllBankWithInfo().foreach(bankInfo => logger.info("bankInfo: " + bankInfo ))

  BankProductRepository.getAllBankWithProduct().foreach(bankProduct => logger.info("bankProduct: " + bankProduct ))

  Thread.sleep(10 * 1000)

}
