package org.derekmorr.math

import com.google.common.primitives.{UnsignedInteger}
import eu.timepit.refined.api.Validate
import org.derekmorr.math.math.UInt

object math extends MathValidate {
  final case class UInt()
}

private[math] trait MathValidate {
  implicit def unsignedIntValidate: Validate.Plain[String, UInt] =
    Validate.fromPartial(UnsignedInteger.valueOf, "UnsignedInteger", UInt())
}
