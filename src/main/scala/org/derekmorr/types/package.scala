package org.derekmorr

import scala.util.matching.Regex

import eu.timepit.refined._
import eu.timepit.refined.auto._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.boolean.{And, Not}
import eu.timepit.refined.char.Whitespace
import eu.timepit.refined.collection.{Forall, NonEmpty}
import eu.timepit.refined.numeric.Interval
import eu.timepit.refined.string.MatchesRegex
import eu.timepit.refined.util.string.regex


package object types {

  type VlanType = Interval.Closed[W.`0`.T, W.`4096`.T]
  type VlanId = Int Refined VlanType

  type NonBlank = And[NonEmpty, Forall[Not[Whitespace]]]
  type NonBlankString = String Refined NonBlank

  private val safeStringRegex: Regex = regex("[a-zA-Z0-9.:_ \\[\\]\\/(){}-]{0,255}")

  // split b/c of https://stackoverflow.com/questions/41152928/compile-error-using-the-refined-constraint-interval-closed
  type SafeStringRegex = MatchesRegex[W.`"[a-zA-Z0-9.:_ (){}-]{0,255}"`.T]
  type SafeString = String Refined SafeStringRegex

  type NonBlankSafe = And[NonBlank, SafeStringRegex]
  type NonBlankSafeString = String Refined NonBlankSafe

}
