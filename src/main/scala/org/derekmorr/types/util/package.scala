package org.derekmorr.types

import java.net.{Inet4Address, Inet6Address, InetAddress}

import com.google.common.primitives.UnsignedInteger
import com.google.common.net.{InetAddresses, InternetDomainName}
import eu.timepit.refined.api.Refined
import org.derekmorr.net.net.{DNS, IP, IPv4, IPv6}
import org.derekmorr.primitives.primitives.UInt

package object util {
  def ip(s: String Refined IP): InetAddress =
    InetAddresses.forString(s.value)

  def ipv4(s: String Refined IPv4): Inet4Address =
    InetAddresses.forString(s.value).asInstanceOf[Inet4Address]

  def ipv6(s: String Refined IPv6): Inet6Address =
    InetAddresses.forString(s.value).asInstanceOf[Inet6Address]

  def dns(s: String Refined DNS): InternetDomainName =
    InternetDomainName.from(s.value)

  def safe(s: String Refined SafeStringRegex): SafeString =
    s

  def uint(s: String Refined UInt): UnsignedInteger = 
    UnsignedInteger.valueOf(s.value)

  def uint(s: String Refined UInt, radix: Int): UnsignedInteger = 
    UnsignedInteger.valueOf(s.value, radix)
}
