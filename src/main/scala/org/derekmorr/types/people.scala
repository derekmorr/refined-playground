package org.derekmorr.types

import eu.timepit.refined.W
import eu.timepit.refined.api.{RefType, Refined}
import eu.timepit.refined.auto._
import eu.timepit.refined.boolean.And
import eu.timepit.refined.collection.{MaxSize, MinSize}
import eu.timepit.refined.numeric.{GreaterEqual, Less}
import eu.timepit.refined.types.numeric.PosInt
import Person._

case class Minor(name: Name, age: MinorAge)
case class Voter(name: Name, age: VoterAge)
case class Drinker(name: Name, age: DrinkerAge)
case class Retiree(name: Name, age: RetireeAge)


trait Person {
  def name: Name
  def age: PosInt
}

object Person {

  type Name = String Refined And[MinSize[W.`3`.T], MaxSize[W.`64`.T]]

  type MinorAge   = Int Refined Less[W.`18`.T]
  type VoterAge   = Int Refined GreaterEqual[W.`18`.T]
  type DrinkerAge = Int Refined GreaterEqual[W.`21`.T]
  type RetireeAge = Int Refined GreaterEqual[W.`65`.T]

  def drinkerToVoter(d: Drinker): Voter = Voter(d.name, d.age)

  def voterToDrinker(v: Voter): Option[Drinker] = {
    RefType.applyRef[DrinkerAge](v.age.value) match {
      case Left(_) => None
      case Right(age) => Some(Drinker(v.name, age))
    }
  }
}

