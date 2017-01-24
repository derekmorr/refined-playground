package org.derekmorr.primitives

import com.google.common.primitives.UnsignedInteger
import eu.timepit.refined.api.Validate
import org.derekmorr.primitives.primitives.UInt


object primitives extends PrimitivesValidate {
  final case class UInt()
}

private[primitives] trait PrimitivesValidate {
  implicit def unsignedIntValidate: Validate.Plain[String, UInt] =
    Validate.fromPartial(UnsignedInteger.valueOf, "UnsignedInteger", UInt())
}
