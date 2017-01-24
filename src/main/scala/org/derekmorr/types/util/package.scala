package org.derekmorr.types

import java.net.InetAddress

import com.google.common.primitives.UnsignedInteger
import com.google.common.net.{InetAddresses, InternetDomainName}
import eu.timepit.refined.api.Refined
import org.derekmorr.net.net.{DNS, IP}
import org.derekmorr.primitives.primitives.UInt

package object util {
  def ip(s: String Refined IP): InetAddress =
    InetAddresses.forString(s.value)

  def dns(s: String Refined DNS): InternetDomainName =
    InternetDomainName.from(s.value)

  def safe(s: String Refined SafeStringRegex): SafeString =
    s

  def uint(s: String Refined UInt): UnsignedInteger = 
    UnsignedInteger.valueOf(s.value)

  def uint(s: String Refined UInt, radix: Int): UnsignedInteger = 
    UnsignedInteger.valueOf(s.value, radix)
}
