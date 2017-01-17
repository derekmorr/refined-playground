package org.derekmorr.net

import com.google.common.net.{InetAddresses, InternetDomainName}
import eu.timepit.refined.api.Validate
import org.derekmorr.net.net.{DNS, IP}

object net extends NetValidate {
  final case class IP()
  final case class DNS()
}

private[net] trait NetValidate {
  implicit def ipValidate: Validate.Plain[String, IP] =
    Validate.fromPartial(InetAddresses.forString, "IP Address", IP())

  implicit def dnsValidate: Validate.Plain[String, DNS] =
    Validate.fromPartial(InternetDomainName.from, "DNS name", DNS())
}
