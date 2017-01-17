package org.derekmorr

import java.net.{InetAddress, URL}
import java.util.UUID

import eu.timepit.refined._
import eu.timepit.refined.auto._
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.types.net._
import eu.timepit.refined.util.string._
import org.derekmorr.net.net.IP
import org.derekmorr.types._
import org.derekmorr.types.util.{dns, ip}


object TestFixtures {

  val str = refineMV[NonEmpty]("hello")
  val vlanId: VlanId = 2020
  val vlan2: Either[String, VlanId] = refineV[VlanType](2)

  val safe1: SafeString = "hello"

  // runtime validation
  val notSafe: Either[String, SafeString] = refineV[SafeStringRegex]("OR '1'='1' --")

  // compile-time validation
  //  val notSafe2: SafeString = "Or '1'='1' --"

  val redHatURL: URL = url("https://redhat.psu.edu/")

  //--------------------------------

  // compile-time checking uses the base type + refinement
  val vmname: NonBlankString = "hi"

  // run-time checking uses the refinement only
  val runTimeNonBlank: Either[String, NonBlankString] = refineV[NonBlank]("runtime")

  val https: PortNumber = 443
//  val imaps = refineV[PortNumber](993)
  val imaps = Protocol("imaps", 993)
  //val invalid = Protocol("invalid", -1)

  // runtime:
  val v4loopback = refineV[IP]("127.0.0.1")
  val thingy: InetAddress = ip(v4loopback.right.get)

  // compile-time
  val v4Loopback2: InetAddress = ip("127.0.0.1")
  val v6Loopback: InetAddress = ip("::1")

  //val noOk = ip("999.999.999.999")

  val u: UUID = uuid("9adc9444-1cb6-464d-a4d4-03b484aa49ca")

  val h = dns("redhat.psu.edu")
  val notH = dns("lkjhlkjhlkjhlkjh`````;;")
}
